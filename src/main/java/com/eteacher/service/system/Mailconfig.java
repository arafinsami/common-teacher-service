package com.eteacher.service.system;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


/*@Configuration
@RequiredArgsConstructor
public class Mailconfig {

    private final Environment env;

    @Bean(name = "mailSender")
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getProperty("myapp.host"));
        javaMailSender.setPort(Integer.parseInt(env.getProperty("myapp.port")));
        javaMailSender.setProtocol(env.getProperty("myapp.protocol"));
        javaMailSender.setUsername(env.getProperty("myapp.username"));
        javaMailSender.setPassword(env.getProperty("myapp.password"));
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(mailProperties);
        return javaMailSender;
    }
}*/
