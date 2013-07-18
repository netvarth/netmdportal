/**
 * 
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

import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentDTO;
import com.nv.youNeverWait.rs.dto.AppointmentDetailsDTO;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDetail;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.PatientOrderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.ResultDTO;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleListDTO;
import com.nv.youNeverWait.user.bl.service.DoctorService;
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.service.PatientService;

/**
 * @author Luciya Jose
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class PatientServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void filterpatientResult() {
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("patientId");
			exp.setOperator("eq");
			exp.setValue("73");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			ResultListResponseDTO response = service.getresultList(filter);
			for (ResultDTO b : response.getResultList()) {
				System.out.println(b.getTestName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void deleteappointmentWrong() {
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			service.deleteAppointment(2);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void deleteappointmentSuccess() {
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			service.deleteAppointment(3);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changepassword() {
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			PasswordDTO passwords = new PasswordDTO();
			passwords.setUsername("vbn");
			passwords.setOldPassword("netvarth");
			passwords.setNewPassword("ricky");
			service.changePassword(passwords);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void forgotPassword(){
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try{
		LoginDTO login = new LoginDTO();
		login.setUserName("+BxeQK9EYSBLFI93CMEeSQ==");
		service.forgotPassword(login);
		}catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createPassword() {
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			CreatePasswordDTO passwords = new CreatePasswordDTO();
			String userName = "netvarth";
			String encryptedUserName = StringEncoder
					.encryptWithStaticKey(userName);
			passwords.setConfirmPassword("netvarth");
			passwords.setPassword("netvarth");
			passwords.setUsername(encryptedUserName);
			service.createPassword(passwords);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createFailed() {
		System.out.println("$$$$$$$$$$$$$$$$Create Patient$$$$$$$$$$$ ");
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(10);
		header.setNetMdBranchId(11);
		header.setPassPhrase("1TVt2Ao19pbaFyS3e7pNWQ==");
		header.setMacId("08-00-27-00-18-11");
		LoginDTO login = new LoginDTO();
		login.setUserName("maneesha@netvarth.com");
		login.setUserType("patient");
		PatientDetail detail = new PatientDetail();
		detail.setBranchId(5);
		detail.setFirstName("maneesha");
		detail.setLastName("joyson");
		detail.setPhone("01224242442");
		detail.setAddress("Vadakkan");
		detail.setGender("Male");
		detail.setAge(20);
		detail.setEmail("maneesha@netvarth.com");
		detail.setMobile("9234567821");
		detail.setLogin(login);
		


		try {
			service.createPatient(detail, header);
			System.out.println("saved");
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError().getErrMsg());
			System.out.println(e.getParamList());

		}
	}


	@Test
	public void createSuccess() {
		System.out.println("$$$$$$$$$$$$$$$$Create Patient$$$$$$$$$$$ ");
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setNetMdBranchId(5);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-80-48-6E-E1-E2");
		LoginDTO login = new LoginDTO();
		login.setUserName("maneesha@netvarth.com");
		login.setUserType("patient");
		PatientDetail detail = new PatientDetail();
		detail.setBranchId(5);
		detail.setFirstName("maneesha");
		detail.setLastName("joyson");
		detail.setPhone("01224242442");
		detail.setAddress("Vadakkan");
		detail.setGender("Male");
		detail.setAge(20);
		detail.setEmail("maneesha@netvarth.com");
		detail.setMobile("9234567821");
		detail.setLogin(login);
		


		try {
			service.createPatient(detail, header);
			System.out.println("saved");
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError().getErrMsg());
			System.out.println(e.getParamList());

		}
	}

	@Test
	public void createAppointment() {
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		Appointment appointment = new Appointment();
		AppointmentDetailsDTO details = new AppointmentDetailsDTO();
		HeaderDTO header = new HeaderDTO();
		details.setScheduleId(53);
		details.setDoctorId(27);
		details.setPatientId(61);
		details.setPatientName("jayaram");
		details.setStartDate("2013-05-07");
		details.setStartTime("10:00 am");
		appointment.setAppointmentDetails(details);		
		header.setNetMdId(3);
		header.setNetMdBranchId(5);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-80-48-6E-E1-E2");
		appointment.setHeader(header);
		try {
			service.createAppointment(appointment);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updatePatoentPhoneFromatWrong() {
		System.out.println("$$$$$$$$$$$$$$$$Update Patient$$$$$$$$$$$ ");
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDTO patient = new PatientDTO();
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("123");
		header.setMacId("56");
		header.setNetMdBranchId(5);
		patient.setHeader(header);

		PatientDetail detail = new PatientDetail();
		detail.setGlobalId(14);
		detail.setFirstName("Neenu");
		detail.setLastName("Seeraj");
		detail.setPhone("012 2424244");
		detail.setAddress("Vadakkan");
		detail.setGender("Female");
		detail.setAge(28);
		detail.setEmail("grace001@gmail.com");
		detail.setMobile("1234567890");
		patient.setPatientDetail(detail);

		try {
			service.updatePatient(patient.getPatientDetail(),
					patient.getHeader());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientWrongMobileFormat() {
		System.out.println("$$$$$$$$$$$$$$$$Update Patient$$$$$$$$$$$ ");
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDTO patient = new PatientDTO();
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("123");
		header.setMacId("56");
		header.setNetMdBranchId(5);
		patient.setHeader(header);

		PatientDetail detail = new PatientDetail();
		detail.setGlobalId(14);
		detail.setFirstName("Neenu");
		detail.setLastName("Seeraj");
		detail.setPhone("04872535353");
		detail.setAddress("Vadakkan");
		detail.setGender("Female");
		detail.setAge(28);
		detail.setEmail("grace001@gmail.com");
		detail.setMobile("12345678900");
		patient.setPatientDetail(detail);

		try {
			service.updatePatient(patient.getPatientDetail(),
					patient.getHeader());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updatePatientInvalidEmailId() {
		System.out.println("$$$$$$$$$$$$$$$$Update Patient$$$$$$$$$$$ ");
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDTO patient = new PatientDTO();
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("123");
		header.setMacId("56");
		header.setNetMdBranchId(5);
		patient.setHeader(header);

		PatientDetail detail = new PatientDetail();
		detail.setGlobalId(14);
		detail.setFirstName("Neenu");
		detail.setLastName("Seeraj");
		detail.setPhone("04872535353");
		detail.setAddress("Vadakkan");
		detail.setGender("Female");
		detail.setAge(28);
		detail.setEmail("grace001@gmail");
		detail.setMobile("1234567890");
		patient.setPatientDetail(detail);

		try {
			service.updatePatient(patient.getPatientDetail(),
					patient.getHeader());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updatePatientSuccess() {
		System.out.println("$$$$$$$$$$$$$$$$Update Patient$$$$$$$$$$$ ");
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientDTO patient = new PatientDTO();
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-80-48-6E-E1-E2");
		header.setNetMdBranchId(5);
		patient.setHeader(header);

		PatientDetail detail = new PatientDetail();
		detail.setGlobalId(14);
		detail.setFirstName("Naraen");
		detail.setLastName("Seeraj");
		detail.setPhone("04872535353");
		detail.setAddress("Vadakkan");
		detail.setGender("Female");
		detail.setAge(28);
		detail.setEmail("grace001@gmail.com");
		detail.setMobile("1234567890");
		detail.setStatus("active");
		patient.setPatientDetail(detail);

		try {
			service.updatePatient(patient.getPatientDetail(),
					patient.getHeader());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void daylist() {
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
//		ViewScheduleListDTO response = new ViewScheduleListDTO();
		try {
			// service.weeklyView("2013-03-18");
			// service.monthlyView("2013-03-01", "2013-03-31");
			service.listAppointmentsForPatient("5");
		} catch (ServiceException e) {
			

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void view() {
		System.out.println("$$$$$$$$$$$$$$$$View Patient$$$$$$$$$$$ ");
		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			PatientDetail pd = new PatientDetail();
			pd = service.viewPatient(5);
			System.out.println("----------->" + pd.getFirstName());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listDoctors() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			service.listDoctors("5");

		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listOfPatientsOnlogin() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {

			service.patientListOnLogin("asha@gmail.com");
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listAppointments() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			service.listAppointmentsForPatient("5");
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listBranches() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		List<ExpressionDTO> exList = new ArrayList<ExpressionDTO>();
		FilterDTO filterDTO = new FilterDTO();

		ExpressionDTO exdto = new ExpressionDTO();
		ExpressionDTO exdto1 = new ExpressionDTO();
		exdto.setName("patientFirstName");
		exdto.setOperator("eq");
		exdto.setValue("asha");
		exdto1.setName("patientEmail");
		exdto1.setOperator("eq");
		exdto1.setValue("asha@gmail.com");
		exList.add(exdto);
		exList.add(exdto1);
		filterDTO.setExp(exList);
		try {
			service.getBranchList(filterDTO);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void getPastAppointmentList() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			service.getPastAppointmentList("5");
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void delete() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		try {
			service.deletePatient(5);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void patientTestResultPatientIdAndOrderIdNullOrEmpty() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientOrderDTO patient= new PatientOrderDTO();
		patient.setOrderId("");
		patient.setPatientId(0);
		try {
			service.patientTestResult(patient);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void patientTestResultOrderIdNullOrEmpty() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientOrderDTO patient= new PatientOrderDTO();
		patient.setOrderId(null);
		patient.setPatientId(73);
		try {
			service.patientTestResult(patient);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void patientTestResultOrderIdNegVal() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientOrderDTO patient= new PatientOrderDTO();
		patient.setOrderId("JV123");
		patient.setPatientId(-1);
		try {
			service.patientTestResult(patient);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void patientTestResultWrongPatientId() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientOrderDTO patient= new PatientOrderDTO();
		patient.setOrderId("2");
		patient.setPatientId(888);
		try {
			service.patientTestResult(patient);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void patientTestResultOrderIdWrong() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientOrderDTO patient= new PatientOrderDTO();
		patient.setOrderId("123333");
		patient.setPatientId(73);
		try {
			service.patientTestResult(patient);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void patientTestResultSucccess() {

		PatientService service = (PatientService) applicationContext
				.getBean("patient.service");
		PatientOrderDTO patient= new PatientOrderDTO();
		patient.setOrderId("JV8813");
		patient.setPatientId(73);
		try {
			service.patientTestResult(patient);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
