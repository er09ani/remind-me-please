package com.remindermeapp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.remindermeapp.repository.UserRepository;

@Service
@Qualifier("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<CustomUser> users = userRepo.findByEmailId(username);
		if(users.isEmpty()) throw new UsernameNotFoundException("User details not found for username : "+ username);
		return new CustomUserDetails(users.get(0));
	}
	
	

}
