package com.springcloud.app.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.app.productos.dao.ProductoDAO;
import com.springcloud.app.commons.models.entity.Producto;

@Service
public class IProductoServiceImp  implements IProductoService{

	@Autowired
	private ProductoDAO productoDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return productoDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional //No se pone read only porque es de escritura
	public Producto save(Producto producto) {
		return productoDAO.save(producto);
	}

	@Override
	@Transactional 
	public void deleteById(Long id) {
	productoDAO.deleteById(id);
	}



}
