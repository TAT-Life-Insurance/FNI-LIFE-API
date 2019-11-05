package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Province;
import com.ace.demoapi.repository.ProvinceRepository;


@Service
public class ProvinceService implements IProvinceService {
	
	@Autowired
	private ProvinceRepository provinceRepository;

	@Override
	public Optional<Province> findProvinceById(String id) {
		return provinceRepository.findById(id);
	}

	@Override
	public List<Province> findAllProvince() {
		return provinceRepository.findAll();
	}

}
