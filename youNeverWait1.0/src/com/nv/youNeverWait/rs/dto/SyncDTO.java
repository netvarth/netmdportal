/**
 * SyncDTO.java
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Luciya Jose
 *
 */
public class SyncDTO {
	private HeaderDTO header;
	private String lastSyncTime;
	private List<DoctorDetail> newDoctorList = new ArrayList<DoctorDetail>();
	private List<DoctorDetail> updateDoctorList = new ArrayList<DoctorDetail>();
	private List<DoctorDetail> deleteDoctorList = new ArrayList<DoctorDetail>();
	private List<PatientDetail> newPatientList = new ArrayList<PatientDetail>();
	private List<PatientDetail> updatePatientList = new ArrayList<PatientDetail>();
	private List<PatientDetail> deletedPatientList = new ArrayList<PatientDetail>();
	private List<ScheduleDetail> newScheduleList = new ArrayList<ScheduleDetail>();
	private List<ScheduleDetail> updateScheduleList = new ArrayList<ScheduleDetail>();
	private List<ScheduleDetail> deleteScheduleList = new ArrayList<ScheduleDetail>();
	private List<NetMdUserDetail> newUserList = new ArrayList<NetMdUserDetail>();
	private List<NetMdUserDetail> updateUserList = new ArrayList<NetMdUserDetail>();
	private List<NetMdUserDetail> deleteUserList = new ArrayList<NetMdUserDetail>();
	private List<AppointmentDetailsDTO> newAppointmentList = new ArrayList<AppointmentDetailsDTO>();
	private List<AppointmentDetailsDTO> updatedAppointmentList = new ArrayList<AppointmentDetailsDTO>();
	private List<AppointmentDetailsDTO> deletedAppointmentList = new ArrayList<AppointmentDetailsDTO>();
	private List<UserInfoDetail> user = new ArrayList<UserInfoDetail>();
	private List<BillSummaryDTO> newBillList=new ArrayList<BillSummaryDTO>();
	private List<BillSummaryDTO> updateBillList=new ArrayList<BillSummaryDTO>();
//	private String freqType;
//	private int interval;
	
//	/**
//	 * @return the freqType
//	 */
//	public String getFreqType() {
//		return freqType;
//	}
//	/**
//	 * @param freqType the freqType to set
//	 */
//	public void setFreqType(String freqType) {
//		this.freqType = freqType;
//	}
//	/**
//	 * @return the interval
//	 */
//	public int getInterval() {
//		return interval;
//	}
//	/**
//	 * @param interval the interval to set
//	 */
//	public void setInterval(int interval) {
//		this.interval = interval;
//	}
//	
	/**
	 * @return the newBillList
	 */
	public List<BillSummaryDTO> getNewBillList() {
		return newBillList;
	}
	/**
	 * @param newBillList the newBillList to set
	 */
	public void setNewBillList(List<BillSummaryDTO> newBillList) {
		this.newBillList = newBillList;
	}
	/**
	 * @return the updateBillList
	 */
	public List<BillSummaryDTO> getUpdateBillList() {
		return updateBillList;
	}
	/**
	 * @param updateBillList the updateBillList to set
	 */
	public void setUpdateBillList(List<BillSummaryDTO> updateBillList) {
		this.updateBillList = updateBillList;
	}
	/**
	 * @return the newUserList
	 */
	public List<NetMdUserDetail> getNewUserList() {
		return newUserList;
	}
	/**
	 * @param newUserList the newUserList to set
	 */
	public void setNewUserList(List<NetMdUserDetail> newUserList) {
		this.newUserList = newUserList;
	}
	/**
	 * @return the updateUserList
	 */
	public List<NetMdUserDetail> getUpdateUserList() {
		return updateUserList;
	}
	/**
	 * @param updateUserList the updateUserList to set
	 */
	public void setUpdateUserList(List<NetMdUserDetail> updateUserList) {
		this.updateUserList = updateUserList;
	}
	/**
	 * @return the deleteUserList
	 */
	public List<NetMdUserDetail> getDeleteUserList() {
		return deleteUserList;
	}
	/**
	 * @param deleteUserList the deleteUserList to set
	 */
	public void setDeleteUserList(List<NetMdUserDetail> deleteUserList) {
		this.deleteUserList = deleteUserList;
	}
	/**
	 * @return the lastSyncTime
	 */
	public String getLastSyncTime() {
		return lastSyncTime;
	}
	/**
	 * @param lastSyncTime the lastSyncTime to set
	 */
	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}
	/**
	 * @return the header
	 */
	public HeaderDTO getHeader() {
		return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(HeaderDTO header) {
		this.header = header;
	}
	
	/**
	 * 
	 */
	public SyncDTO() {
		
	}
	
	
	
	public List<PatientDetail> getNewPatientList() {
		return newPatientList;
	}
	public void setNewPatientList(List<PatientDetail> newPatientList) {
		this.newPatientList = newPatientList;
	}
	/**
	 * @return the user
	 */
	public List<UserInfoDetail> getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(List<UserInfoDetail> user) {
		this.user = user;
	}
	/**
	 * @return the newDoctorList
	 */
	public List<DoctorDetail> getNewDoctorList() {
		return newDoctorList;
	}
	/**
	 * @param newDoctorList the newDoctorList to set
	 */
	public void setNewDoctorList(List<DoctorDetail> newDoctorList) {
		this.newDoctorList = newDoctorList;
	}
	/**
	 * @return the updateDoctorList
	 */
	public List<DoctorDetail> getUpdateDoctorList() {
		return updateDoctorList;
	}
	/**
	 * @param updateDoctorList the updateDoctorList to set
	 */
	public void setUpdateDoctorList(List<DoctorDetail> updateDoctorList) {
		this.updateDoctorList = updateDoctorList;
	}
	/**
	 * @return the deleteDoctorList
	 */
	public List<DoctorDetail> getDeleteDoctorList() {
		return deleteDoctorList;
	}
	/**
	 * @param deleteDoctorList the deleteDoctorList to set
	 */
	public void setDeleteDoctorList(List<DoctorDetail> deleteDoctorList) {
		this.deleteDoctorList = deleteDoctorList;
	}

	public List<PatientDetail> getUpdatePatientList() {
		return updatePatientList;
	}
	public void setUpdatePatientList(List<PatientDetail> updatePatientList) {
		this.updatePatientList = updatePatientList;
	}

	/**
	 * @return the newScheduleList
	 */
	public List<ScheduleDetail> getNewScheduleList() {
		return newScheduleList;
	}
	/**
	 * @param newScheduleList the newScheduleList to set
	 */
	public void setNewScheduleList(List<ScheduleDetail> newScheduleList) {
		this.newScheduleList = newScheduleList;
	}
	/**
	 * @return the updateScheduleList
	 */
	public List<ScheduleDetail> getUpdateScheduleList() {
		return updateScheduleList;
	}
	/**
	 * @param updateScheduleList the updateScheduleList to set
	 */
	public void setUpdateScheduleList(List<ScheduleDetail> updateScheduleList) {
		this.updateScheduleList = updateScheduleList;
	}
	/**
	 * @return the deleteScheduleList
	 */
	public List<ScheduleDetail> getDeleteScheduleList() {
		return deleteScheduleList;
	}
	/**
	 * @param deleteScheduleList the deleteScheduleList to set
	 */
	public void setDeleteScheduleList(List<ScheduleDetail> deleteScheduleList) {
		this.deleteScheduleList = deleteScheduleList;
	}
	public List<PatientDetail> getDeletedPatientList() {
		return deletedPatientList;
	}
	public void setDeletedPatientList(List<PatientDetail> deletedPatientList) {
		this.deletedPatientList = deletedPatientList;
	}
	public List<AppointmentDetailsDTO> getNewAppointmentList() {
		return newAppointmentList;
	}
	public void setNewAppointmentList(List<AppointmentDetailsDTO> newAppointmentList) {
		this.newAppointmentList = newAppointmentList;
	}
	public List<AppointmentDetailsDTO> getUpdatedAppointmentList() {
		return updatedAppointmentList;
	}
	public void setUpdatedAppointmentList(
			List<AppointmentDetailsDTO> updatedAppointmentList) {
		this.updatedAppointmentList = updatedAppointmentList;
	}
	public List<AppointmentDetailsDTO> getDeletedAppointmentList() {
		return deletedAppointmentList;
	}
	public void setDeletedAppointmentList(
			List<AppointmentDetailsDTO> deletedAppointmentList) {
		this.deletedAppointmentList = deletedAppointmentList;
	}

	
	
	
}