package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Organization;
import com.ace.demoapi.modal.PaymentType;


public interface OrganizationRepository extends CrudRepository<Organization, String> {
	
	Optional<Organization> findById(String id);

	List<Organization> findAll();


}
