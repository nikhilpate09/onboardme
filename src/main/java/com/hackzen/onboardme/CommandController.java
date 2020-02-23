package com.hackzen.onboardme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackzen.onboardme.service.OnboardmeService;
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
	
	@PostMapping(value = "/admin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String processAdminCommand(@RequestBody MultiValueMap<String, String> request) {
		log.info("Request: {}", request);
		
		return onboardMeService.initiateOnboardingProcess(request);
	}

	@PostMapping(value = "/onboard/action", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String cmd(@RequestBody MultiValueMap<String, String> request) {
		System.out.println("--> " + request);
		return "FromNewSpringBootApp";

	}
	
	@GetMapping("/users/pull")
	public String saveUsers() {
		onboardMeService.pullUsersAndSave();
		return "Success";
	}
}
