/**
 * 
 */
package com.nv.youNeverWait.user.pl.dao;

import java.sql.Connection;

/**
 * @author Joshi
 *
 */
public interface ReportDao {

	String getJRXml(String reportName);

	Connection getConnection();

}
