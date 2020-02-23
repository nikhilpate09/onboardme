package com.hackzen.onboardme.command.handler;

import org.springframework.stereotype.Service;

import com.hackzen.onboardme.CommandRequest;

@Service
public class HelpCommandHandler implements CommandHandler {

	@Override
	public CommandRequest handle(String commandText) {
		String responseString = "{\n" + 
				"	\"type\": \"modal\",\n" + 
				"	\"title\": {\n" + 
				"		\"type\": \"plain_text\",\n" + 
				"		\"text\": \"My App\",\n" + 
				"		\"emoji\": true\n" + 
				"	},\n" + 
				"	\"submit\": {\n" + 
				"		\"type\": \"plain_text\",\n" + 
				"		\"text\": \"Submit\",\n" + 
				"		\"emoji\": true\n" + 
				"	},\n" + 
				"	\"close\": {\n" + 
				"		\"type\": \"plain_text\",\n" + 
				"		\"text\": \"Cancel\",\n" + 
				"		\"emoji\": true\n" + 
				"	},\n" + 
				"	\"blocks\": [\n" + 
				"		{\n" + 
				"			\"type\": \"section\",\n" + 
				"			\"text\": {\n" + 
				"				\"type\": \"mrkdwn\",\n" + 
				"				\"text\": \"please submit it \"\n" + 
				"			},\n" + 
				"			\"accessory\": {\n" + 
				"				\"type\": \"button\",\n" + 
				"				\"text\": {\n" + 
				"					\"type\": \"plain_text\",\n" + 
				"					\"text\": \"Button\",\n" + 
				"					\"emoji\": true\n" + 
				"				},\n" + 
				"				\"value\": \"click_me_123\"\n" + 
				"			}\n" + 
				"		}\n" + 
				"	]\n" + 
				"}";
		
		CommandRequest cr = CommandRequest.builder().text(responseString).channel_name("somechannel")
				.build();
		return cr;
	}

}
