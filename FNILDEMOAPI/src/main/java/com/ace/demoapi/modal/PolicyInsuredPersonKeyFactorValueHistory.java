package com.ace.demoapi.modal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;


import com.ace.demoapi.common.UserRecorder;

import lombok.Data;



@Data
@Entity
public class PolicyInsuredPersonKeyFactorValueHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSUREDPERSONKEYFACTORHIS_GEN")
	private String id;

	private String value;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KEYFACTORID", referencedColumnName = "ID")
	private KeyFactor keyFactor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPOLICYINSUREDPERSONID", referencedColumnName = "ID")
	private PolicyInsuredPersonHistory policyInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;
	public PolicyInsuredPersonKeyFactorValueHistory() {
	}

	public PolicyInsuredPersonKeyFactorValueHistory(KeyFactor keyFactor) {
		this.keyFactor = keyFactor;
	}

	public PolicyInsuredPersonKeyFactorValueHistory(InsuredPersonKeyFactorValue keyFactorValue) {
		this.value = keyFactorValue.getValue();
		this.keyFactor = keyFactorValue.getKeyFactor();
	}

	public PolicyInsuredPersonKeyFactorValueHistory(PolicyInsuredPersonKeyFactorValue keyFactorValue) {
		this.value = keyFactorValue.getValue();
		this.keyFactor = keyFactorValue.getKeyFactor();
	}
	public void setKeyFactor(KeyFactor keyFactor) {
		this.keyFactor = keyFactor;
	}

	public void setPolicyInsuredPersonInfo(PolicyInsuredPersonHistory policyInsuredPerson) {
		this.policyInsuredPerson = policyInsuredPerson;
	}

}