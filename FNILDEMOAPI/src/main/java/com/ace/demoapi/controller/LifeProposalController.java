package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.LifeProposal;
import com.ace.demoapi.service.ILifePropasalService;


@RestController
public class LifeProposalController {

	
	@Autowired
	private ILifePropasalService lifeProposalService;

	@GetMapping(path = "/lifeproposals", produces = "application/json")
	public List<LifeProposal> getAllIndustry() {
		return lifeProposalService.findAllLifeProposal();
	}

	@GetMapping(path = "/lifeproposal/{id}", produces = "application/json")
	public LifeProposal getLifeProposalById(@PathVariable String id) {
		return lifeProposalService.findLifeProposalById(id).orElse(null);
	}
}
