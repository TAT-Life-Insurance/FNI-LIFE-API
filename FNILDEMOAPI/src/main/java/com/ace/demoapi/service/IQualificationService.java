package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;
import com.ace.demoapi.modal.Qualification;

public interface IQualificationService {
	Optional<Qualification> findQualificationById(String id);

	List<Qualification> findAllQualification();

}
