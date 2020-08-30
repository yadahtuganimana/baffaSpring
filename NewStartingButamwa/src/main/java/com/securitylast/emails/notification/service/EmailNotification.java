package com.securitylast.emails.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.securitylast.domain.User;

@Service
public class EmailNotification {

 private JavaMailSender javamailsender;
 @Autowired
 public EmailNotification(JavaMailSender javamailsender) {
	this.javamailsender=javamailsender; 
	 
 }
 public void sendNotification(User user) throws MailException{
	SimpleMailMessage mail = new SimpleMailMessage();
	mail.setTo(user.getUsername());
	mail.setFrom("butamwaSite@gmail.com");
	mail.setSubject("Butamwaconfirmation email");
	mail.setText("Hi"+" "+user.getUserfirstname().toUpperCase()+"thank you for your registration wait for admin to approve you login credentials");
	
	javamailsender.send(mail);
	
 } 
}
