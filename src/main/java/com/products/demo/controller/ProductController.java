package com.products.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.demo.exception.BadRequestException;
import com.products.demo.exception.SimilarProductsNotFoundException;
import com.products.demo.model.ProductModel;
import com.products.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/product")
@Validated
@Slf4j
public class ProductController {
	
	private ProductService service = new ProductService();
	
	@GetMapping("/{productId}/similar")
	public ResponseEntity<List<ProductModel>> getSimilarProducts(@PathVariable int productId) throws SimilarProductsNotFoundException, BadRequestException {
		log.info("Request Received - GET:/product/" + productId + "/similar");
		if(productId < 1) {
			log.error("Bad Request - uri param " + productId + " is not valid.");
			throw new BadRequestException("Bad Request: uri param " + productId + " is not valid.");
		}
		return ResponseEntity.ok(service.getSimilarProducts(Integer.toString(productId)));
	}

}
