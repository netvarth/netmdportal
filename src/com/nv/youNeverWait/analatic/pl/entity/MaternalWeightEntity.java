package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MaternalWeightEntity {
	
	private String hospital;
	private String year;
	private String month;
	private Integer lt40;
	private Integer bt40to49;
	private Integer bt50to69;
	private Integer bt70to89;
	private Integer gteq90;
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
	
	
	
	
	

	public Integer getLt40() {
		return lt40;
	}
	public void setLt40(Integer lt40) {
		this.lt40 = lt40;
	}
	public Integer getBt40to49() {
		return bt40to49;
	}
	public void setBt40to49(BigDecimal bt40to49) {
		this.bt40to49 = bt40to49.intValueExact();
	}
	public Integer getBt50to69() {
		return bt50to69;
	}
	public void setBt50to69(BigDecimal bt50to69) {
		this.bt50to69 = bt50to69.intValueExact();
	}
	public Integer getBt70to89() {
		return bt70to89;
	}
	public void setBt70to89(BigDecimal bt70to89) {
		this.bt70to89 = bt70to89.intValueExact();
	}
	public Integer getGteq90() {
		return gteq90;
	}
	public void setGteq90(BigDecimal gteq90) {
		this.gteq90 = gteq90.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("<40", lt40);
		   map.put("40-49", bt40to49);
		   map.put("50-69", bt50to69);
		   map.put("70-89",bt70to89);
		   map.put(">=90", gteq90);
		   
		return map;
	}
	    

	

}
