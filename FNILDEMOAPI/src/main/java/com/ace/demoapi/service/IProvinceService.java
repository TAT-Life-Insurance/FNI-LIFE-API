package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.Province;

public interface IProvinceService {
	Optional<Province> findProvinceById(String id);

	List<Province> findAllProvince();

}
