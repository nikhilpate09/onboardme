package com.hackzen.onboardme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import com.hackzen.onboardme.db.DatabaseCache;
import com.hackzen.onboardme.db.UserInfo;
import com.hackzen.onboardme.slack.api.SlackService;

@Service
public class OnboardmeService {
	
	@Autowired
	private SlackService slackService;
	
	@Autowired	
	private DatabaseCache dbCache;
	
	public String initiateOnboardingProcess(MultiValueMap<String, String> request) {
		String attributeValue = getAttributeValue("text", request);
		UserInfo userInfo = slackService.getUserByEmailId(attributeValue);
		String welcomeMessage = "Welcome " + userInfo.getName() + " to Appzen !!";

		slackService.sendMessage(userInfo.getUserId(), welcomeMessage);
		dbCache.saveReminderInfo(userInfo);

		return "Onboarding initiated !!";
	}

	public String process(MultiValueMap<String, String> request) {
		String command = getAttributeValue("text", request);
		
		return process(command);
	}

	private String process(String commandtext) {
		String commandKey = CommandProcessor.extractKey(commandtext);
		
		if("help_me".equalsIgnoreCase(commandKey)) {
			return createHelpText();
		}
		
		String info = dbCache.getInfo(commandKey);
		
		if(StringUtils.isEmpty(info)) {
			return "Didn't get that !! Try /buddy help me for more info !!";
		}

		return info;
	}
	
	private String createHelpText() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("*No worries friend, 'YourBuddy' is here for you !!* :sunglasses:").append(System.lineSeparator())
		.append("\t```Try /buddy <whatever command you want to give> ").append(System.lineSeparator())
		.append("\tObviously you need examples to understand.. Here are those,").append(System.lineSeparator())
		.append("\t\twhat is appzen").append(System.lineSeparator())
		.append("\t\twhere is meeting room x").append(System.lineSeparator())
		.append("\t\twhere is cafeteria...").append(System.lineSeparator())
		.append("\t\twhat is leave policy...").append(System.lineSeparator())
		.append("\t\thow to open pf account...").append(System.lineSeparator())
		.append("\tAdditionally, YourBuddy will remind you to submit the documents, it will also remind admin for your ID card creation.```").append(System.lineSeparator())
		.append(System.lineSeparator())
		.append("\tAny help you need, just ping _YourBuddy_ !!").append(System.lineSeparator());
		
		return sb.toString();
	}

	private String getAttributeValue(String key, MultiValueMap<String, String> request) {
		return request.get("text").get(0);
	}
	
	public void pullUsersAndSave() {
		slackService.pullUsersAndSave();
	}
}
