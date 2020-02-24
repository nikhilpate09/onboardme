package com.hackzen.onboardme.slack.api;

import com.hackzen.onboardme.db.Submission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DialogueRequest {

	private String type;

	private String token;

	private String action_ts;

	private Team team;

	private User user;

	private Channel channel;

	private Submission submission;

	private String callback_id;

	private String response_url;

	private String state;
}

class Team {
	private String id;

	private String domain;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDomain() {
		return this.domain;
	}
}

class User {
	private String id;

	private String name;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}

class Channel {
	private String id;

	private String name;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
