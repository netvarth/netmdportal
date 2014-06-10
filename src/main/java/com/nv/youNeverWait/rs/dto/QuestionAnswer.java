/**
 * QuestionAnswer.java
 * @author netvarth
 *
 * Version 1.0 Dec 2, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Luciya Jose
 */
public class QuestionAnswer {
	private int caseId;
	private int deptId;
	private List<AnswerDTO> answerList = new ArrayList<AnswerDTO>();
	/**
	 * @return the caseId
	 */
	public int getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the deptId
	 */
	public int getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the answerList
	 */
	public List<AnswerDTO> getAnswerList() {
		return answerList;
	}
	/**
	 * @param answerList the answerList to set
	 */
	public void setAnswerList(List<AnswerDTO> answerList) {
		this.answerList = answerList;
	}
	

}
