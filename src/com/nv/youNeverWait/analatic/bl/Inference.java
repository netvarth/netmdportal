package com.nv.youNeverWait.analatic.bl;

import java.util.List;

public class Inference<T> {
	
private String cluster ; 
 
private  List<T> evaluations ;

public String getCluster() {
	return cluster;
}

public void setCluster(String cluster) {
	this.cluster = cluster;
}

public List<T> getEvaluations() {
  return this.evaluations;	
}


public void setEvaluations(List<T> evaluations) {
	this.evaluations = evaluations;
}
}
