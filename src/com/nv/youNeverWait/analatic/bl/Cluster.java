package com.nv.youNeverWait.analatic.bl;




public interface Cluster {
	
public Inference<Measure> getInference( String fmonth,String  fyear,String toMonth, String toYear); 	

public Inference<Measure> getInference( String fmonth,String  fyear,String toMonth, String toYear,Integer hospital); 	


}
