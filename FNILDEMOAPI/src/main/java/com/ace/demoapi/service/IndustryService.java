package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ace.demoapi.modal.Industry;
import com.ace.demoapi.repository.IndustryRepository;

@Service
public class IndustryService implements IIndustryService {

	@Autowired
	private IndustryRepository industryRepository;

	@Override
	public Optional<Industry> findIndustryById(String id) {
		
		return industryRepository.findById(id);
	}

	@Override
	public List<Industry> findAllIndustry() {
		
		return industryRepository.findAll();
	}
	
	@Override
	public void saveIndustry(Industry industry) {
		industryRepository.save(industry);
	    }

	@Override
	public void deleteById(String id) {
		industryRepository.deleteById(id);
		
	}



}
