/**
 * QuestionAnswerDTO.java
 * @author Nithesh Mohanan
 *
 * Version 1.0 Feb 25, 2014
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */

package com.nv.youNeverWait.rs.dto;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nv.youNeverWait.common.Constants;



/**
 *
 *
 * @author Nithesh Mohanan
 */
public class NetmdQuestionAnswerDTO {
	private int questionnaireId;
	private String createdDate;
	private List<AnswerDTO> answerDTO=new ArrayList<AnswerDTO>();
	private ErrorDTO error;
	private boolean success;

	/**
	 * 
	 */
	public NetmdQuestionAnswerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param questionnaireId
	 * @param createdDate
	 * @param answerDTO
	 */
	public NetmdQuestionAnswerDTO(int questionnaireId, Date createdDate) {
		super();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		String createdOn = df.format(createdDate);
		this.questionnaireId = questionnaireId;
		this.createdDate = createdOn;
	}

	/**
	 * @return the answerDTO
	 */
	public List<AnswerDTO> getAnswerDTO() {
		return answerDTO;
	}

	/**
	 * @param answerDTO the answerDTO to set
	 */
	public void setAnswerDTO(List<AnswerDTO> answerDTO) {
		this.answerDTO = answerDTO;
	}

	public ErrorDTO getError() {
		return error;
	}

	public void setError(ErrorDTO error) {
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the questionnaireId
	 */
	public int getQuestionnaireId() {
		return questionnaireId;
	}

	/**
	 * @param questionnaireId the questionnaireId to set
	 */
	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
