package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Organization;
import com.ace.demoapi.service.IOrganizationService;



@RestController
public class OrgnizationController {
	
	@Autowired
	private IOrganizationService organizationService;

	@GetMapping(path = "/organizations", produces = "application/json")
	public List<Organization> getAllOrganization() {
		return organizationService.findAllOrganization();
	}

	@GetMapping(path = "/organization/{id}", produces = "application/json")
	public Organization getOrganizationById(@PathVariable String id) {
		return organizationService.findOrganizationById(id).orElse(null);
	}

}
