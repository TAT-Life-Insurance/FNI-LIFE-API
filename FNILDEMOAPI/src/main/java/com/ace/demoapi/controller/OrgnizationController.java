package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(path = "/saveOrganization")
	public Organization savOrganization(@RequestBody Organization organization) {
		organizationService.saveOrganization(organization);
		
		return organization;
	}
	
	
	@DeleteMapping("/deleteOrganization/{id}")
	public void deleteById(@PathVariable String id){
		organizationService.deleteById(id);
		
	}

}
