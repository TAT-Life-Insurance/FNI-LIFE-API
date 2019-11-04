package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Branch;

public interface BranchRepository extends CrudRepository<Branch, String>{
	
	Optional<Branch> findById(String id);

	List<Branch> findAll();
}
