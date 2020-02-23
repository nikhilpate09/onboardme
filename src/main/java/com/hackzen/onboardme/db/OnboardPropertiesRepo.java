package com.hackzen.onboardme.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardPropertiesRepo extends JpaRepository<OnboardProperties, Integer> {
	
}
