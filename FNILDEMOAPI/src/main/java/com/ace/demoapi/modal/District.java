package com.ace.demoapi.modal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;

import lombok.Data;

@Data
@Entity
public class District {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DISTRICT_GEN")
	private String id;
	private String name;
	private String code;
	private String description;

	@OneToOne
	@JoinColumn(name = "PROVINCEID", referencedColumnName = "ID")
	private Province province;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;
	
	public String getFullDistrict() {
		return name + "," + province.getName();
	}


	
}
