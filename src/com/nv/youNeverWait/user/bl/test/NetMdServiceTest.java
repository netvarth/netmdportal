/**
 * NetMdServiceTest.java
 */
package com.nv.youNeverWait.user.bl.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.BranchBillListDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDetail;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdDetail;
import com.nv.youNeverWait.rs.dto.NetMdListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdResponseDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.SystemHealthDetails;
import com.nv.youNeverWait.user.bl.service.NetMdService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class NetMdServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void clearMac(){
		System.out.println("Clear Mac");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		HeaderDTO header= new HeaderDTO();
		header.setHeadOfficeId(17);
		header.setBranchId(20);
		header.setPassPhrase("V4WueKvId5iyYsvi8gtPYA==");
		try{
			service.clearMacId(header);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void clearMacWrong(){
		System.out.println("Clear Mac");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		HeaderDTO header= new HeaderDTO();
		header.setHeadOfficeId(17);
		header.setBranchId(20);
		header.setPassPhrase("V4WueKvId5iyYsvi8gtPYA==jjjjjj");
		try{
			service.clearMacId(header);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void createuserAlreadyExists() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		NetMdUserDTO userCreationDTO = new NetMdUserDTO();
		NetMdUserDetail user = new NetMdUserDetail();
		user.setFirstName("Vbin");
		user.setLastName("P v");
		user.setUserType("admin");
		user.setPhone("0487-2342822");
		user.setMobile("1234567890");
		user.setAddress("kodannur");
		user.setEmail("misha.mohanan@netvarth.com");
		user.setUserName("vbnpv");
		user.setPassword("netvarth");
		user.setUserType("admin");
		user.setNetMdId(10);
		user.setNetMdBranchId(11);
		userCreationDTO.setUser(user);
		HeaderDTO header = new HeaderDTO();
		header.setBranchId(17);
		header.setHeadOfficeId(25);
		header.setMacId("5646opiopiop");
		header.setPassPhrase("");
		userCreationDTO.setHeader(header);
		try {
			service.createUser(userCreationDTO.getHeader(),userCreationDTO.getUser());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createuser() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		NetMdUserDTO userCreationDTO = new NetMdUserDTO();
		NetMdUserDetail user = new NetMdUserDetail();
		user.setFirstName("Vyshu");
		user.setLastName("P v");
		user.setUserType("admin");
		user.setPhone("0487-2342822");
		user.setMobile("1234567890");
		user.setAddress("kodannur");
		user.setEmail("misha.mohanan@netvarth.com");
		user.setUserName("vb");
		user.setPassword("netvarth");
		user.setUserType("admin");
		user.setNetMdId(89);
		user.setNetMdBranchId(86);
		userCreationDTO.setUser(user);
		HeaderDTO header = new HeaderDTO();
		header.setBranchId(86);
		header.setHeadOfficeId(89);
		header.setMacId("123");
		header.setPassPhrase("Dzpl8VvTlrHGzjdSCfTqMw==");
		userCreationDTO.setHeader(header);
		try {
			service.createUser(userCreationDTO.getHeader(),userCreationDTO.getUser());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void createBranchAlreadyExists() {
		System.out.println("create branch");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		NetMdBranchDTO branch = new NetMdBranchDTO();
		branch.setNetMdId(89);
		branch.setName("Bheemaa");
		branch.setAddress("Thrissur");
		branch.setMobile("9974859685");
		branch.setEmail("asha.chandran@netvarth.com");
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

	@Test
	public void createBranchSuccess() {
		System.out.println("create branch");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		NetMdBranchDTO branch = new NetMdBranchDTO();
		branch.setNetMdId(89);
		branch.setName("veeru");
		branch.setAddress("Thrissur");
		branch.setMobile("9974859685");
		branch.setEmail("asha.chandran@netvarth.com");
		branch.setNumberOfDevices(3);
		// branch.setPhone("0487-225632");
		try {
			service.createBranch(branch);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateBranchWrong() {
		System.out.println("update branch");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		NetMdBranchDTO branch = new NetMdBranchDTO();
		branch.setName("Jyothi clinic");
		branch.setAddress("Koorkenchery");
		branch.setMobile("9974859685");
		branch.setPhone("9974859685");
		branch.setNumberOfDevices(2);
		branch.setGlobalId(17);
		branch.setNetMdId(25);
		try {
			service.updateBranch(branch);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void updateBranchSuccess() {
		System.out.println("update branch");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		NetMdBranchDTO branch = new NetMdBranchDTO();
		branch.setName("Aruna");
		branch.setAddress("Koorkenchery");
		branch.setMobile("9974859685");
		branch.setPhone("9974859685");
		branch.setNumberOfDevices(2);
		branch.setGlobalId(86);
		branch.setNetMdId(89);
		try {
			service.updateBranch(branch);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void deleteBranchWrong() {
		System.out.println("delete Branch");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		try {
			service.deleteBranch(1113);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void deleteBranchSuccess() {
		System.out.println("delete Branch");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		try {
			service.deleteBranch(86);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void getMacStatus() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		try {
			NetMdResponseDTO response = service.getMacStatus("123");
			System.out.println(response.isExistMac());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void activatenetMd() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		HeaderDTO header = new HeaderDTO();
		header.setBranchId(5);
		header.setHeadOfficeId(3);
		header.setMacId("00-80-48-6E-E1-E2");
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		try {
			service.activateNetMd(header);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listNetMdBranch() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("userId");
			exp.setOperator("eq");
			exp.setValue("1");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			NetMdBranchListResponseDTO response = service.getBranchList(filter);
			for (NetMdBranchDetail b : response.getNetmdBranch()) {
				System.out.println(b.getName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void listNetMd() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		try {
			FilterDTO filter = new FilterDTO();
			ExpressionDTO exp = new ExpressionDTO();
			exp.setName("branchName");
			exp.setOperator("eq");
			exp.setValue("Bheema");
			List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
			exps.add(exp);
			filter.setExp(exps);
			filter.setCount(10);
			filter.setFrom(0);
			filter.setAsc(true);
			NetMdListResponseDTO response = service.getNetMdList(filter);
			for (NetMdDetail b : response.getNetMd()) {
				System.out.println(b.getName());
			}
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	/**
	 * Method to get mac id of the system
	 * 
	 * @return
	 */
	private String getMac() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i],
						(i < mac.length - 1) ? "-" : ""));
			}
			return sb.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "";
	}

	@Test
	public void viewNetMd() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");

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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");

		try {
			service.viewBranch(86);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void deletebranch() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");

		try {
			service.deleteBranch(5);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void deleteNetMd() {
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");

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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		NetMdUserDTO userCreationDTO = new NetMdUserDTO();
		NetMdUserDetail user = new NetMdUserDetail();
		user.setFirstName("Misha");
		user.setLastName("Mohanan");
		user.setUserType("staff");
		user.setPhone("0487-2342822");
		user.setMobile("1234567890");
		user.setAddress("kodannur");
		user.setEmail("misha.mohanan@netvarth.com");
		user.setUserName("mishamohan");
		user.setPassword("netvarth");
		user.setGlobalId(1);
		userCreationDTO.setUser(user);
		HeaderDTO header = new HeaderDTO();
		header.setBranchId(17);
		header.setHeadOfficeId(25);
		header.setMacId("");
		header.setPassPhrase("");
		userCreationDTO.setHeader(header);
		try {
			service.updateUser(userCreationDTO.getHeader(),userCreationDTO.getUser());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void forgotPassword(){
		System.out.println("Forgot Password");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("netMd1");
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
	public void deleteUser(){
		System.out.println("deleteUser");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		
		try{
			service.deleteUser(7);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewUser(){
		System.out.println("viewUser");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		
		try{
			NetMdUserDTO user = service.viewUser(7);
			System.out.println(user);
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword(null);
		passwords.setNewPassword(null);
		passwords.setUsername("mohini");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword(null);
		passwords.setUsername("mohini");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword(null);
		passwords.setNewPassword("mohini");
		passwords.setUsername("mohini");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("");
		passwords.setNewPassword("sdfsf");
		passwords.setUsername("mohini");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword("");
		passwords.setUsername("mohini");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("");
		passwords.setNewPassword("");
		passwords.setUsername("mohini");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarthkkkk");
		passwords.setNewPassword("netvarth");
		passwords.setUsername("mohini");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
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
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		PasswordDTO passwords=new PasswordDTO();
		passwords.setOldPassword("netvarth");
		passwords.setNewPassword("mohini");
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
		passwords.setOldPassword("mohini");
		passwords.setNewPassword("netvarth");
		passwords.setUsername("mohini");
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
	public void checkSystemHealth(){
		System.out.println("checkSystemHealth");
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		SystemHealthDetails systemHealthDetails = new SystemHealthDetails();
		systemHealthDetails.setAppType("netmd");
		systemHealthDetails.setCpuUsage("50");
		systemHealthDetails.setHardDiskUsed("50");
		systemHealthDetails.setMemoryUsed("50");
		systemHealthDetails.setTotalCpuSpace("100");
		systemHealthDetails.setTotalHardDiskSpace("100");
		systemHealthDetails.setTotalMemorySpace("100");
		
		HeaderDTO netmdHeader = new HeaderDTO();
		netmdHeader.setMacId("00-80-48-6E-E1-E2");
		netmdHeader.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		netmdHeader.setHeadOfficeId(3);
		netmdHeader.setBranchId(5);
		
		systemHealthDetails.setHeader(netmdHeader);
		try{
			
			service.checkSystemHealth(systemHealthDetails);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewBillSuccess(){
		NetMdService service = (NetMdService) applicationContext
				.getBean("netMd.service");
		BranchBillListDTO listDTO= new BranchBillListDTO();
		listDTO.setFromDate("2013-10-01");
		listDTO.setToDate("2013-10-10");
		listDTO.setNetmdId(3);
		listDTO.setNetmdBranchId(5); 
		
		try{
			service.billList(listDTO);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
}
