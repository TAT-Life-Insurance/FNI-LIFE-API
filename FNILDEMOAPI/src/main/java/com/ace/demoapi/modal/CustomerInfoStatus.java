package com.ace.demoapi.modal;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.ace.demoapi.common.CustomerStatus;
import com.ace.demoapi.common.UserRecorder;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class CustomerInfoStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CUSTOMERSTATUS_GEN")
	private String id;
	@Enumerated(EnumType.STRING)
	private CustomerStatus statusName;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "CUSTOMERID", referencedColumnName = "ID")
	private Customer customer;
	@Version
	private int version;

	@Embedded
	private UserRecorder recorder;

}
