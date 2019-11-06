package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("sender", adminConfig.getSender());
        context.setVariable("company_details", adminConfig.getCompanyName() + "\n" +
                                                            adminConfig.getCompanyGoal() + "\n" +
                                                            adminConfig.getCompanyMail() + "\n" +
                                                            adminConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildOnceADayEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        List<String> companyDetails = new ArrayList<>();
        companyDetails.add(adminConfig.getCompanyName());
        companyDetails.add(adminConfig.getCompanyGoal());
        companyDetails.add(adminConfig.getCompanyMail());
        companyDetails.add(adminConfig.getCompanyPhone());

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("trello_url", "https://trello.com/b/YgoSTegK/kodilla-application");
        context.setVariable("button", "Visit Tasks Application website");
        context.setVariable("trello_site", "Go to trello website");
        context.setVariable("company_details", companyDetails);
        context.setVariable("show_button", true);
        context.setVariable("trello_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/once-a-day-mail", context);
    }
}