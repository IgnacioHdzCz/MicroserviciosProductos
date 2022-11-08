package com.springcloud.app.oauth.event;

import org.jboss.logging.Logger;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	//Los dos metodos reciben como parametro el authentication y se pueden obtener lso datos del usuario
	//private Logger log = Logger.getLogger(AuthenticationSuccessErrorHandler.class);
	
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login : " + user.getUsername();
		log.info("Success Login: {} ", mensaje);
	
		
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		// TODO Auto-generated method stub
		String mensaje = "Error en el login: " + exception.getMessage();
		log.info(mensaje);
		
		
	}

}
