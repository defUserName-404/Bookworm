package com.defusername.bookworm.security;

import com.defusername.bookworm.security.auth.entity.constant.UserRole;
import com.defusername.bookworm.security.auth.user.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final AuthUserDetailsService userDetailsService;

	@Autowired
	public SecurityConfig(AuthUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
						   .cors(AbstractHttpConfigurer::disable)
						   .sessionManagement(
								   session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
						   .authorizeHttpRequests(authorize -> authorize.requestMatchers(
																				"/auth/**")
																		.permitAll()
																		.requestMatchers("/api/v1/*/admin/**")
																		.hasRole(UserRole.ADMIN.toString())
																		.anyRequest()
																		.authenticated())
						   .authenticationProvider(authenticationProvider())
						   .build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
