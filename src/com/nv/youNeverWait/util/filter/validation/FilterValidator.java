/**
 * FilterValidator.java
 *
 * Sep 5, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.util.filter.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.core.PropertyUtil;
import com.nv.youNeverWait.util.filter.queryBuilder.OperatorEnum;


public class FilterValidator {

	/**
	 * Return error for invalid expression name	
	 * @param exp
	 * @return ErrorDTO
	 *
	 */
	protected ErrorDTO getInvalidSortByFieldError(String sortBy){

		ErrorDTO error = new ErrorDTO();
		error.setErrCode(ErrorCodeEnum.InvalidSortBy.getErrCode());
		List<Parameter> params= new ArrayList<Parameter>();
		Parameter param = new Parameter();
		param.setParameterName(Constants.SORT_BY);
		param.setValue(sortBy);
		params.add(param);
		error.setParams(params);
		return error;
	}
	
	/**
	 * Return error for invalid expression name	
	 * @param exp
	 * @return ErrorDTO
	 *
	 */
	protected ErrorDTO getInvalidExpNameError(ExpressionDTO exp){

		ErrorDTO error = new ErrorDTO();
		error.setErrCode(ErrorCodeEnum.InvalidFilterExpName.getErrCode());
		List<Parameter> params= new ArrayList<Parameter>();
		Parameter param = new Parameter();
		param.setParameterName(Constants.PROPERTY_NAME);
		param.setValue(exp.getName());
		params.add(param);
		error.setParams(params);
		return error;
	}

	/**
	 * Validate expression and return error,
	 * if expression name,value or operator is invalid
	 * @param exp
	 * @return ErrorDTO
	 *
	 */
	protected ErrorDTO validateExp(ExpressionDTO exp,Property property){

		ErrorDTO error = new ErrorDTO();
		if(property ==null){
			error.setErrCode(ErrorCodeEnum.InvalidFilterExpName.getErrCode());
			List<Parameter> params= new ArrayList<Parameter>();
			Parameter param = new Parameter();
			param.setParameterName(Constants.PROPERTY_NAME);
			param.setValue(exp.getName());
			params.add(param);
			error.setParams(params);
			return error;
		}
		if(!isValidExpName(exp.getName(),property)){

			error.setErrCode(ErrorCodeEnum.InvalidFilterExpName.getErrCode());
			List<Parameter> params= new ArrayList<Parameter>();
			Parameter param = new Parameter();
			param.setParameterName(Constants.PROPERTY_NAME);
			param.setValue(exp.getName());
			params.add(param);
			error.setParams(params);
			return error;
		}
		if(!isValidExpValue(exp.getValue(), property)){

			error.setErrCode(ErrorCodeEnum.InvalidFilterExpValue.getErrCode());
			List<Parameter> params= new ArrayList<Parameter>();
			Parameter param = new Parameter();
			param.setParameterName(Constants.PROPERTY_NAME);
			param.setValue(exp.getValue());
			params.add(param);
			error.setParams(params);
			return error;
		}
		if(!isValidExpOperator(exp.getOperator())){

			error.setErrCode(ErrorCodeEnum.InvalidFilterExpOperator.getErrCode());
			List<Parameter> params= new ArrayList<Parameter>();
			Parameter param = new Parameter();
			param.setParameterName(Constants.OPERATOR);
			param.setValue(exp.getOperator());
			params.add(param);
			Parameter param1= new Parameter();
			param1.setParameterName(Constants.PROPERTY_NAME);
			param1.setValue(exp.getName());
			params.add(param1);
			error.setParams(params);
			return error;
		}
		return null;
	}

	/**
	 * Check validity of expression name
	 * @param name
	 * @return boolean
	 *
	 */
	private boolean isValidExpName(String name,Property p) {

		if(name!=null && !name.equals("")){
			if(p.toString().equals(name)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Check validity of expression value
	 * @param value
	 * @return boolean
	 *
	 */
	private boolean isValidExpValue(String value,Property property) {
		String val=null;
		if(value!=null && !value.equals("")){
			val = value.trim();
			Class<?> type = PropertyUtil.getFieldType(property.getFieldName(), property.getEntityName());
			if(PropertyUtil.isValidPropertyValue(type,val)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Check validity of expression operator
	 * @param operator
	 * @return boolean
	 *
	 */
	private boolean isValidExpOperator(String operator) {

		if(operator!=null && !operator.equals("")){
			for (OperatorEnum operatorEnum : OperatorEnum.values()) {
				if(operatorEnum.getDisplayName().equals(operator)){
					return true;
				}
			}
		}
		return false;
	}
}
