package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;



public class MaternalAgeEntity {
	private String hospital;
	private String year;
	private String month;
	private Integer lt20;
	private Integer bt20to24;
	private Integer bt25to29;
	private Integer bt30to35;
	private Integer gt35;
	
	
	
	
	public MaternalAgeEntity(String hospital, String year, String month,
			BigDecimal lt20, BigDecimal bt20to24, BigDecimal bt25to29, BigDecimal bt30to35,
			BigDecimal gt35) {
		super();
		this.hospital = hospital;
		this.year = year;
		this.month = month;
		this.lt20 = lt20.intValueExact();
		this.bt20to24 = bt20to24.intValueExact();
		this.bt25to29 = bt25to29.intValueExact();
		this.bt30to35 = bt30to35.intValueExact();
		this.gt35 = gt35.intValueExact();
	}
	public MaternalAgeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
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
	
	
	
	public Integer getLt20() {
		return lt20;
	}
	public void setLt20(BigDecimal lt20) {
		this.lt20 = lt20.intValueExact();
	}
	public Integer getBt20to24() {
		return bt20to24;
	}
	public void setBt20to24(BigDecimal bt20to24) {
		this.bt20to24 = bt20to24.intValueExact();
	}
	public Integer getBt25to29() {
		return bt25to29;
	}
	public void setBt25to29(BigDecimal bt25to29) {
		this.bt25to29 = bt25to29.intValueExact();
	}
	public Integer getBt30to35() {
		return bt30to35;
	}
	public void setBt30to35(BigDecimal bt30to35) {
		this.bt30to35 = bt30to35.intValueExact();
	}
	public Integer getGt35() {
		return gt35;
	}
	public void setGt35(BigDecimal gt35) {
		this.gt35 = gt35.intValueExact();
	}
	
	
	
	
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new HashMap<String,Integer>();
		   map.put("<20", lt20);
		   map.put("20-24", bt20to24);
		   map.put("25-29", bt25to29);
		   map.put("25-29", bt30to35);
		   map.put(">35", bt30to35);
		return map;
	}
	    
	    
	    	
	    	
	    	
	    	
	   
	 	
		
	
	
	

}
