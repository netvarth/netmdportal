/**
 * PatientResource.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.portal.rs.youNeverWait;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.security.youNeverWait.User;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.pl.entity.LogUserTypeEnum;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.PatientService;

@Controller
@RequestMapping("ui/patient/")
public class PatientResource {
	private PatientService service;
	private LogService logService;


	
	/**
	 * To show patient index page
	 * 
	 * @return patientindex.html
	 */
	@RequestMapping(value = "startUp", method = RequestMethod.GET)
	public String check() {
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		logService.saveUserDetails(request.getRemoteAddr(), null,
				LogUserTypeEnum.Nil.getDisplayName(), null, null,
				ApplicationNameEnum.Patient.getDisplayName(),
				Constants.STARTUP);
		return "patientindex";
	}
	
	

	/**
	 * Creates a patient
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createPatient", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createPatient(@RequestBody PatientDTO patient) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.createPatient(patient.getPatientDetail(), patient.getHeader());
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
	 * updates a patient
	 * 
	 * @param patient
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updatePatient(@RequestBody PatientDTO patient) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.updatePatient(patient.getPatientDetail(),
					patient.getHeader());
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
	 * list of doctors by clinic id
	 * 
	 * @param clinicId
	 * @return DoctorListResponseDTO
	 */
	@RequestMapping(value = "listDoctors/{clinicId}", method = RequestMethod.GET)
	@ResponseBody
	public DoctorListResponseDTO listDoctors(@PathVariable String clinicId) {
		DoctorListResponseDTO response = new DoctorListResponseDTO();
		try {
			response = service.listDoctors(clinicId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.DOCTOR_LIST);
		}
		return response;
	}
	

	/**
	 * list of patients for a login id
	 * 
	 * @param clinicId
	 * @return
	 */
	
	@RequestMapping(value = "list/{email}/", method = RequestMethod.GET)
	@ResponseBody
	public PatientListResponseDTO listPatientsForLogin(@PathVariable String email) {
		PatientListResponseDTO response = new PatientListResponseDTO();
		try {
			response = service.patientListOnLogin(email);	
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.PATIENT_LIST);
		}
		return response;
	}
		
	/**
	 * create appointment from NetMd
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@RequestMapping(value = "createAppointmentFromNetMd", method = RequestMethod.POST)
	@ResponseBody
	public AppointmentResponse createAppointmentFromNetMd(
			@RequestBody Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.createAppointmentFromNetMd(appointment);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.CREATE_APPOINTMENT);
		}
		return response;

	}
	
	/**
	 * Create appointment from Portal
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@RequestMapping(value = "createAppointmentFromPortal", method = RequestMethod.POST)
	@ResponseBody
	public AppointmentResponse createAppointmentFromPortal(
			@RequestBody Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.createAppointmentFromPortal(appointment);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.CREATE_APPOINTMENT);
		}
		return response;

	}
	
	/**
	 * update appointment from Netmd
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@RequestMapping(value = "updateAppointmentFromNetMd", method = RequestMethod.POST)
	@ResponseBody
	public AppointmentResponse updateAppointmentFromNetMd(
			@RequestBody Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.updateAppointmentFromNetMd(appointment);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.UPDATE_APPOINTMENT);
		}
		return response;

	}

	/**
	 * update appointment from portal
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@RequestMapping(value = "updateAppointmentFromPortal", method = RequestMethod.POST)
	@ResponseBody
	public AppointmentResponse updateAppointmentFromPortal(
			@RequestBody Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.updateAppointmentFromPortal(appointment);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.UPDATE_APPOINTMENT);
		}
		return response;

	}
	/**
	 * list of appointments for a patient
	 * 
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value = "appointments/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public AppointmentListResponseDTO listOfAppointments(
			@PathVariable String patientId) {
		AppointmentListResponseDTO response = new AppointmentListResponseDTO();
		try {
			response = service.listAppointmentsForPatient(patientId);
		} catch (ServiceException e) {
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.APPOINTMENT_LIST);
		}
		return response;
	}

	/**
	 * list of past appointments for a patient
	 * 
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value = "listOfPastAppointments/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public PastAppointmentListResponseDTO listOfPastAppointments(
			@PathVariable String patientId) {
		PastAppointmentListResponseDTO response = new PastAppointmentListResponseDTO();
		try {
			response = service.getPastAppointmentList(patientId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.PAST_APPOINTMENT_LIST);
		}
		return response;
	}
	
	/**
	 * delete appointment
	 * 
	 * @param id
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deleteAppointmentFromNetmd/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AppointmentResponse deleteAppointmentFromNetmd(@PathVariable int id) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.deleteAppointmentFromNetmd(id);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.DELETE_APPOINTMENT);
		}
		return response;

	}

	/**
	 * delete appointment from portal
	 * 
	 * @param id
	 * @return AppointmentResponse
	 */
	@RequestMapping(value = "deleteAppointmentFromPortal/{id}", method = RequestMethod.GET)
	@ResponseBody
	public AppointmentResponse deleteAppointmentFromPortal(@PathVariable int id) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.deleteAppointmentFromPortal(id);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.DELETE_APPOINTMENT);
		}
		return response;

	}

	
	/**
	 * view details of a patient
	 * 
	 * @param globalId
	 * @return PatientDTO
	 */
	@RequestMapping(value = "viewPatient/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public PatientDetail viewPatient(@PathVariable int patientId) {
		PatientDetail response = new PatientDetail();
		try {
			response = service.viewPatient(patientId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);

			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.VIEW_PATIENT);
		}
		return response;
	}

	/**
	 * deletes patient
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "deletePatient/{patientId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deletePatient(@PathVariable int patientId) {
		ResponseDTO response = new ResponseDTO();
		try {
			response = service.deletePatient(patientId);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.DELETE_PATIENT);
			
		}
		return response;
	}

	/**
	 * Shows a list of all netmd branches
	 * 
	 * @param filter
	 * @return NetMdBranchListResponseDTO
	 */
	@RequestMapping(value = "netmdBranchList", method = RequestMethod.POST)
	@ResponseBody
	public NetMdBranchListResponseDTO netmdBranchList(@RequestBody FilterDTO filter) {
		NetMdBranchListResponseDTO response = new NetMdBranchListResponseDTO();
		try {
			response = service.getBranchList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(request.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.NETMD_BRANCH_LIST);
		}
		return response;
	}
	
	/**
	 * To retrieve a list of past appointments which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return ResultListResponseDTO
	 */
	@RequestMapping(value = "getPastAppointments", method = RequestMethod.POST)
	@ResponseBody
	public PastAppointmentListResponseDTO getPastAppointments(
			@RequestBody FilterDTO filter) {
		PastAppointmentListResponseDTO response = new PastAppointmentListResponseDTO();
		try {
			response = service.getPastAppointments(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.PAST_APPOINTMENTS);
		}
		return response;
	}
	
	/**
	 * To retrieve a list of results which satisfy all filter conditions
	 * 
	 * @param filter
	 * @return ResultListResponseDTO
	 */
	@RequestMapping(value = "getresultList", method = RequestMethod.POST)
	@ResponseBody
	public ResultListResponseDTO getresultList(
			@RequestBody FilterDTO filter) {
		ResultListResponseDTO response = new ResultListResponseDTO();
		try {
			response = service.getresultList(filter);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest request = t.getRequest();
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj != null) {
			logService.saveUserDetails(request.getRemoteAddr(),
					userObj.getName(), userObj.getUserType(),
					userObj.getLoginTime(), null,
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.RESULT_LIST);
		}
		return response;
	}
	/**
	 * To retrieve a  result corresponding to the orderId and patient given
	 * 
	 * @param patient
	 * @return ResultDTO
	 */
	@RequestMapping(value = "patientTestResult", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO patientTestResult(
			@RequestBody PatientOrderDTO patient) {
		ResultDTO response = new ResultDTO();
		try {
			response = service.patientTestResult(patient);
		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		return response;
	}
	
	/**
	 * @return the service
	 */
	public PatientService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(PatientService service) {
		this.service = service;
	}

	/**
	 * @return the logService
	 */
	public LogService getLogService() {
		return logService;
	}

	/**
	 * @param logService the logService to set
	 */
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
