package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FetalComplicationsEntity {

	private String hospital;
	private String year;
	private Integer month;
	private Integer fetalStllBrth;
	private Integer fetalNeonatalDeath;
	private Integer fetalAnomalies;
	private Integer fetalNICUAdmn;
	private Integer fetalAdmission;
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
	public Integer getFetalStllBrth() {
		return fetalStllBrth;
	}
	public void setFetalStllBrth(BigDecimal fetalStllBrth) {
		this.fetalStllBrth = fetalStllBrth.intValueExact();
	}
	public Integer getFetal1NeonatalDeath() {
		return fetalNeonatalDeath;
	}
	public void setFetal1NeonatalDeath(BigDecimal fetalNeonatalDeath) {
		this.fetalNeonatalDeath = fetalNeonatalDeath.intValueExact();
	}
	public Integer getFetalAnomalies() {
		return fetalAnomalies;
	}
	public void setFetalAnomalies(BigDecimal fetalAnomalies) {
		this.fetalAnomalies = fetalAnomalies.intValueExact();
	}
	public Integer getFetalNICUAdmn() {
		return fetalNICUAdmn;
	}
	public void setFetalNICUAdmn(BigDecimal fetalnicuAdmn) {
		fetalNICUAdmn = fetalnicuAdmn.intValueExact();
	}
	
	
	
	
	
	public Integer getFetalAdmission() {
		return fetalAdmission;
	}
	public void setFetalAdmission(BigDecimal fetalAdmission) {
		this.fetalAdmission = fetalAdmission.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Still Birth", fetalStllBrth);
		   map.put("Fetal Death", fetalNeonatalDeath);
		   map.put("Fetal ICU Admission", fetalAdmission);
		   map.put("Fetal ICU Admission with indication", fetalNICUAdmn);
		   map.put("Congenital Anomalies", fetalAnomalies);
		  
		   
	return map;
	}
	
	
	

	
}
