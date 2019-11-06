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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.ace.demoapi.common.Gender;
import com.ace.demoapi.common.IdType;
import com.ace.demoapi.common.Name;
import com.ace.demoapi.common.ResidentAddress;
import com.ace.demoapi.common.TableName;
import com.ace.demoapi.common.UserRecorder;
import com.ace.demoapi.common.dto.BeneficiariesInfoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
@Data
@Entity
@Table(name=TableName.INSUREDPERSONBENEFICIARIES)
public class InsuredPersonBeneficiaries {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "INSUREDPERSONBENEFICIARIES_GEN")
	private String id;

	private String beneficiaryNo;
	private String initialId;
	private String idNo;
	private String phone;

	private float percentage;

	private int age;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@Embedded
	private Name name;

	@Embedded
	private ResidentAddress residentAddress;

	@OneToOne
	@JoinColumn(name = "RELATIONSHIPID", referencedColumnName = "ID")
	private RelationShip relationship;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSUREDPERSONID", referencedColumnName = "ID")
	private ProposalInsuredPerson proposalInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	

	/**********************
	 * Generated Method
	 */
	public String getFullName() {
		String result = "";
		if (name != null) {
			if (initialId != null && !initialId.isEmpty()) {
				result = initialId;
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

	public InsuredPersonBeneficiaries() {
	}

	public InsuredPersonBeneficiaries(PolicyInsuredPersonBeneficiaries pinsuredPersonBeneficiaries) {
		this.beneficiaryNo = pinsuredPersonBeneficiaries.getBeneficiaryNo();
		this.age = pinsuredPersonBeneficiaries.getAge();
		this.percentage = pinsuredPersonBeneficiaries.getPercentage();
		this.initialId = pinsuredPersonBeneficiaries.getInitialId();
		this.idNo = pinsuredPersonBeneficiaries.getIdNo();
		this.gender = pinsuredPersonBeneficiaries.getGender();
		this.idType = pinsuredPersonBeneficiaries.getIdType();
		this.residentAddress = pinsuredPersonBeneficiaries.getResidentAddress();
		this.name = pinsuredPersonBeneficiaries.getName();
		this.relationship = pinsuredPersonBeneficiaries.getRelationship();
		this.phone = pinsuredPersonBeneficiaries.getPhone();
	}

	public InsuredPersonBeneficiaries(BeneficiariesInfoDTO dto) {
		this.age = dto.getAge();
		this.percentage = dto.getPercentage();
		this.beneficiaryNo = dto.getBeneficiaryNo();
		this.initialId = dto.getInitialId();
		this.idNo = dto.getFullIdNo();
		this.gender = dto.getGender();
		this.idType = dto.getIdType();
		this.residentAddress = dto.getResidentAddress();
		this.name = dto.getName();
		this.relationship = dto.getRelationship();
		this.phone = dto.getPhone();
		if (dto.isExistEntity()) {
			this.id = dto.getTempId();
			this.version = dto.getVersion();
		}
	}

	public InsuredPersonBeneficiaries(String beneficiaryNo, int age, float percentage, String initialId, String idNo, Gender gender, IdType idType, ResidentAddress residentAddress,
			Name name, RelationShip relationship) {
		this.beneficiaryNo = beneficiaryNo;
		this.age = age;
		this.percentage = percentage;
		this.initialId = initialId;
		this.idNo = idNo;
		this.gender = gender;
		this.idType = idType;
		this.residentAddress = residentAddress;
		this.name = name;
		this.relationship = relationship;
	}

}
