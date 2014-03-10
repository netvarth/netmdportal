package com.nv.youNeverWait.analatic.bl;

import java.util.Map;

public interface SubClusters {
	
	public String getHospital();
	public String getYear();
	public String getMonth();
	public Map<String,Map<String,Integer>> getClusterMap();

}
