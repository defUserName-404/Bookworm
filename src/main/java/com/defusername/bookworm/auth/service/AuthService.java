package com.defusername.bookworm.auth.service;

import com.defusername.bookworm.auth.dto.SignInRequest;
import com.defusername.bookworm.auth.dto.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

	void signUp(SignUpRequest data);

	void signIn(SignInRequest data);

}
