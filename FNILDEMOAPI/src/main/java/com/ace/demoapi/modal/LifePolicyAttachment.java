package com.ace.demoapi.modal;

import java.io.Serializable;

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
public class LifePolicyAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LPOLATT_GEN")
	private String id;

	private String name;
	private String filePath;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIFEPOLICYID", referencedColumnName = "ID")
	private LifePolicy lifePolicy;
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public LifePolicyAttachment() {

	}

	public LifePolicyAttachment(LifeProposalAttachment attachment) {
		this.name = attachment.getName();
		this.filePath = attachment.getFilePath();
	}

	public LifePolicyAttachment(LifePolicyAttachmentHistory attachment) {
		this.name = attachment.getName();
		this.filePath = attachment.getFilePath();
	}

	public LifePolicyAttachment(String name, String filePath) {
		this.name = name;
		this.filePath = filePath;
	}

}
