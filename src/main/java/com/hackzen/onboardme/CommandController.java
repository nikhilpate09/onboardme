package com.hackzen.onboardme;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackzen.onboardme.db.Submission;
import com.hackzen.onboardme.service.OnboardmeService;
import com.hackzen.onboardme.slack.api.DialogueRequest;
import com.hackzen.onboardme.slack.api.OpenFillupFormRequest;
import com.hackzen.onboardme.slack.api.SlackClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommandController {

	@Autowired
	private OnboardmeService onboardMeService;

	@Autowired
	private SlackClient sc;

	@PostMapping(value = "/buddy", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String processCommand(@RequestBody MultiValueMap<String, String> request) {
		log.info("Request: {}", request);

		return onboardMeService.process(request);
	}

	@PostMapping(value = "/fillup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void fillupFormCommand(@RequestBody MultiValueMap<String, String> request) {
		log.info("Request: {}", request);
		sc.openDialogue(request.get("trigger_id").get(0));
	}

	@PostMapping(value = "/admin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String processAdminCommand(@RequestBody MultiValueMap<String, String> request) {
		log.info("Request: {}", request);

		return onboardMeService.initiateOnboardingProcess(request);
	}

	@PostMapping(value = "/onboard/action", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity cmd(@RequestBody MultiValueMap<String, String> request)
			throws JsonParseException, JsonMappingException, IOException {
		log.info("Request: {}", request);

		String payload = request.get("payload").get(0);

		if (payload.contains("openForm")) {
			ObjectMapper mapper = new ObjectMapper();
			OpenFillupFormRequest readValue = mapper.readValue(payload.getBytes(), OpenFillupFormRequest.class);
			String triggerId = readValue.getTrigger_id();
			sc.openDialogue(triggerId);
		} else {
			onboardMeService.processDialogue(request);
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping("/users/pull")
	public String saveUsers() {
		onboardMeService.pullUsersAndSave();
		return "Success";
	}
}
