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
@Table(name = "TBL_USER")
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class User {

	@Id
	@Getter
	@Setter
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer userId;

	@Getter
	@Setter
	@Column(name = "password")
	private String password;

	@Getter
	@Setter
	@Column(name = "email_id")
	private String emailId;

}
