package com.codegym.demo.service.email;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;

    public void sendVerificationEmail(User user)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Register notification";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getName()+",</p>";
        mailContent += "<p> Bạn đã đăng ký thành công </p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom("dongdd159@gmail.com",senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        mailSender.send(message);
    }
    public void sendVerificationEmailCompany(Company company)
            throws MessagingException, UnsupportedEncodingException {
        String subject = "Register notification";
        String senderName = "admin";
        String mailContent = "<p>Dear " + company.getCompanyName()+",</p>";
        mailContent += "<p> Bạn đã đăng ký thành công </p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        helper.setFrom("dongdd159@gmail.com",senderName);
        helper.setTo(company.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        mailSender.send(message);

    }
}
