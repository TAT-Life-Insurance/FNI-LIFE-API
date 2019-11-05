package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Branch;

public interface IBranchService {
	

	Optional<Branch> findBranchById(String id);

	List<Branch> findAllBranch();

}
