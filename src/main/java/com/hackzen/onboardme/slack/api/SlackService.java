package com.hackzen.onboardme.slack.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hackzen.onboardme.db.UserInfo;
import com.hackzen.onboardme.db.UserInfoRepo;

@Service
public class SlackService {

	@Autowired
	private SlackClient sc;
	
	@Autowired
	private UserInfoRepo repo;

	public UserInfo getUserByEmailId(String emailId) {
		return repo.findByEmailId(emailId);
	}
	
	@Async
	public void sendMessage(String userId, String message) {
		sc.sendMessage(userId, message);
	}
	
	public void pullUsersAndSave() {
		GetUsersResponse allUsers = sc.getAllUsers();
		
		allUsers.getMembers().stream().map(this::convert).forEach(repo::save);
	}
	
	private UserInfo convert(Members members) {
		Profile profile = members.getProfile();
		
		return UserInfo.builder().name(profile.getReal_name()).emailId(profile.getEmail())
				.teamId(profile.getTeam()).userId(members.getId()).build();
	}
}
