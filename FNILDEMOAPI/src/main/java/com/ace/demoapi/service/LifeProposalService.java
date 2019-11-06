package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.LifeProposal;
import com.ace.demoapi.repository.LIfeProposalRepository;


@Service
public class LifeProposalService implements ILifePropasalService {
	
	@Autowired
	private LIfeProposalRepository lifeproposalRepository;


	@Override
	public Optional<LifeProposal> findLifeProposalById(String id) {
		// TODO Auto-generated method stub
		return lifeproposalRepository.findById(id);
	}

	@Override
	public List<LifeProposal> findAllLifeProposal() {
		// TODO Auto-generated method stub
		return lifeproposalRepository.findAll();
	}

}
