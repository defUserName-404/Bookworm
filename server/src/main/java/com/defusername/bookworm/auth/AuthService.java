package com.defusername.bookworm.auth;

import com.defusername.bookworm.auth.type.SignInDto;
import com.defusername.bookworm.auth.type.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

	void signUp(SignUpDto data);

	void signIn(SignInDto data);

}
