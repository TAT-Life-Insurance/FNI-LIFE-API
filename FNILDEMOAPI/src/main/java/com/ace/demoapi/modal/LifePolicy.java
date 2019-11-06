package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.ace.demoapi.common.EndorsementStatus;
import com.ace.demoapi.common.IInsuredItem;
import com.ace.demoapi.common.IPolicy;
import com.ace.demoapi.common.ISorter;
import com.ace.demoapi.common.InsuranceType;
import com.ace.demoapi.common.KeyFactorChecker;
import com.ace.demoapi.common.PolicyStatus;
import com.ace.demoapi.common.ProposalType;
import com.ace.demoapi.common.SaleChannelType;
import com.ace.demoapi.common.UserRecorder;
import com.ace.demoapi.common.Utils;

import lombok.Data;


@Entity
@Data
public class LifePolicy implements IPolicy, Serializable, ISorter {
	private static final long serialVersionUID = 2379164707215020929L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LIFEPOLICY_GEN")
	private String id;

	private boolean delFlag;
	private boolean isCoinsuranceApplied;
	private boolean isEndorsementApplied;
	private boolean isNonFinancialEndorse;
	private int lastPaymentTerm;
	@Column(name = "PERIODOFMONTH")
	private int periodMonth;
	private int printCount;
	private double totalDiscountAmount;
	private double standardExcess;
	private double specialDiscount;
	private double currencyRate;
	private String policyNo;
	private String endorsementNo;

	/* Underwriting payment date */
	@Temporal(TemporalType.TIMESTAMP)
	private Date commenmanceDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVEDPOLICYSTARTDATE")
	private Date activedPolicyStartDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTIVEDPOLICYENDDATE")
	private Date activedPolicyEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COVERAGEDATE")
	private Date coverageDate;

	@Column(name = "ENDORSEMENTCONFIRMDATE")
	@Temporal(TemporalType.DATE)
	private Date endorsementConfirmDate;

	@Column(name = "RENEWALCONFIRMDATE")
	@Temporal(TemporalType.DATE)
	private Date renewalConfirmDate;

	@Enumerated(EnumType.STRING)
	private PolicyStatus policyStatus;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID")
	private Customer customer;

	@Enumerated(EnumType.STRING)
	private SaleChannelType saleChannelType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATIONID", referencedColumnName = "ID")
	private Organization organization;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPROVERID", referencedColumnName = "ID")
	private User approvedBy;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALESPOINTSID", referencedColumnName = "ID")
	private SalesPoints salesPoints;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENTTYPEID", referencedColumnName = "ID")
	private PaymentType paymentType;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENTID", referencedColumnName = "ID")
	private Agent agent;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALEBANKID", referencedColumnName = "ID")
	private BankBranch saleBank;

	@OneToOne
	@JoinColumn(name = "PROPOSALID")
	private LifeProposal lifeProposal;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lifePolicy", orphanRemoval = true)
	private List<PolicyInsuredPerson> policyInsuredPersonList;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lifePolicy", orphanRemoval = true)
	private List<LifePolicyAttachment> attachmentList;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public LifePolicy() {
	}

	public LifePolicy(LifeProposal lifeProposal) {
		this.customer = lifeProposal.getCustomer();
		this.organization = lifeProposal.getOrganization();
		this.branch = lifeProposal.getBranch();
		this.salesPoints = lifeProposal.getSalesPoints();
		this.paymentType = lifeProposal.getPaymentType();
		this.saleBank = lifeProposal.getSaleBank();
		this.saleChannelType = lifeProposal.getSaleChannelType();
		this.agent = lifeProposal.getAgent();
		this.lifeProposal = lifeProposal;
		this.periodMonth = lifeProposal.getPeriodMonth();
		this.lastPaymentTerm = lifeProposal.getPaymentTerm();
		this.isEndorsementApplied = false;
		this.isNonFinancialEndorse = lifeProposal.isNonFinancialEndorse();
		this.specialDiscount = lifeProposal.getSpecialDiscount();
		this.currencyRate = lifeProposal.getCurrencyRate();
		for (ProposalInsuredPerson person : lifeProposal.getProposalInsuredPersonList()) {
			addInsuredPerson(new PolicyInsuredPerson(person));
		}

		for (LifeProposalAttachment attach : lifeProposal.getAttachmentList()) {
			addLifePolicyAttachment(new LifePolicyAttachment(attach));
		}
	}

