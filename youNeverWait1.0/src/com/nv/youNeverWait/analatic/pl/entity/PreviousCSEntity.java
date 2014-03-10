package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.SubClusters;

public class PreviousCSEntity {
	private String hospital;
	private String year;
	private String month;
	
	
	private Integer same1;
	private Integer same2;
	private Integer same3;
	private Integer samegt3;
	
	private Integer other1;
	private Integer other2;
	private Integer other3;
	private Integer othergt3;
	
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
	
	
	
	public Integer getSame1() {
		return same1;
	}
	public void setSame1(BigDecimal same1) {
		this.same1 = same1.intValueExact();
	}
	public Integer getSame2() {
		return same2;
	}
	public void setSame2(BigDecimal same2) {
		this.same2 = same2.intValueExact();
	}
	public Integer getSame3() {
		return same3;
	}
	public void setSame3(BigDecimal same3) {
		this.same3 = same3.intValueExact();
	}
	public Integer getSamegt3() {
		return samegt3;
	}
	public void setSamegt3(BigDecimal samegt3) {
		this.samegt3 = samegt3.intValueExact();
	}
	public Integer getOther1() {
		return other1;
	}
	public void setOther1(BigDecimal other1) {
		this.other1 = other1.intValueExact();
	}
	public Integer getOther2() {
		return other2;
	}
	public void setOther2(BigDecimal other2) {
		this.other2 = other2.intValueExact();
	}
	public Integer getOther3() {
		return other3;
	}
	public void setOther3(BigDecimal other3) {
		this.other3 = other3.intValueExact();
	}
	public Integer getOthergt3() {
		return othergt3;
	}
	public void setOthergt3(BigDecimal othergt3) {
		
		this.othergt3 = othergt3.intValueExact();
	}
	
	public SubClusters getSubClusters(){
		
		final class PreviousCSsubclusters implements SubClusters {
			private String hospitl=hospital;
			private String yer=year;
			private String mnth=month;
			@Override
			public String getHospital() {
				
				return this.hospitl;
			}

			@Override
			public String getYear() {
				
				return this.yer;
			}

			@Override
			public String getMonth() {
				
				return this.mnth;
			}

			@Override
			public Map<String,Map<String,Integer>> getClusterMap() {
				Map<String,Map<String,Integer>> outerMap = new HashMap<String,Map<String,Integer>>();
				
				Map<String, Integer> innerMap = new HashMap<String,Integer>();
				innerMap.put("One CS",same1);
				innerMap.put("Two CS",same2);
				innerMap.put("Three CS",same3);
				innerMap.put("> Three CS",samegt3);
				outerMap.put("Same Hospital",innerMap );
				innerMap.put("One CS",other1);
				innerMap.put("Two CS",other2);
				innerMap.put("Three CS",other3);
				innerMap.put("> Three CS",othergt3);
				outerMap.put("Other Hospital",innerMap );
				
				return outerMap;
			}
			
			
		}
		return new PreviousCSsubclusters();
	}
	

	
	

}