package com.savior.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.savior.model.security.BloodBank;
import com.savior.repository.BloodBankRepository;

@RestController
@RequestMapping("/bloodbanks")
public class BloodBankRestController {

	@Autowired
	BloodBankRepository bbRepo;

	@RequestMapping("/getallbb")
	 @PreAuthorize("hasRole('USER')")
	public List<BloodBank> listBloodBanks() {
		return bbRepo.findAll();
	}
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/getallbb1" , method = RequestMethod.POST )
	public List<BloodBank> listBloodBankspost() {
		return bbRepo.findAll();
	}

	 @RequestMapping("/{id}")
	 public BloodBank getById(@PathVariable Long id) {
	 return bbRepo.findOne(id);
	 }

}
