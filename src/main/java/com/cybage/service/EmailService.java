package com.cybage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail(String to, String subject, String message) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(to);
		helper.setFrom("abhijeetgu@evolvingsols.com");
		helper.setSubject(subject);
		helper.setText(message, true);

		javaMailSender.send(msg);
	}

}

/*
 * String emailBody = ("To confirm your account, please click here : "
				+ "http://172.27.232.112:8080/api/user/confirm-account?token="
				+ confirmationToken.getConfirmationToken());
		String emailSubject = "Complete Registration!";
		emailService.sendEmail(user.getEmail(), emailSubject, emailBody);
 * */
