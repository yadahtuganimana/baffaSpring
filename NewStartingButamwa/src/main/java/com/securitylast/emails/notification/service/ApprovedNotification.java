package com.securitylast.emails.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.securitylast.domain.User;

@Service
public class ApprovedNotification {

 private JavaMailSender javamailsender;
 @Autowired
 public ApprovedNotification(JavaMailSender javamailsender) {
	this.javamailsender=javamailsender; 
	 
 }
 public void sendMyEmail (User user) throws MailException{
	SimpleMailMessage mail = new SimpleMailMessage();
	mail.setTo(user.getUsername());
	mail.setFrom("butamwaSite@gmail.com");
	mail.setSubject("ButamwaConfirmation_Email");
	mail.setText("Hi"+" "+user.getUserfirstname().toUpperCase()+" your Login credentials has been approved you can now login in ButamwaFirm Stytem");
	
	javamailsender.send(mail);
	
 } 
}
