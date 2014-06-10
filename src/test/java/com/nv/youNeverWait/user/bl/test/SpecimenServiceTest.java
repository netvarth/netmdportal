/**
 * SpecimenServiceTest.java
 * @author netvarth
 *
 * Version 1.0 Sep 5, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.youNeverWait.user.bl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.SpecimenDTO;
import com.nv.youNeverWait.user.bl.service.SpecimenService;

/**
 * 
 * 
 * @author Luciya Jose
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "file:resource/context.xml",
//		"file:resource/testDataSource.xml",
//		"file:resource/youNeverWait-context.xml" })
//public class SpecimenServiceTest {
//	@Autowired
//	private ApplicationContext applicationContext;
//
////	@Test
////	public void createSpecimenEmptyOrNullSpecimenName() {
////		SpecimenService service = (SpecimenService) applicationContext
////				.getBean("specimen.manager");
////		SpecimenDTO specimen = new SpecimenDTO();
////		specimen.setSpecimenName(null);
////		specimen.setUid(58);
////		specimen.setUnit("350 ml");
////		try {
////			service.createSpecimen(specimen);
////		} catch (ServiceException e) {
////			System.out.println(e.isDisplayErrMsg());
////			System.out.println(e.getError());
////			System.out.println(e.getParamList());
////		}
////	}
////	
////	@Test
////	public void createSpecimenSuccess() {
////		SpecimenService service = (SpecimenService) applicationContext
////				.getBean("specimen.manager");
////		SpecimenDTO specimen = new SpecimenDTO();
////		specimen.setSpecimenName("gladder");
////		specimen.setUnit("350 ml");
////		try {
////			service.createSpecimen(specimen);
////		} catch (ServiceException e) {
////			System.out.println(e.isDisplayErrMsg());
////			System.out.println(e.getError());
////			System.out.println(e.getParamList());
////		}
////	}
//
////	@Test
////	public void createSpecimenNameAlreadyExists() {
////		SpecimenService service = (SpecimenService) applicationContext
////				.getBean("specimen.manager");
////		SpecimenDTO specimen = new SpecimenDTO();
////		specimen.setSpecimenName("Vitamin D3");
////		specimen.setUid(58);
////		specimen.setUnit("350 ml");
////		try {
////			service.createSpecimen(specimen);
////		} catch (ServiceException e) {
////			System.out.println(e.isDisplayErrMsg());
////			System.out.println(e.getError());
////			System.out.println(e.getParamList());
////		}
////	}
////	@Test
////	public void updateSpecimenNameAlreadyExists() {
////		SpecimenService service = (SpecimenService) applicationContext
////				.getBean("specimen.manager");
////		SpecimenDTO specimen = new SpecimenDTO();
////		specimen.setSpecimenName("Vitamin D3");
////		specimen.setUid(58);
////		specimen.setUnit("350 ml");
////		try {
////			service.updateSpecimen(specimen);
////		} catch (ServiceException e) {
////			System.out.println(e.isDisplayErrMsg());
////			System.out.println(e.getError());
////			System.out.println(e.getParamList());
////		}
////	}
////	@Test
////	public void updateSpecimenSuccess() {
////		SpecimenService service = (SpecimenService) applicationContext
////				.getBean("specimen.manager");
////		SpecimenDTO specimen = new SpecimenDTO();
////		specimen.setSpecimenName("glader");
////		specimen.setUid(58);
////		specimen.setUnit("350 ml");
////		try {
////			service.updateSpecimen(specimen);
////		} catch (ServiceException e) {
////			System.out.println(e.isDisplayErrMsg());
////			System.out.println(e.getError());
////			System.out.println(e.getParamList());
////		}
////	}
////	@Test
////	public void updateSpecimenEmptyOrNullSpecimenName() {
////		SpecimenService service = (SpecimenService) applicationContext
////				.getBean("specimen.manager");
////		SpecimenDTO specimen = new SpecimenDTO();
////		specimen.setSpecimenName(null);
////		specimen.setUid(58);
////		specimen.setUnit("350 ml");
////		try {
////			service.updateSpecimen(specimen);
////		} catch (ServiceException e) {
////			System.out.println(e.isDisplayErrMsg());
////			System.out.println(e.getError());
////			System.out.println(e.getParamList());
////		}
////	}
//}
