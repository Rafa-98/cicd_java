package com.products.demo.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.products.demo.controller.ProductController;
import com.products.demo.model.ProductModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductRepository {
	
	static final String URL = "http://localhost:3001/product/";
	
	private RestTemplate restTemplate;
	
	public ProductRepository() {
		this.restTemplate = new RestTemplate();
	}	

	public List<String> getSimilarProducts(String productId) {
		String uri = URL + productId + "/similarids";	
		log.info("Executing request to: " + URL + productId + "/similarids");
		ResponseEntity<String[]> responseEntity = this.restTemplate.getForEntity(uri, String[].class);
		log.info("Response received from: " + URL + productId + "/similarids");
		List<String> result = Arrays.asList(responseEntity.getBody());
		return result;		
	}

	public ProductModel GetProductDetails(String productId) {		
		String uri = URL + productId;
		log.info("Executing request to: " + URL + productId);
		ProductModel result = this.restTemplate.getForObject(uri, ProductModel.class);
		log.info("Response received from: " + URL + productId);
		return result;
	}

}
