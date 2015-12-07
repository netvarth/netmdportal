/**
 * ServerListener.java
 *
 * @author Asha Chandran
 *
 * Mar 27, 2013
 */
package com.nv.youNeverWait.common;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.nv.platform.email.sendmsg.SendEmailMsgWorkerThread;


/**
 * @author Asha Chandran
 *
 */
public class ServerListener implements ServletContextListener {
	private ServletContext context = null;
	private ApplicationContext applicationContext = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("**************starting******************");
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		SendEmailMsgWorkerThread emailthread = (SendEmailMsgWorkerThread) applicationContext
				.getBean("email.worker.thread");
		Thread thread = new Thread(emailthread, emailthread.getThreadName());
		thread.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {

		System.out.println("destroyed");

	}
}
