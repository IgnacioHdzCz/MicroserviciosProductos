package com.springcloud.app.usuarios.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.springcloud.app.commons.usuarios.models.entity.Usuario;



@RepositoryRestResource(path="usuarios")
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long>{
	//si quiero agregar mas parametros de busqueda agrego el AND y asi sucesivamente o el OR
		//public Usuario findByUsernameAndEmail(String username,String email);

		@RestResource(path="buscar-username")
		public Usuario findByUsername(@Param ("username") String username);

		//Ejemplo con la anotacion @Query
//		@Query(value="SELECT U FROM USUARIO U WHERE U.USERNAME = ?1 AND U.EMAIL=?2",natuve)
//		public Usuario obtenerPorUsername(String username,String email);
		@Query(value="SELECT * FROM USUARIOS  WHERE USERNAME = ?1",nativeQuery = true)
		public Usuario obtenerPorUsername(String username);
}
