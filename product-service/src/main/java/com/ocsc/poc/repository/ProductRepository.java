package com.ocsc.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ocsc.poc.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p  WHERE p.productName like %?1% ")
	List<Product> findByProductName(String name);

	@Query("SELECT p FROM Product p  WHERE p.productId in (?1) ")
	List<Product> findByProductIds(List<Integer> productIdList);

}
