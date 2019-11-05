package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Qualification;
import com.ace.demoapi.service.IQualificationService;

@RestController
public class QualificationController {
	
	@Autowired
	private IQualificationService qualificationService;

	@GetMapping(path = "/qualifications", produces = "application/json")
public List<Qualification> getAllQualification() {
		List<Qualification> qualificationList = qualificationService.findAllQualification();
		return qualificationList;
	}

	@GetMapping(path = "/qualificaiton/{id}", produces = "application/json")
	public Qualification getQualificationById(@PathVariable String id) {
		return qualificationService.findQualificationById(id).orElse(null);
	}

}
