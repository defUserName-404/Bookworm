package com.defusername.bookworm.auth;

import com.defusername.bookworm.auth.type.SignInDto;
import com.defusername.bookworm.auth.type.SignUpDto;
import com.defusername.bookworm.auth.user.User;
import com.defusername.bookworm.auth.user.UserRepository;
import com.defusername.bookworm.util.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImplementation implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void signUp(SignUpDto data) {
		if (userRepository.findUserByUsername(data.username())
						  .isPresent()) {
			throw new UsernameAlreadyExistsException();
		}
		final String encryptedPassword = passwordEncoder.encode(data.password());
		final User newUser = new User(data.username(), encryptedPassword, data.role());
		userRepository.save(newUser);
	}

	@Override
	public void signIn(SignInDto data) {
		Optional<User> user = userRepository.findUserByUsername(data.username());
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Invalid Username");
		}
		if (!passwordEncoder.matches(data.password(), user.get()
														  .getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
	}
	
}
