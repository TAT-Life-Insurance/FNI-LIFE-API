package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.ace.demoapi.common.ContentInfo;
import com.ace.demoapi.common.FamilyInfo;
import com.ace.demoapi.common.Gender;
import com.ace.demoapi.common.IdType;
import com.ace.demoapi.common.MaritalStatus;
import com.ace.demoapi.common.Name;
import com.ace.demoapi.common.PermanentAddress;
import com.ace.demoapi.common.ProductGroupType;
import com.ace.demoapi.common.ResidentAddress;
import com.ace.demoapi.common.UserRecorder;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String codeNo;
	private String liscenseNo;
	@Temporal(TemporalType.DATE)
	private Date liscenseExpiredDate;
	private String initialId;
	private String idNo;
	private String fatherName;
	private String birthMark;
	private String accountNo;
	private String remark;
	private String training;
	private String outstandingEvent;
	private String fullIdNo;
	@Embedded
	private Name name;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private MaritalStatus maritalStatus;

	@Temporal(TemporalType.DATE)
	private Date appointedDate;

	@JsonBackReference
	@ElementCollection
	@CollectionTable(name = "AGENT_FAMILY_LINK", joinColumns = @JoinColumn(name = "AGENTID", referencedColumnName = "ID"))
	private List<FamilyInfo> familyInfo;

	@OneToOne
	@JoinColumn(name = "QUALIFICATIONID", referencedColumnName = "ID")
	private Qualification qualification;

	@OneToOne
	@JoinColumn(name = "BANKBRANCHID", referencedColumnName = "ID")
	private BankBranch bankBranch;

	@OneToOne
	@JoinColumn(name = "RELIGIONID", referencedColumnName = "ID")
	private Religion religion;

	@OneToOne
	@JoinColumn(name = "NATIONALITYID", referencedColumnName = "ID")
	private Country country;

	@OneToOne
	@JoinColumn(name = "ORGANIZATIONID", referencedColumnName = "ID")
	private Organization organization;

	@Embedded
	private PermanentAddress permanentAddress;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "agent", orphanRemoval = true)
	private AgentAttachment attachment;

	@Embedded
	private ResidentAddress residentAddress;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@Column(name = "GROUPTYPE")
	@Enumerated(value = EnumType.STRING)
	private ProductGroupType groupType;

	@Embedded
	private ContentInfo contentInfo;

	@OneToOne
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	@Transient
	private String stateCode;
	@Transient
	private String townshipCode;
	@Transient
	private String idConditionType;

	public void loadTransientIdNo() {
		if (idType != null && idType.equals(IdType.NRCNO) && fullIdNo != null) {
			String[] NRC = new String[4];
			StringTokenizer st = new StringTokenizer(fullIdNo, "/()");
			int i = 0;
			while (st.hasMoreTokens()) {
				NRC[i] = st.nextToken();
				i++;
			}
			stateCode = NRC[0];
			townshipCode = NRC[1];
			idConditionType = NRC[2];
			idNo = NRC[3];
		} else if (idType != null && (idType.equals(IdType.FRCNO) || idType.equals(IdType.PASSPORTNO))) {
			idNo = fullIdNo;
		}
	}

	public String getFullAddress() {
		String result = "";
		if (residentAddress.getResidentAddress() != null && !residentAddress.getResidentAddress().isEmpty()) {
			result = result + residentAddress.getResidentAddress();
		}
		if (residentAddress.getTownship() != null && !residentAddress.getTownship().getFullTownShip().isEmpty()) {
			result = result + "," + residentAddress.getTownship().getFullTownShip();
		}
		return result;
	}

	/****** System Generated Method ****/
	public String getAccountNoForView() {
		if (accountNo == null || accountNo.isEmpty())
			return " - ";
		return accountNo;
	}
	
	public String getFullName() {
		String result = "";
		if (name != null) {
			if (initialId != null && !initialId.isEmpty()) {
				result = initialId;
			}
			if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
				result = result.trim() + " " + name.getFirstName();
			}
			if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
				result = result.trim() + " " + name.getMiddleName();
			}
			if (name.getLastName() != null && !name.getLastName().isEmpty()) {
				result = result.trim() + " " + name.getLastName();
			}
		}
		return result;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (appointedDate == null) {
			if (other.appointedDate != null)
				return false;
		} else if (!appointedDate.equals(other.appointedDate))
			return false;
		if (attachment == null) {
			if (other.attachment != null)
				return false;
		} else if (!attachment.equals(other.attachment))
			return false;
		if (bankBranch == null) {
			if (other.bankBranch != null)
				return false;
		} else if (!bankBranch.equals(other.bankBranch))
			return false;
		if (birthMark == null) {
			if (other.birthMark != null)
				return false;
		} else if (!birthMark.equals(other.birthMark))
			return false;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (codeNo == null) {
			if (other.codeNo != null)
				return false;
		} else if (!codeNo.equals(other.codeNo))
			return false;
		if (contentInfo == null) {
			if (other.contentInfo != null)
				return false;
		} else if (!contentInfo.equals(other.contentInfo))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (familyInfo == null) {
			if (other.familyInfo != null)
				return false;
		} else if (!familyInfo.equals(other.familyInfo))
			return false;
		if (fatherName == null) {
			if (other.fatherName != null)
				return false;
		} else if (!fatherName.equals(other.fatherName))
			return false;
		if (fullIdNo == null) {
			if (other.fullIdNo != null)
				return false;
		} else if (!fullIdNo.equals(other.fullIdNo))
			return false;
		if (gender != other.gender)
			return false;
		if (groupType != other.groupType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idConditionType == null) {
			if (other.idConditionType != null)
				return false;
		} else if (!idConditionType.equals(other.idConditionType))
			return false;
		if (idNo == null) {
			if (other.idNo != null)
				return false;
		} else if (!idNo.equals(other.idNo))
			return false;
		if (idType != other.idType)
			return false;
		if (initialId == null) {
			if (other.initialId != null)
				return false;
		} else if (!initialId.equals(other.initialId))
			return false;
		if (liscenseExpiredDate == null) {
			if (other.liscenseExpiredDate != null)
				return false;
		} else if (!liscenseExpiredDate.equals(other.liscenseExpiredDate))
			return false;
		if (liscenseNo == null) {
			if (other.liscenseNo != null)
				return false;
		} else if (!liscenseNo.equals(other.liscenseNo))
			return false;
		if (maritalStatus != other.maritalStatus)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (organization == null) {
			if (other.organization != null)
				return false;
		} else if (!organization.equals(other.organization))
			return false;
		if (outstandingEvent == null) {
			if (other.outstandingEvent != null)
				return false;
		} else if (!outstandingEvent.equals(other.outstandingEvent))
			return false;
		if (permanentAddress == null) {
			if (other.permanentAddress != null)
				return false;
		} else if (!permanentAddress.equals(other.permanentAddress))
			return false;
		if (qualification == null) {
			if (other.qualification != null)
				return false;
		} else if (!qualification.equals(other.qualification))
			return false;
		if (recorder == null) {
			if (other.recorder != null)
				return false;
		} else if (!recorder.equals(other.recorder))
			return false;
		if (religion == null) {
			if (other.religion != null)
				return false;
		} else if (!religion.equals(other.religion))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (residentAddress == null) {
			if (other.residentAddress != null)
				return false;
		} else if (!residentAddress.equals(other.residentAddress))
			return false;
		if (stateCode == null) {
			if (other.stateCode != null)
				return false;
		} else if (!stateCode.equals(other.stateCode))
			return false;
		if (townshipCode == null) {
			if (other.townshipCode != null)
				return false;
		} else if (!townshipCode.equals(other.townshipCode))
			return false;
		if (training == null) {
			if (other.training != null)
				return false;
		} else if (!training.equals(other.training))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((appointedDate == null) ? 0 : appointedDate.hashCode());
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result + ((birthMark == null) ? 0 : birthMark.hashCode());
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((codeNo == null) ? 0 : codeNo.hashCode());
		result = prime * result + ((contentInfo == null) ? 0 : contentInfo.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((familyInfo == null) ? 0 : familyInfo.hashCode());
		result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
		result = prime * result + ((fullIdNo == null) ? 0 : fullIdNo.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((groupType == null) ? 0 : groupType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idConditionType == null) ? 0 : idConditionType.hashCode());
		result = prime * result + ((idNo == null) ? 0 : idNo.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
		result = prime * result + ((initialId == null) ? 0 : initialId.hashCode());
		result = prime * result + ((liscenseExpiredDate == null) ? 0 : liscenseExpiredDate.hashCode());
		result = prime * result + ((liscenseNo == null) ? 0 : liscenseNo.hashCode());
		result = prime * result + ((maritalStatus == null) ? 0 : maritalStatus.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organization == null) ? 0 : organization.hashCode());
		result = prime * result + ((outstandingEvent == null) ? 0 : outstandingEvent.hashCode());
		result = prime * result + ((permanentAddress == null) ? 0 : permanentAddress.hashCode());
		result = prime * result + ((qualification == null) ? 0 : qualification.hashCode());
		result = prime * result + ((recorder == null) ? 0 : recorder.hashCode());
		result = prime * result + ((religion == null) ? 0 : religion.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((residentAddress == null) ? 0 : residentAddress.hashCode());
		result = prime * result + ((stateCode == null) ? 0 : stateCode.hashCode());
		result = prime * result + ((townshipCode == null) ? 0 : townshipCode.hashCode());
		result = prime * result + ((training == null) ? 0 : training.hashCode());
		result = prime * result + version;
		return result;
	}
	
	

}