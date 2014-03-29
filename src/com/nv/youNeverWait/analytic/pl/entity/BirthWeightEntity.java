package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BirthWeightEntity {

	
	private String hospital;
	private String year;
	private Integer month;
	private Integer baby1GnderM;
	private Integer baby1GnderF;
	private Integer baby1WtGr3500;
	private Integer baby1WtBtw2500And3499;
	private Integer baby1WtLs1500;
	private Integer baby1WtBtw1500And2499;
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
	public Integer getBaby1GnderM() {
		return baby1GnderM;
	}
	public void setBaby1GnderM(BigDecimal baby1GnderM) {
		this.baby1GnderM = baby1GnderM.intValueExact();
	}
	public Integer getBaby1GnderF() {
		return baby1GnderF;
	}
	public void setBaby1GnderF(BigDecimal baby1GnderF) {
		this.baby1GnderF = baby1GnderF.intValueExact();
	}
	public Integer getBaby1WtGr3500() {
		return baby1WtGr3500;
	}
	public void setBaby1WtGr3500(BigDecimal baby1WtGr3500) {
		this.baby1WtGr3500 = baby1WtGr3500.intValueExact();
	}
	public Integer getBaby1WtBtw2500And3499() {
		return baby1WtBtw2500And3499;
	}
	public void setBaby1WtBtw2500And3499(BigDecimal baby1WtBtw2500And3499) {
		this.baby1WtBtw2500And3499 = baby1WtBtw2500And3499.intValueExact();
	}
	public Integer getBaby1WtLs1500() {
		return baby1WtLs1500;
	}
	public void setBaby1WtLs1500(BigDecimal baby1WtLs1500) {
		this.baby1WtLs1500 = baby1WtLs1500.intValueExact();
	}
	public Integer getBaby1WtBtw1500And2499() {
		return baby1WtBtw1500And2499;
	}
	public void setBaby1WtBtw1500And2499(BigDecimal baby1WtBtw1500And2499) {
		this.baby1WtBtw1500And2499 = baby1WtBtw1500And2499.intValueExact();
	}
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Total Males", baby1GnderM);
		   map.put("Total Females", baby1GnderF);
		   map.put("Wt < 1500", baby1WtLs1500 );
		   map.put("Wt 1500-2499", baby1WtBtw1500And2499);
		   map.put("Wt 2500-3499 ", baby1WtBtw2500And3499);
		   map.put("Wt >3500 ", baby1WtGr3500);
	return map;
	}
	
}
