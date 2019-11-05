package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.PaymentType;
import com.ace.demoapi.repository.PaymentTypeRepository;

@Service
public class PaymentTypeService implements IPaymentTypeService {
	
	@Autowired
	private PaymentTypeRepository paymentTypeRepository;

	@Override
	public Optional<PaymentType> findPaymentTypeById(String id) {
		
		return paymentTypeRepository.findById(id);
	}

	@Override
	public List<PaymentType>  findAllPaymentType() {
		
		return paymentTypeRepository.findAll();
	}

	

}
