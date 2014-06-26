package com.nv.youNeverWait.user.pl.impl.test;

/**
 * 
 */

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.rs.dto.Address;
import com.nv.youNeverWait.rs.dto.OrderResultSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderSyncDTO;
import com.nv.youNeverWait.rs.dto.Patient;
import com.nv.youNeverWait.user.pl.dao.ResultDao;

/**
 * @author Mani E.V
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youNeverWait-context.xml"})
public class ResultDaoImplTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 
	 */
	@Test
	public void processOrderResult(){
		ResultDao resultDao = (ResultDao) applicationContext.getBean("result.dao");
		Patient patient = new Patient();
		patient.setName("Mani");
		Address address = new Address();
		address.setCity("Guruvayur");
		address.setEmail("maniev@rediffmail.com");
		patient.setAddress(address);
		OrderResultSyncDTO orderResult=new OrderResultSyncDTO();
		OrderSyncDTO order = new OrderSyncDTO();
		order.setPatient(patient);
		order.setUid("14JV1");
//		order.setOrderDate(new Date());
		order.setOrderStatus("ResultReady");
	//	orderResult.setOrder(order);
		orderResult.setGlobalId(0);
		Integer branchId=213;
		resultDao.processOrderResult(orderResult, branchId);
	}
}
