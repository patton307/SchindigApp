//package com.schindig;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import java.util.Properties;
//
///**
// * Created by Agronis on 12/16/15.
// */
//@Configurable
//public class AppConfig {
//    @Bean
//    public JavaMailSenderImpl javaMailSenderImpl(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        //Set gmail email id
//        mailSender.setUsername("arvindraivns06@gmail.com");
//        //Set gmail email password
//        mailSender.setPassword("password");
//        Properties prop = mailSender.getJavaMailProperties();
//        prop.put("mail.transport.protocol", "smtp");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.debug", "true");
//        return mailSender;
//    }
//
//
//}
