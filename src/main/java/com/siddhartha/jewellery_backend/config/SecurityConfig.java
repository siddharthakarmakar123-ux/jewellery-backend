package com.siddhartha.jewellery_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.siddhartha.jewellery_backend.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(
	            HttpSecurity http) throws Exception {

	        http
	            .csrf(AbstractHttpConfigurer::disable)
	            .cors(cors -> {})
	            .sessionManagement(session ->
	                session.sessionCreationPolicy(
	                    SessionCreationPolicy.STATELESS
	                )
	            )

	            .authorizeHttpRequests(auth -> auth

	                .requestMatchers(
	                    "/api/login/post/authinticate",
	                    "/api/login/post/signup"
	                ).permitAll()
	                
	                .requestMatchers(
	                        org.springframework.http.HttpMethod.OPTIONS,
	                        "/**"
	                    ).permitAll()

	                .anyRequest().authenticated()
	            )

	            .addFilterBefore(
	                jwtAuthenticationFilter,
	                UsernamePasswordAuthenticationFilter.class
	            );

	        return http.build();
	    }
}
