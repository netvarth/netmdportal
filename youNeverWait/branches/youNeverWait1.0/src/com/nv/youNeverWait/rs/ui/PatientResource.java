/**
 * PatientResource.java
 *
 * Dec 19, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.rs.ui;

import java.util.Date;
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

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.pl.entity.LogUserTypeEnum;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.DoctorListResponseDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientLoginDTO;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.security.User;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.PatientService;

@Controller
@RequestMapping("ui/patient/")
public class PatientResource {
	private PatientService service;
	private LogService logService;

	@RequestMapping(value = "pForm", method = RequestMethod.GET)
	public String pForm() {
		return "patientLogin";
	}
	
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
	 * Method performed for session logout
	 * 
	 * @return LoginResponseDTO
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public LoginResponseDTO logout() {

		LoginResponseDTO response = new LoginResponseDTO();
		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpServletRequest req = t.getRequest();
		User user = (User) req.getSession().getAttribute("user");
		if (user != null) {
			logService.saveUserDetails(req.getRemoteAddr(), user.getName(),
					user.getUserType(), user.getLoginTime(), new Date(),
					ApplicationNameEnum.Patient.getDisplayName(),
					Constants.LOGOUT);
		}
		req.getSession().setAttribute(Constants.USER, null);
		response.setSuccess(true);
		response.setError(null);
		return response;
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
	
	@RequestMapping(value = "listPatientsForLogin", method = RequestMethod.POST)
	@ResponseBody
	public PatientListResponseDTO listPatientsForLogin(@RequestBody PatientLoginDTO patient) {
		PatientListResponseDTO response = new PatientListResponseDTO();
		try {
			response = service.patientListOnLogin(patient.getEmail());
			System.out.println("--------->"+response.getPatientList().size());
			
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
	 * To reset password of netmd user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO resetPassword(@RequestBody LoginDTO login) {

		ResponseDTO response = new ResponseDTO();
		try {
			response = service.resetPassword(login);
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
		logService.saveUserDetails(request.getRemoteAddr(),
				login.getUserName(), login.getUserType(), null, null,
				ApplicationNameEnum.NetMd.getDisplayName(),
				Constants.RESET_PSWD);
		return response;
	}

	/**
	 * To reset password if user forgot his password
	 * @param login
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO forgotPassword(@RequestBody LoginDTO login)
	{
		ResponseDTO response=new ResponseDTO();
		try{
			response=service.forgotPassword(login);
		}
		catch (ServiceException e) {
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
	 * create appointment
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@RequestMapping(value = "createAppointment", method = RequestMethod.POST)
	@ResponseBody
	private AppointmentResponse createAppointment(
			@RequestBody Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.createAppointment(appointment);
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
	 * update appointment
	 * 
	 * @param appointment
	 * @return AppointmentResponse
	 */
	@RequestMapping(value = "updateAppointment", method = RequestMethod.POST)
	@ResponseBody
	private AppointmentResponse updateAppointment(
			@RequestBody Appointment appointment) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.updateAppointment(appointment);
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
	@RequestMapping(value = "listOfAppointments/{patientId}", method = RequestMethod.GET)
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
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	private AppointmentResponse deleteAppointment(@PathVariable int id) {
		AppointmentResponse response = new AppointmentResponse();
		try {
			response = service.deleteAppointment(id);
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
	 * Change password
	 * @param passwords
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords)
	{
		ResponseDTO response=new ResponseDTO();
		try{
			response=service.changePassword(passwords);
		}
		catch (ServiceException e) {
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
					Constants.CHANGE_PASSWORD);
		}
		return response;
	}
	
	/**
	 * Create password
	 * @param passwords
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "createPassword", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createPassword(@RequestBody CreatePasswordDTO passwords)
	{
		ResponseDTO response=new ResponseDTO();
		try{
			response=service.createPassword(passwords);
		}
		catch (ServiceException e) {
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
					Constants.CREATE_PASSWORD);
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
					ApplicationNameEnum.NetMd.getDisplayName(),
					Constants.BRANCH_LIST);
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
