package com.savior.security.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.savior.model.security.Authority;
import com.savior.model.security.AuthorityName;
import com.savior.model.security.User;
import com.savior.repository.BloodBankRepository;
import com.savior.repository.UserRepository;
import com.savior.security.JwtAuthenticationRequest;
import com.savior.security.JwtTokenUtil;
import com.savior.security.JwtUser;
import com.savior.service.JwtAuthenticationResponse;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	// public ResponseEntity<?> createAuthenticationToken(@RequestBody
	// JwtAuthenticationRequest authenticationRequest, Device device) throws
	// AuthenticationException {
	public String createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device)
			throws AuthenticationException {

		// Perform the security
		try {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
							authenticationRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Reload password post-security so we can generate token
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails, device);

			// finding user from username

			User user = userRepo.findByUsername(authenticationRequest.getUsername());
			for (Authority a : user.getAuthorities())

				// TODO : better to use switch statement
				if (a.getName() == AuthorityName.ROLE_USER) {
					return "mainpage";
				} else if (a.getName() == AuthorityName.ROLE_ADMIN) {
					return "adminpage";
				}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		// instead of returning token we will redirect the user appropriately to
		// particular page..
		// Return the token

		return "homepage";
		// return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
