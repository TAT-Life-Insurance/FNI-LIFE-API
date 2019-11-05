package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Bank;

public interface BankRepository extends CrudRepository<Bank, String>{
	
	Optional<Bank> findById(String id);

	List<Bank> findAll();
	
}
