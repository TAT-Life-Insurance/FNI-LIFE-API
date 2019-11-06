package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.LifeProposal;

public interface ILifePropasalService {

	

	Optional<LifeProposal> findLifeProposalById(String id);

	List<LifeProposal> findAllLifeProposal();
}
