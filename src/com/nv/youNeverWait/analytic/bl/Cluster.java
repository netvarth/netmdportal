package com.nv.youNeverWait.analytic.bl;




public interface Cluster {
	
public Inference getInference( String fmonth,String  fyear,String toMonth, String toYear); 	

public Inference getInference( String fmonth,String  fyear,String toMonth, String toYear,Integer hospital); 	


}
