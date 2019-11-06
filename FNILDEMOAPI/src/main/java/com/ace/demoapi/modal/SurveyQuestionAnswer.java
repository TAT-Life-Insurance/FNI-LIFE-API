package com.ace.demoapi.modal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.ace.demoapi.common.ClaimType;
import com.ace.demoapi.common.InputType;
import com.ace.demoapi.common.SurveyType;
import com.ace.demoapi.common.UserRecorder;
import com.ace.demoapi.common.ValidationUtil;
import com.ace.demoapi.common.dto.ResourceQuestionDTO;
import com.ace.demoapi.common.dto.SurveyQuestionDTO;

import lombok.Data;

@Data
@Entity
public class SurveyQuestionAnswer {

	@Transient
	private ResourceQuestionAnswer selectResQuesAns;
	@Transient
	private List<ResourceQuestionAnswer> selectResQuesAnsList;
	@Transient
	private boolean tureLabelValue;
	@Transient
	private Date answerDate;
	@Transient
	private String answer;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SURVEYQUESTIONANSWER_GEN")
	private String id;
	private String questionId;
	private String description;
	private String frontLabel;
	private String behindLabel;
	private String tureLabel;
	private String falseLabel;

	@Column(name = "REQUIRED")
	private boolean option;
	private boolean deleteFlag;
	private int priority;

	@Enumerated(EnumType.STRING)
	private InputType inputType;

	@OneToOne
	@JoinColumn(name = "PRODUCTPROCESSID")
	private ProductProcess productProcess;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "surveyQuestionAnswer", orphanRemoval = true)
	private List<ResourceQuestionAnswer> resourceQuestionList;

	@Enumerated(EnumType.STRING)
	private SurveyType surveyType;

	@Enumerated(EnumType.STRING)
	private ClaimType claimType;

	@Embedded
	private UserRecorder recorder;
	@Version
	private int version;

	public SurveyQuestionAnswer() {
		super();
	}

	public SurveyQuestionAnswer(ProductProcessQuestionLink ppQLink) {
		this.option = ppQLink.isOption();
		this.priority = ppQLink.getPriority();
		this.deleteFlag = ppQLink.getSurveyQuestion().isDeleteFlag();
		this.description = ppQLink.getSurveyQuestion().getDescription();
		this.frontLabel = ppQLink.getSurveyQuestion().getFrontLabel();
		this.behindLabel = ppQLink.getSurveyQuestion().getBehindLabel();
		this.tureLabel = ppQLink.getSurveyQuestion().getTureLabel();
		this.falseLabel = ppQLink.getSurveyQuestion().getFalseLabel();
		this.inputType = ppQLink.getSurveyQuestion().getInputType();
		this.productProcess = ppQLink.getProductProcess();
		if (inputType.equals(InputType.TEXT) || inputType.equals(InputType.NUMBER) || inputType.equals(InputType.DATE)) {
			ResourceQuestionAnswer answer = new ResourceQuestionAnswer();
			addResourceQuestionList(answer);
		}
		if (inputType.equals(InputType.BOOLEAN)) {
			ResourceQuestionAnswer answerTrue = new ResourceQuestionAnswer();
			answerTrue.setName(ppQLink.getSurveyQuestion().getTureLabel());
			answerTrue.setValue(0);
			ResourceQuestionAnswer answerFalse = new ResourceQuestionAnswer();
			answerFalse.setName(ppQLink.getSurveyQuestion().getFalseLabel());
			answerFalse.setValue(1);
			addResourceQuestionList(answerTrue);
			addResourceQuestionList(answerFalse);
		}
		for (ResourceQuestion resourceQuestion : ppQLink.getSurveyQuestion().getResourceQuestionList()) {
			ResourceQuestionAnswer rqa = new ResourceQuestionAnswer(resourceQuestion.getName());
			addResourceQuestionList(rqa);
		}
	}

	public SurveyQuestionAnswer(SurveyQuestionDTO surveyQuestionDTO) {
		this.deleteFlag = surveyQuestionDTO.isDeleteFlag();
		this.description = surveyQuestionDTO.getDescription();
		this.frontLabel = surveyQuestionDTO.getFrontLabel();
		this.behindLabel = surveyQuestionDTO.getBehindLabel();
		this.tureLabel = surveyQuestionDTO.getTureLabel();
		this.falseLabel = surveyQuestionDTO.getFalseLabel();
		this.inputType = surveyQuestionDTO.getInputType();

		if (inputType.equals(InputType.TEXT) || inputType.equals(InputType.NUMBER) || inputType.equals(InputType.DATE)) {
			ResourceQuestionAnswer answer = new ResourceQuestionAnswer();
			addResourceQuestionList(answer);
		}
		if (inputType.equals(InputType.BOOLEAN)) {
			ResourceQuestionAnswer answerTrue = new ResourceQuestionAnswer();
			ResourceQuestionAnswer answerFalse = new ResourceQuestionAnswer();
			addResourceQuestionList(answerTrue);
			addResourceQuestionList(answerFalse);
		}
		for (ResourceQuestionDTO resourceQuestion : surveyQuestionDTO.getResourceQuestionList()) {
			ResourceQuestionAnswer rqa = new ResourceQuestionAnswer(resourceQuestion.getName());
			addResourceQuestionList(rqa);
		}
	}



	public List<ResourceQuestionAnswer> getResourceQuestionList() {
		if (resourceQuestionList == null) {
			resourceQuestionList = new ArrayList<ResourceQuestionAnswer>();
		}
		return resourceQuestionList;
	}

	public void setResourceQuestionList(List<ResourceQuestionAnswer> resourceQuestionList) {
		this.resourceQuestionList = resourceQuestionList;
	}

	public void addResourceQuestionList(ResourceQuestionAnswer resourceAnswer) {
		resourceAnswer.setSurveyQuestionAnswer(this);
		getResourceQuestionList().add(resourceAnswer);
	}

	

	public String getAnswer() {
		StringBuffer sb = new StringBuffer();
		if (InputType.TEXT.equals(this.inputType) || InputType.NUMBER.equals(this.inputType) || InputType.DATE.equals(this.inputType)) {
			if (getResourceQuestionList() != null && !getResourceQuestionList().isEmpty()) {
				this.answer = getResourceQuestionList().get(0).getResult();
			}
		} else {
			for (ResourceQuestionAnswer resQA : getResourceQuestionList()) {
				if (resQA.getValue() == 1) {
					if (sb.length() > 0) {
						sb.append(",");
					}
					sb.append(resQA.getName());
				}
			}
			this.answer = sb.toString();
		}

		if (ValidationUtil.isStringEmpty(this.answer)) {
			if (InputType.NONE.equals(this.inputType)) {
				this.answer = "";
			} else if (InputType.TEXT.equals(this.inputType)) {
				this.answer = "\" \"";
			} else if (InputType.NUMBER.equals(this.inputType)) {
				this.answer = "0";
			} else if (InputType.DATE.equals(this.inputType)) {
				this.answer = "No Answer";
			} else {
				this.answer = "No Answer";
			}
		}
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	

}
