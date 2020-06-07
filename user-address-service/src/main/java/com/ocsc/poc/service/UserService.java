package com.ocsc.poc.service;

import com.ocsc.poc.model.UserDetails;

public interface UserService {

	public String saveUser(UserDetails userDetails);

	public UserDetails validateUserPass(String emailId,String password);

	public UserDetails getUserByEmailId(String emailId);

}
