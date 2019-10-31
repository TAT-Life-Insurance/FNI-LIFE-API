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

public class Township {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TOWNSHIP_GEN")
	private String id;

	private String name;
	private String shortName;
	private String code;
	private String description;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICTID", referencedColumnName = "ID")
	private District district;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public Township() {
	}

	public Township(String id, String name, String shortName, String code, String description, District district, int version) {
		super();
		this.id = id;
		this.name = name;
		this.shortName = shortName;
		this.code = code;
		this.description = description;
		this.district = district;
		this.version = version;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFullTownShip() {
		String fullAddress = name;
		if (district != null && !district.getFullDistrict().isEmpty()) {
			fullAddress = name + ", " + district.getFullDistrict();
		}
		return fullAddress;
	}

}
