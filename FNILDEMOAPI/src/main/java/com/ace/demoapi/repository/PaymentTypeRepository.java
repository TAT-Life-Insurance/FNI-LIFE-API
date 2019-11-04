package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.ace.demoapi.modal.PaymentType;

public interface PaymentTypeRepository extends CrudRepository<PaymentType, String> {
	
	Optional<PaymentType> findById(String id);

	List<PaymentType> findAll();

}
