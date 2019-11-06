package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.ace.demoapi.common.ClaimStatus;
import com.ace.demoapi.common.ClassificationOfHealth;
import com.ace.demoapi.common.EndorsementStatus;
import com.ace.demoapi.common.Gender;
import com.ace.demoapi.common.IInsuredItem;
import com.ace.demoapi.common.IdType;
import com.ace.demoapi.common.Name;
import com.ace.demoapi.common.ResidentAddress;
import com.ace.demoapi.common.UserRecorder;
import com.ace.demoapi.common.Utils;
import com.ace.demoapi.common.dto.BeneficiariesInfoDTO;
import com.ace.demoapi.common.dto.InsuredPersonAddOnDTO;
import com.ace.demoapi.common.dto.InsuredPersonInfoDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;



@Data
@Entity
public class PolicyInsuredPerson implements IInsuredItem, Serializable {
	private static final long serialVersionUID = -1810680158208016018L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSURPERSON_GEN")
	private String id;

	@Column(name = "AGE")
	private int age;
	private double sumInsured;
	private double basicTermPremium;
	private double premium;
	private double addOnTermPremium;
	private double endorsementNetBasicPremium;;
	private double endorsementNetAddonPremium;
	private double interest;
	@Column(name = "INPERSONCODENO")
	private String insPersonCodeNo;
	@Column(name = "INPERSONGROUPCODENO")
	private String inPersonGroupCodeNo;
	private String initialId;
	private String idNo;
	private String fatherName;
	private int unit;
	private int weight;
	private int height;
	private String phone;
	private double premiumRate;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Enumerated(value = EnumType.STRING)
	private IdType idType;

	@Enumerated(EnumType.STRING)
	private EndorsementStatus endorsementStatus;

	@Enumerated(EnumType.STRING)
	private ClaimStatus claimStatus;

	@Enumerated(value = EnumType.STRING)
	private ClassificationOfHealth clsOfHealth;

	@Embedded
	private ResidentAddress residentAddress;

