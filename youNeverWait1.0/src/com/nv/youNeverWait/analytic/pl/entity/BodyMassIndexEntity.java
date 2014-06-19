package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BodyMassIndexEntity {

	private String hospital;
	private String year;
	private Integer month;
	private Integer lt20;
	private Integer bt20to24;
	private Integer bt25to29;
	private Integer bt30to34;
	private Integer gteq35;
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
	
	
	
	
	
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
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
	public Integer getBt30to34() {
		return bt30to34;
	}
	public void setBt30to34(BigDecimal bt30to34) {
		this.bt30to34 = bt30to34.intValueExact();
	}
	public Integer getGteq35() {
		return gteq35;
	}
	public void setGteq35(BigDecimal gteq35) {
		this.gteq35 = gteq35.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("<20", lt20);
		   map.put("20-24", bt20to24);
		   map.put("25-29", bt25to29);
		   map.put("30-34", bt30to34);
		   map.put(">=35", gteq35);
		   
		return map;
	}

	
	
}
