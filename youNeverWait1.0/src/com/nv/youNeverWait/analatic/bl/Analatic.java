package com.nv.youNeverWait.analatic.bl;

import java.util.ArrayList;
import java.util.List;



public class Analatic {
	
private List<Cluster>clusters = new ArrayList<Cluster>();



public List<Inference<? extends Measure>> getInferences(String fmonth, String fyear, String toMonth, String toYear){
	 List<Inference<? extends Measure>>inferences = new ArrayList<Inference<? extends Measure>>();
	 Inference<Measure> inference ;
	 for (Cluster cluster:clusters){
	     inference = cluster.getInference(fmonth, fyear, toMonth, toYear);
	     inferences.add(inference);
	 }
	 
 return inferences;
 }

 public List<Inference<? extends Measure>> getInferencesPerHospital(String fmonth, String fyear, String toMonth, String toYear,Integer hospital){
	 
	 List<Inference<? extends Measure>>inferences = new ArrayList<Inference<? extends Measure>>();
	 Inference<Measure> inference ;
	 for (Cluster cluster:clusters){
	     inference = cluster.getInference(fmonth, fyear, toMonth, toYear,hospital);
	     inferences.add(inference);
	 }
	 
 return inferences;
 }

public  List<Inference<? extends Measure>> getAggregatedInferences(String fmonth, String fyear, String toMonth, String toYear){
	
	Inference<Measure> inference=null ;
	 List<AggregateMeasure>aggregatedMeasures=new ArrayList<AggregateMeasure>();
	 List<Measure> measures=null;
	 for (Cluster cluster:clusters){
		 inference = cluster.getInference(fmonth, fyear, toMonth, toYear);
		 measures = inference.getEvaluations();
		 for (Measure measure:measures){
			 aggregatedMeasures.add(new AggregateMeasure(inference.getCluster(), measure));
		 }
	 }
	 Inference<AggregateMeasure> aginference= new Inference<AggregateMeasure>();
	 aginference.setCluster("");
	 aginference.setEvaluations(aggregatedMeasures);
	 List<Inference<? extends Measure>> inferenceList = new ArrayList<Inference<? extends Measure>>();
	 inferenceList.add(aginference);
return inferenceList;
}

 
 
 
public List<Inference<? extends Measure>> getAggregatedInferencesPerHospital(String fmonth, String fyear, String toMonth, String toYear,Integer hospital){
	
	Inference<Measure> inference=null ;
	 List<AggregateMeasure>aggregatedMeasures=new ArrayList<AggregateMeasure>();
	 List<Measure> measures=null;
	 for (Cluster cluster:clusters){
		 inference = cluster.getInference(fmonth, fyear, toMonth, toYear,hospital);
		 measures = inference.getEvaluations();
		 for (Measure measure:measures){
			 aggregatedMeasures.add(new AggregateMeasure(inference.getCluster(), measure));
		 }
	 }
	 Inference<AggregateMeasure> aginference= new Inference<AggregateMeasure>();
	 aginference.setCluster("");
	 aginference.setEvaluations(aggregatedMeasures);
	 List<Inference<? extends Measure>> inferenceList = new ArrayList<Inference<? extends Measure>>();
	 inferenceList.add(aginference);
return inferenceList;
}

 
 
 public List<Cluster> getClusters() {
	return clusters;
}

public void setClusters(List<Cluster> clusters) {
	this.clusters = clusters;
}
 
 
 
 
}
