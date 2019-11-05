package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Customer;
import com.ace.demoapi.modal.Occupation;
import com.ace.demoapi.repository.CustomerRepository;
import com.ace.demoapi.repository.OccupationRepository;


@Service
public class OccupationService implements IOccupationService {
	
	@Autowired
	private OccupationRepository occupationRepository;

	

	@Override
	public Optional<Occupation> findOccupationById(String id) {
		return occupationRepository.findById(id);
	}

	@Override
	public List<Occupation> findAllOccupation() {
		return occupationRepository.findAll();
	}

}
