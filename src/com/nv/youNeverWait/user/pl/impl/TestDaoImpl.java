/**
 * TestDaoImpl.java
 * @author netvarth
 *
 * Version 1.0 Sep 3, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.pl.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ActionNameEnum;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.SpecimenTbl;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.entity.TestSpecimenTbl;
import com.nv.youNeverWait.pl.entity.TestTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.AddTestDTO;
import com.nv.youNeverWait.rs.dto.DeleteTestResponseDTO;
import com.nv.youNeverWait.rs.dto.EnableLogStatusResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LogDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.TestSpecimenDTO;
import com.nv.youNeverWait.rs.dto.UpdateTestResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.rs.dto.ViewTestResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.TestDao;
/**
 *
 *
 * @author Luciya Jose
 */
public class TestDaoImpl extends GenericDaoHibernateImpl implements TestDao{

	@PersistenceContext()
	private EntityManager em;
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.TestDao#createTest(com.nv.youNeverWait.rs.dto.AddTestDTO)
	 */
	/**
	 * for creating a new test
	 * @param testDTO
	 * @return CreateTestResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO createTest(AddTestDTO testDTO) {
		ResponseDTO response = new ResponseDTO();
		TestTbl testTbl = new TestTbl();
	//	BigInteger testUid = getNextSequence(Constants.TEST_UID_SEQ);		
		//testTbl.setUid(testUid.intValue());		
		String testName = testDTO.getName().trim().replaceAll(" +", " ");
		TestTbl test = getTestTblByName(testName.toUpperCase());
		if(test!=null){
			ServiceException se =new ServiceException(ErrorCodeEnum.TestNameExists);
			se.addParam(new Parameter(Constants.NAME,testName));
			se.setDisplayErrMsg(true);
			throw se;
		}
		testTbl.setTestName(testName.trim());
		testTbl.setGenericName(testDTO.getGenericName());		
		if(!testDTO.getAbbreviation().equals("")){
			String abbreviation = testDTO.getAbbreviation().trim(); 
			TestTbl testAbb = getTestTblByAbbreviation(abbreviation.toUpperCase());
			if(testAbb!=null){
				ServiceException se =new ServiceException(ErrorCodeEnum.AbbreviationExists);
				se.addParam(new Parameter(Constants.NAME,abbreviation));
				se.setDisplayErrMsg(true);
				throw se;
			}
			testTbl.setAbbreviation(abbreviation.toUpperCase());
		}
		if(!testDTO.getMinTimeRequired().equals(""))			
			testTbl.setMinTimeRequired(testDTO.getMinTimeRequired());			
		testTbl.setResult(testDTO.getResult());
		testTbl.setRemarks(testDTO.getRemarks());
		testTbl.setNormalRange(testDTO.getNormalRange());
		testTbl.setUploadStatus(false);
		testTbl.setActive(true);
		testTbl.setSpecimenentryStatus(testDTO.isSpecimenEntryStatus());
		testTbl.setMachineentryStatus(testDTO.isMachineEntryStatus());
		save(testTbl);

		List<TestSpecimenDTO> testSpecimens = new ArrayList<TestSpecimenDTO>(); 
		if(testDTO.getTestSpecimen()!=null){			
			for(TestSpecimenDTO testSpecimen:testDTO.getTestSpecimen()){
				TestSpecimenTbl testSpecimenTbl = new TestSpecimenTbl();
				SpecimenTbl specimenTbl = getByUid(SpecimenTbl.class,testSpecimen.getSpecimenUid());
				if(specimenTbl!=null){
					testSpecimenTbl.setTestTbl(testTbl);
					testSpecimenTbl.setSpecimenTbl(specimenTbl);
					testSpecimenTbl.setQuantity(testSpecimen.getQuantity());
					save(testSpecimenTbl);
				}

			}
		}	
		response.setId(testTbl.getId());
		response.setSuccess(true);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.TestDao#updateTest(com.nv.youNeverWait.rs.dto.AddTestDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public UpdateTestResponseDTO updateTest(AddTestDTO testDTO) {
		UpdateTestResponseDTO response = new UpdateTestResponseDTO();
		TestTbl testTbl = null;
		List<TestSpecimenDTO> testSpecimens = new ArrayList<TestSpecimenDTO>(); 
		if(testDTO.getUid()!=null){

			testTbl = getByUid(TestTbl.class,Integer.parseInt(testDTO.getUid()));
			if(testTbl==null){
				ServiceException se =new ServiceException(ErrorCodeEnum.InvalidTestUid);
				se.setDisplayErrMsg(true);
				throw se;
			}

			if(testDTO.getName()!=null){
				String testName = testDTO.getName().trim().replaceAll(" +", " ");
				TestTbl test = getTestTblByName(testName.toUpperCase());
				if(test!=null){
					if(Integer.parseInt(testDTO.getUid())!=test.getUid()){
						ServiceException se =new ServiceException(ErrorCodeEnum.TestNameExists);
						se.addParam(new Parameter(Constants.NAME,testName));
						se.setDisplayErrMsg(true);
						throw se;
					}
				}
				testTbl.setTestName(testName.trim());
			}						
			testTbl.setUid(Integer.parseInt(testDTO.getUid()));
			if(!testDTO.getAbbreviation().equals("")){
				String abbreviation = testDTO.getAbbreviation().trim(); 				
				TestTbl testAbb = getTestTblByAbbreviation(abbreviation.toUpperCase());
				if(testAbb!=null){
					if(Integer.parseInt(testDTO.getUid())!=testAbb.getUid()){
						ServiceException se =new ServiceException(ErrorCodeEnum.AbbreviationExists);
						se.addParam(new Parameter(Constants.NAME,abbreviation));
						se.setDisplayErrMsg(true);
						throw se;
					}
				}
				testTbl.setAbbreviation(abbreviation.toUpperCase());
			}	
			if(!testDTO.getMinTimeRequired().equals(""))			
				testTbl.setMinTimeRequired(testDTO.getMinTimeRequired());		
			testTbl.setGenericName(testDTO.getGenericName());				
			testTbl.setResult(testDTO.getResult());
			testTbl.setRemarks(testDTO.getRemarks());
			testTbl.setUploadStatus(false);
			testTbl.setSpecimenentryStatus(testDTO.isSpecimenEntryStatus());
			testTbl.setMachineentryStatus(testDTO.isMachineEntryStatus());
			testTbl.setNormalRange(testDTO.getNormalRange());
			update(testTbl);

			if(!testDTO.getTestSpecimen().isEmpty()){
				for(TestSpecimenDTO testSpecimen:testDTO.getTestSpecimen()){						
					SpecimenTbl specimenTbl = getByUid(SpecimenTbl.class,testSpecimen.getSpecimenUid());
					if(specimenTbl!=null){							
						TestSpecimenTbl testSpecimenTbl = getTestSpecimenBySpecimenUid(testTbl.getUid(),specimenTbl.getUid());
						if(testSpecimen.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
							if(testSpecimenTbl!=null){
								ServiceException se =new ServiceException(ErrorCodeEnum.SpecimenExist);
								se.addParam(new Parameter(Constants.SPECIMENUID,Integer.toString(testSpecimen.getSpecimenUid())));
								se.setDisplayErrMsg(true);
								throw se;
							}
							TestSpecimenTbl newTestSpecimen = new TestSpecimenTbl();
							newTestSpecimen.setTestTbl(testTbl);
							newTestSpecimen.setSpecimenTbl(specimenTbl);
							newTestSpecimen.setQuantity(testSpecimen.getQuantity());
							save(newTestSpecimen);								
						}
						if(testSpecimen.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
							if(testSpecimenTbl==null){
								ServiceException se =new ServiceException(ErrorCodeEnum.SpecimenNotExist);
								se.addParam(new Parameter(Constants.SPECIMENUID,Integer.toString(testSpecimen.getSpecimenUid())));
								se.setDisplayErrMsg(true);
								throw se;
							}
							testSpecimenTbl.setTestTbl(testTbl);
							testSpecimenTbl.setSpecimenTbl(specimenTbl);
							testSpecimenTbl.setQuantity(testSpecimen.getQuantity());
							update(testSpecimenTbl);								
						}
						if(testSpecimen.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
							if(testSpecimenTbl==null){
								ServiceException se =new ServiceException(ErrorCodeEnum.SpecimenNotExist);
								se.addParam(new Parameter(Constants.SPECIMENUID,Integer.toString(testSpecimen.getSpecimenUid())));
								se.setDisplayErrMsg(true);
								throw se;
							}
							delete(testSpecimenTbl);
						}
					}
					TestSpecimenDTO testSpec = new TestSpecimenDTO();
					testSpec.setSpecimenUid(testSpecimen.getSpecimenUid());	
					testSpec.setSpecimenName(testSpecimen.getSpecimenName());
					testSpec.setQuantity(testSpecimen.getQuantity());	
					testSpecimens.add(testSpec);
				}					
			}
		}
		
//		TestDTO testDto = new TestDTO();
//		testDto.setUid(Constants.Test+testTbl.getUid());
//		testDto.setName(testTbl.getTestName());
//		testDto.setAbbreviation(testTbl.getAbbreviation());
//		testDto.setGenericName(testTbl.getGenericName());
//		testDto.setInformaticValue(testTbl.getInformaticValues());
//		testDto.setRemarks(testTbl.getRemarks());
//		testDto.setResult(testTbl.getResult());
//		testDto.setMinTimeRequired(testTbl.getMinTimeRequired());
//		testDto.setUploadStatus(testTbl.getUploadStatus());
//		if(testRateTbl!=null)
//			testDto.setStandardRate(testRateTbl.getStandardRate());			
//		testDto.setTestSpecimen(testSpecimens);
//		response.setTest(testDto);
		response.setSuccess(true);
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.TestDao#deleteTest(java.lang.String)
	 */
	@Override
	@Transactional
	public DeleteTestResponseDTO deleteTest(String uid) {
		DeleteTestResponseDTO response = new DeleteTestResponseDTO();
		
		TestTbl testTbl = getByUid(TestTbl.class,Integer.parseInt(uid));
		if(testTbl==null){
			ServiceException se =new ServiceException(ErrorCodeEnum.InvalidTest);
			se.addParam(new Parameter(Constants.TESTUID,uid));
			se.setDisplayErrMsg(true);
			throw se;
		}
				
			List<TestSpecimenTbl> testSpecimenList = getSpecimenList(Integer.parseInt(uid));
			for (TestSpecimenTbl testSpecimenTbl : testSpecimenList) {
				delete(testSpecimenTbl);
			}			
			delete(testTbl);
		
		response.setUid(uid);
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.TestDao#viewTest(java.lang.String)
	 */
	@Override
	@Transactional
	public ViewTestResponseDTO viewTest(String uid) {
		ViewTestResponseDTO  response=new ViewTestResponseDTO() ;
			
			TestTbl testTbl= getByUid(TestTbl.class, Integer.parseInt(uid));
			if(testTbl==null){
				ServiceException se =new ServiceException(ErrorCodeEnum.NullTestUid);
				se.addParam(new Parameter(Constants.TESTUID,uid));
				se.setDisplayErrMsg(true);
				throw se;
			}
			response.setUid(Integer.toString(testTbl.getUid()));
			response.setName(testTbl.getTestName());
			response.setAbbreviation(testTbl.getAbbreviation());
			response.setGenericName(testTbl.getGenericName());
			response.setMinTimeRequired(testTbl.getMinTimeRequired());
			response.setNormalRange(testTbl.getNormalRange());
			response.setRemarks(testTbl.getRemarks());
			response.setUploadStatus(testTbl.getUploadStatus());
			response.setSpecimenentryStatus(testTbl.getSpecimenentryStatus());
			response.setMachineEntryStatus(testTbl.getMachineentryStatus());
			response.setActive(testTbl.getActive());
			response.setResult(testTbl.getResult());
			
			// get specimen details of the test
			List<TestSpecimenTbl> testSpecimenList =(List<TestSpecimenTbl>)getTestSpecimen(Integer.parseInt(uid));
			if(testSpecimenList.size()!=0){
				List<TestSpecimenDTO>testSpecimens =new ArrayList<TestSpecimenDTO>();
				for (TestSpecimenTbl testSpecimenTbl : testSpecimenList) {
					TestSpecimenDTO testSpecimenDTO=new TestSpecimenDTO();
					if(testSpecimenTbl.getSpecimenTbl()!=null){
						testSpecimenDTO.setSpecimenName(testSpecimenTbl.getSpecimenTbl().getName());
						testSpecimenDTO.setSpecimenUid(testSpecimenTbl.getSpecimenTbl().getUid());
						testSpecimenDTO.setQuantity(testSpecimenTbl.getQuantity());
					}
					testSpecimens.add(testSpecimenDTO);
				}// end of for loop
				response.setTestSpecimens(testSpecimens);
			}			
			response.setSuccess(true);
			return response;

		
	}

	
	/**
	 * @param parseInt
	 * @return
	 */
	private List<TestSpecimenTbl> getTestSpecimen(Integer testUId){
		javax.persistence.Query query=em.createQuery(Query.GET_TEST_SPECIMEN);
		query.setParameter("param1", testUId);
		return executeQuery(TestSpecimenTbl.class, query);
	}

	/**
	 * @param uid
	 * @param uid2
	 * @return
	 */
	private TestSpecimenTbl getTestSpecimenBySpecimenUid(int testUid,int specimenUid) {
		javax.persistence.Query query=em.createQuery(Query.GET_TEST_SPECIMEN_BY_SPECIMEN_UID);
		query.setParameter("param1", testUid);
		query.setParameter("param2", specimenUid);
		return executeUniqueQuery(TestSpecimenTbl.class, query);
	}


	private TestTbl getTestTblByName(String testName) {
		javax.persistence.Query query=em.createQuery(Query.GET_TEST_BY_NAME);
		query.setParameter("param1", testName);
		return executeUniqueQuery(TestTbl.class, query);
	}

	private TestTbl getTestTblByAbbreviation(String abbreviation) {
		javax.persistence.Query query=em.createQuery(Query.GET_TEST_BY_ABBREVIATION);
		query.setParameter("param1", abbreviation);
		return executeUniqueQuery(TestTbl.class, query);
	}
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}
	/**
	 * @param em the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	

	

}