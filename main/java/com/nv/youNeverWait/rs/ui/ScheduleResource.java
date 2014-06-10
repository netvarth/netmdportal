/**
 * ScheduleResource.java
 *
 * Mar 12, 2013
 *
 * @author Luciya
 */
package com.nv.youNeverWait.rs.ui;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ScheduleDTO;
import com.nv.youNeverWait.rs.dto.ScheduleListDTO;
import com.nv.youNeverWait.rs.dto.ScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleListDTO;
import com.nv.youNeverWait.user.bl.service.ScheduleService;

@Controller
@RequestMapping("ui/schedule/")
public class ScheduleResource {
	private ScheduleService scheduleService;

	/**
	 * Method to create schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ScheduleResponseDTO create(@RequestBody ScheduleDTO schedule) {

		ScheduleResponseDTO response = new ScheduleResponseDTO();
		try {
			response = scheduleService.create(schedule.getHeader(), schedule.getScheduleDetail());
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}

	/**
	 * Method to update a schedule
	 * 
	 * @param schedule
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ScheduleResponseDTO update(@RequestBody ScheduleDTO schedule) {

		ScheduleResponseDTO response = new ScheduleResponseDTO();
		try {
			response = scheduleService.update(schedule.getHeader(),schedule.getScheduleDetail());
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

		}
		return response;
	}
	/**
	 * Method to view schedule details
	 * 
	 * @param scheduleId
	 * @return ScheduleViewResponseDTO
	 */
	@RequestMapping(value = "view/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ScheduleViewResponseDTO view(@PathVariable int globalId) {

		ScheduleViewResponseDTO response = new ScheduleViewResponseDTO();
		try {
			response = scheduleService.view(globalId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;

	}

	/**
	 * Method to delete schedule
	 * 
	 * @param scheduleId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "delete/{globalId}", method = RequestMethod.GET)
	@ResponseBody
	public ScheduleResponseDTO delete(@PathVariable int globalId) {

		ScheduleResponseDTO response = new ScheduleResponseDTO();
		try {
			response = scheduleService.delete(globalId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;

	}
	
	/**
	 * Method to get day view of schedule
	 * 
	 * @param date
	 * @return ViewScheduleListDTO
	 */
	@RequestMapping(value = "dayView/{netMdBranchId},{doctorId},{date}", method = RequestMethod.GET)
	@ResponseBody
	public ViewScheduleListDTO dayView(@PathVariable int netMdBranchId, @PathVariable int doctorId, @PathVariable String date){
		ViewScheduleListDTO response = new ViewScheduleListDTO();
	try{
		response = scheduleService.dayView(netMdBranchId, doctorId, date);
	} catch (ServiceException e) {

		List<Parameter> parameters = e.getParamList();
		ErrorDTO error = new ErrorDTO();
		error.setErrCode(e.getError().getErrCode());
		error.setParams(parameters);
		error.setDisplayErrMsg(e.isDisplayErrMsg());
		response.setError(error);
		response.setSuccess(false);
	}
		return response;
	}
	
	/**
	 * Method to get weekly view of schedule
	 * 
	 * @param date
	 * @return ScheduleListDTO
	 */
	@RequestMapping(value = "weeklyView/{netMdBranchId},{doctorId},{date}", method = RequestMethod.GET)
	@ResponseBody
	public ScheduleListDTO weeklyView(@PathVariable int netMdBranchId, @PathVariable int doctorId,@PathVariable String date){
		ScheduleListDTO response = new ScheduleListDTO();
		try{
		response = scheduleService.weeklyView(netMdBranchId, doctorId, date);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	
	/**
	 * Method to get monthly view of schedule
	 * 
	 * @param startDate
	 * @param endDate
	 * @return ScheduleListDTO
	 */
	@RequestMapping(value = "monthlyView/{netMdBranchId},{doctorId},{startDate},{endDate}", method = RequestMethod.GET)
	@ResponseBody
	public ScheduleListDTO monthlyView(@PathVariable int netMdBranchId, @PathVariable int doctorId,@PathVariable String startDate,
			@PathVariable String endDate) {
			
		ScheduleListDTO response = new ScheduleListDTO();
		try{
		response = scheduleService.monthlyView(netMdBranchId, doctorId,startDate, endDate);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}

	/**
	 * @return the scheduleService
	 */
	public ScheduleService getScheduleService() {
		return scheduleService;
	}

	/**
	 * @param scheduleService
	 *            the scheduleService to set
	 */
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}

}
