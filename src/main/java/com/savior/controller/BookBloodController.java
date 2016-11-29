package com.savior.controller;

import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.savior.security.JwtTokenUtil;
import com.savior.service.BookBloodService;
import com.savior.service.JwtAuthenticationResponse;




@RestController
public class BookBloodController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private BookBloodService bookBloodService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/bookBlood" , method = RequestMethod.POST )
	public ResponseEntity<?> listBloodBankspost(@RequestBody String bbid ,HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		
		String authToken = request.getHeader(this.tokenHeader);		
		String username = jwtTokenUtil.getUsernameFromToken(authToken);		
		bookBloodService.addTransaction(username, bbid);
		
		return ResponseEntity.ok("");
	}
	
}
