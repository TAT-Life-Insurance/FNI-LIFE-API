package com.ace.demoapi.service;

import java.util.List;
import java.util.Optional;

import com.ace.demoapi.modal.District;

public interface IDistrictService {

	Optional<District> findDistrictById(String id);

	List<District> findAllDistrict();


}
