package dev.practice.order.infrastructure.item;

import dev.practice.order.domain.item.Item;
import dev.practice.order.domain.item.ItemCommand;
import dev.practice.order.domain.item.ItemOptionSeriesFactory;
import dev.practice.order.domain.item.ItemOptionGroup;
import dev.practice.order.domain.item.ItemOptionGroupStore;
import dev.practice.order.domain.item.ItemOptionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemOptionSeriesFactoryImpl implements ItemOptionSeriesFactory {
    private final ItemOptionGroupStore itemOptionGroupStore;
    private final ItemOptionStore itemOptionStore;

    @Override
    public List<ItemOptionGroup> store(ItemCommand.RegisterItemRequest command, Item item) {
        var itemOptionGroupRequestList = command.getItemOptionGroupRequestList();
        if (CollectionUtils.isEmpty(itemOptionGroupRequestList)) return Collections.emptyList();

        return itemOptionGroupRequestList.stream()
                .map(requestItemOptionGroup -> {
                   var initItemOptionGroup = requestItemOptionGroup.toEntity(item);
                   var itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup);

                   requestItemOptionGroup.getItemOptionRequestList().forEach(requestItemOption -> {
                       var itemOption = requestItemOption.toEntity(itemOptionGroup);
                       itemOptionStore.store(itemOption);
                   });

                   return itemOptionGroup;
                }).collect(Collectors.toList());
    }
}
