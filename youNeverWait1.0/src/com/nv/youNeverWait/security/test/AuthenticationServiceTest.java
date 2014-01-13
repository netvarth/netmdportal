package com.nv.youNeverWait.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationResponseDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.security.bl.service.AuthenticationService;

@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youNeverWait-context.xml"})

public class AuthenticationServiceTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void netlimsLogin(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		LoginDTO login = new LoginDTO();
		login.setPassword("net1");
		login.setUserName("net1");
		try{
		 service.netlimsLogin(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
		
	}
	@Test
	public void getnetlimsUser(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		try{
		service.getNetlimsUser("meena");
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void netmdLogin(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		LoginDTO login = new LoginDTO();
		login.setPassword("netvarth");
		login.setUserName("assavbn");
		LoginResponseDTO  response = service.netmdLogin(login);
		System.out.println("LoginResponseDTO "+response.isSuccess());
	}
	@Test
	public void captcha(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		service.getCaptcha();
	}
	@Test
	public void verifyCaptcha(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		CaptchaVerificationDTO captcha = new  CaptchaVerificationDTO();
		captcha.setSecretCode("jepKD2CA2lZ5Kv6fkaiLLg==");
		captcha.setVerificationCode("1w7obds");
		CaptchaVerificationResponseDTO  response = service.verifyCaptcha(captcha );
		System.out.println("response "+response.isValid());
		
	}
	@Test
	public void patientLogin(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		LoginDTO login = new LoginDTO();
		login.setPassword("netvarth");
		login.setUserName("asha@gmail.com");
		try{
		LoginResponseDTO  response = service.patientLogin(login);
		System.out.println("LoginResponseDTO "+response.isSuccess());
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void userDetail(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		
		try{
			service.getPatient("asha");
		}
		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void OrganisationLogin(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		LoginDTO login = new LoginDTO();
		login.setPassword("ds");
		login.setUserName("star");
		try{
		 service.organisationLogin(login);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
		
	}
	@Test
	public void getOrganisationUser(){	
		AuthenticationService service = (AuthenticationService) applicationContext.getBean("authentication.service");
		
		try{
		 service.getOrganisationUser("rt");
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
		
	}
}