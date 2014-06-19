package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PerinealTearEntity {
	private String hospital;
	private String year;
	private Integer month;
	private Integer perinealTear1;
	private Integer perinealTear2;
	private Integer perinealTear3a;
	private Integer perinealTear3b;
	private Integer perinealTear3c;
	private Integer perinealTear4;
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
	public Integer getPerinealTear1() {
		return perinealTear1;
	}
	public void setPerinealTear1(BigDecimal perinealTear1) {
		this.perinealTear1 = perinealTear1.intValueExact();
	}
	public Integer getPerinealTear2() {
		return perinealTear2;
	}
	public void setPerinealTear2(BigDecimal  perinealTear2) {
		this.perinealTear2 = perinealTear2.intValueExact();
	}
	public Integer getPerinealTear3a() {
		return perinealTear3a;
	}
	public void setPerinealTear3a(BigDecimal perinealTear3a) {
		this.perinealTear3a = perinealTear3a.intValueExact();
	}
	public Integer getPerinealTear3b() {
		return perinealTear3b;
	}
	public void setPerinealTear3b(BigDecimal perinealTear3b) {
		this.perinealTear3b = perinealTear3b.intValueExact();
	}
	public Integer getPerinealTear3c() {
		return perinealTear3c;
	}
	public void setPerinealTear3c(BigDecimal perinealTear3c) {
		this.perinealTear3c = perinealTear3c.intValueExact();
	}
	public Integer getPerinealTear4() {
		return perinealTear4;
	}
	public void setPerinealTear4(BigDecimal perinealTear4) {
		this.perinealTear4 = perinealTear4.intValueExact();
	}
	
	
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("1", perinealTear1);
		   map.put("2", perinealTear2);
		   map.put("3a",perinealTear3a);
		   map.put("3b", perinealTear3b);
		   map.put("3c", perinealTear3c);
		   map.put("4", perinealTear4);
		   		   
	return map;
	}

	
	
}
