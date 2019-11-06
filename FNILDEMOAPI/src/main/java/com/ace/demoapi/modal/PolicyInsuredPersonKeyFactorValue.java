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
public class PolicyInsuredPersonKeyFactorValue {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSUREDPERSONKEYFACTOR_GEN")
	private String id;

	private String value;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "KEYFACTORID", referencedColumnName = "ID")
	private KeyFactor keyFactor;

	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public PolicyInsuredPersonKeyFactorValue() {
	}

	public PolicyInsuredPersonKeyFactorValue(KeyFactor keyFactor) {
		this.keyFactor = keyFactor;
	}

	public PolicyInsuredPersonKeyFactorValue(InsuredPersonKeyFactorValue keyFactorValue) {
		this.value = keyFactorValue.getValue();
		this.keyFactor = keyFactorValue.getKeyFactor();
	}

	public PolicyInsuredPersonKeyFactorValue(PolicyInsuredPersonKeyFactorValue keyFactorValue) {
		this.value = keyFactorValue.getValue();
		this.keyFactor = keyFactorValue.getKeyFactor();
	}

	public PolicyInsuredPersonKeyFactorValue(PolicyInsuredPersonKeyFactorValueHistory keyFactorValue) {
		this.value = keyFactorValue.getValue();
		this.keyFactor = keyFactorValue.getKeyFactor();
	}

	

}