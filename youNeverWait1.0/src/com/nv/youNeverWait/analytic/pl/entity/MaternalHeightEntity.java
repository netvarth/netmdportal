package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MaternalHeightEntity {
	
	private String hospital;
	private String year;
	private Integer month;
	private Integer lt145;
	private Integer bt145to149;
	private Integer bt150to170;
	private Integer gt170;
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
	public Integer getLt145() {
		return lt145;
	}
	public void setLt145(BigDecimal lt45) {
		this.lt145 = lt45.intValueExact();
	}
	public Integer getBt145to149() {
		return bt145to149;
	}
	public void setBt145to149(BigDecimal bt145to149) {
		this.bt145to149 = bt145to149.intValueExact();
	}
	public Integer getBt150to170() {
		return bt150to170;
	}
	public void setBt150to170(BigDecimal bt150to170) {
		this.bt150to170 = bt150to170.intValueExact();
	}
	public Integer getGt170() {
		return gt170;
	}
	public void setGt170(BigDecimal gt170) {
		this.gt170 = gt170.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("<145", lt145);
		   map.put("145-149", bt145to149);
		   map.put("150-170", bt150to170);
		   map.put(">170", gt170);
		   
		return map;
	}
	    

	

}
