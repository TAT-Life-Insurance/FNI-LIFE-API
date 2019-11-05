package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.BankBranch;
import com.ace.demoapi.modal.Organization;;

public interface IOrganizationService {
	
	Optional<Organization> findOrganizationById(String id);

	List<Organization> findAllOrganization();
	
    public void saveOrganization(Organization organization);
	
	public void deleteById(String id);
	

}
