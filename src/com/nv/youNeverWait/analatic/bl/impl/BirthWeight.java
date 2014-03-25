package com.nv.youNeverWait.analatic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analatic.bl.Cluster;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
import com.nv.youNeverWait.analatic.pl.entity.BirthWeightEntity;


public class BirthWeight implements Cluster {

	private AnalaticDao analaticDao;
	private String name ="Birth Weight";
	
	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear) {
	     Inference inference = new Inference();
         inference.setCluster(name);
	     
		 List<BirthWeightEntity> entityList= analaticDao.getBirthWeightInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear));
	     List<Measure> measures = getMeasures(entityList);
	     inference.setEvaluations(measures);

  
	return inference;
	}

	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear, Integer hospital) {
		Inference inference = new Inference();
        inference.setCluster(name);
	     
		 List<BirthWeightEntity> entityList= analaticDao.getBirthWeightInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hospital);
	     List<Measure> measures = getMeasures(entityList);
	     inference.setEvaluations(measures);
	     
	return inference;
	}


	  private  List<Measure>  getMeasures(List<BirthWeightEntity> data){
	    	List<Measure> measures = new ArrayList<Measure>();
	    	Measure measure;
	    	for (BirthWeightEntity entity :data){
	    	     
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

	public AnalaticDao getAnalaticDao() {
		return analaticDao;
	}

	public void setAnalaticDao(AnalaticDao analaticDao) {
		this.analaticDao = analaticDao;
	}




}
