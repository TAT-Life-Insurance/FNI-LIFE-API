package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.RelationShip;

public interface IRelationShipService {
	Optional<RelationShip> findRelationShipById(String id);

	List<RelationShip> findAllRelationShip();


}
