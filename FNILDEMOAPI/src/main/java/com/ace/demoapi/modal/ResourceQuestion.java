package com.ace.demoapi.modal;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.ace.demoapi.common.UserRecorder;

import lombok.Data;

@Data
@Entity
public class ResourceQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RESOURCE_QUESTION_GEN")
	private String id;
	private String name;
	@Version
	private int version;
	@Embedded
	private UserRecorder recorder;

	

}
