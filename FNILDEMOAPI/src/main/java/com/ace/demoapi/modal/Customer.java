package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
import com.ace.demoapi.common.PassportType;
import com.ace.demoapi.common.PermanentAddress;
import com.ace.demoapi.common.ResidentAddress;
import com.ace.demoapi.common.UserRecorder;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String initialId;
	private String fatherName;
	@Transient
	private String stateCode;
	@Transient
	private String townshipCode;
	@Transient
	private String idConditionType;
	@Transient
	private String idNo;
	private String fullIdNo;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	@Temporal(TemporalType.TIMESTAMP)
	private Date activedDate;
	private String labourNo;
	private String birthMark;
	private String salary;
	private int closedPolicy;
	private int activePolicy;
	private String bankAccountNo;

	private double height;
	private double weight;
	private String placeOfBirth;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@Enumerated(value = EnumType.STRING)
	private MaritalStatus maritalStatus;

	@Enumerated(value = EnumType.STRING)
	private PassportType passportType;

	@Embedded
	private OfficeAddress officeAddress;

	@Embedded
	private PermanentAddress permanentAddress;

	@Embedded
	private ResidentAddress residentAddress;

	@Embedded
	private ContentInfo contentInfo;

	@Embedded
	private Name name;

	@ElementCollection
	@CollectionTable(name = "CUSTOMER_FAMILY_LINK", joinColumns = @JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID"))
	private List<FamilyInfo> familyInfo;

	@OneToOne
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

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
	@JoinColumn(name = "OCCUPATIONID", referencedColumnName = "ID")
	private Occupation occupation;

	@OneToOne
	@JoinColumn(name = "INDURSTRYID", referencedColumnName = "ID")
	private Industry industry;

	@OneToOne
	@JoinColumn(name = "NATIONALITYID", referencedColumnName = "ID")
	private Country country;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
	private List<CustomerInfoStatus> customerStatusList;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	public Customer(String id) {
		this.id = id;
	}

}
