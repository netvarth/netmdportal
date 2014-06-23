/**
 * NetMdDaoImpl.java
 *
 * Jan 3, 2013
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.user.pl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.BranchStatusEnum;
import com.nv.youNeverWait.pl.entity.DoctorScheduleTbl;
import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabUserTypeEnum;
import com.nv.youNeverWait.pl.entity.NetmdBillTbl;
import com.nv.youNeverWait.pl.entity.NetmdBranchSystemInfoTbl;
import com.nv.youNeverWait.pl.entity.NetmdDoctorTbl;
import com.nv.youNeverWait.pl.entity.NetmdHealthMonitorTbl;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdLoginTbl;
import com.nv.youNeverWait.pl.entity.NetmdTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.pl.entity.OrganisationNetmdTbl;
import com.nv.youNeverWait.pl.entity.OrganisationTbl;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.pl.entity.NetmdPatientTbl;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.entity.SyncFreqTypeEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.AppointmentDTO;
import com.nv.youNeverWait.rs.dto.BillResponseDTO;
import com.nv.youNeverWait.rs.dto.BillSummaryDTO;
import com.nv.youNeverWait.rs.dto.BranchBillListResponseDTO;
import com.nv.youNeverWait.rs.dto.BranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdActivationResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDetail;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdDetail;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PassPhraseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalUserResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrieveNetmdListResponseDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.SystemHealthMonitorDetailList;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.NetMdDao;

public class NetMdDaoImpl extends GenericDaoHibernateImpl implements NetMdDao {

	@PersistenceContext()
	private EntityManager em;

	/**
	 * Creates a netmd account
	 * 
	 * @param netMd
	 * @return ResponseDTO
	 */
	@Transactional(readOnly = false)
	@Override
	public ResponseDTO create(NetMdDTO netMd) {
		ResponseDTO response = new ResponseDTO();
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		NetmdLoginTbl loginTbl = getLoginByUserName(netMd.getUserName());
		if (loginTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetMdAccountAlreadyExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		// save login details
		NetmdLoginTbl login = new NetmdLoginTbl();
		login.setUserName(netMd.getUserName());
		login.setUserType(NetmdUserTypeEnum.Owner.getDisplayName());
		String password = StringEncoder.encryptWithKey(netMd.getPassword()
				.trim());
		login.setPassword(password);
		save(login);

		// checking whether the netmd account with given name already exists or
		// not
		if (netMd.getName() != null) {
			String alphaDigitsOnly = netMd.getName().replaceAll(
					"[^a-zA-Z0-9]+", "");
			NetmdTbl dupNetMdTbl = (NetmdTbl) getNetMdByName(alphaDigitsOnly
					.toUpperCase().trim());
			if (dupNetMdTbl != null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.DuplicateNetMd);
				se.addParam(new Parameter(Constants.NAME, netMd.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		// save netmd details to netmd table
		NetmdTbl netMdTbl = new NetmdTbl();
		netMdTbl.setName(netMd.getName());
		netMdTbl.setOwnerFirstName(netMd.getOwnerFirstName());
		netMdTbl.setOwnerLastName(netMd.getOwnerLastName());
		netMdTbl.setOwnerEmail(netMd.getOwnerEmail());
		netMdTbl.setOwnerAddress(netMd.getOwnerAddress());
		netMdTbl.setOwnerMobile(netMd.getOwnerMobile());
		netMdTbl.setOwnerPhone(netMd.getOwnerPhone());
		netMdTbl.setNetmdLoginTbl(login);
		netMdTbl.setHeadOfficeAddress(netMd.getHeadOfficeAddress());
		netMdTbl.setHeadOfficeEmail(netMd.getHeadOfficeEmail());
		netMdTbl.setHeadOfficeMobile(netMd.getHeadOfficeMobile());
		netMdTbl.setHeadOfficeName(netMd.getHeadOfficeName());
		netMdTbl.setHeadOfficePhone(netMd.getHeadOfficePhone());
		netMdTbl.setStatus(StatusEnum.Active.getDisplayName());
		netMdTbl.setLogo(netMd.getLogo());
		Date createdTime = new Date();
		netMdTbl.setCreateDateTime(createdTime);
		netMdTbl.setUpdateDateTime(createdTime);
		if (superAdmin.getEnableSync() == false) {
			netMdTbl.setEnableSync(false);
		} else {
			netMdTbl.setEnableSync(true);
			netMdTbl.setSyncFreqType(superAdmin.getSyncFreqType());
			netMdTbl.setSyncTime(superAdmin.getSyncTime());
		}
		save(netMdTbl);
		response.setGlobalId(netMdTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieves all Netmd list after last synchronization time
	 * 
	 * @param syncTime
	 * @return RetrieveNetmdListResponseDTO
	 */
	@Override
	@Transactional
	public RetrieveNetmdListResponseDTO retrieveNetmdList(String syncTime,
			Date currentTime) {
		RetrieveNetmdListResponseDTO response = new RetrieveNetmdListResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date lastSyncTime = null;
		try {
			lastSyncTime = sdf.parse(syncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		List<NetMdDTO> newNetmdList = new ArrayList<NetMdDTO>();
		List<NetMdDTO> updateNetmdList = new ArrayList<NetMdDTO>();

		List<NetmdTbl> newNetmds = getNewNetmd(lastSyncTime, currentTime);
		for (NetmdTbl newNetmd : newNetmds) {
			NetMdDTO netmdDTO = new NetMdDTO();
			netmdDTO.setName(newNetmd.getName());
			netmdDTO.setOwnerFirstName(newNetmd.getOwnerFirstName());
			netmdDTO.setOwnerLastName(newNetmd.getOwnerLastName());
			netmdDTO.setOwnerAddress(newNetmd.getOwnerAddress());
			netmdDTO.setOwnerEmail(newNetmd.getOwnerEmail());
			netmdDTO.setOwnerMobile(newNetmd.getOwnerMobile());
			netmdDTO.setOwnerPhone(newNetmd.getOwnerPhone());
			netmdDTO.setHeadOfficeName(newNetmd.getHeadOfficeName());
			netmdDTO.setHeadOfficeAddress(newNetmd.getHeadOfficeAddress());
			netmdDTO.setHeadOfficeEmail(newNetmd.getHeadOfficeEmail());
			netmdDTO.setHeadOfficeMobile(newNetmd.getHeadOfficeMobile());
			netmdDTO.setHeadOfficePhone(newNetmd.getHeadOfficePhone());
			netmdDTO.setGlobalId(newNetmd.getId());
			netmdDTO.setStatus(newNetmd.getStatus());

			newNetmdList.add(netmdDTO);
		}

		List<NetmdTbl> updateNetmds = getUpdateNetmd(lastSyncTime, currentTime);
		for (NetmdTbl updateNetmd : updateNetmds) {
			NetMdDTO netmdDTO = new NetMdDTO();
			netmdDTO.setName(updateNetmd.getName());
			netmdDTO.setOwnerFirstName(updateNetmd.getOwnerFirstName());
			netmdDTO.setOwnerLastName(updateNetmd.getOwnerLastName());
			netmdDTO.setOwnerAddress(updateNetmd.getOwnerAddress());
			netmdDTO.setOwnerEmail(updateNetmd.getOwnerEmail());
			netmdDTO.setOwnerMobile(updateNetmd.getOwnerMobile());
			netmdDTO.setOwnerPhone(updateNetmd.getOwnerPhone());
			netmdDTO.setHeadOfficeName(updateNetmd.getHeadOfficeName());
			netmdDTO.setHeadOfficeAddress(updateNetmd.getHeadOfficeAddress());
			netmdDTO.setHeadOfficeEmail(updateNetmd.getHeadOfficeEmail());
			netmdDTO.setHeadOfficeMobile(updateNetmd.getHeadOfficeMobile());
			netmdDTO.setHeadOfficePhone(updateNetmd.getHeadOfficePhone());
			netmdDTO.setGlobalId(updateNetmd.getId());
			netmdDTO.setStatus(updateNetmd.getStatus());

			updateNetmdList.add(netmdDTO);

		}
		response.setNewNetmdList(newNetmdList);
		response.setUpdateNetmdList(updateNetmdList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieves all Netmd branch list after last synchronization time
	 * 
	 * @param syncTime
	 * @return RetrieveNetmdBranchListResponseDTO
	 */
	@Override
	@Transactional
	public RetrieveNetmdBranchListResponseDTO retrieveNetmdBranchList(
			String syncTime, Date currentTime) {
		RetrieveNetmdBranchListResponseDTO response = new RetrieveNetmdBranchListResponseDTO();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date lastSyncTime = null;
		try {
			lastSyncTime = sdf.parse(syncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<NetMdBranchDTO> newNetmdBranchList = new ArrayList<NetMdBranchDTO>();
		List<NetMdBranchDTO> updateNetmdBranchList = new ArrayList<NetMdBranchDTO>();

		List<NetmdBranchTbl> newNetmdBranches = getNewNetmdBranches(
				lastSyncTime, currentTime);
		for (NetmdBranchTbl newBranch : newNetmdBranches) {

			NetMdBranchDTO newNetmdBranch = new NetMdBranchDTO();
			newNetmdBranch.setGlobalId(newBranch.getId());
			newNetmdBranch.setName(newBranch.getName());
			newNetmdBranch.setAddress(newBranch.getAddress());
			newNetmdBranch.setPhone(newBranch.getPhone());
			newNetmdBranch.setMobile(newBranch.getMobile());
			newNetmdBranch.setEmail(newBranch.getEmail());
			newNetmdBranch.setNetMdId(newBranch.getNetmdTbl().getId());
			newNetmdBranch.setStatus(newBranch.getStatus());

			newNetmdBranchList.add(newNetmdBranch);
		}
		List<NetmdBranchTbl> updateNetmdBranches = getUpdateNetmdBranches(
				lastSyncTime, currentTime);
		for (NetmdBranchTbl updateBranch : updateNetmdBranches) {
			NetMdBranchDTO updateNetmdBranch = new NetMdBranchDTO();
			updateNetmdBranch.setGlobalId(updateBranch.getId());
			updateNetmdBranch.setName(updateBranch.getName());
			updateNetmdBranch.setAddress(updateBranch.getAddress());
			updateNetmdBranch.setPhone(updateBranch.getPhone());
			updateNetmdBranch.setMobile(updateBranch.getMobile());
			updateNetmdBranch.setEmail(updateBranch.getEmail());
			updateNetmdBranch.setNetMdId(updateBranch.getNetmdTbl().getId());
			updateNetmdBranch.setStatus(updateBranch.getStatus());

			updateNetmdBranchList.add(updateNetmdBranch);
		}
		response.setNewNetmdBranchList(newNetmdBranchList);
		response.setUpdateNetmdBranchList(updateNetmdBranchList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Creates a netmd branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO createBranch(NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		NetmdTbl netMd = getById(NetmdTbl.class, branch.getNetMdId());
		if (netMd == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getNetMdId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdBranchTbl netMdBranch = new NetmdBranchTbl();
		netMdBranch.setAddress(branch.getAddress());
		Date createdTime = new Date();
		netMdBranch.setName(branch.getName());
		netMdBranch.setStatus(BranchStatusEnum.Active.getDisplayName());
		netMdBranch.setCreateDateTime(createdTime);
		netMdBranch.setUpdateDateTime(createdTime);
		netMdBranch.setPhone(branch.getPhone());
		netMdBranch.setMobile(branch.getMobile());
		netMdBranch.setEmail(branch.getEmail());
		netMdBranch.setNetmdTbl(netMd);
		if (netMd.isEnableSync() == false) {
			netMdBranch.setEnableSync(false);
		} else {
			netMdBranch.setEnableSync(true);
			netMdBranch.setSyncFreqType(netMd.getSyncFreqType());
			netMdBranch.setSyncTime(netMd.getSyncTime());
		}
		save(netMdBranch);
		NetmdPassphraseTbl netMdPassPhrase = new NetmdPassphraseTbl();
		String passphrase = StringEncoder.getKeyvalue(StringEncoder.getKey());
		netMdPassPhrase.setPassPhrase(passphrase);
		netMdPassPhrase.setNetmdBranchTbl(netMdBranch);
		netMdPassPhrase.setMacId(null);
		netMdPassPhrase.setPrimaryDevice(true);
		save(netMdPassPhrase);
		for (int i = 1; i < branch.getNumberOfDevices(); i++) {
			NetmdPassphraseTbl netMdPassPhraseTbl = new NetmdPassphraseTbl();
			String pass = StringEncoder.getKeyvalue(StringEncoder.getKey());
			netMdPassPhraseTbl.setPassPhrase(pass);
			netMdPassPhraseTbl.setNetmdBranchTbl(netMdBranch);
			netMdPassPhrase.setMacId(null);
			netMdPassPhraseTbl.setPrimaryDevice(false);
			save(netMdPassPhraseTbl);
		}
		/*Saving record in organisationNetmdTbl if any*/
		if(branch.getOrganisationName()!=null && !branch.getOrganisationName().equals("")){
			String alphaDigitsOnly = branch.getOrganisationName().replaceAll(
					"[^a-zA-Z0-9]+", "");
			OrganisationTbl organisationTbl= getOrganisationByName(alphaDigitsOnly.toUpperCase().trim());
			if(organisationTbl==null){
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidOrganisationName);
				se.setDisplayErrMsg(true);
				throw se;
			}
			OrganisationNetmdTbl orgNetmdTbl= new OrganisationNetmdTbl();
			orgNetmdTbl.setOrganisationTbl(organisationTbl);
			orgNetmdTbl.setNetmdBranchTbl(netMdBranch);
			save(orgNetmdTbl);
		}
		
		response.setSuccess(true);
		response.setGlobalId(netMdBranch.getId());
		return response;
	}

	/**
	 * Update netmd account
	 * 
	 * @param netMd
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO update(NetMdDTO netMd) {
		ResponseDTO response = new ResponseDTO();
		if (netMd.getGlobalId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netMd
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdTbl netmdTbl = getById(NetmdTbl.class, netMd.getGlobalId());
		if (netmdTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netMd
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* checking whether the name already exists */
		String alphaDigitsOnly = netMd.getName()
				.replaceAll("[^a-zA-Z0-9]+", "");
		NetmdTbl netMdTbl = (NetmdTbl) getNetMdByName(alphaDigitsOnly
				.toUpperCase().trim());
		if (netMdTbl != null) {
			if (netMdTbl.getId() != netMd.getGlobalId()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NetMdNameExists);
				se.addParam(new Parameter(Constants.NAME, netMd.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		netmdTbl.setName(netMd.getName());
		netmdTbl.setOwnerFirstName(netMd.getOwnerFirstName());
		netmdTbl.setOwnerLastName(netMd.getOwnerLastName());
		netmdTbl.setOwnerEmail(netMd.getOwnerEmail());
		netmdTbl.setOwnerAddress(netMd.getOwnerAddress());
		netmdTbl.setOwnerMobile(netMd.getOwnerMobile());
		netmdTbl.setOwnerPhone(netMd.getOwnerPhone());
		netmdTbl.setHeadOfficeAddress(netMd.getHeadOfficeAddress());
		netmdTbl.setHeadOfficeEmail(netMd.getHeadOfficeEmail());
		netmdTbl.setHeadOfficeMobile(netMd.getHeadOfficeMobile());
		netmdTbl.setHeadOfficeName(netMd.getHeadOfficeName());
		netmdTbl.setHeadOfficePhone(netMd.getHeadOfficePhone());
		netmdTbl.setUpdateDateTime(new Date());
		netmdTbl.setLogo(netMd.getLogo());
		update(netmdTbl);

		response.setGlobalId(netmdTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * Delete netMd
	 * 
	 * @param netmdId
	 * @return ResponseDTO
	 */
	@Transactional(readOnly = false)
	@Override
	public ResponseDTO delete(int netMdId) {
		ResponseDTO response = new ResponseDTO();
		NetmdTbl netmd = getById(NetmdTbl.class, netMdId);
		if (netmd == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		netmd.setStatus(StatusEnum.InActive.getDisplayName());
		for (NetmdBranchTbl netMdBranchTbl : netmd.getNetmdBranchTbls()) {

			for (NetmdPatientTbl patientTbl : netMdBranchTbl.getNetmdPatientTbls()) {
				patientTbl.setStatus(StatusEnum.InActive.getDisplayName());
				update(patientTbl);
			}
			for (NetmdUserTbl netmdUserTbl : netMdBranchTbl.getNetmdUserTbls()) {
				netmdUserTbl.setStatus(StatusEnum.InActive.getDisplayName());
				update(netmdUserTbl);
			}
			for (PatientAppointmentTbl patientAppointmentTbl : netMdBranchTbl
					.getPatientAppointmentTbls()) {
				patientAppointmentTbl.setStatus(StatusEnum.InActive
						.getDisplayName());
				update(patientAppointmentTbl);
			}
			for (NetmdDoctorTbl doctorTbl : netMdBranchTbl.getNetmdDoctorTbls()) {
				doctorTbl.setStatus(StatusEnum.InActive.getDisplayName());
				update(doctorTbl);
			}
			for (DoctorScheduleTbl doctorScheduleTbl : netMdBranchTbl
					.getDoctorScheduleTbls()) {
				doctorScheduleTbl.setStatus(StatusEnum.InActive
						.getDisplayName());
				update(doctorScheduleTbl);
			}
			netMdBranchTbl.setStatus(StatusEnum.InActive.getDisplayName());
			update(netMdBranchTbl);
		}
		update(netmd);
		response.setSuccess(true);
		return response;
	}

	/**
	 * View a netmd account
	 */
	@Transactional
	@Override
	public NetMdViewResponseDTO view(int netMdId) {
		NetmdTbl netmdTbl = getById(NetmdTbl.class, netMdId);
		if (netmdTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netMdId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetMdDTO netMd = new NetMdDTO();
		netMd.setGlobalId(netmdTbl.getId());
		netMd.setName(netmdTbl.getName());
		netMd.setOwnerAddress(netmdTbl.getOwnerAddress());
		netMd.setOwnerEmail(netmdTbl.getOwnerEmail());
		netMd.setOwnerMobile(netmdTbl.getOwnerMobile());
		netMd.setOwnerFirstName(netmdTbl.getOwnerFirstName());
		netMd.setOwnerLastName(netmdTbl.getOwnerLastName());
		netMd.setOwnerPhone(netmdTbl.getOwnerPhone());
		netMd.setHeadOfficeAddress(netmdTbl.getHeadOfficeAddress());
		netMd.setHeadOfficeEmail(netmdTbl.getHeadOfficeEmail());
		netMd.setHeadOfficeMobile(netmdTbl.getHeadOfficeMobile());
		netMd.setHeadOfficePhone(netmdTbl.getHeadOfficePhone());
		netMd.setHeadOfficeName(netmdTbl.getHeadOfficeName());
		netMd.setUserName(netmdTbl.getNetmdLoginTbl().getUserName());
		netMd.setPassword(netmdTbl.getNetmdLoginTbl().getPassword());
		netMd.setUserType(netmdTbl.getNetmdLoginTbl().getUserType());
		netMd.setLogo(netmdTbl.getLogo());
		NetMdViewResponseDTO response = new NetMdViewResponseDTO();
		response.setNetMd(netMd);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Updates a branch details
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO updateBranch(NetMdBranchDTO branch) {
		ResponseDTO response = new ResponseDTO();
		NetmdTbl netMd = getById(NetmdTbl.class, branch.getNetMdId());
		if (netMd == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getNetMdId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdBranchTbl netMdBranch = (NetmdBranchTbl) getBranchByNetMdId(
				branch.getGlobalId(), branch.getNetMdId());
		if (netMdBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		netMdBranch.setName(branch.getName());
		netMdBranch.setAddress(branch.getAddress());
		netMdBranch.setPhone(branch.getPhone());
		netMdBranch.setMobile(branch.getMobile());
		netMdBranch.setEmail(branch.getEmail());
		netMdBranch.setUpdateDateTime(new Date());
		update(netMdBranch);
		/*updating record in organisationNetmdTbl if any*/
		if(branch.getOrganisationName()!=null && !branch.getOrganisationName().equals("")){
			OrganisationTbl organisationTbl= getOrganisationByName(branch.getOrganisationName().trim());
			if(organisationTbl==null){
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidOrganisationName);
				se.setDisplayErrMsg(true);
				throw se;
			}
			OrganisationNetmdTbl orgNetmdTbl= getOrganisationByNetmd(netMdBranch.getId());
			if(orgNetmdTbl!=null){
			orgNetmdTbl.setOrganisationTbl(organisationTbl);
			update(orgNetmdTbl);
			}
		}
		
		response.setGlobalId(netMdBranch.getId());
		response.setSuccess(true);
		return response;
	}

	

	/**
	 * view a branch details
	 * 
	 * @param globalId
	 * @return BranchResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public NetMdBranchResponseDTO viewBranch(int netMdBranchId) {
		NetMdBranchResponseDTO response = new NetMdBranchResponseDTO();
		NetmdBranchTbl netMdBranch = getById(NetmdBranchTbl.class,
				netMdBranchId);
		if (netMdBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetMdBranchDTO branch = new NetMdBranchDTO();
		branch.setName(netMdBranch.getName());
		branch.setAddress(netMdBranch.getAddress());
		branch.setMobile(netMdBranch.getMobile());
		branch.setPhone(netMdBranch.getPhone());
		branch.setStatus(netMdBranch.getStatus());
		branch.setGlobalId(netMdBranch.getId());
		branch.setNetMdId(netMdBranch.getNetmdTbl().getId());
		branch.setEmail(netMdBranch.getEmail());
		List<NetmdPassphraseTbl> passPhrases = getMacPassPhraseByNetMdBranch(netMdBranchId);
		List<PassPhraseDTO> passPhraseList = new ArrayList<PassPhraseDTO>();
		branch.setNumberOfDevices(passPhrases.size());
		for (NetmdPassphraseTbl netmdPassphraseTbl : passPhrases) {
			PassPhraseDTO pass = new PassPhraseDTO();
			pass.setPassPhrase(netmdPassphraseTbl.getPassPhrase());
			pass.setMacId(netmdPassphraseTbl.getMacId());
			if (netmdPassphraseTbl.isPrimaryDevice()) {
				pass.setPrimary(true);
			} else {
				pass.setPrimary(false);
			}
			passPhraseList.add(pass);
		}
		
		branch.setPassPhrase(passPhraseList);
		OrganisationNetmdTbl orgNetmdTbl= getOrganisationByNetmd(netMdBranch.getId());
		if(orgNetmdTbl!=null){
			branch.setOrganisationName(orgNetmdTbl.getNetmdBranchTbl().getName());
		
		}
		response.setBranch(branch);
		response.setSuccess(true);
		return response;
	}

	/**
	 * deletes a branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO deleteBranch(int netMdBranchId) {
		ResponseDTO response = new ResponseDTO();
		NetmdBranchTbl netmdBranch = getById(NetmdBranchTbl.class,
				netMdBranchId);
		if (netmdBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		netmdBranch.setStatus(StatusEnum.InActive.getDisplayName());

		for (NetmdPatientTbl patientTbl : netmdBranch.getNetmdPatientTbls()) {
			patientTbl.setStatus(StatusEnum.InActive.getDisplayName());
			update(patientTbl);
		}
		for (NetmdUserTbl netmdUserTbl : netmdBranch.getNetmdUserTbls()) {
			netmdUserTbl.setStatus(StatusEnum.InActive.getDisplayName());
			update(netmdUserTbl);
		}
		for (PatientAppointmentTbl patientAppointmentTbl : netmdBranch
				.getPatientAppointmentTbls()) {
			patientAppointmentTbl.setStatus(StatusEnum.InActive
					.getDisplayName());
			update(patientAppointmentTbl);
		}
		for (NetmdDoctorTbl doctorTbl : netmdBranch.getNetmdDoctorTbls()) {
			doctorTbl.setStatus(StatusEnum.InActive.getDisplayName());
			update(doctorTbl);
		}
		for (DoctorScheduleTbl doctorScheduleTbl : netmdBranch
				.getDoctorScheduleTbls()) {
			doctorScheduleTbl.setStatus(StatusEnum.InActive.getDisplayName());
			update(doctorScheduleTbl);
		}

		update(netmdBranch);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method performed for deleting users from NetmdUserTbl
	 * 
	 * @param globalId
	 */
	@Override
	@Transactional
	public ResponseDTO deleteUser(int globalId) {
		ResponseDTO response = new ResponseDTO();
		NetmdUserTbl user = getById(NetmdUserTbl.class, globalId);
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdUser);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		user.setStatus(StatusEnum.InActive.getDisplayName());
		user.setUpdateDateTime(new Date());
		update(user);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Returns netmd branch details and set Mac Id
	 * 
	 * @param header
	 * @return
	 */
	@Override
	@Transactional
	public NetMdActivationResponseDTO activateNetMd(HeaderDTO header) {
		NetMdActivationResponseDTO response = new NetMdActivationResponseDTO();
		NetmdPassphraseTbl netmdpassPhrase = (NetmdPassphraseTbl) getByPassphrase(header
				.getPassPhrase());
		if (netmdpassPhrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassphrase);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if (netmdpassPhrase.getMacId() != null) {
			if (!netmdpassPhrase.getMacId().equals(header.getMacId())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.MacIdExists);
				se.addParam(new Parameter(Constants.PASSPHRASE, netmdpassPhrase
						.getPassPhrase()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			/*
			 * Retrieve all the details to the primary device after the device
			 * is crashed
			 */
			else if (netmdpassPhrase.isPrimaryDevice()) {

				Date currentSyncTime = new Date(); // setting current date time
				Date firstSyncTime = new Date(0);

				/* Retrieving Doctor List */
				List<DoctorDetail> retrievedDoctors = new ArrayList<DoctorDetail>();

				/* Getting doctors list from doctor tbl */
				List<NetmdDoctorTbl> DoctorsList = getDoctors(firstSyncTime,
						netmdpassPhrase.getId(), netmdpassPhrase
								.getNetmdBranchTbl().getId(), currentSyncTime);
				for (NetmdDoctorTbl doctor : DoctorsList) {

					retrievedDoctors.add(new DoctorDetail(doctor));
				}
				response.setRetrieveDoctorsList(retrievedDoctors);

				/* Retrieving Patient list */

				List<PatientDetail> retrievedPatients = new ArrayList<PatientDetail>();

				/* Getting patients list from patient tbl */
				List<NetmdPatientTbl> patients = getPatients(firstSyncTime,
						netmdpassPhrase.getId(), netmdpassPhrase
								.getNetmdBranchTbl().getId(), currentSyncTime);
				for (NetmdPatientTbl patientObj : patients) {
					PatientDetail patientDetail = new PatientDetail(patientObj);
					retrievedPatients.add(patientDetail);
				}

				response.setRetrievePatients(retrievedPatients);

				/* Retrieving Schedule List */

				List<ScheduleDetail> retrievedSchedules = new ArrayList<ScheduleDetail>();
				/* Getting users list */
				List<DoctorScheduleTbl> scheduleList = getSchedules(
						firstSyncTime, netmdpassPhrase.getId(), netmdpassPhrase
								.getNetmdBranchTbl().getId(), currentSyncTime);
				for (DoctorScheduleTbl schedule : scheduleList) {
					retrievedSchedules.add(new ScheduleDetail(schedule));
				}
				response.setRetrieveScheduleList(retrievedSchedules);

				/* Retrieve all appointments created by primary device */
				List<AppointmentDTO> retrieveAppointments = new ArrayList<AppointmentDTO>();
				/* get appointment list from table */
				List<PatientAppointmentTbl> appointmentList = getAppointments(
						firstSyncTime, netmdpassPhrase.getId(), netmdpassPhrase
								.getNetmdBranchTbl().getId(), currentSyncTime);

				for (PatientAppointmentTbl appointmnets : appointmentList) {
					retrieveAppointments.add(new AppointmentDTO(appointmnets));
				}
				response.setRetrieveAppointments(retrieveAppointments);
			} // End of else if loop
		}

		netmdpassPhrase.setMacId(header.getMacId());
		update(netmdpassPhrase);

		NetmdBranchTbl netMdBranch = getById(NetmdBranchTbl.class,
				netmdpassPhrase.getNetmdBranchTbl().getId());
		if (netMdBranch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}

		// setting whether it's primary or not
		if (netmdpassPhrase.isPrimaryDevice()) {
			response.setPrimary(true);
		} else {
			response.setPrimary(false);
		}

		// setting branch details
		NetMdBranchDTO branchDetail = new NetMdBranchDTO();
		branchDetail.setName(netMdBranch.getName());
		branchDetail.setAddress(netMdBranch.getAddress());
		branchDetail.setStatus(netMdBranch.getStatus());
		branchDetail.setPhone(netMdBranch.getPhone());
		branchDetail.setMobile(netMdBranch.getMobile());
		branchDetail.setGlobalId(netMdBranch.getId());
		branchDetail.setEmail(netMdBranch.getEmail());
		branchDetail.setNetMdId(netMdBranch.getNetmdTbl().getId());
		response.setBranch(branchDetail);

		NetmdTbl netMdTbl = getById(NetmdTbl.class, netMdBranch.getNetmdTbl()
				.getId());
		// setting netmd details
		NetMdDTO netMd = new NetMdDTO();
		netMd.setOwnerFirstName(netMdTbl.getOwnerFirstName());
		netMd.setOwnerLastName(netMdTbl.getOwnerLastName());
		netMd.setName(netMdTbl.getName());
		netMd.setOwnerAddress(netMdTbl.getOwnerAddress());
		netMd.setOwnerEmail(netMdTbl.getOwnerEmail());
		netMd.setOwnerMobile(netMdTbl.getOwnerMobile());
		netMd.setOwnerPhone(netMdTbl.getOwnerPhone());
		netMd.setStatus(netMdTbl.getStatus());
		netMd.setGlobalId(netMdTbl.getId());
		netMd.setHeadOfficeAddress(netMdTbl.getHeadOfficeAddress());
		netMd.setHeadOfficeEmail(netMdTbl.getHeadOfficeEmail());
		netMd.setHeadOfficeMobile(netMdTbl.getHeadOfficeMobile());
		netMd.setHeadOfficeName(netMdTbl.getHeadOfficeName());
		netMd.setHeadOfficePhone(netMdTbl.getHeadOfficePhone());
		netMd.setLogo(netMdTbl.getLogo());
		netMd.setPassword(netMdTbl.getNetmdLoginTbl().getPassword());
		netMd.setUserName(netMdTbl.getNetmdLoginTbl().getUserName());
		netMd.setUserType(netMdTbl.getNetmdLoginTbl().getUserType());
		response.setNetmd(netMd);

		List<NetmdUserTbl> users = getNetMdUsersByBranchId(netMdBranch.getId());
		List<NetMdUserDetail> userList = new ArrayList<NetMdUserDetail>();
		for (NetmdUserTbl netmdUserTbl : users) {
			NetMdUserDetail user = new NetMdUserDetail();
			user.setAddress(netmdUserTbl.getAddress());
			user.setEmail(netmdUserTbl.getEmail());
			user.setFirstName(netmdUserTbl.getFirstName());
			user.setGlobalId(netmdUserTbl.getId());
			user.setLastName(netmdUserTbl.getLastName());
			user.setMobile(netmdUserTbl.getMobile());
			user.setNetMdBranchId(netmdUserTbl.getNetmdBranchTbl().getId());
			user.setPassword(netmdUserTbl.getNetmdLoginTbl().getPassword());
			user.setPhone(netmdUserTbl.getPhone());
			user.setStatus(netmdUserTbl.getStatus());
			user.setUserType(netmdUserTbl.getNetmdLoginTbl().getUserType());
			user.setUserName(netmdUserTbl.getNetmdLoginTbl().getUserName());
			userList.add(user);
		}
		response.setUser(userList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Retrieve netmd owner details
	 * 
	 * @param netMdBranchId
	 * @return NetMdBranchOwnerDetails
	 */

	@Override
	@Transactional
	public NetMdBranchOwnerDetails getBranchOwners(int netMdBranchId) {
		NetmdBranchTbl netmdBranchTbl = getById(NetmdBranchTbl.class,
				netMdBranchId);
		NetMdBranchOwnerDetails response = new NetMdBranchOwnerDetails();
		response.setOwnerEmail(netmdBranchTbl.getNetmdTbl().getOwnerEmail());
		response.setOwnerFirstName(netmdBranchTbl.getNetmdTbl()
				.getOwnerFirstName());
		response.setOwnerLastName(netmdBranchTbl.getNetmdTbl()
				.getOwnerLastName());
		response.setNetMdName(netmdBranchTbl.getNetmdTbl().getName());
		List<NetmdPassphraseTbl> netmdPassphraseTbls = getMacPassPhraseByNetMdBranch(netMdBranchId);
		List<String> passPhrases = new ArrayList<String>();
		for (NetmdPassphraseTbl netmdPassphraseTbl : netmdPassphraseTbls) {
			if (netmdPassphraseTbl.isPrimaryDevice()) {
				response.setPrimaryPassPhrase(netmdPassphraseTbl
						.getPassPhrase());
			} else {
				passPhrases.add(netmdPassphraseTbl.getPassPhrase());
			}

		}
		response.setPassPhrase(passPhrases);
		response.setBranchName(netmdBranchTbl.getName());
		return response;
	}

	/**
	 * Creates netmd user
	 * 
	 * @param netmdUser
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO createUser(HeaderDTO header, NetMdUserDetail netMdUser) {
		ResponseDTO response = new ResponseDTO();
		NetmdUserTbl user = new NetmdUserTbl();

		NetmdBranchTbl branch = getById(NetmdBranchTbl.class,
				header.getBranchId());
		if (branch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			NetmdPassphraseTbl passPhrase = getByPassphrase(header
					.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getHeadOfficeId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			} else {
				user.setNetmdPassphraseTbl(passPhrase);
			}
		}

		NetmdUserTbl netmdUserTbl = getNetMdUserByEmail(netMdUser.getEmail(),
				header.getBranchId());
		if (netmdUserTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetMdUserAlreadyExists);
			se.addParam(new Parameter(Constants.EMAIL, netMdUser.getEmail()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		NetmdLoginTbl loginTbl = getLoginByUserName(netMdUser.getUserName());
		if (loginTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetMdUserAccountAlreadyExists);
			se.addParam(new Parameter(Constants.USER, netMdUser.getUserName()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		// save user login details to NetMdLoginTbl
		NetmdLoginTbl login = new NetmdLoginTbl();
		login.setUserName(netMdUser.getUserName());
		String password = new StringEncoder().encryptWithKey(netMdUser
				.getPassword().trim());
		login.setUserType(netMdUser.getUserType());
		login.setPassword(password);
		save(login);

		Date date = new Date();
		user.setAddress(netMdUser.getAddress());
		user.setCreateDateTime(date);
		user.setEmail(netMdUser.getEmail());
		user.setMobile(netMdUser.getMobile());
		user.setFirstName(netMdUser.getFirstName());
		user.setLastName(netMdUser.getLastName());
		user.setNetmdBranchTbl(branch);
		user.setNetmdLoginTbl(login);
		user.setPhone(netMdUser.getPhone());
		user.setStatus(StatusEnum.Active.getDisplayName());
		user.setUpdateDateTime(date);
		user.setUserType(netMdUser.getUserType());
		save(user);
		response.setGlobalId(user.getId());
		response.setId(netMdUser.getId());
		return response;
	}

	/**
	 * Update netmd user
	 * 
	 * @param netmdUser
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO updateUser(HeaderDTO header, NetMdUserDetail netMdUser) {
		ResponseDTO response = new ResponseDTO();
		NetmdUserTbl user = getById(NetmdUserTbl.class, netMdUser.getGlobalId());
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdUser);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netMdUser
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdUserTbl netmdUserTbl = getNetMdUserByEmail(netMdUser.getEmail(),
				netMdUser.getNetMdBranchId());
		// duplicate checking
		if (netmdUserTbl != null && netmdUserTbl.getId() != user.getId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetMdUserAlreadyExists);
			se.addParam(new Parameter(Constants.EMAIL, netMdUser.getEmail()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		user.setAddress(netMdUser.getAddress());
		user.setEmail(netMdUser.getEmail());
		user.setMobile(netMdUser.getMobile());
		user.setFirstName(netMdUser.getFirstName());
		user.setLastName(netMdUser.getLastName());
		user.setPhone(netMdUser.getPhone());
		user.setUserType(netMdUser.getUserType());
		user.setUpdateDateTime(new Date());
		update(user);
		NetmdLoginTbl login = getById(NetmdLoginTbl.class, user
				.getNetmdLoginTbl().getId());
		login.setUserType(netMdUser.getUserType());
		update(login);

		response.setGlobalId(user.getId());
		response.setId(netMdUser.getId());
		return response;
	}

	/**
	 * View netmd user
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public NetMdUserDTO viewUser(int globalId) {
		NetMdUserDTO response = new NetMdUserDTO();
		NetMdUserDetail userDetail = new NetMdUserDetail();
		NetmdUserTbl user = getById(NetmdUserTbl.class, globalId);
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdUser);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		userDetail.setAddress(user.getAddress());
		userDetail.setEmail(user.getEmail());
		userDetail.setFirstName(user.getFirstName());
		userDetail.setGlobalId(user.getId());
		userDetail.setLastName(user.getLastName());
		userDetail.setMobile(user.getMobile());
		userDetail.setNetMdBranchId(user.getNetmdBranchTbl().getId());
		userDetail.setNetMdId(user.getNetmdBranchTbl().getNetmdTbl().getId());
		userDetail.setPhone(user.getPhone());
		userDetail.setStatus(user.getStatus());
		if (user.getNetmdLoginTbl() != null) {
			userDetail.setUserName(user.getNetmdLoginTbl().getUserName());
			userDetail.setUserType(user.getNetmdLoginTbl().getUserType());
		}
		response.setUser(userDetail);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to retrieve details of a lab owner/user
	 * 
	 * @param login
	 * @return UserCredentials
	 */
	@Override
	@Transactional
	public UserCredentials getUserCredentials(LoginDTO login) {

		UserCredentials user = new UserCredentials();
		NetmdLoginTbl userLogin = getNetMdUserByName(login.getUserName().trim());
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (userLogin.getUserType().equals(
				LabUserTypeEnum.Owner.getDisplayName())) {
			NetmdTbl netmdTbl = getNetMdByLoginId(userLogin.getId());
			if (netmdTbl == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(netmdTbl.getOwnerEmail());
			user.setFirstName(netmdTbl.getOwnerFirstName());
			user.setLastName(netmdTbl.getOwnerLastName());
			user.setUserName(userLogin.getUserName());
		} else {
			NetmdUserTbl netmdUser = getNetMdUserByLoginId(userLogin.getId());
			if (netmdUser == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(netmdUser.getEmail());
			user.setFirstName(netmdUser.getFirstName());
			user.setLastName(netmdUser.getLastName());
			user.setUserName(userLogin.getUserName());
		}
		return user;
	}

	/**
	 * Method performed to retrieve created updated and deleted users list from
	 * portal
	 * 
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return RetrievalUserResponseDTO
	 */
	@Override
	@Transactional
	public RetrievalUserResponseDTO retrieveUserList(String syncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		RetrievalUserResponseDTO response = new RetrievalUserResponseDTO();
		List<NetMdUserDetail> retrieveUsers = new ArrayList<NetMdUserDetail>();
		Date lastSyncTime = null;
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		try {
			lastSyncTime = df.parse(syncTime);
		} catch (ParseException e) {
			e.printStackTrace();
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}

		int netmdPassphraseId = getNetmdPassphrase(passPhrase, netmdBranchId);
		if (netmdPassphraseId == 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}

		/* Getting users list */
		List<NetmdUserTbl> usersList = getUsers(lastSyncTime,
				netmdPassphraseId, netmdBranchId, currentSyncTime);
		for (NetmdUserTbl user : usersList) {
			retrieveUsers.add(new NetMdUserDetail(user));
		}

		response.setRetrieveUsersList(retrieveUsers);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords) {
		ResponseDTO response = new ResponseDTO();
		String encOldPassword = StringEncoder.encryptWithKey(passwords
				.getOldPassword().trim());
		NetmdLoginTbl login = getNetMdUserByName(passwords.getUsername());
		if (login == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNotExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!login.getPassword().equals(encOldPassword)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PasswordNotExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		String encNewPassword = StringEncoder.encryptWithKey(passwords
				.getNewPassword().trim());
		login.setPassword(encNewPassword);
		update(login);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to reset password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO resetPassword(LoginDTO login) {
		ResponseDTO response = new ResponseDTO();

		String newPassword = StringEncoder.encryptWithKey(login.getPassword());
		String decrypedUserName = StringEncoder.decryptWithStaticKey(login
				.getUserName());
		NetmdLoginTbl userLogin = getNetMdUserByName(decrypedUserName);
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		userLogin.setPassword(newPassword);
		update(userLogin);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method for making device primary
	 */
	@Override
	@Transactional
	public ResponseDTO makePrimary(HeaderDTO header) {
		ResponseDTO response = new ResponseDTO();
		List<NetmdPassphraseTbl> passphraseObjList = getPassPhraseByBranch(header
				.getBranchId());
		for (NetmdPassphraseTbl netmdPassphraseTblObj : passphraseObjList) {
			if (header.getPassPhrase().equals(
					netmdPassphraseTblObj.getPassPhrase())) {
				netmdPassphraseTblObj.setPrimaryDevice(true);
				update(netmdPassphraseTblObj);
			} else {
				netmdPassphraseTblObj.setPrimaryDevice(false);
				update(netmdPassphraseTblObj);
			}

		}
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method which clears mac Id
	 * 
	 * @param header
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO clearMacId(HeaderDTO header) {

		ResponseDTO response = new ResponseDTO();
		/* Query to get netmd record for given netMd id */
		NetmdTbl netMd = getById(NetmdTbl.class, header.getHeadOfficeId());
		/* Setting error message when netmd Id is incorrect */
		if (netMd == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Quert to get the list of branches for given Netmd id */
		List<NetmdBranchTbl> branches = (List<NetmdBranchTbl>) getNetMdBranches(netMd
				.getId());
		/*
		 * Setting error message when there is no branches corresponding to the
		 * Netmd given
		 */
		if (branches.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		int count = 0;
		/*
		 * Iterating through each branches and retrieving the netmdPassPhrase
		 * record corresponding to it
		 */
		for (NetmdBranchTbl netMdBranch : branches) {
			count++;
			/*
			 * Checking whether netmd branch id matches with given netMd branch
			 * id
			 */
			if (netMdBranch.getId() == header.getBranchId()) {
				/* Query for retrieving netmd branch passphrase */
				NetmdPassphraseTbl branchPassPhrase = getMacPassPhraseByBranch(
						netMdBranch.getId(), header.getPassPhrase());
				/*
				 * Setting error message when there is no netmd branch
				 * passphrase
				 */
				if (branchPassPhrase == null) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.PassPhraseNotExist);
					se.setDisplayErrMsg(false);
					throw se;
				}// end of branch passphrase if loop
				branchPassPhrase.setMacId(null);
				update(branchPassPhrase);
				response.setSuccess(true);
				return response;

			} else if (count == branches.size()) {
				/*
				 * Setting error message when no branches matches to the given
				 * branch id
				 */
				ServiceException se = new ServiceException(
						ErrorCodeEnum.BranchMissMatch);
				se.addParam(new Parameter(Constants.ID, Integer.toString(header
						.getBranchId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.NetMdDao#createBill(com.nv.youNeverWait
	 * .rs.dto.BillSummaryDTO, com.nv.youNeverWait.rs.dto.HeaderDTO)
	 */
	@Override
	@Transactional
	public BillResponseDTO createBill(BillSummaryDTO newBill, HeaderDTO header) {
		BillResponseDTO response = new BillResponseDTO();
		DateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		NetmdPassphraseTbl passPhrase = null;
		// Validate header details
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			passPhrase = getByPassphrase(header.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getHeadOfficeId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		NetmdPatientTbl existingPatient = getById(NetmdPatientTbl.class,
				Integer.parseInt(newBill.getPatientGlobalId()));
		if (existingPatient == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, newBill
					.getPatientGlobalId()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date createdDateTime = new Date();
		NetmdBillTbl newBillTbl = new NetmdBillTbl();
		newBillTbl.setAmountPaid(newBill.getAmountPaid());
		newBillTbl.setBillAmount(newBill.getBillAmount());
		newBillTbl.setCreatedDateTime(createdDateTime);
		try {
			newBillTbl.setOrderDate(sdf.parse(newBill.getOrderDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newBillTbl.setPatientName(newBill.getPatientName());
		newBillTbl.setNetmdPatientTbl(existingPatient);
		newBillTbl.setPayStatus(newBill.getPayStatus());
		newBillTbl.setUid(newBill.getUid());
		newBillTbl.setNetmdTbl(passPhrase.getNetmdBranchTbl().getNetmdTbl());
		newBillTbl.setNetmdBranchTbl(passPhrase.getNetmdBranchTbl());
		newBillTbl.setUpdatedDateTime(createdDateTime);
		save(newBillTbl);
		response.setUid(newBillTbl.getUid());
		response.setGlobalId(newBillTbl.getId());
		response.setSuccess(true);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.NetMdDao#updateBill(com.nv.youNeverWait
	 * .rs.dto.BillSummaryDTO, com.nv.youNeverWait.rs.dto.HeaderDTO)
	 */
	@Override
	@Transactional
	public BillResponseDTO updateBill(BillSummaryDTO updatedBill,
			HeaderDTO header) {
		BillResponseDTO response = new BillResponseDTO();
		DateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITHOUT_TIME);
		NetmdPassphraseTbl passPhrase = null;
		// Validate header details
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			passPhrase = getByPassphrase(header.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getHeadOfficeId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		NetmdBillTbl netMdBill = getById(NetmdBillTbl.class,
				updatedBill.getGlobalId());
		if (netMdBill == null) {
			// set error
		}

		NetmdPatientTbl existingPatient = getById(NetmdPatientTbl.class,
				Integer.parseInt(updatedBill.getPatientGlobalId()));
		if (existingPatient == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PatientNotFound);
			se.addParam(new Parameter(Constants.ID, updatedBill
					.getPatientGlobalId()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date updatedDateTime = new Date();
		netMdBill.setAmountPaid(updatedBill.getAmountPaid());
		netMdBill.setBillAmount(updatedBill.getBillAmount());

		try {
			netMdBill.setOrderDate(sdf.parse(updatedBill.getOrderDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		netMdBill.setPatientName(updatedBill.getPatientName());
		netMdBill.setNetmdPatientTbl(existingPatient);
		netMdBill.setPayStatus(updatedBill.getPayStatus());
		netMdBill.setUid(updatedBill.getUid());
		netMdBill.setNetmdTbl(passPhrase.getNetmdBranchTbl().getNetmdTbl());
		netMdBill.setNetmdBranchTbl(passPhrase.getNetmdBranchTbl());
		netMdBill.setUpdatedDateTime(updatedDateTime);
		update(netMdBill);

		response.setUid(netMdBill.getUid());
		response.setGlobalId(netMdBill.getId());
		response.setSuccess(true);

		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.NetMdDao#setNetMdSync(com.nv.youNeverWait
	 * .rs.dto.SyncFreqDTO)
	 */
	@Override
	@Transactional
	public SyncFreqResponseDTO setNetMdSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		Date newDate = new Date();
		NetmdTbl netmd = getById(NetmdTbl.class, sync.getNetmdId());
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		if (netmd != null) {
			if (superAdmin.getEnableSync() == false) {
				netmd.setEnableSync(false);
			} else
				netmd.setEnableSync(sync.isEnableSync());
			netmd.setUpdateDateTime(newDate);
			update(netmd);
			/**** Setting values when the sync is enabled ****/
			if (netmd.isEnableSync() == true) {

				/****** Checking sync values with global sync time *****/
				checkSync(superAdmin.getSyncFreqType(), sync.getSyncFreqType(),
						sync.getSyncTime(), superAdmin.getSyncTime());

				netmd.setSyncTime(sync.getSyncTime());
				netmd.setSyncFreqType(sync.getSyncFreqType());
				netmd.setUpdateDateTime(newDate);
				update(netmd);
			} else {
				if (sync.isEnableSync())
					response.setMsg(Constants.MESSAGE);
				/****** Setting all branches of the lab as disabled *******/
				for (NetmdBranchTbl netmdBranch : netmd.getNetmdBranchTbls()) {
					netmdBranch.setEnableSync(netmd.isEnableSync());
					netmdBranch.setUpdateDateTime(newDate);
					update(netmdBranch);

				}// end of for loop
			}

		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(sync
					.getNetmdId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		response.setSuccess(true);
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.NetMdDao#setNetMdBranchSync(com.nv.
	 * youNeverWait.rs.dto.SyncFreqDTO)
	 */
	@Override
	@Transactional
	public SyncFreqResponseDTO setNetMdBranchSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		Date newDate = new Date();
		NetmdBranchTbl netmdBranch = getById(NetmdBranchTbl.class,
				sync.getNetmdBranchId());
		if (netmdBranch != null) {
			if (netmdBranch.getNetmdTbl().isEnableSync() == false) {
				netmdBranch.setEnableSync(false);
			} else {
				SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
				if (superAdmin.getEnableSync() == false) {
					netmdBranch.setEnableSync(false);
				} else {
					netmdBranch.setEnableSync(sync.isEnableSync());
				}
			}
			netmdBranch.setUpdateDateTime(newDate);
			update(netmdBranch);
			if (netmdBranch.isEnableSync() == true) {
				/**
				 * Checking whether branch sync time is greater than netmd sync
				 * time
				 **/
				checkSync(netmdBranch.getNetmdTbl().getSyncFreqType(),
						sync.getSyncFreqType(), sync.getSyncTime(), netmdBranch
								.getNetmdTbl().getSyncTime());

				netmdBranch.setSyncTime(sync.getSyncTime());
				netmdBranch.setSyncFreqType(sync.getSyncFreqType());
				netmdBranch.setUpdateDateTime(newDate);
				update(netmdBranch);
			} else {
				if (sync.isEnableSync())
					response.setMsg(Constants.MESSAGE);
			}
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		response.setSuccess(true);
		return response;
	}

	/**
	 * @param priorSyncFreqType
	 * @param syncFreqType
	 * @param syncTime
	 * @param priorSyncTime
	 */
	private void checkSync(String priorSyncFreqType, String syncFreqType,
			int syncTime, int priorSyncTime) {
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of daily if loop
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.HOURLY.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SynctimeExceeds);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (syncFreqType.equals(SyncFreqTypeEnum.HOURLY
					.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of hourly if loop
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.MINUTES.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())
					|| syncFreqType.equals(SyncFreqTypeEnum.HOURLY
							.getDisplayName())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SynctimeExceeds);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (syncFreqType.equals(SyncFreqTypeEnum.MINUTES
					.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of minutes if loop
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.LabDao#getLabSyncDetails(int)
	 */
	@Override
	@Transactional
	public SyncFreqDTO getNetmdSyncDetails(int netmdId) {
		SyncFreqDTO sync = new SyncFreqDTO();
		NetmdTbl netmd = getById(NetmdTbl.class, netmdId);
		if (netmd != null) {
			sync.setSyncFreqType(netmd.getSyncFreqType());
			sync.setSyncTime(netmd.getSyncTime());
			sync.setEnableSync(netmd.isEnableSync());
			sync.setSuccess(true);
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMd);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netmdId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

		return sync;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.youNeverWait.user.pl.dao.LabDao#getBranchSyncDetails(int)
	 */
	@Override
	@Transactional
	public SyncFreqDTO getBranchSyncDetails(int branchId) {
		SyncFreqDTO sync = new SyncFreqDTO();
		NetmdBranchTbl netmdBranch = getById(NetmdBranchTbl.class, branchId);
		if (netmdBranch != null) {
			sync.setSyncFreqType(netmdBranch.getSyncFreqType());
			sync.setSyncTime(netmdBranch.getSyncTime());
			sync.setEnableSync(netmdBranch.isEnableSync());
			sync.setSuccess(true);
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branchId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

		return sync;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.NetMdDao#viewBranchSystemInfoDetails(
	 * java.lang.String)
	 */
	@Override
	@Transactional
	public BranchSystemInfoDetails viewBranchSystemInfoDetails(String passphrase) {
		BranchSystemInfoDetails details = new BranchSystemInfoDetails();
		List<SystemHealthMonitorDetailList> healthMonitorList = new ArrayList<SystemHealthMonitorDetailList>();
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		NetmdPassphraseTbl netmdPassphrase = getByPassphrase(passphrase);
		if (netmdPassphrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassphrase);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/*
		 * Getting system default information like critical memory level, freq
		 * type and so on
		 */
		NetmdBranchSystemInfoTbl systemInfo = getSystemDetailsByNetmdBranchId(
				netmdPassphrase.getNetmdBranchTbl().getId(),
				netmdPassphrase.getId());
		if (systemInfo == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetmdBranchSystemInfoNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		details.setBranchId(netmdPassphrase.getNetmdBranchTbl().getId());
		details.setBranchName(netmdPassphrase.getNetmdBranchTbl().getName());
		details.setCriticalCpuLevel(Float.toString(systemInfo
				.getCriticalCpuLevel()));
		details.setCriticalHardDiskSpaceLevel(Float.toString(systemInfo
				.getCriticalHardDiskLevel()));
		details.setCriticalMemoryLevel(Float.toString(systemInfo
				.getCriticalMemoryLevel()));
		details.setFreqType(systemInfo.getFreqType());
		details.setIntervalTime(Integer.toString(systemInfo.getIntervalTime()));
		/** Query for getting total number of records */
		int totalRecords = getTotalHealthMonitorRecords(netmdPassphrase.getId());
		int startIndex = 0;
		if (totalRecords > 10)
			startIndex = totalRecords - 10;
		/* Getting last 10 records of system health monitor details */
		List<NetmdHealthMonitorTbl> healthMonitorTblList = getMonitorDetailsByBranchId(
				netmdPassphrase.getId(), startIndex);
		if (healthMonitorTblList.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.SystemMonitorDetailsNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for (NetmdHealthMonitorTbl hMonitor : healthMonitorTblList) {
			SystemHealthMonitorDetailList systemHealth = new SystemHealthMonitorDetailList();

			systemHealth.setCpuUsage(hMonitor.getFreeCpuSpace());
			systemHealth.setHardDiskSpaceUasge(hMonitor.getFreeHardDiskSpace());
			systemHealth.setMemoryUsage(hMonitor.getFreeMemorySpace());
			systemHealth.setCreatedDateTime(sdf.format(hMonitor
					.getCreatedDateTime()));
			healthMonitorList.add(systemHealth);
			details.setTotalCpu(hMonitor.getTotalCpuSpace());
			details.setTotalHardDisk(hMonitor.getTotalHardDiskSpace());
			details.setTotalMemory(hMonitor.getTotalMemorySpace());
		}
		details.setHealthMonitorList(healthMonitorList);

		details.setSuccess(true);
		return details;
	}

	public void checkHeader(HeaderDTO header) {
		NetmdPassphraseTbl passPhrase = getByPassphrase(header.getPassPhrase());
		if (passPhrase == null
				|| passPhrase.getMacId() == null
				|| !passPhrase.getMacId().equals(header.getMacId())
				|| passPhrase.getNetmdBranchTbl().getId() != header
						.getBranchId()
				|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
						.getHeadOfficeId()) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetMdAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Override
	@Transactional
	public NetMdBranchListResponseDTO getNetMdBrnchList(int organisationId) {
		NetMdBranchListResponseDTO response = new NetMdBranchListResponseDTO();
		List<NetMdBranchDetail> netMdBranchDetails = new ArrayList<NetMdBranchDetail>();
		List<OrganisationNetmdTbl> orgNetmdTblList= getOrganisationNetMdBranches(organisationId);
		for(OrganisationNetmdTbl orgNetmd:orgNetmdTblList){
			netMdBranchDetails.add(new NetMdBranchDetail(orgNetmd.getNetmdBranchTbl()));
		}

		response.setNetmdBranch(netMdBranchDetails);
		response.setSuccess(true);
		return response;
		
	}

	private List<OrganisationNetmdTbl> getOrganisationNetMdBranches(int organisationId) {
		javax.persistence.Query query = em.createQuery(Query.GET_ORGANISATION_NETMD_BRANCH_LIST);
		query.setParameter("param1", organisationId);
		return executeQuery(OrganisationNetmdTbl.class, query);
	}

	private List<NetmdHealthMonitorTbl> getMonitorDetailsByBranchId(
			int passPhraseId, int startIndex) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_HEALTH_MONITORING_DETAILS);
		query.setParameter("param1", passPhraseId);
		query.setFirstResult(startIndex);
		query.setMaxResults(10);
		return executeQuery(NetmdHealthMonitorTbl.class, query);
	}

	/**
	 * get passphrase table list
	 * 
	 * @param branchId
	 * @return NetmdPassphraseTbl
	 */
	public List<NetmdPassphraseTbl> getPassPhraseByBranch(int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.NETMD_PASSPHRASE_BY_BRANCH);
		query.setParameter("param1", branchId);
		return executeQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * @param passPhraseId
	 * @return
	 */
	private int getTotalHealthMonitorRecords(int passPhraseId) {
		int totalRecords = 0;
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_TOTAL_RECORDS);
		query.setParameter("param1", passPhraseId);
		totalRecords = ((Number) query.getSingleResult()).intValue();
		return totalRecords;
	}

	/**
	 * @param netmdBranchId
	 * @param netmdPassphraseId
	 * @return
	 */
	private NetmdBranchSystemInfoTbl getSystemDetailsByNetmdBranchId(
			int netmdBranchId, int netmdPassphraseId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BRANCH_SYSTEM_DETAILS);
		query.setParameter("param1", netmdBranchId);
		query.setParameter("param2", netmdPassphraseId);
		return executeUniqueQuery(NetmdBranchSystemInfoTbl.class, query);
	}

	/**
	 * 
	 * @param branchId
	 * @return LabPassphraseTbl
	 */
	public NetmdPassphraseTbl getMacPassPhraseByBranch(int branchId,
			String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_PASSPHRASE_BY_BRANCH_ID);
		query.setParameter("param1", branchId);
		query.setParameter("param2", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * Method for retrieving all created updated and deleted users after last
	 * sync time
	 * 
	 * @param lastSyncTime
	 * @param netmdPassphraseId
	 * @param netmdBranchId
	 * @return NetmdUserTbl
	 */
	private List<NetmdUserTbl> getUsers(Date lastSyncTime,
			int netmdPassphraseId, int netmdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_NETMD_USERS);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", netmdPassphraseId);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(NetmdUserTbl.class, query);

	}

	/**
	 * 
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	public NetmdLoginTbl getNetMdUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetmdLoginTbl.class, query);
	}

	/**
	 * Method for getting netmd passphrase id
	 * 
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return netmd passphrase id
	 */
	public Integer getNetmdPassphrase(String passPhrase, int netmdBranchId) {
		int count = 0;
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_PASSPHRASE_ID);
		query.setParameter("param1", passPhrase);
		query.setParameter("param2", netmdBranchId);
		count = (Integer) query.getSingleResult();
		return count;
	}

	/**
	 * @param name
	 * @return
	 */
	private OrganisationTbl getOrganisationByName(String name) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ORGANISATION_BY_NAME);
		query.setParameter("param1", name);
		return executeUniqueQuery(OrganisationTbl.class, query);
	}
	
	/**
	 * 
	 * @param loginId
	 * @return NetmdTbl
	 */
	public NetmdTbl getNetMdByLoginId(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdTbl.class, query);
	}

	/**
	 * 
	 * @param loginId
	 * @return NetmdUserTbl
	 */
	public NetmdUserTbl getNetMdUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETMD_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetmdUserTbl.class, query);
	}

	/**
	 * To get netmd name by giving netmd id
	 * 
	 * @param netMdId
	 * @return netmd name
	 */
	@Override
	public String getNetMdName(int netMdId) {
		NetmdTbl netmdTbl = getById(NetmdTbl.class, netMdId);
		if (netmdTbl == null) {
			return "";
		}
		return netmdTbl.getName();
	}

	/**
	 * To get netmd branch name by giving netmd branch id
	 * 
	 * @param netMdBranchId
	 * @return netmd branch name
	 */
	@Override
	public String getNetMdBranchName(int netMdBranchId) {
		NetmdBranchTbl netMdBranchTbl = getById(NetmdBranchTbl.class,
				netMdBranchId);
		if (netMdBranchTbl == null) {
			return "";
		}
		return netMdBranchTbl.getName();
	}

	/**
	 * Retrieve netmd user by email
	 * 
	 * @param email
	 * @return NetmdUserTbl
	 */
	private NetmdUserTbl getNetMdUserByEmail(String email, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BY_EMAIL_AND_BRANCH);
		query.setParameter("param1", email.trim());
		query.setParameter("param2", branchId);
		return executeUniqueQuery(NetmdUserTbl.class, query);
	}

	/**
	 * @param netMdBranchId
	 * @return
	 */
	private List<NetmdPassphraseTbl> getMacPassPhraseByNetMdBranch(
			int netMdBranchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MAC_AND_PASSPHRASE_BY_NETMD_BRANCH);
		query.setParameter("param1", netMdBranchId);
		return (List<NetmdPassphraseTbl>) executeQuery(
				NetmdPassphraseTbl.class, query);
	}

	/**
	 * Retrieve netmd record by giving name
	 * 
	 * @param name
	 * @return NetmdTbl
	 */
	private NetmdTbl getNetMdByName(String name) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETMD_BY_NAME);
		query.setParameter("param1", name);
		return executeUniqueQuery(NetmdTbl.class, query);
	}

	/**
	 * Retrieve NetmdPassphraseTbl record by giving passPhrase
	 * 
	 * @param passPhrase
	 * @return NetmdPassphraseTbl
	 */
	private NetmdPassphraseTbl getByPassphrase(String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BRANCH_PASSPHRASE);
		query.setParameter("param1", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * Retrieve mac id from NetmdPassphraseTbl by giving passPhrase
	 * 
	 * @param passPhrase
	 * @return macId
	 */
	@Override
	@Transactional
	public String getMacByPassphrase(String passPhrase) {
		try {
			javax.persistence.Query query = em
					.createQuery(Query.GET_MAC_BY_PASSPHRASE);
			query.setParameter("param1", passPhrase);
			return (String) query.getSingleResult();
		} catch (Exception e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassphrase);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/**
	 * Retrieve NetmdLoginTbl record by giving userName
	 * 
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	private NetmdLoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGIN_BY_OWNER_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetmdLoginTbl.class, query);
	}

	/**
	 * Retrieve NetmdBranchTbl record by giving branchId and netMdId
	 * 
	 * @param branchId
	 *            and netMdId
	 * @return NetmdBranchTbl
	 */
	private NetmdBranchTbl getBranchByNetMdId(int branchId, int netMdId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETMD_BRANCH);
		query.setParameter("param1", branchId);
		query.setParameter("param2", netMdId);
		return executeUniqueQuery(NetmdBranchTbl.class, query);
	}

	/**
	 * Method to retrieve new netmd list
	 * 
	 * @param syncTime
	 * @return
	 */
	public List<NetmdTbl> getNewNetmd(Date syncTime, Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_NEW_NETMD);
		query.setParameter("param1", syncTime);
		query.setParameter("param2", currentTime);
		return executeQuery(NetmdTbl.class, query);
	}

	/**
	 * Method to retrieve updated netmd list
	 * 
	 * @param syncTime
	 * @return
	 */
	public List<NetmdTbl> getUpdateNetmd(Date syncTime, Date currentTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_UPDATE_NETMD);
		query.setParameter("param1", syncTime);
		query.setParameter("param2", currentTime);
		return executeQuery(NetmdTbl.class, query);
	}

	/**
	 * Method to get new netmd branches list
	 * 
	 * @param syncTime
	 * @return
	 */
	public List<NetmdBranchTbl> getNewNetmdBranches(Date syncTime,
			Date currentTime) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_NETMD_BRANCHES);
		query.setParameter("param1", syncTime);
		query.setParameter("param2", currentTime);
		return executeQuery(NetmdBranchTbl.class, query);
	}

	/**
	 * Method to get new netmd branches list
	 * 
	 * @param syncTime
	 * @return
	 */
	public List<NetmdBranchTbl> getNetMdBranches(int netMdId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BRANCHES);
		query.setParameter("param1", netMdId);
		return executeQuery(NetmdBranchTbl.class, query);
	}

	/**
	 * Method to retrieve updated netmd branches list
	 * 
	 * @param syncTime
	 * @return
	 */
	public List<NetmdBranchTbl> getUpdateNetmdBranches(Date syncTime,
			Date currentTime) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATE_NETMD_BRANCHES);
		query.setParameter("param1", syncTime);
		query.setParameter("param2", currentTime);
		return executeQuery(NetmdBranchTbl.class, query);
	}

	/**
	 * @param firstSyncTime
	 * @param passPhraseId
	 * @param netMdBranchId
	 * @param currentSyncTime
	 * @return
	 */
	private List<NetmdPatientTbl> getPatients(Date firstSyncTime, int passPhraseId,
			int netMdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_PATIENTS_FOR_PRIMARY);
		query.setParameter("param1", firstSyncTime);
		query.setParameter("param2", passPhraseId);
		query.setParameter("param3", netMdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(NetmdPatientTbl.class, query);
	}

	/**
	 * @param firstSyncTime
	 * @param passPhraseId
	 * @param id
	 * @param currentSyncTime
	 * @return
	 */
	private List<NetmdDoctorTbl> getDoctors(Date firstSyncTime, int passPhraseId,
			int netmdBranchId, Date currentSyncTime) {

		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_DOCTORS_FOR_PRIMARY);
		query.setParameter("param1", firstSyncTime);
		query.setParameter("param2", passPhraseId);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(NetmdDoctorTbl.class, query);
	}

	/**
	 * To retreive users in netmd branch by giving branchId
	 * 
	 * @param netMdBranchId
	 * @return NetmdUserTbl
	 */
	private List<NetmdUserTbl> getNetMdUsersByBranchId(int netMdBranchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_USRS_BY_NETMD_BRANCH);
		query.setParameter("param1", netMdBranchId);
		return (List<NetmdUserTbl>) executeQuery(NetmdUserTbl.class, query);
	}

	/**
	 * @param firstSyncTime
	 * @param id
	 * @param id2
	 * @param currentSyncTime
	 * @return
	 */
	private List<PatientAppointmentTbl> getAppointments(Date firstSyncTime,
			int netmdPassphraseId, int netmdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em.createQuery(Query.GET_APPOINTMENTS);
		query.setParameter("param1", firstSyncTime);
		query.setParameter("param2", netmdPassphraseId);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(PatientAppointmentTbl.class, query);
	}

	/**
	 * @param firstSyncTime
	 * @param id
	 * @param id2
	 * @param currentSyncTime
	 * @return
	 */
	private List<DoctorScheduleTbl> getSchedules(Date firstSyncTime,
			int netmdPassphraseId, int netmdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_SCHEDULES_FOR_PRIMARY);
		query.setParameter("param1", firstSyncTime);
		query.setParameter("param2", netmdPassphraseId);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(DoctorScheduleTbl.class, query);
	}

	private OrganisationNetmdTbl getOrganisationByNetmd(int netmdBranchId) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE_ORGANISATIONS);
		query.setParameter("param1", netmdBranchId);
		return executeUniqueQuery(OrganisationNetmdTbl.class, query);
	}
	
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.NetMdDao#systemCriticalDetails(com.nv
	 * .youNeverWait.rs.dto.BranchSystemInfoDetails)
	 */
	@Override
	@Transactional
	public ResponseDTO updateNetmdBranchSystemInfo(
			BranchSystemInfoDetails systemCriticalDetails) {
		ResponseDTO response = new ResponseDTO();
		NetmdPassphraseTbl netmdPassphrase = getByPassphrase(systemCriticalDetails
				.getPasspharse());
		if (netmdPassphrase == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassphrase);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetmdBranchSystemInfoTbl systemInfo = getSystemDetailsByNetmdBranchId(
				netmdPassphrase.getNetmdBranchTbl().getId(),
				netmdPassphrase.getId());
		if (systemInfo == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetmdBranchSystemInfoNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		systemInfo
				.setCriticalHardDiskLevel(Integer
						.parseInt(systemCriticalDetails
								.getCriticalHardDiskSpaceLevel()));
		systemInfo.setCriticalCpuLevel(Integer.parseInt(systemCriticalDetails
				.getCriticalCpuLevel()));
		systemInfo.setCriticalMemoryLevel(Integer
				.parseInt(systemCriticalDetails.getCriticalMemoryLevel()));
		systemInfo.setFreqType(systemCriticalDetails.getFreqType());
		systemInfo.setIntervalTime(Integer.parseInt(systemCriticalDetails
				.getIntervalTime()));
		update(systemInfo);

		response.setSuccess(true);
		return response;
	}



	@Override
	public NetMdDTO getUpdateNetMd(String lastSyncTime, Date currentSyncTime,HeaderDTO header) {
		NetMdDTO netmdDTO=null;
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date lastsync = null;
		try {
			lastsync = sdf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
	
		NetmdTbl netmdTbl = getUpdateNetmdDetails(lastsync, currentSyncTime,header);
		if(netmdTbl!=null){
			netmdDTO=new NetMdDTO();
			netmdDTO.setName(netmdTbl.getName());
			netmdDTO.setOwnerFirstName(netmdTbl.getOwnerFirstName());
			netmdDTO.setOwnerLastName(netmdTbl.getOwnerLastName());
			netmdDTO.setOwnerAddress(netmdTbl.getOwnerAddress());
			netmdDTO.setOwnerEmail(netmdTbl.getOwnerEmail());
			netmdDTO.setOwnerMobile(netmdTbl.getOwnerMobile());
			netmdDTO.setOwnerPhone(netmdTbl.getOwnerPhone());
			netmdDTO.setHeadOfficeName(netmdTbl.getHeadOfficeName());
			netmdDTO.setHeadOfficeAddress(netmdTbl.getHeadOfficeAddress());
			netmdDTO.setHeadOfficeEmail(netmdTbl.getHeadOfficeEmail());
			netmdDTO.setHeadOfficeMobile(netmdTbl.getHeadOfficeMobile());
			netmdDTO.setHeadOfficePhone(netmdTbl.getHeadOfficePhone());
			netmdDTO.setLogo(netmdTbl.getLogo());
			netmdDTO.setGlobalId(netmdTbl.getId());
			netmdDTO.setStatus(netmdTbl.getStatus());
		//	netmdDTO.setUpdateDateTime(currentSyncTime.toString());

		}
		return netmdDTO;
	}

	private NetmdTbl getUpdateNetmdDetails(Date lastSyncTime,
			Date currentSyncTime,HeaderDTO header) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATE_NETMD_DETAILS);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", currentSyncTime);
		query.setParameter("param3",header.getHeadOfficeId());
		return executeUniqueQuery(NetmdTbl.class, query);
	}

	@Override
	public NetMdBranchDTO getUpdateNetmdBranch(String lastSyncTime,
			Date currentSyncTime,HeaderDTO header) {
		NetMdBranchDTO netmdBranchDTO=null;
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		Date lastsync = null;
		try {
			lastsync = sdf.parse(lastSyncTime);
		} catch (ParseException e) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidSyncTime);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		
		NetmdBranchTbl netmdbranchTbl=getUpdateNetmdBranchDetails(lastsync, currentSyncTime,header);
		if(netmdbranchTbl!=null){
			netmdBranchDTO=new NetMdBranchDTO();
			netmdBranchDTO.setGlobalId(netmdbranchTbl.getId());
			netmdBranchDTO.setName(netmdbranchTbl.getName());
			netmdBranchDTO.setAddress(netmdbranchTbl.getAddress());
			netmdBranchDTO.setPhone(netmdbranchTbl.getPhone());
			netmdBranchDTO.setMobile(netmdbranchTbl.getMobile());
			netmdBranchDTO.setEmail(netmdbranchTbl.getEmail());
			netmdBranchDTO.setNetMdId(netmdbranchTbl.getNetmdTbl().getId());
			netmdBranchDTO.setStatus(netmdbranchTbl.getStatus());	
			
		}
		return netmdBranchDTO;
	
	}

	private NetmdBranchTbl getUpdateNetmdBranchDetails(Date lastSyncTime,
			Date currentSyncTime, HeaderDTO header) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATE_NETMD_BRANCH_DETAILS);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", currentSyncTime);
		query.setParameter("param3",header.getBranchId());
		return executeUniqueQuery(NetmdBranchTbl.class, query);
	}

	@Override
	@Transactional
	public BranchBillListResponseDTO getBranchBillAmount(String netmdBranchId,
			String fromDate, String toDate) {
		BranchBillListResponseDTO billResponse= new BranchBillListResponseDTO();
		int branchId=Integer.parseInt(netmdBranchId);
		SimpleDateFormat sdf= new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date frmDate = null;
		Date tDate = null;
		try {
			frmDate = sdf.parse(fromDate);
			tDate=sdf.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Object[]> billDetails= getBillTotalAmountDetails(branchId,frmDate,tDate);
		if(billDetails.isEmpty()|| billDetails.size()==0 ||billDetails.equals(null)){
			throw new ServiceException(ErrorCodeEnum.BillPaymentsNull);
		}
		for(Object[] billPayment:billDetails){
		if (billPayment[0]!=null&& billPayment[1]!=null){
				double bllAmt=(Double) billPayment[0];
				double amtPd=(Double) billPayment[1];
				billResponse.setTotalBillAmt(bllAmt);
				billResponse.setTotalAmtPaid(amtPd);
				billResponse.setTotalAmtDue(bllAmt-amtPd);
		  	}
		}
		
		return billResponse;
	}

	private List<Object[]> getBillTotalAmountDetails(int branchId, Date frmDate,
			Date tDate) {
		List<Object[]> response=null;
		try{
			javax.persistence.Query query = em
					.createQuery(Query.GET_NETMD_BILL_DETAILS);
			query.setParameter("param1", branchId);
			query.setParameter("param2", frmDate);
			query.setParameter("param3",tDate);
			response=executeQuery(Object[].class,query);
		}
		catch (RuntimeException e) {
			e.printStackTrace();
		}
		return response;
	}

}
