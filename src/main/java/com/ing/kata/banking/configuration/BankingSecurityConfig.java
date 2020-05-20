package com.ing.kata.banking.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BankingSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("test").password("{noop}test123").roles("USER").and().withUser("admin")
				.password("{noop}admin123").roles("USER", "ADMIN");

	}

	// Secure the endpoins with HTTP Basic authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// HTTP Basic authentication
		http.httpBasic().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/banking/*").hasRole("USER").and().csrf().disable().formLogin()
				.disable();
	}
}
