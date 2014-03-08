package com.nv.youNeverWait.report;

import java.util.ArrayList;
import java.util.List;

public class DataBean {

	private String name;
	
	private List<KeyValue> valueSet = new ArrayList<KeyValue>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public  List<KeyValue> getValueSet() {
		return valueSet;
	}

	public void setValueSet(KeyValue keypair) {
		this.valueSet.add(keypair);
	}



	
	
	
	
	
	

}
