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
}
