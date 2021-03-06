/**
 * Expression.java
 *
 * Aug 18, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.util.filter.core;

public class Expression {
	private String name;
	private Object value;
	
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
	public Object getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
