package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CaesareanSectionEntity {
	private String hospital;
	private String year;
	private String month;
	private Integer totalCs;
    //private Integer primary;
    //private Integer repeat;
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
	public Integer getTotalCs() {
		return totalCs;
	}
	public void setTotalCs(BigDecimal totalCs) {
		this.totalCs = totalCs.intValueExact();
	}
    
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Total", totalCs);
		return map;
	}
    
}
