package com.ace.demoapi.common;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ace.demoapi.modal.Township;

import lombok.Data;


@Data
@Embeddable
public class ResidentAddress {
	private String residentAddress;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RESIDENTTOWNSHIPID", referencedColumnName = "ID")
	private Township township;

	public ResidentAddress() {
		township = new Township();
	}

	

	public String getFullResidentAddress() {
		if (residentAddress == null || township == null) {
			return "";
		}

		return residentAddress + ", " + township.getFullTownShip();
	}

}
