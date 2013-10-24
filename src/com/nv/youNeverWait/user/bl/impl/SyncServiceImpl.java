/**
 * SyncServiceImpl.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.youNeverWait.user.bl.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nv.youNeverWait.rs.dto.BillSyncResponseDTO;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ActionNameEnum;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentDetailsDTO;
import com.nv.youNeverWait.rs.dto.AppointmentResponse;
import com.nv.youNeverWait.rs.dto.BillResponseDTO;
import com.nv.youNeverWait.rs.dto.BillSummaryDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderCountResponseDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorLoginDTO;
import com.nv.youNeverWait.rs.dto.DoctorResponse;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.HeaderResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LabSyncDTO;
import com.nv.youNeverWait.rs.dto.LabSyncResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.OrderDetails;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientResponse;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultRetrievalResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalAppointmentResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalDoctorResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalPatientResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalUserResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveResultsResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveSpecimenResponse;
import com.nv.youNeverWait.rs.dto.RetrieveUserListResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.ScheduleResponse;
import com.nv.youNeverWait.rs.dto.ScheduleResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveTestResponse;
import com.nv.youNeverWait.rs.dto.UserResponse;
import com.nv.youNeverWait.user.bl.service.AppointmentService;
import com.nv.youNeverWait.user.bl.service.DoctorService;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.service.OrderService;
import com.nv.youNeverWait.user.bl.service.PatientService;
import com.nv.youNeverWait.user.bl.service.ResultService;
import com.nv.youNeverWait.user.bl.service.ScheduleService;
import com.nv.youNeverWait.user.bl.service.SpecimenService;
import com.nv.youNeverWait.user.bl.service.SyncService;
import com.nv.youNeverWait.user.bl.service.TestService;
import com.nv.youNeverWait.user.bl.validation.SyncValidator;
import com.nv.youNeverWait.user.pl.dao.SyncDao;

/**
 * @author Luciya Jose
 * 
 */
public class SyncServiceImpl implements SyncService {
	private SyncDao syncDao;
	private SyncValidator validator;
	private DoctorService doctorService;
	private PatientService patientService;
	private ScheduleService scheduleService;
	private NetMdService netMdService;
	private AppointmentService appointmentService;
	private ResultService resultService;
	private LabService labService;
	private SpecimenService specimenService;
	private TestService testService;
	
	private static final Log log = LogFactory.getLog(SyncServiceImpl.class);

	/**
	 * Method which performs synchronization for NetMd
	 * 
	 * @param sync
	 * @return syncResponse
	 */
	@Override
	public SyncResponseDTO syncData(SyncDTO sync) {

		SyncResponseDTO syncResponse = new SyncResponseDTO();
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);

		Date currentSyncTime = new Date(); // setting current date time

		/* Validating header details */
		validator.validateHeaderDetails(sync.getHeader());

		/* Validating last sync time */
		validator.validateLastSyncTime(sync.getLastSyncTime());

		/*Checking whether sync is enabled or disabled*/
//		SyncFreqDTO syncDetails=netMdService.syncEnableStatus(sync.getHeader(),sync.getFreqType(),sync.getInterval());
//		syncResponse.setSyncFreqType(syncDetails.getSyncFreqType());
//		syncResponse.setSyncInterval(syncDetails.getSyncTime());
//		syncResponse.setSyncStatus(syncDetails.isEnableSync());
//		validator.checkSyncFreq(syncDetails,sync.getFreqType(),sync.getInterval());
		
		/*Setting last sync time when the syncData calling for the first time*/
		if(sync.getLastSyncTime()==null){
			sync.setLastSyncTime(df.format(new Date(0)));

		}
		/* Checking whether primary device or not */
		HeaderResponseDTO headerResponse = syncDao.getHeaderStatus(sync
				.getHeader());

		if (headerResponse.isPrimaryDevice()) {
			/* Synchronizing doctor details */
			List<DoctorResponse> doctorResponseList = getDoctorResponseList(
					sync.getHeader(), sync.getNewDoctorList(),
					sync.getUpdateDoctorList(), sync.getDeleteDoctorList());

			syncResponse.setDoctorResponse(doctorResponseList);

			/* Synchronizing patient details */
			List<PatientResponse> patientResponseList = getPatientResponseList(
					sync.getHeader(), sync.getNewPatientList(),
					sync.getUpdatePatientList(), sync.getDeletedPatientList());

			syncResponse.setPatientResponse(patientResponseList);

			/* Synchronizing schedule details */
			List<ScheduleResponse> scheduleResponseList = getScheduleResponseList(
					sync.getHeader(), sync.getNewScheduleList(),
					sync.getUpdateScheduleList(), sync.getDeleteScheduleList());
			syncResponse.setScheduleResponse(scheduleResponseList);

			/* Synchronizing appointment details */
			List<AppointmentResponse> appointmentResponseList = getAppointmentResponseList(
					sync.getHeader(), sync.getNewAppointmentList(),
					sync.getUpdatedAppointmentList(),
					sync.getDeletedAppointmentList());
			syncResponse.setAppointmentResponse(appointmentResponseList);

			/* Synchronizing user details */
			List<UserResponse> userResponseList = getUserResponseList(
					sync.getHeader(), sync.getNewUserList(),
					sync.getUpdateUserList(), sync.getDeleteUserList());
			syncResponse.setUserResponse(userResponseList);

			/* Retrieving appointments for primary device */
			RetrievalAppointmentResponseDTO retrievalAppointmentDTOForPrimary = appointmentService
					.retrieveAppointmentForPrimary(sync.getLastSyncTime(), sync
							.getHeader().getPassPhrase(), sync.getHeader()
							.getNetMdBranchId(), currentSyncTime);
			syncResponse.setRetrievalAppointmentListForPrimary(retrievalAppointmentDTOForPrimary);

			/*Synchronizing bill details*/
			List<BillSyncResponseDTO> billResponseList = getBillResponseList(
					sync.getHeader(), sync.getNewBillList(),
					sync.getUpdateBillList());

			syncResponse.setBillResponse(billResponseList);
			
		}// end of if loop

