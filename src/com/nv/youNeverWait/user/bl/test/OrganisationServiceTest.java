/**
 * OrganisationServiceTest.java
 * January 10, 2013
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
import com.nv.youNeverWait.rs.dto.OrganisationUserDetail;
import com.nv.youNeverWait.user.bl.service.OrganisationService;

/**
 * @author Luciya Jose
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youNeverWait-context.xml"})

public class OrganisationServiceTest {
	@Autowired
	private ApplicationContext applicationContext;	
	
	
	@Test
	public void forgotPassword(){
		System.out.println("Forgot Password");
		OrganisationService service =(OrganisationService) applicationContext.getBean("organisation.service");
		LoginDTO login =new LoginDTO();
		login.setUserName("star");
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
	public void userList(){
		System.out.println("user list");
		OrganisationService service =(OrganisationService) applicationContext.getBean("organisation.service");
		FilterDTO filter= new FilterDTO();
		List<ExpressionDTO> exp= new ArrayList<ExpressionDTO>();
		ExpressionDTO exp1= new ExpressionDTO();
		exp1.setName("organisationId");
		exp1.setOperator("eq");
		exp1.setValue("7");
		exp.add(exp1);
		filter.setExp(exp);
		filter.setFrom(0);
		try{
			
			service.getUserList(filter);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void createUser(){
		System.out.println("create user");
		OrganisationService service =(OrganisationService) applicationContext.getBean("organisation.service");
		OrganisationUserDetail userDetail = new OrganisationUserDetail();
		userDetail.setAddress("chittal");
		userDetail.setEmail("ashly.pauly@netvarth.com");
		userDetail.setFirstName("ashly");
		userDetail.setLastName("pauly");
		userDetail.setMobile("9945623562");
		userDetail.setOrganisationId(7);
		userDetail.setPhone("04872565656");
		userDetail.setUserName("ashly");
		userDetail.setUserType("admin");
		userDetail.setPassword("netvarth");
		try{
			
		
			service.createUser(userDetail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void updateUser(){
		System.out.println("update user");
		OrganisationService service =(OrganisationService) applicationContext.getBean("organisation.service");
		OrganisationUserDetail userDetail = new OrganisationUserDetail();
		userDetail.setAddress("palayur");
		userDetail.setEmail("liyanto.jose@netvarth.com");
		userDetail.setFirstName("liya");
		userDetail.setLastName("jose");
		userDetail.setMobile("9945623562");
		userDetail.setOrganisationId(7);
		userDetail.setPhone("04872565656");
		userDetail.setUserType("owner");
		userDetail.setGlobalId(2);
		try{
			
		
			service.updateUser(userDetail);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void viewUser(){
		System.out.println("view user");
		OrganisationService service =(OrganisationService) applicationContext.getBean("organisation.service");
		try{
			
		
			service.viewUser(2);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void deleteUser(){
		System.out.println("delete user");
		OrganisationService service =(OrganisationService) applicationContext.getBean("organisation.service");
		
		try{
			
		
			service.deleteUser(2);
		}
		catch(ServiceException e){

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
