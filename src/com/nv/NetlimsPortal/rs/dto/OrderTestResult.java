/**
 * 
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joshi
 *
 */
public class OrderTestResult {
private String orderUid;
private Map<String,String> testResultList=new HashMap<String,String>();
public String getOrderUid() {
	return orderUid;
}
public void setOrderUid(String orderUid) {
	this.orderUid = orderUid;
}
public Map<String, String> getTestResultList() {
	return testResultList;
}
public void setTestResultList(Map<String, String> testResultList) {
	this.testResultList = testResultList;
}
public void addToMap(String key, String value){
	testResultList.put(key, value);
}
}
