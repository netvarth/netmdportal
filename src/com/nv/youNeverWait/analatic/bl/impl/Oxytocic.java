package com.nv.youNeverWait.analatic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nv.youNeverWait.analatic.bl.Cluster;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.analatic.bl.SubClusters;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
import com.nv.youNeverWait.analatic.pl.entity.FourthStageEntity;
import com.nv.youNeverWait.analatic.pl.entity.InductionEntity;
import com.nv.youNeverWait.analatic.pl.entity.OxyTocicEntity;

public class Oxytocic implements Cluster {

	private AnalaticDao analaticDao;
	private String name ="Oxytocic Route";

	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear) {

		Inference inference = new Inference();
		inference.setCluster(name);

		List<OxyTocicEntity> entityList= analaticDao.getOxyTocicInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear));
		List<Measure> measures = getMeasures(entityList);
		inference.setEvaluations(measures);
		return inference;
	}

	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear, Integer hospital) {
		Inference inference = new Inference();
		inference.setCluster(name);

		List<OxyTocicEntity> entityList= analaticDao.getOxyTocicInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hospital);
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

	 private  List<Measure>  getMeasures(List<OxyTocicEntity> data){
	    	List<Measure> measures = new ArrayList<Measure>();
	    	Measure measure;
	    	for (OxyTocicEntity entity :data){
	    	     
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
