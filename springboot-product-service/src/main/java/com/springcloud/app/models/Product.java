package com.springcloud.app.models;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value="product")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	private String id;
	private String name;
	private String description;
	private Double price;
	
	

}
