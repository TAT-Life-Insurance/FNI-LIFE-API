package com.ace.demoapi.modal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.ace.demoapi.common.TableName;
import com.ace.demoapi.common.UserRecorder;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;


@Data
@Entity
@Table(name=TableName.INSUREDPERSONATTACHMENT)
public class InsuredPersonAttachment {

	@Id
	private String id;

	private String name;
	private String filePath;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPROPOSALINSUREDPERSONID", referencedColumnName = "ID")
	private ProposalInsuredPerson proposalInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	
	public InsuredPersonAttachment(PolicyInsuredPersonAttachment attachment) {
		this.name = attachment.getName();
		this.filePath = attachment.getFilePath();
	}
}