package com.springcloud.app;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.assertions.Assertions;
import com.springcloud.app.dao.ProductDAO;
import com.springcloud.app.dto.ProductDTO;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class SpringbootProductServiceApplicationTests {
	
	@Autowired
	private MockMvc mockMvc ;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ObjectMapper objectMapper;
	

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo: 4.4.2");
	
	
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	

	
	@Test
	void shouldCreateProduct() throws Exception {
	 ProductDTO productRequest=getProductRequest();
	  String productRequestString= objectMapper.writeValueAsString(productRequest);
			mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(productRequestString))
					.andExpect(status().isCreated());
			
		
		assertEquals(1,productDAO.findAll().size());
	}



	private ProductDTO getProductRequest() {
		// TODO Auto-generated method stub
		return ProductDTO.builder()
				.name("ixx")
				.description("asasd")
				.price(Double.valueOf(1200))
				.build();
	}
	
	
}
