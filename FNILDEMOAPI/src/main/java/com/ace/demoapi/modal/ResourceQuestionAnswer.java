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
public class ResourceQuestionAnswer {
	private int value;
	private String result;
	@Version
	private int version;
	private String name;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RESOURCEQUESTIONANSWER_GEN")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SURVEYQUESTIONANSWERID", referencedColumnName = "ID")
	private SurveyQuestionAnswer surveyQuestionAnswer;

	@Embedded
	private UserRecorder recorder;
	
	public ResourceQuestionAnswer() {
		
	}
	
	public ResourceQuestionAnswer(String name) {
		this.name=name;
	}



}
