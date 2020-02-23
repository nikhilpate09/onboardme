package com.hackzen.onboardme.db;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DatabaseCache {
	private Map<String, String> onboardProperties = new HashMap<>();

	@Autowired
	private OnboardPropertiesRepo repo;

	@Autowired
	private ReminderInfoRepo reminderInfoRepo;

	@PostConstruct
	public void init() {
		repo.findAll().stream().forEach(this::saveInMap);
	}

	private void saveInMap(OnboardProperties op) {
		onboardProperties.put(op.getPropertyKey(), op.getPropertyValue());
	}

	public String getInfo(String key) {
		return onboardProperties.get(key);
	}

	@Async
	public void saveReminderInfo(UserInfo userInfo) {
		ReminderInfo ri = ReminderInfo.builder().joiningDate(LocalDate.now()).userId(userInfo.getUserId())
				.name(userInfo.getName()).build();
		reminderInfoRepo.save(ri);
	}
	
	public String createHelpText() {
		StringBuilder sb = new StringBuilder("Try below commands with /buddy").append(System.lineSeparator());
		repo.findAll().stream().forEach(op -> sb.append(op.getPropertyKey()).append(": ").append(op.getDescription()).append(System.lineSeparator()));
		return sb.toString();
	}
}
