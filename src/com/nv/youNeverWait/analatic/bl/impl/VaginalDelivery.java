package com.nv.youNeverWait.analatic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.Cluster;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
import com.nv.youNeverWait.analatic.pl.entity.VaginalDeliveryEntity;

public class VaginalDelivery implements Cluster {

	private AnalaticDao analaticDao;
	  private String name ="VaginalDelivery";
	    
	    @Override
		public Inference getInference( String fmonth, String fyear,
				String toMonth, String toYear) {
			
			 
		         Inference inference = new Inference();
		         inference.setCluster(name);
			     
				 List<VaginalDeliveryEntity> entityList= analaticDao.getVaginalDeliveryInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear));
			     List<Measure> measures = getMeasures(entityList);
			     inference.setEvaluations(measures);
	    
	      
			return inference;
		}
		
	    
	    
		@Override
		public Inference getInference(String fmonth, String fyear,
				String toMonth, String toYear, Integer hosptl) {
			
		     Inference inference = new Inference();
	         inference.setCluster(name);
		     
		     List<VaginalDeliveryEntity> entityList =analaticDao.getVaginalDeliveryInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hosptl);
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


	    private  List<Measure>  getMeasures(List<VaginalDeliveryEntity> data){
	    	List<Measure> measures = new ArrayList<Measure>();
	    	Measure measure;
	    	for (VaginalDeliveryEntity entity :data){
	    	     
	    		for(Map.Entry<String, Integer> entry :entity.getSubClusters().entrySet()){
	    		measure = new Measure();
	    	    measure.setKey(entry.getKey());
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
