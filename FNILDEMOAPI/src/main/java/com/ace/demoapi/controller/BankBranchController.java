package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.BankBranch;
import com.ace.demoapi.service.IBankBranchService;



@RestController
public class BankBranchController {
	
	@Autowired
	private IBankBranchService bankBranchService;

	@GetMapping(path = "/bankBranches", produces = "application/json")
	public List<BankBranch> getAllBankBranch() {
		return bankBranchService.findAllBankBranch();
	}

	@GetMapping(path = "/bankBranch/{id}", produces = "application/json")
	public BankBranch getBankBranchById(@PathVariable String id) {
		return bankBranchService.findBankBranchById(id).orElse(null);
	}

}
