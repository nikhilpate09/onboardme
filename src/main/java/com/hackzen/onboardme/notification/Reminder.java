package com.hackzen.onboardme.notification;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hackzen.onboardme.db.ReminderInfo;
import com.hackzen.onboardme.db.ReminderInfoRepo;
import com.hackzen.onboardme.slack.api.SlackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Reminder {

	@Autowired
	private ReminderInfoRepo reminderInfoRepo;

	@Autowired
	private SlackService ss;

	private String hrUserId = "US6G31MMJ";

	private String adminUserId = "US6G31MMJ";

	@PostConstruct
	public void init() {
		remindToCreateIdCard();
	}

	@Scheduled(cron = "${submit.docs.cron.expression}")
	public void remindToSubmitDocs() {
		log.info("Backend::Current time is :: " + Calendar.getInstance().getTime());

		List<ReminderInfo> usersToSendReminder = reminderInfoRepo.findAll().stream().filter(this::shouldSendReminder)
				.collect(Collectors.toList());
		sendReminder(usersToSendReminder,
				"Please submit all required documents on GreytHR portal [http://appzen.greythr.com/]!!");
	}

	private boolean shouldSendReminder(ReminderInfo ri) {
		LocalDate now = LocalDate.now();
		return ri.getJoiningDate().equals(now);
	}

	private void sendReminder(List<ReminderInfo> usersToSendReminder, String reminderText) {
		usersToSendReminder.stream().forEach(u -> sendMessage(u.getUserId(), reminderText));
	}

	private void sendMessage(String userId, String reminderText) {
		ss.sendMessage(userId, reminderText);
	}

	@Scheduled(cron = "${complete.training.cron.expression}")
	public void remindToCompleteTraining() {
		log.info("Backend::Current time is :: " + Calendar.getInstance().getTime());

		List<ReminderInfo> usersToSendReminder = reminderInfoRepo.findAll().stream().filter(this::shouldSendReminder)
				.collect(Collectors.toList());
		sendReminder(usersToSendReminder,
				"Please complete onboarding training from AppZen university [https://appzen.litmos.com/home/dashboard] !!");

	}

	@Scheduled(cron = "${send.channels.list.cron.expression}")
	public void remindToJoinChannels() {
		log.info("Backend::Current time is :: " + Calendar.getInstance().getTime());
		Set<String> channels = new HashSet<>();

		channels.add("#team-picazen");
		channels.add("#team-picazen-india");
		channels.add("#hr");
		channels.add("#pune");

		List<ReminderInfo> usersToSendReminder = reminderInfoRepo.findAll().stream().filter(this::shouldSendReminder)
				.collect(Collectors.toList());
		sendReminder(usersToSendReminder, "Buddy, here is the list of channels you should join !!" + channels);
	}

	@Scheduled(cron = "0 0 12 * * *")
	public void remindToCreateIdCard() {
		log.info("Backend::Current time is :: " + Calendar.getInstance().getTime());

		List<ReminderInfo> usersToSendReminder = reminderInfoRepo.findAll().stream().filter(this::shouldSendReminder)
				.collect(Collectors.toList());
		usersToSendReminder.stream().forEach(this::remindAdminForUser);
	}

	private void remindAdminForUser(ReminderInfo ri) {
		String remindText = "Hey admin, reminder to create ID for our new hire " + ri.getName();
		sendMessage(adminUserId, remindText);
	}
}
