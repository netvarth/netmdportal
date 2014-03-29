package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class IntravenusFluidEntity {
	
	
	private String hospital;
	private String year;
	private Integer month;
	private Integer amtFluidsLs1000;
	private Integer amtFluidsBtw1000And3000;
	private Integer amtFluidsGr3000;

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
	
	
	public Integer getAmtFluidsLs1000() {
		return amtFluidsLs1000;
	}
	public void setAmtFluidsLs1000(BigDecimal amtFluidsLs1000) {
		this.amtFluidsLs1000 = amtFluidsLs1000.intValueExact();
	}
	public Integer getAmtFluidsBtw1000And3000() {
		return amtFluidsBtw1000And3000;
	}
	public void setAmtFluidsBtw1000And3000(BigDecimal amtFluidsBtw1000And3000) {
		this.amtFluidsBtw1000And3000 = amtFluidsBtw1000And3000.intValueExact();
	}
	public Integer getAmtFluidsGr3000() {
		return amtFluidsGr3000;
	}
	public void setAmtFluidsGr3000(BigDecimal amtFluidsGr3000) {
		this.amtFluidsGr3000 = amtFluidsGr3000.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("<1000", amtFluidsLs1000);
		   map.put("1000-5000", amtFluidsBtw1000And3000);
		   map.put(">3000", amtFluidsGr3000);
		   
	return map;
	}


	

}
