/**
 * DoctorDaoImpl.java
 *
 * Dec 20, 2012
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
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.DoctorAchievementTbl;
import com.nv.youNeverWait.pl.entity.DoctorEducationalQualificationTbl;
import com.nv.youNeverWait.pl.entity.DoctorExpertiseTbl;
import com.nv.youNeverWait.pl.entity.DoctorMembershipTbl;
import com.nv.youNeverWait.pl.entity.DoctorPracticeExperienceTbl;
import com.nv.youNeverWait.pl.entity.DoctorScheduleTbl;
import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.entity.NetmdLoginTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.DoctorAchievementDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorExperienceDTO;
import com.nv.youNeverWait.rs.dto.DoctorExpertiseDTO;
import com.nv.youNeverWait.rs.dto.DoctorLoginDTO;
import com.nv.youNeverWait.rs.dto.DoctorMembershipDTO;
import com.nv.youNeverWait.rs.dto.DoctorQualificationDTO;
import com.nv.youNeverWait.rs.dto.DoctorViewResponseDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.RetrievalDoctorResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.DoctorDao;

public class DoctorDaoImpl extends GenericDaoHibernateImpl implements DoctorDao {

	@PersistenceContext()
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.DoctorDao#create(com.nv.youNeverWait.
	 * rs.dto.DoctorDetail, int, java.lang.String,
	 * com.nv.youNeverWait.rs.dto.LoginDTO)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nv.youNeverWait.user.pl.dao.DoctorDao#create(com.nv.youNeverWait.
	 * rs.dto.DoctorDetail, int, java.lang.String,
	 * com.nv.youNeverWait.rs.dto.LoginDTO)
	 */

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
		/* Checking userName and password existing or not */
		NetmdLoginTbl netmdLogin = getLoginByUserNameAndUserType(
				decrypedUserName, NetmdUserTypeEnum.Doctor.getDisplayName());

		if (netmdLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		netmdLogin.setPassword(newPassword);
		update(netmdLogin);

		List<DoctorTbl> doctorList = getDoctorByLoginId(netmdLogin.getId());
		if (doctorList.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidDoctorLogin);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netmdLogin
					.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		for (DoctorTbl doctr : doctorList) {
			doctr.setUpdateDateTime(new Date());
			update(doctr);
		}
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to perform doctor creation
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO create(DoctorDetail doctor, HeaderDTO header) {

		ResponseDTO response = new ResponseDTO();
		DateFormat df = new SimpleDateFormat(
				Constants.DATE_FORMAT_WITH_TIME_SECONDS);
		DoctorTbl newDoctor = new DoctorTbl();

		// Validate header details
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			NetmdPassphraseTbl passPhrase = getByPassphrase(header
					.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getNetMdBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getNetMdId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			} else {
				newDoctor.setNetmdBranchTbl(passPhrase.getNetmdBranchTbl());
				newDoctor.setNetmdPassphraseTbl(passPhrase);
			}
		}

		/* Checking userName and password existing or not */
		NetmdLoginTbl existingUser = (NetmdLoginTbl) getLoginByUserNameAndUserType(
				doctor.getEmail().trim(),
				NetmdUserTypeEnum.Doctor.getDisplayName());
		if (existingUser != null) {
			/* Checking doctor with same login exists */
			DoctorTbl DoctorLogin = getExistingDoctorWithLogin(
					existingUser.getId(), header.getNetMdBranchId());
			if (DoctorLogin != null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.LoginExists);
				se.setDisplayErrMsg(true);
				throw se;
			}
			newDoctor.setNetmdLoginTbl(existingUser);

		} else {

			NetmdLoginTbl loginTbl = new NetmdLoginTbl();
			loginTbl.setUserName(doctor.getEmail());
			loginTbl.setUserType(NetmdUserTypeEnum.Doctor.getDisplayName());
			save(loginTbl);
			newDoctor.setNetmdLoginTbl(loginTbl);
		}

		/* Checking doctor duplication */
		List<DoctorTbl> existingDoc = getExistingDoctors(doctor.getEmail(),
				header.getNetMdBranchId());
		if (existingDoc.size() != 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorEmailExists);
			se.addParam(new Parameter(Constants.EMAIL, doctor.getEmail()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		/* Saving doctor info in doctor tbl */
		newDoctor.setFirstName(doctor.getFirstName());
		newDoctor.setLastName(doctor.getLastName());
		newDoctor.setAddress(doctor.getAddress());
		newDoctor.setDateOfBirth(doctor.getDateOfBirth());
		newDoctor.setGender(doctor.getGender());
		newDoctor.setWorkHistory(doctor.getWorkHistory());
		newDoctor.setDesignation(doctor.getDesignation());
		newDoctor.setSpecialization(doctor.getSpecialization());
		newDoctor.setEmail(doctor.getEmail());
		newDoctor.setMobile(doctor.getMobile());
		newDoctor.setPhone(doctor.getPhone());
		newDoctor.setWorkingPlaces(doctor.getWorkingPlaces());
		if (doctor.getConsultationInterval() != null
				&& !doctor.getConsultationInterval().equals("")) {
			newDoctor.setConsultationInterval(Integer.parseInt(doctor
					.getConsultationInterval()));
		}

		newDoctor.setStatus(StatusEnum.Active.getDisplayName());
		Date createdTime = new Date();
		newDoctor.setCreateDateTime(createdTime);
		/*
		 * Setting updated time of doctor tbl depending upon the login password
		 * existing or not
		 */
		if (existingUser != null) {
			if (existingUser.getPassword() != null) {
				Date doctorUpdatedTime = new Date();// get current date time for
													// updatedDateTime field
				String olddate = df.format(doctorUpdatedTime); // converting
																// date to
																// string
				long oldDateInMillis = 0;
				try {
					oldDateInMillis = df.parse(olddate).getTime(); // getting
																	// minutes
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String seconds = "5";
				String newDateAdds = df.format(new Date(oldDateInMillis
						+ Long.valueOf(seconds) * 1000)); // new upadted time
															// after adding
															// seconds in string
				Date newUpdatedTime;
				try {
					newUpdatedTime = df.parse(newDateAdds);
					newDoctor.setUpdateDateTime(newUpdatedTime);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				newDoctor.setUpdateDateTime(createdTime);
			}
		} else {
			newDoctor.setUpdateDateTime(createdTime);
		}

		save(newDoctor);

		// Saving doctor educational qualification details
		if (!doctor.getDoctorQualifications().isEmpty()) {
			for (DoctorQualificationDTO doctorQualification : doctor
					.getDoctorQualifications()) {
				createDoctorQualification(newDoctor, doctorQualification);
			}
		}

		// Saving doctor achievement details
		if (!doctor.getAchievement().isEmpty()) {
			for (DoctorAchievementDTO doctorAchievement : doctor
					.getAchievement()) {
				createDoctorAchievements(newDoctor, doctorAchievement);
			}
		}

		// Saving doctor experience details
		if (!doctor.getDoctorExperience().isEmpty()) {
			for (DoctorExperienceDTO doctorExperience : doctor
					.getDoctorExperience()) {
				createDoctorExperience(newDoctor, doctorExperience);
			}
		}
		// Saving doctor expertise details
		if (!doctor.getExpertise().isEmpty()) {
			for (DoctorExpertiseDTO doctorExpertise : doctor.getExpertise()) {
				createDoctorExpertise(newDoctor, doctorExpertise);
			}
		}
		// Saving doctor membership details
		if (!doctor.getMembership().isEmpty()) {
			for (DoctorMembershipDTO doctorMembership : doctor.getMembership()) {
				createDoctorMembership(newDoctor, doctorMembership);
			}
		}
		response.setSuccess(true);
		response.setCreateDateTime(df.format(newDoctor.getCreateDateTime()));
		response.setUpdateDateTime(df.format(newDoctor.getUpdateDateTime()));
		response.setGlobalId(newDoctor.getId());// global id
		response.setId(doctor.getId());// local id
		return response;
	}

	/**
	 * Method to update doctor details
	 * 
	 * @param doctor
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO update(DoctorDetail doctor, HeaderDTO header) {

		ResponseDTO response = new ResponseDTO();
		DateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);

		// Validate header details
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			NetmdPassphraseTbl passPhrase = getByPassphrase(header
					.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getNetMdBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getNetMdId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		if (doctor.getEmail() != null && !doctor.getEmail().equals("")) {

			/* Checking doctor duplication */
			List<DoctorTbl> existingDoctrs = getExistingDoctors(
					doctor.getEmail(), header.getNetMdBranchId());
			if (existingDoctrs.size() != 0) {
				for (DoctorTbl doc : existingDoctrs) {
					if (doctor.getGlobalId() != doc.getId()) {
						ServiceException se = new ServiceException(
								ErrorCodeEnum.DoctorEmailExists);
						se.addParam(new Parameter(Constants.EMAIL, doctor
								.getEmail()));
						se.setDisplayErrMsg(true);
						throw se;
					}
				}
			}
		}
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class,
				doctor.getGlobalId());
		if (doctorTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(doctor
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		doctorTbl.setFirstName(doctor.getFirstName());
		doctorTbl.setLastName(doctor.getLastName());
		doctorTbl.setAddress(doctor.getAddress());
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date dob = null;
		if (doctor.getDateOfBirth() != null) {
			try {
				dob = df.parse(doctor.getDateOfBirth());
			} catch (ParseException e) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
			doctorTbl.setDateOfBirth(dob.toString());
		}

		doctorTbl.setGender(doctor.getGender());
		doctorTbl.setWorkHistory(doctor.getWorkHistory());
		doctorTbl.setDesignation(doctor.getDesignation());
		doctorTbl.setSpecialization(doctor.getSpecialization());
		doctorTbl.setEmail(doctor.getEmail());
		doctorTbl.setMobile(doctor.getMobile());
		doctorTbl.setPhone(doctor.getPhone());
		doctorTbl.setWorkingPlaces(doctor.getWorkingPlaces());
		if (doctor.getConsultationInterval() != null
				&& !doctor.getConsultationInterval().equals("")) {
			doctorTbl.setConsultationInterval(Integer.parseInt(doctor
					.getConsultationInterval()));
		}
		doctorTbl.setUpdateDateTime(new Date());
		update(doctorTbl);

		// Deleting each table record related to the doctor
		List<DoctorAchievementTbl> doctorAchievement = getAchievementByDoctorId(doctor
				.getGlobalId());
		if (!doctorAchievement.isEmpty()) {
			for (DoctorAchievementTbl achievement : doctorAchievement)
				delete(achievement);
		}
		List<DoctorEducationalQualificationTbl> doctorQualification = getQualificationByDoctorId(doctor
				.getGlobalId());
		if (!doctorQualification.isEmpty()) {
			for (DoctorEducationalQualificationTbl educationQlfctn : doctorQualification)
				delete(educationQlfctn);
		}
		List<DoctorExpertiseTbl> doctorExpertise = getExpertiseByDoctorId(doctor
				.getGlobalId());
		if (!doctorExpertise.isEmpty()) {
			for (DoctorExpertiseTbl expert : doctorExpertise)
				delete(expert);
		}
		List<DoctorMembershipTbl> doctorMembership = getMembershipByDoctorId(doctor
				.getGlobalId());
		if (!doctorMembership.isEmpty()) {
			for (DoctorMembershipTbl membershp : doctorMembership)
				delete(membershp);
		}
		List<DoctorPracticeExperienceTbl> doctorExperience = getExperienceByDoctorId(doctor
				.getGlobalId());
		if (!doctorExperience.isEmpty()) {
			for (DoctorPracticeExperienceTbl experience : doctorExperience)
				delete(experience);
		}

		// Saving doctor educational qualification details
		if (!doctor.getDoctorQualifications().isEmpty()) {
			for (DoctorQualificationDTO newQualification : doctor
					.getDoctorQualifications()) {
				createDoctorQualification(doctorTbl, newQualification);
			}
		}

		// Saving doctor achievement details
		if (!doctor.getAchievement().isEmpty()) {
			for (DoctorAchievementDTO newAchievement : doctor.getAchievement()) {
				createDoctorAchievements(doctorTbl, newAchievement);
			}
		}

		// Saving doctor experience details
		if (!doctor.getDoctorExperience().isEmpty()) {
			for (DoctorExperienceDTO newExperience : doctor
					.getDoctorExperience()) {
				createDoctorExperience(doctorTbl, newExperience);
			}
		}

		// Saving doctor expertise details
		if (!doctor.getExpertise().isEmpty()) {
			for (DoctorExpertiseDTO newExpertise : doctor.getExpertise()) {
				createDoctorExpertise(doctorTbl, newExpertise);
			}
		}

		// Saving doctor membership details
		if (!doctor.getMembership().isEmpty()) {
			for (DoctorMembershipDTO newMembership : doctor.getMembership()) {
				createDoctorMembership(doctorTbl, newMembership);
			}
		}

		response.setSuccess(true);
		response.setId(doctor.getId());
		response.setGlobalId(doctorTbl.getId());
		response.setUpdateDateTime(sdf.format(doctorTbl.getUpdateDateTime()));
		return response;
	}

	/**
	 * Method to delete doctor
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO delete(int globalId) {
		ResponseDTO response = new ResponseDTO();

		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class, globalId);
		if (doctorTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		doctorTbl.setStatus(StatusEnum.InActive.getDisplayName());
		update(doctorTbl);

		// Deleting each table record related to the doctor
		List<DoctorAchievementTbl> doctorAchievement = getAchievementByDoctorId(globalId);
		if (!doctorAchievement.isEmpty()) {
			for (DoctorAchievementTbl achievement : doctorAchievement)
				delete(achievement);
		}
		List<DoctorEducationalQualificationTbl> doctorQualification = getQualificationByDoctorId(globalId);
		if (!doctorQualification.isEmpty()) {
			for (DoctorEducationalQualificationTbl educationQlfctn : doctorQualification)
				delete(educationQlfctn);
		}
		List<DoctorExpertiseTbl> doctorExpertise = getExpertiseByDoctorId(globalId);
		if (!doctorExpertise.isEmpty()) {
			for (DoctorExpertiseTbl expert : doctorExpertise)
				delete(expert);
		}
		List<DoctorMembershipTbl> doctorMembership = getMembershipByDoctorId(globalId);
		if (!doctorMembership.isEmpty()) {
			for (DoctorMembershipTbl membershp : doctorMembership)
				delete(membershp);
		}
		List<DoctorPracticeExperienceTbl> doctorExperience = getExperienceByDoctorId(globalId);
		if (!doctorExperience.isEmpty()) {
			for (DoctorPracticeExperienceTbl experience : doctorExperience)
				delete(experience);
		}

		List<DoctorScheduleTbl> doctorSchedules = getScheduleByDoctorId(globalId);
		if (!doctorSchedules.isEmpty()) {
			for (DoctorScheduleTbl doctorSchedule : doctorSchedules)
				delete(doctorSchedule);
		}
		response.setGlobalId(globalId);
		response.setSuccess(true);
		return response;
	}

	/**
	 * View a doctor by giving input as id
	 * 
	 * @param id
	 * @return DoctorDetail
	 */
	@Override
	@Transactional
	public DoctorViewResponseDTO view(int id) {

		DoctorViewResponseDTO response = new DoctorViewResponseDTO();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		DoctorTbl doctorTbl = (DoctorTbl) getById(DoctorTbl.class, id);
		if (doctorTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorNotFound);
			se.addParam(new Parameter(Constants.ID, Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		DoctorDetail doctor = new DoctorDetail();
		doctor.setFirstName(doctorTbl.getFirstName());
		doctor.setLastName(doctorTbl.getLastName());
		doctor.setAddress(doctorTbl.getAddress());
		doctor.setMobile(doctorTbl.getMobile());
		doctor.setPhone(doctorTbl.getPhone());
		doctor.setDateOfBirth(doctorTbl.getDateOfBirth());
		doctor.setGender(doctorTbl.getGender());
		doctor.setWorkHistory(doctorTbl.getWorkHistory());
		doctor.setWorkingPlaces(doctorTbl.getWorkingPlaces());
		// doctor.setSpecialisation(doctorTbl.getSpecialisation());
		doctor.setConsultationInterval(Integer.toString(doctorTbl
				.getConsultationInterval()));
		doctor.setDesignation(doctorTbl.getDesignation());
		doctor.setEmail(doctorTbl.getEmail());
		doctor.setStatus(doctorTbl.getStatus());
		doctor.setGlobalId(doctorTbl.getId());
		doctor.setCreateDateTime(df.format(doctorTbl.getCreateDateTime()));
		doctor.setUpdateDateTime(df.format(doctorTbl.getUpdateDateTime()));
		List<DoctorExperienceDTO> doctorExperiences = new ArrayList<DoctorExperienceDTO>();
		List<DoctorQualificationDTO> doctorQualifications = new ArrayList<DoctorQualificationDTO>();
		List<DoctorAchievementDTO> doctorAchievements = new ArrayList<DoctorAchievementDTO>();
		List<DoctorExpertiseDTO> doctorExpertises = new ArrayList<DoctorExpertiseDTO>();
		List<DoctorMembershipDTO> doctorMemberships = new ArrayList<DoctorMembershipDTO>();
		List<DoctorAchievementTbl> doctorAchievment = getAchievementByDoctorId(doctor
				.getGlobalId());
		List<DoctorEducationalQualificationTbl> doctorQualficaton = getQualificationByDoctorId(doctor
				.getGlobalId());
		List<DoctorExpertiseTbl> doctorExpertse = getExpertiseByDoctorId(doctor
				.getGlobalId());
		List<DoctorMembershipTbl> doctorMembrship = getMembershipByDoctorId(doctor
				.getGlobalId());
		List<DoctorPracticeExperienceTbl> doctorExp = getExperienceByDoctorId(doctor
				.getGlobalId());
		if (!doctorExp.isEmpty()) {
			for (DoctorPracticeExperienceTbl doctorPracticeExp : doctorExp) {
				DoctorExperienceDTO doctorExperience = new DoctorExperienceDTO();
				doctorExperience.setDesignation(doctorPracticeExp
						.getDesignation());
				doctorExperience.setFromDate(df.format(doctorPracticeExp
						.getFromDate()));
				doctorExperience.setToDate(df.format(doctorPracticeExp
						.getToDate()));
				doctorExperience.setWorkedAt(doctorPracticeExp.getWorkedAt());
				doctorExperience.setId(doctorPracticeExp.getId());
				doctorExperiences.add(doctorExperience);
			}
			doctor.setDoctorExperience(doctorExperiences);
		}
		if (!doctorQualficaton.isEmpty()) {
			for (DoctorEducationalQualificationTbl doctorEducationQlfcatn : doctorQualficaton) {
				DoctorQualificationDTO doctorQualification = new DoctorQualificationDTO();
				doctorQualification.setEducationalDegree(doctorEducationQlfcatn
						.getEducationalDegree());
				doctorQualification.setInstitution(doctorEducationQlfcatn
						.getInstitution());
				doctorQualification.setPassedOutDate(df
						.format(doctorEducationQlfcatn.getPassedOutDate()));
				doctorQualification.setId(doctorEducationQlfcatn.getId());
				doctorQualifications.add(doctorQualification);
			}
			doctor.setDoctorQualifications(doctorQualifications);
		}
		if (!doctorAchievment.isEmpty()) {
			for (DoctorAchievementTbl doctorAchievementTbl : doctorAchievment) {
				DoctorAchievementDTO doctorAcheivement = new DoctorAchievementDTO();
				doctorAcheivement.setAchievement(doctorAchievementTbl
						.getAchievement());
				doctorAcheivement.setId(doctorAchievementTbl.getId());
				doctorAchievements.add(doctorAcheivement);
			}
			doctor.setAchievement(doctorAchievements);
		}
		if (!doctorExpertse.isEmpty()) {
			for (DoctorExpertiseTbl doctorExpertiseTbl : doctorExpertse) {
				DoctorExpertiseDTO doctorExpertise = new DoctorExpertiseDTO();
				doctorExpertise.setExpertise(doctorExpertiseTbl.getExpertise());
				doctorExpertise.setId(doctorExpertiseTbl.getId());
				doctorExpertises.add(doctorExpertise);
			}
			doctor.setExpertise(doctorExpertises);
		}
		if (!doctorMembrship.isEmpty()) {
			for (DoctorMembershipTbl doctorMembership : doctorMembrship) {
				DoctorMembershipDTO doctorMembershp = new DoctorMembershipDTO();
				doctorMembershp.setMembership(doctorMembership.getMembership());
				doctorMembershp.setId(doctorMembership.getId());
				doctorMemberships.add(doctorMembershp);

			}
			doctor.setMembership(doctorMemberships);
		}
		NetmdLoginTbl doctorLogin = getById(NetmdLoginTbl.class, doctorTbl
				.getNetmdLoginTbl().getId());
		if (doctorLogin == null) {

			ServiceException se = new ServiceException(
					ErrorCodeEnum.DoctorLoginNull);
			se.addParam(new Parameter(Constants.NAME, doctorTbl.getFirstName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		LoginDTO login = new LoginDTO();
		login.setUserName(doctorLogin.getUserName());
		login.setUserType(doctorLogin.getUserType());
		doctor.setLogin(login);
		response.setDoctorDetail(doctor);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to create doctor educational qualifications
	 * 
	 * @param doctor
	 * @param doctorQualificationDTO
	 * @return ResponseDTO
	 */
	private ResponseDTO createDoctorQualification(DoctorTbl doctor,
			DoctorQualificationDTO doctorQualificationDTO) {

		ResponseDTO response = new ResponseDTO();
		DateFormat sdf = new SimpleDateFormat(Constants.YEAR);
		DoctorEducationalQualificationTbl qualificationTbl = new DoctorEducationalQualificationTbl();
		qualificationTbl.setEducationalDegree(doctorQualificationDTO
				.getEducationalDegree());
		qualificationTbl
				.setInstitution(doctorQualificationDTO.getInstitution());
		if (doctorQualificationDTO.getPassedOutDate() != null) {
			Date passoutDate = null;
			try {
				passoutDate = sdf.parse(doctorQualificationDTO
						.getPassedOutDate());
				qualificationTbl.setPassedOutDate(passoutDate);
			} catch (ParseException e) {
				e.printStackTrace();
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		qualificationTbl.setDoctorTbl(doctor);
		save(qualificationTbl);
		response.setId(qualificationTbl.getId());
		return response;
	}

	/**
	 * Method to save doctor achievements
	 * 
	 * @param doctor
	 * @param doctorAchievement
	 * @return ResponseDTO
	 */
	private ResponseDTO createDoctorAchievements(DoctorTbl doctor,
			DoctorAchievementDTO doctorAchievement) {

		ResponseDTO response = new ResponseDTO();
		DoctorAchievementTbl achievementTbl = new DoctorAchievementTbl();
		achievementTbl.setAchievement(doctorAchievement.getAchievement());
		achievementTbl.setDoctorTbl(doctor);
		save(achievementTbl);
		response.setId(achievementTbl.getId());
		return response;
	}

	/**
	 * Method to save doctor experiences
	 * 
	 * @param doctor
	 * @param doctorExperience
	 * @return ResponseDTO
	 */
	private ResponseDTO createDoctorExperience(DoctorTbl doctor,
			DoctorExperienceDTO doctorExperience) {

		ResponseDTO response = new ResponseDTO();
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		DoctorPracticeExperienceTbl doctorPracticeExperience = new DoctorPracticeExperienceTbl();
		doctorPracticeExperience.setDesignation(doctorExperience
				.getDesignation());
		doctorPracticeExperience.setWorkedAt(doctorExperience.getWorkedAt());
		if (doctorExperience.getFromDate() != null) {
			Date fromDate = null;

			try {
				fromDate = df.parse(doctorExperience.getFromDate());
				doctorPracticeExperience.setFromDate(fromDate);

			} catch (ParseException e) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

		}
		if (doctorExperience.getToDate() != null) {

			Date toDate = null;
			try {
				toDate = df.parse(doctorExperience.getToDate());
				doctorPracticeExperience.setToDate(toDate);
			} catch (ParseException e) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidDateFormat);
				se.setDisplayErrMsg(true);
				throw se;
			}

		}
		doctorPracticeExperience.setDoctorTbl(doctor);
		save(doctorPracticeExperience);
		response.setId(doctorPracticeExperience.getId());
		return response;
	}

	/**
	 * Method to save doctor expertises
	 * 
	 * @param doctor
	 * @param doctorExpertise
	 * @return ResponseDTO
	 */
	private ResponseDTO createDoctorExpertise(DoctorTbl doctor,
			DoctorExpertiseDTO doctorExpertise) {

		ResponseDTO response = new ResponseDTO();
		DoctorExpertiseTbl doctorExpert = new DoctorExpertiseTbl();
		doctorExpert.setExpertise(doctorExpertise.getExpertise());
		doctorExpert.setDoctorTbl(doctor);
		save(doctorExpert);
		response.setId(doctorExpert.getId());
		return response;
	}

	/**
	 * Method to save doctor memberships
	 * 
	 * @param doctor
	 * @param doctorMembership
	 * @return ResponseDTO
	 */
	private ResponseDTO createDoctorMembership(DoctorTbl doctor,
			DoctorMembershipDTO doctorMembership) {

		ResponseDTO response = new ResponseDTO();
		DoctorMembershipTbl doctorMembershp = new DoctorMembershipTbl();
		doctorMembershp.setMembership(doctorMembership.getMembership());
		doctorMembershp.setDoctorTbl(doctor);
		save(doctorMembershp);
		response.setId(doctorMembershp.getId());
		return response;
	}

	/**
	 * Method to check whether doctor login exists or not
	 */
	@Override
	@Transactional
	public boolean isDoctorLoginExists(String email, String userType) {
		boolean flag = true;
		NetmdLoginTbl doctorLogin = getLoginByUserNameAndUserType(email.trim(),
				userType);
		if (doctorLogin != null) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Method to retreive doctor login details if exists
	 */
	@Override
	@Transactional
	public NetmdLoginTbl DoctorLoginDetails(String email, String userType) {

		NetmdLoginTbl doctorLogin = getLoginByUserNameAndUserType(email.trim(),
				userType);
		return doctorLogin;
	}

	/**
	 * Method to retrieve the password of a doctor to Netmd after resetting it
	 * in the portal
	 */
	@Override
	@Transactional
	public List<DoctorLoginDTO> DoctorPasswordList(String syncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		List<DoctorLoginDTO> response = new ArrayList<DoctorLoginDTO>();
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
		/* Getting doctors list */
		List<DoctorTbl> DoctorsList = getDoctorsWithNewPasswords(lastSyncTime,
				netmdPassphraseId, netmdBranchId, currentSyncTime);
		for (DoctorTbl doctor : DoctorsList) {

			response.add(new DoctorLoginDTO(doctor));
		}

		return response;
	}

	/**
	 * Method performed to retrieve created updated and deleted doctors list
	 * from portal
	 * 
	 * @param lastSyncTime
	 * @param passPhrase
	 * @param netmdBranchId
	 * @return RetrievalDoctorResponseDTO
	 */
	@Override
	@Transactional
	public RetrievalDoctorResponseDTO retrieveDoctorList(String syncTime,
			String passPhrase, int netmdBranchId, Date currentSyncTime) {
		RetrievalDoctorResponseDTO response = new RetrievalDoctorResponseDTO();
		List<DoctorDetail> retrieveDoctorsList = new ArrayList<DoctorDetail>();
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

		/* Getting doctors list */
		List<DoctorTbl> DoctorsList = getDoctors(lastSyncTime,
				netmdPassphraseId, netmdBranchId, currentSyncTime);
		for (DoctorTbl doctor : DoctorsList) {

			retrieveDoctorsList.add(new DoctorDetail(doctor));
		}

		response.setRetrieveDoctorsList(retrieveDoctorsList);
		response.setSuccess(true);
		return response;
	}
	
	/**
	 * Method for retrieving all newly created doctors after last sync time
	 * 
	 * @param lastSyncTime
	 * @param netmdPassphraseId
	 * @param netmdBranchId
	 * @return DoctorTbl
	 */
	private List<DoctorTbl> getDoctors(Date lastSyncTime,
			int netmdPassphraseId, int netmdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em.createQuery(Query.RETRIEVE_DOCTORS);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", netmdPassphraseId);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(DoctorTbl.class, query);

	}

	/**
	 * Method for retrieving all newly created doctors after last sync time
	 * 
	 * @param lastSyncTime
	 * @param netmdPassphraseId
	 * @param netmdBranchId
	 * @return DoctorTbl
	 */
	private List<DoctorTbl> getDoctorsWithNewPasswords(Date lastSyncTime,
			int netmdPassphraseId, int netmdBranchId, Date currentSyncTime) {
		javax.persistence.Query query = em
				.createQuery(Query.RETRIEVE__UPDATED_DOCTORS);
		query.setParameter("param1", lastSyncTime);
		query.setParameter("param2", netmdPassphraseId);
		query.setParameter("param3", netmdBranchId);
		query.setParameter("param4", currentSyncTime);
		return executeQuery(DoctorTbl.class, query);

	}

	/**
	 * Method for getting doctor record by giving login id
	 * 
	 * @param loginId
	 * @return DoctorTbl
	 */
	public List<DoctorTbl> getDoctorByLoginId(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeQuery(DoctorTbl.class, query);
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
	 * Retrives doctor detail by id
	 * 
	 * @param globalId
	 * @return DoctorTbl
	 */
	public DoctorTbl getDoctorByGlobalId(int globalId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_GLOBALID);
		query.setParameter("param1", globalId);
		return executeUniqueQuery(DoctorTbl.class, query);
	}

	/**
	 * Retrieves doctor details
	 * 
	 * @param email
	 * @param branchId
	 * @return DoctorTbl
	 */
	public List<DoctorTbl> getExistingDoctors(String email, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXISTING_DOCTOR);
		query.setParameter("param1", email);
		query.setParameter("param2", branchId);
		return executeQuery(DoctorTbl.class, query);
	}

	/**
	 * retrieve doctor details by login id and branch id
	 * 
	 * @param loginId
	 * @param branchId
	 * @return DoctorTbl
	 */
	public DoctorTbl getExistingDoctorWithLogin(int loginId, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_WITH_LOGIN);
		query.setParameter("param1", loginId);
		query.setParameter("param2", branchId);
		return executeUniqueQuery(DoctorTbl.class, query);
	}

	/**
	 * Retrieve doctor details by email id
	 * 
	 * @param eMail
	 * @return DoctorTbl
	 */
	public DoctorTbl getDoctorByEmail(String eMail) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTOR_BY_EMAIL);
		query.setParameter("param1", eMail);
		return executeUniqueQuery(DoctorTbl.class, query);
	}

	/**
	 * Method to retrieve netmd login of a user
	 * 
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	public NetmdLoginTbl getLoginByUserNameAndUserType(String userName,
			String userType) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_LOGIN_BY_USERNAME_USERTYPE);
		query.setParameter("param1", userName);
		query.setParameter("param2", userType);
		return executeUniqueQuery(NetmdLoginTbl.class, query);
	}

	/**
	 * Method to retrieve netmd login of a user
	 * 
	 * @param userName
	 * @return NetmdLoginTbl
	 */
	public List<NetmdLoginTbl> getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeQuery(NetmdLoginTbl.class, query);
	}

	/**
	 * retrieve netmd details
	 * 
	 * @param netMdId
	 * @param passPhrase
	 * @return NetmdPassphraseTbl
	 */
	public NetmdPassphraseTbl getNetmdByPassphrase(int netMdId,
			String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BY_PASSPHRASE);
		query.setParameter("param1", netMdId);
		query.setParameter("param2", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * Method to retrieve experiences of a doctor
	 * 
	 * @param id
	 * @return DoctorPracticeExperienceTbl
	 */
	public List<DoctorPracticeExperienceTbl> getByDoctorId(int id) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXPERIENCE_BY_DOCTORID);
		query.setParameter("param1", id);
		return executeQuery(DoctorPracticeExperienceTbl.class, query);
	}

	/**
	 * list Doctors
	 * 
	 * @param clinicId
	 * @return DoctorTbl
	 */
	public List<DoctorTbl> listDoctors(String clinicId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DOCTORS_BY_CLINIC);
		query.setParameter("param1", Integer.parseInt(clinicId));
		return executeQuery(DoctorTbl.class, query);
	}

	/**
	 * Method to retrieve experiences
	 * 
	 * @param id
	 * @param doctorId
	 * @return DoctorPracticeExperienceTbl
	 */
	public DoctorPracticeExperienceTbl getExperience(int id, int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXPERIENCE_BY_GLOBALID);
		query.setParameter("param1", id);
		query.setParameter("param2", doctorId);
		return executeUniqueQuery(DoctorPracticeExperienceTbl.class, query);
	}

	/**
	 * method to retrieve qualification
	 * 
	 * @param id
	 * @param doctorId
	 * @return DoctorEducationalQualificationTbl
	 */
	public DoctorEducationalQualificationTbl getQualification(int id,
			int doctorId) {
		javax.persistence.Query query = em.createQuery(Query.GET_QUALIFICATION);
		query.setParameter("param1", id);
		query.setParameter("param2", doctorId);
		return executeUniqueQuery(DoctorEducationalQualificationTbl.class,
				query);
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
	 * Method to get achievements of a doctor
	 * 
	 * @param doctorId
	 * @return DoctorAchievementTbl
	 */
	public List<DoctorAchievementTbl> getAchievementByDoctorId(int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_ACHIEVEMENT_BY_DOCTOR_ID);
		query.setParameter("param1", doctorId);
		return executeQuery(DoctorAchievementTbl.class, query);
	}

	/**
	 * Method to get educational qualifications of adoctor
	 * 
	 * @param doctorId
	 * @return DoctorEducationalQualificationTbl
	 */
	public List<DoctorEducationalQualificationTbl> getQualificationByDoctorId(
			int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_QUALIFICATION_BY_DOCTOR_ID);
		query.setParameter("param1", doctorId);
		return executeQuery(DoctorEducationalQualificationTbl.class, query);
	}

	/**
	 * Method to get memberships of a doctor
	 * 
	 * @param doctorId
	 * @return DoctorMembershipTbl
	 */
	public List<DoctorMembershipTbl> getMembershipByDoctorId(int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MEMBERSHIP_BY_DOCTOR_ID);
		query.setParameter("param1", doctorId);
		return executeQuery(DoctorMembershipTbl.class, query);
	}

	/**
	 * Method to get expertises of a doctor
	 * 
	 * @param doctorId
	 * @return DoctorExpertiseTbl
	 */
	public List<DoctorExpertiseTbl> getExpertiseByDoctorId(int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXPERTISE_BY_DOCTOR_ID);
		query.setParameter("param1", doctorId);
		return executeQuery(DoctorExpertiseTbl.class, query);
	}

	/**
	 * Method to get experiences of a doctor
	 * 
	 * @param doctorId
	 * @return DoctorPracticeExperienceTbl
	 */
	public List<DoctorPracticeExperienceTbl> getExperienceByDoctorId(
			int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_EXPERIENCE_BY_DOCTORID);
		query.setParameter("param1", doctorId);
		return executeQuery(DoctorPracticeExperienceTbl.class, query);
	}

	/**
	 * Method to get schedule by doctor id
	 * 
	 * @param doctorId
	 * @return DoctorScheduleTbl
	 */
	public List<DoctorScheduleTbl> getScheduleByDoctorId(int doctorId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_SCHEDULE_BY_DOCTORID);
		query.setParameter("param1", doctorId);
		return executeQuery(DoctorScheduleTbl.class, query);
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

}
