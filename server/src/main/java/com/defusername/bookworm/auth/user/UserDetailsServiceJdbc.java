package com.defusername.bookworm.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceJdbc implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceJdbc(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		return userRepository.findUserByUsername(username)
							 .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

}
