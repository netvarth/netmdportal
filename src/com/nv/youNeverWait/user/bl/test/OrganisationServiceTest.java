/**
 * OrganisationServiceTest.java
 * January 10, 2013
 */
package com.nv.youNeverWait.user.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.user.bl.service.LabService;
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
}
