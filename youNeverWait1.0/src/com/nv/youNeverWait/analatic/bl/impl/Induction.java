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
import com.nv.youNeverWait.analatic.pl.entity.InductionEntity;


public class Induction implements Cluster{
	
	private AnalaticDao analaticDao;
	private String name ="Induced Delivery";

	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear) {

		Inference inference = new Inference();
		inference.setCluster(name);

		List<InductionEntity> entityList= analaticDao.getInductionInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear));
		List<Measure> measures = getMeasures(entityList);
		inference.setEvaluations(measures);

		return inference;
	}

	@Override
	public Inference getInference(String fmonth, String fyear, String toMonth,
			String toYear, Integer hospital) {
		Inference inference = new Inference();
		inference.setCluster(name);

		List<InductionEntity> entityList= analaticDao.getInductionInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hospital);
		List<Measure> measures = getMeasures(entityList);
		inference.setEvaluations(measures);

		return inference;
	}

	
	
	private  List<Measure>  getMeasures(List<InductionEntity> data){
		List<Measure> measures = new ArrayList<Measure>();
		Measure measure = null;
		if ((data ==null) || data.size()==0)   return measures;
		for (InductionEntity entity :data){
			SubClusters subCluster =entity.getSubClusters();
			for( Entry<String, Map<String, Integer>> clusterMap :subCluster.getClusterMap().entrySet()){
				
				for ( Entry<String, Integer> onesubCluster:clusterMap.getValue().entrySet()){
					measure = new Measure();
					measure.setColumn(clusterMap.getKey());
					measure.setHospital(subCluster.getHospital());
					measure.setMonth(subCluster.getMonth());
					measure.setYear(entity.getYear());
					measure.setKey(onesubCluster.getKey());
					measure.setMeasure(onesubCluster.getValue());
					measures.add(measure);
				}

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
