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
public class PolicyInsuredPersonAttachmentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLINSUREDPERSONATTACHMENTHIS_GEN")
	private String id;

	private String name;
	private String filePath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPOLICYINSUREDPERSONID", referencedColumnName = "ID")
	private PolicyInsuredPersonHistory policyInsuredPerson;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public PolicyInsuredPersonAttachmentHistory() {
	}

	public PolicyInsuredPersonAttachmentHistory(InsuredPersonAttachment attachment) {
		this.name = attachment.getName();
		this.filePath = attachment.getFilePath();
	}

	public PolicyInsuredPersonAttachmentHistory(PolicyInsuredPersonAttachment attachment) {
		this.name = attachment.getName();
		this.filePath = attachment.getFilePath();
	}

	public PolicyInsuredPersonAttachmentHistory(String name, String filePath) {
		this.name = name;
		this.filePath = filePath;
	}

}