package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ace.demoapi.common.IDataModel;
import com.ace.demoapi.common.IProposal;
import com.ace.demoapi.common.KeyFactorChecker;
import com.ace.demoapi.common.ProposalStatus;
import com.ace.demoapi.common.ProposalType;
import com.ace.demoapi.common.SaleChannelType;
import com.ace.demoapi.common.UserRecorder;
import com.ace.demoapi.common.Utils;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class LifeProposal implements Serializable, IDataModel, IProposal {
	private static final long serialVersionUID = 7564214263861012292L;

	private boolean complete;
	private boolean isNonFinancialEndorse;

	@Column(name = "PERIODOFMONTH")
	private int periodMonth;
	private int paymentTerm;

	private double specialDiscount;
	private double currencyRate;

	@Id
	private String id;

	private String proposalNo;
	private String portalId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submittedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Enumerated(EnumType.STRING)
	private ProposalType proposalType;

	@Enumerated(EnumType.STRING)
	private SaleChannelType saleChannelType;

	@Enumerated(EnumType.STRING)
	private ProposalStatus proposalStatus;

	@OneToOne
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	@OneToOne
	@JoinColumn(name = "SALESPOINTSID", referencedColumnName = "ID")
	private SalesPoints salesPoints;

	@OneToOne
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID")
	private Customer customer;

	
	@OneToOne
	@JoinColumn(name = "ORGANIZATIONID", referencedColumnName = "ID")
	private Organization organization;

	@OneToOne
	@JoinColumn(name = "PAYMENTTYPEID", referencedColumnName = "ID")
	private PaymentType paymentType;

	
	@OneToOne
	@JoinColumn(name = "AGENTID", referencedColumnName = "ID")
	private Agent agent;

	@OneToOne
	@JoinColumn(name = "SALEBANKID", referencedColumnName = "ID")
	private BankBranch saleBank;

	@OneToOne
	@JoinColumn(name = "LIFEPOLICYID", referencedColumnName = "ID")
	private LifePolicy lifePolicy;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lifeProposal", orphanRemoval = true)
	private List<ProposalInsuredPerson> proposalInsuredPersonList;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lifeProposal", orphanRemoval = true)
	private List<LifeProposalAttachment> attachmentList;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public LifeProposal() {
	}

	public LifeProposal(LifePolicy lifePolicy) {
		this.agent = lifePolicy.getAgent();
		this.branch = lifePolicy.getBranch();
		this.salesPoints = lifePolicy.getSalesPoints();
		this.customer = lifePolicy.getCustomer();
		this.saleBank = lifePolicy.getSaleBank();
		this.saleChannelType = lifePolicy.getSaleChannelType();
		this.paymentType = lifePolicy.getPaymentType();
		this.organization = lifePolicy.getOrganization();
		this.specialDiscount = lifePolicy.getSpecialDiscount();
		this.submittedDate = lifePolicy.getCommenmanceDate();
		this.startDate = lifePolicy.getActivedPolicyStartDate();
		this.endDate = lifePolicy.getActivedPolicyEndDate();
		this.periodMonth = lifePolicy.getPeriodMonth();
		this.paymentTerm = lifePolicy.getLastPaymentTerm();
		this.currencyRate = lifePolicy.getCurrencyRate();
		this.lifePolicy = lifePolicy;

		for (PolicyInsuredPerson iPerson : lifePolicy.getInsuredPersonInfo()) {
			addInsuredPerson(new ProposalInsuredPerson(iPerson));
		}
		for (LifePolicyAttachment attachment : lifePolicy.getAttachmentList()) {
			addAttachment(new LifeProposalAttachment(attachment));
		}
	}

	

	public int getPeriodOfInsurance() {
		if (KeyFactorChecker.isPersonalAccident(proposalInsuredPersonList.get(0).getProduct())) {
			return periodMonth;
		} else {
			return periodMonth / 12;
		}
	}

	public String getPeriod() {
		if (KeyFactorChecker.isPersonalAccident(proposalInsuredPersonList.get(0).getProduct())) {
			return periodMonth + " - Months";
		} else {
			return periodMonth / 12 + " - Year";
		}
	}

	public int getPeriodOfYears() {
		return periodMonth / 12;
	}

	

	public List<ProposalInsuredPerson> getProposalInsuredPersonList() {
		if (this.proposalInsuredPersonList == null) {
			this.proposalInsuredPersonList = new ArrayList<ProposalInsuredPerson>();
		}
		return this.proposalInsuredPersonList;
	}

	public void setInsuredPersonList(List<ProposalInsuredPerson> proposalInsuredPersonList) {
		this.proposalInsuredPersonList = proposalInsuredPersonList;
	}

	public List<LifeProposalAttachment> getAttachmentList() {
		if (this.attachmentList == null) {
			this.attachmentList = new ArrayList<LifeProposalAttachment>();
		}
		return attachmentList;
	}

	public void setAttachmentList(List<LifeProposalAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public void addAttachment(LifeProposalAttachment attachment) {
		if (attachmentList == null) {
			attachmentList = new ArrayList<LifeProposalAttachment>();
		}
		attachment.setLifeProposal(this);
		attachmentList.add(attachment);
	}

	public void addInsuredPerson(ProposalInsuredPerson proposalInsuredPerson) {
		if (this.proposalInsuredPersonList == null) {
			proposalInsuredPersonList = new ArrayList<ProposalInsuredPerson>();
		}
		proposalInsuredPerson.setLifeProposal(this);
		this.proposalInsuredPersonList.add(proposalInsuredPerson);
	}

	

	public void setProposalInsuredPersonList(List<ProposalInsuredPerson> proposalInsuredPersonList) {
		this.proposalInsuredPersonList = proposalInsuredPersonList;
	}

	

	public String getSalePersonName() {
		if (agent != null) {
			return agent.getFullName();
		} else if (saleBank != null) {
			return saleBank.getName();
		}
		return null;
	}

	public ProposalStatus getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(ProposalStatus proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	/* System Auto Calculate Process */
	public double getPremium() {
		double premium = 0.0;
		for (ProposalInsuredPerson pv : proposalInsuredPersonList) {
			if (pv.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				premium = Utils.getTwoDecimalPoint(premium + (pv.getProposedPremium() + pv.getAddOnPremium()));
			}
		}
		return premium;
	}

	public double getAddOnPremium() {
		double premium = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				premium = Utils.getTwoDecimalPoint(premium + pi.getAddOnPremium());
			}
		}
		return premium;
	}

	public double getProposedAddOnPremium() {
		double premium = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				if (pi.isApproved()) {
					premium = Utils.getTwoDecimalPoint(premium + pi.getAddOnPremium());
				}
			}
		}
		return premium;
	}

	public double getTotalBasicTermPremium() {
		double termPermium = 0.0;
		for (ProposalInsuredPerson pv : proposalInsuredPersonList) {
			if (pv.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				termPermium = Utils.getTwoDecimalPoint(termPermium + pv.getBasicTermPremium());
			}
		}
		return termPermium;
	}

	public double getTotalAddOnTermPremium() {
		double termPermium = 0.0;
		for (ProposalInsuredPerson pv : proposalInsuredPersonList) {
			if (pv.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				termPermium = Utils.getTwoDecimalPoint(termPermium + pv.getAddOnTermPremium());
			}
		}
		return termPermium;
	}

	public double getStampFees() {
		double stampFees = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			stampFees = stampFees + pi.getProposedSumInsured() * 0.003;
		}
		return stampFees;
	}

	public double getSumInsured() {
		double sumInsured = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				sumInsured = sumInsured + pi.getProposedSumInsured();
			}
		}
		return sumInsured;
	}

	public double getTotalCalculateSumInsured() {
		double sumInsured = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				sumInsured = sumInsured + pi.getCalculateSumInsured();
			}
		}
		return sumInsured;
	}

	public int getUnits() {
		int unit = 0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getUnit() > 0) {
				unit = unit + pi.getUnit();
			}
		}
		return unit;
	}

	public double getTotalUntis() {
		double unit = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getUnit() > 0) {
				unit = unit + pi.getUnit();
			}
		}
		return unit;
	}

	public double getSuminsuredPerUnit() {
		double siPerUnit = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getUnit() > 0) {
				siPerUnit = siPerUnit + pi.getSuminsuredPerUnit();
			}
		}
		return siPerUnit;
	}

	public double getProposedPremium() {
		double proposedPremium = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getProposedPremium() > 0) {
				proposedPremium = proposedPremium + pi.getProposedPremium();
			}
		}
		return proposedPremium;
	}
	
	
	public SaleChannelType getSaleChannelType() {
		return saleChannelType;
	}

	public double getApprovedSumInsured() {
		double sumInsured = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				if (pi.isApproved()) {
					sumInsured = sumInsured + pi.getProposedSumInsured();
				}
			}
		}
		return sumInsured;
	}

	public double getTotalAmount() {
		return Utils.getTwoDecimalPoint(getTotalBasicTermPremium() + getTotalAddOnTermPremium() + getStampFees());
	}

	public double getAddOnSumInsured() {
		double sumInsured = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				sumInsured = sumInsured + pi.getAddOnSumInsured();
			}
		}
		return sumInsured;
	}

	public double getApprovedAddOnSumInsured() {
		double sumInsured = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementStatus() != EndorsementStatus.TERMINATE) {
				if (pi.isApproved()) {
					sumInsured = sumInsured + pi.getAddOnSumInsured();
				}
			}
		}
		return sumInsured;
	}

	public double getTotalPremium() {
		return Utils.getTwoDecimalPoint(getPremium() + getAddOnPremium());
	}

	public double getTotalSumInsured() {
		return getSumInsured() + getAddOnSumInsured();
	}

	public double getApprovedTotalSumInsured() {
		return getApprovedSumInsured() + getApprovedAddOnSumInsured();
	}

	public double getTotalTermPremium() {
		return Utils.getTwoDecimalPoint(getTotalBasicTermPremium() + getTotalAddOnTermPremium());
	}

	public double getPATotoalPremium() {
		double result = 0.0;
		if (!proposalInsuredPersonList.isEmpty()) {
			result = proposalInsuredPersonList.get(0).getProposedPremium() + getPAAddonPremium();
		}
		return result;
	}

	public double getPAAddonPremium() {
		double result = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (!pi.getInsuredPersonAddOnList().isEmpty()) {
				result = pi.getInsuredPersonAddOnList().get(0).getProposedPremium();
			}
		}
		return result;
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

	public String getPhoneNo() {
		if (customer != null) {
			return customer.getContentInfo().getPhone();
		}
		if (organization != null) {
			return organization.getContentInfo().getPhone();
		}
		return null;
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

	public String getCustomerAddress() {
		if (customer != null) {
			return customer.getFullAddress();
		}
		if (organization != null) {
			return organization.getFullAddress();
		}
		return null;
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

	public double getEndorsementNetPremium() {
		double amount = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.getEndorsementNetBasicPremium() > 0) {
				amount = Utils.getTwoDecimalPoint(amount + pi.getEndorsementNetBasicPremium());
			}
		}
		return amount;
	}

	public double getEndorsementAddOnPremium() {
		double amount = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.isApproved()) {
				amount = Utils.getTwoDecimalPoint(amount + pi.getEndorsementNetAddonPremium());
			}
		}
		return amount;
	}

	public double getTotalEndorsementNetPremium() {
		return Utils.getTwoDecimalPoint(getEndorsementNetPremium() + getEndorsementAddOnPremium());
	}

	public double getTotalInterest() {
		double interest = 0.0;
		for (ProposalInsuredPerson pi : proposalInsuredPersonList) {
			if (pi.isApproved()) {
				interest = interest + pi.getInterest();
			}
		}
		return interest;
	}

	public LifePolicy getLifePolicy() {
		return lifePolicy;
	}

	public void setLifePolicy(LifePolicy lifePolicy) {
		this.lifePolicy = lifePolicy;
	}

	public ProposalType getProposalType() {
		return proposalType;
	}

	public void setProposalType(ProposalType proposalType) {
		this.proposalType = proposalType;
	}

	

	public double getSpecialDiscountAmount() {
		double specialDiscountAmount = Utils.getPercentOf(specialDiscount, getPremium());
		return Utils.getTwoDecimalPoint(specialDiscountAmount);
	}

	

	

	@Override
	public String getUserType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Currency getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getTotalDiscountAmount() {
		double specialDiscountAmount = Utils.getPercentOf(specialDiscount, getProposedPremium());
		return Utils.getTwoDecimalPoint(specialDiscountAmount);
	}

	public double getNetPremium() {
		return getTotalPremium() - getTotalDiscountAmount();
	}

	public double getTotalTermDiscountAmount() {
		double specialDiscountAmount = Utils.getPercentOf(specialDiscount, getTotalTermPremium());
		return Utils.getTwoDecimalPoint(specialDiscountAmount);
	}

	public double getNetTermPremium() {
		return getTotalTermPremium() - getTotalTermDiscountAmount();
	}

	public String getSalePointName() {
		if (salesPoints != null)
			return salesPoints.getName();
		else
			return "";
	}

	public String getPremiumRateStr() {
		String premiumRateStr = "";
		Map<String, String> rateMap = new HashMap<String, String>();
		for (ProposalInsuredPerson p : getProposalInsuredPersonList()) {
			rateMap.put(p.getPremiumRate() + "", p.getPremiumRate() + "");
		}
		int count = 1;
		int size = rateMap.size();
		for (String rateStr : rateMap.values()) {
			premiumRateStr += rateStr;
			if (count++ != size)
				premiumRateStr += ", ";
		}
		return premiumRateStr;
	}

}