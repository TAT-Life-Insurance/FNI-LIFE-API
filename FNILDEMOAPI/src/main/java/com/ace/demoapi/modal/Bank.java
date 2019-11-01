package com.ace.demoapi.modal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;

@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BANK_GEN")
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

	public Bank() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAcode() {
		return this.acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public String getCscBankCode() {
		return cscBankCode;
	}

	public void setCscBankCode(String cscBankCode) {
		this.cscBankCode = cscBankCode;
	}
}
