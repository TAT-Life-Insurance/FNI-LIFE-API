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
import javax.persistence.Table;
import javax.persistence.Version;

import com.ace.demoapi.common.TableName;
import com.ace.demoapi.common.UserRecorder;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;


@Data
@Entity
@Table(name = TableName.INSUREDPERSONKEYFACTORVALUE)
public class InsuredPersonKeyFactorValue {
	@Id
	private String id;

	private String value;
	@OneToOne
	@JoinColumn(name = "KEYFACTORID", referencedColumnName = "ID")
	private KeyFactor keyFactor;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPROPOSALINSUREDPERSONID", referencedColumnName = "ID")
	private ProposalInsuredPerson proposalInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;
	
	public InsuredPersonKeyFactorValue() {
	}

	public InsuredPersonKeyFactorValue(KeyFactor keyFactor) {
		this.keyFactor = keyFactor;
	}

	public InsuredPersonKeyFactorValue(String value, KeyFactor keyFactor) {
		this.value = value;
		this.keyFactor = keyFactor;
	}

	public InsuredPersonKeyFactorValue(PolicyInsuredPersonKeyFactorValue pinsuredPersonKeyFactorValue) {
		this.keyFactor = pinsuredPersonKeyFactorValue.getKeyFactor();
		this.value = pinsuredPersonKeyFactorValue.getValue();
	}


	
}