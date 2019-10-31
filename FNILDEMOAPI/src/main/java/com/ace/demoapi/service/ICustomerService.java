package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Customer;

public interface ICustomerService {

	Optional<Customer> findCustomerById(String id);

	List<Customer> findAllCustomer();

}