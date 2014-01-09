/**
 * QuestionnaireDao.java
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.QuestionAnswerDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Luciya Jose
 * Jan 07,2013
 *
 */
public interface QuestionnaireDao {

	ResponseDTO create(QuestionAnswerDTO questionAnswer,HeaderDTO header);
	ResponseDTO update(QuestionAnswerDTO questionAnswer);

}
