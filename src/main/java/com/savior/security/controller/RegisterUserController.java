package com.savior.security.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.savior.model.security.Authority;
import com.savior.model.security.AuthorityName;
import com.savior.model.security.User;
import com.savior.repository.UserRepository;
import com.savior.security.JwtTokenUtil;
import com.savior.service.JwtAuthenticationResponse;

@RestController
@Controller
public class RegisterUserController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	List<Authority> authorities = null;
	
	@Autowired
	UserRepository userRepo;
	
	List<Authority> authorities2 = new ArrayList<Authority>();
	 
	 Authority auth = new Authority();
	 
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	    public ResponseEntity<?> registerUser(@RequestBody User user, Device device) throws AuthenticationException {
		 
		 
		 auth.setName(AuthorityName.ROLE_USER);
		 auth.setId(2l);
		 System.out.println("into register post");
		 System.out.println("The USer is trying to login wih username "+ user.getUsername() +" password" + user.getPassword() + " email id: " + user.getEmail());
		 
		 	Date date = new Date();
		 
		    String password = user.getPassword();
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			
			System.out.println("The password is " + hashedPassword);
			
		 	User user1 = new User();//user.getUsername(),hashedPassword,"FirstName","LastName",user.getEmail(),true,date); 
	       
		 	user1.setUsername(user.getUsername());
		 	user1.setPassword(hashedPassword);
		 	user1.setFirstname("firstname");
		 	user1.setLastname("lastname");
		 	user1.setEmail(user.getEmail());
		 	user1.setEnabled(true);
		 	user1.setLastPasswordResetDate(date);
		 	this.authorities2.add(auth);
		 	user1.setAuthorities(this.authorities2);
		 	
 			userRepo.save(user1);
		 			
 			System.out.println("The user got added in database !");
	        // Reload password post-security so we can generate token
	        final UserDetails userDetails = userDetailsService.loadUserByUsername(user1.getUsername());
	        
	        System.out.println("The user is successfully logged in with role as " + userDetails.getAuthorities().toString());
	        final String token = jwtTokenUtil.generateToken(userDetails, device);

	        // Return the token

	        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	    }

	
	
}
