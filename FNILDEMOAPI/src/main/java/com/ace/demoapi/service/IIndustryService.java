package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Industry;

public interface IIndustryService {
	Optional<Industry> findIndustryById(String id);

	List<Industry> findAllIndustry();

}
