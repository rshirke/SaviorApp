package com.savior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savior.model.security.BloodBank;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Long> {

	
}
