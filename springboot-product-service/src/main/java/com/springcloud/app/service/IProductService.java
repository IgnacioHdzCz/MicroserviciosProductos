package com.springcloud.app.service;

import java.util.List;

import com.springcloud.app.dto.ProductDTO;
import com.springcloud.app.dto.ProductResponse;

public interface IProductService {
	public void createProduct (ProductDTO productDTO);
	public List<ProductResponse> getAllProducts();
}
