package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EpisiotomyEntity {
	
	private String hospital;
	private String year;
	private String month;
	private Integer midlineEpisiotomy;
	private Integer medioLateralEpisiotomy;
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
	public Integer getMidlineEpisiotomy() {
		return midlineEpisiotomy;
	}
	public void setMidlineEpisiotomy(BigDecimal midlineEpisiotomy) {
		this.midlineEpisiotomy = midlineEpisiotomy.intValueExact();
	}
	public Integer getMedioLateralEpisiotomy() {
		return medioLateralEpisiotomy;
	}
	public void setMedioLateralEpisiotomy(BigDecimal medioLateralEpisiotomy) {
		this.medioLateralEpisiotomy = medioLateralEpisiotomy.intValueExact();
	}
	
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Midline Episiotomy", midlineEpisiotomy);
		   map.put("Medio Lateral Episiotomy", medioLateralEpisiotomy);
		  
		   
	return map;
	}

	
	

}
