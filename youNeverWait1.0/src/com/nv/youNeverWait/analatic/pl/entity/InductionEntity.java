package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.SubClusters;

public class InductionEntity {
	
	private String hospital;
	private String year;
	private Integer month;
	private Integer inducedLabourGr24HrVag;
	private Integer inducedLabourLs24HrVag;
	private Integer inducedLabourCs;
	private Integer electiveInductionGr24HrVag;
	private Integer electiveInductionLs24HrVag;
    private Integer electiveInductionCs;
    private Integer indicatedInductionGr24HrVag;
    private Integer indicatedInductionLs24HrVag;
    private Integer indicatedInductionCs;
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
	public Integer getInducedLabourGr24HrVag() {
		return inducedLabourGr24HrVag;
	}
	public void setInducedLabourGr24HrVag(BigDecimal inducedLabourGr24HrVag) {
		this.inducedLabourGr24HrVag = inducedLabourGr24HrVag.intValueExact();
	}
	public Integer getInducedLabourLs24HrVag() {
		return inducedLabourLs24HrVag;
	}
	public void setInducedLabourLs24HrVag(BigDecimal inducedLabourLs24HrVag) {
		this.inducedLabourLs24HrVag = inducedLabourLs24HrVag.intValueExact();
	}
	public Integer getInducedLabourCs() {
		return inducedLabourCs;
	}
	public void setInducedLabourCs(BigDecimal inducedLabourCs) {
		this.inducedLabourCs = inducedLabourCs.intValueExact();
	}
	public Integer getElectiveInductionGr24HrVag() {
		return electiveInductionGr24HrVag;
	}
	public void setElectiveInductionGr24HrVag(BigDecimal electiveInductionGr24HrVag) {
		this.electiveInductionGr24HrVag = electiveInductionGr24HrVag.intValueExact();
	}
	public Integer getElectiveInductionLs24HrVag() {
		return electiveInductionLs24HrVag;
	}
	public void setElectiveInductionLs24HrVag(BigDecimal electiveInductionLs24HrVag) {
		this.electiveInductionLs24HrVag = electiveInductionLs24HrVag.intValueExact();
	}
	public Integer getElectiveInductionCs() {
		return electiveInductionCs;
	}
	public void setElectiveInductionCs(BigDecimal electiveInductionCs) {
		this.electiveInductionCs = electiveInductionCs.intValueExact();
	}
	public Integer getIndicatedInductionGr24HrVag() {
		return indicatedInductionGr24HrVag;
	}
	public void setIndicatedInductionGr24HrVag(BigDecimal indicatedInductionGr24HrVag) {
		this.indicatedInductionGr24HrVag = indicatedInductionGr24HrVag.intValueExact();
	}
	public Integer getIndicatedInductionLs24HrVag() {
		return indicatedInductionLs24HrVag;
	}
	public void setIndicatedInductionLs24HrVag(BigDecimal indicatedInductionLs24HrVag) {
		this.indicatedInductionLs24HrVag = indicatedInductionLs24HrVag.intValueExact();
	}
	public Integer getIndicatedInductionCs() {
		return indicatedInductionCs;
	}
	public void setIndicatedInductionCs(BigDecimal indicatedInductionCs) {
		this.indicatedInductionCs = indicatedInductionCs.intValueExact();
	}
    
    
	public SubClusters getSubClusters(){


		final class RobsonSubclusters implements SubClusters {
			private String hospitl= hospital;
			private String yer=year;
			private Integer mnth=month;
			@Override
			public String getHospital() {

				return this.hospitl;
			}
			@Override
			public String getYear() {

				return this.yer;
			}
			@Override
			public Integer getMonth() {

				return this.mnth;
			}
			@Override
			public Map<String, Map<String, Integer>> getClusterMap() {
				Map<String,Map<String,Integer>> outerMap = new LinkedHashMap<String,Map<String,Integer>>();
				Map<String, Integer> innerMap = new LinkedHashMap<String,Integer>();
				innerMap.put("Vag  del <24hrs", electiveInductionGr24HrVag+indicatedInductionGr24HrVag);
				innerMap.put("Vag  del >24hrs", electiveInductionLs24HrVag+indicatedInductionLs24HrVag);
				innerMap.put("CS", inducedLabourCs);
				outerMap.put("Total induced", innerMap);
				
				innerMap = new LinkedHashMap<String,Integer>();
				innerMap.put("Vag  del <24hrs", electiveInductionGr24HrVag);
				innerMap.put("Vag  del >24hrs", electiveInductionLs24HrVag);
				innerMap.put("CS", electiveInductionCs);
				outerMap.put("Elective induction", innerMap);
				
				innerMap = new LinkedHashMap<String,Integer>();
				innerMap.put("Vag  del <24hrs", indicatedInductionGr24HrVag);
				innerMap.put("Vag  del >24hrs", indicatedInductionLs24HrVag);
				innerMap.put("CS", indicatedInductionCs);
				outerMap.put("Indicated induction", innerMap);
		
				return outerMap ;
			}
		}	



		return 	new  RobsonSubclusters();
	}
		
	

    
}
