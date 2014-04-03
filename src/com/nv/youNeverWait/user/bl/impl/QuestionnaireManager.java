/**
 * QuestionnaireManager.java
 */
package com.nv.youNeverWait.user.bl.impl;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.NetmdQuestionAnswerDTO;
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
	public ResponseDTO update(QuestionAnswerDTO questionAnswer,HeaderDTO header) {
		ResponseDTO response= questionnaireDao.update(questionAnswer,header);
		return response;
	}
	@Override
	public ResponseDTO NetmdQuestionnaire(NetmdQuestionAnswerDTO questionAnswerDTO,HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		response = questionnaireDao.NetmdQuestionnaire(questionAnswerDTO);
		
		if(response.getId()!=0){
			NetmdQuestionAnswerDTO questionAnswer=new NetmdQuestionAnswerDTO();
			 questionAnswer.setQuestionnaireId(response.getId());
			 questionAnswer.setAnswerDTO(questionAnswerDTO.getAnswerDTO());
			response= questionnaireDao.createNetmdQuestionnaire(questionAnswer,header);
		}
		response.setSuccess(true);
		return response;
	}
	
	@Override
	public ResponseDTO updateQuestionnaire(NetmdQuestionAnswerDTO questionAnswer,HeaderDTO header) {
		ResponseDTO response= questionnaireDao.updateQuestionnaire(questionAnswer,header);
		return response;
	}
	@Override
	public ResponseDTO deleteQuestionnaire(int id,HeaderDTO header) {
		ResponseDTO response=new ResponseDTO();
		response=questionnaireDao.deleteQuestionnaire(id,header);
		return response;
	}

	@Override
	public ResponseDTO delete(int id, HeaderDTO header) {
		
		ResponseDTO response= questionnaireDao.delete(id,header);
		return response;
	}



}
