/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshi
 *
 */
public class TransferredOrders {
	private String fromBranch;
	private List<OrderDestinationBranchDTO> destinationBranches= new ArrayList<OrderDestinationBranchDTO>();
	private String orderUid;
	
	
	public List<OrderDestinationBranchDTO> getDestinationBranches() {
		return destinationBranches;
	}
	public void setDestinationBranches(
			List<OrderDestinationBranchDTO> destinationBranches) {
		this.destinationBranches = destinationBranches;
	}
	public String getFromBranch() {
		return fromBranch;
	}
	public void setFromBranch(String fromBranch) {
		this.fromBranch = fromBranch;
	}
	
	public String getOrderUid() {
		return orderUid;
	}
	public void setOrderUid(String orderUid) {
		this.orderUid = orderUid;
	}
	
}
