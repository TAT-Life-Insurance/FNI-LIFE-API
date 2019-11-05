package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.demoapi.modal.Qualification;
import com.ace.demoapi.repository.QualificationRepository;


@Service
public class QualificationService implements IQualificationService {

	@Autowired
	private QualificationRepository qualificationRepository;


	@Override
	public Optional<Qualification> findQualificationById(String id) {
		// TODO Auto-generated method stub
		return qualificationRepository.findById(id);
	}

	@Override
	public List<Qualification> findAllQualification() {
		// TODO Auto-generated method stub
		return qualificationRepository.findAll();
	}

}
