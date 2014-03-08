package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BookedStatisticsEntity {

	private String hospital;
	private String year;
	private String month;
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
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
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
		
		Map<String,Integer>map = new HashMap<String,Integer>();
		   map.put("Booked", booked);
		   map.put("Unbooked", unbooked);
		return map;
	}
	
}
