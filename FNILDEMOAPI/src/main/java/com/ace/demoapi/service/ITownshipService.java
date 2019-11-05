package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Township;

public interface ITownshipService {
	
	Optional<Township> findTownshipById(String id);

	List<Township> findAllTownship();


}
