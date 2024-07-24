package com.example.G_Books.service;

import com.example.G_Books.request.EmailTemplateName;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail (String to ,String username ,EmailTemplateName emailTemplateName ,String confirmationUrl ,
                          String activationCode ,String subject)throws MessagingException {

        String templateName;
        if(emailTemplateName ==  null){
            templateName = "confirm email";
        }else{
            templateName = emailTemplateName.name();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name()
        );

        Map<String ,Object> properties = new HashMap<>();
        properties.put(" username " , username);
        properties.put(" Confirmation_Url ",confirmationUrl);
        properties.put(" Activation_Code ",activationCode);

        Context context = new Context();
        context.setVariables(properties);

        mimeMessageHelper.setFrom("oualidchoukri0@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);

        String template = templateEngine.process(templateName , context);

        mimeMessageHelper.setText(template,true);

        mailSender.send(mimeMessage);
    }
}
