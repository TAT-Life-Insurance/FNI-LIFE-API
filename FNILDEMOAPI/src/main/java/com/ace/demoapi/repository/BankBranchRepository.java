package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ace.demoapi.modal.BankBranch;

@Repository
public interface BankBranchRepository extends CrudRepository<BankBranch, String>{
	
	Optional<BankBranch> findById(String id);

	List<BankBranch> findAll();
		
	//void deleteBankBranch(int id);
	
}
