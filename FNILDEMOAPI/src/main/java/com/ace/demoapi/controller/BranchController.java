package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Branch;
import com.ace.demoapi.service.IBranchService;



@RestController
public class BranchController {
	
	@Autowired
	private IBranchService branchService;

	@GetMapping(path = "/branches", produces = "application/json")
	public List<Branch> getAllBranch() {
		return branchService.findAllBranch();
	}

	@GetMapping(path = "/branch/{id}", produces = "application/json")
	public Branch getBranchById(@PathVariable String id) {
		return branchService.findBranchById(id).orElse(null);
	}

}
