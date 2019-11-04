package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ace.demoapi.modal.District;
import com.ace.demoapi.repository.DistrictRepository;

@Service
public class DistrictService implements IDistrictService {

	@Autowired
	private DistrictRepository districtRepository;

	@Override
	public Optional<District> findDistrictById(String id) {
		
		return districtRepository.findById(id);
	}

	@Override
	public List<District> findAllDistrict() {
		
		return districtRepository.findAll();
	}
}
