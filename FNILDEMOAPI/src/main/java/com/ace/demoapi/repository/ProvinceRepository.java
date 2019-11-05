package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Province;

public interface ProvinceRepository extends CrudRepository<Province, String>{

	Optional<Province> findById(String id);

	List<Province> findAll();
}
