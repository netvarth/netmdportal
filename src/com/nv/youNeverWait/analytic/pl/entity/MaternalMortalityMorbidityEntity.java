package com.nv.youNeverWait.analytic.pl.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analytic.bl.Measure;

public class MaternalMortalityMorbidityEntity {
	
	
	     private String hospital;
	     private String year;
	     private Integer month;
	 	 private Integer maternalDth;
		 private Integer matMorbidility ;
		 
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
		public Integer getMaternalDth() {
			return maternalDth;
		}
		public void setMaternalDth(BigDecimal maternalDth) {
			this.maternalDth = maternalDth.intValueExact();
		}
		public Integer getMatMorbidility() {
			return matMorbidility;
		}
		public void setMatMorbidility(BigDecimal matMorbidility) {
			this.matMorbidility = matMorbidility.intValueExact();
		}
		 
		  
		public Map<String,Integer> getSubClusters(){
			
			Map<String,Integer>map = new LinkedHashMap<String,Integer>();
			   map.put("Mortality", maternalDth);
			   map.put("Morbidity", matMorbidility);
			return map;
		} 
		
		
		 

}
