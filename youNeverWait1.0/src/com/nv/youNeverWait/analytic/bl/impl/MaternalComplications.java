package com.nv.youNeverWait.analytic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nv.youNeverWait.analytic.bl.Cluster;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;
import com.nv.youNeverWait.analytic.pl.AnalyticDao;
import com.nv.youNeverWait.analytic.pl.entity.MaternalComplicationsEntity;


public class MaternalComplications implements Cluster{

	private AnalyticDao analaticDao;
    private String name ="Maternal Complications";
    
    @Override
	public Inference getInference( String fmonth, String fyear,
			 String toMonth, String toYear) {
	         Inference inference = new Inference();
	         inference.setCluster(name);
			 List<MaternalComplicationsEntity> entityList= analaticDao.getMaternalComplicationsInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),Integer.parseInt(toYear));
		     List<Measure> measures = getMeasures(entityList);
		     inference.setEvaluations(measures);
		return inference;
	}
	
    
    
	@Override
	public Inference getInference(String fmonth, String fyear,
			String toMonth, String toYear, Integer hosptl) {
		
	     Inference inference = new Inference();
         inference.setCluster(name);
	     List<MaternalComplicationsEntity> entityList =analaticDao.getMaternalComplicationsInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),Integer.parseInt(toYear),hosptl);
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


    private  List<Measure>  getMeasures(List<MaternalComplicationsEntity> data){
    	List<Measure> measures = new ArrayList<Measure>();
    	Measure measure;
    	if ((data ==null) || data.size()==0)   return measures;
    	for (MaternalComplicationsEntity entity :data){
    	     
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
