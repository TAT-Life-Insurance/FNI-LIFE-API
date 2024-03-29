package com.ace.demoapi.modal;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class SalesPoints implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	private String code;
	private String description;
	private String phone;
	private String address;
	private String email;
	private String receivableAcName;

	
	
	@OneToOne
	@JoinColumn(name = "TOWNSHIPID", referencedColumnName = "ID")
	private Township township;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	@JsonIgnore
	@Embedded
	private UserRecorder recorder;

	@JsonIgnore
	@Version
	private int version;

}
