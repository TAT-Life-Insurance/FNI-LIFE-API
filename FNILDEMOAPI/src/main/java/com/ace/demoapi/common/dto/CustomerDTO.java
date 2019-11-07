package com.ace.demoapi.common.dto;

import java.util.Date;

import com.ace.demoapi.common.Gender;
import com.ace.demoapi.common.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
	private String id;
	private String initialId;
	private String fatherName;
	private String fullName;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateOfBirth;
	private Gender gender;
	private IdType idType;
	private String fullIdNo;
	private String residentAddressId;
	private String branchId;
	private String countryId;
}
