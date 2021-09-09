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

    public void sendVerificationEmail(User user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String verifyURL =siteURL + "/auth/verify?code=" +user.getVerificationCode() ;
        System.out.println(verifyURL);
        String subject = "Please verify your registration";
        String senderName = "admin";
        String mailContent = "<p>Dear " + user.getName()+",</p>";
        mailContent += "<p>Please click the link below to verify your registration:</p>";
        mailContent += "<h3><a href=\""+verifyURL+"\">VERIFY</a></h3>";
        mailContent += "<p>Thank you</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("dongdd159@gmail.com",senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        mailSender.send(message);

    }
    public void sendVerificationEmailCompany(Company company, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String verifyURL =siteURL + "/auth/verify?code=" +company.getVerificationCode() ;
        System.out.println(verifyURL);
        String subject = "Please verify your registration";
        String senderName = "admin";
        String mailContent = "<p>Dear " + company.getCompanyName()+",</p>";
        mailContent += "<p>Please click the link below to verify your registration:</p>";
        mailContent += "<h3><a href=\""+verifyURL+"\">VERIFY</a></h3>";
        mailContent += "<p>Thank you</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("dongdd159@gmail.com",senderName);
        helper.setTo(company.getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent,true);
        mailSender.send(message);

    }
}
