package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class ThirdStageEntity {

	private String hospital;
	private String year;
	private Integer month;
	private Integer thrdStgDurtnLs10;
	private Integer thrdStgDurtn10To29;
	private Integer thrdStgDurtnGr30;
	
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
	
	
	

	public Integer getThrdStgDurtnLs10() {
		return thrdStgDurtnLs10;
	}
	public void setThrdStgDurtnLs10(BigDecimal thrdStgDurtnLs10) {
		this.thrdStgDurtnLs10 = thrdStgDurtnLs10.intValueExact();
	}
	public Integer getThrdStgDurtn10To29() {
		return thrdStgDurtn10To29;
	}
	public void setThrdStgDurtn10To29(BigDecimal thrdStgDurtn10To29) {
		this.thrdStgDurtn10To29 = thrdStgDurtn10To29.intValueExact();
	}
	public Integer getThrdStgDurtnGr30() {
		return thrdStgDurtnGr30;
	}
	public void setThrdStgDurtnGr30(BigDecimal thrdStgDurtnGr30) {
		this.thrdStgDurtnGr30 = thrdStgDurtnGr30.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("<10", thrdStgDurtnLs10);
		   map.put("10-29", thrdStgDurtn10To29);
		   map.put(">30", thrdStgDurtnGr30);
	return map;
	}
	



	

}
