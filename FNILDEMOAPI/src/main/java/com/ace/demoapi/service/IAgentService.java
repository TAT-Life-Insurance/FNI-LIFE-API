package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Agent;

public interface IAgentService {
	

	Optional<Agent> findAgentById(String id);

	List<Agent> findAllAgent();


}
