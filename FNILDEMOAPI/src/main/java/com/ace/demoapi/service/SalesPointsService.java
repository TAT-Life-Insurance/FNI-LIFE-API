package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.SalesPoints;
import com.ace.demoapi.repository.SalePointRepository;


@Service
public class SalesPointsService implements ISalesPointsService {
	
	@Autowired
	private SalePointRepository salePointRepository;


	@Override
	public Optional<SalesPoints> findSalesPointsById(String id) {
	
		return salePointRepository.findById(id);
	}

	@Override
	public List<SalesPoints> findAllSalesPoints() {
		
		return salePointRepository.findAll();
	}

}
