package com.springcloud.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springcloud.app.models.Product;

public interface ProductDAO extends MongoRepository<Product, String> {

}
