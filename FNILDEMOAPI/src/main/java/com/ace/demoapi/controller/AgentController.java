package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Agent;
import com.ace.demoapi.service.IAgentService;

@RestController
public class AgentController {
	
	@Autowired
	private IAgentService agentService;

	@GetMapping(path = "/agents", produces = "application/json")
	public List<Agent> getAllAgent() {
		return agentService.findAllAgent();
	}

	@GetMapping(path = "/agent/{id}", produces = "application/json")
	public Agent getAgentById(@PathVariable String id) {
		return agentService.findAgentById(id).orElse(null);
	}

}
