package com.savior.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/loginfb")
public class HelloController {

//	private static Facebook facebook;
//	private static ConnectionRepository connectionRepository;
//
//	@Autowired
//	UserRepository repo;
//
//	public HelloController(Facebook facebook, ConnectionRepository connectionRepository) {
//		this.facebook = facebook;
//		this.connectionRepository = connectionRepository;
//	}
//
//	
//	
//	@RequestMapping(method = RequestMethod.GET)
//	public String helloFacebook(Model model) {
//
//		if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//			return "redirect:/connect/facebook";
//		}
//		
//		//fetch connection from facebook
//		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);	
//		User user = connection.getApi().userOperations().getUserProfile();
//		
//		List<UserFB> fblist = repo.findByFacebookid(user.getId());
//		
//		for( UserFB fb : fblist)
//		{
//			System.out.println(fb.toString());	
//		}
//				
//		//check whether the user already exists in database;
//		if( repo.findByFacebookid(user.getId()).isEmpty())
//		{
//			//user doesnot exist in database
//			System.out.println(" User doennot exist and going to add one !!");
//			repo.save(new UserFB(user.getFirstName(), "null", user.getId())); //insert the user object in database
//			
//		} else {
//			//the user exists
//			System.out.println("User already exits");
//			
//		}
//	
//		model.addAttribute("Profile", facebook.userOperations().getUserProfile());		
//		PagedList<Post> feed = facebook.feedOperations().getFeed();
//		model.addAttribute("feed", feed);
//			
//		return "postlogin";
//	}
}