		/*Retrieve appointments for secondary devices*/
		if(!headerResponse.isPrimaryDevice()){
			/* Retrieving appointments for Secondary device */
			RetrievalAppointmentResponseDTO retrievalAppointmentDTO = appointmentService
					.retrieveAppointmentForSecondary(sync.getLastSyncTime(), sync
							.getHeader().getPassPhrase(), sync.getHeader()
							.getNetMdBranchId(), currentSyncTime);
			syncResponse.setRetrievalAppointmentList(retrievalAppointmentDTO);

		}
		/* Retrieving Doctor Password List */
		List<DoctorLoginDTO> doctorPasswordList = doctorService
				.DoctorPasswordList(sync.getLastSyncTime(), sync.getHeader()
						.getPassPhrase(), sync.getHeader().getNetMdBranchId(),
						currentSyncTime);
		syncResponse.setDoctorLogin(doctorPasswordList);

		/*Get  Result from resultTbl*/
		List<RetrieveResultsResponseDTO> retrieveResults= resultService.getPatientResults(sync.getLastSyncTime(),sync.getHeader()
				.getPassPhrase(), sync.getHeader().getNetMdBranchId(),currentSyncTime);

		/* Retrieving Doctor List */
		RetrievalDoctorResponseDTO retrieveDoctors = doctorService
				.retrieveDoctorList(sync.getLastSyncTime(), sync.getHeader()
						.getPassPhrase(), sync.getHeader().getNetMdBranchId(),
						currentSyncTime);

		/* Retrieving User List */
		RetrievalUserResponseDTO retrieveUsers = netMdService.retrieveUserList(
				sync.getLastSyncTime(), sync.getHeader().getPassPhrase(), sync
				.getHeader().getNetMdBranchId(), currentSyncTime);

		/* Synchronizing patients back to netMD */
		RetrievalPatientResponseDTO retrievalPatientDTO = patientService
				.retrievePatientsForNetMd(sync.getLastSyncTime(), sync
						.getHeader().getPassPhrase(), sync.getHeader()
						.getNetMdBranchId(), currentSyncTime);

		
		/* Retrieving Schedule List */
		RetrievalScheduleResponseDTO retrieveSchedules = scheduleService.retrieveScheduleList(
				sync.getLastSyncTime(), sync.getHeader().getPassPhrase(), sync
				.getHeader().getNetMdBranchId(), currentSyncTime);

