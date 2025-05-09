package com.rcphotography.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rcphotography.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	private CustomUserDetailsService service; 
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		return http
			.authorizeHttpRequests(auth-> 
			auth.requestMatchers("/admin/**").hasRole("ADMIN")
			.requestMatchers("/login*").permitAll()
			.anyRequest().permitAll()
			)
//			.formLogin( Customizer.withDefaults())  //Enables form-based login with default setup
			.formLogin(form ->
		    form
		        .loginPage("/login") //my custom login page
		        .successHandler((request, response, authentication) -> {
		            boolean isAdmin = authentication.getAuthorities().stream()
		                              .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
		            System.out.println(isAdmin);
		            if (isAdmin) {
		                response.sendRedirect("/admin/dashboard"); // Redirect Admins properly
		            } else {
		                response.sendRedirect("/user-dashboard"); // Redirect Users
		            }
		        })
		        .failureUrl("/login?error=true")
		
		)
			.csrf(csrf -> csrf.disable()) // This allows Postman testing
			.build(); //Finalizes configs		
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager  // Ensures authentication checks work properly.
	(AuthenticationConfiguration authConfig) throws Exception{  
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Hashes Password Securely.
	}
	
}
