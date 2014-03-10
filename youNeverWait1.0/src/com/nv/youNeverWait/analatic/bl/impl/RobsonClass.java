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
import com.nv.youNeverWait.analatic.pl.entity.RobsonClassEntity;

public class RobsonClass implements Cluster {
	
	 private AnalaticDao analaticDao;
	 private String name ="Robson's Class";

	 @Override
		public Inference getInference( String fmonth, String fyear,
				String toMonth, String toYear) {
			
			 
		         Inference inference = new Inference();
		         inference.setCluster(name);
				 List<RobsonClassEntity> entityList= analaticDao.getRobsonClassInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear));
			     List<Measure> measures = getMeasures(entityList);
			     inference.setEvaluations(measures);
	    
	      
			return inference;
		}
		
	    
	    
		@Override
		public Inference getInference(String fmonth, String fyear,
				String toMonth, String toYear, Integer hosptl) {
			
		     Inference inference = new Inference();
	         inference.setCluster(name);
		     
		     List<RobsonClassEntity> entityList =analaticDao.getRobsonClassInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth), Integer.parseInt(toYear),hosptl);
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


		private  List<Measure>  getMeasures(List<RobsonClassEntity> data){
			List<Measure> measures = new ArrayList<Measure>();
			Measure measure = null;
			if ((data ==null) || data.size()==0)   return measures;
			for (RobsonClassEntity entity :data){
				SubClusters subCluster =entity.getSubClusters();
				for( Entry<String, Map<String, Integer>> clusterMap :subCluster.getClusterMap().entrySet()){
					
					for ( Entry<String, Integer> onesubCluster:clusterMap.getValue().entrySet()){
						measure = new Measure();
						measure.setColumn(clusterMap.getKey());
						measure.setHospital(subCluster.getHospital());
						measure.setMonth(subCluster.getHospital());
						measure.setYear(entity.getYear());
						measure.setKey(onesubCluster.getKey());
						measure.setMeasure(onesubCluster.getValue());
						measures.add(measure);
					}

				}	

			}
			return measures;
		}




}
