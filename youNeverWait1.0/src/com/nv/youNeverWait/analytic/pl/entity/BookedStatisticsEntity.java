package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BookedStatisticsEntity {

	private String hospital;
	private String year;
	private Integer month;
	private Integer booked; 
	private Integer unbooked;
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
	public Integer getBooked() {
		return booked;
	}
	public void setBooked(BigDecimal booked) {
		this.booked = booked.intValueExact();
	}
	public Integer getUnbooked() {
		return unbooked;
	}
	public void setUnbooked(BigDecimal unbooked) {
		this.unbooked = unbooked.intValueExact();
	}
	
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Booked", booked);
		   map.put("Unbooked", unbooked);
		return map;
	}
	
}
