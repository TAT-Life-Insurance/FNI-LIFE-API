package com.ace.demoapi.controller;

import java.util.ArrayList;
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
		List<Customer> customerList = new ArrayList<>();
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		 customerList = customerService.findAllCustomer().stream().limit(10).collect(Collectors.toList());
		 for(Customer customer :customerList ) {
			 CustomerDTO customerDTO = new CustomerDTO();
			 customerDTO.setId(customer.getId());
			 customerDTO.setFullName(customer.getFullName());
			 customerDTO.setFatherName(customer.getFatherName());
			 customerDTO.setDateOfBirth(customer.getDateOfBirth());
			 customerDTO.setGender(customer.getGender());
			 customerDTO.setIdType(customer.getIdType());
			 if(null != customer.getFullIdNo()) {
				 customerDTO.setFullIdNo(customer.getFullIdNo());
			 }
			 customerDTO.setAddress(customer.getFullAddress());
			 customerDTOList.add(customerDTO);
		 }
		
		 return customerDTOList;
	}

	@GetMapping(path = "/customer/{id}", produces = "application/json")
	public Customer getCustomerById(@PathVariable String id) {
		return customerService.findCustomerById(id).orElse(null);
	}
}
