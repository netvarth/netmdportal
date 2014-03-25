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
	    	Measure measure;
            int typeOrder=0;
	    	for (RobsonClassEntity entity :data){
				SubClusters subCluster =entity.getSubClusters();
				for( Entry<String, Map<String, Integer>> clusterMap :subCluster.getClusterMap().entrySet()){
					 typeOrder=1;
					for ( Entry<String, Integer> onesubCluster:clusterMap.getValue().entrySet()){
						measure = new Measure();
						measure.setTypeOrder(typeOrder);
						measure.setColumn(onesubCluster.getKey());
						measure.setHospital(subCluster.getHospital());
						measure.setMonth(subCluster.getMonth());
						measure.setYear(entity.getYear());
						measure.setRow(clusterMap.getKey() );
						measure.setMeasure(onesubCluster.getValue());
						measures.add(measure);
					}
                    typeOrder++;
				}	

			}
	    return measures;
	    }





}
