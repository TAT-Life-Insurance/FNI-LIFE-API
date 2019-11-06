package com.ace.demoapi.modal;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;


import com.ace.demoapi.common.KeyFactorType;
import com.ace.demoapi.common.UserRecorder;

import lombok.Data;


@Data
@Entity
public class KeyFactor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;

	private String value;
	@Enumerated(value = EnumType.STRING)
	private KeyFactorType keyFactorType;
	@Version
	private int version;
	@Embedded
	private UserRecorder recorder;

	public KeyFactor() {
	}

	public KeyFactor(KeyFactor keyFactor) {
		this.id = keyFactor.getId();
		this.value = keyFactor.getValue();
		this.keyFactorType = keyFactor.getKeyFactorType();
		this.recorder = keyFactor.getRecorder();
		this.version = keyFactor.getVersion();
	}

}