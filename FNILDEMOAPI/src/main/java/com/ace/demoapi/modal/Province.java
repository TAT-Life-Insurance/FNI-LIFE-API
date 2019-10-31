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

public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PROVINCE_GEN")
	private String id;
	private String provinceNo;
	private String name;
	private String code;
	private String description;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COUNTRYID", referencedColumnName = "ID")
	private Country country;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public Province() {
	}

	public String getId() {
		return id;
	}

	public String getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(String provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFullProvience() {
		return name + "," + country.getName();
	}

}
