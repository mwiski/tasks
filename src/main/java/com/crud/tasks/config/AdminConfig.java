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
    private String companyName;

    @Value("${info.company.goal}")
    private String companyGoal;

    @Value("${info.company.email}")
    private String companyMail;

    @Value("${info.company.phone}")
    private String companyPhone;

    @Value("${info.app.name}")
    private String appName;
}
