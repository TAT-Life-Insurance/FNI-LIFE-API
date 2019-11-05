package com.ace.demoapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.common.dto.AgentDTO;
import com.ace.demoapi.modal.Agent;
import com.ace.demoapi.service.IAgentService;

@RestController
public class AgentController {

	@Autowired
	private IAgentService agentService;

	@GetMapping(path = "/agents", produces = "application/json")
	public List<AgentDTO> getAllAgent() {

		List<Agent> agentList = new ArrayList<>();
		List<AgentDTO> agentDTOList = new ArrayList<>();
		agentList = agentService.findAllAgent().stream().limit(10).collect(Collectors.toList());
		for (Agent agent : agentList) {
			AgentDTO agentDTO = new AgentDTO();
			agentDTO.setId(agent.getId());
			agentDTO.setInitialId(agent.getInitialId());
			agentDTO.setFullName(agent.getFullName());
			agentDTO.setFatherName(agent.getFatherName());
			agentDTO.setDateOfBirth(agent.getDateOfBirth());
			agentDTO.setGender(agent.getGender());
			agentDTO.setIdType(agent.getIdType());
			if (null != agent.getFullIdNo()) {
				agentDTO.setFullIdNo(agent.getFullIdNo());
			}
			agentDTO.setResidentAddress(agent.getResidentAddress());
			agentDTO.setBranch(agent.getBranch());
			agentDTO.setCountry(agent.getCountry());
			agentDTO.setLicenseNo(agent.getLiscenseNo());
			agentDTOList.add(agentDTO);
		}

		return agentDTOList;
	}

	@GetMapping(path = "/agent/{id}", produces = "application/json")
	public Agent getAgentById(@PathVariable String id) {
		Agent agent = agentService.findAgentById(id).orElse(null);
		return agent;
	}

	@PostMapping(path = "/saveAgent")
	public Agent saveAgent(@RequestBody Agent agent) {
		agentService.saveAgent(agent);
		return agent;
	}
	
	
	@DeleteMapping("/deleteAgent/{id}")
	public void deleteById(@PathVariable String id){
		agentService.deleteById(id);
		
	}
}
