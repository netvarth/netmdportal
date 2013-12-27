/**
 * NetPosServiceTest.java
 * Jithinraj
 * Dec 11, 2013
 */
package com.nv.youNeverWait.user.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.user.bl.service.NetPosService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class NetPosServiceTest {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testCreate()
	{
		NetPosService service = (NetPosService) applicationContext
				.getBean("netPos.service");
		
		NetPosDTO posDto=new NetPosDTO();
		posDto.setName("jtihin");
		posDto.setHeadOfficeAddress("abc");
		posDto.setHeadOfficeEmail("jtihi.mechery@gmail.com");
		posDto.setHeadOfficeMobile("98745679");
		posDto.setHeadOfficeName("cdf");
		posDto.setHeadOfficePhone("2876545");
		posDto.setOwnerAddress("efg");
		posDto.setOwnerEmail("jtihin.mechery@gmail.com");
		posDto.setOwnerFirstName("sree");
		posDto.setOwnerLastName("jith");
		posDto.setOwnerMobile("93752314");
		posDto.setOwnerPhone("2345678");
		posDto.setPassword("xyz");
		posDto.setStatus("active");
		posDto.setUserName("abi");
		posDto.setUserType("admin");
	try{
		
		service.createNetPos(posDto);
	}catch(ServiceException e){
		System.out.println(e.isDisplayErrMsg());
		System.out.println(e.getError());
		System.out.println(e.getParamList());	
	}
	}
}
