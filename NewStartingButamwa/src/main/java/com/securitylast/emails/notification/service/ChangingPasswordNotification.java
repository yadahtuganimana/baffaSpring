package com.securitylast.emails.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.securitylast.domain.User;

@Service
public class ChangingPasswordNotification {

 private JavaMailSender javamailsender;
 @Autowired
 public ChangingPasswordNotification(JavaMailSender javamailsender) {
	this.javamailsender=javamailsender; 
	 
 }
 public void sendNotification(User user) throws MailException{
	SimpleMailMessage mail = new SimpleMailMessage();
	mail.setTo(user.getUsername());
	mail.setFrom("butamwaSite@gmail.com");
	mail.setSubject("ButamwaFirm Link To changePassword");
	mail.setText("Hi"+" "+user.getUserfirstname().toUpperCase()+"The following is the link to change your password<br><br>"+"http://localhost:8010/butamwa/login");
	
	javamailsender.send(mail);
	
 } 
}
