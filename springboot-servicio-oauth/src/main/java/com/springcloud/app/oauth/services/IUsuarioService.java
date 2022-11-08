package com.springcloud.app.oauth.services;

import com.springcloud.app.commons.usuarios.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);



}
