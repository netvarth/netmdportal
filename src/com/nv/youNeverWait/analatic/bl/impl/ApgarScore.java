package com.nv.youNeverWait.analatic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.Cluster;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
import com.nv.youNeverWait.analatic.pl.entity.ApgarScoreEntity;
import com.nv.youNeverWait.analatic.pl.entity.BloodGroupEntity;

public class ApgarScore implements Cluster {

	private AnalaticDao analaticDao;
	  private String name ="Upgar Score";
	    
	    @Override
		public Inference getInference( String fmonth, String fyear,
				String toMonth, String toYear) {
			
			 
		         Inference inference = new Inference();
		        // inference.setCluster(name);
			     
				 //List<ApgarScoreEntity> entityList= analaticDao.getUpgarScoreInferences(fmonth,fyear,toMonth, toYear);
			     //List<Measure> measures = getMeasures(entityList);
			     //inference.setEvaluations(measures);
	    
	      
			return inference;
		}
		
	    
	    
		@Override
		public Inference getInference(String fmonth, String fyear,
				String toMonth, String toYear, Integer hosptl) {
			
		     Inference inference = new Inference();
	       //  inference.setCluster(name);
		     
		   //  List<ApgarScoreEntity> entityList =analaticDao.getUpgarScoreInferences(fmonth,fyear,toMonth, toYear,hosptl);
		   //  List<Measure> measures = getMeasures(entityList);
		   //  inference.setEvaluations(measures);
			
			
			return inference;
		}



		public AnalaticDao getAnalaticDao() {
			return analaticDao;
		}



		public void setAnalaticDao(AnalaticDao analaticDao) {
			this.analaticDao = analaticDao;
		}


	    private  List<Measure>  getMeasures(List<ApgarScoreEntity> data){
	    	List<Measure> measures = new ArrayList<Measure>();
	    	Measure measure;
	    	for (ApgarScoreEntity entity :data){
	    	     
	    		for(Map.Entry<String, Integer> entry :entity.getSubClusters().entrySet()){
	    		measure = new Measure();
	    	    measure.setKey(entry.getKey());
	    	    measure.setMeasure(entry.getValue());
	    	    measure.setMonth(entity.getMonth());
	    	    measure.setYear(entity.getYear());
	    	    measure.setHospital(entity.getHostpital());
	    	    measures.add(measure);
	    		}
	    		
	    		
	    	
	    	}
	    return measures;
	    }


	
	
	
	



}
