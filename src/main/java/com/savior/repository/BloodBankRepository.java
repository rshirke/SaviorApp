package com.savior.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savior.model.security.BloodBank;

public interface BloodBankRepository extends JpaRepository<BloodBank, Long>{
	
}
