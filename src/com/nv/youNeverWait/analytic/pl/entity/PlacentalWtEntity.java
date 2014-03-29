package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class PlacentalWtEntity {
	
	private String hospital;
	private String year;
	private Integer month;
	private Integer placentalWghtLs200;
	private Integer placentalWghtBtw200And399;
	private Integer placentalWghtGr400;
	
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
	
	public Integer getPlacentalWghtLs200() {
		return placentalWghtLs200;
	}
	public void setPlacentalWghtLs200(BigDecimal placentalWghtLs200) {
		this.placentalWghtLs200 = placentalWghtLs200.intValueExact();
	}
	public Integer getPlacentalWghtBtw200And399() {
		return placentalWghtBtw200And399;
	}
	public void setPlacentalWghtBtw200And399(BigDecimal placentalWghtBtw200And399) {
		this.placentalWghtBtw200And399 = placentalWghtBtw200And399.intValueExact();
	}
	public Integer getPlacentalWghtGr400() {
		return placentalWghtGr400;
	}
	public void setPlacentalWghtGr400(BigDecimal placentalWghtGr400) {
		this.placentalWghtGr400 = placentalWghtGr400.intValueExact();
	}
	
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("<200", placentalWghtLs200);
		   map.put("200-399", placentalWghtBtw200And399);
		   map.put(">400", placentalWghtGr400);
		   
		return map;
	}
	

}
