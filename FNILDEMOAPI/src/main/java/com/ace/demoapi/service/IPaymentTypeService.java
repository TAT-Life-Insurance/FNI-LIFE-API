package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.PaymentType;

public interface IPaymentTypeService {
	
	Optional<PaymentType> findPaymentTypeById(String id);

	List<PaymentType> findAllPaymentType();

}
