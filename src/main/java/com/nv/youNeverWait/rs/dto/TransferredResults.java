/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * @author Joshi
 *
 */
public class TransferredResults {
private String fromBranch;
private String toBracnh;
private String orderUid;
private String  testUId;
private boolean sent;
public String getFromBranch() {
	return fromBranch;
}
public void setFromBranch(String fromBranch) {
	this.fromBranch = fromBranch;
}
public String getToBracnh() {
	return toBracnh;
}
public void setToBracnh(String toBracnh) {
	this.toBracnh = toBracnh;
}
public String getOrderUid() {
	return orderUid;
}
public void setOrderUid(String orderUid) {
	this.orderUid = orderUid;
}
public String getTestUId() {
	return testUId;
}
public void setTestUId(String testUId) {
	this.testUId = testUId;
}
public boolean isSent() {
	return sent;
}
public void setSent(boolean sent) {
	this.sent = sent;
}

}
