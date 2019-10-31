package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Customer;
import com.ace.demoapi.service.ICustomerService;

@RestController
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@GetMapping(path = "/customers", produces = "application/json")
	public List<Customer> getAllCustomer() {
		return customerService.findAllCustomer();
	}

	@GetMapping(path = "/customer/{id}", produces = "application/json")
	public Customer getCustomerById(@PathVariable String id) {
		return customerService.findCustomerById(id).orElse(null);
	}
}
