/**
 * PropertyUtil.java
 *
 * Aug 24, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.NetlimsPortal.util.filter.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.ParameterExpression;

import com.nv.NetlimsPortal.general.Constants;
import com.nv.NetlimsPortal.rs.dto.ExpressionDTO;
import com.nv.NetlimsPortal.util.filter.queryBuilder.LabPropertyEnum;
import com.nv.NetlimsPortal.util.filter.queryBuilder.OperatorEnum;





public class PropertyUtil {

	private static final String DATE_PATTERN = 
			"((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";

	/**
	 * Get parameter expression for property by giving property name and type
	 * @param criteriaBuilder
	 * @param propertyName
	 * @param type
	 * @return ParameterExpression<T>
	 *
	 */
	public static  <T> ParameterExpression<T> getParameterExpForProperty(CriteriaBuilder criteriaBuilder,
			String propertyName,Class<?> type){
		Class<?> type1 = null;
		if(type.getSimpleName().equals("float")){
			type1 = Float.class;
		}else if(type.getSimpleName().equals("int")){
			type1 = Integer.class;
		}else if(type.getSimpleName().equals("boolean")){
			type1 = Boolean.class;
		}
		ParameterExpression<T> p =(ParameterExpression<T>) criteriaBuilder.parameter(type1,propertyName);
		return p;
	}
	/**
	 * Get field type of property of an entity
	 * @param fieldName
	 * @param entityName
	 * @return Class<?>
	 *
	 */
	public static Class<?> getFieldType(String fieldName,String entityName){
		Class<?> t = null ;
		try {
			Class cls = Class.forName(entityName);
			t= cls.getDeclaredField(fieldName).getType();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return t;

	}
	/**
	 * Check validity of property value
	 * @param type
	 * @param value
	 * @return boolean
	 *
	 */
	public static boolean isValidPropertyValue(Class<?> type, String value){
		String value1="";
		String typeName = type.getSimpleName();
		System.out.println(typeName);
		if(value!=null && !value.isEmpty()){
			value1 = value.trim();
		}
		if(typeName.equals("String")){
			return true;
		}
		if(typeName.equals("Date")){
			if(!validateDate(value1)){
				return false;
			}
			SimpleDateFormat df =new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			Date date =null;
			try {
				date = df.parse(value);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}

		if(typeName.equals("Integer")||typeName.equals("int")){
			try{
				Integer val = Integer.parseInt(value);
			}catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
		if(typeName.equals("float")){
			try{
				float f = Float.parseFloat(value);
			}catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
		if(typeName.equals("Boolean")||typeName.equals("boolean")){
			if(value.toUpperCase().equals("TRUE")
					||value.toUpperCase().equals("FALSE")){
				return true;

			}
		}
	
		return false;

	}
	/**
	 * Cast property value to desired type
	 * @param type
	 * @param value
	 * @return Object
	 *
	 */
	public static Object getPropertyValue(Class<?> type,String value){
		String value1="";
		String typeName = type.getSimpleName();
		if(value==null){
			return value;
		}
		if(!value.isEmpty()){
			value1 = value.trim();
		}
		if(typeName.equals("String")){
			return value;
		}
		if(typeName.equals("Date")){
			SimpleDateFormat df =new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			Date date =null;
			try {
				date = df.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date ;
		}

		if(typeName.equals("Integer")||typeName.equals("int")){

			Integer val = null;
			try{
				val = Integer.parseInt(value);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
			return val;
		}
		if(typeName.equals("float")){
			Float f = null;
			try{
				f = Float.parseFloat(value);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
			return f;
		}
		if(typeName.equals("Boolean")||typeName.equals("boolean")){
			if(value.toUpperCase().equals("TRUE")){
				return true;
			}
			if(value.toUpperCase().equals("FALSE")){
				return false;
			}
		}
		
		return null;
	}


	/**
	 * Validate date format with regular expression
	 * @param date date address for validation
	 * @return true valid date fromat, false invalid date format
	 */
	public static boolean validateDate(final String date){
		Pattern pattern = Pattern.compile(DATE_PATTERN);
		Matcher matcher = pattern.matcher(date);

		if(matcher.matches()){

			matcher.reset();

			if(matcher.find()){

				String[] str = date.split("-");
				System.out.println(str[0]);

				String day = str[2];
				String month = str[1];
				int year = Integer.parseInt(str[0]);

				if (day.equals("31") && 
						(month.equals("4") || month .equals("6") || month.equals("9") ||
								month.equals("11") || month.equals("04") || month .equals("06") ||
								month.equals("09"))) {
					return false; // only 1,3,5,7,8,10,12 has 31 days
				} else if (month.equals("2") || month.equals("02")) {
					//leap year
					if(year % 4==0){
						if(day.equals("30") || day.equals("31")){
							return false;
						}else{
							return true;
						}
					}else{
						if(day.equals("29")||day.equals("30")||day.equals("31")){
							return false;
						}else{
							return true;
						}
					}
				}else{				 
					return true;				 
				}
			}else{
				return false;
			}		  
		}else{
			return false;
		}			    
	}

	public static ExpressionDTO getExpression(String value,	Property property ){
		String value1="";
		ExpressionDTO exp = new ExpressionDTO();
		Class<?> type = getFieldType(property.getFieldName(),property.getEntityName());
		String operator = OperatorEnum.like.getDisplayName();
		if(value==null){
			return null;
		}
		if(!value.isEmpty()){
			value1 = value.trim();
		}
		if(!property.getPathReferenceName().isEmpty()){
			return null;
		}

		if(!isValidPropertyValue(type, value1)){
			return null;
		}

		if(type.getSimpleName().equals("int") 
				||type.getSimpleName().equals("Integer")){
			operator = OperatorEnum.equal.getDisplayName();
		}else
			if(type.getSimpleName().toUpperCase().equals("boolean") 
					||type.getSimpleName().equals("Boolean")){
				operator = OperatorEnum.equal.getDisplayName();
			}else
				if(type.getSimpleName().equals("float")
						||type.getSimpleName().equals("double")
						){
					operator = OperatorEnum.equal.getDisplayName();
					if(!value.matches("\\d*.\\d*")){
						return null;
					}	

				}else
					if(type.getSimpleName().equals("StatusEnum")
							||type.getSimpleName().equals("PriorityEnum")
							||type.getSimpleName().equals("CollectStatusEnum")
							||type.getSimpleName().equals("DiscountTypeEnum")
							||type.getSimpleName().equals("DesignationEnum")
							||type.getSimpleName().equals("CalculationTypeEnum")
							||type.getSimpleName().equals("OriginEnum")
							||type.getSimpleName().equals("PayStatusEnum")
							||type.getSimpleName().equals("Date")){
						operator = OperatorEnum.equal.getDisplayName();

					}
					else{
						operator = OperatorEnum.like.getDisplayName();
					}

		exp.setOperator(operator);
		exp.setValue(value);
		exp.setName(property.getDisplayName());
		return exp;
	}

	public static String getFieldName(String propertyName){
		return LabPropertyEnum.valueOf(propertyName).getFieldName();
	}

	public static String getEntityName(String propertyName){
		return LabPropertyEnum.valueOf(propertyName).getEntityName();
	}
}
