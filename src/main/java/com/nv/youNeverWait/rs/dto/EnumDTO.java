/**
 * EnumDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luciya Jos
 *
 */
public class EnumDTO {
	private String key;
	private  List<String> EnumValues = new ArrayList<String>();
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the enumValues
	 */
	public List<String> getEnumValues() {
		return EnumValues;
	}
	/**
	 * @param enumValues the enumValues to set
	 */
	public void setEnumValues(List<String> enumValues) {
		EnumValues = enumValues;
	}
	
}
