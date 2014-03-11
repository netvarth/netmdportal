package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MaternalComplicationsEntity {
	
	private String hospital;
	private String year;
	private String month;
	private Integer anemia;
	private Integer diabetes;
	private Integer heartDisease;
	private Integer hyperTension;
	
	
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

	public Integer getAnemia() {
		return anemia;
	}
	public void setAnemia(BigDecimal anemia) {
		this.anemia = anemia.intValueExact();
	}
	public Integer getDiabetes() {
		return diabetes;
	}
	public void setDiabetes(BigDecimal diabetes) {
		this.diabetes = diabetes.intValueExact();
	}
	public Integer getHyperTension() {
		return hyperTension;
	}
	public void setHyperTension(BigDecimal hyperTension) {
		this.hyperTension = hyperTension.intValueExact();
	}
	public Integer getHeartDisease() {
		return heartDisease;
	}
	public void setHeartDisease(BigDecimal heartDisease) {
		this.heartDisease = heartDisease.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Anemia", anemia);
		   map.put("Diabetes", diabetes);
		   map.put("Hypertension", hyperTension);
		   map.put("HeartDisease", heartDisease);
		   
		return map;
	}
	    

	
	

}
