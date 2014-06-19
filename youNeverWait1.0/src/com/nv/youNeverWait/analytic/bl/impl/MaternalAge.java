package com.nv.youNeverWait.analytic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analytic.bl.Cluster;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;
import com.nv.youNeverWait.analytic.pl.AnalyticDao;
import com.nv.youNeverWait.analytic.pl.entity.MaternalAgeEntity;

public class MaternalAge implements Cluster{
	
    private AnalyticDao analaticDao;
    private String name ="Maternal Age";
    private List<MaternalAgeEntity> maternalAgeEntity = new ArrayList<MaternalAgeEntity>();
    
    @Override
	public Inference getInference( String fmonth, String fyear,
			String toMonth, String toYear) {

    	     Inference inference = new Inference();
	         inference.setCluster(name);
			 List<MaternalAgeEntity> ageList= analaticDao.getMaternalAgeInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear));
		     List<Measure> measures = getMeasures(ageList);
		     inference.setEvaluations(measures);
    
      
		return inference;
	}
	
    
    
	@Override
	public Inference getInference(String fmonth, String fyear,
			String tomonth, String toyear, Integer hosptl) {
		
	     Inference inference = new Inference();
         inference.setCluster(name);
	     
	     List<MaternalAgeEntity> ageList =analaticDao.getMaternalAgeInferences(Integer.parseInt(fyear),Integer.parseInt(fmonth),Integer.parseInt(toyear),Integer.parseInt(tomonth),hosptl);
	     List<Measure> measures = getMeasures(ageList);
	     inference.setEvaluations(measures);
		
		
		return inference;
	}



	public AnalyticDao getAnalaticDao() {
		return analaticDao;
	}



	public void setAnalaticDao(AnalyticDao analaticDao) {
		this.analaticDao = analaticDao;
	}


    public List<Measure>  getMeasures(List<MaternalAgeEntity> ageData){
    	
    	List<Measure> measures = new ArrayList<Measure>();
    	
    	if ((ageData ==null) || ageData.size()==0)   return measures;

    	Measure measure;
    	for (MaternalAgeEntity ageEntity :ageData){
    	     
    		for(Map.Entry<String, Integer> entry :ageEntity.getSubClusters().entrySet()){
    		measure = new Measure();
    	    measure.setRow(entry.getKey());
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
