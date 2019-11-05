package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ace.demoapi.modal.Province;
import com.ace.demoapi.service.IProvinceService;

@RestController
public class ProvinceController {
	
	@Autowired
	private IProvinceService provinceService;

	@GetMapping(path = "/provinces", produces = "application/json")
	public List<Province> getAllProvince() {
		List<Province> provinceList = provinceService.findAllProvince();
		return provinceList;
	}

	@GetMapping(path = "/province/{id}", produces = "application/json")
	public Province getProvinceById(@PathVariable String id) {
		return provinceService.findProvinceById(id).orElse(null);
	}


}
