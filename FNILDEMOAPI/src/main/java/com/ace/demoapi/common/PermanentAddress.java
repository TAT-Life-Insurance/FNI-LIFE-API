package com.ace.demoapi.common;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ace.demoapi.modal.Township;

import lombok.Data;

@Data
@Embeddable
public class PermanentAddress {

	private String permanentAddress;
    @OneToOne
	@JoinColumn(name = "PERMANENTTOWNSHIPID", referencedColumnName = "ID")
	private Township township;

	

	public String getFullAddress() {
		if (permanentAddress == null || township == null) {
			return "";
		}
		return permanentAddress + ", " + township.getFullTownShip();
	}
}
