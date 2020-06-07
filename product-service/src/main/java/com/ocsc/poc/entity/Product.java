package com.ocsc.poc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TBL_PRODUCT")
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Product {

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productId;

	@Getter
	@Setter
	@Column(name = "product_name")
	private String productName;

	@Getter
	@Setter
	@Column(name = "product_company")
	private String company;

	@Getter
	@Setter
	@Column(name = "product_price")
	private Double price;

	// image bytes can have large lengths so we specify a value
	// which is more than the default length for picByte column
	@Getter
	@Setter
	@Column(name = "product_picbyte")
	private byte[] picByte;

}
