/**
 * TestServiceTest.java
 * @author netvarth
 *
 * Version 1.0 Sep 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
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

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.AddTestDTO;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.rs.dto.TestSpecimenDTO;
import com.nv.youNeverWait.user.bl.service.SpecimenService;
import com.nv.youNeverWait.user.bl.service.TestService;

/**
 *
 *
 * @author Luciya Jose
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class TestServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void createTest() {
		TestService service = (TestService) applicationContext
				.getBean("test.manager");
		AddTestDTO testDTO = new AddTestDTO();
		testDTO.setName("Skin Test");
		
		try {
		
			service.createTest(testDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
	@Test
	public void createTestWithSpecimen() {
		TestService service = (TestService) applicationContext
				.getBean("test.manager");
		AddTestDTO testDTO = new AddTestDTO();
		List<TestSpecimenDTO> specimenList= new ArrayList<TestSpecimenDTO>();
		testDTO.setName("Radcliff Test");
		TestSpecimenDTO testSpecimen = new TestSpecimenDTO();
		testSpecimen.setSpecimenUid(57);
		testSpecimen.setSpecimenName("Drain");
		specimenList.add(testSpecimen);
		testDTO.setTestSpecimen(specimenList);
		
		
		try {
		
			service.createTest(testDTO);
		} catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	
}
