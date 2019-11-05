package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.SalesPoints;

public interface ISalesPointsService {

	
	Optional<SalesPoints> findSalesPointsById(String id);

	List<SalesPoints> findAllSalesPoints();
}