	public LifePolicy(LifePolicyHistory history) {
		this.id = history.getPolicyReferenceNo();
		this.isCoinsuranceApplied = history.isCoinsuranceApplied();
		this.isEndorsementApplied = history.isActiveEndorsement();
		this.isNonFinancialEndorse = history.isNonFinancialEndorse();
		this.lastPaymentTerm = history.getLastPaymentTerm();
		this.periodMonth = history.getPeriodMonth();
		this.printCount = history.getPrintCount();
		this.totalDiscountAmount = history.getTotalDiscountAmount();
		this.standardExcess = history.getStandardExcess();
		this.policyNo = history.getPolicyNo();
		this.endorsementNo = history.getEndorsementNo();
		this.commenmanceDate = history.getCommenmanceDate();
		this.activedPolicyStartDate = history.getActivedPolicyStartDate();
		this.activedPolicyEndDate = history.getActivedPolicyEndDate();
		this.coverageDate = history.getCoverageDate();
		this.endorsementConfirmDate = history.getEndorsementConfirmDate();
		this.policyStatus = history.getPolicyStatus();
		this.customer = history.getCustomer();
		this.organization = history.getOrganization();
		this.approvedBy = history.getApprovedBy();
		this.branch = history.getBranch();
		this.salesPoints = history.getSalesPoints();
		this.paymentType = history.getPaymentType();
		this.agent = history.getAgent();
		this.saleBank = history.getSaleBank();
		this.saleChannelType = history.getSaleChannelType();
		this.lifeProposal = history.getLifeProposal();
		this.currencyRate = history.getCurrencyRate();

		for (PolicyInsuredPersonHistory iPerson : history.getPolicyInsuredPersonList()) {
			addInsuredPerson(new PolicyInsuredPerson(iPerson));
		}

		for (LifePolicyAttachmentHistory attachment : history.getAttachmentList()) {
			addLifePolicyAttachment(new LifePolicyAttachment(attachment));
		}
	}

	public void addInsuredPerson(PolicyInsuredPerson policyInsuredPerson) {
		policyInsuredPerson.setLifePolicy(this);
		getPolicyInsuredPersonList().add(policyInsuredPerson);
	}

	

	public int getPeriodOfMonths() {
		if (KeyFactorChecker.isPersonalAccident(policyInsuredPersonList.get(0).getProduct())) {
			return periodMonth;
		} else {
			return periodMonth * 12;
		}
	}

	public int getPeriodOfInsurance() {
		if (KeyFactorChecker.isPersonalAccident(policyInsuredPersonList.get(0).getProduct())) {
			return periodMonth;
		} else {
			return periodMonth / 12;
		}
	}

	public int getPeriodOfYears() {
		return periodMonth / 12;
	}

