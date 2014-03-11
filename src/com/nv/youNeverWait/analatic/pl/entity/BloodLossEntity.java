package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.SubClusters;

public class BloodLossEntity {
	
	private String hospital;
	private String year;
	private Integer month;
	private Integer bldLosLs500Cs;
	private Integer bldLosBtw500And1000Cs;
	private Integer bldLosGr1000Cs;
	private Integer bldLosLs500Vag;
	private Integer bldLosBtw500And1000Vag;
	private Integer bldLosGr1000Vag;
	private Integer bldLosLs500Total;
	private Integer bldLosBtw500And1000Total;
	private Integer bldLosGr1000Total;
	
	public Integer getBldLosLs500Cs() {
		return bldLosLs500Cs;
	}



	public void setBldLosLs500Cs(BigDecimal bldLosLs500Cs) {
		this.bldLosLs500Cs = bldLosLs500Cs.intValueExact();
	}



	public Integer getBldLosBtw500And1000Cs() {
		return bldLosBtw500And1000Cs;
	}



	public void setBldLosBtw500And1000Cs(BigDecimal bldLosBtw500And1000Cs) {
		this.bldLosBtw500And1000Cs = bldLosBtw500And1000Cs.intValueExact();
	}



	public Integer getBldLosGr1000Cs() {
		return bldLosGr1000Cs;
	}



	public void setBldLosGr1000Cs(BigDecimal bldLosGr1000Cs) {
		this.bldLosGr1000Cs = bldLosGr1000Cs.intValueExact();
	}



	public Integer getBldLosLs500Vag() {
		return bldLosLs500Vag;
	}



	public void setBldLosLs500Vag(BigDecimal bldLosLs500Vag) {
		this.bldLosLs500Vag = bldLosLs500Vag.intValueExact();
	}



	public Integer getBldLosBtw500And1000Vag() {
		return bldLosBtw500And1000Vag;
	}



	public void setBldLosBtw500And1000Vag(BigDecimal bldLosBtw500And1000Vag) {
		this.bldLosBtw500And1000Vag = bldLosBtw500And1000Vag.intValueExact();
	}



	public Integer getBldLosGr1000Vag() {
		return bldLosGr1000Vag;
	}



	public void setBldLosGr1000Vag(BigDecimal bldLosGr1000Vag) {
		this.bldLosGr1000Vag = bldLosGr1000Vag.intValueExact();
	}



	public Integer getBldLosLs500Total() {
		return bldLosLs500Total;
	}



	public void setBldLosLs500Total(BigDecimal bldLosLs500Total) {
		this.bldLosLs500Total = bldLosLs500Total.intValueExact();
	}



	public Integer getBldLosBtw500And1000Total() {
		return bldLosBtw500And1000Total;
	}



	public void setBldLosBtw500And1000Total(BigDecimal bldLosBtw500And1000Total) {
		this.bldLosBtw500And1000Total = bldLosBtw500And1000Total.intValueExact();
	}



	public Integer getBldLosGr1000Total() {
		return bldLosGr1000Total;
	}



	public void setBldLosGr1000Total(BigDecimal bldLosGr1000Total) {
		this.bldLosGr1000Total = bldLosGr1000Total.intValueExact();
	}



	

	
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



	public SubClusters getSubClusters(){


		final class BloodLossSubclusters implements SubClusters {
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
				innerMap.put("<500",bldLosLs500Vag );
				innerMap.put("500-999", bldLosBtw500And1000Vag);
				innerMap.put(">1000", bldLosGr1000Vag);
				outerMap.put("Vaginal Delivery", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("<500",bldLosLs500Cs );
				innerMap.put("500-999", bldLosBtw500And1000Cs);
				innerMap.put(">1000", bldLosGr1000Cs);
				outerMap.put("CS", innerMap);
				
			
				return outerMap ;
			}
		}	



		return 	new  BloodLossSubclusters();
	}
		
	
	
}
