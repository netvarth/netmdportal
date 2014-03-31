package com.nv.youNeverWait.analytic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;





import com.nv.youNeverWait.analytic.bl.Cluster;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;
import com.nv.youNeverWait.analytic.pl.AnalyticDao;
import com.nv.youNeverWait.analytic.pl.entity.OxyTocicEntity;

public class Oxytocic implements Cluster {

	private AnalyticDao analaticDao;
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

	
	

	public AnalyticDao getAnalaticDao() {
		return analaticDao;
	}

	public void setAnalaticDao(AnalyticDao analaticDao) {
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
