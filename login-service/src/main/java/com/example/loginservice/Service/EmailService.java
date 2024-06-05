package com.example.loginservice.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Đặt lại mật khẩu");
            message.setText(body);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateCode() {
        Random random = new Random(); // random mã để gửi mail
        String code = "";
        int s1 = random.nextInt(10);
        int s2 = random.nextInt(10);
        int s3 = random.nextInt(10);
        int s4 = random.nextInt(10);
        int s5 = random.nextInt(10);
        int s6 = random.nextInt(10);
        code = Integer.toString(s1) + Integer.toString(s2) + Integer.toString(s3) + Integer.toString(s4)
                + Integer.toString(s5) + Integer.toString(s6);
        return code;
    }
}
