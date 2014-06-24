package com.nv.youNeverWait.user.pl.impl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.rs.dto.Address;
import com.nv.youNeverWait.rs.dto.FacilityInfo;
import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.user.bl.service.ActionEnum;
import com.nv.youNeverWait.user.pl.dao.FacilityDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })

public class FacilityDaoImplTest {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void processFacility(){
		
		FacilityDao facilityDao=(FacilityDao) applicationContext.getBean("facility.dao");
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
		FacilityInfo facilityInfo=new FacilityInfo();
		facilityInfo.setAddress(address);
		facilityInfo.setName("Krishna Labs");
		facilityInfo.setUid("678");
		FacilitySyncDTO facilitySyncDTO=new FacilitySyncDTO();
		facilitySyncDTO.setFacility(facilityInfo);
		facilitySyncDTO.setActionName(ActionEnum.UPDATE);
		facilitySyncDTO.setGlobalId(567);
		facilityDao.processFacility(facilitySyncDTO);
		
	}
	
}
