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

import com.nv.youNeverWait.analatic.bl.AggregateMeasure;
import com.nv.youNeverWait.analatic.bl.Analatic;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.user.pl.dao.ReportDao;



/**
 * @author Joshi
 *
 */
public class ReportHandler {
	
	
	private Map<ReportEnum,ReportData> reportMap;

	
	public InputStream getJRXml(Map<String,Object> map,ServletContext context) {
		String reportName = (String) map.get("reportName");
		ReportEnum reportEnum = ReportEnum.getEnum(reportName);
		String realPath = context.getRealPath("jrxml"+File.separator+reportEnum.getJrxml()+".jrxml");
		File file = new File(realPath);
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorCodeEnum.invalidJrxmlPath);
		}
	
		
	}
	
	
	
	

	
	public JasperPrint createReport(InputStream is,Map<String,Object> map){
		String reportName = (String) map.get("reportName");
		String fMonth = (String) map.get("startMonth");
		String fYear = (String) map.get("startYear");
		String toMonth = (String) map.get("endMonth");
		String toYear = (String) map.get("endYear");
		String hospitalString = (String) map.get("paramList");
		Integer hospital=null;
		if (hospitalString !=null)
		hospital = Integer.parseInt(hospitalString);
		ReportEnum reportEnum = ReportEnum.getEnum(reportName);
		ReportData dataBean = reportMap.get(reportEnum);
		List<Inference<? extends Measure>> dataBeanList= null;
		if (hospital !=null){
			dataBeanList = dataBean.getDataBeans(fMonth, fYear, toMonth, toYear,hospital);
		}else{
			dataBeanList = dataBean.getDataBeans(fMonth, fYear, toMonth, toYear);
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






	public Map<ReportEnum, ReportData> getReportMap() {
		return reportMap;
	}






	public void setReportMap(Map<ReportEnum, ReportData> reportMap) {
		this.reportMap = reportMap;
	}




	
	
	
	
	

}
