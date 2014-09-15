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

	@RequestMapping(value="/print", method=RequestMethod.GET)
	public ModelAndView printResult() {
		String inputJson = "{'resultHeader':{'orderId':'14JV278','patientName':'Ms. Thara','age':'42--','referral':'Dr. Kesavan Namboodiri MS','specimen':'Blood Urine','gender':'Female','date':'03-04-2014','time':'09:29 AM','collectedAt':'Jeeva'},'layouts':[{'testLayout':'General','tests':[{'testName':'Hb','Set':[{'id':'hb','key':'Hb','value':'11.2','unit':'gms%','normal':'14 - 17'}]},{'testLayout':'General','testName':'PCV (Hematocrit)','Set':[{'id':'PCV','key':'PCV','value':'11.2','unit':'gms%','normal':'14 - 17'}]}]},{'testLayout':'Urine','tests':[{'testName':'Urine Routine Examination','testId':'T100','values':[{'id':'colour','key':'Colour','value':'Pale Yellow','unit':'','normal':''},{'id':'reaction','key':'Reaction','value':'Alkaline','unit':'','normal':''},{'id':'rbc','key':'Red Blood Cells','value':'','unit':'','normal':''},{'id':'sgravity','key':'Specific Gravity','value':'--','unit':'','normal':''},{'id':'pus','key':'Pus Cells','value':'','unit':'','normal':''},{'id':'albumin','key':'Albumin','value':'','unit':'','normal':''},{'id':'bacteria','key':'Bacteria','value':'','unit':'','normal':''},{'id':'epitcell','key':'Epithelial Cells','value':'','unit':'','normal':''},{'id':'sugar','key':'Sugar','value':'','unit':'','normal':''},{'id':'cast','key':'Cast','value':'','unit':'','normal':''},{'id':'acetone','key':'Acetone','value':'--','unit':'','normal':''},{'id':'cryst','key':'Crystals','value':'','unit':'','normal':''},{'id':'bilepig','key':'Bile Pigments','value':'--','unit':'','normal':''},{'id':'bilesalt','key':'Bile salt','value':'--','unit':'','normal':''},{'id':'ammor','key':'Ammor.Urates','value':'--','unit':'','normal':''},{'id':'urobi','key':'Urobilinogen','value':'--','unit':'','normal':''},{'id':'porph','key':'Porphobilinogen','value':'--','unit':'','normal':''}],'departmentName':'','remarks':'Hb - Test repeated,Kindly correlate clinically','userId':'3','userName':'Mr. Lakshminarayanan V.K.','userDesignation':'M.Sc (Med Bio) Sr. Biochemist'}]}]}";
		PDFResultView view= new PDFResultView();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
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
