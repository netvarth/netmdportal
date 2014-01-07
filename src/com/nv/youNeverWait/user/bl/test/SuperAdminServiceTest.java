/**
 * SuperAdminServiceTest.java
 */
package com.nv.youNeverWait.user.bl.test;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.DoctorDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.DoctorExperienceDTO;
import com.nv.youNeverWait.rs.dto.DoctorQualificationDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabDTO;
import com.nv.youNeverWait.rs.dto.LogDTO;
import com.nv.youNeverWait.rs.dto.LogDetail;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxDetail;
import com.nv.youNeverWait.rs.dto.NetRxListResponseDTO;
import com.nv.youNeverWait.rs.dto.Organisation;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.rs.dto.SpecimenListResponseDTO;
import com.nv.youNeverWait.rs.dto.TestDTO;
import com.nv.youNeverWait.rs.dto.TestListResponseDTO;
import com.nv.youNeverWait.rs.dto.UserLogListResponseDTO;
import com.nv.youNeverWait.user.bl.service.DoctorService;
import com.nv.youNeverWait.user.bl.service.SuperAdminService;

/**
 * @author Luciya Jose
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class SuperAdminServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void forgotPassword() {
		System.out.println("Forgot Password");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LoginDTO login = new LoginDTO();
		login.setUserName("misha");
		try {
			service.forgotPassword(login);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void resetPassword() {
		System.out.println("Reset Password");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LoginDTO login = new LoginDTO();
		login.setUserName("aW8oFWDMOJUrIV3l7R7hqQ==");
		login.setPassword("netvarth");
		try {
			service.resetPassword(login);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void create() {

		System.out.println("##########create################ ");
		DoctorService service = (DoctorService) applicationContext
				.getBean("doctor.service");
		DoctorDTO doctor = new DoctorDTO();
		DoctorDetail doctorDetail = new DoctorDetail();
		doctorDetail.setAddress("vazhappilly");
		doctorDetail.setEmail("aSeeraj@gmail.com");
		doctorDetail.setFirstName("Seeraj");
		doctorDetail.setLastName("Roy");
		doctorDetail.setDateOfBirth("2012-10-10");
		doctorDetail.setConsultationInterval("14");

		List<DoctorExperienceDTO> doctorExperience = new ArrayList<DoctorExperienceDTO>();
		DoctorExperienceDTO experience = new DoctorExperienceDTO();
		experience.setDesignation("Senior doctor");
		experience.setFromDate("2010-10-10");
		experience.setToDate("2010-11-11");
		doctorExperience.add(experience);
		doctorDetail.setDoctorExperience(doctorExperience);

		List<DoctorQualificationDTO> doctorQualifications = new ArrayList<DoctorQualificationDTO>();
		DoctorQualificationDTO qualification = new DoctorQualificationDTO();
		qualification.setInstitution("de paul");
		doctorQualifications.add(qualification);
		doctorDetail.setDoctorQualifications(doctorQualifications);

		LoginDTO login = new LoginDTO();
		login.setPassword("Seraj");
		login.setUserName("Seraj");
		login.setUserType("Senior doctor");
		doctorDetail.setLogin(login);

		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(1);
		header.setPassPhrase("123");
		doctor.setHeader(header);
		doctor.setDoctor(doctorDetail);
		try {
			// service.create(doctor.getDoctor(),
			// doctor.getHeader().getNetMdId(),doctor.getDoctor().getLogin());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void login() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LoginDTO login = new LoginDTO();
		login.setPassword("netvarth");
		login.setUserName("asha");
		LoginResponseDTO response = service.login(login);
		System.out.println("LoginResponseDTO " + response.isSuccess());
	}

	@Test
	public void createLabAlreadyExists() {
		System.out.println("create Lab");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LabDTO lab = new LabDTO();
		lab.setHeadOfficeAddress("chembookavu");
		lab.setHeadOfficeEmail("asha.chandran@netvarth.com");
		lab.setHeadOfficeMobile("9947848484");
		lab.setHeadOfficeName("techno");
		lab.setHeadOfficePhone("04885-224859");
		lab.setName("Jeeva lab");
		lab.setOwnerAddress("kochi");
		lab.setOwnerEmail("asha.chandran@netvarth.com");
		lab.setOwnerFirstName("Beena");
		lab.setOwnerMobile("6363636363");
		lab.setOwnerPhone("04885214141");
		lab.setPassword("netvarth");
		lab.setUserName("jhme");
		try {
			service.createLab(lab);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createLab() {
		System.out.println("create Lab");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LabDTO lab = new LabDTO();
		lab.setHeadOfficeAddress("chembookavu");
		lab.setHeadOfficeEmail("asha.chandran@netvarth.com");
		lab.setHeadOfficeMobile("9947848484");
		lab.setHeadOfficeName("techno");
		lab.setHeadOfficePhone("04885-224859");
		lab.setName("Jeeva lab");
		lab.setOwnerAddress("kochi");
		lab.setOwnerEmail("asha.chandran@netvarth.com");
		lab.setOwnerFirstName("Beena");
		lab.setOwnerMobile("6363636363");
		lab.setOwnerPhone("04885214141");
		lab.setPassword("netvarth");
		lab.setUserName("rhme");
		try {
			service.createLab(lab);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateLabInvalid() {
		System.out.println("update Lab");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LabDTO lab = new LabDTO();
		lab.setGlobalId(43);
		lab.setHeadOfficeAddress("Reliance");
		lab.setHeadOfficeEmail("Reliance@gmail.com");
		lab.setHeadOfficeMobile("9947848484");
		lab.setHeadOfficeName("Reliance Home");
		lab.setHeadOfficePhone("04885-224859");
		lab.setName("My Reliance");
		lab.setOwnerAddress("Palakkad");
		lab.setOwnerEmail("Reliance@gmail.com");
		lab.setOwnerFirstName("Reliance");
		lab.setOwnerMobile("6363636363");
		lab.setOwnerPhone("04885214141");
		lab.setPassword("Reliance");
		lab.setUserName("Reliance");
		try {
			service.updateLab(lab);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateLabSuccess() {
		System.out.println("update Lab");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LabDTO lab = new LabDTO();
		lab.setGlobalId(11);
		lab.setHeadOfficeAddress("Reliance");
		lab.setHeadOfficeEmail("Reliance@gmail.com");
		lab.setHeadOfficeMobile("9947848484");
		lab.setHeadOfficeName("Reliance Home");
		lab.setHeadOfficePhone("04885-224859");
		lab.setName("Jayalakshmi");
		lab.setOwnerAddress("Palakkad");
		lab.setOwnerEmail("Reliance@gmail.com");
		lab.setOwnerFirstName("Reliance");
		lab.setOwnerMobile("6363636363");
		lab.setOwnerPhone("04885214141");
		lab.setPassword("Reliance");
		lab.setUserName("lakshmi");
		try {
			service.updateLab(lab);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewLab() {
		System.out.println("view Lab");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");

		try {
			service.viewLab(11);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void deleteLab() {
		System.out.println("delete Lab");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");

		try {
			service.deleteLab(188);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void changePassword() {
		System.out.println("change password");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		PasswordDTO passwords = new PasswordDTO();
		passwords.setOldPassword("asha");
		passwords.setNewPassword("netvarth");
		passwords.setUsername("misha");
		try {
			service.changePassword(passwords);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createNetMd() {
		System.out.println("create netmd");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		NetMdDTO netmd = new NetMdDTO();
		netmd.setHeadOfficeAddress("Museum Cross Lane road");
		netmd.setHeadOfficeEmail("asha.chandran@netvarth.com");
		netmd.setHeadOfficeMobile("9947848484");
		netmd.setHeadOfficeName("Regal");
		netmd.setHeadOfficePhone("04885-224859");
		netmd.setName("regal1");
		netmd.setOwnerAddress("kochi");
		netmd.setOwnerEmail("asha.chandran@netvarth.com");
		netmd.setOwnerFirstName("asha");
		netmd.setOwnerMobile("6363636363");
		netmd.setOwnerPhone("04885214141");
		netmd.setPassword("netvarth");
		netmd.setUserName("asha_regal18");
		try {
			service.createNetMd(netmd);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateNetMd() {
		System.out.println("create netmd");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		NetMdDTO netmd = new NetMdDTO();
		netmd.setHeadOfficeAddress("Museum Cross Lane road");
		netmd.setHeadOfficeEmail("asha.chandran@netvarth.com");
		netmd.setHeadOfficeMobile("9947848484");
		netmd.setHeadOfficeName("Regal");
		netmd.setHeadOfficePhone("04885-224859");
		netmd.setName("rickys clinic");
		netmd.setOwnerAddress("kochi");
		netmd.setOwnerEmail("asha.chandran@netvarth.com");
		netmd.setOwnerFirstName("Ricky");
		netmd.setOwnerMobile("6363636363");
		netmd.setOwnerPhone("04885214141");

		netmd.setGlobalId(3);
		try {
			service.updateNetMd(netmd);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void viewNetMd() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");

		try {
			service.viewNetMd(3);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void viewNetMdBranch() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");

		try {
			service.viewNetMdBranch(17);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void deletebranch() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");

		try {
			service.deleteNetMdBranch(11);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateBranch() {
		System.out.println("update branch");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		NetMdBranchDTO branch = new NetMdBranchDTO();
		branch.setName("Jyothis clinic");
		branch.setAddress("Koorkenchery");
		branch.setMobile("9974859685");
		branch.setPhone("9974859685");
		branch.setNumberOfDevices(2);
		branch.setGlobalId(17);
		branch.setNetMdId(25);
		try {
			service.updateNetMdBranch(branch);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void deleteNetMd() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");

		try {
			service.deleteNetMd(10);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateuser() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		NetMdUserDTO userCreationDTO = new NetMdUserDTO();
		NetMdUserDetail user = new NetMdUserDetail();
		user.setFirstName("Misha");
		user.setLastName("Mohanan");
		user.setUserType("admin");
		user.setPhone("0487-2342822");
		user.setMobile("1234567890");
		user.setAddress("kodannur");
		user.setEmail("misha.mohanan@netvarth.com");
		user.setUserName("mishamohan");
		user.setPassword("netvarth");
		user.setGlobalId(3);
		userCreationDTO.setUser(user);
		HeaderDTO header = new HeaderDTO();
		header.setBranchId(5);
		header.setHeadOfficeId(3);
		header.setMacId("");
		header.setPassPhrase("");
		userCreationDTO.setHeader(header);
		try {
			service.updateNetMdUser(userCreationDTO);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void enableLogRequestNull() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		LogDTO log = new LogDTO();
		log.setEnableLog(false);
		HttpServletRequest request = null;
		try {
			service.enableLog(log, request);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void logLlist() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");

		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("applicationName");
			exp.setOperator("eq");
			exp.setValue("Netlims");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			ExpressionDTO exp1 = new ExpressionDTO();
			exp1.setName("actionDate");
			exp1.setOperator("eq");
			exp1.setValue("2013-05-04");
			//exps.add(exp1);
			filter.setExp(exps);
			filter.setCount(50);
			filter.setFrom(0);
			filter.setAsc(true);
			UserLogListResponseDTO response = service.userLogList(filter);
			for (LogDetail log : response.getLog()) {
				System.out.println(log.getLoginTime());
				System.out.println(log.getActionName());
				System.out.println(log.getApplicationName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void createNetRX() {
		System.out.println("create netrx");
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		NetRxDTO netrx = new NetRxDTO();
		netrx.setHeadOfficeAddress("Museum Cross Lane road");
		netrx.setHeadOfficeEmail("asha.chandran@netvarth.com");
		netrx.setHeadOfficeMobile("9947848484");
		netrx.setHeadOfficeName("Env");
		netrx.setHeadOfficePhone("02085242101");
		netrx.setName("regal netrx2");
		netrx.setOwnerAddress("kochi");
		netrx.setOwnerEmail("asha.chandran@netvarth.com");
		netrx.setOwnerFirstName("asha");
		netrx.setOwnerMobile("6363636363");
		netrx.setOwnerPhone("04872279166");
		netrx.setPassword("netvarth");
		netrx.setUserName("regalnetrx8");
		try {
			/*if (netrx.getHeadOfficePhone().matches("^0?[1-9]{1}[0-9]{9}$")) {
				System.out.println("correct");
			}*/
			service.createNetRx(netrx);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateNetRx() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		NetRxDTO netrx = new NetRxDTO();
		netrx.setHeadOfficeAddress("Museum Cross Lane road");
		netrx.setHeadOfficeEmail("asha.chandran@netvarth.com");
		netrx.setHeadOfficeMobile("9947848484");
		netrx.setHeadOfficeName("Regal");
		netrx.setHeadOfficePhone("04885224859");
		netrx.setName("regal netrx2");
		netrx.setOwnerAddress("kochi");
		netrx.setOwnerEmail("asha.chandran@netvarth.com");
		netrx.setOwnerFirstName("Asha");
		netrx.setOwnerLastName("Vbn");
		netrx.setOwnerMobile("6363636363");
		netrx.setOwnerPhone("04885214141");

		netrx.setGlobalId(4);
		try {
			service.updateNetRx(netrx);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void listNetrx() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("name");
			exp.setOperator("like");
			exp.setValue("asha");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			NetRxListResponseDTO response = service.getNetRxList(filter);
			for (NetRxDetail b : response.getNetRx()) {
				System.out.println(b.getName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void testList() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("uid");
			exp.setOperator("eq");
			exp.setValue("152");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			TestListResponseDTO response = service.testList(filter);
			for (TestDTO b : response.getTestList()) {
				System.out.println(b.getTestName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void specimenList() {
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("specimenUid");
			exp.setOperator("eq");
			exp.setValue("1");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			SpecimenListResponseDTO response = service.testSpecimenList(filter);
			for (SpecimenDTO b : response.getSpecimenList()) {
				System.out.println(b.getSpecimenName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void createOrganisationAccount(){
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		try {
			Organisation org= new Organisation();
			org.setOwnerFirstName("saga");
			org.setName("Kerala Gynecology federation");
			org.setHeadOfficeName("keral gynecology federation");
			org.setDepartmentType("obstetrics");
			org.setHeadOfficeEmail("luciya.jose@netvarth.com");
			org.setOwnerFirstName("Dr.Paily");
			org.setOwnerEmail("luciya.jose@netvarth.com");
			org.setUserName("paily");
			org.setPassword("netvarth");
			service.createOrganisation(org);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void updateOrganisationAccount(){
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		try {
			Organisation org= new Organisation();
			org.setOwnerFirstName("saga");
			org.setName("Kerala Gynecology federation");
			org.setHeadOfficeName("keral gynecology federation");
			org.setDepartmentType("obstetrics");
			org.setHeadOfficeEmail("luciya.jose@netvarth.com");
			org.setOwnerFirstName("Dr.Paily");
			org.setOwnerEmail("luciya.jose@netvarth.com");
			org.setUserName("paily");
			org.setPassword("netvarth");
			org.setId(6);
			service.updateOrganisation(org);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void viewOrganisationAccount(){
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		try {
			
			service.viewOrganisation(6);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteOrganisationAccount(){
		SuperAdminService service = (SuperAdminService) applicationContext
				.getBean("superAdmin.service");
		try {
			service.deleteOrganisation(6);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
}
