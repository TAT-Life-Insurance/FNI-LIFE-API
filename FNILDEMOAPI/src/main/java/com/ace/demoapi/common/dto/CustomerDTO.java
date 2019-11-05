package com.ace.demoapi.common.dto;

import java.util.Date;

import com.ace.demoapi.common.Gender;
import com.ace.demoapi.common.IdType;
import com.ace.demoapi.common.ResidentAddress;
import com.ace.demoapi.modal.Branch;
import com.ace.demoapi.modal.Country;

import lombok.Data;

@Data
public class CustomerDTO {
	private String id;
	private String initialId;
	private String fatherName;
	private String fullName;
	private Date dateOfBirth;
	private Gender gender;
	private IdType idType;
	private String fullIdNo;
	private ResidentAddress residentAddress;
	private Branch branch;
	private Country country;
}
