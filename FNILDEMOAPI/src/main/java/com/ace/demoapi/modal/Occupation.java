package com.ace.demoapi.modal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;

import lombok.Data;


@Data
@Entity
public class Occupation {

	@Id
	private String id;

	private String name;
	private String mName;
	private String description;

	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	

}
