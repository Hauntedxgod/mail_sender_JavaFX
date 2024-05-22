package com.example.demo3.service;

import java.io.File;

public interface EmailService {

    // попробовать отразить в котроллере
    String sendMail(File[] file , String[] cc, String subject, String body);
}
