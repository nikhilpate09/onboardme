package com.hackzen.onboardme.slack.api;

import java.util.List;
import java.util.Optional;

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
		List<UserInfo> findAll = repo.findAll();

		allUsers.getMembers().stream().filter(m -> isNotPresentInDB(m, findAll)).map(this::convert).forEach(repo::save);
	}

	private boolean isNotPresentInDB(Members members, List<UserInfo> dbUsers) {
		String user_id = members.getId();
		Optional<UserInfo> result = dbUsers.stream().filter(u -> user_id.equalsIgnoreCase(u.getUserId())).findAny();

		return !result.isPresent();
	}

	private UserInfo convert(Members members) {
		Profile profile = members.getProfile();

		return UserInfo.builder().name(profile.getReal_name()).emailId(profile.getEmail()).teamId(profile.getTeam())
				.userId(members.getId()).build();
	}

	public void sendInformationSavedMessage(String responseUrl) {
		sc.sendInformationSavedMessage(responseUrl);
	}

	public void sendOnbordingFormMessage(String userId) {
		sc.sendMessageForOnboardForm(userId);
	}
}
