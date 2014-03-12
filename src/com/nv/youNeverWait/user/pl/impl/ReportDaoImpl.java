/**
 * ReportDaoImpl.java
 * January 13,2014
 */
package com.nv.youNeverWait.user.pl.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.user.pl.dao.ReportDao;

/**
 * @author Luciya
 *
 */
public class ReportDaoImpl extends GenericDaoHibernateImpl implements ReportDao{
	@PersistenceContext()
	private EntityManager em;
	
	private String jrxmlPath;
	private String serverPath;
	private String mySqlIpAddress;
	private String mySqlUserName;
	private String mySqlPassword;

	@Override
	@Transactional
	public String getJRXml(String reportName) {
		String jrXml = null;
		if(reportName==null){
			ServiceException se =new ServiceException(ErrorCodeEnum.ReportNull);			
			se.setDisplayErrMsg(true);
			throw se;
		}		
		
		//ReportTbl reportTbl = getJrXmlByReportName(reportName);
//		if(reportTbl == null){
//			ServiceException se =new ServiceException(ErrorCodeEnum.ReportNull);			
//			se.setDisplayErrMsg(true);
//			throw se;
//		}
		String reprtName="GynecologySummaryReport";
		//jrXml ="F://jrxml/"+reprtName+".jrxml";
		jrXml =serverPath.trim()+jrxmlPath.trim()+reprtName+".jrxml";
		return jrXml;

	}
	
	/**
	 * for getting database connection
	 */
	@Override
	@Transactional(readOnly=false)
	public Connection getConnection() {
		Connection conn =null;
		try {
			conn = DriverManager.getConnection(
					mySqlIpAddress+"youneverwait", mySqlUserName,
					mySqlPassword);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorCodeEnum.DatabaseError);
		}
		return conn;
		//		return em.unwrap(java.sql.Connection.class); 
	}

	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getJrxmlPath() {
		return jrxmlPath;
	}

	public void setJrxmlPath(String jrxmlPath) {
		this.jrxmlPath = jrxmlPath;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getMySqlIpAddress() {
		return mySqlIpAddress;
	}

	public void setMySqlIpAddress(String mySqlIpAddress) {
		this.mySqlIpAddress = mySqlIpAddress;
	}

	public String getMySqlUserName() {
		return mySqlUserName;
	}

	public void setMySqlUserName(String mySqlUserName) {
		this.mySqlUserName = mySqlUserName;
	}

	public String getMySqlPassword() {
		return mySqlPassword;
	}

	public void setMySqlPassword(String mySqlPassword) {
		this.mySqlPassword = mySqlPassword;
	}

	
	

}
