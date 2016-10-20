package com.savior;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bloodbanks")
public class BloodBankRestController {

	@Autowired BloodBankRepository bbRepo;
	
	@RequestMapping("")
	public List<BloodBank> listBloodBanks() {
		
		return bbRepo.findAll();
	}
	
	@RequestMapping("/{id}")
	public BloodBank getById(@PathVariable Long id) {		
		return bbRepo.findOne(id);
	}
	
}
