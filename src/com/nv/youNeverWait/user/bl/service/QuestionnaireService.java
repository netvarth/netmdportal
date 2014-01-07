/**
 * QuestionnaireService.java
 */
package com.nv.youNeverWait.user.bl.service;

import com.nv.youNeverWait.rs.dto.QuestionAnswerDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Luciya Jose
 *
 */
public interface QuestionnaireService {

	ResponseDTO create(QuestionAnswerDTO questionAnswer);

}
