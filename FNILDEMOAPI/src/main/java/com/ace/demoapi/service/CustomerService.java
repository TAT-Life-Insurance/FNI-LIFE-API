package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Customer;
import com.ace.demoapi.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Optional<Customer> findCustomerById(String id) {
		return customerRepository.findById(id);
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

}
