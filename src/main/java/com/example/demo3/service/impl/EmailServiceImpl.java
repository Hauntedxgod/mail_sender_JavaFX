package com.example.demo3.service.impl;


import com.example.demo3.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;
import java.util.Properties;


@Service
@PropertySource("classpath:application.properties")
public class EmailServiceImpl implements EmailService {



    @Autowired
    private JavaMailSender javaMailSender = new JavaMailSenderImpl();

    @Autowired
    private JavaMailSenderImpl javaMailSenders = new JavaMailSenderImpl();


    @Override
    public String sendMail(File[] file , String[] cc, String subject, String body) {
        try {

            javaMailSenders.setHost("smtp.gmail.com");
            javaMailSenders.setPort(587);
            javaMailSenders.setUsername("kamal080176guz@gmail.com");
            javaMailSenders.setPassword("qzstwetpedbcyyka");

            Properties props = javaMailSenders.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            MimeMessage mimeMessage = javaMailSenders.createMimeMessage();


            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(Objects.requireNonNull(javaMailSenders.getUsername()));
            mimeMessageHelper.setTo(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);


            for (int i = 0; i < file.length; i++) {
                mimeMessageHelper.addAttachment(
                        file[i].getName(),
                        file[i].getAbsoluteFile());
            }


            javaMailSenders.send(mimeMessage); // здесь нету отправки файла через mimeMessage
            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
