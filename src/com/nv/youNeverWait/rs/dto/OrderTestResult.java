/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joshi
 *
 */
public class OrderTestResult {
private String orderUid;
private Map<String,Object> testResultList=new HashMap<String,Object>();
public String getOrderUid() {
	return orderUid;
}
public void setOrderUid(String orderUid) {
	this.orderUid = orderUid;
}
public Map<String, Object> getTestResultList() {
	return testResultList;
}
public void setTestResultList(Map<String, Object> testResultList) {
	this.testResultList = testResultList;
}
public void addToMap(String key, Object value){
	testResultList.put(key, value);
}
}
