package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.RelationShip;
import com.ace.demoapi.repository.RelationShipRepository;


@Service
public class RelationShipService implements IRelationShipService {
	
	@Autowired
	private RelationShipRepository relationshipRepository;

	@Override
	public Optional<RelationShip> findRelationShipById(String id) {
		// TODO Auto-generated method stub
		return relationshipRepository.findById(id);
	}

	@Override
	public List<RelationShip> findAllRelationShip() {
		// TODO Auto-generated method stub
		return relationshipRepository.findAll();
	}

}
