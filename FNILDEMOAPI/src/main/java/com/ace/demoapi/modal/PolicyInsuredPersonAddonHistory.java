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
public class PolicyInsuredPersonAddonHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSUREDPERSONADDONHIS_GEN")
	private String id;

	private double premium;
	private double sumInsured;
	private double premiumRate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTADDONID", referencedColumnName = "ID")
	private AddOn addOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPOLICYINSUREDPERSONID", referencedColumnName = "ID")
	private PolicyInsuredPersonHistory policyInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public PolicyInsuredPersonAddonHistory() {
	}

	public PolicyInsuredPersonAddonHistory(InsuredPersonAddon addOn) {
		this.premium = addOn.getProposedPremium();
		this.sumInsured = addOn.getProposedSumInsured();
		this.premiumRate = addOn.getPremiumRate();
		this.addOn = addOn.getAddOn();
	}

	public PolicyInsuredPersonAddonHistory(PolicyInsuredPersonAddon addOn) {
		this.premium = addOn.getPremium();
		this.sumInsured = addOn.getSumInsured();
		this.premiumRate = addOn.getPremiumRate();
		this.addOn = addOn.getAddOn();
	}

	public PolicyInsuredPersonAddonHistory(AddOn addOn, int addOnSumInsured) {
		this.addOn = addOn;
		this.sumInsured = addOnSumInsured;
	}
	public void setPolicyInsuredPersonInfo(PolicyInsuredPersonHistory policyInsuredPerson) {
		this.policyInsuredPerson = policyInsuredPerson;
	}
}