package com.artisan.vitrine.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

@Service
public class EmailService {

    @Value("${SENDGRID_API_KEY}")
    private String sendgridApiKey;

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendEmail(String userEmail, String subject, String messageContent) throws IOException {

        Email from = new Email("noreply@libertyceramique.com");
        Email to = new Email("libertyceramique@gmail.com");
        Content content = new Content("text/plain", messageContent);
        Mail mail = new Mail(from, subject, to, content);

        mail.setReplyTo(new Email(userEmail));

        MailSettings mailSettings = new MailSettings();
        Setting sandbox = new Setting();
        sandbox.setEnable(true);
        mailSettings.setSandboxMode(sandbox);
        mail.setMailSettings(mailSettings);

        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        logger.info("=== Mail simulé ===");
        logger.info("From: {}", from.getEmail());
        logger.info("To: {}", to.getEmail());
        logger.info("Reply-To: {}", userEmail);
        logger.info("Subject: {}", subject);
        logger.info("Body: {}", messageContent);
        logger.info("===================");
    }
}

