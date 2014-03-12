package com.nv.youNeverWait.analatic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.Cluster;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
import com.nv.youNeverWait.analatic.pl.entity.BookedStatisticsEntity;

public class BookedStatistics implements Cluster {


    
   
    
    private AnalaticDao analaticDao;
    private String name ="Booked( At least 1 Visit in each trimester)";
    
    @Override
	public Inference getInference( String fmonth, String fyear,
			String toMonth, String toYear) {
		
		 
	         Inference inference = new Inference();
	         inference.setCluster(name);
		     
			 List<BookedStatisticsEntity> entityList= analaticDao.getBookedStatInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),Integer.parseInt(toYear));
		     List<Measure> measures = getMeasures(entityList);
		     inference.setEvaluations(measures);
    
      
		return inference;
	}
	
    
    
	@Override
	public Inference getInference(String fmonth, String fyear,
			String toMonth, String toYear, Integer hosptl) {
		
	     Inference inference = new Inference();
         inference.setCluster(name);
	     
	     List<BookedStatisticsEntity> entityList =analaticDao.getBookedStatInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),Integer.parseInt(toYear),hosptl);
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


    private List<Measure>  getMeasures(List<BookedStatisticsEntity> ageData){
    	List<Measure> measures = new ArrayList<Measure>();
    	Measure measure;
    	if ((ageData ==null) || ageData.size()==0)   return measures;
    	
    	for (BookedStatisticsEntity ageEntity :ageData){
    	     
    		for(Map.Entry<String, Integer> entry :ageEntity.getSubClusters().entrySet()){
    		measure = new Measure();
    	    measure.setKey(entry.getKey());
    	    measure.setMeasure(entry.getValue());
    	    measure.setMonth(ageEntity.getMonth());
    	    measure.setYear(ageEntity.getYear());
    	    measure.setHospital(ageEntity.getHospital());
    	    measures.add(measure);
    		}
    		
    		
    	
    	}
    return measures;
    }


}
