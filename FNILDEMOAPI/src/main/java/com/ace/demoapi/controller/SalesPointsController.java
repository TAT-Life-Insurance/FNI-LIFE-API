package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.SalesPoints;
import com.ace.demoapi.service.ISalesPointsService;


@RestController
public class SalesPointsController {

	@Autowired
	private ISalesPointsService salepointService;

	@GetMapping(path = "/salespoints", produces = "application/json")
	public List<SalesPoints> getAllSalesPoints() {
		List<SalesPoints> salespointsList = salepointService.findAllSalesPoints();
		return salespointsList;
	}

	@GetMapping(path = "/salespoints/{id}", produces = "application/json")
	public SalesPoints getSalesPointsById(@PathVariable String id) {
		return salepointService.findSalesPointsById(id).orElse(null);
	}
}
