package com.nv.youNeverWait.analatic.bl;

import java.util.ArrayList;
import java.util.List;

public class Analatic {
	
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

 
 public List<Cluster> getClusters() {
	return clusters;
}

public void setClusters(List<Cluster> clusters) {
	this.clusters = clusters;
}
 
 
 
 
}
