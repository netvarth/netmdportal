/**
 * LogDaoImpl.java
 *
 * April 16, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.LogTbl;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.user.pl.dao.LogDao;

public class LogDaoImpl extends GenericDaoHibernateImpl implements LogDao {

	@PersistenceContext()
	private EntityManager em;

	/**
	 * Save user details
	 */
	@Override
	@Transactional
	public void saveUserDetails(String ipAddress, String userName,
			String userType, Date loginTime, Date logoutTime,
			String applicationName, String actionName) {
		LogTbl log = new LogTbl();
		log.setActionName(actionName);
		log.setApplicationName(applicationName);
		log.setIpAddress(ipAddress);
		log.setLoginTime(loginTime);
		log.setLogoutTime(logoutTime);
		log.setUserName(userName);
		log.setUserType(userType);
		log.setActionDate(new Date());
		SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
		log.setActionTime(df.format(log.getActionDate()));
		try {
			ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpServletRequest request = t.getRequest();
			if (request.getSession().getServletContext()
					.getAttribute("logEnabled") == null) {
				SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
				request.getSession().getServletContext()
						.setAttribute("logEnabled", superAdmin.isEnableLog());
			}
			System.out.println(request.getSession().getServletContext()
					.getAttribute("logEnabled"));
			if ((Boolean) request.getSession().getServletContext()
					.getAttribute("logEnabled")) {
				save(log);
			}
		} catch (ServiceException se) {

		}

	}

	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}
}