/**
 * QuestionnaireDao.java
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.NetmdQuestionAnswerDTO;
import com.nv.youNeverWait.rs.dto.QuestionAnswerDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Luciya Jose
 * Jan 07,2013
 *
 */
public interface QuestionnaireDao {

	ResponseDTO create(QuestionAnswerDTO questionAnswer,HeaderDTO header);
	ResponseDTO update(QuestionAnswerDTO questionAnswer,HeaderDTO header);
	ResponseDTO NetmdQuestionnaire(NetmdQuestionAnswerDTO questionnaire);
    ResponseDTO createNetmdQuestionnaire(NetmdQuestionAnswerDTO questionnaire,HeaderDTO header);
    ResponseDTO updateQuestionnaire(NetmdQuestionAnswerDTO questionAnswer,HeaderDTO header);
	ResponseDTO deleteQuestionnaire(int id,HeaderDTO header);
	ResponseDTO delete(int id, HeaderDTO header);
	

}
