package com.nv.youNeverWait.report;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;

public enum ReportEnum {
	
	DETAILED("Detailed","vertical"),
	SUMMARY("Summary","summary");
	
	
	private String displayName;

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	
	private String jrxml;

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	
	private ReportEnum(String displayName, String jrxml) {
		this.displayName = displayName;
		this.jrxml = jrxml;
	}
	
	
	
	


	public String getJrxml() {
		return jrxml;
	}



	public void setJrxml(String jrxml) {
		this.jrxml = jrxml;
	}



	public static ReportEnum getEnum(String value) {
		if(value == null){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidActionType);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(ReportEnum v : values()){        	
			if(value.equalsIgnoreCase(v.getDisplayName())){
				return v;}}
		ServiceException se = new ServiceException(ErrorCodeEnum.InvalidActionType);
		se.setDisplayErrMsg(true);
		throw se;
    }


}
