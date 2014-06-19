/**
 * BranchOrdersResponseDTO.java
 *
 * @Author Luciya Jos
 * Jun 24, 2013 
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya Jos
 *
 */
public class BranchOrdersResponseDTO {
	private List<BranchOrderDetail> branchOrders=new ArrayList<BranchOrderDetail>();
	private long count;
	private ErrorDTO error;
	private boolean success;
	
	
	/**
	 * @return the branchOrders
	 */
	public List<BranchOrderDetail> getBranchOrders() {
		return branchOrders;
	}
	/**
	 * @param branchOrders the branchOrders to set
	 */
	public void setBranchOrders(List<BranchOrderDetail> branchOrders) {
		this.branchOrders = branchOrders;
	}
	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}
	
}
