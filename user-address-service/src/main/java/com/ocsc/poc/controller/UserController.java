package com.ocsc.poc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ocsc.poc.model.UserDetails;
import com.ocsc.poc.service.UserService;

@RestController
@RequestMapping(path = "/profile")
@Validated
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/validate", produces = "application/json")
	public UserDetails getUserbyId(@RequestHeader("emailId") String emailId,
			@RequestHeader("password") String password) {

		return userService.validateUserPass(emailId, password);
	}

	@GetMapping(path = "/email/{emailId}", produces = "application/json")
	public UserDetails getUserByEmailId(@PathVariable("emailId") String emailId) {

		return userService.getUserByEmailId(emailId);
	}

	@PostMapping
	public String SaveAddress(@Valid @RequestBody UserDetails user) {
		return userService.saveUser(user);
	}
}
