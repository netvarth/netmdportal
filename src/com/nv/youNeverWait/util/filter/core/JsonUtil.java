/**
 * JsonUtil.java
 * @author Mani E.V
 *
 * Version 1.0 14-Nov-2014
 *
 * Copyright (c) 2014 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.util.filter.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;

/**
 *
 *
 * @author Mani E.V
 */
public class JsonUtil {
	
	private static ObjectMapper maper = new ObjectMapper();
	
	/**
	 * Mani E.V	
	 * return convert json object to json string
	 * @param obj
	 * @return json string
	 */
	public static String getString(Object obj){
		try {
			return maper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.OrderTransferException);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	
	/**
	 * Mani E.V	
	 * to convert a json string to json object
	 * @param str
	 * @return object 
	 */
	public static Object getObject(String str){
		try {
			return maper.readValue(str, Object.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Mani E.V	
	 * to convert a json string to object
	 * @param str
	 * @param responseClass
	 * @return object
	 */
	public static Object getObject(String str, Class responseClass){
			try {
				return maper.readValue(str, responseClass);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
}
