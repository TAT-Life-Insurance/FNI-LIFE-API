package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Qualification;
import com.ace.demoapi.modal.Township;
import com.ace.demoapi.service.ITownshipService;

@RestController
public class TownshipController {
	

	@Autowired
	private ITownshipService townshipService;

	@GetMapping(path = "/townships", produces = "application/json")
public List<Township> getAllTownship() {
		List<Township> townshipList = townshipService.findAllTownship();
		return townshipList;
	}

	@GetMapping(path = "/township/{id}", produces = "application/json")
	public Township getTownshipById(@PathVariable String id) {
		return townshipService.findTownshipById(id).orElse(null);
	}

}
