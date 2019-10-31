package com.ace.demoapi.modal;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class OfficeAddress {
	private String officeAddress;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OFFICETOWNSHIPID", referencedColumnName = "ID")
	private Township township;

	public OfficeAddress() {
	}

	public OfficeAddress(String officeAddress, Township township) {
		this.officeAddress = officeAddress;
		this.township = township;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public Township getTownship() {
		return this.township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public void getFullTownShip() {

	}

}
