package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Agent;

public interface AgentReposity extends CrudRepository<Agent, String>{
	
	Optional<Agent> findById(String id);

	List<Agent> findAll();


}