		syncResponse.setRetrieveResults(retrieveResults);
		syncResponse.setRetrievalPatientDTO(retrievalPatientDTO);
		syncResponse.setLastSynctime(df.format(currentSyncTime));
		syncResponse.setRetrievalUsersList(retrieveUsers);
		syncResponse.setRetrievalDoctorList(retrieveDoctors);
		syncResponse.setRetrievalScheduleList(retrieveSchedules);
		syncResponse.setHeaderResponse(headerResponse);
		syncResponse.setSuccess(true);
		return syncResponse;
	}

	/**
	 * @param header
	 * @param newBillList
	 * @param updateBillList
	 * @return
	 */
	private List<BillSyncResponseDTO> getBillResponseList(HeaderDTO header,
			List<BillSummaryDTO> newBillList,
			List<BillSummaryDTO> updateBillList) {
		
		List<BillSyncResponseDTO> billResponseList = new ArrayList<BillSyncResponseDTO>();

		List<BillSyncResponseDTO> newBillResponseList = syncNewBills(
				newBillList, header);
		List<BillSyncResponseDTO> updatedBillResponseList = syncUpdatedBills(
				updateBillList, header);
		billResponseList.addAll(newBillResponseList);
		billResponseList.addAll(updatedBillResponseList);

		return billResponseList;
	}

	/**
	 * @param updateBillList
	 * @param header
	 * @return
	 */
	private List<BillSyncResponseDTO> syncUpdatedBills(
			List<BillSummaryDTO> updateBillList, HeaderDTO header) {
		List<BillSyncResponseDTO> updatedBillResponseList = new ArrayList<BillSyncResponseDTO>();
		for (BillSummaryDTO updatedBill : updateBillList) {
			try {

				BillResponseDTO response = netMdService.updateBills(updatedBill,
						header);
				BillSyncResponseDTO billResponse = new BillSyncResponseDTO();
				billResponse.setActionName(ActionNameEnum.UPDATE.getDisplayName());
				billResponse.setUpdateDateTime(response.getUpdateDateTime());
				billResponse.setGlobalId(response.getGlobalId());
				billResponse.setUid(response.getUid());
				billResponse.setSuccess(true);

				updatedBillResponseList.add(billResponse);
			} catch (ServiceException se) {
				log.error("Error while saving updated bills into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				BillSyncResponseDTO billResponse = new BillSyncResponseDTO();
				billResponse.setError(error);
				billResponse.setSuccess(false);
				billResponse.setUid(updatedBill.getUid());
				billResponse.setGlobalId(updatedBill.getGlobalId());
				billResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());

				updatedBillResponseList.add(billResponse);
			}
		}
		return updatedBillResponseList;
	}

	/**
	 * @param newBillList
	 * @param header
	 * @return
	 */
	private List<BillSyncResponseDTO> syncNewBills(
			List<BillSummaryDTO> newBillList, HeaderDTO header) {
		List<BillSyncResponseDTO> newBillResponseList = new ArrayList<BillSyncResponseDTO>();
		for (BillSummaryDTO newBill : newBillList) {
			try {

				BillResponseDTO response = netMdService.createBill(newBill, header);
				BillSyncResponseDTO billResponse = new BillSyncResponseDTO();
				billResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());
				billResponse.setCreateDateTime(response.getCreateDateTime());
				billResponse.setUpdateDateTime(response.getUpdateDateTime());
				billResponse.setGlobalId(response.getGlobalId());
				billResponse.setUid(response.getUid());
				billResponse.setSuccess(true);

				newBillResponseList.add(billResponse);
			} catch (ServiceException se) {
				log.error("Error while saving new bills into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				BillSyncResponseDTO billResponse = new BillSyncResponseDTO();
				billResponse.setError(error);
				billResponse.setSuccess(false);
				billResponse.setUid(newBill.getUid());
				billResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());
				newBillResponseList.add(billResponse);
			}
		}
		return newBillResponseList;
	}

	/**
	 * Method to get the doctorResponseList for synchronisation
	 * 
	 * @param header
	 * @param newDoctorList
	 * @param updateDoctorList
	 * @param deleteDoctorList
	 * @return doctorResponseList
	 */
	private List<DoctorResponse> getDoctorResponseList(HeaderDTO header,
			List<DoctorDetail> newDoctorList,
			List<DoctorDetail> updateDoctorList,
			List<DoctorDetail> deleteDoctorList) {

		List<DoctorResponse> doctorResponseList = new ArrayList<DoctorResponse>();

		List<DoctorResponse> newDoctorResponseList = syncNewDoctors(
				newDoctorList, header);
		List<DoctorResponse> updatedDoctorResponseList = syncUpdatedDoctors(
				updateDoctorList, header);
		List<DoctorResponse> deletedDoctorResponseList = syncDeletedDoctors(
				deleteDoctorList, header);

		doctorResponseList.addAll(newDoctorResponseList);
		doctorResponseList.addAll(updatedDoctorResponseList);
		doctorResponseList.addAll(deletedDoctorResponseList);

		return doctorResponseList;
	}

	/**
	 * Method to get the patientResponseList for sync
	 * 
	 * @param header
	 * @param patient
	 * @return patientResponseList
	 */
	private List<PatientResponse> getPatientResponseList(HeaderDTO header,
			List<PatientDetail> newPatients,
			List<PatientDetail> updatePatients,
			List<PatientDetail> deletedPatients) {

		List<PatientResponse> patientResponseList = new ArrayList<PatientResponse>();

		List<PatientResponse> newPatientResponseList = syncNewPatients(
				newPatients, header);
		List<PatientResponse> updatePatientResponseList = syncUpdatePatients(
				updatePatients, header);
		List<PatientResponse> deletedPatientResponseList = synDeletedPatients(
				deletedPatients, header);
		patientResponseList.addAll(newPatientResponseList);
		patientResponseList.addAll(updatePatientResponseList);
		patientResponseList.addAll(deletedPatientResponseList);

		return patientResponseList;
	}

	/**
	 * 
	 * sync schedule
	 * 
	 * @param newPatients
	 * @param header
	 */

	private List<ScheduleResponse> getScheduleResponseList(HeaderDTO header,
			List<ScheduleDetail> newScheduleList,
			List<ScheduleDetail> updateScheduleList,
			List<ScheduleDetail> deleteScheduleList) {

		List<ScheduleResponse> scheduleResponseList = new ArrayList<ScheduleResponse>();

		List<ScheduleResponse> newScheduleResponseList = syncNewSchedules(
				newScheduleList, header);
		List<ScheduleResponse> updateScheduleResponseList = syncUpdateSchedules(
				updateScheduleList, header);
		List<ScheduleResponse> deleteScheduleResponseList = syncDeleteSchedules(deleteScheduleList);

		scheduleResponseList.addAll(newScheduleResponseList);
		scheduleResponseList.addAll(updateScheduleResponseList);
		scheduleResponseList.addAll(deleteScheduleResponseList);

		return scheduleResponseList;
	}

	/**
	 * Sync appointments
	 * 
	 * @param header
	 * @param newAppointment
	 * @return
	 */
	private List<AppointmentResponse> getAppointmentResponseList(
			HeaderDTO header, List<AppointmentDetailsDTO> newAppointments,
			List<AppointmentDetailsDTO> updatedAppointments,
			List<AppointmentDetailsDTO> deletedAppointments) {
		List<AppointmentResponse> appointmentResponseList = new ArrayList<AppointmentResponse>();
		List<AppointmentResponse> newAppointmentResponseList = syncNewAppointment(
				newAppointments, header);
		List<AppointmentResponse> updatedAppointmentResponseList = syncUpdatedAppointment(
				updatedAppointments, header);
		List<AppointmentResponse> deletedAppointmentResponseList = syncDeletedAppointments(
				deletedAppointments, header);
		appointmentResponseList.addAll(newAppointmentResponseList);
		appointmentResponseList.addAll(updatedAppointmentResponseList);
		appointmentResponseList.addAll(deletedAppointmentResponseList);
		return appointmentResponseList;
	}

	/**
	 * Sync new appointments
	 * 
	 * @param newAppointmentList
	 * @param header
	 * @return newAppointmentResponseList
	 */
	private List<AppointmentResponse> syncNewAppointment(
			List<AppointmentDetailsDTO> newAppointmentList, HeaderDTO header) {
		List<AppointmentResponse> newAppointmentResponseList = new ArrayList<AppointmentResponse>();
		for (AppointmentDetailsDTO appointment : newAppointmentList) {
			try {
				Appointment appointmentDto = new Appointment();
				appointmentDto.setAppointmentDetails(appointment);
				appointmentDto.setHeader(header);
				AppointmentResponse response = appointmentService
						.createAppointmentFromNetMd(appointmentDto);
				response.setActionName(ActionNameEnum.ADD.getDisplayName());
				newAppointmentResponseList.add(response);
			} catch (ServiceException se) {
				log.error("Error while saving new appointments into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				AppointmentResponse response = new AppointmentResponse();
				response.setError(error);
				response.setSuccess(false);
				response.setId(appointment.getId());
				response.setActionName(ActionNameEnum.ADD.getDisplayName());

				newAppointmentResponseList.add(response);
			}
		}
		return newAppointmentResponseList;
	}

	/**
	 * sync updated appointments
	 * 
	 * @param updatedAppointments
	 * @param header
	 * @return updatedAppointmentResponseList
	 */
	private List<AppointmentResponse> syncUpdatedAppointment(
			List<AppointmentDetailsDTO> updatedAppointments, HeaderDTO header) {
		List<AppointmentResponse> updatedAppointmentResponseList = new ArrayList<AppointmentResponse>();
		for (AppointmentDetailsDTO appointment : updatedAppointments) {
			try {
				Appointment appointmentDto = new Appointment();
				appointmentDto.setAppointmentDetails(appointment);
				appointmentDto.setHeader(header);
				AppointmentResponse response = appointmentService
						.updateAppointmentFromNetMd(appointmentDto);
				response.setActionName(ActionNameEnum.UPDATE.getDisplayName());
				updatedAppointmentResponseList.add(response);
			} catch (ServiceException se) {
				log.error("Error while saving updated appointments into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				AppointmentResponse response = new AppointmentResponse();
				response.setError(error);
				response.setSuccess(false);
				response.setId(response.getId());
				response.setGlobalId(response.getGlobalId());
				response.setActionName(ActionNameEnum.UPDATE.getDisplayName());
				updatedAppointmentResponseList.add(response);
			}
		}
		return updatedAppointmentResponseList;
	}

	/**
	 * sync deleted appointments
	 * 
	 * @param deletedAppointments
	 * @param header
	 * @return deletedAppointmentResponseList
	 */
	private List<AppointmentResponse> syncDeletedAppointments(
			List<AppointmentDetailsDTO> deletedAppointments, HeaderDTO header) {
		List<AppointmentResponse> deletedAppointmentResponseList = new ArrayList<AppointmentResponse>();
		for (AppointmentDetailsDTO deletedAppointment : deletedAppointments) {
			try {
				Appointment appointmentDto = new Appointment();
				appointmentDto.setAppointmentDetails(deletedAppointment);
				appointmentDto.setHeader(header);
				AppointmentResponse response = appointmentService
						.deleteAppointmentFromNetMd(appointmentDto
								.getAppointmentDetails().getGlobalId());
				response.setActionName(ActionNameEnum.DELETE.getDisplayName());
				deletedAppointmentResponseList.add(response);
			} catch (ServiceException se) {
				log.error("Error while saving deleted appointments into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				AppointmentResponse response = new AppointmentResponse();
				response.setError(error);
				response.setSuccess(false);
				response.setId(response.getId());
				response.setGlobalId(response.getGlobalId());
				response.setActionName(ActionNameEnum.DELETE.getDisplayName());
				deletedAppointmentResponseList.add(response);
			}

		}
		return deletedAppointmentResponseList;
	}

	/**
	 * Method to get the userResponseList for synchronisation
	 * 
	 * @param header
	 * @param newUserList
	 * @param updateUserList
	 * @param deleteUserList
	 * @return userResponseList
	 */
	private List<UserResponse> getUserResponseList(HeaderDTO header,
			List<NetMdUserDetail> newUserList,
			List<NetMdUserDetail> updateUserList,
			List<NetMdUserDetail> deleteUserList) {

		List<UserResponse> userResponseList = new ArrayList<UserResponse>();
		List<UserResponse> newUserResponseList = syncNewUsers(newUserList,
				header);
		List<UserResponse> updateUserResponseList = syncUpdateUsers(
				updateUserList, header);
		List<UserResponse> deleteUserResponseList = syncDeleteUsers(deleteUserList);

		userResponseList.addAll(newUserResponseList);
		userResponseList.addAll(updateUserResponseList);
		userResponseList.addAll(deleteUserResponseList);

		return userResponseList;
	}

	/**
	 * Method to sync patients
	 * 
	 * @param newPatients
	 * @param header
	 * @return newPatientResponseList
	 */
	private List<PatientResponse> syncNewPatients(
			List<PatientDetail> newPatients, HeaderDTO header) {

		List<PatientResponse> newPatientResponseList = new ArrayList<PatientResponse>();
		for (PatientDetail newPatient : newPatients) {
			try {

				ResponseDTO response = patientService.createPatient(newPatient,
						header);
				PatientResponse patientResponse = new PatientResponse();
				patientResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());
				patientResponse.setCreateDateTime(response.getCreateDateTime());
				patientResponse.setUpdateDateTime(response.getUpdateDateTime());
				patientResponse.setGlobalId(response.getGlobalId());
				patientResponse.setId(response.getId());
				patientResponse.setSuccess(true);

				newPatientResponseList.add(patientResponse);
			} catch (ServiceException se) {
				log.error("Error while saving new patients into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				PatientResponse patientResponse = new PatientResponse();
				patientResponse.setError(error);
				patientResponse.setSuccess(false);
				patientResponse.setId(newPatient.getId());
				patientResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());

				newPatientResponseList.add(patientResponse);
			}
		}
		return newPatientResponseList;
	}

	/**
	 * Sync updated patients
	 * 
	 * @param updatePatients
	 * @param header
	 * @return
	 */
	private List<PatientResponse> syncUpdatePatients(
			List<PatientDetail> updatePatients, HeaderDTO header) {
		List<PatientResponse> updatePatientResponseList = new ArrayList<PatientResponse>();
		for (PatientDetail patient : updatePatients) {
			try {
				ResponseDTO response = patientService.updatePatient(patient,
						header);

				PatientResponse patientResponse = new PatientResponse();
				patientResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());
				patientResponse.setUpdateDateTime(response.getUpdateDateTime());
				patientResponse.setGlobalId(response.getGlobalId());
				patientResponse.setId(response.getId());
				patientResponse.setSuccess(true);
				updatePatientResponseList.add(patientResponse);
			} catch (ServiceException se) {
				log.error("Error while saving updated patients into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				PatientResponse patientResponse = new PatientResponse();
				patientResponse.setError(error);
				patientResponse.setSuccess(false);
				patientResponse.setId(patient.getId());
				patientResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());
				updatePatientResponseList.add(patientResponse);
			}
		}
		return updatePatientResponseList;
	}

	/**
	 * Sync deleted patients
	 * 
	 * @param deletedPatients
	 * @param header
	 * @return deletedPatientResponseList
	 */
	private List<PatientResponse> synDeletedPatients(
			List<PatientDetail> deletedPatients, HeaderDTO header) {
		List<PatientResponse> deletedPatientResponseList = new ArrayList<PatientResponse>();
		for (PatientDetail patient : deletedPatients) {
			try {
				ResponseDTO response = patientService.deletePatient(patient
						.getGlobalId());
				PatientResponse patientResponse = new PatientResponse();
				patientResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());
				patientResponse.setGlobalId(response.getGlobalId());
				patientResponse.setId(patient.getId());
				patientResponse.setSuccess(true);
				deletedPatientResponseList.add(patientResponse);
			} catch (ServiceException se) {
				log.error("Error while saving deleted patients into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				PatientResponse patientResponse = new PatientResponse();
				patientResponse.setError(error);
				patientResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());
				patientResponse.setGlobalId(patient.getGlobalId());
				patientResponse.setId(patient.getId());
				patientResponse.setSuccess(false);
			}
		}
		return deletedPatientResponseList;
	}

	/**
	 * Method to save newly created doctors
	 * 
	 * @param newDoctors
	 * @param header
	 * @return newDoctorResponseList
	 */
	private List<DoctorResponse> syncNewDoctors(List<DoctorDetail> newDoctors,
			HeaderDTO header) {

		List<DoctorResponse> newDoctorResponseList = new ArrayList<DoctorResponse>();
		for (DoctorDetail newDoctor : newDoctors) {
			try {

				ResponseDTO response = doctorService.create(newDoctor, header);
				DoctorResponse doctorResponse = new DoctorResponse();
				doctorResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());
				doctorResponse.setCreateDateTime(response.getCreateDateTime());
				doctorResponse.setUpdateDateTime(response.getUpdateDateTime());
				doctorResponse.setGlobalId(response.getGlobalId());
				doctorResponse.setId(response.getId());
				doctorResponse.setSuccess(true);

				newDoctorResponseList.add(doctorResponse);
			} catch (ServiceException se) {
				log.error("Error while saving new doctors into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				DoctorResponse doctorResponse = new DoctorResponse();
				doctorResponse.setError(error);
				doctorResponse.setSuccess(false);
				doctorResponse.setId(newDoctor.getId());
				doctorResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());

				newDoctorResponseList.add(doctorResponse);
			}
		}
		return newDoctorResponseList;
	}

	/**
	 * Method to update doctors
	 * 
	 * @param updateDoctors
	 * @param header
	 * @return updatedDoctorResponseList
	 */
	private List<DoctorResponse> syncUpdatedDoctors(
			List<DoctorDetail> updateDoctors, HeaderDTO header) {

		List<DoctorResponse> updatedDoctorResponseList = new ArrayList<DoctorResponse>();
		for (DoctorDetail updatedDoctor : updateDoctors) {
			try {

				ResponseDTO response = doctorService.update(updatedDoctor,
						header);
				DoctorResponse doctorResponse = new DoctorResponse();
				doctorResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());
				doctorResponse.setUpdateDateTime(response.getUpdateDateTime());
				doctorResponse.setGlobalId(response.getGlobalId());
				doctorResponse.setId(response.getId());
				doctorResponse.setSuccess(true);

				updatedDoctorResponseList.add(doctorResponse);
			} catch (ServiceException se) {
				log.error("Error while saving updated doctors into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				DoctorResponse doctorResponse = new DoctorResponse();
				doctorResponse.setError(error);
				doctorResponse.setSuccess(false);
				doctorResponse.setId(updatedDoctor.getId());
				doctorResponse.setGlobalId(updatedDoctor.getGlobalId());
				doctorResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());

				updatedDoctorResponseList.add(doctorResponse);
			}
		}
		return updatedDoctorResponseList;
	}

	/**
	 * Method to delete doctors
	 * 
	 * @param deleteDoctors
	 * @param header
	 * @return deletedDoctorResponseList
	 */
	private List<DoctorResponse> syncDeletedDoctors(
			List<DoctorDetail> deleteDoctors, HeaderDTO header) {

		List<DoctorResponse> deletedDoctorResponseList = new ArrayList<DoctorResponse>();
		for (DoctorDetail deletedDoctor : deleteDoctors) {
			try {

				ResponseDTO response = doctorService.delete(deletedDoctor
						.getGlobalId());
				DoctorResponse doctorResponse = new DoctorResponse();
				doctorResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());

				doctorResponse.setGlobalId(response.getGlobalId());

				doctorResponse.setId(deletedDoctor.getId());
				doctorResponse.setSuccess(true);

				deletedDoctorResponseList.add(doctorResponse);
			} catch (ServiceException se) {
				log.error("Error while saving deleted doctors into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				DoctorResponse doctorResponse = new DoctorResponse();
				doctorResponse.setError(error);
				doctorResponse.setSuccess(false);
				doctorResponse.setId(deletedDoctor.getId());
				doctorResponse.setGlobalId(deletedDoctor.getGlobalId());
				doctorResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());

				deletedDoctorResponseList.add(doctorResponse);
			}
		}
		return deletedDoctorResponseList;
	}

	/**
	 * Method to save new schedules
	 * 
	 * @param newSchedules
	 * @param header
	 * @return newScheduleResponseList
	 */
	private List<ScheduleResponse> syncNewSchedules(
			List<ScheduleDetail> newSchedules, HeaderDTO header) {

		List<ScheduleResponse> newScheduleResponseList = new ArrayList<ScheduleResponse>();
		for (ScheduleDetail newSchedule : newSchedules) {
			try {
				ScheduleResponseDTO response = scheduleService.create(header,
						newSchedule);
				ScheduleResponse scheduleResponse = new ScheduleResponse();
				scheduleResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());
				scheduleResponse.setGlobalId(response.getGlobalId());
				scheduleResponse.setId(response.getId());
				scheduleResponse.setSuccess(true);
				scheduleResponse.setSeriesGlobalId(response.getSeriesGlobalId());
				scheduleResponse.setSeriesId(response.getSeriesId());
				newScheduleResponseList.add(scheduleResponse);
			} catch (ServiceException se) {
				log.error("Error while saving new schedules into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				ScheduleResponse scheduleResponse = new ScheduleResponse();
				scheduleResponse.setError(error);
				scheduleResponse.setSuccess(false);
				scheduleResponse.setId(newSchedule.getId());
				scheduleResponse.setActionName(ActionNameEnum.ADD
						.getDisplayName());

				newScheduleResponseList.add(scheduleResponse);
			}
		}
		return newScheduleResponseList;
	}

	/**
	 * Method to update schedules
	 * 
	 * @param updateSchedules
	 * @param header
	 * @return updateScheduleResponseList
	 */
	private List<ScheduleResponse> syncUpdateSchedules(
			List<ScheduleDetail> updateSchedules, HeaderDTO header) {

		List<ScheduleResponse> updateScheduleResponseList = new ArrayList<ScheduleResponse>();
		for (ScheduleDetail updatedSchedule : updateSchedules) {
			try {
				ScheduleResponseDTO response = scheduleService.update(header,
						updatedSchedule);
				ScheduleResponse scheduleResponse = new ScheduleResponse();
				scheduleResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());
				scheduleResponse.setGlobalId(response.getGlobalId());
				scheduleResponse.setId(updatedSchedule.getId());
				scheduleResponse.setSuccess(true);
				scheduleResponse.setSeriesGlobalId(response.getSeriesGlobalId());
				scheduleResponse.setSeriesId(response.getSeriesId());
				updateScheduleResponseList.add(scheduleResponse);
			} catch (ServiceException se) {
				log.error("Error while saving updated schedules into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				ScheduleResponse scheduleResponse = new ScheduleResponse();
				scheduleResponse.setError(error);
				scheduleResponse.setGlobalId(updatedSchedule
						.getScheduleGlobalId());
				scheduleResponse.setSuccess(false);
				scheduleResponse.setId(updatedSchedule.getId());
				scheduleResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());

				updateScheduleResponseList.add(scheduleResponse);
			}
		}
		return updateScheduleResponseList;
	}

	/**
	 * Method to delete schedules
	 * 
	 * @param deleteSchedules
	 * @return deleteScheduleResponseList
	 */
	private List<ScheduleResponse> syncDeleteSchedules(
			List<ScheduleDetail> deleteSchedules) {

		List<ScheduleResponse> deleteScheduleResponseList = new ArrayList<ScheduleResponse>();
		for (ScheduleDetail deletedSchedule : deleteSchedules) {
			try {
				ScheduleResponseDTO response = scheduleService.delete(deletedSchedule
						.getScheduleGlobalId());
				ScheduleResponse scheduleResponse = new ScheduleResponse();
				scheduleResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());
				scheduleResponse.setGlobalId(response.getGlobalId());
				scheduleResponse.setId(deletedSchedule.getId());
				scheduleResponse.setSuccess(true);
				scheduleResponse.setSeriesGlobalId(response.getSeriesGlobalId());
				scheduleResponse.setSeriesId(response.getSeriesId());
				deleteScheduleResponseList.add(scheduleResponse);
			} catch (ServiceException se) {
				log.error("Error while saving deleted schedules into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				ScheduleResponse scheduleResponse = new ScheduleResponse();
				scheduleResponse.setError(error);
				scheduleResponse.setSuccess(false);
				scheduleResponse.setId(deletedSchedule.getId());
				scheduleResponse.setGlobalId(deletedSchedule
						.getScheduleGlobalId());
				scheduleResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());

				deleteScheduleResponseList.add(scheduleResponse);
			}
		}
		return deleteScheduleResponseList;
	}

	/**
	 * Method to save new users
	 * 
	 * @param newUsers
	 * @param header
	 * @return newUsersResponseList
	 */
	private List<UserResponse> syncNewUsers(List<NetMdUserDetail> newUsers,
			HeaderDTO header) {

		List<UserResponse> newUsersResponseList = new ArrayList<UserResponse>();
		for (NetMdUserDetail newUser : newUsers) {
			try {
				ResponseDTO response = netMdService.createUser(header, newUser);

				UserResponse userResponse = new UserResponse();
				userResponse.setActionName(ActionNameEnum.ADD.getDisplayName());
				userResponse.setGlobalId(response.getGlobalId());
				userResponse.setId(response.getId());
				userResponse.setSuccess(true);

				newUsersResponseList.add(userResponse);
			} catch (ServiceException se) {
				log.error("Error while saving new users into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				UserResponse userResponse = new UserResponse();
				userResponse.setError(error);
				userResponse.setSuccess(false);
				userResponse.setId(newUser.getId());
				userResponse.setActionName(ActionNameEnum.ADD.getDisplayName());

				newUsersResponseList.add(userResponse);
			}
		}
		return newUsersResponseList;
	}

	/**
	 * Method to update users
	 * 
	 * @param updateUsers
	 * @param header
	 * @return updateUserResponseList
	 */
	private List<UserResponse> syncUpdateUsers(
			List<NetMdUserDetail> updateUsers, HeaderDTO header) {

		List<UserResponse> updateUserResponseList = new ArrayList<UserResponse>();
		for (NetMdUserDetail updatedUser : updateUsers) {
			try {
				ResponseDTO response = netMdService.updateUser(header,
						updatedUser);
				UserResponse userResponse = new UserResponse();
				userResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());
				userResponse.setGlobalId(response.getGlobalId());
				userResponse.setId(updatedUser.getId());
				userResponse.setSuccess(true);

				updateUserResponseList.add(userResponse);
			} catch (ServiceException se) {
				log.error("Error while saving updated users into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				UserResponse userResponse = new UserResponse();
				userResponse.setError(error);
				userResponse.setSuccess(false);
				userResponse.setId(updatedUser.getId());
				userResponse.setGlobalId(updatedUser.getGlobalId());
				userResponse.setActionName(ActionNameEnum.UPDATE
						.getDisplayName());

				updateUserResponseList.add(userResponse);
			}
		}
		return updateUserResponseList;
	}

	/**
	 * Method to delete users
	 * 
	 * @param deleteUsers
	 * @return deleteUserResponseList
	 */
	private List<UserResponse> syncDeleteUsers(List<NetMdUserDetail> deleteUsers) {

		List<UserResponse> deleteUserResponseList = new ArrayList<UserResponse>();
		for (NetMdUserDetail deletedUser : deleteUsers) {
			try {
				 netMdService.deleteUser(deletedUser
						.getGlobalId());
				UserResponse userResponse = new UserResponse();
				userResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());
				userResponse.setGlobalId(deletedUser.getGlobalId());
				userResponse.setId(deletedUser.getId());
				userResponse.setSuccess(true);

				deleteUserResponseList.add(userResponse);
			} catch (ServiceException se) {
				log.error("Error while saving deleted users into portal ", se);
				List<Parameter> parameters = se.getParamList();
				ErrorDTO error = new ErrorDTO();
				error.setErrCode(se.getError().getErrCode());
				error.setParams(parameters);
				error.setDisplayErrMsg(se.isDisplayErrMsg());
				UserResponse userResponse = new UserResponse();
				userResponse.setError(error);
				userResponse.setSuccess(false);
				userResponse.setId(deletedUser.getId());
				userResponse.setGlobalId(deletedUser.getGlobalId());
				userResponse.setActionName(ActionNameEnum.DELETE
						.getDisplayName());

				deleteUserResponseList.add(userResponse);
			}
		}
		return deleteUserResponseList;
	}

	/**
	 * Method which performs synchronization for NetLims
	 * 
	 * @param sync
	 * @return syncResponse
	 */
	@Override
	public LabSyncResponseDTO syncNetLimsData(LabSyncDTO sync) {
		LabSyncResponseDTO syncResponse = new LabSyncResponseDTO();
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);

		/* Validating header details */
		validator.validateLabHeaderDetails(sync.getHeader());

		/* Validating last sync time */
		validator.validateLastSyncTime(sync.getLastSyncTime());

		/*Setting last sync time when the syncData calling for the first time*/
		if(sync.getLastSyncTime()==null)
			sync.setLastSyncTime(df.format(new Date(0)));
		
		Date currentSyncTime = new Date(); // setting current date time
		
