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
import com.nv.youNeverWait.pl.entity.PatientAppointmentTbl;
import com.nv.youNeverWait.rs.dto.Appointment;
import com.nv.youNeverWait.rs.dto.AppointmentDetailsDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.PastAppointmentListResponseDTO;
import com.nv.youNeverWait.rs.dto.PatientDTO;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.ResultListResponseDTO;
import com.nv.youNeverWait.rs.dto.ViewScheduleListDTO;
import com.nv.youNeverWait.user.bl.service.DoctorService;
import com.nv.youNeverWait.user.bl.service.PatientService;
import com.nv.youNeverWait.user.bl.service.ResultService;

/**
 * @author Luciya Jose
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)


@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youNeverWait-context.xml"})
public class ResultServiceTest {
	@Autowired
	private ApplicationContext applicationContext;	
	@Test
	public void resultlist(){
		ResultService service =(ResultService) applicationContext.getBean("result.service");
		ResultListResponseDTO response = new ResultListResponseDTO();
		try{
			response = service.listResult("5");
			
		}
		catch(ServiceException e){
			
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
}
