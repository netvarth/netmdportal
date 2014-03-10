package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.SubClusters;

public class RobsonClassEntity {
	private String hospital;
	private String year;
	private String month;
	private Integer total1;
	private Integer total2e;
	private Integer total2i;
	private Integer total3;
	private Integer total4e;
	private Integer total4i;
	private Integer total5;
	private Integer total6;
	private Integer total7;
	private Integer total8;
	private Integer total9;
	private Integer total10;
	private Integer cs1;
	private Integer cs2e;
	private Integer cs2i;
	private Integer cs3;
	private Integer cs4e;
	private Integer cs4i;
	private Integer cs5;
	private Integer cs6;
	private Integer cs7;
	private Integer cs8;
	private Integer cs9;
	private Integer cs10;
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
	public Integer getTotal1() {
		return total1;
	}
	public void setTotal1(BigDecimal total1) {
		this.total1 = total1.intValueExact();
	}
	
	public Integer getTotal2e() {
		return total2e;
	}
	public void setTotal2e(BigDecimal total2e) {
		this.total2e = total2e.intValueExact();
	}
	
	public Integer getTotal2i() {
		return total2i;
	}
	public void setTotal2i(BigDecimal total2i) {
		this.total2e = total2i.intValueExact();
	}
	
	public Integer getTotal3() {
		return total3;
	}
	public void setTotal3(BigDecimal total3) {
		this.total3 = total3.intValueExact();
	}
	
	
	public Integer getTotal4e() {
		return total2e;
	}
	public void setTotal4e(BigDecimal total4e) {
		this.total2e = total4e.intValueExact();
	}
	
	public Integer getTotal4i() {
		return total4i;
	}
	public void setTotal4i(BigDecimal total4i) {
		this.total4e = total4i.intValueExact();
	}

	public Integer getTotal5() {
		return total5;
	}
	public void setTotal5(BigDecimal total5) {
		this.total5 = total5.intValueExact();
	}
	public Integer getCs1() {
		return cs1;
	}
	public void setCs1(BigDecimal cs1) {
		this.cs1 = cs1.intValueExact();
	}
	public Integer getCs2e() {
		return cs2e;
	}
	public void setCs2e(BigDecimal cs2e) {
		this.cs2e = cs2e.intValueExact();
	}
	
	public Integer getCs2i() {
		return cs2i;
	}
	public void setCs2i(BigDecimal cs2i) {
		this.cs2e = cs2i.intValueExact();
	}
	public Integer getCs3() {
		return cs3;
	}
	public void setCs3(BigDecimal cs3) {
		this.cs3 = cs3.intValueExact();
	}
	public Integer getCs4e() {
		return cs4e;
	}
	public void setCs4e(BigDecimal cs4e) {
		this.cs4e = cs4e.intValueExact();
	}

	public Integer getCs4i() {
		return cs4i;
	}

	public void setCs4i(BigDecimal cs4i) {
		this.cs4e = cs4i.intValueExact();
	}
	

	public Integer getCs5() {
		return cs5;
	}
	public void setCs5(BigDecimal cs5) {
		this.cs5 = cs5.intValueExact();
	}
	
	public Integer getTotal6() {
		return total6;
	}
	public void setTotal6(BigDecimal total6) {
		this.total6 = total6.intValueExact();
	}
	public Integer getTotal7() {
		return total7;
	}
	public void setTotal7(BigDecimal total7) {
		this.total7 = total7.intValueExact();
	}
	public Integer getTotal8() {
		return total8;
	}
	public void setTotal8(BigDecimal total8) {
		this.total8 = total8.intValueExact();
	}
	public Integer getTotal9() {
		return total9;
	}
	public void setTotal9(BigDecimal total9) {
		this.total9 = total9.intValueExact();
	}
	public Integer getTotal10() {
		return total10;
	}
	public void setTotal10(BigDecimal total10) {
		this.total10 = total10.intValueExact();
	}
	public Integer getCs6() {
		return cs6;
	}
	public void setCs6(BigDecimal cs6) {
		this.cs6 = cs6.intValueExact();
	}
	public Integer getCs7() {
		return cs7;
	}
	public void setCs7(BigDecimal cs7) {
		this.cs7 = cs7.intValueExact();
	}
	public Integer getCs8() {
		return cs8;
	}
	public void setCs8(BigDecimal cs8) {
		this.cs8 = cs8.intValueExact();
	}
	public Integer getCs9() {
		return cs9;
	}
	public void setCs9(BigDecimal cs9) {
		this.cs9 = cs9.intValueExact();
	}
	public Integer getCs10() {
		return cs10;
	}
	
	
	
	
	
	public void setCs10(BigDecimal cs10) {
		this.cs10 = cs10.intValueExact();
	}
	
	
	
	
	
	public SubClusters getSubClusters(){


		final class RobsonSubclusters implements SubClusters {
			private String hospitl= hospital;
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
			public Map<String, Map<String, Integer>> getClusterMap() {
				Map<String,Map<String,Integer>> outerMap = new HashMap<String,Map<String,Integer>>();
				Map<String, Integer> innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total1);
				innerMap.put("CS", cs1);
				outerMap.put("1", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total2e);
				innerMap.put("CS", cs2e);
				outerMap.put("2e", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total2i);
				innerMap.put("CS", cs2i);
				outerMap.put("2i", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total3);
				innerMap.put("CS", cs3);
				outerMap.put("3", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total4e);
				innerMap.put("CS", cs4e);
				outerMap.put("4e", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total4i);
				innerMap.put("CS", cs4i);
				outerMap.put("4i", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total5);
				innerMap.put("CS", cs5);
				outerMap.put("5", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total6);
				innerMap.put("CS", cs6);
				outerMap.put("6", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total7);
				innerMap.put("CS", cs7);
				outerMap.put("7", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total8);
				innerMap.put("CS", cs8);
				outerMap.put("8", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total9);
				innerMap.put("CS", cs9);
				outerMap.put("9", innerMap);
				innerMap = new HashMap<String,Integer>();
				innerMap.put("Total", total10);
				innerMap.put("CS", cs10);
				outerMap.put("10", innerMap);

				return outerMap ;
			}
		}	



		return 	new  RobsonSubclusters();
	}
		
	
		
		
		
	
	
}
