package com.ace.demoapi.common;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import com.ace.demoapi.modal.Township;

import lombok.Data;

@Data
@Embeddable
public class PermanentAddress {

	private String permanentAddress;
	@JoinColumn(name = "PERMANENTTOWNSHIPID", referencedColumnName = "ID")
	private Township township;

	

	public String getFullAddress() {
		if (permanentAddress == null || township == null) {
			return "";
		}
		return permanentAddress + ", " + township.getFullTownShip();
	}
}
