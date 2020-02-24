package com.hackzen.onboardme.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Async
	public void sendWelcomeEmail(String emailId, String body) {
		String subject = "Welcome !!";
		
		try {
			sendEmail(subject, body, emailId);
		} catch (MessagingException e) {
			log.error("Error in sending email..", e);
		}
	}

	private void sendEmail(String subject, String body, String recipient) throws MessagingException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true);
		mimeMessageHelper.setTo(recipient);

		mimeMessageHelper.setSubject(subject);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(body);
		mimeMessageHelper.setText(stringBuilder.toString(), true);

		javaMailSender.send(msg);
	}
}
