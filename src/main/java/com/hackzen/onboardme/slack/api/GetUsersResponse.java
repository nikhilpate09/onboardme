package com.hackzen.onboardme.slack.api;

import java.util.List;

import lombok.Data;

@Data
public class GetUsersResponse {
	private boolean ok;

	private List<Members> members;
}

@Data
class Profile {
	private String title;

	private String phone;

	private String skype;

	private String real_name;

	private String real_name_normalized;

	private String display_name;

	private String display_name_normalized;

	private String status_text;

	private String status_emoji;

	private int status_expiration;

	private String avatar_hash;

	private String image_original;

	private boolean is_custom_image;

	private String email;

	private String first_name;

	private String last_name;

	private String image_24;

	private String image_32;

	private String image_48;

	private String image_72;

	private String image_192;

	private String image_512;

	private String image_1024;

	private String status_text_canonical;

	private String team;

}

@Data
class Members {
	private String id;

	private String team_id;

	private String name;

	private boolean deleted;

	private String color;

	private String real_name;

	private String tz;

	private String tz_label;

	private int tz_offset;

	private Profile profile;
}
