package com.springcloud.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.app.dto.ProductDTO;
import com.springcloud.app.dto.ProductResponse;
import com.springcloud.app.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	private final IProductService iProductService;

	/*
	 * Se piuede inyectar la dependencia por medio de la anotacion 
	 * lombok = @RequiredArgsConstructor
	 */
	public ProductController(IProductService iProductService) {
		super();
		this.iProductService = iProductService;
	}


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductDTO productDTO) {
		iProductService.createProduct(productDTO);
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts(){
		
		return iProductService.getAllProducts();
	}
	

	
}
