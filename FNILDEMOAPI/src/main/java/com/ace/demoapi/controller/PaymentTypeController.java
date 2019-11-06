package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ace.demoapi.modal.PaymentType;
import com.ace.demoapi.service.IPaymentTypeService;

@RestController
public class PaymentTypeController {
	

	@Autowired
	private IPaymentTypeService paymentTypeService;

	@GetMapping(path = "/paymentTypes", produces = "application/json")
	public List<PaymentType> getAllPaymentType() {
		return paymentTypeService.findAllPaymentType();
	}

	@GetMapping(path = "/paymentType/{id}", produces = "application/json")
	public PaymentType getPaymentTypeById(@PathVariable String id) {
		return paymentTypeService.findPaymentTypeById(id).orElse(null);
	}
	
	@PostMapping(path = "/savePaymentType")
	public PaymentType savePaymentType(@RequestBody PaymentType paymentType) {
		paymentTypeService.savePaymentType(paymentType);
		return paymentType;
	}
	
	
	@DeleteMapping("/deletePaymentType/{id}")
	public void deleteById(@PathVariable String id){
		paymentTypeService.deleteById(id);
		
	}


}
