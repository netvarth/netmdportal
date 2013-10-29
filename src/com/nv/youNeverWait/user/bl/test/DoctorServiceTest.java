/**
 * DoctorServiceTest.java
 */
package com.nv.youNeverWait.user.bl.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.DoctorAchievementDTO;
import com.nv.youNeverWait.rs.dto.DoctorDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorExperienceDTO;
import com.nv.youNeverWait.rs.dto.DoctorExpertiseDTO;
import com.nv.youNeverWait.rs.dto.DoctorMembershipDTO;
import com.nv.youNeverWait.rs.dto.DoctorQualificationDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.user.bl.service.DoctorService;

/**
 * @author Luciya Jose
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class DoctorServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void createDoctorAlreadyExists() {

		System.out.println("##########create################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDTO doctor = new DoctorDTO();
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setAddress("Peramangalam");
		doctorDetail.setEmail("misha.ligesh@netvarth.com");
		doctorDetail.setFirstName("misl");
		doctorDetail.setLastName("Jay");
		doctorDetail.setDateOfBirth("1988-03-29");
		doctorDetail.setConsultationInterval("20");
		doctorDetail.setGender("Male");
//		 List<DoctorExperienceDTO> doctorExperience= new
//		 ArrayList<DoctorExperienceDTO>();
//		 DoctorExperienceDTO experience= new DoctorExperienceDTO();
//		 experience.setDesignation("Dentist");
//		 experience.setFromDate("2000-05-05");
//		 experience.setToDate("2005-05-06");
//		 doctorExperience.add(experience);
//		 doctorDetail.setDoctorExperience(doctorExperience);

		 List<DoctorQualificationDTO> doctorQualifications= new ArrayList<DoctorQualificationDTO>();
		 DoctorQualificationDTO qualification=new DoctorQualificationDTO();
		 qualification.setInstitution("St.Josephs");
		 qualification.setEducationalDegree("MD");
		 qualification.setPassedOutDate("2006");
		 doctorQualifications.add(qualification);
		 doctorDetail.setDoctorQualifications(doctorQualifications);

		LoginDTO login = new LoginDTO();
		login.setUserName("misha.ligesh@netvarth.com");
		login.setUserType("doctor");
		doctorDetail.setLogin(login);

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(76);
		header.setPassPhrase("bab5TqPDNCbFCy7LWRffyg==");
		header.setMacId("20-CF-30-D9-9C-04");
		header.setBranchId(72);
		doctor.setHeader(header);
		doctor.setDoctor(doctorDetail);
		try {
			service.create(doctor.getDoctor(), header);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createSucccess() {

		System.out.println("##########create################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDTO doctor = new DoctorDTO();
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setAddress("Peramangalam");
		doctorDetail.setEmail("linakerLouis@netvarth.com");
		doctorDetail.setFirstName("linakerLouis");
		doctorDetail.setLastName("Jaya");
		doctorDetail.setDateOfBirth("1988-03-29");
		doctorDetail.setConsultationInterval("20");
		doctorDetail.setGender("FeMale");
		
		 List<DoctorExperienceDTO> doctorExperience= new
		 ArrayList<DoctorExperienceDTO>();
		 DoctorExperienceDTO experience= new DoctorExperienceDTO();
		 experience.setDesignation("Dentist");
		 experience.setFromDate("2000-05-05");
		 experience.setToDate("2005-05-06");
		 doctorExperience.add(experience);
		 doctorDetail.setDoctorExperience(doctorExperience);

		 List<DoctorQualificationDTO> doctorQualifications= new ArrayList<DoctorQualificationDTO>();
		 DoctorQualificationDTO qualification=new DoctorQualificationDTO();
		 qualification.setInstitution("St.Josephs");
		 qualification.setEducationalDegree("MD");
		 qualification.setPassedOutDate("2006");
		 doctorQualifications.add(qualification);
		 doctorDetail.setDoctorQualifications(doctorQualifications);

		 List<DoctorAchievementDTO> doctorAch= new ArrayList<DoctorAchievementDTO>();
		 DoctorAchievementDTO ach=new DoctorAchievementDTO();
		 ach.setAchievement("superb");
		 ach.setActionName("Add");
		 doctorAch.add(ach);
		 doctorDetail.setAchievement(doctorAch);
		 
		 List<DoctorExpertiseDTO> doctorExpt= new ArrayList<DoctorExpertiseDTO>();
		 DoctorExpertiseDTO expt=new DoctorExpertiseDTO();
		 expt.setExpertise("Senior doctor");
		 doctorExpt.add(expt);
		 doctorDetail.setExpertise(doctorExpt);
		 
		 List<DoctorMembershipDTO> doctorMem= new ArrayList<DoctorMembershipDTO>();
		 DoctorMembershipDTO mem=new DoctorMembershipDTO();
		 expt.setExpertise("Senior doctor");
		 mem.setMembership("REliance");
		 doctorMem.add(mem);
		 doctorDetail.setMembership(doctorMem);
		 
		 
		LoginDTO login = new LoginDTO();
		login.setUserName("linakerlouisd@netvarth.com");
		login.setUserType("doctor");
		doctorDetail.setLogin(login);

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(76);
		header.setPassPhrase("bab5TqPDNCbFCy7LWRffyg==");
		header.setMacId("20-CF-30-D9-9C-04");
		header.setBranchId(72);
		doctor.setHeader(header);
		doctor.setDoctor(doctorDetail);
		try {
			service.create(doctor.getDoctor(), header);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateFirstNameExists() {

		System.out.println("##########update################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDTO doctor = new DoctorDTO();
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setAddress("vazhappilly");
		doctorDetail.setEmail("Amith@gmail.com");
		doctorDetail.setConsultationInterval("25");
		doctorDetail.setGlobalId(3);
		 List<DoctorAchievementDTO> doctorAchievmnet= new
		 ArrayList<DoctorAchievementDTO>();
		 DoctorAchievementDTO docAch=new DoctorAchievementDTO();
		 docAch.setAchievement("Senior Consultant");
		 doctorAchievmnet.add(docAch);
		 doctorDetail.setAchievement(doctorAchievmnet);
		 LoginDTO login= new LoginDTO();
		 login.setPassword("Seeraj");
		 login.setUserName("NeenuSeeraj");
		login.setUserType("doctor");
		 doctorDetail.setLogin(login);

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(3);
		header.setPassPhrase("123");
		header.setMacId("5646opiopiop");
		header.setBranchId(5);
		doctor.setHeader(header);
		doctor.setDoctor(doctorDetail);
		try {
			service.update(doctor.getDoctor(), header);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void update() {

		System.out.println("##########update################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDTO doctor = new DoctorDTO();
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setAddress("vazhappilly");
		doctorDetail.setEmail("Amith@gmail.com");
		doctorDetail.setConsultationInterval("25");
		doctorDetail.setGlobalId(86);
		doctorDetail.setFirstName("Maushamni");
		 List<DoctorAchievementDTO> doctorAchievmnet= new
		 ArrayList<DoctorAchievementDTO>();
		 DoctorAchievementDTO docAch=new DoctorAchievementDTO();
		 docAch.setAchievement("Senior Consultant");
		 doctorAchievmnet.add(docAch);
		 doctorDetail.setAchievement(doctorAchievmnet);
		 LoginDTO login= new LoginDTO();
		 login.setPassword("Seeraj");
		 login.setUserName("NeenuSeeraj");
		login.setUserType("doctor");
		 doctorDetail.setLogin(login);

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(3);
		header.setPassPhrase("123");
		header.setMacId("5646opiopiop");
		header.setBranchId(5);
		doctor.setHeader(header);
		doctor.setDoctor(doctorDetail);
		try {
			service.update(doctor.getDoctor(), header);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void delete() {

		System.out.println("##########delete################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDTO doctor = new DoctorDTO();
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setGlobalId(3);

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(3);
		header.setPassPhrase("123");
		header.setMacId("5646opiopiop");
		header.setBranchId(5);
		doctor.setHeader(header);
		doctor.setDoctor(doctorDetail);
		try {
			service.delete(doctorDetail.getGlobalId());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void view() {

		System.out.println("##########delete################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDTO doctor = new DoctorDTO();
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setGlobalId(23);

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(3);
		header.setPassPhrase("123");
		header.setMacId("5646opiopiop");
		header.setBranchId(5);
		doctor.setHeader(header);
		doctor.setDoctor(doctorDetail);
		try {
			service.view(86);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void resetPassword(){
		System.out.println("##########create paswd################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		LoginDTO login = new LoginDTO();
		login.setUserName("jqqG0AXVqTyMcEhB/Bu06Wq5E/m3N+0F0pMmX+jE2YE=");
		login.setPassword("netvarth");
		try {
			service.resetPassword(login);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
			
}
