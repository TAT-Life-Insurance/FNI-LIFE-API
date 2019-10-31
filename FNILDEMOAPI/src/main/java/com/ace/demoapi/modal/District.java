package com.ace.demoapi.modal;

import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;

public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DISTRICT_GEN")
	private String id;
	private String name;
	private String code;
	private String description;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROVINCEID", referencedColumnName = "ID")
	private Province province;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public District() {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getFullDistrict() {
		return name + "," + province.getName();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
