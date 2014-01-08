/**
 * AnswerDTO.java
 * @author netvarth
 *
 * Version 1.0 Dec 4, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.rs.dto;

/**
 *
 *
 * @author Luciya Jose
 */
public class AnswerDTO {
 private String questionKey;
 private String answer;
/**
 * @return the questionKey
 */
public String getQuestionKey() {
	return questionKey;
}
/**
 * @param questionKey the questionKey to set
 */
public void setQuestionKey(String questionKey) {
	this.questionKey = questionKey;
}
/**
 * @return the answer
 */
public String getAnswer() {
	return answer;
}
/**
 * @param answer the answer to set
 */
public void setAnswer(String answer) {
	this.answer = answer;
}
 
}
