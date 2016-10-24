package com.savior;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String signingKey = "signingKey";
    private static final Map<String, String> credentials = new HashMap<>();

    public LoginController() {
        credentials.put("hellokoding", "hellokoding");
        credentials.put("hellosso", "hellosso");
    }

    
    
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model){
        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
            model.addAttribute("error", "Invalid username or password!");
            System.out.println("Invalid User!");
            return "index1";
        }

        String token = JwtUtil.generateToken(signingKey, username);
        System.out.println("The token is :" + token);
        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");

        return "redirect:/postlogin";
    }
    
    @RequestMapping("/postlogin")
    public String postlogin(Model model) {
    	
//    	HttpServletResponse httpServletResponse, String username, String password, String redirect, Model model
//        if (username == null || !credentials.containsKey(username) || !credentials.get(username).equals(password)){
//            model.addAttribute("error", "Invalid username or password!");
//            System.out.println("Invalid User!");
//            return "login";
//        }

//        String token = JwtUtil.generateToken(signingKey, username);
//        System.out.println("The token is :" + token);
//        CookieUtil.create(httpServletResponse, jwtTokenCookieName, token, false, -1, "localhost");
    	
    	//Create Profile model using .addattribute and pass it to post login page.
    	//pass token as well.
    	
    	System.out.println("PostLogin Get Request was called !!");
        return "mainpage";
    }
}
