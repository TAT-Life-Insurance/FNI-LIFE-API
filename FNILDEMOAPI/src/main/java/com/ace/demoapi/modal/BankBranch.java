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
public class BankBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BANKBRANCH_GEN")
	private String id;

	private String name;
	private String description;
	private String branchCode;
	private String address;

	@OneToOne
	@JoinColumn(name = "BANKID", referencedColumnName = "ID")
	private Bank bank;

	@OneToOne
	@JoinColumn(name = "TOWNSHIPID", referencedColumnName = "ID")
	private Township township;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	

	public String getFullAddress() {
		String fullAddress = "";
		if (address != null && township != null) {
			String townShip = township == null ? "" : township.getFullTownShip();
			fullAddress = address + ", " + townShip;
		}
		return fullAddress;
	}

	

}
