/**
 * ErrorDTO.java
 *
 * Jul 25, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 */
public class ErrorDTO {
	
	private String errCode;
	private List<Parameter> params;
	private boolean displayErrMsg;
	
	
	/**
	 * @param errCode
	 * @param params
	 * @param displayErrMsg
	 */
	public ErrorDTO(String errCode, List<Parameter> params,
			boolean displayErrMsg) {
		super();
		this.errCode = errCode;
		this.params = params;
		this.displayErrMsg = displayErrMsg;
	}

	public ErrorDTO() {
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode() {
		return errCode;
	}
	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	/**
	 * @return the params
	 */
	public List<Parameter> getParams() {
		return params;
	}
	/**
	 * @param params the params to set
	 */
	public void setParams(List<Parameter> params) {
		this.params = params;
	}

	/**
	 * @return the displayErrMsg
	 */
	public boolean isDisplayErrMsg() {
		return displayErrMsg;
	}

	/**
	 * @param displayErrMsg the displayErrMsg to set
	 */
	public void setDisplayErrMsg(boolean displayErrMsg) {
		this.displayErrMsg = displayErrMsg;
	}
	
}
