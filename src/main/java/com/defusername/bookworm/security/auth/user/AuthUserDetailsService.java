package com.defusername.bookworm.security.auth.user;

import com.defusername.bookworm.security.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public AuthUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String emailOrUsername) {
		return userRepository.findUserByUsername(emailOrUsername)
							 .or(() -> userRepository.findUserByEmail(emailOrUsername))
							 .map(AuthUserDetails::new)
							 .orElseThrow(() -> new UsernameNotFoundException("Invalid Username or Email"));
	}

}
