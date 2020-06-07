package com.ocsc.poc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ocsc.poc.entity.Product;
import com.ocsc.poc.model.GetProductDetailsRequest;
import com.ocsc.poc.model.ProductDetails;
import com.ocsc.poc.repository.ProductRepository;
import com.ocsc.poc.util.TechnicalException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repository;

	Logger logger;

	@Override
	public List<ProductDetails> getAllProducts(String name) {
		List<ProductDetails> productList = new ArrayList<>();
		if (StringUtils.isEmpty(name)) {
			repository.findAll().forEach(e -> {
				productList.add(new ProductDetails(e.getProductId(), e.getProductName(), e.getCompany(), e.getPrice(),
						e.getPicByte()));
			});
		} else {
			repository.findByProductName(name.trim()).forEach(e -> {
				productList.add(new ProductDetails(e.getProductId(), e.getProductName(), e.getCompany(), e.getPrice(),
						e.getPicByte()));
			});
		}
		return productList;
	}

	@Override
	public ProductDetails saveProduct(ProductDetails pd) {

		if (pd.getProductId() != null && repository.findById(pd.getProductId()).isPresent()) {
			throw new RuntimeException("Product exists");
		}
		try {
			Product product = new Product(pd.getProductId(), pd.getProductName(), pd.getCompany(), pd.getPrice(),
					pd.getPicByte());
			repository.save(product);
			pd.setProductId(product.getProductId());
		} catch (Exception ex) {
			logger.log(Level.ERROR, " in saveProduct method: ", ex);
			throw new TechnicalException("Internal Server Error");
		}
		return pd;
	}

	@Override
	public List<ProductDetails> getProductDetailsByIds(GetProductDetailsRequest getProductDetails) {
		List<ProductDetails> productList = new ArrayList<>();
		repository.findByProductIds(getProductDetails.getProductIdList()).forEach(e -> {
			productList.add(new ProductDetails(e.getProductId(), e.getProductName(), e.getCompany(), e.getPrice(),
					e.getPicByte()));
		});
		return productList;
	}

}
