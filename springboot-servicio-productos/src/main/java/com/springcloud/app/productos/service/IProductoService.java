package com.springcloud.app.productos.service;

import java.util.List;

import com.springcloud.app.commons.models.entity.Producto;



public interface IProductoService {
	
	public List <Producto> findAll();
	public Producto findById(Long id);
	public Producto save(Producto producto);
	public void deleteById(Long id);

}
