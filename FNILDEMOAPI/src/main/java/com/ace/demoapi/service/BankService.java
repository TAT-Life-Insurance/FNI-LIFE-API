package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Agent;
import com.ace.demoapi.modal.Bank;
import com.ace.demoapi.repository.AgentReposity;
import com.ace.demoapi.repository.BankRepository;

@Service
public class BankService implements IBankService {

	@Autowired
	private BankRepository bankRepository;

	@Override
	public Optional<Bank> findBankById(String id) {
		
		return bankRepository.findById(id);
	}

	@Override
	public List<Bank> findAllBank() {
		
		return bankRepository.findAll();
	}

}
