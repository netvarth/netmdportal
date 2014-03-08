package com.nv.youNeverWait.analatic.pl.entity;

import java.util.HashMap;
import java.util.Map;

public class ApgarScoreEntity {
	
	 
     private String hostpital;
     private String year;
     private String month;
 	 private Integer oneAtFive;
	 private Integer fiveAt5;
	 private Integer stillBirth; 
	 private Integer neonatalDeath;
	 private Integer nICUAdmission;
	 private Integer nComplications;
	 
	 
	 
	public Integer getOneAtFive() {
		return oneAtFive;
	}
	public void setOneAtFive(Integer oneAtFive) {
		this.oneAtFive = oneAtFive;
	}
	public Integer getFiveAt5() {
		return fiveAt5;
	}
	public void setFiveAt5(Integer fiveAt5) {
		this.fiveAt5 = fiveAt5;
	}
	public Integer getStillBirth() {
		return stillBirth;
	}
	public void setStillBirth(Integer stillBirth) {
		this.stillBirth = stillBirth;
	}
	public Integer getNeonatalDeath() {
		return neonatalDeath;
	}
	public void setNeonatalDeath(Integer neonatalDeath) {
		this.neonatalDeath = neonatalDeath;
	}
	
	
	
	public Integer getnICUAdmission() {
		return nICUAdmission;
	}
	public void setnICUAdmission(Integer nICUAdmission) {
		this.nICUAdmission = nICUAdmission;
	}
	public Integer getnComplications() {
		return nComplications;
	}
	public void setnComplications(Integer nComplications) {
		this.nComplications = nComplications;
	}
	public String getHostpital() {
		return hostpital;
	}
	public void setHostpital(String hostpital) {
		this.hostpital = hostpital;
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
	 
	 
public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new HashMap<String,Integer>();
		   map.put("1 at 5'", oneAtFive);
		   map.put("5 at 5'", fiveAt5);
		   map.put("Still Birth", stillBirth);
		   map.put("Neonatal Death", neonatalDeath);
		   map.put("Neonatal ICU Admissions", nICUAdmission);
		   map.put("Neonatal Complications", nComplications );
		return map;
	} 
	
	
}
