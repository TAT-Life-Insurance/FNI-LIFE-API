package com.ace.demoapi.modal;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.Version;

import com.ace.demoapi.common.TransactionType;
import com.ace.demoapi.common.UserRecorder;
import com.ace.demoapi.common.WorkFlowType;
import com.ace.demoapi.common.WorkflowTask;

import lombok.Data;


@Data
@Entity
public class Authority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AUTHORITY_GEN")
	private String id;

	
	
	@Column(name = "INSURANCETYPE")
	@Enumerated(EnumType.STRING)
	private WorkFlowType workFlowType;

	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@ElementCollection(targetClass = WorkflowTask.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "PERMISSION")
	@CollectionTable(name = "USER_PERMISSION", joinColumns = @JoinColumn(name = "AUTHORITYID", referencedColumnName = "ID"))
	private List<WorkflowTask> permissionList;

	@Embedded
	private UserRecorder recorder;

	@Version
	private int version;

	

}
