package com.nv.youNeverWait.analytic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analytic.bl.Cluster;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;
import com.nv.youNeverWait.analytic.pl.AnalaticDao;
import com.nv.youNeverWait.analytic.pl.entity.EpisiotomyEntity;
import com.nv.youNeverWait.analytic.pl.entity.IntravenusFluidEntity;

public class IntravenusFluid implements Cluster {
	private AnalaticDao analaticDao;
	private String name ="Intravenus Fluid Used";
	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear) {
	     Inference inference = new Inference();
         inference.setCluster(name);
	     
		 List<IntravenusFluidEntity> entityList= analaticDao.getIvFluidInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear));
	     List<Measure> measures = getMeasures(entityList);
	     inference.setEvaluations(measures);

  
	return inference;
	}

	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear, Integer hospital) {
		Inference inference = new Inference();
        inference.setCluster(name);
	     
		 List<IntravenusFluidEntity> entityList= analaticDao.getIvFluidInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hospital);
	     List<Measure> measures = getMeasures(entityList);
	     inference.setEvaluations(measures);
	     
	return inference;
	}


	  private  List<Measure>  getMeasures(List<IntravenusFluidEntity> data){
	    	List<Measure> measures = new ArrayList<Measure>();
	    	Measure measure;
	    	for (IntravenusFluidEntity entity :data){
	    	     
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
