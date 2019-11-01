package com.ace.demoapi.modal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;


@Data
@Embeddable
public class OfficeAddress {
	private String officeAddress;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICETOWNSHIPID", referencedColumnName = "ID")
	private Township township;

	

}
