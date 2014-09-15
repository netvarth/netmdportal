/**
 * PDFResultView.java
 * @author Mani E.V 
 *
 * Version 1.0 08-Apr-2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.report;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;


import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.web.servlet.View;

/**
 *
 *
 * @author Mani E.V
 */
public class PDFResultView implements View {

	@PersistenceContext()
	private static final String CONTENT_TYPE = "application/pdf";
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.View#getContentType()
	 */
	@Override
	public String getContentType() {
		return this.CONTENT_TYPE;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.View#render(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void render(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		String layoutFolder = (String) model.get("JRXML_URL")+ "\\";
		String layoutPath =layoutFolder + "\\ResultLayout.jrxml";
		JasperDesign generalDesign = JRXmlLoader.load(layoutFolder+"\\General.jrxml");
		JasperCompileManager.compileReportToFile(generalDesign, layoutFolder+"\\General.jasper");
		generalDesign = JRXmlLoader.load(layoutFolder+"\\Urine.jrxml");
		JasperCompileManager.compileReportToFile(generalDesign, layoutFolder+"\\Urine.jasper");	
		String jsonstr = (String) model.get("inputJson");
		InputStream is = new ByteArrayInputStream(jsonstr.getBytes());
		JRDataSource jsource = new JsonDataSource(is);
		JasperDesign design = JRXmlLoader.load(layoutPath);
		params.put("IS_IGNORE_PAGINATION", false);
		params.put("SUBREPORT_DIR", layoutFolder);
		System.out.println(layoutPath);	
		JasperReport jReport= JasperCompileManager.compileReport(design);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jReport, params, jsource);
		OutputStream out = response.getOutputStream();	
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		out.flush(); 
		out.close();
	}

}
