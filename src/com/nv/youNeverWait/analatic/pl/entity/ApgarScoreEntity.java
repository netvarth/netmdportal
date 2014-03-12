package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApgarScoreEntity {
	
	 
     private String hospital;
     private String year;
     private Integer month;
 	 private Integer oneAtFive;
	 private Integer fiveAt5;
	 
	 
	 
	public Integer getOneAtFive() {
		return oneAtFive;
	}
	public void setOneAtFive(BigDecimal oneAtFive) {
		this.oneAtFive = oneAtFive.intValueExact();
	}
	public Integer getFiveAt5() {
		return fiveAt5;
	}
	public void setFiveAt5(BigDecimal fiveAt5) {
		this.fiveAt5 = fiveAt5.intValueExact();
	}
	
	
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hostpital) {
		this.hospital = hostpital;
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
public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("1 at 5'", oneAtFive);
		   map.put("5 at 5'", fiveAt5);
		return map;
	} 
	
	
}
