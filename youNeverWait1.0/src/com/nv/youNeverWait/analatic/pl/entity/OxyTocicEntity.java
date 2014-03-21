package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class OxyTocicEntity {
	private String hospital;
	private String year;
	private Integer month;
	private Integer oxytoxinReciv;
	private Integer iV;
	private Integer iM;
	private Integer rectal;
	private Integer intra;
	
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
	public Integer getIV() {
		return iV;
	}
	public void setIV(BigDecimal iV) {
		this.iV = iV.intValueExact();
	}
	public Integer getIM() {
		return iM;
	}
	public void setIM(BigDecimal iM) {
	    this.iM = iM.intValueExact();
	}
	public Integer getRectal() {
		return rectal;
	}
	public void setRectal(BigDecimal rectal) {
		this.rectal = rectal.intValueExact();
	}
	public Integer getIntra() {
		return intra;
	}
	public void setIntra(BigDecimal intra) {
		this.intra = intra.intValueExact();
	}
	
	
	
	
public Integer getOxytoxinReciv() {
		return oxytoxinReciv;
	}
	public void setOxytoxinReciv(BigDecimal oxytoxinReciv) {
		this.oxytoxinReciv = oxytoxinReciv.intValueExact();
	}
public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("IV",iV );
		   map.put("IM", iM);
		   map.put("rectal", rectal);
		   map.put("intra", intra);
		return map;
	}
	


}
