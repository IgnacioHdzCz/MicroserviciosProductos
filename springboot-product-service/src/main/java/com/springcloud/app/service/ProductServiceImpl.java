package com.springcloud.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springcloud.app.dao.ProductDAO;
import com.springcloud.app.dto.ProductDTO;
import com.springcloud.app.dto.ProductResponse;
import com.springcloud.app.models.Product;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

	private final ProductDAO productDAO;

	public ProductServiceImpl(ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}




	@Override
	public void createProduct(ProductDTO productDTO) {
		Product product = 	
				Product.builder()
				.name(productDTO.getName())
				.description(productDTO.getDescription())
				.price(productDTO.getPrice())
				.build();
		productDAO.save(product);
		log.info("Product {} is saved", product.getId());
		
	}

	@Override
	public List<ProductResponse> getAllProducts() {

		List<Product> products = productDAO.findAll();
		return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
		
	}




	private ProductResponse mapToProductResponse(Product product) {
		// TODO Auto-generated method stub
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}
