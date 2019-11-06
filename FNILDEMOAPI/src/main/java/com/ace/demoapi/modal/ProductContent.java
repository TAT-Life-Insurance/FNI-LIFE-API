package com.ace.demoapi.modal;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;

import lombok.Data;

@Data
@Entity
public class ProductContent implements Serializable {

	private static final long serialVersionUID = 2253619645955447054L;

	@Id
	private String id;

	private String name;
	private String description;
	private String code;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	

}