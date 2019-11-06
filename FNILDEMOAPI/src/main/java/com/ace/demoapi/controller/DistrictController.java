package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(path = "/saveDistrict")
	public District saveDistrict(@RequestBody District district) {
		districtService.saveDistrict(district);
		return district;
	}
	
	
	@DeleteMapping("/deleteDistrict/{id}")
	public void deleteById(@PathVariable String id){
		districtService.deleteById(id);
		
	}

}
