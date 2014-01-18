/**
 * ReportHandler.java
 */
package com.nv.youNeverWait.api.report;

import java.sql.Connection;

import com.nv.youNeverWait.user.pl.dao.ReportDao;



/**
 * @author Joshi
 *
 */
public class ReportHandler {
	private ReportDao reportDao;
	
	public String getJRXml(String reportName) {
		return reportDao.getJRXml(reportName);
	}
	public ReportDao getReportDao() {
		return reportDao;
	}
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	/**
	 * @return
	 */
	public Connection getConnection() {
		return reportDao.getConnection();
	}

}
