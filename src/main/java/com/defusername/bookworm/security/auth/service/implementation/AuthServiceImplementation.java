package com.defusername.bookworm.security.auth.service.implementation;

import com.defusername.bookworm.security.auth.dto.SignInRequest;
import com.defusername.bookworm.security.auth.dto.SignUpRequest;
import com.defusername.bookworm.security.auth.entity.User;
import com.defusername.bookworm.security.auth.service.AuthService;
import com.defusername.bookworm.security.auth.repository.UserRepository;
import com.defusername.bookworm.util.exception.EmailAlreadyExistsException;
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
	public User signUp(SignUpRequest data) {
		if (userRepository.findUserByUsername(data.username())
						  .isPresent()) {
			throw new UsernameAlreadyExistsException();
		}
		if (userRepository.findUserByEmail(data.email())
						  .isPresent()) {
			throw new EmailAlreadyExistsException();
		}
		final String encryptedPassword = passwordEncoder.encode(data.password());
		final User newUser = new User(data.username(), data.email(), encryptedPassword, data.role());
		userRepository.save(newUser);
		return newUser;
	}

	@Override
	public User signIn(SignInRequest data) {
		Optional<User> user = userRepository.findUserByUsername(data.username());
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Invalid Username");
		}
		if (!passwordEncoder.matches(data.password(), user.get()
														  .getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		return user.get();
	}

}
