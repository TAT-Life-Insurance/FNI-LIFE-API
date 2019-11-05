package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Occupation;

public interface IOccupationService {
	

	Optional<Occupation> findOccupationById(String id);

	List<Occupation> findAllOccupation();

}
