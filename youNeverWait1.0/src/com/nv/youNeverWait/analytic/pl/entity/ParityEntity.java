package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParityEntity {
	private String hospital;
	private String year;
	private Integer month;
	private Integer eq1;
	private Integer eq2;
	private Integer eq3;
	private Integer bt4to5;
	private Integer gteq6;
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
	public Integer getEq1() {
		return eq1;
	}
	public void setEq1(BigDecimal eq1) {
		this.eq1 = eq1.intValueExact();
	}
	public Integer getEq2() {
		return eq2;
	}
	public void setEq2(BigDecimal eq2) {
		this.eq2 = eq2.intValueExact();
	}
	public Integer getEq3() {
		return eq3;
	}
	public void setEq3(BigDecimal eq3) {
		this.eq3 = eq3.intValueExact();
	}
	public Integer getBt4to5() {
		return bt4to5;
	}
	public void setBt4to5(BigDecimal bt4to5) {
		this.bt4to5 = bt4to5.intValueExact();
	}
	public Integer getGteq6() {
		return gteq6;
	}
	public void setGteq6(BigDecimal gteq6) {
		this.gteq6 = gteq6.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Para1", eq1);
		   map.put("Para2", eq2);
		   map.put("Para3", eq3);
		   map.put("Para4&5", bt4to5);
		   map.put("Para6&Above", gteq6);
		   
		return map;
	}
	

	
	
	

}
