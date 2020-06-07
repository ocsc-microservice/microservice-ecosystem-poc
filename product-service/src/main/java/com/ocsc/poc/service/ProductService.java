package com.ocsc.poc.service;

import java.util.List;

import com.ocsc.poc.model.GetProductDetailsRequest;
import com.ocsc.poc.model.ProductDetails;

public interface ProductService {

	public List<ProductDetails> getAllProducts(String name);

	public ProductDetails saveProduct(ProductDetails productDetails);

	public List<ProductDetails> getProductDetailsByIds(GetProductDetailsRequest getProductDetails);

}
