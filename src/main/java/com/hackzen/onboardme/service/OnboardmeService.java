package com.hackzen.onboardme.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackzen.onboardme.db.DatabaseCache;
import com.hackzen.onboardme.db.Submission;
import com.hackzen.onboardme.db.UserInfo;
import com.hackzen.onboardme.db.UserMetaDataRepo;
import com.hackzen.onboardme.slack.api.DialogueRequest;
import com.hackzen.onboardme.slack.api.SlackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OnboardmeService {

	@Autowired
	private SlackService slackService;

	@Autowired
	private DatabaseCache dbCache;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserMetaDataRepo umdr;

	public String initiateOnboardingProcess(MultiValueMap<String, String> request) {
		String attributeValue = getAttributeValue("text", request);
		UserInfo userInfo = slackService.getUserByEmailId(attributeValue);

		if (userInfo == null) {
			String welcomeMessage = "Welcome to AppZen !! Please create account on Slack. "
					+ "You don't need to ask anyone about onboarding, " + "'YourBuddy' on slack will help you !!"
					+ "How to start ? Try command" + "\n\t/buddy help me";

			emailService.sendWelcomeEmail(attributeValue, welcomeMessage);
		} else {
			String name = userInfo.getName();
			String slackWelcomeMessage = "Welcome " + name + " to AppZen !! \nPlease create account on Slack. "
					+ "You don't need to ask anyone about onboarding, " + "'YourBuddy' on slack will help you !!"
					+ "How to start ? Try below command" + "\n\t/buddy help me";

			slackService.sendMessage(userInfo.getUserId(), slackWelcomeMessage);
			dbCache.saveReminderInfo(userInfo);
		}

		return "Onboarding initiated !!";
	}

	public String sendOnboardingForm(MultiValueMap<String, String> request) {
		String attributeValue = getAttributeValue("text", request);
		UserInfo userInfo = slackService.getUserByEmailId(attributeValue);
		slackService.sendOnbordingFormMessage(userInfo.getUserId());
		return "Onboarding form sent !!";
	}

	public String process(MultiValueMap<String, String> request) {
		String command = getAttributeValue("text", request);

		return process(command);
	}

	private String process(String commandtext) {
		String commandKey = CommandProcessor.extractKey(commandtext);
		String info = dbCache.getInfo(commandKey);

		if (StringUtils.isEmpty(info)) {
			return "Didn't get that !! Try `/buddy help me` for more info !!";
		}
		return info.replaceAll("LB", System.lineSeparator()).replaceAll("TAB", "    ");
	}

	@Async
	public void processDialogue(MultiValueMap<String, String> request) {
		String payload = getAttributeValue("payload", request);
 
		ObjectMapper mapper = new ObjectMapper();
		try {
			DialogueRequest readValue = mapper.readValue(payload.getBytes(), DialogueRequest.class);
			Submission submission = readValue.getSubmission();
			umdr.save(submission);
			slackService.sendInformationSavedMessage(readValue.getResponse_url());
		} catch (IOException e) {
			return;
		}
	}

	private String getAttributeValue(String key, MultiValueMap<String, String> request) {
		return request.get(key).get(0);
	}

	public void pullUsersAndSave() {
		slackService.pullUsersAndSave();
	}
}
