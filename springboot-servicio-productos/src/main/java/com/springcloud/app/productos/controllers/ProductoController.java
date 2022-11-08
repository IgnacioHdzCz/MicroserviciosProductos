package com.springcloud.app.productos.controllers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.app.commons.models.entity.Producto;
import com.springcloud.app.productos.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private IProductoService iProductoService;
	
	@Autowired
	private Environment env;
	
	@Value("${server.port}")
	private Integer puerto;
	
	
	@GetMapping("/listar")
	public List<Producto> listar (){
		return iProductoService.findAll().stream().map(producto -> {
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			//roducto.setPort(puerto);
			return producto;
		}).collect(Collectors.toList());
	}
	
	
	
	@GetMapping("/ver/{id}")
	public Producto detalle (@PathVariable Long id) throws InterruptedException {
		
		if(id.equals(10L)) {
			throw new IllegalStateException("Producto no encontrado");
		}
		
		if(id.equals(7L)) {
			TimeUnit.SECONDS.sleep(5L);
		}
		
		Producto producto = iProductoService.findById(id);
		//producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		//return iProductoService.findById(id);
		producto.setPort(puerto);
//		boolean ok = false;
//		
//		if(!ok ) {
//			throw new RuntimeException("No se pudo cargar el producto");
//		}
		
//	
//		try {
//			Thread.sleep(2000000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return producto;
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return iProductoService.save(producto);
	}
	
	
	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar (@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDB = iProductoService.findById(id);
		productoDB.setNombre(producto.getNombre());
		productoDB.setPrecio(producto.getPrecio());
		return iProductoService.save(productoDB);	
	}
	
	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar (@PathVariable Long id) {
		iProductoService.deleteById(id);
		
	}
}
