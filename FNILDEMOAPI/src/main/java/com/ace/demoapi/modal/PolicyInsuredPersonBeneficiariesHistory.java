package com.ace.demoapi.modal;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;



import com.ace.demoapi.common.ClaimStatus;
import com.ace.demoapi.common.Gender;
import com.ace.demoapi.common.IdType;
import com.ace.demoapi.common.Name;
import com.ace.demoapi.common.ResidentAddress;
import com.ace.demoapi.common.UserRecorder;

import lombok.Data;

@Data
@Entity
public class PolicyInsuredPersonBeneficiariesHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSUREDPERSONBENEFICIARIESHIS_GEN")
	private String id;

	private int age;
	private float percentage;
	private String beneficiaryNo;
	private String initialId;
	private String idNo;
	private String phone;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@Enumerated(EnumType.STRING)
	private ClaimStatus claimStatus;

	@Embedded
	private ResidentAddress residentAddress;

	@Embedded
	private Name name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELATIONSHIPID", referencedColumnName = "ID")
	private RelationShip relationship;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private PolicyInsuredPersonHistory policyInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;
	public PolicyInsuredPersonBeneficiariesHistory() {
	}

	public PolicyInsuredPersonBeneficiariesHistory(InsuredPersonBeneficiaries insuredPersonBeneficiaries) {
		this.beneficiaryNo = insuredPersonBeneficiaries.getBeneficiaryNo();
		this.dateOfBirth = insuredPersonBeneficiaries.getDateOfBirth();
		this.age = insuredPersonBeneficiaries.getAge();
		this.percentage = insuredPersonBeneficiaries.getPercentage();
		this.relationship = insuredPersonBeneficiaries.getRelationship();
		this.initialId = insuredPersonBeneficiaries.getInitialId();
		this.idNo = insuredPersonBeneficiaries.getIdNo();
		this.gender = insuredPersonBeneficiaries.getGender();
		this.idType = insuredPersonBeneficiaries.getIdType();
		this.residentAddress = insuredPersonBeneficiaries.getResidentAddress();
		this.name = insuredPersonBeneficiaries.getName();
		this.relationship = insuredPersonBeneficiaries.getRelationship();
		this.phone = insuredPersonBeneficiaries.getPhone();
	}

	public PolicyInsuredPersonBeneficiariesHistory(PolicyInsuredPersonBeneficiaries insuredPersonBeneficiaries) {
		this.beneficiaryNo = insuredPersonBeneficiaries.getBeneficiaryNo();
		this.dateOfBirth = insuredPersonBeneficiaries.getDateOfBirth();
		this.age = insuredPersonBeneficiaries.getAge();
		this.percentage = insuredPersonBeneficiaries.getPercentage();
		this.relationship = insuredPersonBeneficiaries.getRelationship();
		this.initialId = insuredPersonBeneficiaries.getInitialId();
		this.idNo = insuredPersonBeneficiaries.getIdNo();
		this.gender = insuredPersonBeneficiaries.getGender();
		this.idType = insuredPersonBeneficiaries.getIdType();
		this.residentAddress = insuredPersonBeneficiaries.getResidentAddress();
		this.name = insuredPersonBeneficiaries.getName();
		this.relationship = insuredPersonBeneficiaries.getRelationship();
		this.phone = insuredPersonBeneficiaries.getPhone();
	}

	
}
