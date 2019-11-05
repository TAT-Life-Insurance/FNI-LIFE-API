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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

import lombok.Data;

@Data
@Entity
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AGENT_GEN")
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

}