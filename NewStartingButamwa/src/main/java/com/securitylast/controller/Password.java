package com.securitylast.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.securitylast.domain.User;
import com.securitylast.emails.notification.service.ChangingPasswordNotification;
import com.securitylast.interfaces.UserInt;

@Controller
@RequestMapping("/password")
public class Password {
	private Logger logger = LoggerFactory.getLogger(Password.class);
	@Autowired
	private UserInt userint;
	@Autowired
	private ChangingPasswordNotification changepass;

	@GetMapping("/changeyourpassword")
	public String checkPassword(Model model) {
		model.addAttribute("user", new User());
		return "changepassword/index";
	}

	@PostMapping("/getpst")
	public String getPassword(@Valid @ModelAttribute("user") User user, RedirectAttributes redirect,
			BindingResult binding) {
		if (binding.hasErrors()) {
			return "changepassword/index";
		}
		User existing = userint.findbyname(user.getUsername());
		if (existing == null) {
			binding.rejectValue("username", null, "no such email address in our database");
		}
		try {
			user.setUsername(user.getUsername());
			changepass.sendNotification(user);
		} catch (MailException e) {

			logger.info("message errore for email here" + e.getMessage());
		}
		return "";
	}
}
