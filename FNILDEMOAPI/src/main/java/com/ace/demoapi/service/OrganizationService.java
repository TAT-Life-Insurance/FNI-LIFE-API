package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ace.demoapi.modal.Organization;
import com.ace.demoapi.repository.OrganizationRepository;



@Service
public class OrganizationService implements IOrganizationService {
	
	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public Optional<Organization> findOrganizationById(String id) {
		
		 return organizationRepository.findById(id);
	}

	@Override
	public List<Organization> findAllOrganization() {
		
		return organizationRepository.findAll();
	}
	
	@Override
	public void saveOrganization(Organization organization) {
		organizationRepository.save(organization);
	    }

	@Override
	public void deleteById(String id) {
		organizationRepository.deleteById(id);
		
	}


}
