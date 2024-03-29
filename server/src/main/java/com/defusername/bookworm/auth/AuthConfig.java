package com.defusername.bookworm.auth;

import com.defusername.bookworm.auth.user.UserDetailsServiceJdbc;
import com.defusername.bookworm.auth.user.UserRole;
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
public class AuthConfig {

	private final UserDetailsServiceJdbc userDetailsServiceJdbc;

	@Autowired
	public AuthConfig(UserDetailsServiceJdbc userDetailsServiceJdbc) {
		this.userDetailsServiceJdbc = userDetailsServiceJdbc;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
						   .sessionManagement(
								   session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
						   .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/v1/auth/**")
																		.permitAll()
																		.requestMatchers("/api/v1/*/admin/**")
																		.hasRole(UserRole.ADMIN.toString())
																		.anyRequest()
																		.authenticated())
						   .userDetailsService(userDetailsServiceJdbc)
						   .build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsServiceJdbc);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
