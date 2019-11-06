package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.ace.demoapi.modal.Bank;
import com.ace.demoapi.service.IBankService;

@RestController
public class BankController {
	
	@Autowired
	private IBankService bankService;

	@GetMapping(path = "/banks", produces = "application/json")
	public List<Bank> getAllBank() {
		return bankService.findAllBank();
	}

	@GetMapping(path = "/bank/{id}", produces = "application/json")
	public Bank getBankById(@PathVariable String id) {
		return bankService.findBankById(id).orElse(null);
	}
	
	@PostMapping(path = "/saveBank")
	public Bank savBank(@RequestBody Bank bank) {
		bankService.saveBank(bank);
		return bank;
	}
	
	
	@DeleteMapping("/deleteBank/{id}")
	public void deleteById(@PathVariable String id){
		bankService.deleteById(id);
		
	}

}
