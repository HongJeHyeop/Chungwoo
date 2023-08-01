package com.transport.cw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {
    @Value("${spring.mail.username}")
    private String ADMIN_EMAIL;

    private final JavaMailSender javaMailSender;

    public String generateRandomAuth() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

    @Async
    public void sendEmail(String to, String randomAuth) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("이메일 인증요청 [청우국제운송(주)]");
        String text = "인증번호 : " + randomAuth;
        message.setText(text, "utf-8");
        message.setFrom(new InternetAddress(ADMIN_EMAIL, "청우국제운송(주)"));
        javaMailSender.send(message);
    }
}
