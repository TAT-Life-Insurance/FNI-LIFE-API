package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Bank;

public interface IBankService {
	
	Optional<Bank> findBankById(String id);

	List<Bank> findAllBank();
	
    public void saveBank(Bank bank);
	
	public void deleteById(String id);
	

}
