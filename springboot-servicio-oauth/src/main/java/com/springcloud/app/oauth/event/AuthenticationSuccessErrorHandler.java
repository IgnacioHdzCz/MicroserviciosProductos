package com.springcloud.app.oauth.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.springcloud.app.commons.usuarios.models.entity.Usuario;
import com.springcloud.app.oauth.services.IUsuarioService;

import feign.FeignException;
import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	// Los dos metodos reciben como parametro el authentication y se pueden obtener
	// lso datos del usuario
	// private Logger log =
	// Logger.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		if (authentication.getDetails() instanceof WebAuthenticationDetails) {
			return;
		}

		// No es muy eficiente
//		if(authentication.getName().equalsIgnoreCase("frontendapp")) {
//			return;
//		}
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login : " + user.getUsername();
		log.info(mensaje);
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		if(usuario.getIntentos() != null && usuario.getIntentos() > 0) {
			usuario.setIntentos(0);
			usuarioService.update(usuario, usuario.getId());
			
		}

	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		// TODO Auto-generated method stub
		String mensaje = "Error en el login: " + exception.getMessage();
		log.info(mensaje);

		try {
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getIntentos() == null) {
				usuario.setIntentos(0);
			}
			log.info("Intentos ACTUAL es de "+ usuario.getIntentos());
			usuario.setIntentos(usuario.getIntentos() + 1);
			log.info("Intentos DESPUES es de "+ usuario.getIntentos());
			if(usuario.getIntentos() >= 3) {
				log.error("Usuario %s deshabilitado por máximos intentos",usuario.getUsername());
				usuario.setEnabled(false);
				
			}
			usuarioService.update(usuario, usuario.getId());
			
		} catch (FeignException e) {
			// TODO: handle exception
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}

	}

}
