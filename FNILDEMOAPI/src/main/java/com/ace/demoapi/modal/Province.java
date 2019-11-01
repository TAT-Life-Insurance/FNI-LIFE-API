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

	

	public String getFullProvience() {
		return name + "," + country.getName();
	}

}
