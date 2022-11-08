package com.springcloud.app.oauth.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.springcloud.app.commons.usuarios.models.entity.Usuario;
import com.springcloud.app.oauth.services.IUsuarioService;

@Component  //anotar con la anotacion component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired  //inyectamos la interfaz
	private IUsuarioService usuarioService;
	
	@Override  //Potenciar el token 
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();
		//Se usa object porque es generico, se agrega el nombre,apellido y correo
		
		//retorna el usuario
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("correo", usuario.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
		
	}

}
