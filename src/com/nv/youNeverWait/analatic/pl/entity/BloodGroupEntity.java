package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BloodGroupEntity {
	
	private String hospital;
	private String year;
	private String month;
	private Integer a;
	private Integer b;
	private Integer ab;
	private Integer o;
	private Integer rhNegative;
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
	public Integer getA() {
		return a;
	}
	public void setA(BigDecimal a) {
		this.a = a.intValueExact();
	}
	public Integer getB() {
		return b;
	}
	public void setB(BigDecimal b) {
		this.b = b.intValueExact();
	}
	public Integer getAb() {
		return ab;
	}
	public void setAb(BigDecimal ab) {
		this.ab = ab.intValueExact();
	}
	public Integer getO() {
		return o;
	}
	public void setO(BigDecimal o) {
		this.o = o.intValueExact();
	}
	
	public Integer getRhNegative() {
		return rhNegative;
	}
	public void setRhNegative(BigDecimal rhNegative) {
		this.rhNegative = rhNegative.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new HashMap<String,Integer>();
		   map.put("A", a);
		   map.put("B", b);
		   map.put("AB", ab);
		   map.put("O", o);
		   map.put("RhNegative", rhNegative);
		   
	return map;
	}
	

}
