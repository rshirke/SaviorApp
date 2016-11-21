package com.savior.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.savior.security.JwtTokenUtil;
import com.savior.security.JwtUser;


@RestController
public class SearchController {

	@Value("${jwt.header}")
    private String tokenHeader;
	
	 @Autowired
	 private JwtTokenUtil jwtTokenUtil;
	 
	@Autowired
    private UserDetailsService userDetailsService;
	
//need to preauthorize with role..
    @RequestMapping(value = "searchBank", method = RequestMethod.GET)
    public String getAuthenticatedUser(HttpServletRequest request) {
       
    	//needs to be changed..
    	String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        
        
        
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        
        
        
        return "";
    }

}
