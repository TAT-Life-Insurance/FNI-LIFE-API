package com.ace.demoapi.modal;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.ace.demoapi.common.CustomerStatus;
import com.ace.demoapi.common.UserRecorder;

public class CustomerInfoStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CUSTOMERSTATUS_GEN")
	private String id;
	@Enumerated(EnumType.STRING)
	private CustomerStatus statusName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID")
	private Customer customer;
	@Version
	private int version;

	@Embedded
	private UserRecorder recorder;

	public CustomerInfoStatus() {
	}

	public CustomerInfoStatus(String id, CustomerStatus statusName, Customer customer, int version) {
		this.id = id;
		this.statusName = statusName;
		this.customer = customer;
		this.version = version;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public CustomerStatus getStatusName() {
		return statusName;
	}

	public void setStatusName(CustomerStatus statusName) {
		this.statusName = statusName;
	}

	public UserRecorder getRecorder() {
		return recorder;
	}

	public void setRecorder(UserRecorder recorder) {
		this.recorder = recorder;
	}

}
