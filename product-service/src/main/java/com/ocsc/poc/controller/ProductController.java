package com.ocsc.poc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ocsc.poc.model.GetProductDetailsRequest;
import com.ocsc.poc.model.ProductDetails;
import com.ocsc.poc.service.ProductService;

@RestController
@Validated
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(path = "/products/{name}", produces = "application/json")
	public List<ProductDetails> getProducts(@PathVariable("name") String name) {

		return productService.getAllProducts(name);
	}

	@GetMapping(path = "/products/", produces = "application/json")
	public List<ProductDetails> getAllProducts() {

		return productService.getAllProducts("");
	}

	@PostMapping(path = "/products/", produces = "application/json")
	public List<ProductDetails> getProductDetails(@Valid @RequestBody GetProductDetailsRequest getProductDetails) {

		return productService.getProductDetailsByIds(getProductDetails);

	}

	@PostMapping(path = "/products/product/", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDetails createProduct(@Valid @RequestBody ProductDetails product) {

		return productService.saveProduct(product);

	}

}
