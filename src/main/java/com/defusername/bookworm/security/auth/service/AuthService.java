package com.defusername.bookworm.security.auth.service;

import com.defusername.bookworm.security.auth.dto.SignInRequest;
import com.defusername.bookworm.security.auth.dto.SignUpRequest;
import com.defusername.bookworm.security.auth.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

	User signUp(SignUpRequest data);

	User signIn(SignInRequest data);

}
