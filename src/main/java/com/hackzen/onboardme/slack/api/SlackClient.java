package com.hackzen.onboardme.slack.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SlackClient {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${slack.token}")
	private String slackToken;

	public GetUsersResponse getAllUsers() {
		String api = "https://slack.com/api/users.list";
		String token = "token=" + slackToken;

		ResponseEntity<GetUsersResponse> response = restTemplate.getForEntity(api + "?" + token,
				GetUsersResponse.class);

		return response.getBody();
	}

	public void sendMessage(String userId, String message) {
		SendMessageRequest sendMessageRequest = new SendMessageRequest(userId, message, "YourBuddy");
		String requestString = sendMessageRequest.marshal();

		String api = "https://slack.com/api/chat.postMessage";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", "Bearer " + slackToken);

		HttpEntity<String> entity = new HttpEntity<String>(requestString, headers);
		String result = restTemplate.postForObject(api, entity, String.class);

		log.info(result);
	}
}

@Data
@AllArgsConstructor
class SendMessageRequest {
	private String channel;

	private String text;

	private String username;

	public String marshal() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
}