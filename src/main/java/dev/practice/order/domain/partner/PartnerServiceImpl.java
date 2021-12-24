package dev.practice.order.domain.partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerStore partnerStore;

    @Override
    @Transactional
    public PartnerInfo registerPartner(PartnerCommand command) {
        var initPartner = command.toEntity();
        Partner partner = partnerStore.store(initPartner);
        return new PartnerInfo(partner);
    }

    @Override
    public PartnerInfo getPartnerInfo(String partnerToken) {
        return null;
    }

    @Override
    public PartnerInfo enablePartner(String partnerToken) {
        return null;
    }

    @Override
    public PartnerInfo disablePartner(String partnerToken) {
        return null;
    }
}