	public List<Date> getTimeSlotList() {
		List<Date> result = new ArrayList<Date>();
		result.add(activedPolicyStartDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(activedPolicyStartDate);
		int months = paymentType.getMonth();
		if (months > 0) {
			int a = 12 / months;
			for (int i = 1; i < a; i++) {
				cal.add(Calendar.MONTH, months);
				result.add(cal.getTime());
			}
		}
		return result;
	}

	public Date getLastPaymentDate() {
		List<Date> dateList = getTimeSlotList();
		dateList.add(activedPolicyStartDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(activedPolicyStartDate);
		int months = paymentType.getMonth();
		if (months > 0) {
			int a = getPeriodMonth() / months;
			for (int i = 1; i < a; i++) {
				cal.add(Calendar.MONTH, months);
				dateList.add(cal.getTime());
			}
		}

		if (!dateList.isEmpty()) {
			int lastIndex = dateList.size() - 1;
			return dateList.get(lastIndex);
		}
		return null;
	}

	public int getPaymentTimes() {
		int monthTimes = paymentType.getMonth();
		if (monthTimes > 0) {
			return periodMonth / monthTimes;
		} else {
			return 1;
		}
	}

	

	public String getCustomerEmail() {
		if (customer != null) {
			return customer.getContentInfo().getEmail();
		}
		if (organization != null) {
			return organization.getContentInfo().getEmail();
		}
		return null;
	}

	public List<PolicyInsuredPerson> getInsuredPersonInfo() {
		if (policyInsuredPersonList == null) {
			policyInsuredPersonList = new ArrayList<PolicyInsuredPerson>();
		} else {
			List<PolicyInsuredPerson> tempList = new ArrayList<PolicyInsuredPerson>();
			tempList.addAll(policyInsuredPersonList);
			for (PolicyInsuredPerson policyInsuredPerson : policyInsuredPersonList) {
				if (EndorsementStatus.TERMINATE.equals(policyInsuredPerson.getEndorsementStatus())) {
					tempList.remove(policyInsuredPerson);
				}
			}
			policyInsuredPersonList.clear();
			policyInsuredPersonList.addAll(tempList);
		}
		return policyInsuredPersonList;
	}

	

	public double getSpecialDiscountAmount() {
		double specialDiscountAmount = Utils.getPercentOf(specialDiscount, getPremium());
		return Utils.getTwoDecimalPoint(specialDiscountAmount);
	}

	public List<PolicyInsuredPerson> getPolicyInsuredPersonList() {
		if (policyInsuredPersonList == null) {
			policyInsuredPersonList = new ArrayList<PolicyInsuredPerson>();
		}
		return policyInsuredPersonList;
	}

	public void setPolicyInsuredPersonList(List<PolicyInsuredPerson> policyInsuredPersonList) {
		this.policyInsuredPersonList = policyInsuredPersonList;
	}

	public void addPolicyInsuredPersonInfo(PolicyInsuredPerson policyInsuredPersonInfo) {
		if (policyInsuredPersonList == null) {
			policyInsuredPersonList = new ArrayList<PolicyInsuredPerson>();
		}
		policyInsuredPersonInfo.setLifePolicy(this);
		policyInsuredPersonList.add(policyInsuredPersonInfo);
	}

	public void addLifePolicyAttachment(LifePolicyAttachment attachment) {
		attachment.setLifePolicy(this);
		getAttachmentList().add(attachment);
	}

	public String getProposalNo() {
		return lifeProposal.getProposalNo();
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isCoinsuranceApplied() {
		return isCoinsuranceApplied;
	}

	public void setCoinsuranceApplied(boolean isCoinsuranceApplied) {
		this.isCoinsuranceApplied = isCoinsuranceApplied;
	}

	public boolean isEndorsementApplied() {
		return isEndorsementApplied;
	}

	public void setEndorsementApplied(boolean isEndorsementApplied) {
		this.isEndorsementApplied = isEndorsementApplied;
	}

	

	

	/* System Auto Calculate Process */
	public double getPremium() {
		double premium = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			if (!EndorsementStatus.TERMINATE.equals(pi.getEndorsementStatus())) {
				premium = Utils.getTwoDecimalPoint(premium + (pi.getPremium() + pi.getAddOnTermPremium()));
			}
		}
		return premium;
	}

	public double getAddOnPremium() {
		double premium = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			premium = Utils.getTwoDecimalPoint(premium + pi.getAddOnPremium());
		}
		return premium;
	}

	public double getSumInsured() {
		double sumInsured = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			sumInsured = sumInsured + pi.getSumInsured();
		}
		return sumInsured;
	}

