/**
 * 
 */
package com.nv.portal.rs.netlims.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.rs.dto.OrderResultBundle;
import com.nv.youNeverWait.rs.dto.OrderResultSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderSyncDTO;
import com.nv.youNeverWait.rs.dto.OrderTestResultDTO;
import com.nv.youNeverWait.rs.dto.SyncResponse;
import com.nv.youNeverWait.user.bl.service.SyncService;

/**
 * @author Mani E.V
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
"file:resource/youNeverWait-context.xml" })
public class SyncResourceTest {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Synchronize order invalid order
	 * Mani E.V
	 */
	@Test
	public void processOrderResult_Invalid(){
		SyncService syncService = (SyncService) applicationContext.getBean("sync.service");
		OrderResultBundle bundle = new OrderResultBundle();
		bundle.setSource_branch_id(1);
		List<OrderResultSyncDTO> orderResults = new ArrayList<OrderResultSyncDTO>();
		OrderResultSyncDTO orderResult = new OrderResultSyncDTO();
		OrderSyncDTO order = new OrderSyncDTO();
		order.setAgentGlobalId(0);
		order.setOrderDate(new Date());
		order.setOrderHeader("");
		order.setOrderStatus("Fully Paid");
		order.setPatient(null);
		order.setUid("");
		orderResult.setOrder(order);
		orderResult.setGlobalId(1);
		List<OrderTestResultDTO> testResult=new ArrayList<OrderTestResultDTO>();
		orderResult.setTestResult(testResult);
		orderResults.add(orderResult);
		bundle.setOrderResults(orderResults);

		List<SyncResponse> response = syncService.processOrderResult(bundle);
		for(SyncResponse resp : response)
			assertEquals("500", resp.getStatusCode());
	}
	/**
	 * Synchronize order valid transfer
	 * Mani E.V
	 */
	@Test
	public void processOrderTest_Valid() {
		SyncService syncService = (SyncService) applicationContext.getBean("sync.service");
		OrderResultBundle bundle = new OrderResultBundle();
		bundle.setSource_branch_id(339);
		List<OrderResultSyncDTO> orderResults = new ArrayList<OrderResultSyncDTO>();
		OrderResultSyncDTO orderResult = new OrderResultSyncDTO();
		OrderSyncDTO order = new OrderSyncDTO();
		order.setAgentGlobalId(1);
		order.setOrderDate(new Date());
		order.setOrderHeader("");
		order.setOrderStatus("Fully Paid");
		order.setPatient(null);
		order.setUid("1");
		orderResult.setOrder(order);
		orderResult.setGlobalId(0);
		List<OrderTestResultDTO> testResult=new ArrayList<OrderTestResultDTO>();
		orderResult.setTestResult(testResult);
		orderResults.add(orderResult);
		bundle.setOrderResults(orderResults);

		List<SyncResponse> response = syncService.processOrderResult(bundle);
		for(SyncResponse resp : response)
			assertEquals("200", resp.getStatusCode());
	}
	
	
}