	@Embedded
	private Name name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTID", referencedColumnName = "ID")
	private Product product;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OCCUPATIONID", referencedColumnName = "ID")
	private Occupation occupation;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RISKYOCCUPATIONID", referencedColumnName = "ID")
	private RiskyOccupation riskyOccupation;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID")
	private Customer customer;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPESOFSPORTID", referencedColumnName = "ID")
	private TypesOfSport typesOfSport;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RELATIONSHIPID", referencedColumnName = "ID")
	private RelationShip relationship;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPOLICYID", referencedColumnName = "ID")
	private LifePolicy lifePolicy;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policyInsuredPerson", orphanRemoval = true)
	private List<PolicyInsuredPersonAttachment> attachmentList;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policyInsuredPerson", orphanRemoval = true)
	private List<PolicyInsuredPersonAddon> policyInsuredPersonAddOnList;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "REFERENCEID", referencedColumnName = "ID")
	private List<PolicyInsuredPersonKeyFactorValue> policyInsuredPersonkeyFactorValueList;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policyInsuredPerson", orphanRemoval = true)
	private List<PolicyInsuredPersonBeneficiaries> policyInsuredPersonBeneficiariesList;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public PolicyInsuredPerson() {
	}

	public PolicyInsuredPerson(ProposalInsuredPerson insuredPerson) {
		this.dateOfBirth = insuredPerson.getDateOfBirth();
		this.clsOfHealth = insuredPerson.getClsOfHealth();
		this.sumInsured = insuredPerson.getProposedSumInsured();
		this.product = insuredPerson.getProduct();
		this.premium = insuredPerson.getProposedPremium();
		this.basicTermPremium = insuredPerson.getBasicTermPremium();
		this.addOnTermPremium = insuredPerson.getAddOnTermPremium();
		this.endorsementNetBasicPremium = insuredPerson.getEndorsementNetBasicPremium();
		this.endorsementNetAddonPremium = insuredPerson.getEndorsementNetAddonPremium();
		this.interest = insuredPerson.getInterest();
		this.weight = insuredPerson.getWeight();
		this.height = insuredPerson.getHeight();
		this.premiumRate = insuredPerson.getPremiumRate();
		this.insPersonCodeNo = insuredPerson.getInsPersonCodeNo();
		this.endorsementStatus = insuredPerson.getEndorsementStatus();
		this.initialId = insuredPerson.getInitialId();
		this.idNo = insuredPerson.getIdNo();
		this.idType = insuredPerson.getIdType();
		this.name = insuredPerson.getName();
		this.gender = insuredPerson.getGender();
		this.residentAddress = insuredPerson.getResidentAddress();
		this.occupation = insuredPerson.getOccupation();
		this.fatherName = insuredPerson.getFatherName();
		this.customer = insuredPerson.getCustomer();
		this.age = insuredPerson.getAge();
		this.inPersonGroupCodeNo = insuredPerson.getInPersonGroupCodeNo();
		this.typesOfSport = insuredPerson.getTypesOfSport();
		this.unit = insuredPerson.getUnit();
		this.relationship = insuredPerson.getRelationship();
		this.riskyOccupation = insuredPerson.getRiskyOccupation();
		this.phone = insuredPerson.getPhone();
		for (InsuredPersonAttachment attachment : insuredPerson.getAttachmentList()) {
			addAttachment(new PolicyInsuredPersonAttachment(attachment));
		}
		for (InsuredPersonAddon addOn : insuredPerson.getInsuredPersonAddOnList()) {
			addInsuredPersonAddOn(new PolicyInsuredPersonAddon(addOn));
		}
		for (InsuredPersonKeyFactorValue keyFactorValue : insuredPerson.getKeyFactorValueList()) {
			addPolicyInsuredPersonKeyFactorValue(new PolicyInsuredPersonKeyFactorValue(keyFactorValue));
		}
		for (InsuredPersonBeneficiaries insuredPersonBeneficiaries : insuredPerson.getInsuredPersonBeneficiariesList()) {
			addInsuredPersonBeneficiaries(new PolicyInsuredPersonBeneficiaries(insuredPersonBeneficiaries));
		}
	}

	public PolicyInsuredPerson(PolicyInsuredPersonHistory history) {
		this.age = history.getAge();
		this.sumInsured = history.getSumInsured();
		this.premium = history.getPremium();
		this.basicTermPremium = history.getBasicTermPremium();
		this.addOnTermPremium = history.getAddOnTermPremium();
		this.endorsementNetBasicPremium = history.getEndorsementNetBasicPremium();
		this.endorsementNetAddonPremium = history.getEndorsementNetAddonPremium();
		this.interest = history.getInterest();
		this.weight = history.getWeight();
		this.height = history.getHeight();
		this.premiumRate = history.getPremiumRate();
		this.insPersonCodeNo = history.getInPersonCodeNo();
		this.inPersonGroupCodeNo = history.getInPersonGroupCodeNo();
		this.initialId = history.getInitialId();
		this.idNo = history.getIdNo();
		this.fatherName = history.getFatherName();
		this.dateOfBirth = history.getDateOfBirth();
		this.gender = history.getGender();
		this.idType = history.getIdType();
		this.endorsementStatus = history.getEndorsementStatus();
		this.claimStatus = history.getClaimStatus();
		this.clsOfHealth = history.getClsOfHealth();
		this.residentAddress = history.getResidentAddress();
		this.name = history.getName();
		this.product = history.getProduct();
		this.occupation = history.getOccupation();
		this.customer = history.getCustomer();
		this.typesOfSport = history.getTypesOfSport();
		this.relationship = history.getRelationship();
		this.riskyOccupation = history.getRiskyOccupation();
		this.unit = history.getUnit();
		// this.phone = history.getph
		for (PolicyInsuredPersonAttachmentHistory attachment : history.getAttachmentList()) {
			addAttachment(new PolicyInsuredPersonAttachment(attachment));
		}

		for (PolicyInsuredPersonKeyFactorValueHistory keyFactorValue : history.getPolicyInsuredPersonkeyFactorValueList()) {
			addPolicyInsuredPersonKeyFactorValue(new PolicyInsuredPersonKeyFactorValue(keyFactorValue));
		}

		for (PolicyInsuredPersonBeneficiariesHistory insuredPersonBeneficiaries : history.getPolicyInsuredPersonBeneficiariesList()) {
			addInsuredPersonBeneficiaries(new PolicyInsuredPersonBeneficiaries(insuredPersonBeneficiaries));
		}

		for (PolicyInsuredPersonAddonHistory addOn : history.getPolicyInsuredPersonAddOnList()) {
			addInsuredPersonAddOn(new PolicyInsuredPersonAddon(addOn));
		}

	}

	public PolicyInsuredPerson(InsuredPersonInfoDTO dto) {
		this.age = dto.getAge();
		this.sumInsured = dto.getSumInsuredInfo();
		this.premium = dto.getPremium();
		this.basicTermPremium = dto.getBasicTermPremium();
		this.addOnTermPremium = dto.getAddOnTermPremium();
		this.endorsementNetBasicPremium = dto.getEndorsementBasicPremium();
		this.endorsementNetAddonPremium = dto.getEndorsementAddonPremium();
		this.interest = dto.getInterest();
		this.insPersonCodeNo = dto.getInsPersonCodeNo();
		this.inPersonGroupCodeNo = dto.getInPersonGroupCodeNo();
		this.initialId = dto.getInitialId();
		this.idNo = dto.getIdNo();
		this.fatherName = dto.getFatherName();
		this.dateOfBirth = dto.getDateOfBirth();
		this.gender = dto.getGender();
		this.idType = dto.getIdType();
		this.endorsementStatus = dto.getEndorsementStatus();
		this.claimStatus = dto.getClaimStatus();
		this.clsOfHealth = dto.getClassificationOfHealth();
		this.residentAddress = dto.getResidentAddress();
		this.name = dto.getName();
		this.product = dto.getProduct();
		this.occupation = dto.getOccupation();
		this.customer = dto.getCustomer();
		this.typesOfSport = dto.getTypesOfSport();
		this.relationship = dto.getRelationship();
		this.riskyOccupation = dto.getRiskyOccupation();

		for (PolicyInsuredPersonAttachment attach : dto.getPrePolicyAttachmentList()) {
			addAttachment(attach);
		}
		for (PolicyInsuredPersonKeyFactorValue kfv : dto.getPolicyKeyFactorValueList()) {
			addPolicyInsuredPersonKeyFactorValue(kfv);
		}
		for (BeneficiariesInfoDTO beneficiary : dto.getBeneficiariesInfoDTOList()) {
			addInsuredPersonBeneficiaries(new PolicyInsuredPersonBeneficiaries(beneficiary));
		}
		for (InsuredPersonAddOnDTO addOn : dto.getInsuredPersonAddOnDTOMap().values()) {
			addInsuredPersonAddOn(new PolicyInsuredPersonAddon(addOn));
		}
		if (dto.isExistsEntity()) {
			this.id = dto.getTempId();
			this.version = dto.getVersion();
		}
	}

	public PolicyInsuredPerson(Date dateOfBirth, double sumInsured, Product product, LifePolicy lifePolicy, int periodMonth, Date startDate, Date endDate, double premium,
			double endorsementNetBasicPremium, double endorsementNetAddonPremium, double interest, String insPersonCodeNo, EndorsementStatus endorsementStatus,
			String inPersonGroupCodeNo) {
		this.endorsementStatus = endorsementStatus;
		this.dateOfBirth = dateOfBirth;
		this.sumInsured = sumInsured;
		this.product = product;
		this.lifePolicy = lifePolicy;
		this.premium = premium;
		this.endorsementNetBasicPremium = endorsementNetAddonPremium;
		this.endorsementNetAddonPremium = endorsementNetAddonPremium;
		this.insPersonCodeNo = insPersonCodeNo;
		this.interest = interest;
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	}

	public PolicyInsuredPerson(Date dateOfBirth, double sumInsured, Product product, LifePolicy lifePolicy, int periodMonth, Date startDate, Date endDate, double premium,
			String initialId, String idNo, IdType idType, Name name, Gender gender, ResidentAddress residentAddress, Occupation occupation, String fatherName,
			double endorsementNetBasicPremium, double endorsementNetAddonPremium, double interest, EndorsementStatus status, String insPersonCodeNo, Customer customer, int age,
			String inPersonGroupCodeNo, int paymentTerm, double basicTermPremium, double addOnTermPremium, ClaimStatus claimStatus) {
		this.dateOfBirth = dateOfBirth;
		this.sumInsured = sumInsured;
		this.product = product;
		this.lifePolicy = lifePolicy;
		this.premium = premium;
		this.initialId = initialId;
		this.idNo = idNo;
		this.idType = idType;
		this.name = name;
		this.residentAddress = residentAddress;
		this.gender = gender;
		this.occupation = occupation;
		this.fatherName = fatherName;
		this.endorsementNetBasicPremium = endorsementNetBasicPremium;
		this.endorsementNetAddonPremium = endorsementNetAddonPremium;
		this.interest = interest;
		this.endorsementStatus = status;
		this.insPersonCodeNo = insPersonCodeNo;
		this.customer = customer;
		this.age = age;
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
		this.basicTermPremium = basicTermPremium;
		this.addOnTermPremium = addOnTermPremium;
		this.claimStatus = claimStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void overwriteId(String id) {
		this.id = id;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(double sumInsured) {
		this.sumInsured = sumInsured;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public double getBasicTermPremium() {
		return basicTermPremium;
	}

	public void setBasicTermPremium(double basicTermPremium) {
		this.basicTermPremium = basicTermPremium;
	}

	public double getAddOnTermPremium() {
		return addOnTermPremium;
	}

	public void setAddOnTermPremium(double addOnTermPremium) {
		this.addOnTermPremium = addOnTermPremium;
	}

	public EndorsementStatus getEndorsementStatus() {
		return endorsementStatus;
	}

	public void setEndorsementStatus(EndorsementStatus endorsementStatus) {
		this.endorsementStatus = endorsementStatus;
	}

	public double getEndorsementNetBasicPremium() {
		return endorsementNetBasicPremium;
	}

	public void setEndorsementNetBasicPremium(double endorsementNetBasicPremium) {
		this.endorsementNetBasicPremium = endorsementNetBasicPremium;
	}

	public double getEndorsementNetAddonPremium() {
		return endorsementNetAddonPremium;
	}

	public void setEndorsementNetAddonPremium(double endorsementNetAddonPremium) {
		this.endorsementNetAddonPremium = endorsementNetAddonPremium;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public String getInsPersonCodeNo() {
		return insPersonCodeNo;
	}

	public void setInsPersonCodeNo(String insPersonCodeNo) {
		this.insPersonCodeNo = insPersonCodeNo;
	}

	public String getInPersonGroupCodeNo() {
		return inPersonGroupCodeNo;
	}

	public void setInPersonGroupCodeNo(String inPersonGroupCodeNo) {
		this.inPersonGroupCodeNo = inPersonGroupCodeNo;
	}

	public List<PolicyInsuredPersonAttachment> getAttachmentList() {
		if (this.attachmentList == null) {
			this.attachmentList = new ArrayList<PolicyInsuredPersonAttachment>();
		}
		return this.attachmentList;
	}

	public void setAttachmentList(List<PolicyInsuredPersonAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LifePolicy getLifePolicy() {
		return lifePolicy;
	}

	public void setLifePolicy(LifePolicy lifePolicy) {
		this.lifePolicy = lifePolicy;
	}

	public List<PolicyInsuredPersonAddon> getPolicyInsuredPersonAddOnList() {
		if (policyInsuredPersonAddOnList == null) {
			policyInsuredPersonAddOnList = new ArrayList<PolicyInsuredPersonAddon>();
		}
		return policyInsuredPersonAddOnList;
	}

	public void setPolicyInsuredPersonAddOnList(List<PolicyInsuredPersonAddon> policyInsuredPersonAddOnList) {
		this.policyInsuredPersonAddOnList = policyInsuredPersonAddOnList;
	}

	public List<PolicyInsuredPersonKeyFactorValue> getPolicyInsuredPersonkeyFactorValueList() {
		if (policyInsuredPersonkeyFactorValueList == null) {
			policyInsuredPersonkeyFactorValueList = new ArrayList<PolicyInsuredPersonKeyFactorValue>();
		}
		return policyInsuredPersonkeyFactorValueList;
	}

	public void setPolicyInsuredPersonkeyFactorValueList(List<PolicyInsuredPersonKeyFactorValue> policyInsuredPersonkeyFactorValueList) {
		this.policyInsuredPersonkeyFactorValueList = policyInsuredPersonkeyFactorValueList;
	}

	public String getKeyFactorValueListForDetails() {
		StringBuffer buffer = new StringBuffer();
		if (getPolicyInsuredPersonkeyFactorValueList().size() > 0) {
			String id = getPolicyInsuredPersonkeyFactorValueList().get(getPolicyInsuredPersonkeyFactorValueList().size() - 1).getKeyFactor().getId();
			for (PolicyInsuredPersonKeyFactorValue keyfactorValue : getPolicyInsuredPersonkeyFactorValueList()) {
				buffer.append(keyfactorValue.getKeyFactor().getValue()).append(" - ").append(keyfactorValue.getValue())
						.append(id == keyfactorValue.getKeyFactor().getId() ? "" : " , ");
			}
		}
		return buffer.toString();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public double getAddOnPremium() {
		double premium = 0.0;
		if (policyInsuredPersonAddOnList != null && !policyInsuredPersonAddOnList.isEmpty()) {
			for (PolicyInsuredPersonAddon pia : policyInsuredPersonAddOnList) {
				premium = Utils.getTwoDecimalPoint(premium + pia.getPremium());
			}
		}
		return premium;
	}

	public double getTotalPermium() {
		return Utils.getTwoDecimalPoint(premium + getAddOnPremium());
	}

	public double getTotalTermPermium() {
		return Utils.getTwoDecimalPoint(getBasicTermPremium() + getAddOnTermPremium());
	}

	public double getAddOnSumInsure() {
		double premium = 0.0;
		if (policyInsuredPersonAddOnList != null && !policyInsuredPersonAddOnList.isEmpty()) {
			for (PolicyInsuredPersonAddon pia : policyInsuredPersonAddOnList) {
				premium = premium + pia.getSumInsured();
			}
		}
		return premium;
	}

	public double getSuminsuredPerUnit() {
		double suminsuredPerUnit = 0.0;
		suminsuredPerUnit = unit * product.getSumInsuredPerUnit();
		return suminsuredPerUnit;
	}

	public List<PolicyInsuredPersonBeneficiaries> getPolicyInsuredPersonBeneficiariesList() {
		if (this.policyInsuredPersonBeneficiariesList == null) {
			this.policyInsuredPersonBeneficiariesList = new ArrayList<PolicyInsuredPersonBeneficiaries>();
		}
		return this.policyInsuredPersonBeneficiariesList;
	}

	public void setPolicyInsuredPersonBeneficiariesList(List<PolicyInsuredPersonBeneficiaries> policyInsuredPersonBeneficiariesList) {
		this.policyInsuredPersonBeneficiariesList = policyInsuredPersonBeneficiariesList;
	}

	public void addAttachment(PolicyInsuredPersonAttachment attachment) {
		attachment.setPolicyInsuredPerson(this);
		getAttachmentList().add(attachment);
	}

	public void addInsuredPersonAddOn(PolicyInsuredPersonAddon policyInsuredPersonAddOn) {
		policyInsuredPersonAddOn.setPolicyInsuredPersonInfo(this);
		getPolicyInsuredPersonAddOnList().add(policyInsuredPersonAddOn);
	}

	public void addPolicyInsuredPersonKeyFactorValue(PolicyInsuredPersonKeyFactorValue keyFactorValue) {
		// keyFactorValue.setPolicyInsuredPersonInfo(this);
		getPolicyInsuredPersonkeyFactorValueList().add(keyFactorValue);
	}

	public void addInsuredPersonBeneficiaries(PolicyInsuredPersonBeneficiaries policyInsuredPersonBeneficiaries) {
		policyInsuredPersonBeneficiaries.setPolicyInsuredPerson(this);
		getPolicyInsuredPersonBeneficiariesList().add(policyInsuredPersonBeneficiaries);
	}

	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}

	public ClassificationOfHealth getClsOfHealth() {
		return clsOfHealth;
	}

	public void setClsOfHealth(ClassificationOfHealth clsOfHealth) {
		this.clsOfHealth = clsOfHealth;
	}

	public void setPaymentTimes(int paymentTimes) {
		// do nothing
	}

	public int getAgeForNextYear() {
		Calendar cal_1 = Calendar.getInstance();
		int currentYear = cal_1.get(Calendar.YEAR);
		Calendar cal_2 = Calendar.getInstance();
		cal_2.setTime(dateOfBirth);
		cal_2.set(Calendar.YEAR, currentYear);

		if (new Date().after(cal_2.getTime())) {
			Calendar cal_3 = Calendar.getInstance();
			cal_3.setTime(dateOfBirth);
			int year_1 = cal_3.get(Calendar.YEAR);
			int year_2 = cal_1.get(Calendar.YEAR) + 1;
			return year_2 - year_1;
		} else {
			Calendar cal_3 = Calendar.getInstance();
			cal_3.setTime(dateOfBirth);
			int year_1 = cal_3.get(Calendar.YEAR);
			int year_2 = cal_1.get(Calendar.YEAR);
			return year_2 - year_1;
		}
	}

	/**
	 * @see org.ace.insurance.common.interfaces.IPolicy#getTotalPremium()
	 */
	public double getTotalPremium() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getInitialId() {
		return initialId;
	}

	public void setInitialId(String initialId) {
		this.initialId = initialId;
	}

	public String getIdNo() {
		return idNo;
	}

	public String getIdNoForView() {
		if (idNo == null || idNo.isEmpty()) {
			return "Still Applying";
		}
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public IdType getIdType() {
		return idType;
	}

	public void setIdType(IdType idType) {
		this.idType = idType;
	}

	public ResidentAddress getResidentAddress() {
		return residentAddress;
	}

	public void setResidentAddress(ResidentAddress residentAddress) {
		this.residentAddress = residentAddress;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getFullName() {
		String result = "";
		if (name != null) {
			if (initialId != null && !initialId.isEmpty()) {
				result = initialId.trim();
			}
			if (name.getFirstName() != null && !name.getFirstName().isEmpty()) {
				result = result + " " + name.getFirstName().trim();
			}
			if (name.getMiddleName() != null && !name.getMiddleName().isEmpty()) {
				result = result + " " + name.getMiddleName().trim();
			}
			if (name.getLastName() != null && !name.getLastName().isEmpty()) {
				result = result + " " + name.getLastName().trim();
			}
		}
		return result;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public String getOccupationName() {
		if (occupation != null)
			return occupation.getName();
		else
			return "-";
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TypesOfSport getTypesOfSport() {
		return typesOfSport;
	}

	public String getTypesOfSportName() {
		if (typesOfSport != null)
			return typesOfSport.getName();
		else
			return "";
	}

	public void setTypesOfSport(TypesOfSport typesOfSport) {
		this.typesOfSport = typesOfSport;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(double premiumRate) {
		this.premiumRate = premiumRate;
	}

	public RelationShip getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationShip relationship) {
		this.relationship = relationship;
	}

	public RiskyOccupation getRiskyOccupation() {
		return riskyOccupation;
	}

	public void setRiskyOccupation(RiskyOccupation riskyOccupation) {
		this.riskyOccupation = riskyOccupation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneForView() {
		if (phone != null && !phone.isEmpty())
			return phone;
		else
			return "-";
	}

	

}
