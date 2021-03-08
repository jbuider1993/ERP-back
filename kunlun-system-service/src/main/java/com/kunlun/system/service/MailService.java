package com.kunlun.system.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {
    private Logger log = LogManager.getLogger();

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String userName;

    public void sendSimpleMail(String receiver, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(userName);
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        try {
            mailSender.send(mailMessage);
            log.info("simple email sended");
        } catch (MailException e) {
            log.error("MailService sendSimpleMail Error: ", e);
        }
    }

    public void sendHtmlMail(String receiver, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(userName);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
            log.info("html email sended");
        } catch (MessagingException e) {
            log.error("MailService sendHtmlMail Error: ", e);
        }
    }

    public void sendAttachmentMail(String receiver, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(userName);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);

            mailSender.send(message);
            log.info("attachment email sended");
        } catch (MessagingException e) {
            log.error("MailService sendAttachmentMail Error: ", e);
        }
    }

    public void sendResourceMail(String receiver, String subject, String content, String filePath, String resId) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(userName);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addInline(resId, file);

            mailSender.send(message);
            log.info("resource email sended");
        } catch (MessagingException e) {
            log.error("MailService sendResourceMail Error: ", e);
        }
    }
}
