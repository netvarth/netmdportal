package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class VaginalDeliveryEntity {
	private String hospital;
	private String year;
	private Integer month;
	private Integer tVaginal;
	private Integer spontaneous;
	private Integer forceps;
	private Integer vacum;
	private Integer breech;
	private Integer vbac;
    private Integer mp2;
    private Integer mpgt2;
    
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
	public Integer gettVaginal() {
		return tVaginal;
	}
	public void settVaginal(BigDecimal tVaginal) {
		this.tVaginal = tVaginal.intValueExact();
	}
	public Integer getSpontaneous() {
		return spontaneous;
	}
	public void setSpontaneous(BigDecimal spontaneous) {
		this.spontaneous = spontaneous.intValueExact();
	}
	public Integer getForceps() {
		return forceps;
	}
	public void setForceps(BigDecimal forceps) {
		this.forceps = forceps.intValueExact();
	}
	public Integer getVacum() {
		return vacum;
	}
	public void setVacum(BigDecimal vacum) {
		this.vacum = vacum.intValueExact();
	}
	public Integer getBreech() {
		return breech;
	}
	public void setBreech(BigDecimal breech) {
		this.breech = breech.intValueExact();
	}
	public Integer getVbac() {
		return vbac;
	}
	public void setVbac(BigDecimal vbac) {
		this.vbac = vbac.intValueExact();
	}
	public Integer getMp2() {
		return mp2;
	}
	public void setMp2(BigDecimal mp2) {
		this.mp2 = mp2.intValueExact();
	}
	public Integer getMpgt2() {
		return mpgt2;
	}
	public void setMpgt2(BigDecimal mpgt2) {
		this.mpgt2 = mpgt2.intValueExact();
	}
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Total", tVaginal);
		   map.put("Spontaneous", spontaneous);
		   map.put("Forceps", forceps);
		   map.put("Vacum",  vacum);
		   map.put("Breech Vaginal Delivery", breech );
		   map.put("VBAC", vbac);
		   map.put("Mulptple Pregnancy 2", mp2);
		   map.put("Mulptple Pregnancy >2",  mpgt2);
		return map;
	}
	

	
	

}
