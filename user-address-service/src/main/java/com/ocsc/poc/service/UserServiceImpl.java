package com.ocsc.poc.service;

import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsc.poc.entity.User;
import com.ocsc.poc.model.UserDetails;
import com.ocsc.poc.repository.UserRepository;
import com.ocsc.poc.util.RecordNotFoundException;
import com.ocsc.poc.util.TechnicalException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	Logger logger;

	@Override
	public String saveUser(UserDetails userDetailObj) {

		if (repository.findByEmailId(userDetailObj.getEmailId()).isPresent()) {
			throw new RuntimeException("User already exists");
		}
		try {
			User user = new User(userDetailObj.getUserId(), userDetailObj.getPassword(),userDetailObj.getEmailId());
			repository.save(user);
		return user.getEmailId();
		} catch (Exception ex) {
			logger.log(Level.ERROR, " in saveUser method: ", ex);
			throw new TechnicalException("Internal Server Error");
		}
		
	}

	@Override
	public UserDetails validateUserPass(String emailId, String password) {
		Optional<User> user = repository.validateUserPass(emailId, password);
		if (!user.isPresent()) {
			throw new RecordNotFoundException("User does not exists");
		}
		UserDetails userDetailObj = new UserDetails(user.get().getUserId(), "NA", user.get().getEmailId());

		return userDetailObj;
	}

	@Override
	public UserDetails getUserByEmailId(String emailId) {
		Optional<User> user = repository.findByEmailId(emailId);
		if (!user.isPresent()) {
			throw new RecordNotFoundException("User doesnot exists");
		}
		UserDetails ud = new UserDetails(user.get().getUserId(), "NA", user.get().getEmailId());
		return ud;
	}

}
