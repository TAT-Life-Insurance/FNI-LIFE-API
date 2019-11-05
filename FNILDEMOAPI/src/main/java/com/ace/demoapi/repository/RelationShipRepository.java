package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.RelationShip;

public interface RelationShipRepository  extends CrudRepository<RelationShip, String>{
	
	Optional<RelationShip> findById(String id);

	List<RelationShip> findAll();

}
