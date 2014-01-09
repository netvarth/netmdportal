/**
 * QuestionnaireManager.java
 */
package com.nv.youNeverWait.user.bl.impl;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.QuestionAnswerDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.QuestionnaireService;
import com.nv.youNeverWait.user.pl.dao.QuestionnaireDao;

/**
 * @author Luciya Jose
 * Jan 07, 2013
 *
 */
public class QuestionnaireManager implements QuestionnaireService{

	private QuestionnaireDao questionnaireDao;
	@Override
	public ResponseDTO create(QuestionAnswerDTO questionAnswer,HeaderDTO header) {
		ResponseDTO response= questionnaireDao.create(questionAnswer, header);
		return response;
	}
	public QuestionnaireDao getQuestionnaireDao() {
		return questionnaireDao;
	}
	public void setQuestionnaireDao(QuestionnaireDao questionnaireDao) {
		this.questionnaireDao = questionnaireDao;
	}
	@Override
	public ResponseDTO update(QuestionAnswerDTO questionAnswer) {
		ResponseDTO response= questionnaireDao.update(questionAnswer);
		return response;
	}

}
