package com.remindermeapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.remindermeapp.dto.UserProfileForm;
import com.remindermeapp.dto.UserRegistrationForm;
import com.remindermeapp.service.UserService;
import com.remindermeapp.user.CustomUserDetails;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/login")
    public String getLogin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || AnonymousAuthenticationToken.class.
			      isAssignableFrom(authentication.getClass())) 
			return "login";
       return "redirect:/";
    }
	
	@GetMapping("/register")
	public String registerUser(Model model, UserRegistrationForm user ) {
		
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUserSubmit(Model model, @ModelAttribute UserRegistrationForm userRegistrationForm ) {
		
		
		if(!userRegistrationForm.getPassword().equals(userRegistrationForm.getConfirmedPassword())) {
			model.addAttribute("passwordMisMatch", "Passwords do not match");
			return "register";
		}
		boolean res = userService.registerUser(userRegistrationForm);
		if(res) {
			
		return "redirect:/login";
		
		}
		else {
			
			model.addAttribute("status", "Email id entered already exists in database");
			return "register";
		}
		
	}
	
	@GetMapping("/profile")
	public String viewProfile(Model model) {
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 UserProfileForm userProfileForm = userService.getUserProfile(((CustomUserDetails)auth.getPrincipal()).getUsername());
		 CustomUserDetails cud = (CustomUserDetails)auth.getPrincipal();
		 model.addAttribute("userProfileForm", userProfileForm);
		 
		return "profile";
	}
}
