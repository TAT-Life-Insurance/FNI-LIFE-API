package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Township;
import com.ace.demoapi.repository.TownshipRepository;


@Service
public class TownshipService implements ITownshipService {
	
	@Autowired
	private TownshipRepository townshipRepository;

	@Override
	public Optional<Township> findTownshipById(String id) {
		return townshipRepository.findById(id);
	}

	@Override
	public List<Township> findAllTownship() {
		return townshipRepository.findAll();
	}

}
