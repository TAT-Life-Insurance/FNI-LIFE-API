package com.ace.demoapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.common.dto.CustomerDTO;
import com.ace.demoapi.modal.Customer;
import com.ace.demoapi.service.ICustomerService;

@RestController
public class CustomerController {
	@Autowired
	private ICustomerService customerService;

	@GetMapping(path = "/customers")
	public List<CustomerDTO> getAllCustomer() {
		return customerService.findAllCustomer().stream().limit(10).map(this::getDTOFromCustomer).collect(Collectors.toList());
	}
	
	private CustomerDTO getDTOFromCustomer(Customer customer) {
		CustomerDTO customerDTO = CustomerDTO.builder()
				.id(customer.getId())
				.initialId(customer.getInitialId())
				.fullName(customer.getFullName())
				.fatherName(customer.getFatherName())
				.dateOfBirth(customer.getDateOfBirth())
				.gender(customer.getGender())
				.idType(customer.getIdType())
				.build();
		
		if (null != customer.getFullIdNo()) {
			customerDTO.setFullIdNo(customer.getFullIdNo());
		}
		if(null != customer.getBranch()) {
			customerDTO.setBranchId(customer.getBranch().getId());	
		}
		if(null != customer.getCountry()) {
			customerDTO.setCountryId(customer.getCountry().getId());	
		}
		if(null != customer.getResidentAddress()) {
			if(null != customer.getResidentAddress().getTownship()) {
				customerDTO.setResidentAddressId(customer.getResidentAddress().getTownship().getId());
			}
		}
		return customerDTO;
	}

	@GetMapping(path = "/customer/{id}", produces = "application/json")
	public Customer getCustomerById(@PathVariable String id) {
		return customerService.findCustomerById(id).orElse(null);
	}
}
