package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Agent;
import com.ace.demoapi.repository.AgentReposity;


@Service
public class AgentService implements IAgentService {
	
	@Autowired
	private AgentReposity agentRepository;

	@Override
	public Optional<Agent> findAgentById(String id) {
		
		return agentRepository.findById(id);
	}

	@Override
	public List<Agent> findAllAgent() {
		
		return agentRepository.findAll();
	}
	
	@Override
	public void saveAgent(Agent agent) {
		agentRepository.save(agent);
	    }

	@Override
	public void deleteById(String id) {
		agentRepository.deleteById(id);
		
	}

}
