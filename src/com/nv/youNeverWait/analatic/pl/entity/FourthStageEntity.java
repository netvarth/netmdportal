package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class FourthStageEntity {
	

	private String hospital;
	private String year;
	private Integer month;
	private Integer fourthStgObsrve;
	private Integer manualRmvl;
	private Integer hypotensn;
	
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
	public Integer getFourthStgObsrve() {
		return fourthStgObsrve;
	}
	public void setFourthStgObsrve(BigDecimal fourthStgObsrve) {
		this.fourthStgObsrve = fourthStgObsrve.intValueExact();
	}
	public Integer getManualRmvl() {
		return manualRmvl;
	}
	public void setManualRmvl(BigDecimal manualRmvl) {
		this.manualRmvl = manualRmvl.intValueExact();
	}
	public Integer getHypotensn() {
		return hypotensn;
	}
	public void setHypotensn(BigDecimal hypotensn) {
		this.hypotensn = hypotensn.intValueExact();
	}
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Observed >2 hours      ", fourthStgObsrve);
		   map.put("Placenta Manual Removal", manualRmvl);
		   map.put("Hypotension Developed    ", hypotensn);
	return map;
	}
	


	
	
	
	
	

}
