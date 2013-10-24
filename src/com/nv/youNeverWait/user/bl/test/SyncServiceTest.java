/**
 * SyncServiceTest.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
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
import com.nv.youNeverWait.rs.dto.AppointmentDetailsDTO;
import com.nv.youNeverWait.rs.dto.BillSummaryDTO;
import com.nv.youNeverWait.rs.dto.DoctorDetail;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.LabHeaderDTO;
import com.nv.youNeverWait.rs.dto.LabSyncDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetMdUserDetail;
import com.nv.youNeverWait.rs.dto.PatientDetail;
import com.nv.youNeverWait.rs.dto.ScheduleDetail;
import com.nv.youNeverWait.rs.dto.SeriesDTO;
import com.nv.youNeverWait.rs.dto.SyncDTO;
import com.nv.youNeverWait.user.bl.service.SyncService;

/**
 * @author Luciya Jose
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })
public class SyncServiceTest {

		
@Autowired
private ApplicationContext applicationContext;	
		
		@Test
		public void syncappointment(){
			SyncService service =(SyncService) applicationContext.getBean("sync.service");
			SyncDTO sync=new SyncDTO();
			AppointmentDetailsDTO details = new AppointmentDetailsDTO();
			List<AppointmentDetailsDTO> appointmentDetailsList = new ArrayList<AppointmentDetailsDTO>();
			HeaderDTO header = new HeaderDTO();
			header.setNetMdId(3);
			header.setNetMdBranchId(5);
			header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
			header.setMacId("00-19-DB-E0-7C-E1");
			details.setScheduleId(1);
			details.setDoctorId(1);
			//details.setId(79);
			details.setGlobalId(78);
			details.setPatientId(5);
			details.setPatientName("asha");
			details.setStartDate("2013-04-20");
			details.setStartTime("10:00 am");
			
			appointmentDetailsList.add(details);
			sync.setDeletedAppointmentList(appointmentDetailsList);
			sync.setHeader(header);
			sync.setLastSyncTime("2012-10-10 00:00:00");
			try {
				service.syncData(sync);
			} catch (ServiceException e) {

				System.out.println(e.isDisplayErrMsg());
				System.out.println(e.getError());
				System.out.println(e.getParamList());
			}
		}
		
		
		
		@Test
		public void syncPatientData(){	 
			SyncService service =(SyncService) applicationContext.getBean("sync.service");
			SyncDTO sync=new SyncDTO();
			sync.setLastSyncTime("2012-10-10 21:12:12");
			HeaderDTO header = new HeaderDTO();
			header.setNetMdId(3);
			header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
			header.setMacId("00-19-DB-E0-7C-E1");
			header.setNetMdBranchId(5);
			sync.setHeader(header);
			LoginDTO login = new LoginDTO();
			login.setUserType("patient");
			PatientDetail detail = new PatientDetail();
			List<PatientDetail> patientList = new ArrayList<PatientDetail>();
			//detail.setGlobalId(46);
			detail.setBranchId(5);
			detail.setFirstName("rix");
			detail.setLastName("joyson");
			detail.setPhone("012 2424244");
			detail.setAddress("Vadakkan");
			detail.setGender("Male");
			detail.setAge(20);
			detail.setEmail("rickyjohn007@gmail.com");
			detail.setMobile("1234567890");
			detail.setLogin(login);
			patientList.add(detail);
			sync.setNewPatientList(patientList);
			try{
				service.syncData(sync);
			}
			catch(ServiceException e){
				
				System.out.println(e.isDisplayErrMsg());
				System.out.println(e.getError());
				System.out.println(e.getParamList());
			}
		}

		@Test
		public void syncData(){	
			
			System.out.println("########## Sync Calling... ################ ");
			SyncService service =(SyncService) applicationContext.getBean("sync.service");
			SyncDTO sync=new SyncDTO();
			sync.setLastSyncTime("2012-10-10 21:12:12");
			HeaderDTO header = new HeaderDTO();
			header.setNetMdId(3);
			header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
			header.setMacId("00-19-DB-E0-7C-E1");
			header.setNetMdBranchId(5);
			header.setNetMdId(3);
			sync.setHeader(header);
		
			
			
			List<DoctorDetail> doctor = new ArrayList<DoctorDetail>();
			DoctorDetail doctorDetail1= new DoctorDetail();
			doctorDetail1.setAddress("vadakkan");
			doctorDetail1.setEmail("rakhi.vasudevan@netvarth.com");
			doctorDetail1.setFirstName("ash");
			doctorDetail1.setLastName("pauly");
			doctorDetail1.setDateOfBirth("1989-10-10");
			doctorDetail1.setConsultationInterval("15");
			
			/*LoginDTO login1= new LoginDTO();
			login1.setPassword("netvarth");
			login1.setUserName("ash");
			login1.setUserType("doctor");
			doctorDetail1.setLogin(login1);*/
			doctor.add(doctorDetail1);

			/*DoctorDetail doctorDetail2= new DoctorDetail();
			doctorDetail2.setAddress("Thekkkan");
			doctorDetail2.setEmail("nithesh.mohanan@netvarth.com");
			doctorDetail2.setFirstName("nithesh");
			doctorDetail2.setLastName("mohanan");
			doctorDetail2.setPhone("0487 2535353");
			doctorDetail2.setDateOfBirth("2000-10-10");
			doctorDetail2.setConsultationInterval("14");
			List<DoctorExpertiseDTO> doctorExpertisee2= new ArrayList<DoctorExpertiseDTO>();
			DoctorExpertiseDTO docExpt = new DoctorExpertiseDTO();
			docExpt.setExpertise("m phil");
			doctorExpertisee2.add(docExpt);
			doctorDetail2.setExpertise(doctorExpertisee2);
			
			LoginDTO login2= new LoginDTO();
			login2.setPassword("netvarth");
			login2.setUserName("sreeram");
			login2.setUserType("doctor");
			doctorDetail2.setLogin(login2);
			doctor.add(doctorDetail2);
		
			DoctorDetail doctorDetail3= new DoctorDetail();
			doctorDetail3.setAddress("Mgfsdgfsd");
			doctorDetail3.setEmail("ranjith.sankar@netvarth.com");
			doctorDetail3.setFirstName("ranjith");
			doctorDetail3.setLastName("sankar");
			doctorDetail3.setPhone("0487-2535353");
			doctorDetail3.setDateOfBirth("2012-10-10");
			doctorDetail3.setConsultationInterval("15");
			
			LoginDTO login3= new LoginDTO();
			login3.setPassword("netvarth");
			login3.setUserName("ranjith");
			login3.setUserType("doctor");
			doctorDetail3.setLogin(login3);
			doctor.add(doctorDetail3);*/
			sync.setNewDoctorList(doctor);
			/*Update list
			List<DoctorDetail> upDoctor = new ArrayList<DoctorDetail>();
			DoctorDetail doctorDetail4= new DoctorDetail();
			doctorDetail4.setGlobalId(1);
			doctorDetail4.setEmail("ashly.pauly@netvarth.com");
			doctorDetail4.setId(1);
			List<DoctorExperienceDTO> doctorExperience4= new ArrayList<DoctorExperienceDTO>();
			DoctorExperienceDTO docExp4 = new DoctorExperienceDTO();
			docExp4.setDesignation("Senior Nurse");
			docExp4.setToDate("2012-10-10");
			doctorExperience4.add(docExp4);
			doctorDetail4.setDoctorExperience(doctorExperience4);
			upDoctor.add(doctorDetail4);
			
			
			DoctorDetail doctorDetail5= new DoctorDetail();
			doctorDetail5.setGlobalId(2);
			doctorDetail5.setId(1);
			doctorDetail5.setFirstName("leonora");
			doctorDetail5.setEmail("leonora.louis@netvarth.com");
			List<DoctorExperienceDTO> doctorExperience5= new ArrayList<DoctorExperienceDTO>();
			DoctorExperienceDTO docExp5 = new DoctorExperienceDTO();
			docExp5.setDesignation("head Nurse");
			docExp5.setToDate("2012-10-10");
			doctorExperience5.add(docExp5);
 		  doctorDetail5.setDoctorExperience(doctorExperience5);
			
			List<DoctorAchievementDTO> doctorAcheivement5= new ArrayList<DoctorAchievementDTO>();
			DoctorAchievementDTO docAch5 = new DoctorAchievementDTO();
			docAch5.setAchievement("Managing Director of Amala Hospital");
			doctorAcheivement5.add(docAch5);
			
			DoctorAchievementDTO docAch6 = new DoctorAchievementDTO();
			docAch6.setAchievement("Managing Director of Apollo Hospital");
			doctorAcheivement5.add(docAch6);
			doctorDetail5.setAchievement(doctorAcheivement5);
			upDoctor.add(doctorDetail5);
			sync.setUpdateDoctorList(upDoctor);
			Deletion
			List<DoctorDetail> delDoctor = new ArrayList<DoctorDetail>();
			DoctorDetail doctorDetail6= new DoctorDetail();
			doctorDetail6.setGlobalId(3);
			doctorDetail6.setId(3);
			delDoctor.add(doctorDetail6);
			
			DoctorDetail doctorDetail7= new DoctorDetail();
			doctorDetail7.setGlobalId(2);
			doctorDetail7.setId(2);
			delDoctor.add(doctorDetail7);
			sync.setDeleteDoctorList(delDoctor);*/
			try{
				service.syncData(sync);
			}
			catch(ServiceException e){
				
				System.out.println(e.isDisplayErrMsg());
				System.out.println(e.getError());
				System.out.println(e.getParamList());
			}

		}
	@Test
	public void syncDoctorData() {

		System.out
				.println("########## Sync Doctor Calling... ################ ");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		SyncDTO sync = new SyncDTO();
		sync.setLastSyncTime("2013-06-03 09:08:21");
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(76);
		header.setPassPhrase("bab5TqPDNCbFCy7LWRffyg==");
		header.setMacId("20-CF-30-D9-9C-04");
		header.setNetMdBranchId(72);
		sync.setHeader(header);

//		/* New doctors list */
//		List<DoctorDetail> doctor = new ArrayList<DoctorDetail>();
//		DoctorDetail doctorDetail1 = new DoctorDetail();
//		doctorDetail1.setAddress("vadakkan");
//		doctorDetail1.setEmail("luciya.jose@netvarth.com");
//		doctorDetail1.setFirstName("Dr.Radha");
//		doctorDetail1.setLastName("Krishnanan");
//		doctorDetail1.setDateOfBirth("1989-10-10");
//		doctorDetail1.setConsultationInterval("15");
//		doctor.add(doctorDetail1);

//		DoctorDetail doctorDetail2 = new DoctorDetail();
//		doctorDetail2.setAddress("Thekkkan");
//		doctorDetail2.setEmail("nithesh.mohanan@netvarth.com");
//		doctorDetail2.setFirstName("nithesh");
//		doctorDetail2.setLastName("mohanan");
//		doctorDetail2.setPhone("0487 2535353");
//		doctorDetail2.setDateOfBirth("2000-10-10");
//		doctorDetail2.setConsultationInterval("14");
//		List<DoctorExpertiseDTO> doctorExpertisee2 = new ArrayList<DoctorExpertiseDTO>();
//		DoctorExpertiseDTO docExpt = new DoctorExpertiseDTO();
//		docExpt.setExpertise("m phil");
//		doctorExpertisee2.add(docExpt);
//		doctorDetail2.setExpertise(doctorExpertisee2);
//		doctor.add(doctorDetail2);
//
//		DoctorDetail doctorDetail3 = new DoctorDetail();
//		doctorDetail3.setAddress("Mgfsdgfsd");
//		doctorDetail3.setEmail("ranjith.sankar@netvarth.com");
//		doctorDetail3.setFirstName("ranjith");
//		doctorDetail3.setLastName("sankar");
//		doctorDetail3.setPhone("0487-2535353");
//		doctorDetail3.setDateOfBirth("2012-10-10");
//		doctorDetail3.setConsultationInterval("15");
//		doctor.add(doctorDetail3);

		//sync.setNewDoctorList(doctor);

//		/* Doctor Update List */
//		List<DoctorDetail> upDoctor = new ArrayList<DoctorDetail>();
//		DoctorDetail doctorDetail4 = new DoctorDetail();
//		doctorDetail4.setGlobalId(1);
//		doctorDetail4.setEmail("ashly.pauly@netvarth.com");
//		doctorDetail4.setId(1);
//		List<DoctorExperienceDTO> doctorExperience4 = new ArrayList<DoctorExperienceDTO>();
//		DoctorExperienceDTO docExp4 = new DoctorExperienceDTO();
//		docExp4.setDesignation("Senior Nurse");
//		docExp4.setToDate("2012-10-10");
//		doctorExperience4.add(docExp4);
//		doctorDetail4.setDoctorExperience(doctorExperience4);
//		upDoctor.add(doctorDetail4);
//
//		DoctorDetail doctorDetail5 = new DoctorDetail();
//		doctorDetail5.setGlobalId(2);
//		doctorDetail5.setId(1);
//		doctorDetail5.setFirstName("leonora");
//		doctorDetail5.setEmail("leonora.louis@netvarth.com");
//		List<DoctorExperienceDTO> doctorExperience5 = new ArrayList<DoctorExperienceDTO>();
//		DoctorExperienceDTO docExp5 = new DoctorExperienceDTO();
//		docExp5.setDesignation("head Nurse");
//		docExp5.setToDate("2012-10-10");
//		doctorExperience5.add(docExp5);
//		doctorDetail5.setDoctorExperience(doctorExperience5);
//
//		List<DoctorAchievementDTO> doctorAcheivement5 = new ArrayList<DoctorAchievementDTO>();
//		DoctorAchievementDTO docAch5 = new DoctorAchievementDTO();
//		docAch5.setAchievement("Managing Director of Amala Hospital");
//		doctorAcheivement5.add(docAch5);
//
//		DoctorAchievementDTO docAch6 = new DoctorAchievementDTO();
//		docAch6.setAchievement("Managing Director of Apollo Hospital");
//		doctorAcheivement5.add(docAch6);
//		doctorDetail5.setAchievement(doctorAcheivement5);
//		upDoctor.add(doctorDetail5);
//		sync.setUpdateDoctorList(upDoctor);
//
//		/* Doctor Deletion List */
//		List<DoctorDetail> delDoctor = new ArrayList<DoctorDetail>();
//		DoctorDetail doctorDetail6 = new DoctorDetail();
//		doctorDetail6.setGlobalId(3);
//		doctorDetail6.setId(3);
//		delDoctor.add(doctorDetail6);
//
//		DoctorDetail doctorDetail7 = new DoctorDetail();
//		doctorDetail7.setGlobalId(2);
//		doctorDetail7.setId(2);
//		delDoctor.add(doctorDetail7);
//		sync.setDeleteDoctorList(delDoctor);

		try {
			service.syncData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());

		}
	}

	@Test
	public void syncPatient() {
		System.out
				.println("########## Sync Patient Calling... ################ ");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		SyncDTO sync = new SyncDTO();
		sync.setLastSyncTime("2012-10-10 21:12:12");
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-19-DB-E0-7C-E1");
		header.setNetMdBranchId(5);
		header.setNetMdId(3);
		sync.setHeader(header);

		LoginDTO login = new LoginDTO();
		login.setUserName("ricky.john@netvath.com");
		login.setUserType("patient");
		PatientDetail detail = new PatientDetail();
		List<PatientDetail> patientList = new ArrayList<PatientDetail>();
		detail.setBranchId(5);
		detail.setFirstName("ricky");
		detail.setLastName("joyson");
		detail.setPhone("012 2424244");
		detail.setAddress("Vadakkan");
		detail.setGender("Female");
		detail.setAge(20);
		detail.setEmail("ricky.john@netvath.com");
		detail.setMobile("1234567890");
		detail.setLogin(login);
		patientList.add(detail);
		sync.setNewPatientList(patientList);
		try {
			service.syncData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void syncScheduleData() {
		System.out
				.println("########## Sync Schedule Calling... ################ ");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		SyncDTO sync = new SyncDTO();
		sync.setLastSyncTime("2012-10-10 09:12:12");
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-19-DB-E0-7C-E1");
		header.setNetMdBranchId(5);
		sync.setHeader(header);

		/* new schedule */
		List<ScheduleDetail> newScheduleList = new ArrayList<ScheduleDetail>();

		ScheduleDetail newSchedule1 = new ScheduleDetail();
		SeriesDTO series1 = new SeriesDTO();
		series1.setSeriesId(31);
		series1.setOccuranceType("None");
		series1.setRepeat("Daily");
		series1.setEndDate("2012-10-11");
		newSchedule1.setSeries(series1);

		newSchedule1.setDoctorGlobalId(25);
		newSchedule1.setScheduleStatus("Working Hours");
		newSchedule1.setEndTime("11:12 am");
		newSchedule1.setStartDate("2014-04-20");
		newSchedule1.setStartTime("07:00 am");
		newScheduleList.add(newSchedule1);

		ScheduleDetail newSchedule2 = new ScheduleDetail();
		SeriesDTO series2 = new SeriesDTO();
		series2.setOccuranceType("2");
		series2.setSeriesId(12);
		series2.setRepeat("Daily");
		series2.setEndDate("2014-12-11");
		newSchedule2.setSeries(series2);

		newSchedule2.setDoctorGlobalId(1);
		newSchedule2.setScheduleStatus("Vacation");
		newSchedule2.setEndTime("03:10 pm");
		newSchedule2.setStartDate("2013-10-11");
		newSchedule2.setStartTime("01:12 am");
		newSchedule2.setScheduleStatus("Vacation");
		newScheduleList.add(newSchedule2);

		ScheduleDetail newSchedule3 = new ScheduleDetail();
		SeriesDTO series3 = new SeriesDTO();
		series3.setOccuranceType("None");
		series3.setRepeat("Daily");
		series3.setEndDate("2014-10-11");
		newSchedule3.setSeries(series3);

		newSchedule3.setDoctorGlobalId(1);
		newSchedule3.setScheduleStatus("Vacation");
		newSchedule3.setEndTime("11:12 am");
		newSchedule3.setStartDate("2013-10-11");
		newSchedule3.setStartTime("02:10 pm");
		newSchedule3.setScheduleStatus("Vacation");
		newScheduleList.add(newSchedule3);

		sync.setNewScheduleList(newScheduleList);

		/* delete schedule */
		List<ScheduleDetail> deleteScheduleList = new ArrayList<ScheduleDetail>();
		ScheduleDetail delSchedule1 = new ScheduleDetail();
		delSchedule1.setScheduleGlobalId(0);
		delSchedule1.setId(1);
		ScheduleDetail delSchedule2 = new ScheduleDetail();
		delSchedule2.setScheduleGlobalId(15);
		delSchedule2.setId(2);
		ScheduleDetail delSchedule3 = new ScheduleDetail();
		delSchedule3.setScheduleGlobalId(13);
		ScheduleDetail delSchedule4 = new ScheduleDetail();
		delSchedule4.setScheduleGlobalId(12);
		deleteScheduleList.add(delSchedule1);
		deleteScheduleList.add(delSchedule2);
		deleteScheduleList.add(delSchedule3);
		deleteScheduleList.add(delSchedule4);
		sync.setDeleteScheduleList(deleteScheduleList);

		/* update schedule */
		List<ScheduleDetail> updateScheduleList = new ArrayList<ScheduleDetail>();

		ScheduleDetail updateSchedule1 = new ScheduleDetail();
		SeriesDTO useries1 = new SeriesDTO();
		useries1.setOccuranceType("Ends Date");
		useries1.setRepeat("Daily");
		useries1.setEndDate("2014-10-11");
		useries1.setSeriesId(139);
		updateSchedule1.setSeries(useries1);

		updateSchedule1.setDoctorGlobalId(12);
		updateSchedule1.setScheduleStatus("Vacation");
		updateSchedule1.setEndTime("12:30 pm");
		updateSchedule1.setStartDate("2013-10-11");
		updateSchedule1.setStartTime("11:12 am");
		updateSchedule1.setScheduleGlobalId(23);
		updateScheduleList.add(updateSchedule1);

		ScheduleDetail updateSchedule2 = new ScheduleDetail();
		SeriesDTO upseries2 = new SeriesDTO();
		upseries2.setOccuranceType("Endsdate");
		upseries2.setRepeat("Daily");
		upseries2.setEndDate("2014-10-11");
		upseries2.setEndsOn(123);
		upseries2.setSeriesId(139);
		updateSchedule2.setSeries(upseries2);

		updateSchedule2.setDoctorGlobalId(1);
		updateSchedule2.setEndTime("04:10 am");
		updateSchedule2.setStartDate("2013-10-11");
		updateSchedule2.setStartTime("03:12 am");
		updateSchedule2.setScheduleStatus("Vacation");
		updateSchedule2.setScheduleGlobalId(13);
		updateScheduleList.add(updateSchedule2);

		sync.setUpdateScheduleList(updateScheduleList);

		try {
			service.syncData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void syncUserData() {
		System.out.println("########## Sync User Calling... ################ ");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		SyncDTO sync = new SyncDTO();
		sync.setLastSyncTime("2012-10-10 09:12:12:12");
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-19-DB-E0-7C-E1");
		header.setNetMdBranchId(5);
		sync.setHeader(header);

		/* new users list */
		List<NetMdUserDetail> newUserList = new ArrayList<NetMdUserDetail>();
		NetMdUserDetail newUser1 = new NetMdUserDetail();
		newUser1.setFirstName("RanjithSankar");
		newUser1.setEmail("ranjith.sankar@netvarth.com");
		newUser1.setUserType("admin");
		newUser1.setUserName("lucky");
		newUser1.setPassword("netvarth");
		newUserList.add(newUser1);

		NetMdUserDetail newUser2 = new NetMdUserDetail();
		newUser2.setFirstName("nithesh");
		newUser2.setEmail("nithesh.mohanan@netvarth.com");
		newUser2.setUserType("admin");
		newUser2.setUserName("nithesh");
		newUser2.setPassword("netvarth");
		newUserList.add(newUser2);
		sync.setNewUserList(newUserList);

		/* update users list */
		List<NetMdUserDetail> upUserList = new ArrayList<NetMdUserDetail>();
		NetMdUserDetail updUser1 = new NetMdUserDetail();
		updUser1.setFirstName("Rajeev Sankar");
		updUser1.setEmail("rajeev.sankar@netvarth.com");
		updUser1.setUserType("staff");
		updUser1.setId(10);
		updUser1.setGlobalId(3);
		upUserList.add(updUser1);

		NetMdUserDetail updUser2 = new NetMdUserDetail();
		updUser2.setFirstName("jithesh");
		updUser2.setEmail("nithesh.mohana@netvarth.com");
		updUser2.setUserType("admin");
		updUser2.setId(13);
		updUser2.setGlobalId(11);
		upUserList.add(updUser2);
		sync.setUpdateUserList(upUserList);

		/* deleted users list */
		List<NetMdUserDetail> deleteUserList = new ArrayList<NetMdUserDetail>();
		NetMdUserDetail deldUser1 = new NetMdUserDetail();
		deldUser1.setId(10);
		deldUser1.setGlobalId(3);
		deleteUserList.add(deldUser1);

		NetMdUserDetail deldUser2 = new NetMdUserDetail();
		deldUser2.setId(11);
		deldUser2.setGlobalId(20);
		deleteUserList.add(deldUser2);

		NetMdUserDetail deldUser3 = new NetMdUserDetail();
		deldUser3.setId(0);
		deldUser2.setGlobalId(10);
		deleteUserList.add(deldUser3);

		sync.setDeleteUserList(deleteUserList);
		try {
			service.syncData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

	@Test
	public void syncRetrievalData() {
		System.out.println("########## Sync retrieve Calling... ################ ");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		SyncDTO sync = new SyncDTO();
		//sync.setLastSyncTime("2013-03-11 16:15:54");
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-80-48-6E-E1-E2");
		header.setNetMdBranchId(5);
		sync.setHeader(header);
		try {
			service.syncData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void netLimsSyncData() {
		System.out.println("########## Sync retrieve Calling for Netlims... ################ ");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		LabSyncDTO sync = new LabSyncDTO();
		sync.setLastSyncTime("2013-10-03 11:10:11");
		LabHeaderDTO header = new LabHeaderDTO();
		header.setLabId(192);
		header.setLabBranchId(122);
		header.setPassPhrase("mxfdKpnGwKrrnT03vHa4ww==");
		header.setMacId("00-1C-C0-5A-AA-7B");
		sync.setHeader(header);
		try {
			service.syncNetLimsData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void retrieveAppointmentsForPrimary()
	{
		System.out.println("########## Sync retrieve appointments for primary... ################ ");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		SyncDTO sync = new SyncDTO();
		sync.setLastSyncTime(null);
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(83);
		header.setPassPhrase("cT4GAum1sOK/bqOp8h2x4Q==");
		header.setMacId("20-CF-30-D9-9C-04");
		header.setNetMdBranchId(81);
		sync.setHeader(header);
		try {
			service.syncData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}
	@Test
	public void createBill (){
		System.out.println("create bill");
		SyncService service = (SyncService) applicationContext
				.getBean("sync.service");
		SyncDTO sync = new SyncDTO();
		sync.setLastSyncTime("2013-10-19 10:10:10");
		HeaderDTO header = new HeaderDTO();
		header.setNetMdId(3);
		header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
		header.setMacId("00-80-48-6E-E1-E2");
		header.setNetMdBranchId(5);
		header.setNetMdId(3);
		sync.setHeader(header);
		 List<BillSummaryDTO> newBillList=new ArrayList<BillSummaryDTO>();
		 BillSummaryDTO bill= new BillSummaryDTO();
		 bill.setPatientGlobalId("5");
		 bill.setPatientName("asha");
		 bill.setPayStatus("hold");
		 bill.setAmountPaid(1000);
		 bill.setOrderDate("2013-10-01");
		 bill.setBillAmount(2000);
		 bill.setUid("JV");
		 newBillList.add(bill);
		sync.setNewBillList(newBillList);
//		sync.setFreqType("hourly");
//		sync.setInterval(1);
		try {
			service.syncData(sync);
		} catch (ServiceException e) {

			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}
	}

@Test
public void updateBill (){
	System.out.println("update bill");
	SyncService service = (SyncService) applicationContext
			.getBean("sync.service");
	SyncDTO sync = new SyncDTO();
	sync.setLastSyncTime("2013-10-19 10:10:10");
	HeaderDTO header = new HeaderDTO();
	header.setNetMdId(3);
	header.setPassPhrase("n8ih3gftEbXR7NUd8Cfvhg==");
	header.setMacId("00-80-48-6E-E1-E2");
	header.setNetMdBranchId(56);
	header.setNetMdId(3);
	sync.setHeader(header);
	 List<BillSummaryDTO> updateBillList=new ArrayList<BillSummaryDTO>();
	 BillSummaryDTO bill= new BillSummaryDTO();
	 bill.setGlobalId(7);
	 bill.setPatientGlobalId("5");
	 bill.setPatientName("asha");
	 bill.setPayStatus("progress");
	 bill.setAmountPaid(1000);
	 bill.setOrderDate("2013-10-01");
	 bill.setBillAmount(2000);
	 bill.setUid("JH01");
	 updateBillList.add(bill);
	sync.setNewBillList(updateBillList);
//	sync.setFreqType("hourly");
//	sync.setInterval(1);
	try {
		service.syncData(sync);
	} catch (ServiceException e) {

		System.out.println(e.isDisplayErrMsg());
		System.out.println(e.getError());
		System.out.println(e.getParamList());
	}
}
}
