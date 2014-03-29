package com.nv.youNeverWait.analytic.bl;

import java.util.ArrayList;
import java.util.List;



public class Analytic implements Runnable {
	
private List<Cluster>clusters = new ArrayList<Cluster>();



public List<Inference> getInferences(String fmonth, String fyear, String toMonth, String toYear){
	 List<Inference>inferences = new ArrayList<Inference>();
	 Inference inference ;
	 for (Cluster cluster:clusters){
	     inference = cluster.getInference(fmonth, fyear, toMonth, toYear);
	     inferences.add(inference);
	 }
	 
 return inferences;
 }

 public List<Inference> getInferencesPerHospital(String fmonth, String fyear, String toMonth, String toYear,Integer hospital){
	 
	 List<Inference>inferences = new ArrayList<Inference>();
	 Inference inference ;
	 for (Cluster cluster:clusters){
	     inference = cluster.getInference(fmonth, fyear, toMonth, toYear,hospital);
	     inferences.add(inference);
	 }
	 
 return inferences;
 }

public  List<Inference> getAggregatedInferences(String fmonth, String fyear, String toMonth, String toYear){
	
	Inference inference=null ;
	 List<AggregateMeasure>aggregatedMeasures=new ArrayList<AggregateMeasure>();
	 List<? extends Measure> measures=null;
	 for (Cluster cluster:clusters){
		 inference = cluster.getInference(fmonth, fyear, toMonth, toYear);
		 measures = inference.getEvaluations();
		 for (Measure measure:measures){
			 aggregatedMeasures.add(new AggregateMeasure(inference.getCluster(), measure));
		 }
	 }
	 Inference aginference= new Inference();
	 aginference.setCluster("Aggregated Inference");
	 aginference.setEvaluations(aggregatedMeasures);
	 List<Inference> inferenceList = new ArrayList<Inference>();
	 inferenceList.add(aginference);
return inferenceList;
}

 
 
 
public List<Inference> getAggregatedInferencesPerHospital(String fmonth, String fyear, String toMonth, String toYear,Integer hospital){
	
	Inference inference=null ;
	 List<AggregateMeasure>aggregatedMeasures=new ArrayList<AggregateMeasure>();
	 List<? extends Measure> measures=null;
	 for (Cluster cluster:clusters){
		 inference = cluster.getInference(fmonth, fyear, toMonth, toYear,hospital);
		 measures = inference.getEvaluations();
		 for (Measure measure:measures){
			 aggregatedMeasures.add(new AggregateMeasure(inference.getCluster(), measure));
		 }
	 }
	 Inference aginference= new Inference();
	 aginference.setCluster("");
	 aginference.setEvaluations(aggregatedMeasures);
	 List<Inference> inferenceList = new ArrayList<Inference>();
	 inferenceList.add(aginference);
return inferenceList;
}

 
 
 public List<Cluster> getClusters() {
	return clusters;
}

public void setClusters(List<Cluster> clusters) {
	this.clusters = clusters;
}

@Override
public void run() {
	while (true){
		for (Cluster cluster:clusters){
			
		    	
		}
		
		
	}
	
}
 
 
 
 
}
