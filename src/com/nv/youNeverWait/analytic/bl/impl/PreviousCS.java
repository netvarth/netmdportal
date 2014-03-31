package com.nv.youNeverWait.analytic.bl.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nv.youNeverWait.analytic.bl.Cluster;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;
import com.nv.youNeverWait.analytic.bl.SubClusters;
import com.nv.youNeverWait.analytic.pl.AnalyticDao;
import com.nv.youNeverWait.analytic.pl.entity.PreviousCSEntity;


public class PreviousCS implements Cluster{

	private AnalyticDao analaticDao;
	  private String name ="Previous Caesereans";
	    
	    @Override
		public Inference getInference( String fmonth, String fyear,
				String toMonth, String toYear) {
			
			 
		         Inference inference = new Inference();
		         inference.setCluster(name);
			     
				 List<PreviousCSEntity> entityList= analaticDao.getPrevCsInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),Integer.parseInt(toYear));
			     List<Measure> measures = getMeasures(entityList);
			     inference.setEvaluations(measures);
	    
	      
			return inference;
		}
		
	    
	    
		@Override
		public Inference getInference(String fmonth, String fyear,
				String toMonth, String toYear, Integer hosptl) {
			
		     Inference inference = new Inference();
	         inference.setCluster(name);
		     
		     List<PreviousCSEntity> entityList =analaticDao.getPrevCsInferences(Integer.parseInt(fmonth),Integer.parseInt(fyear),Integer.parseInt(toMonth),hosptl);
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


	    private  List<Measure>  getMeasures(List<PreviousCSEntity> data){
	    	List<Measure> measures = new ArrayList<Measure>();
	    	Measure measure;
            int typeOrder=0;
	    	for (PreviousCSEntity entity :data){
				SubClusters subCluster =entity.getSubClusters();
				for( Entry<String, Map<String, Integer>> clusterMap :subCluster.getClusterMap().entrySet()){
					 typeOrder=1;
					for ( Entry<String, Integer> onesubCluster:clusterMap.getValue().entrySet()){
						measure = new Measure();
						measure.setTypeOrder(typeOrder);
						measure.setRow(clusterMap.getKey() );
						measure.setHospital(subCluster.getHospital());
						measure.setMonth(subCluster.getMonth());
						measure.setYear(entity.getYear());
						measure.setColumn(onesubCluster.getKey());
						measure.setMeasure(onesubCluster.getValue());
						measures.add(measure);
					}
                    typeOrder++;
				}	

			}
	    return measures;
	    }


	
	
}