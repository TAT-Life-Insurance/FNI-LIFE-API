package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Agent;
import com.ace.demoapi.modal.Branch;
import com.ace.demoapi.repository.BranchRepository;

@Service
public class BranchService implements IBranchService {
	
	@Autowired
	private BranchRepository branchRepository;

	@Override
	public Optional<Branch> findBranchById(String id) {
		
		return branchRepository.findById(id);
	}

	@Override
	public List<Branch> findAllBranch() {
		
		return branchRepository.findAll();
	}
	

}
