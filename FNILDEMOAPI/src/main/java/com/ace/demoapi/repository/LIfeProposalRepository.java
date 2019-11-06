package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ace.demoapi.modal.LifeProposal;


@Repository
public interface LIfeProposalRepository extends CrudRepository<LifeProposal, String>{
	
	Optional<LifeProposal> findById(String id);

	List<LifeProposal> findAll();



}
