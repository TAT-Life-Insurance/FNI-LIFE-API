package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

	Optional<Customer> findById(String id);

	List<Customer> findAll();

}
