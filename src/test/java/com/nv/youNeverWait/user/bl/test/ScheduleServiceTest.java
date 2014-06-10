/**
 * ScheduleServiceTest.java
 */
package com.nv.youNeverWait.user.bl.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.service.ScheduleService;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDTO;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.SeriesDTO;



/**
 * @author Luciya Jose
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })

public class ScheduleServiceTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void dayView() {
		ScheduleService scheduleService = (ScheduleService) applicationContext
				.getBean("schedule.service");
		try {
			scheduleService.dayView(5,1,"2013-03-26");
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}

	@Test
	public void create() {
		System.out.println("create Schedule");
		ScheduleService service = (ScheduleService) applicationContext.getBean("schedule.service");
		ScheduleDTO schedule = new ScheduleDTO();
		HeaderDTO header = new HeaderDTO();
		
		header.setMacId("00-80-48-6E-E1-E2");
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setBranchId(5);
		header.setHeadOfficeId(3);
		schedule.setHeader(header);
		ScheduleDetail scheduleDetail = new ScheduleDetail();
		scheduleDetail.setDoctorGlobalId(1);
		SeriesDTO series = new SeriesDTO();
		series.setOccuranceType("None");
		series.setRepeat("Daily");
		series.setEndDate("2015-10-11");
		series.setSeriesId(1);
		scheduleDetail.setSeries(series);
		scheduleDetail.setDoctorGlobalId(1);
		scheduleDetail.setScheduleStatus("Vacation");
		scheduleDetail.setEndTime("02:10 pm");
		scheduleDetail.setStartDate("2014-10-11");
		scheduleDetail.setStartTime("11:12 am");
		scheduleDetail.setScheduleStatus("Vacation");
		schedule.setScheduleDetail(scheduleDetail);
		
		try {
			service.create(schedule.getHeader(),schedule.getScheduleDetail());
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
	}

}
