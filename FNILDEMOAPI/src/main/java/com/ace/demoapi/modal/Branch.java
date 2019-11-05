package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Branch implements Serializable {
	private static final long serialVersionUID = 1680499663032866031L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BRANCH_GEN")
	private String id;
	private String name;
	private String preFix;
	private String branchCode;
	private String address;
	private boolean isCoInsuAccess;
	private String description;

	@OneToOne
	@JoinColumn(name = "TOWNSHIPID", referencedColumnName = "ID")
	private Township township;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "branch", orphanRemoval = true)
	private List<SalesPoints> salesPointsList;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;
	
	
	
	

}
