package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.BankBranch;

public interface BankBranchRepository extends CrudRepository<BankBranch, String>{
	
	Optional<BankBranch> findById(String id);

	List<BankBranch> findAll();
}
