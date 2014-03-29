package com.nv.youNeverWait.analytic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analytic.bl.Cluster;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;
import com.nv.youNeverWait.analytic.pl.AnalaticDao;
import com.nv.youNeverWait.analytic.pl.entity.MaternalHeightEntity;

public class MaternalHeight implements Cluster {


	
    	
  
    
   
    
    private AnalaticDao analaticDao;
    private String name ="Maternal Height";
    
    @Override
	public Inference getInference( String fmonth, String fyear,
			String toMonth, String toYear) {
		
		 
	         Inference inference = new Inference();
	         inference.setCluster(name);
		     
			 List<MaternalHeightEntity> entityList= analaticDao.getMaternalHeightInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),Integer.parseInt(toYear));
		     List<Measure> measures = getMeasures(entityList);
		     inference.setEvaluations(measures);
    
      
		return inference;
	}
	
    
    
	@Override
	public Inference getInference(String fmonth, String fyear,
			String toMonth, String toYear, Integer hosptl) {
		
	     Inference inference = new Inference();
         inference.setCluster(name);
	     
	     List<MaternalHeightEntity> entityList =analaticDao.getMaternalHeightInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),Integer.parseInt(toYear),hosptl);
	     List<Measure> measures = getMeasures(entityList);
	     inference.setEvaluations(measures);
		
		
		return inference;
	}



	public AnalaticDao getAnalaticDao() {
		return analaticDao;
	}



	public void setAnalaticDao(AnalaticDao analaticDao) {
		this.analaticDao = analaticDao;
	}


    private  List<Measure>  getMeasures(List<MaternalHeightEntity> data){
    	List<Measure> measures = new ArrayList<Measure>();
    	Measure measure;
    	if ((data ==null) || data.size()==0)   return measures;
    	
    	for (MaternalHeightEntity entity :data){
    	     
    		for(Map.Entry<String, Integer> entry :entity.getSubClusters().entrySet()){
    		measure = new Measure();
    	    measure.setRow(entry.getKey());
    	    measure.setMeasure(entry.getValue());
    	    measure.setMonth(entity.getMonth());
    	    measure.setYear(entity.getYear());
    	    measure.setHospital(entity.getHospital());
    	    measures.add(measure);
    		}
    		
    		
    	
    	}
    return measures;
    }


}
