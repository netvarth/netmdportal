/**
 * 
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshi
 *
 */
public class TransferredDetails {
	private List<TransferredOrders> transferreddetails= new ArrayList<TransferredOrders>();
	private long count;
	private ErrorDTO error;
	private boolean success;
	
	public ErrorDTO getError() {
		return error;
	}
	public void setError(ErrorDTO error) {
		this.error = error;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<TransferredOrders> getTransferreddetails() {
		return transferreddetails;
	}
	public void setTransferreddetails(List<TransferredOrders> transferreddetails) {
		this.transferreddetails = transferreddetails;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	

}
