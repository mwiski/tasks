package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${spring.mail.username}")
    private String adminMail;

    @Value("${spring.mail.admin}")
    private String adminName;

    @Value("${spring.mail.sender}")
    private String sender;

    @Value("${info.company.name}")
    private String company_name;

    @Value("${info.company.goal}")
    private String company_goal;

    @Value("${info.company.email}")
    private String company_mail;

    @Value("${info.company.phone}")
    private String company_phone;
}
