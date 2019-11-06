package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.BankBranch;


public interface IBankBranchService {
	
	Optional<BankBranch> findBankBranchById(String id);

	List<BankBranch> findAllBankBranch();
	
	public void saveBankBranch(BankBranch bankBranch);
	
	public void deleteById(String id);
	


}
