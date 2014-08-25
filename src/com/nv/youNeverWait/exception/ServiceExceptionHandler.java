/**
 * 
 */
package com.nv.youNeverWait.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.youNeverWait.rs.dto.Parameter;

/**
 * @author Mani E.V
 *
 */
public class ServiceExceptionHandler {
	private static final Log log = LogFactory.getLog(ServiceExceptionHandler.class);
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
	/**
	 * @param se
	 * @return errorMessage
	 */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public String handleException(ServiceException se){
		log.info(se);
		String errorMsg = null;
		String oldMsg=se.getError().getErrMsg();
		if(se.getParamList().isEmpty())
			errorMsg = oldMsg;
		for(Parameter param : se.getParamList()){
			String toReplace = "{"+param.getParameterName()+"}";
			String valToReplace = param.getValue();
			errorMsg = oldMsg.replace(toReplace, valToReplace);
		}
		return errorMsg;
	}
}
