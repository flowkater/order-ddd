package dev.practice.order.domain.partner;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partnerToken;
    private String partnerName;
    private String businessNo;
    private String email;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        ENABLE("활성화"), DISABLE("비활성화");

        private final String description;
    }

    @Builder
    public Partner(String partnerName, String businessNo, String email) {
        if(partnerName == null) throw new RuntimeException("empty partnerName");
        if(businessNo == null) throw new RuntimeException("empty businessNo");
        if(email == null) throw new RuntimeException("empty email");

        this.partnerToken = "abcde";
        this.partnerName = partnerName;
        this.businessNo = businessNo;
        this.email = email;
        this.status = Status.ENABLE;
    }

    public void enable() {
        this.status = Status.ENABLE;
    }

    public void disable() {
        this.status = Status.DISABLE;
    }
}
