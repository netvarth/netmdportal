package com.nv.youNeverWait.analytic.bl;

import java.util.Map;

public interface SubClusters {
	
	public String getHospital();
	public String getYear();
	public Integer getMonth();
	public Map<String,Map<String,Integer>> getClusterMap();

}
