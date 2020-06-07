package com.ocsc.poc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ocsc.poc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT p FROM User p  WHERE p.emailId =?1")
	Optional<User> findByEmailId(String emailId);

	
	@Query("SELECT p FROM User p  WHERE p.emailId =?1 and p.password =?2")
	Optional<User> validateUserPass(String emailId,String password);
	
}
