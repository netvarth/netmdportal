/**
 * ResultResource.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Jan 5, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.portal.rs.netlims;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.exception.ServiceExceptionHandler;
import com.nv.youNeverWait.report.PDFResultView;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.PageLayoutSettings;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.user.bl.service.ResultService;


/**
 * 
 */
@Controller
@RequestMapping("ui/result/")
public class ResultResource extends ServiceExceptionHandler{
	private ResultService resultService;


	
	/**
	 * list of patient result result
	 * @param patientId 
	 * @return ResultListResponseDTO
	 */
	@RequestMapping(value = "list/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public ResultListResponseDTO listResult(@PathVariable String patientId) {
		ResultListResponseDTO response = new ResultListResponseDTO();
		try {
			response = resultService.listResult(patientId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	@RequestMapping(value="/print/", method=RequestMethod.POST)
	public ModelAndView printResult(HttpServletRequest request) throws IOException{
		//String inputJson = "{'resultHeader':{'orderId':'14JV278','patientName':'Ms.Thara','age':'42--','referral':'Dr.KesavanNamboodiriMS','specimen':'BloodUrine','gender':'Female','date':'03-04-2014','time':'09:29AM','collectedAt':'Jeeva'},'layouts':[{'testLayout':'General','tests':[{'testName':'UrineRoutineExamination','testId':'T100','values':[{'id':'colour','key':'Colour','value':'PaleYellow','unit':'','normal':''},{'id':'reaction','key':'Reaction','value':'Alkaline','unit':'','normal':''},{'id':'rbc','key':'RedBloodCells','value':'','unit':'','normal':''},{'id':'sgravity','key':'SpecificGravity','value':'--','unit':'','normal':''},{'id':'pus','key':'PusCells','value':'','unit':'','normal':''},{'id':'albumin','key':'Albumin','value':'','unit':'','normal':''},{'id':'bacteria','key':'Bacteria','value':'','unit':'','normal':''},{'id':'epitcell','key':'EpithelialCells','value':'','unit':'','normal':''},{'id':'sugar','key':'Sugar','value':'','unit':'','normal':''},{'id':'cast','key':'Cast','value':'','unit':'','normal':''},{'id':'acetone','key':'Acetone','value':'--','unit':'','normal':''},{'id':'cryst','key':'Crystals','value':'','unit':'','normal':''},{'id':'bilepig','key':'BilePigments','value':'--','unit':'','normal':''},{'id':'bilesalt','key':'Bilesalt','value':'--','unit':'','normal':''},{'id':'ammor','key':'Ammor.Urates','value':'--','unit':'','normal':''},{'id':'urobi','key':'Urobilinogen','value':'--','unit':'','normal':''},{'id':'porph','key':'Porphobilinogen','value':'--','unit':'','normal':''}],'departmentName':'','remarks':'Hb-Testrepeated,Kindlycorrelateclinically','userId':'3','userName':'Mr.LakshminarayananV.K.','userDesignation':'M.Sc(MedBio)Sr.Biochemist'}]},{'testLayout':'General','tests':[{'testName':'HB','testId':'T100','values':[{'id':'hb','key':'Colour','value':'PaleYellow','unit':'','normal':''}],'departmentName':'','remarks':'Hb-Testrepeated,Kindlycorrelateclinically','userId':'3','userName':'Mr.LakshminarayananV.K.','userDesignation':'M.Sc(MedBio)Sr.Biochemist'}]}]}";
		String inputJson =request.getParameter("input");
		PDFResultView view= new PDFResultView();
		System.out.println(inputJson);
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		ServletContext context =	request.getSession().getServletContext();
		String realPath = context.getRealPath("jrxml"+File.separator+"");
		Map <String, Object> model = new HashMap <String, Object>(); 
		model.put("JRXML_URL", realPath);
		model.put("inputJson", inputJson);
		ModelAndView modalview= new ModelAndView(view,model);
		System.out.println("Success");
		return modalview;
	}

	/** 
	 * @return List
	 */
	@RequestMapping(value="/pageSettings/{branchId}", method=RequestMethod.GET)
	@ResponseBody
	public List<PageLayoutSettings> viewPageSettings(@PathVariable int branchId){
		return resultService.getPageSettings(branchId);
	}
	/**
	 * @return the resultService
	 */
	public ResultService getResultService() {
		return resultService;
	}



	/**
	 * @param resultService the resultService to set
	 */
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}
}
