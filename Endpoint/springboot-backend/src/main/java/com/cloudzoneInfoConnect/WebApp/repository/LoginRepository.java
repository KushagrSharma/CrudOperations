package com.cloudzoneInfoConnect.WebApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudzoneInfoConnect.WebApp.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	Optional<Login> findById(String email);
	
	Optional<Login> findByEmail(String email);

}
