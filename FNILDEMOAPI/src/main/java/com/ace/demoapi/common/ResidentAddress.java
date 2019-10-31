package com.ace.demoapi.common;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ace.demoapi.modal.Township;

public class ResidentAddress {
	private String residentAddress;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESIDENTTOWNSHIPID", referencedColumnName = "ID")
	private Township township;

	public ResidentAddress() {
		township = new Township();
	}

	public ResidentAddress(ResidentAddress residentAddress) {
		this.residentAddress = residentAddress.getResidentAddress();
		this.township = residentAddress.getTownship();
	}

	public String getResidentAddress() {
		return residentAddress;
	}

	public void setResidentAddress(String residentAddress) {
		this.residentAddress = residentAddress;
	}

	public Township getTownship() {
		return this.township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}

	public String getFullResidentAddress() {
		if (residentAddress == null || township == null) {
			return "";
		}

		return residentAddress + ", " + township.getFullTownShip();
	}

}
