//package com.springcloud.app.oauth.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	
//	@Autowired // Para poder autenticarse
//	private UserDetailsService usuarioService;
//
//	@Bean
//	public static BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//
//	@Override
//	@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder())
//		;
//	}
//
//	@Bean
//	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//
//	
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		
//		http
//		.authorizeRequests()
//		.antMatchers("/").permitAll()
//		.and()
//		.csrf().disable();
//		
//	}
//	
//	
//}
