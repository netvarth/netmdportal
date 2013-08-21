package com.nv.youNeverWait.user.bl.test;
/**
 * LabServiceTest.java
 * 
 * @Author Asha Chandran
 *
 * June 03, 2013
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.BranchDetail;
import com.nv.youNeverWait.rs.dto.BranchOrderDTO;
import com.nv.youNeverWait.rs.dto.BranchOrderDetail;
import com.nv.youNeverWait.rs.dto.LabBranchDTO;
import com.nv.youNeverWait.rs.dto.BranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LabBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.LabBranchSystemInfoDetails;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabUserDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.PatientInfoDetail;
import com.nv.youNeverWait.rs.dto.ResultTransferDTO;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.rs.dto.TransferNetMdResultDTO;
import com.nv.youNeverWait.rs.dto.UserBranchDTO;
import com.nv.youNeverWait.security.bl.service.AuthenticationService;
import com.nv.youNeverWait.user.bl.service.LabService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youNeverWait-context.xml"})
public class LabServiceTest {
	@Autowired
	private ApplicationContext applicationContext;	


	@Test
	public void createUser(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("Anjana");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("anjana");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("leonora.louis@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createUserWithWrongLabBranchId(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(452);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("ashritha");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("ashirdha");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("asshridha@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createUserWithalreadyUsedLoginDetails(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(452);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("mani");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("mani");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("njithesh.mohanan@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	
	@Test
	public void createUserWithWrongActionName(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Minus");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("Aadhithya");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("aadhi");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("kelvin@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createUserWithBranchIdListEmpty(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
//		UserBranchDTO branch1=new UserBranchDTO();
//		branch1.setBranchId(50);
//		branch1.setActionName("Minus");
//		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("hbo");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("hbo");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("hbo@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	
	@Test
	public void createUserWithWrongLabId(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("amriotha");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(492);
		user.setFirstName("amritha");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("amrithatv@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createUserWithFirstNameNullOrEmpty(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("surya");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("suryatv@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createUserWrongEmailFormat(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("asianet");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("asianet");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("wew#@.com.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createUserWrongPhoneFormat(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("keralvision");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("keralaVision");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("4562312563");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("keralatv@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createUserWrongMobileFormat(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("manorama");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("manorama");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("04885240154");
		user.setMobile("123456789012");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("manorama@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	
	@Test
	public void createUserInvalidUserType(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("mathrubhumi");
		login.setPassword("netvarth");
		login.setUserType("owner");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("mathrubhumi");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("04885240154");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("mathrubhumi@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void createUserWithAlreadyUsedEmail(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("sony");
		login.setPassword("netvarth");
		login.setUserType("admin");
		user.setLogin(login);
		user.setLabId(49);
		user.setFirstName("sony");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("04885240154");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("luciya.jose@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	
	@Test
	public void createUserWithLabIdZero(){	
		System.out.println("##########create user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();

		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(50);
		branch1.setActionName("Add");
		userbranch.add(branch1);
		LoginDTO login =new LoginDTO();
		login.setUserName("zee");
		login.setPassword("netvarth");
		login.setUserType("owner");
		user.setLogin(login);
		user.setLabId(0);
		user.setFirstName("zee");
		user.setLastName("thomas");
		user.setUserType("admin");
		user.setPhone("04885240154");
		user.setMobile("1234567890");
		user.setAddress("sdfdgdfgdfg");
		user.setEmail("zee@netvarth.com");
		user.setBranchIds(userbranch);
		try{
			service.createUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	
	@Test
	public void updateUserSuccess(){	
		System.out.println("##########update user################ ");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabUserDTO user= new LabUserDTO();
		List<UserBranchDTO> userbranch=new ArrayList<UserBranchDTO>();
		UserBranchDTO branch1=new UserBranchDTO();
		branch1.setBranchId(130);
		branch1.setActionName("Delete");
		userbranch.add(branch1);
		UserBranchDTO branch2=new UserBranchDTO();
		branch2.setBranchId(133);
		branch2.setActionName("Add");
		userbranch.add(branch2);
		user.setBranchIds(userbranch);
		user.setGlobalId(3);
		user.setLabId(230);
		user.setFirstName("Nimmy");
		user.setLastName("Jose");
		user.setUserType("admin");
		user.setPhone("0487-2342822");
		user.setMobile("1234567890");
		user.setAddress("Nimmy");
		user.setEmail("Nimmy@gmail.com");
		try{
			service.updateUser(user);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	
	@Test
	public void createBranchSuccess(){
		System.out.println("create branch");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO branch =new LabBranchDTO();
		branch.setLabId(89);
		branch.setName("Jankanan");
		branch.setAddress("Thrissur");
		branch.setMobile("9974859685");
		branch.setHomeBranch(true);
		try{
			service.createBranch(branch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createBranchLabIdAlreadyExists(){
		System.out.println("create branch");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO branch =new LabBranchDTO();
		branch.setLabId(12);
		branch.setName("Bheema");
		branch.setAddress("Thrissur");
		branch.setMobile("9974859685");
		branch.setHomeBranch(true);
		try{
			service.createBranch(branch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void ResultTransferToNetMd(){
		System.out.println("Transfering Result");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		TransferNetMdResultDTO resultTranfer =new TransferNetMdResultDTO();
		LabHeaderDTO header=new LabHeaderDTO();
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
		resultTranfer.setHeader(header);
		resultTranfer.setSourceLabId(49);
		resultTranfer.setSourceLabBranchId(50);
		resultTranfer.setDoctorEmail("luciya.jose@netvarth.com");
		resultTranfer.setOrderUid("JV8813");
		resultTranfer.setOrderDate("2013-07-05 10:10:10");
		resultTranfer.setResult("Dietary recommendations with meal recipe");
		PatientInfoDetail patient= new PatientInfoDetail();
		patient.setFirstName("jinu");
		patient.setEmail("luciya.jose@netvarth.com");
		resultTranfer.setPatient(patient);

		try{
			service.transferResultToNetMd(resultTranfer);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void forgotPassword(){
		System.out.println("Forgot Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("ashvbn");
		try{
			service.forgotPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void forgotPasswordWithUserNameNullOrEmpty(){
		System.out.println("Forgot Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("");
		try{
			service.forgotPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void forgotPasswordWithWrongUserName(){
		System.out.println("Forgot Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("ashvbbn");
		try{
			service.forgotPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void forgotPasswordWithUserTypeAdmin(){
		System.out.println("Forgot Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("anju");
		try{
			service.forgotPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void forgotPasswordWithUserTypeOwner(){
		System.out.println("Forgot Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("mani");
		try{
			service.forgotPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void forgotPasswordWithWrongLoginId(){
		System.out.println("Forgot Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("nancy");
		try{
			service.forgotPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void resetPasswordWithWrongUsername(){
		System.out.println("Reset Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("mish/=+78hjkl++++====1233hjuiha");
		login.setPassword("netvarth");
		try{
			service.resetPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void resetPassword(){
		System.out.println("Reset Password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("mani");
		login.setPassword("netvarth");
		try{
			service.resetPassword(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}


	@Test
	public void retrieveUserList(){
		System.out.println("retrieve user list");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header = new LabHeaderDTO();
		header.setLabBranchId(31);
		header.setLabId(130);
		header.setMacId("gjffd");
		header.setPassPhrase("gf");
		String lastSyncTime="2013-10-10 12:10:10";
		Date currentTime= new Date();

		try{
			service.retrieveUserList(header, lastSyncTime, currentTime);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void retrieveLabList(){
		System.out.println("retrieve Lab list");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header = new LabHeaderDTO();
		header.setLabBranchId(31);
		header.setLabId(130);
		header.setMacId("gjffd");
		header.setPassPhrase("gf");
		String lastSyncTime="2013-10-10 12:10:10";
		Date currentTime= new Date();
		try{
			service.retrieveLabList(header, lastSyncTime, currentTime);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void retrieveNetmdList(){
		System.out.println("retrieve Netmd list");
		LabService service =(LabService) applicationContext.getBean("lab.service");

		LabHeaderDTO header = new LabHeaderDTO();
		header.setLabBranchId(50);
		header.setLabId(49);
		header.setMacId("123");
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		String lastSyncTime="2013-03-02 15:40:40";
		Date currentTime= new Date();
		try{
			service.retrieveNetmdList(header, lastSyncTime, currentTime);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void retrieveNetmdBranchList(){
		System.out.println("retrieve Netmd  Branchlist");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header = new LabHeaderDTO();
		header.setLabBranchId(50);
		header.setLabId(49);
		header.setMacId("123");
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		String lastSyncTime="2013-03-02 15:40:40";
		Date currentTime= new Date();
		try{
			service.retrieveNetmdBranchList(header, lastSyncTime, currentTime);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}


	
	@Test
	public void updateBranch(){
		System.out.println("update branch");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO branch =new LabBranchDTO();
		branch.setName("Bheema changed");
		branch.setAddress("Thrissur");
		branch.setMobile("9974859685");
		branch.setHomeBranch(true);
		branch.setStatus("active");
		branch.setGlobalId(64);
		branch.setLabId(12);
		try{
			service.updateBranch(branch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteBranchWrong(){
		System.out.println("delete Branch");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO branch =new LabBranchDTO();
		branch.setGlobalId(7);
		branch.setLabId(9);
		try{
			service.deleteBranch(branch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteBranchSuccess(){
		System.out.println("delete Branch");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO branch =new LabBranchDTO();
		branch.setGlobalId(9);
		branch.setLabId(11);
		try{
			service.deleteBranch(branch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void viewBranchWrong(){
		System.out.println("view Branch");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			service.viewBranch(4);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewBranchSuccess(){
		System.out.println("view Branch");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			service.viewBranch(9);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void listBranch(){
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("status");
			exp.setOperator("eq");
			exp.setValue("ACTIVe");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			BranchListResponseDTO response = service.branchList(filter);
			for (BranchDetail brnch : response.getBranch()) {
				System.out.println(brnch.getGlobalId());
			}
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void getMacStatus(){
		System.out.println("checking Mac id exist or not");
		LabService service =(LabService) applicationContext.getBean("lab.service");

		try{
			service.getMacStatus(null);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}


	@Test
	public void activatenetLimsWrongPassphrase(){
		System.out.println("Setting Mac");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		header.setMacId("00-1C-C0-5A-AA-7B");
		header.setPassPhrase("FDjZDflPVkT5Dgmt6hGbGg==");
		try{
			service.activateLab(header);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void activatenetLimsSuccess(){
		System.out.println("Setting Mac");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		header.setMacId("00-1C-C0-5A-AA-7B");
		header.setPassPhrase("rLWKLICl/Hk9jc2mE5okCw==");
		try{
			service.activateLab(header);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void transferResult(){
		System.out.println("transferring result");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		ResultTransferDTO result = new ResultTransferDTO();
		result.setDestinationLabId(249);
		result.setDestinationBranchId(180);
		result.setOrderUid("JV005");
		result.setResult("Diabetics all around");
		result.setSourceLabId(249);
		result.setSourceBranchId(179);
		LabHeaderDTO header=new LabHeaderDTO();
		header.setMacId("123");
		header.setPassPhrase("iStxEG1I7u5a/vQAcIRBpg==");
		header.setLabId(249);
		header.setLabBranchId(179);
		result.setHeader(header);

		try{
			service.transferResult(result);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void InvalidtransferResult(){
		System.out.println("transferring result");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		ResultTransferDTO result = new ResultTransferDTO();
		result.setDestinationLabId(248);
		result.setDestinationBranchId(182);
		result.setOrderUid("JV005");
		result.setResult("Pressure all around");
		result.setSourceLabId(249);
		result.setSourceBranchId(179);
		LabHeaderDTO header=new LabHeaderDTO();
		header.setMacId("123");
		header.setPassPhrase("iStxEG1I7u5a/vQAcIRBpg==");
		header.setLabId(249);
		header.setLabBranchId(179);
		result.setHeader(header);

		try{
			service.transferResult(result);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	/**
	 * Creates random string. 
	 */
	public void encryptString(){
		Random randomString = new Random();
		String token = Long.toString(randomString.nextLong(), 36);
		System.out.println(token);
		String enc =StringEncoder.encryptWithStaticKey(token.substring(0,6));
		System.out.println("enc "+enc);
		System.out.println(StringEncoder.decryptWithStaticKey(enc));
	}

	@Test
	public void getBranchList(){
		System.out.println("checking Mac id exist or not");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header = new LabHeaderDTO();
		header.setLabBranchId(7);
		header.setLabId(9);
		header.setMacId("123");
		header.setPassPhrase("FDjZDflPVkT5Dgmt6hGbGg==");
		String lastSyncTime="2013-03-02 15:40:40";
		Date currentTime= new Date();
		try{
			LabBranchListResponseDTO  response = service.retrieveLabBranchList(header, lastSyncTime, currentTime);
//			System.out.println("new "+response.getNewBranchList().size());
//			System.out.println("udated "+response.getUpdatedBranchList().size());
			System.out.println("own "+response.getOwnBranchList().size());
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void orderList(){
		System.out.println("view Branch orders list by date");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO= new BranchOrderDTO ();
		orderDTO.setFromDate("2013-06-24");
		orderDTO.setToDate("2013-06-25");
		try{
			
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void createTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(25251);
		detail.setPaidAmount(5000);
		detail.setNetAmount(45451);
		detail.setLastOrderdTime("2013-07-05 09:29:15");
		detail.setOrderDate("2013-07-05");
		detail.setId(5);
		header.setLabId(238);
		header.setLabBranchId(152);
		header.setPassPhrase("Z6z2gmRFYN5zK4h6BlENgQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime("2013-06-26 12:12:12");
		detail.setOrderDate("2013-06-26");
		detail.setId(5);
		header.setLabId(238);
		header.setLabBranchId(152);
		header.setPassPhrase("Z6z2gmRFYN5zK4h6BlENgQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void wrongPassPhraseTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setOrderDate("2013-04-26");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==pops");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void wrongMacIdTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setOrderDate("2013-04-26");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123XXXXXX");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void wrongLabIdTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setOrderDate("2013-04-26");
		detail.setId(5);
		header.setLabId(49000);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void wrongLabBranchIdTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setOrderDate("2013-04-26");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50111111);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void OrderDateEmptyTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setOrderDate("");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void OrderDateNullTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setId(5);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setOrderDate(null);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void wrongOrderDateFormatTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setId(5);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setOrderDate("2007-03-T1");
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void wrongRexOrderDateTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime("2013-04-26 12:12:12");
		detail.setId(5);
		detail.setOrderDate("2013-2123-45");
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void lastOrderTimeEmptyTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setId(5);
		detail.setLastOrderdTime("");
		detail.setOrderDate("2013-04-05");
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void lastOrderTimeNullTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void MacIdNullTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId(null);
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void MacIdEmptyTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void PassPhraseNullTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase(null);
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void passphraseEmptyTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(50);
		header.setPassPhrase("");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void labBranchIdNegativeValTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(49);
		header.setLabBranchId(-50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void labBranchIdZeroTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setId(5);
		detail.setOrderDate("2013-04-05");
		header.setLabId(49);
		header.setLabBranchId(0);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void labIdNegativeValTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(-45);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void labIdZeroTotalOrders(){
		System.out.println("Creating brnch total orders.....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabHeaderDTO header=new LabHeaderDTO();
		BranchOrderDetail detail= new BranchOrderDetail();
		detail.setTotalOrders(12000);
		detail.setPaidAmount(12500);
		detail.setNetAmount(25000);
		detail.setLastOrderdTime(null);
		detail.setOrderDate("2013-04-05");
		detail.setId(5);
		header.setLabId(0);
		header.setLabBranchId(50);
		header.setPassPhrase("lKbN2WxYPZWPJ73TLgPLVQ==");
		header.setMacId("123");
	

		try{
			service.createTotalOrders(header, detail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void OrderListSuccess(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("2013-07-01");
		orderDTO.setToDate("2013-07-05");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrderListFromDateEmpty(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("");
		orderDTO.setToDate("2013-06-24");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrderListFromDateNull(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate(null);
		orderDTO.setToDate("2013-06-24");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrderListToDateEmpty(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("2013-04-26");
		orderDTO.setToDate("");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrderListToDateNull(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("2013-04-26");
		orderDTO.setToDate(null);

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrderListFromandToEmpty(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("");
		orderDTO.setToDate("");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrderListFromAndToNull(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate(null);
		orderDTO.setToDate(null);

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void OrderListToDateWrongRex(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("2013-04-26");
		orderDTO.setToDate("2013-0445-26");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void OrderListFromDateWrongREx(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("2013-1204-26");
		orderDTO.setToDate("2013-04-26");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrderListFromDateGreaterThanToDate(){
		System.out.println("Showing orders list....");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		BranchOrderDTO orderDTO = new BranchOrderDTO();
		orderDTO.setLabId(49);
		orderDTO.setLabBranchId(50);
		orderDTO.setFromDate("2013-06-26");
		orderDTO.setToDate("2013-04-26");

		try{
			service.orderList(orderDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void viewBranchOrdersSuccess(){
		System.out.println("view branch orders...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=49;
			service.viewBranchOrders(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewBranchOrdersWithGlobalIdZero(){
		System.out.println("view branch orders...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=0;
			service.viewBranchOrders(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void viewBranchOrdersWrongLabId(){
		System.out.println("view branch orders...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=149;
			service.viewBranchOrders(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void viewBranchOrdersWithLabIdNegativeVal(){
		System.out.println("view branch orders...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=-89;
			service.viewBranchOrders(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewUserWithGlobalIdCorrect(){
		System.out.println("view user...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=13;
			service.viewUser(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewUserWithGlobalIdWrong(){
		System.out.println("view user...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=89;
			service.viewUser(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewUserWithGlobalIdZero(){
		System.out.println("view user...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=0;
			service.viewUser(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteUserWithGlobalIdCorrect(){
		System.out.println("deleteUserWithGlobalIdCorrect...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=13;
			service.deleteUser(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteUserWithGlobalIdWrong(){
		System.out.println("deleteUserWithGlobalIdWrong...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=89;
			service.deleteUser(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteUserWithGlobalIdZero(){
		System.out.println("deleteUserWithGlobalIdZero...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			int globalId=0;
			service.deleteUser(globalId);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void clearMacIdWithLabIdZeroOrNegVal(){
		System.out.println("clearMacIdWithLabIdZeroo...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO branch=new LabBranchDTO();
		branch.setLabId(0);
		branch.setGlobalId(50);
		
		try{
			service.clearMacId(branch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void clearMacWithBranchGlobalIdZeroOrNegVal(){
		System.out.println("clearMacWithBranchGlobalIdZeroOrNegVal");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO netlimsBranch= new LabBranchDTO();
		netlimsBranch.setLabId(49);
		netlimsBranch.setGlobalId(0);
		try{
			service.clearMacId(netlimsBranch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void clearMacWithWrongLabId(){
		System.out.println("clearMacWithWrongLabId");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO netlimsBranch= new LabBranchDTO();
		netlimsBranch.setLabId(453);
		netlimsBranch.setGlobalId(50);
		try{
			service.clearMacId(netlimsBranch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void clearMacWithWrongBranchGlobalId(){
		System.out.println("clearMacWithWrongBranchGlobalId");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO netlimsBranch= new LabBranchDTO();
		netlimsBranch.setLabId(49);
		netlimsBranch.setGlobalId(765);
		try{
			service.clearMacId(netlimsBranch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void clearMacSuccess(){
		System.out.println("clearMacSuccess");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchDTO netlimsBranch= new LabBranchDTO();
		netlimsBranch.setLabId(189);
		netlimsBranch.setGlobalId(119);
		try{
			service.clearMacId(netlimsBranch);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordWithOldAndNewPasswordNull(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword(null);
		passwords.setNewPassword(null);
		passwords.setUsername("liyanto");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void changePasswordWithNewPasswordNull(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword(null);
		passwords.setUsername("liyanto");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordWithOldPasswordNull(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword(null);
		passwords.setNewPassword("liyanto");
		passwords.setUsername("liyanto");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordWithOldPasswordStringEmpty(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("");
		passwords.setNewPassword("sdfsf");
		passwords.setUsername("liyanto");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordWithNewPasswordStringEmpty(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword("");
		passwords.setUsername("liyanto");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordWithNewPasswordAndOldPasswordStringEmpty(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("");
		passwords.setNewPassword("");
		passwords.setUsername("liyanto");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void changePasswordWrongOldPwd(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword("netvarth");
		passwords.setUsername("ashvbn");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordWrongUserName(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("thara");
		passwords.setNewPassword("netvarth");
		passwords.setUsername("abcd");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordWithUserNameNullOrEmpty(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword("liyanto");
		passwords.setUsername("");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void changePasswordSuccess(){
		System.out.println("change password");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword("liyanto");
		passwords.setUsername("liyanto");
		try{
			service.changePassword(passwords);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void viewLabSuccess(){
		System.out.println("view lab success");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			service.view(49);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewLabWithGlobalIdZeroOrNegVal(){
		System.out.println("view lab with wrong global id");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			service.view(0);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewLabWithWrongGlobalId(){
		System.out.println("view lab with wrong global id");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			service.view(500);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void deleteLabWithGlobalIdCorrect(){
		System.out.println("deleteLabWithGlobalIdCorrect...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			
			service.delete(49);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteLabWithGlobalIdWrong(){
		System.out.println("deleteLabWithGlobalIdWrong...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
			
			service.delete(89);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteLabWithGlobalIdZero(){
		System.out.println("deleteLabWithGlobalIdZero...");
		LabService service =(LabService) applicationContext.getBean("lab.service");
		try{
		
			service.delete(0);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorFreqTypeNullOrEmpty(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorWrongFreqType(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void systemHealthMonitorWrongPassphrase(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==abcd");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorNullPassPhraseOrEmpty(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase(null);
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorWrongMacId(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7Beeeeeeeeeeee");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorNullMacIdOrEmpty(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId(null);
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorWrongLabId(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorLabIdNegativeOrZero(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(0);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorWrongLabBranchId(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(1022);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorLabBranchIdZeroOegative(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(-1);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorWrongCpuUsage(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("wert");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorWrongHardDiskUsage(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("we8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorWrongMemoryusage(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("1qw9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void systemHealthMonitorTotalCpuUsageMissMatch(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("80");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorTotalMemoryMissMatch(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("8");
		systemResponse.setMemoryUsed("80");
		systemResponse.setTotalCpuSpace("80");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void systemHealthMonitorTotalHardDiskSpaceMissMatch(){	
		LabService service =(LabService) applicationContext.getBean("lab.service");
		SystemHealthDetails systemResponse= new SystemHealthDetails();
		LabHeaderDTO header= new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		systemResponse.setHeader(header);
		systemResponse.setCpuUsage("8");
		systemResponse.setHardDiskUsed("80");
		systemResponse.setMemoryUsed("9");
		systemResponse.setTotalCpuSpace("4");
		systemResponse.setTotalHardDiskSpace("80");
		systemResponse.setTotalMemorySpace("80");
		try{
			service.getHealthMonitor(systemResponse);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateBranchSystemDefaultInfo(){
		LabService service =(LabService) applicationContext.getBean("lab.service");
		LabBranchSystemInfoDetails details = new LabBranchSystemInfoDetails();
		details.setBranchId(122);
		details.setCriticalCpuLevel("");
		details.setCriticalHardDiskSpaceLevel("3");
		details.setCriticalMemoryLevel("2");
		details.setFreqType("daily");
		details.setIntervalTime("2");
		try{
		
			service.updateLabBranchSystemInfo(details);
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
		
	}
}

