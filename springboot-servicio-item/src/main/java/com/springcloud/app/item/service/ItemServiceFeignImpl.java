package com.springcloud.app.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.springcloud.app.item.clientes.ProductoClienteRest;
import com.springcloud.app.item.entity.Item;
import com.springcloud.app.commons.models.entity.Producto;

import lombok.extern.slf4j.Slf4j;

@Service ("serviceFeign")
@Primary
@Slf4j
public class ItemServiceFeignImpl implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		log.info("::::::::::::::::::Usando Feign");
		return clienteFeign.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		log.info(":::::::::::::::::::Usando Feign");
		return new Item (clienteFeign.detalle(id),cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return clienteFeign.crear(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		
		return clienteFeign.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		clienteFeign.eliminar(id);
	}

}
