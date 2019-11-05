package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.BankBranch;
import com.ace.demoapi.repository.BankBranchRepository;


@Service
@Transactional
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
	
	@Override
	public void saveBankBranch(BankBranch bankBranch) {
	        bankBranchRepository.save(bankBranch);
	    }

	@Override
	public void deleteById(String id) {
		bankBranchRepository.deleteById(id);
		
	}

	
	
	  

	    
	}

	
