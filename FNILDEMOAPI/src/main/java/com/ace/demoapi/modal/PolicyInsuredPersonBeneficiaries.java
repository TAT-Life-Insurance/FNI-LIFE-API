package com.ace.demoapi.modal;

import java.io.Serializable;
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
import com.ace.demoapi.common.Utils;
import com.ace.demoapi.common.dto.BeneficiariesInfoDTO;

import lombok.Data;

@Data
@Entity
public class PolicyInsuredPersonBeneficiaries implements Serializable {
	private static final long serialVersionUID = 7644853693620015430L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSUREDPERSONBENEFICIARIES_GEN")
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
	private PolicyInsuredPerson policyInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getDateOfBirthForView() {
		if (dateOfBirth == null)
			return "-";
		return Utils.formattedDate(dateOfBirth);
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		String result = "";
		if (name != null) {
			if (initialId != null && !initialId.isEmpty()) {
				result = initialId.trim();
			}
			if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
				result = result + " " + name.getFirstName();
			}
			if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
				result = result + " " + name.getMiddleName();
			}
			if (name.getLastName() != null && !name.getLastName().isEmpty()) {
				result = result + " " + name.getLastName();
			}
		}
		return result;
	}

	public String getFullAddress() {
		String result = "";
		if (residentAddress != null) {
			if (residentAddress.getResidentAddress() != null && !residentAddress.getResidentAddress().isEmpty()) {
				result = result + residentAddress.getResidentAddress();
			}
			if (residentAddress.getTownship() != null && !residentAddress.getTownship().getFullTownShip().isEmpty()) {
				result = result + ", " + residentAddress.getTownship().getFullTownShip();
			}
		}
		return result;
	}
	

	public PolicyInsuredPersonBeneficiaries(InsuredPersonBeneficiaries insuredPersonBeneficiaries) {
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

	public PolicyInsuredPersonBeneficiaries(PolicyInsuredPersonBeneficiariesHistory insuredPersonBeneficiaries) {
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
		this.claimStatus = insuredPersonBeneficiaries.getClaimStatus();
		this.phone = insuredPersonBeneficiaries.getPhone();
	}

	public PolicyInsuredPersonBeneficiaries(BeneficiariesInfoDTO dto) {
		// this.id = beneficiariesInfoDTO.getTempId();
		this.beneficiaryNo = dto.getBeneficiaryNo();
		this.dateOfBirth = dto.getDateOfBirth();
		this.age = dto.getAge();
		this.percentage = dto.getPercentage();
		this.initialId = dto.getInitialId();
		this.idNo = dto.getIdNo();
		this.gender = dto.getGender();
		this.idType = dto.getIdType();
		this.residentAddress = dto.getResidentAddress();
		this.name = dto.getName();
		this.relationship = dto.getRelationship();
		// this.version = beneficiariesInfoDTO.getVersion();
		this.phone = dto.getPhone();
		if (dto.isExistEntity()) {
			this.id = dto.getTempId();
			this.version = dto.getVersion();
		}
	}

	
}