package com.springcloud.app.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//Correcto
	@Autowired // Para poder autenticarse
    private UserDetailsService usuarioService;
	
	//Correcto

	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		String idcliente = "config.security.oauth.client.id";
		System.out.println(idcliente);
		log.info("::::::::::::INMEMORY","config.security.oauth.client.id".toString());
		log.info("::::::::::::INMEMORY","config.security.oauth.client.secret");
		auth.inMemoryAuthentication().
		withUser("frontendapp").
		//withUser("config.security.oauth.client.id").
		password(passwordEncoder().encode("12345")).
		//password(passwordEncoder().encode("config.security.oauth.client.secret")).
		roles("USER");
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
		
	}
	
	
	//Agregado

	@Override
	
	protected void configure(HttpSecurity http) throws Exception {
		log.info("Entrando al configure");
		http.httpBasic().and().authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
//		.and()
//		.csrf().disable();
	}

	//Correcto

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//Correctoo

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	
	
	
}
