package com.ace.demoapi.modal;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;


import com.ace.demoapi.common.UserRecorder;

import lombok.Data;


@Data
@Entity
public class InsuredPersonAttachment {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "INSUREDPERSONATTACHMENT_GEN")
	private String id;

	private String name;
	private String filePath;

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