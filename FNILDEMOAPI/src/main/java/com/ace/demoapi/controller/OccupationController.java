package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.ace.demoapi.modal.Occupation;
import com.ace.demoapi.service.IOccupationService;

@RestController
public class OccupationController {
	
	@Autowired
	private IOccupationService occupationService;

	@GetMapping(path = "/occupations", produces = "application/json")
	public List<Occupation> getAllOccupation() {
		List<Occupation> occupationList = occupationService.findAllOccupation();
		return occupationList;
	}

	@GetMapping(path = "/occupation/{id}", produces = "application/json")
	public Occupation getOccupationById(@PathVariable String id) {
		return occupationService.findOccupationById(id).orElse(null);
	}

}
