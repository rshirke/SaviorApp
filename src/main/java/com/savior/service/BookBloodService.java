package com.savior.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import com.savior.model.security.BloodBank;
import com.savior.model.security.Transaction;
import com.savior.model.security.User;
import com.savior.repository.BloodBankRepository;
import com.savior.repository.TransactionRepository;
import com.savior.repository.UserRepository;



@Service
public class BookBloodService {

	 @Autowired
	    private UserRepository userRepository;
	 
	 @Autowired
	    private BloodBankRepository bbRepo;
	 
	 @Autowired
	    private TransactionRepository ttRepo;
	 
	 @Autowired
	 	private JavaMailSender  javaMailService;
	 
	 public void addTransaction(String username, String bloodBankId)
	 {
		 //String bloodGroup, String status, int quantity, Date date) {
		 BloodBank bb = bbRepo.getOne(Long.parseLong(bloodBankId));
		 
		 Date date = new Date();
		 User user = userRepository.findByUsername(username);
		 Transaction transaction = new Transaction("A","Pending", 2, date);
		 transaction.setUser(user);
		 transaction.setBloodBank(bb);
		 ttRepo.save(transaction);
		 
	/*	 SimpleMailMessage mailMessage=new SimpleMailMessage();
	        mailMessage.setTo(user.getEmail());
	        mailMessage.setSubject("Registration");
	        mailMessage.setText("Hello " +user.getUsername() +"\n Your registration is successfull");
	        javaMailService.send(mailMessage);*/
		 
	 }
}
