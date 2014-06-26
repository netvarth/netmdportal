/**
 * 
 */
package com.nv.youNeverWait.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mani E.V
 *
 */
public class ServiceExceptionHandler {
	/**
	 * @param se
	 * @return errorMessage
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public String handleRunTimeException(RuntimeException se){
		se.printStackTrace();
		return se.getMessage();
	}
	
	/**
	 * @param se
	 * @return errorMessage
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handleException(Exception se){
		se.printStackTrace();
		return se.getMessage();
	}
}
