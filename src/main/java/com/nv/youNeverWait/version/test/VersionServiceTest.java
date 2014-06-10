/**
 * 
 */
package com.nv.youNeverWait.version.test;

import org.apache.commons.collections.set.CompositeSet.SetMutator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.VersionDetail;
import com.nv.youNeverWait.user.bl.service.SuperAdminService;
import com.nv.youNeverWait.version.bl.service.VersionService;

/**
 * @author Luciya
 * June 06 2014
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class VersionServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void checkUpdates1() {
		System.out.println("checkUpdates");
		VersionService service = (VersionService) applicationContext
				.getBean("version.service");
		VersionDetail details= new VersionDetail();
		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(17);
		header.setBranchId(20);
		header.setPassPhrase("V4WueKvId5iyYsvi8gtPYA==");
		header.setMacId("macId");
		details.setHeader(header);
		details.setAppName("NetMd");
		details.setSourceType("Desktop");
		details.setVersionNumber("1.0");
		try {
			
			service.checkUpdates(details);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void checkUpdates2() {
		System.out.println("checkUpdates");
		VersionService service = (VersionService) applicationContext
				.getBean("version.service");
		VersionDetail details= new VersionDetail();
		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(17);
		header.setBranchId(20);
		header.setPassPhrase("V4WueKvId5iyYsvi8gtPYA==");
		header.setMacId("macId");
		details.setHeader(header);
		details.setAppName("NetMd");
		details.setSourceType("Desktop");
		details.setVersionNumber("1.8");
		try {
			
			service.checkUpdates(details);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void checkUpdates3() {
		System.out.println("checkUpdates");
		VersionService service = (VersionService) applicationContext
				.getBean("version.service");
		VersionDetail details= new VersionDetail();
		HeaderDTO header = new HeaderDTO();
		header.setHeadOfficeId(17);
		header.setBranchId(20);
		header.setPassPhrase("V4WueKvId5iyYsvi8gtPYA==");
		header.setMacId("macId");
		details.setHeader(header);
		details.setAppName("NetMd");
		details.setSourceType("Desktop");
		details.setVersionNumber("1.6");
		try {
			
			service.checkUpdates(details);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
