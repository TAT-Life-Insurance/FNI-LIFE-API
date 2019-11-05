package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.ace.demoapi.modal.RelationShip;
import com.ace.demoapi.service.IRelationShipService;

@RestController
public class RelationShipController {
	
	@Autowired
	private IRelationShipService relationshipService;

	@GetMapping(path = "/relationships", produces = "application/json")
public List<RelationShip> getAllRelationShip() {
		List<RelationShip> relationshipList = relationshipService.findAllRelationShip();
		return relationshipList;
	}

	@GetMapping(path = "/relationship/{id}", produces = "application/json")
	public RelationShip getReltionShipById(@PathVariable String id) {
		return relationshipService.findRelationShipById(id).orElse(null);
	}

}
