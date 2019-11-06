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
public class RiskyOccupation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RISKYOCCUPATION_GEN")
	private String id;
	private String name;
	private String mName;
	private String description;	
	private int extraRate;
	
	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	
	
	
	
}
