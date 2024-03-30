package com.defusername.bookworm.security.auth.service;

import com.defusername.bookworm.security.auth.dto.SignInRequest;
import com.defusername.bookworm.security.auth.dto.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

	void signUp(SignUpRequest data);

	void signIn(SignInRequest data);

}
