/**
 * AppointmentValidator.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 27, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.youNeverWait.user.bl.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentDetailsDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.AppointmentPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 * 
 */
public class AppointmentValidator  extends FilterValidator{
	// validate appointment creation
	public ErrorDTO validateCreateAppointment(Appointment appointment) {
		ErrorDTO error = new ErrorDTO();

		if (appointment.getAppointmentDetails().getDoctorId() == 0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getAppointmentDetails().getScheduleId() == 0) {
			error.setErrCode(ErrorCodeEnum.ScheduleIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getAppointmentDetails().getStartDate() == null
				|| !appointment.getAppointmentDetails().getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getAppointmentDetails().getStartTime()==null) {
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
		try {
			Date startTime = df1.parse(appointment.getAppointmentDetails().getStartTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	// validate appointment update
	public ErrorDTO validateUpdateAppointment(AppointmentDetailsDTO appointment) {
		ErrorDTO error = new ErrorDTO();
		
		if (appointment.getId() == 0) {
			error.setErrCode(ErrorCodeEnum.InvalidAppointment.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getDoctorId() == 0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getScheduleId() == 0) {
			error.setErrCode(ErrorCodeEnum.ScheduleIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartDate() == null
				|| !appointment.getStartDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (appointment.getStartTime()==null) {
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		DateFormat df1 = new SimpleDateFormat(Constants.TIME);
		try {
			Date startTime = df1.parse(appointment.getStartTime());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			error.setErrCode(ErrorCodeEnum.InvalidTimeFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		return null;
	}

	// validate appointment view
	public ErrorDTO validateView(int doctorId, String date) {
		ErrorDTO error = new ErrorDTO();
	
		if (doctorId == 0) {
			error.setErrCode(ErrorCodeEnum.DoctorIdNotNull.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}
		if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			error.setErrCode(ErrorCodeEnum.InvalidDateFormat.getErrCode());
			error.setDisplayErrMsg(true);
			return error;
		}

		return null;
	}
	/**
	 * Validate appointments filter
	 * 
	 * @param filter
	 * @return ErrorDTO
	 */
	public ErrorDTO validateAppointmentFilter(FilterDTO filter) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filter.getExp()) {
			Property property = null;
			try {
				property = AppointmentPropertyEnum.valueOf(exp.getName());
			} catch (IllegalArgumentException e) {
				error = getInvalidExpNameError(exp);
				return error;
			}
			error = validateExp(exp, property);
			if (error != null) {
				return error;
			}
		}
		return null;
	}
}
