/**
 * QuestionnaireDaoImpl
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.AnswerTbl;
import com.nv.youNeverWait.pl.entity.CaseTbl;
import com.nv.youNeverWait.pl.entity.DepartmentTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.QuestionTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.AnswerDTO;
import com.nv.youNeverWait.rs.dto.QuestionAnswerDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.QuestionnaireDao;

/**
 * @author Luciya Jose
 * Jan 07, 2013
 *
 */
public class QuestionnaireDaoImpl extends GenericDaoHibernateImpl implements QuestionnaireDao  {


	@PersistenceContext()
	private EntityManager em;
	
	@Override
	@Transactional
	public ResponseDTO create(QuestionAnswerDTO questionAnswer) {
		ResponseDTO response = new ResponseDTO();
		CaseTbl caseTbl= getById(CaseTbl.class,questionAnswer.getCaseId());
		if(caseTbl==null){
			ServiceException se=new ServiceException(ErrorCodeEnum.InvalidCaseId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		DepartmentTbl departmentTbl= getById(DepartmentTbl.class,questionAnswer.getDepartmentId());
		if(departmentTbl== null){
			ServiceException se=new ServiceException(ErrorCodeEnum.InvalidDepartmentId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		List<QuestionTbl> questionList= getByCaseIdAndDeptId(questionAnswer.getDepartmentId());
		for(QuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
		}
		for(AnswerDTO answer: questionAnswer.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
			if(!ans.isEmpty() && !ans.contains("select")){
				AnswerTbl answerTbl= new AnswerTbl();
				QuestionTbl qtable =new QuestionTbl();
				qtable.setId(qMap.get(answer.getQuestionKey()));
				answerTbl.setDepartmentTbl(departmentTbl);
				answerTbl.setCaseTbl(caseTbl);
				answerTbl.setQuestionTbl(qtable);
				answerTbl.setAnswer(answer.getAnswer());
				save(answerTbl);
			}

		}
		response.setSuccess(true);
		response.setId(caseTbl.getId());
		return response;
	 
	}
	
	/**
	* @param deptId
	* @param caseId
	* @return
	*/
	private List<QuestionTbl> getByCaseIdAndDeptId(int deptId) {
		javax.persistence.Query query = em.createQuery(Query.GET_BY_DEPT);
		query.setParameter("param1", deptId);
		return executeQuery(QuestionTbl.class, query);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
