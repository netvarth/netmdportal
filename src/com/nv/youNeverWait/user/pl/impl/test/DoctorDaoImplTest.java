package com.nv.youNeverWait.user.pl.impl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.api.sync.ReferralInfo;
import com.nv.youNeverWait.api.sync.ReferralSyncDTO;
import com.nv.youNeverWait.rs.dto.Address;
import com.nv.youNeverWait.user.bl.service.ActionEnum;
import com.nv.youNeverWait.user.pl.dao.DoctorDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class DoctorDaoImplTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void processReferral(){
	
		DoctorDao doctorDao= (DoctorDao) applicationContext.getBean("doctor.dao");
		Address address=new Address();
		address.setCity("Thrissur");
		address.setEmail("fair6291@gmail.com");
		address.setFax("");
		address.setMobile("");
		address.setPhone("04872351534");
		address.setPin("");
		address.setPrimaryAddress("");
		address.setSecondaryAddress("");
		address.setState("Kerala");
		ReferralInfo referralInfo=new ReferralInfo();
		referralInfo.setAddress(address);
		referralInfo.setName("Prasana Kumar");
		referralInfo.setUid("123");
		ReferralSyncDTO referralSyncDTO=new ReferralSyncDTO();
		referralSyncDTO.setReferralInfo(referralInfo);
		referralSyncDTO.setActionName(ActionEnum.CREATE);
		referralSyncDTO.setGlobalId(0);
		doctorDao.processReferral(referralSyncDTO);
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
}