//		/*Checking whether sync is enabled or disabled*/
//		SyncFreqDTO syncDetails=labService.syncEnableStatus(sync.getHeader(),sync.getFreqType(),sync.getInterval());
//		syncResponse.setSyncStatus(syncDetails.isEnableSync());
//		syncResponse.setFreqType(syncDetails.getSyncFreqType());
//		syncResponse.setIntervalTime(syncDetails.getSyncTime());
//		validator.checkSyncFreq(syncDetails,sync.getFreqType(),sync.getInterval());
		
		/*Retrieving all lab branches in the portal*/
		LabBranchListResponseDTO retrieveLabBranchList=labService.retrieveLabBranchList(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		syncResponse.setRetrieveLabBranchList(retrieveLabBranchList);

		/*Retrieving lab branch users*/
		RetrieveUserListResponseDTO retrieveUserList= labService.retrieveUserList(sync.getHeader(), sync.getLastSyncTime(), currentSyncTime);
		syncResponse.setRetrieveUserList(retrieveUserList);

		/*Retrieving  newly added and updated lab test*/
		 RetrieveTestResponse getTests= testService.getTests(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		 syncResponse.setRetrieveTests(getTests);
		 
		 /*Retrieving newly added and updated test specimen*/
		 RetrieveSpecimenResponse retreiveSpecimens= specimenService.getSpecimens(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		 syncResponse.setRetrieveSpecimens(retreiveSpecimens);
		 
		 /*Get all results from other branches of same lab*/
		ResultRetrievalResponseDTO getResult = labService.getResult(sync.getHeader(), sync.getLastSyncTime(), currentSyncTime);
		syncResponse.setGetResult(getResult);
		
		/* Save total orders and its related details of a branch*/
		BranchOrderCountResponseDTO response= labService.createTotalOrders(sync.getHeader(),sync.getBranchOrders());
		syncResponse.setOrderAmount(response);
		
		/* Retrieving orders from other branches*/
		OrderDetails orderDetail= labService.retrieveBranchOrders(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		syncResponse.setRetrieveOrders(orderDetail);
		
		/* Retrieving lab details*/
		LabDTO labDetails= labService.getLab(sync.getHeader(),sync.getLastSyncTime(),currentSyncTime);
		syncResponse.setLabDetails(labDetails);
		
		syncResponse.setLastSynctime(df.format(currentSyncTime));
		return syncResponse;
	}

	/**
	 * @return the validator
	 */
	public SyncValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(SyncValidator validator) {
		this.validator = validator;
	}

	/**
	 * @return the syncDao
	 */
	public SyncDao getSyncDao() {
		return syncDao;
	}

	/**
	 * @param syncDao
	 *            the syncDao to set
	 */
	public void setSyncDao(SyncDao syncDao) {
		this.syncDao = syncDao;
	}

	/**
	 * @return the doctorService
	 */
	public DoctorService getDoctorService() {
		return doctorService;
	}

	/**
	 * @param doctorService
	 *            the doctorService to set
	 */
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	/**
	 * @return the patientService
	 */
	public PatientService getPatientService() {
		return patientService;
	}

	/**
	 * @param patientService
	 *            the patientService to set
	 */
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
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

	/**
	 * @return the netMdService
	 */
	public NetMdService getNetMdService() {
		return netMdService;
	}

	/**
	 * @param netMdService
	 *            the netMdService to set
	 */
	public void setNetMdService(NetMdService netMdService) {
		this.netMdService = netMdService;
	}

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	/**
	 * @return the labService
	 */
	public LabService getLabService() {
		return labService;
	}

	/**
	 * @param labService the labService to set
	 */
	public void setLabService(LabService labService) {
		this.labService = labService;
	}

	/**
	 * @return the resultService
	 */
	public ResultService getResultService() {
		return resultService;
	}

	/**
	 * @param resultService the resultService to set
	 */
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}

	/**
	 * @return the specimenService
	 */
	public SpecimenService getSpecimenService() {
		return specimenService;
	}

	/**
	 * @param specimenService the specimenService to set
	 */
	public void setSpecimenService(SpecimenService specimenService) {
		this.specimenService = specimenService;
	}

	/**
	 * @return the testService
	 */
	public TestService getTestService() {
		return testService;
	}

	/**
	 * @param testService the testService to set
	 */
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	


}
