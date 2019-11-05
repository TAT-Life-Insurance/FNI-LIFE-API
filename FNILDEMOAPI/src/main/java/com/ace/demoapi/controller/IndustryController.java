package com.ace.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ace.demoapi.modal.Industry;
import com.ace.demoapi.service.IIndustryService;

@RestController
public class IndustryController {
	
	@Autowired
	private IIndustryService industryService;

	@GetMapping(path = "/industries", produces = "application/json")
	public List<Industry> getAllIndustry() {
		return industryService.findAllIndustry();
	}

	@GetMapping(path = "/industry/{id}", produces = "application/json")
	public Industry getIndustryById(@PathVariable String id) {
		return industryService.findIndustryById(id).orElse(null);
	}
	
	@PostMapping(path = "/saveIndustry")
	public Industry saveIndustry(@RequestBody Industry industry) {
		industryService.saveIndustry(industry);
		return industry;
	}
	
	
	@DeleteMapping("/deleteIndustry/{id}")
	public void deleteById(@PathVariable String id){
		industryService.deleteById(id);
		
	}

}
