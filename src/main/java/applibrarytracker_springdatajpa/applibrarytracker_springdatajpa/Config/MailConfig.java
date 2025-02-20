package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.yandex.ru"); // SMTP-сервер Яндекса
        mailSender.setPort(465); // Порт для SSL
        mailSender.setUsername("nikolai.first21@yandex.ru"); // Email
        mailSender.setPassword("gddnajyotctfouss"); // Пароль приложения

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true"); // Используем SSL
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Указываем TLS 1.2
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");
        props.put("mail.smtp.writetimeout", "5000");

        return mailSender;
    }
}
