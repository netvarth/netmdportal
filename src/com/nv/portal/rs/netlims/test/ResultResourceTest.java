/**
 * ResultServiceTest.java
 * @author Mani E.V 
 *
 * Version 1.0 25-Nov-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.portal.rs.netlims.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.nv.youNeverWait.report.PDFResultView;

/**
 *
 *
 * @author Mani E.V
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youneverwait-context.xml"})
public class ResultResourceTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 
	 */
	@Test
	public void printResults(){
		String inputJson = "{'resultHeader':{'orderId':'14JV278','patientName':'Ms. Thara','age':'42--','referral':'Dr. Kesavan Namboodiri   MS','specimen':'Blood Urine','gender':'Female','date':'03-04-2014','time':'09:29 AM','collectedAt':'Jeeva'},'layouts':[{'testLayout':'GeneralReport','tests':[{'testName':'Hb','Set':[{'id':'hb','key':'Hb','value':'11.2','unit':'gms%','normal':'14 - 17'}]},{'testLayout':'General','testName':'PCV (Hematocrit)','Set':[{'id':'PCV','key':'PCV','value':'11.2','unit':'gms%','normal':'14 - 17'}]}]},{'testLayout':'UrineLayoutNew','tests':[{'testName':'Urine Routine Examination','testId':'T100','values':[{'id':'colour','key':'Colour','value':'Pale Yellow','unit':'','normal':''},{'id':'reaction','key':'Reaction','value':'Alkaline','unit':'','normal':''},{'id':'rbc','key':'Red Blood Cells','value':'','unit':'','normal':''},{'id':'sgravity','key':'Specific Gravity','value':'--','unit':'','normal':''},{'id':'pus','key':'Pus Cells','value':'','unit':'','normal':''},{'id':'albumin','key':'Albumin','value':'','unit':'','normal':''},{'id':'bacteria','key':'Bacteria','value':'','unit':'','normal':''},{'id':'epitcell','key':'Epithelial Cells','value':'','unit':'','normal':''},{'id':'sugar','key':'Sugar','value':'','unit':'','normal':''},{'id':'cast','key':'Cast','value':'','unit':'','normal':''},{'id':'acetone','key':'Acetone','value':'--','unit':'','normal':''},{'id':'cryst','key':'Crystals','value':'','unit':'','normal':''},{'id':'bilepig','key':'Bile Pigments','value':'--','unit':'','normal':''},{'id':'bilesalt','key':'Bile salt','value':'--','unit':'','normal':''},{'id':'ammor','key':'Ammor.Urates','value':'--','unit':'','normal':''},{'id':'urobi','key':'Urobilinogen','value':'--','unit':'','normal':''},{'id':'porph','key':'Porphobilinogen','value':'--','unit':'','normal':''}],'departmentName':'','remarks':'Hb - Test repeated ,Kindly correlate clinically\n','userId':'3','userName':'Mr. Lakshminarayanan V.K.','userDesignation':'M.Sc (Med Bio) Sr. Biochemist'}]}]}";
		PDFResultView view= new PDFResultView();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		ServletContext context =	request.getSession().getServletContext();
		String realPath = context.getRealPath("jrxml"+File.separator+"ResultLayout.jrxml");
		Map <String, Object> model = new HashMap <String, Object>(); 
		model.put("JRXML_URL", realPath);
		model.put("inputJson", inputJson);
		ModelAndView modalview= new ModelAndView(view,model);
		System.out.println("Success");
	}
}
