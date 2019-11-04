package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Bank;
import com.ace.demoapi.modal.BankBranch;
import com.ace.demoapi.repository.BankBranchRepository;
import com.ace.demoapi.repository.BankRepository;

@Service
public class BankBranchService implements IBankBranchService {
	
	@Autowired
	private BankBranchRepository bankBranchRepository;

	@Override
	public Optional<BankBranch> findBankBranchById(String id) {
		
		return bankBranchRepository.findById(id);
	}

	@Override
	public List<BankBranch> findAllBankBranch() {
		
		return bankBranchRepository.findAll();
	}
	

	

}
