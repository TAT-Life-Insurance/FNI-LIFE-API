package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;
import com.ace.demoapi.common.WorkFlowType;
import com.ace.demoapi.common.WorkflowTask;

import lombok.Data;

@Data
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PASSWORD = "password";
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
	private String id;

	private String usercode;
	private String password;
	private String name;
	private boolean disabled;
	private double authority;

	@Temporal(TemporalType.TIMESTAMP)
	private Date disabledDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BRANCHID", referencedColumnName = "ID")
	private Branch branch;

	@Transient
	private Branch loginBranch;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_BRANCH", joinColumns = @JoinColumn(name = "USERID"), inverseJoinColumns = @JoinColumn(name = "BRANCHID"))
	private List<Branch> accessBranchList;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLEID", referencedColumnName = "ID")
	private Role role;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "USERID", referencedColumnName = "ID")
	private List<Authority> authorityList;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	public List<WorkflowTask> getPermissions(WorkFlowType workFlowType) {
		for (Authority auth : authorityList) {
			if (auth.getWorkFlowType().equals(workFlowType)) {
				return auth.getPermissionList();
			}
		}
		return null;
	}

	

}