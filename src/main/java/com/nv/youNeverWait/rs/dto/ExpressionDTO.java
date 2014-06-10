/**
 * ExpressionDTO.java
 *
 * Jan 23,2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.dto;

/**
 * 
 */
public class ExpressionDTO {
	private String name;
	private String value;
	private String operator;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
