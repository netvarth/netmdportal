/**
 * ReportManager
 */
package com.nv.youNeverWait.user.bl.impl;

import java.sql.Connection;

import com.nv.youNeverWait.api.report.ReportHandler;
import com.nv.youNeverWait.user.bl.service.ReportService;

/**
 * @author Joshi
 *
 */
public class ReportManager implements ReportService {
	private ReportHandler reportHandler;
	@Override
	public String getJRXmlPath(String reportName) {
		String jrXml = null;
		try{
			jrXml = reportHandler.getJRXml(reportName);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return jrXml;
	}
	/* (non-Javadoc)
	 * @see com.nv.weblims.report.bl.ReportService#getConnection()
	 */
	@Override
	public Connection getConnection() {
		return reportHandler.getConnection();
	}
	
	public ReportHandler getReportHandler() {
		return reportHandler;
	}
	public void setReportHandler(ReportHandler reportHandler) {
		this.reportHandler = reportHandler;
	}

}
