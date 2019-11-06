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
import com.ace.demoapi.common.dto.InsuredPersonAddOnDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name=TableName.INSUREDPERSONADDON)
public class InsuredPersonAddon {
	@Id
	private String id;

	private double proposedPremium;
	private double proposedSumInsured;
	private double approvedPremium;
	private double approvedSumInsured;
	private double premiumRate;

	@OneToOne
	@JoinColumn(name = "LIFEPRODUCTADDONID", referencedColumnName = "ID")
	private AddOn addOn;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPROPOSALINSUREDPERSONID", referencedColumnName = "ID")
	private ProposalInsuredPerson proposalInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	
	public InsuredPersonAddon(AddOn addOn, double proposedSumInsured) {
		this.addOn = addOn;
		this.proposedSumInsured = proposedSumInsured;
	}

	public InsuredPersonAddon(PolicyInsuredPersonAddon policyInsPersonaddOn) {
		this.addOn = policyInsPersonaddOn.getAddOn();
		this.proposedPremium = policyInsPersonaddOn.getPremium();
		this.proposedSumInsured = policyInsPersonaddOn.getSumInsured();
		this.approvedPremium = policyInsPersonaddOn.getPremium();
		this.approvedSumInsured = policyInsPersonaddOn.getSumInsured();
	}

	public InsuredPersonAddon(InsuredPersonAddOnDTO dto) {
		this.addOn = dto.getAddOn();
		this.proposedSumInsured = dto.getAddOnSumInsured();
		this.proposedPremium = dto.getPremium();
		this.approvedPremium = dto.getApprovedPremium();
		this.approvedSumInsured = dto.getApprovedSumInsured();
		if (dto.isExistEntity()) {
			this.id = dto.getTempId();
			this.version = dto.getVersion();
		}
	}
}
