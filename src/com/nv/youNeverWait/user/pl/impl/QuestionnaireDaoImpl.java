/**
 * QuestionnaireDaoImpl
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.AnswerTbl;
import com.nv.youNeverWait.pl.entity.CaseTbl;
import com.nv.youNeverWait.pl.entity.DepartmentTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdAnswerTbl;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdQuestionTbl;
import com.nv.youNeverWait.pl.entity.NetmdQuestionnaireTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.QuestionTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.AnswerDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.NetmdQuestionAnswerDTO;
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
	public ResponseDTO create(QuestionAnswerDTO questionAnswer,HeaderDTO header) {
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
		
		NetmdBranchTbl netmdBranchTbl=  getById(NetmdBranchTbl.class,header.getBranchId());
		if(netmdBranchTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
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
				answerTbl.setNetmdBranchTbl(netmdBranchTbl);
				save(answerTbl);
			}

		}
		response.setSuccess(true);
		response.setId(caseTbl.getId());
		return response;
	 
	}
	
	@Override
	@Transactional
	public ResponseDTO update(QuestionAnswerDTO questionAnswer,HeaderDTO header) {
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
		NetmdBranchTbl netmdBranchTbl=  getById(NetmdBranchTbl.class,header.getBranchId());
		if(netmdBranchTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		List<AnswerTbl> answerlist=getAnswerList(questionAnswer.getCaseId());
		for(AnswerTbl answer:answerlist){
			delete(answer);
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
				answerTbl.setNetmdBranchTbl(netmdBranchTbl);
				answerTbl.setAnswer(answer.getAnswer());
				save(answerTbl);
			}

		}
		response.setSuccess(true);
		response.setId(caseTbl.getId());
		return response;
	}
	/**
	 * @param id
	 * @param id2
	 * @return
	 */
	public List<AnswerTbl> getAnswerList(int caseId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BY_CASE);
		query.setParameter("param1", caseId);
		return executeQuery(AnswerTbl.class, query);
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

	@Override
	@Transactional
	public ResponseDTO NetmdQuestionnaire(NetmdQuestionAnswerDTO questionnaire) {
		ResponseDTO response = new ResponseDTO();
		NetmdQuestionnaireTbl ntmdQstnnaireTbl=new NetmdQuestionnaireTbl();
		Date currentTime=new Date();
        ntmdQstnnaireTbl.setCreatedTime(currentTime); 
        ntmdQstnnaireTbl.setUpdatedTime(currentTime); 
		save(ntmdQstnnaireTbl);
		response.setSuccess(true);
		response.setId(ntmdQstnnaireTbl.getId());
		return response;
	}

	@Override
	@Transactional
	public ResponseDTO createNetmdQuestionnaire(
			NetmdQuestionAnswerDTO questionnaire,HeaderDTO header) {
	
		ResponseDTO response = new ResponseDTO();
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		NetmdQuestionnaireTbl netmdQuestionnaireTbl=getById(NetmdQuestionnaireTbl.class, questionnaire.getQuestionnaireId());
		if(netmdQuestionnaireTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.QuestionnaireNotFound);
		
			throw se;
		}
		
		NetmdBranchTbl netmdBranchTbl=  getById(NetmdBranchTbl.class,header.getBranchId());
		if(netmdBranchTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<NetmdQuestionTbl> questionList= getByQuestionId();
		for(NetmdQuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
		}
	
		for(AnswerDTO answer:questionnaire.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
			if(!ans.isEmpty() && !ans.contains("select")){
			    NetmdAnswerTbl netmdanswerTbl= new NetmdAnswerTbl();
			    NetmdQuestionTbl qstntable =new NetmdQuestionTbl();
			    qstntable.setId(qMap.get(answer.getQuestionKey()));
				netmdanswerTbl.setNetmdQuestionnaireTbl(netmdQuestionnaireTbl);
				netmdanswerTbl.setNetmdQuestionTbl(qstntable);
				netmdanswerTbl.setAnswer(ans);
				netmdanswerTbl.setNetmdBranchTbl(netmdBranchTbl);
				save(netmdanswerTbl);
			}

		}

		response.setSuccess(true);
		response.setId(questionnaire.getQuestionnaireId());	
		response.setGlobalId(netmdQuestionnaireTbl.getId());
		response.setCreateDateTime(netmdQuestionnaireTbl.getCreatedTime().toString());
		response.setUpdateDateTime(netmdQuestionnaireTbl.getUpdatedTime().toString());
		return response;
	}

	private List<NetmdQuestionTbl> getByQuestionId() {
		javax.persistence.Query query = em.createQuery(Query.GET_NETMD_QUESTION_TBL);
		return executeQuery(NetmdQuestionTbl.class, query);
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseDTO updateQuestionnaire(NetmdQuestionAnswerDTO questionAnswer,HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		Map<String, Integer> qMap = new HashMap<String, Integer>();
		NetmdQuestionnaireTbl netmdQuestionnaireTbl=getById(NetmdQuestionnaireTbl.class, questionAnswer.getQuestionnaireId());
		if(netmdQuestionnaireTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.QuestionnaireNotFound);
		
			throw se;
		}
		NetmdBranchTbl netmdBranchTbl=  getById(NetmdBranchTbl.class,header.getBranchId());
		if(netmdBranchTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<NetmdAnswerTbl> answerlist=getAnswersLists(netmdQuestionnaireTbl.getId());
		for(NetmdAnswerTbl answer:answerlist){
			delete(answer);
		}
		
		List<NetmdQuestionTbl> questionList= getByQuestionId();
		for(NetmdQuestionTbl questionObj:questionList){
			qMap.put(questionObj.getQuestionKey(), questionObj.getId());
		}
		for(AnswerDTO answer:questionAnswer.getAnswerDTO()){
			String ans=answer.getAnswer().trim();
			if(!ans.isEmpty() && !ans.contains("select")){
			    NetmdAnswerTbl netmdanswerTbl= new NetmdAnswerTbl();
			    NetmdQuestionTbl qstntable =new NetmdQuestionTbl();
			    qstntable.setId(qMap.get(answer.getQuestionKey()));
				netmdanswerTbl.setNetmdQuestionnaireTbl(netmdQuestionnaireTbl);
				netmdanswerTbl.setNetmdQuestionTbl(qstntable);
				netmdanswerTbl.setAnswer(answer.getAnswer());
				netmdanswerTbl.setNetmdBranchTbl(netmdBranchTbl);
				save(netmdanswerTbl);
			}
		}
		Date currentTime=new Date();
		netmdQuestionnaireTbl.setUpdatedTime(currentTime); 
		update(netmdQuestionnaireTbl);
		response.setSuccess(true);
		response.setId(questionAnswer.getQuestionnaireId());
		response.setGlobalId(netmdQuestionnaireTbl.getId());
		response.setUpdateDateTime(netmdQuestionnaireTbl.getUpdatedTime().toString());
		return response;
	}

	private List<NetmdAnswerTbl> getAnswersLists(int qstnrId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BY_QUESTIONNAIRE);
		query.setParameter("param1", qstnrId);
		return executeQuery(NetmdAnswerTbl.class, query);
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseDTO deleteQuestionnaire(int id,HeaderDTO header) {
		ResponseDTO response=new ResponseDTO();
		NetmdQuestionnaireTbl netmdQuestionnaireTbl=getById(NetmdQuestionnaireTbl.class,id);
		if(netmdQuestionnaireTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.QuestionnaireNotFound);
			throw se;
		}
		List<NetmdAnswerTbl> answerlist=getAnswersLists(netmdQuestionnaireTbl.getId());
		for(NetmdAnswerTbl answer:answerlist){
			delete(answer);
		}
		delete(netmdQuestionnaireTbl);
		response.setSuccess(true);
		return response;
	}

	@Override
	@Transactional(readOnly = false)
	public ResponseDTO delete(int id, HeaderDTO header) {
		ResponseDTO response=new ResponseDTO();
		QuestionTbl questionTbl=getById(QuestionTbl.class,id);
		if(questionTbl==null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.QuestionnaireNotFound);
			throw se;
		}
		List<AnswerTbl> answerlist=getAnswers(id);
		for(AnswerTbl answer:answerlist){
			delete(answer);
		}
		response.setSuccess(true);
		return response;
	}

	private List<AnswerTbl> getAnswers(int id) {
		
		javax.persistence.Query query = em
				.createQuery(Query.GET_BY_QUESTIONNAIRE_ID);
		query.setParameter("param1", id);
		return executeQuery(AnswerTbl.class, query);
	}

}
