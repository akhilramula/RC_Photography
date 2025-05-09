package com.rcphotography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rcphotography.entity.User;
import com.rcphotography.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=repo.findByUsername(username);
		if (user != null) {
			UserDetails userdetails=org.springframework.security.core.userdetails.User
			.withUsername(username)
			.password(user.getPassword())
			.authorities(user.getRole())
			.build();
		return userdetails;
		}
		throw new UsernameNotFoundException("User with username:"+username+"not found");
	}
	
}
