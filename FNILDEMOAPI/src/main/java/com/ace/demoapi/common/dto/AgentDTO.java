package com.ace.demoapi.common.dto;

import java.util.Date;

import com.ace.demoapi.common.Gender;
import com.ace.demoapi.common.IdType;
import com.ace.demoapi.common.ResidentAddress;

import lombok.Data;

@Data
public class AgentDTO {
	private String id;
	private String fullName;
	private String fatherName;
	private Date dateOfBirth;
	private Gender gender;
	private IdType idType;
	private String fullIdNo;
	private String licenseNo;
	private ResidentAddress residentAddress;

}
