package com.courseportal.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


public class SendMailService {

    private MailSender mailSender;
    
    private SimpleMailMessage templateMessage;
    
    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }


    /**
     * This method will send compose and send the message 
     * */
    public void sendMail(String to, String from, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}