	public double getSuminsuredPerUnit() {
		double siPerUnit = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			if (pi.getUnit() > 0) {
				siPerUnit = siPerUnit + pi.getSuminsuredPerUnit();
			}
		}
		return siPerUnit;
	}

	public double getAddOnSumInsured() {
		double sumInsured = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			sumInsured = sumInsured + pi.getAddOnSumInsure();
		}
		return sumInsured;
	}

	public double getTotalPremium() {
		return getPremium() + getAddOnPremium();
	}

	public double getNetPremium() {
		double netPremium = getTotalPremium() - getSpecialDiscountAmount();
		return Utils.getTwoDecimalPoint(netPremium);
	}

	public double getTotalSumInsured() {
		return getSumInsured() + getAddOnSumInsured();
	}

	public String getTotalSumInsuredString() {
		return Utils.getCurrencyFormatString(getSumInsured() + getAddOnSumInsured());
	}

	public double getTotalTermPremium() {
		return Utils.getTwoDecimalPoint(getTotalBasicTermPremium() + getTotalAddOnTermPremium());
	}

	public double getTotalBasicTermPremium() {
		double termPermium = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			termPermium = Utils.getTwoDecimalPoint(termPermium + pi.getBasicTermPremium());
		}
		return termPermium;
	}

	public String getTotalBasicTermPremiumString() {
		double termPermium = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			termPermium = Utils.getTwoDecimalPoint(termPermium + pi.getBasicTermPremium());
		}
		return Utils.getCurrencyFormatString(termPermium);
	}

	public double getTotalAddOnTermPremium() {
		double termPermium = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			termPermium = Utils.getTwoDecimalPoint(termPermium + pi.getAddOnTermPremium());
		}
		return termPermium;
	}

	public double getEndorsementBasicPremium() {
		double premium = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			premium = Utils.getTwoDecimalPoint(premium + pi.getEndorsementNetBasicPremium());
		}
		return premium;
	}

	public double getEndorsementAddOnPremium() {
		double premium = 0.0;
		for (PolicyInsuredPerson pi : policyInsuredPersonList) {
			premium = Utils.getTwoDecimalPoint(premium + pi.getEndorsementNetAddonPremium());
		}
		return premium;
	}

	public double getTotalEndorsementPremium() {
		return Utils.getTwoDecimalPoint(getEndorsementBasicPremium() + getEndorsementAddOnPremium());
	}

	public double getAgentCommission() {
		double totalCommission = 0.0;
		if (agent != null) {
			for (PolicyInsuredPerson pip : policyInsuredPersonList) {
				double commissionPercent = pip.getProduct().getFirstCommission();
				if (commissionPercent > 0) {
					double totalPremium = pip.getTotalTermPermium();
					double commission = Utils.getPercentOf(commissionPercent, totalPremium);
					totalCommission = totalCommission + commission;
				}
			}
		}
		return Utils.getTwoDecimalPoint(totalCommission);
	}

	public double getRenewalAgentCommission() {
		double totalCommission = 0.0;
		if (agent != null) {
			for (PolicyInsuredPerson pip : policyInsuredPersonList) {
				double commissionPercent = pip.getProduct().getRenewalCommission();
				if (commissionPercent > 0) {
					double totalPremium = pip.getTotalTermPermium();
					double commission = Utils.getPercentOf(commissionPercent, totalPremium);
					totalCommission = totalCommission + commission;
				}
			}
		}
		return Utils.getTwoDecimalPoint(totalCommission);
	}

	public String getCustomerId() {
		if (customer != null) {
			return customer.getId();
		}
		if (organization != null) {
			return organization.getId();
		}
		return null;
	}

	public String getCustomerName() {
		if (customer != null) {
			return customer.getFullName();
		}
		if (organization != null) {
			return organization.getName();
		}
		return null;
	}

	public String getCustomerNRC() {
		if (customer != null) {
			return customer.getFullIdNoForView();
		}
		return null;
	}

	public String getOrganizationName() {
		if (customer != null) {
			return customer.getFullName();
		}
		if (organization != null) {
			return organization.getName();
		}
		return null;
	}

	public String getOwnerName() {
		if (customer != null) {
			return customer.getFullName();
		}
		if (organization != null) {
			return organization.getOwnerNameForView();
		}
		return null;
	}

	public String getCustomerAddress() {
		if (customer != null) {
			return customer.getFullAddress();
		}
		if (organization != null) {
			return organization.getFullAddress();
		}
		return null;
	}

	public String getCustomerPhoneNo() {
		if (customer != null) {
			return customer.getContentInfo().getPhoneOrMoblieNo();
		}
		if (organization != null) {
			return organization.getContentInfo().getPhoneOrMoblieNo();
		}
		return null;
	}

	public String getAgentName() {
		if (agent != null)
			return agent.getFullName();
		else
			return "N/A";
	}

	public String getAgentLiscenseNo() {
		if (agent != null)
			return agent.getLiscenseNo();
		else
			return "[0]";
	}

	public String getAgentNameNLiscenseNo() {
		if (agent != null)
			return agent.getFullName() + " [" + agent.getLiscenseNo() + "]";
		else
			return "N/A[0]";
	}

	public ProductGroup getProductGroup() {
		if (this.policyInsuredPersonList != null && !this.policyInsuredPersonList.isEmpty()) {
			return this.policyInsuredPersonList.get(0).getProduct().getProductGroup();
		}
		return null;
	}

	/**
	 * @see org.ace.insurance.common.interfaces.IPolicy#getInsuredItems()
	 */
	public List<IInsuredItem> getInsuredItems() {
		List<IInsuredItem> insuredItems = Collections.emptyList();
		List<PolicyInsuredPerson> personList = getPolicyInsuredPersonList();
		if (personList != null) {
			insuredItems = new ArrayList<IInsuredItem>();
			for (PolicyInsuredPerson person : personList) {
				insuredItems.add(person);
			}
		}
		return insuredItems;
	}

	public String getSalePersonName() {
		if (agent != null) {
			return agent.getFullName();
		} else if (saleBank != null) {
			return saleBank.getName();
		}
		return null;
	}

	public InsuranceType getInsuranceType() {
		return InsuranceType.LIFE;
	}

	public String getSaleChannel() {
		if (agent != null) {
			return agent.getFullName();
		} else if (saleBank != null) {
			return saleBank.getName();
		} else if (saleChannelType.equals(SaleChannelType.WALKIN)) {
			return SaleChannelType.WALKIN.toString();
		} else if (saleChannelType.equals(SaleChannelType.DIRECTMARKETING)) {
			return SaleChannelType.DIRECTMARKETING.toString();
		}
		return null;
	}

	public double getTotalTermDiscountAmount() {
		double specialDiscountAmount = Utils.getPercentOf(specialDiscount, getTotalTermPremium());
		return Utils.getTwoDecimalPoint(specialDiscountAmount);
	}

	public double getNetTermPremium() {
		return getTotalTermPremium() - getTotalTermDiscountAmount();
	}

	@Override
	public String getRegistrationNo() {
		return policyNo;
	}

	public String agentNameWithLiscenceNo() {
		if (agent != null) {
			return agent.getFullName() + "( " + agent.getLiscenseNo() + " )";
		}
		return "";
	}

	

	@Override
	public ProposalType getProposalType() {
		return null;
	}

	@Override
	public int getTotalUnit() {
		return 0;
	}

	public String getPeriod() {
		if (periodMonth / 12 < 1) {
			return periodMonth + " - Months";
		} else {
			return periodMonth / 12 + " - Year";
		}
	}

	public int getTotalPaymentTimes() {
		if (paymentType.getMonth() == 0) {
			return 1;
		} else {
			int totalPaymentTimes = 0;
			int paymentMonths = 0;
			if (periodMonth > paymentMonths) {
				paymentMonths = periodMonth;
			}
			totalPaymentTimes = paymentMonths / paymentType.getMonth();
			return totalPaymentTimes;
		}
	}

	public String getTimeSlotListStr() {
		StringBuffer buffer = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(activedPolicyStartDate);
		int months = paymentType.getMonth();
		if (months == 1) {
			buffer.append("( ");
			buffer.append(Utils.formattedDate(cal.getTime(), "dd"));
			buffer.append(" )");
		} else if (months > 0 && months != 12) {
			buffer.append(Utils.formattedDate(cal.getTime(), "MMMM"));
			buffer.append(" ");
			int a = 12 / months;
			for (int i = 1; i < a; i++) {
				cal.add(Calendar.MONTH, months);
				buffer.append(Utils.formattedDate(cal.getTime(), "MMMM"));
				buffer.append(" ");
			}
			buffer.append("- ( ");
			buffer.append(Utils.formattedDate(cal.getTime(), "dd"));
			buffer.append(" )");
		} else if (months == 12) {
			buffer.append(Utils.formattedDate(cal.getTime(), "MMMM"));
			buffer.append(" ( ");
			buffer.append(Utils.formattedDate(cal.getTime(), "dd"));
			buffer.append(" )");
		}
		return buffer.toString();
	}

	public Date abstractDatyByPaymentType() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(activedPolicyEndDate);
		int paymentMonth = paymentType.getMonth();
		int modulusMont = paymentMonth % 12;
		if (modulusMont == 0) {
			int year = cal.get(Calendar.YEAR) - 1;
			cal.set(Calendar.YEAR, year);
		} else {
			int month = cal.get(Calendar.MONTH) - modulusMont;
			cal.set(Calendar.MONTH, month);
		}
		return cal.getTime();
	}
}
