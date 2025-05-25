package br.com.reservapro.application.email;

import br.com.reservapro.domain.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendEmail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("reservaproautoatendimento@gmail.com");
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());

        mailSender.send(message);
    }
}