/**
 * FilterDTO.java
 *
 * Aug 24, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.NetlimsPortal.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class FilterDTO {
	private List<ExpressionDTO> exp = new ArrayList<ExpressionDTO>();
	private int from;
	private int count;
	private boolean asc;
	
	/**
	 * @return the exp
	 */
	public List<ExpressionDTO> getExp() {
		return exp;
	}
	/**
	 * @param exp the exp to set
	 */
	public void setExp(List<ExpressionDTO> exp) {
		this.exp = exp;
	}
	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the asc
	 */
	public boolean isAsc() {
		return asc;
	}
	/**
	 * @param asc the asc to set
	 */
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
/*	*//**
	 * @param from the from to set
	 *//*
	public void setFrom(String from) {
		if(from!=null&& !from.equals("")){
			this.from = Integer.parseInt(from);
		}else{
			this.from = 0;
		}
	}*/

/*	*//**
	 * @param count the count to set
	 *//*
	public void setCount(String count) {
		if(count!=null&& !count.equals("")){
			this.count = Integer.parseInt(count);
		}else{
			this.count = 0;
		}
	}*/
		
}
