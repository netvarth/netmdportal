/**
 * ReportHandler.java
 */
package com.nv.youNeverWait.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.nv.youNeverWait.analatic.bl.Analatic;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.user.pl.dao.ReportDao;



/**
 * @author Joshi
 *
 */
public class ReportHandler {
	private ReportDao reportDao;
	private Analatic analatic;
	
	public InputStream getJRXml(Map<String,Object> map,ServletContext context) {
		String reportName = (String) map.get("reportName");
		
		String realPath = context.getRealPath("jrxml"+File.separator+"vertical"+".jrxml");
		File file = new File(realPath);
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorCodeEnum.invalidJrxmlPath);
		}
	
		
	}
	
	
	
	
	public ReportDao getReportDao() {
		return reportDao;
	}
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	
	public JasperPrint createReport(InputStream is,Map<String,Object> map){
		
		String fMonth = (String) map.get("startMonth");
		String fYear = (String) map.get("startYear");
		String toMonth = (String) map.get("endMonth");
		String toYear = (String) map.get("endYear");
		Integer hospital = (Integer) map.get("paramList");
		
		
		List<Inference> dataBeanList=null;
		if (hospital !=null){
			
			dataBeanList = analatic.getInferencesPerHospital(fMonth, fYear, toMonth, toYear,hospital);
		}else{
		
		   dataBeanList = analatic.getInferences(fMonth, fYear, toMonth, toYear);
		}
		JasperReport jasperReport = null;
		JasperPrint jasperPrint = null;
		try{
			HashMap<String, Object> reportParms = new HashMap<String, Object>();
			JasperDesign jasperDesign = JRXmlLoader.load(is);
		
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
			jasperPrint = JasperFillManager.fillReport(jasperReport,reportParms,beanColDataSource);
			
		}
		catch(JRException e){
			e.printStackTrace();
			throw new ServiceException(ErrorCodeEnum.JasperException);
		}

	return jasperPrint;
	}




	public Analatic getAnalatic() {
		return analatic;
	}




	public void setAnalatic(Analatic analatic) {
		this.analatic = analatic;
	}
	
	
	
	
	
	

}
