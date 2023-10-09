package com.products.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.products.demo.model.ProductModel;

class ProductRepositoryTest {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private ProductRepository productRepository;
	
	static final String url = "http://localhost:3001/product/";
	
	private String[] tSimilarProducts;
	private List<ProductModel> tProducts;
	private String tProductId;
	private ProductModel tProduct1;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		tSimilarProducts = new String[]{"2"};
		
		tProducts = new ArrayList<ProductModel>();
		tProduct1 = new ProductModel("0", "product 1", 12.35, true);
		tProducts.add(tProduct1);
		
		tProductId = "1";
	}

	@Test
	@DisplayName("It should return an array of strings representing the list of similar products")
	void getSimilarProductsTest() throws Exception  {
		// prepare
		when(restTemplate.getForEntity(url + tProductId + "/similarids", String[].class)).thenReturn(new ResponseEntity(tSimilarProducts, HttpStatus.OK));
		
		// execute
		List<String> result = productRepository.getSimilarProducts(tProductId);
		
		// assert
		assertNotNull(result);
		verify(restTemplate).getForEntity(url + tProductId + "/similarids", String[].class);		
		assertThat(result).usingRecursiveComparison().isEqualTo(Arrays.asList(tSimilarProducts));
	}
	
	@Test
	@DisplayName("It should return all properties for a specific product")
	void getProductDetails() throws Exception  {
		// prepare
		when(restTemplate.getForObject(url + tProductId, ProductModel.class)).thenReturn(tProduct1);
		
		// execute
		ProductModel result = productRepository.GetProductDetails(tProductId);
		
		// assert
		assertNotNull(result);
		verify(restTemplate).getForObject(url + tProductId, ProductModel.class);		
		assertThat(result).usingRecursiveComparison().isEqualTo(tProduct1);
	}
}
