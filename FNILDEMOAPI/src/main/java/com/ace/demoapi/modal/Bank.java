package com.ace.demoapi.modal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;

import lombok.Data;

@Entity
@Data
public class Bank {

	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_GEN")
	private String id;

	private String name;
	private String description;
	private String acode;

	@Column(name = "CSC_BANK_CODE")
	private String cscBankCode;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	
}
