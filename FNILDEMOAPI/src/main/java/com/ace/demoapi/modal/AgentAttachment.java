package com.ace.demoapi.modal;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.ace.demoapi.common.TableName;
import com.ace.demoapi.common.UserRecorder;

import lombok.Data;


@Data
@Entity
@Table(name = TableName.AGENT_ATTACH_LINK)
public class AgentAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPRL_GEN")
	private String id;

	private String name;
	private String filePath;

	@OneToOne
	@JoinColumn(name = "AGENTID", referencedColumnName = "ID")
	private Agent agent;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	

}
