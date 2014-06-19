package com.nv.youNeverWait.analytic.bl;

import java.util.List;

public class Inference {
	
private String cluster ; 
 
private  List<? extends Measure> evaluations ;

public String getCluster() {
	return cluster;
}

public void setCluster(String cluster) {
	this.cluster = cluster;
}

public List<? extends Measure> getEvaluations() {
	return evaluations;
}

public void setEvaluations(List<? extends Measure> evaluations) {
	this.evaluations = evaluations;
}




}
