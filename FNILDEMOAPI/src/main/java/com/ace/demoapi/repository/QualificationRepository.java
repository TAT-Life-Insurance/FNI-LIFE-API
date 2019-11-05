package com.ace.demoapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ace.demoapi.modal.Qualification;

public interface QualificationRepository  extends CrudRepository<Qualification, String>{
	Optional<Qualification> findById(String id);

	List<Qualification> findAll();

}
