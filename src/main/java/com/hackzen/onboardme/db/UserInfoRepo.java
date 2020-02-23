package com.hackzen.onboardme.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo, Integer> {
	UserInfo findByEmailId(String emailId);
}
