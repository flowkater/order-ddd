package dev.practice.order.infrastructure;

import dev.practice.order.domain.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationExecutor implements NotificationService {

    @Override
    public void sendEmail(String email, String title, String description) {
        log.info("sendEmail: " + email + " " + title + " " + description);
    }

    @Override
    public void sendKakao(String phoneNo, String description) {
        log.info("sendKakao: " + phoneNo + " " + description);
    }

    @Override
    public void SendSms(String phoneNo, String description) {
        log.info("SendSms: " + phoneNo + " " + description);
    }
}
