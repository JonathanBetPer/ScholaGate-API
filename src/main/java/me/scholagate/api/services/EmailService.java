package me.scholagate.api.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendPasswordSetupEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jonathanbetper@gmail.com");
        message.setTo(to);
        message.setSubject("Setup your password");
        message.setText("Please click the following link to setup your password: "
                + "http://localhost:8080/passwd/" + token);
        emailSender.send(message);
    }
}