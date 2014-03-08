package com.nv.youNeverWait.report;

/**
 * @author joshi
 *
 */
public class KeyValue {

	
	private String key;
	private Integer value;
	private String year;
	private String month;
	private String hospital;

	
	public KeyValue(String key, Integer value,String month,String year,String hospital,String name) {
		super();
		
		this.key = key;
		this.value = value;
		this.month = month;
		this.year =year;
		this.hospital = hospital;
		
	}


	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	

	public Integer getValue() {
		return value;
	}


	public void setValue(Integer value) {
		this.value = value;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getHospital() {
		return hospital;
	}


	public void setHospital(String hospital) {
		this.hospital = hospital;
	}



	
	
	
	
}
