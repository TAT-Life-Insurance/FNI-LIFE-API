package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.ace.demoapi.modal.District;
import com.ace.demoapi.service.IDistrictService;

@RestController
public class DistrictController {
	
	@Autowired
	private IDistrictService districtService;

	@GetMapping(path = "/districts", produces = "application/json")
	public List<District> getAllDistrict() {
		return districtService.findAllDistrict();
	}

	@GetMapping(path = "/district/{id}", produces = "application/json")
	public District getDistrictById(@PathVariable String id) {
		return districtService.findDistrictById(id).orElse(null);
	}

}
