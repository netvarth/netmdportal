/**
 * NetRxServiceTest.java
 *
 * @Author Luciya Jos
 * May 8, 2013 
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
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdDetail;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDetail;
import com.nv.youNeverWait.rs.dto.NetRxHeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDetail;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.user.bl.service.LabService;
import com.nv.youNeverWait.user.bl.service.NetMdService;
import com.nv.youNeverWait.user.bl.service.NetRxService;

/**
 * @author netvarth
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class NetRxServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	
	@Test
	public void forgotPasswordWrongUserName(){
		System.out.println("Forgot Password");
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("luciyaJi");
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
	public void forgotPasswordUserNameNullOrEmpty(){
		System.out.println("Forgot Password");
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		LoginDTO login =new LoginDTO();
		login.setUserName(null);
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
	public void forgotPasswordSuccess(){
		System.out.println("Forgot Password");
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("luciya");
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
	public void changePasswordWithOldAndNewPasswordNull(){
		System.out.println("change password");
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword(null);
		passwords.setNewPassword(null);
		passwords.setUsername("usha");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth1");
		passwords.setNewPassword(null);
		passwords.setUsername("usha");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword(null);
		passwords.setNewPassword("usha");
		passwords.setUsername("usha");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("");
		passwords.setNewPassword("sdfsf");
		passwords.setUsername("usha");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth1");
		passwords.setNewPassword("");
		passwords.setUsername("usha");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("");
		passwords.setNewPassword("");
		passwords.setUsername("usha");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvartkkkk");
		passwords.setNewPassword("netvarth1");
		passwords.setUsername("usha");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
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
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth1");
		passwords.setNewPassword("usha");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth1");
		passwords.setNewPassword("usha");
		passwords.setUsername("usha");
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
	public void resetPassword(){
		System.out.println("Reset Password");
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("G9nEs7jGKTyIRe/LbR1d6Q==");
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
	public void listNetrx() {
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("status");
			exp.setOperator("like");
			exp.setValue("active");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			NetRxListResponseDTO response = service.list(filter);
			for (NetRxDetail b : response.getNetRx()) {
				System.out.println(b.getName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

//	@Test
//	public void userlogin() {
//		NetRxService service = (NetRxService) applicationContext
//				.getBean("netRx.service");
//		try {
//			NetRxListResponseDTO response = service.
//		} catch (ServiceException e) {
//
//			System.out.println(e.isDisplayErrMsg());
//			System.out.println(e.getError());
//			System.out.println(e.getParamList());
//		}

	@Test
	public void listNetrxBranch() {
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("status");
			exp.setOperator("like");
			exp.setValue("active");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			NetRxBranchListResponseDTO response = service.getNetRxBranchList(filter);
			for (NetRxBranchDetail b : response.getNetRxBranch()) {
				System.out.println(b.getName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createNetRxUser(){
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		NetRxHeaderDTO header = new NetRxHeaderDTO();
		NetRxUserDetail user = new NetRxUserDetail();
		header.setPassPhrase("K6uHU/eRwUse47+WZhDvUg==");
		header.setMacId("222");
		header.setNetRxId(1);
		header.setNetRxBranchId(2);
		
	user.setFirstName("ricky");
	user.setEmail("ricky.john@netvarth.com");
	user.setMobile("7897897899");
	user.setPhone("01234567899");
	user.setStatus("active");
	user.setUserName("ricky");
	user.setUserType("staff");
	user.setPassword("netvarth");
	try{
		service.createUser(header, user);
	}catch (ServiceException e) {

		System.out.println(e.isDisplayErrMsg());
		System.out.println(e.getError());
		System.out.println(e.getParamList());
	}
	}
	@Test
	public void createBranch() {
		System.out.println("create branch");
		NetRxService service = (NetRxService) applicationContext
				.getBean("netRx.service");
		NetRxBranchDetail branch = new NetRxBranchDetail();
		branch.setNetRxId(1);
		branch.setName("Bheema");
		branch.setAddress("Thrissur");
		branch.setMobile("9974859685");
		branch.setEmail("luciya.jose@netvarth.com");
		branch.setNumberOfDevices(2);
		// branch.setPhone("0487-225632");
		try {
			service.createBranch(branch);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	/**
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
