package com.nv.youNeverWait.analatic.pl.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PresentationEntity {

	private String hospital;
	private String year;
	private String month;
	private Integer vxoccAnt  ;
	private Integer vxoccPost;
	private Integer breechVag;
	private Integer transverse;
	private Integer face;
	private Integer others;
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
	public Integer getVxoccAnt() {
		return vxoccAnt;
	}
	public void setVxoccAnt(BigDecimal vxoccAnt) {
		this.vxoccAnt = vxoccAnt.intValueExact();
	}
	public Integer getVxoccPost() {
		return vxoccPost;
	}
	public void setVxoccPost(BigDecimal vxoccPost) {
		this.vxoccPost = vxoccPost.intValueExact();
	}
	public Integer getBreechVag() {
		return breechVag;
	}
	public void setBreechVag(BigDecimal breechVag) {
		this.breechVag = breechVag.intValueExact();
	}
	public Integer getTransverse() {
		return transverse;
	}
	public void setTransverse(BigDecimal transverse) {
		this.transverse = transverse.intValueExact();
	}
	public Integer getFace() {
		return face;
	}
	public void setFace(BigDecimal face) {
		this.face = face.intValueExact();
	}
	public Integer getOthers() {
		return others;
	}
	public void setOthers(BigDecimal  others) {
		this.others = others.intValueExact();
	}
	
	
	public Map<String,Integer> getSubClusters(){
		
		Map<String,Integer>map = new LinkedHashMap<String,Integer>();
		   map.put("Vx occ ant", vxoccAnt);
		   map.put("Vx occ post", vxoccPost);
		   map.put("breechVag", breechVag);
		   map.put("transverse", transverse);
		   map.put("face", face);
		   map.put("others", others);
		return map;
	}
	
	
	
	
}
