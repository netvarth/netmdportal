/**
 * Query.java
 *
 * Dec 6, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.security.pl;

/**
 * 
 */
public class Query {

	/*** NETMD ***/
	
	/* PatientAppointmentTbl */
	public static final String GET_APPOINTMENT = "from PatientAppointmentTbl as appointment where appointment.doctorTbl.id=:param1 and appointment.appointmentDate=:param2 and appointment.startingTime=:param3 and appointment.status='active'";
	public static final String GET_APPOINTMENTLIST_BY_SCHEDULE = "from PatientAppointmentTbl as appointment where appointment.doctorScheduleTbl.id=:param1 and appointment.status='active'";
	public static final String GET_PAST_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.doctorTbl where appointment.patientTbl.id=:param1 and appointment.appointmentDate <:param2 order by appointment.appointmentDate";
	public static final String GET_PAST_APPOINTMENTS_BY_TIME = "from PatientAppointmentTbl as appointment left join fetch appointment.doctorTbl where appointment.patientTbl.id=:param1 and appointment.startingTime <:param2 order by appointment.appointmentDate";
	public static final String GET_FUTURE_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.doctorTbl where appointment.patientTbl.id=:param1 and appointment.appointmentDate >=:param2 and appointment.status='active'";
	public static final String GET_WAITINGFORAPPROVAL_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.doctorTbl where appointment.patientTbl.id=:param1 and appointment.appointmentStatus = 'Waiting for approval'";
	public static final String GET_APPOINTMENTS_OF_CURRENTDAY = "from PatientAppointmentTbl as appointment left join fetch appointment.doctorTbl where appointment.patientTbl.id=:param1 and appointment.appointmentDate=:param2 and appointment.status='active'";
	public static final String GET_APPOINTMENTS_OF_THE_WEEK = "from PatientAppointmentTbl as appointment left join fetch appointment.doctorTbl where appointment.patientTbl.id=:param1 and appointment.appointmentDate >=:param2 and  appointment.appointmentDate <=:param3 and appointment.status='active'";
	public static final String GET_REJECTED_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.doctorTbl where appointment.patientTbl.id=:param1 and appointment.appointmentStatus = 'Rejected'";
	public static final String GET_APPOINTMENTS_BY_NETMD_BRANCH = "from PatientAppointmentTbl as appointment where appointment.netmdBranchTbl.id=:param1 and appointment.status='active'";
	public static final String RETRIEVE_APPOINTMENTS = "from PatientAppointmentTbl as appointment where appointment.updateDateTime>:param1 and appointment.updateDateTime<=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.netmdPassphraseTbl.id!=:param4";
	public static final String RETRIEVE_APPOINTMENTS_CREATED_IN_PORTAL = "from PatientAppointmentTbl as appointment where appointment.updateDateTime>:param1 and appointment.updateDateTime<=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.netmdPassphraseTbl.id is null";
	public static final String RETRIEVE_APPOINTMENTS_FOR_PRIMARY ="from PatientAppointmentTbl as appointment where appointment.updateDateTime>:param1 and appointment.updateDateTime<=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.appointmnetLevel=true";
	public static final String GET_APPOINTMENTS = "from PatientAppointmentTbl as appointment where appointment.updateDateTime>=:param1 and appointment.netmdPassphraseTbl.id=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.updateDateTime<=:param4 and appointment.appointmnetLevel=false order by updateDateTime";
	
	
	/* NetmdUserTbl */
	public static final String GET_USER = "from NetmdUserTbl as user where user.netmdLoginTbl.id= :param1";
	public static final String GET_USER_BY_NAME = "from NetmdUserTbl as user where TRIM(UPPER(user.name))= :param1";
	public static final String GET_NETMD_BY_EMAIL = "from NetmdUserTbl as netmduser where  TRIM(netmduser.email)=:param1";
	public static final String GET_NETMD_BY_EMAIL_AND_BRANCH = "from NetmdUserTbl as netmduser where  TRIM(netmduser.email)=:param1 and  netmduser.netmdBranchTbl.id=:param2";
	public static final String GET_NETMD_USRS_BY_NETMD_BRANCH = "from NetmdUserTbl as netmduser where netmduser.netmdBranchTbl.id=:param1";
	public static final String GET_NETMD_USER = "from NetmdUserTbl as netmduser where netmduser.netmdLoginTbl.id= :param1";
	public static final String RETRIEVE_NETMD_USERS = "from NetmdUserTbl as netmduser where  netmduser.updateDateTime>:param1 and netmduser.netmdPassphraseTbl.id!=:param2 and netmduser.netmdBranchTbl.id=:param3 and netmduser.updateDateTime <:param4 order by updateDateTime";

	/* DoctorNetmdTbl */
	public static final String DELETE_DOCTOR_BY_NETMDID = "from DoctorNetmdTbl as doctor where doctor.doctorTbl.id =:param1 and doctor.netmdTbl.id =:param2";

	/* DoctorTbl */
	public static final String GET_DOCTOR_BY_EMAIL = "from DoctorTbl as doctor where doctor.email =:param1";
	public static final String GET_DOCTOR_BY_GLOBALID = "from DoctorTbl as doctor where doctor.globalId =:param1";
	public static final String GET_DOCTOR_BY_LOGIN = "from DoctorTbl as doctor where doctor.netmdLoginTbl.userName=:param1";
	public static final String GET_DOCTORS_BY_CLINIC = "from DoctorTbl as doctor where doctor.netmdBranchTbl.id=:param1 and doctor.status='active'";
	public static final String GET_DOCTOR_BY_LOGIN_ID = "from DoctorTbl as doctor where doctor.netmdLoginTbl.id=:param1";
	public static final String GET_EXISTING_DOCTOR = "from DoctorTbl as doctor where doctor.email =:param1 and doctor.netmdBranchTbl.id =:param2";
	public static final String GET_DOCTOR_WITH_LOGIN = "from DoctorTbl as doctor where doctor.netmdLoginTbl.id =:param1 and doctor.netmdBranchTbl.id =:param2";
	public static final String RETRIEVE_DOCTORS = "from DoctorTbl as doctor where  doctor.updateDateTime>:param1 and doctor.netmdPassphraseTbl.id!=:param2 and doctor.netmdBranchTbl.id=:param3 and doctor.updateDateTime<:param4 order by updateDateTime";
	public static final String GET_DOCTORS_BY_NETMD_BRANCH = "from DoctorTbl as doctor where doctor.netmdBranchTbl.id=:param1";
	public static final String RETRIEVE__UPDATED_DOCTORS = "from DoctorTbl as doctor where  doctor.updateDateTime>:param1 and doctor.netmdPassphraseTbl.id=:param2 and doctor.netmdBranchTbl.id=:param3 and doctor.updateDateTime<=:param4 order by updateDateTime";
	public static final String RETRIEVE_DOCTORS_FOR_PRIMARY = "from DoctorTbl as doctor where  doctor.updateDateTime>=:param1 and doctor.netmdPassphraseTbl.id=:param2 and doctor.netmdBranchTbl.id=:param3 and doctor.updateDateTime<:param4 order by updateDateTime";
	
	/* DoctorPracticeExperienceTbl */
	public static final String GET_EXPERIENCE_BY_DOCTORID = "from DoctorPracticeExperienceTbl as experience where experience.doctorTbl.id= :param1";
	public static final String GET_EXPERIENCE_BY_GLOBALID = "from DoctorPracticeExperienceTbl as experience where experience.id= :param1 and experience.doctorTbl.id= :param2";

	/* DoctorEducationalQualificationTbl */
	public static final String GET_QUALIFICATION = "from DoctorEducationalQualificationTbl as qualification where qualification.id=:param1 and qualification.doctorTbl.id=:param2 ";
	public static final String GET_QUALIFICATION_BY_DOCTOR_ID = "  from DoctorEducationalQualificationTbl as qualification where qualification.doctorTbl.id= :param1";

	/* DoctorAchievementTbl */
	public static final String GET_ACHIEVEMENT_BY_DOCTOR_ID = "  from DoctorAchievementTbl as achievement where achievement.doctorTbl.id= :param1";

	/* DoctorMembershipTbl */
	public static final String GET_MEMBERSHIP_BY_DOCTOR_ID = "  from DoctorMembershipTbl as membership where membership.doctorTbl.id= :param1";

	/* DoctorExpertiseTbl */
	public static final String GET_EXPERTISE_BY_DOCTOR_ID = "  from DoctorExpertiseTbl as expertise where expertise.doctorTbl.id= :param1";

	/* DoctorScheduleTbl */
	public static final String GET_SCHEDULE_BY_DOCTORID = "  from DoctorScheduleTbl as schedule where schedule.doctorTbl.id= :param1";
	public static final String GET_SCHEDULE_BY_DATE_DOC = "from DoctorScheduleTbl as schedule where schedule.date=:param1 and schedule.doctorTbl.id=:param2 and schedule.status='active' ";
	public static final String GET_SCHEDULE_BY_DATE_DOC_BRANCH = "from DoctorScheduleTbl as schedule where schedule.date=:param1 and schedule.netmdBranchTbl.id=:param2 and schedule.doctorTbl.id=:param3 and schedule.status='active'";
	public static final String GET_SCHEDULE = "from DoctorScheduleTbl as schedule where schedule.doctorTbl.id=:param1 and schedule.date=:param2 and schedule.status='active'";
	public static final String GET_SCHEDULE_BY_NETMD_BRANCH = "from DoctorScheduleTbl as schedule where schedule.netmdBranchTbl.id=:param1";
	public static final String RETRIEVE_SCHEDULES = "from DoctorScheduleTbl as schedule where  schedule.updateDateTime>:param1 and schedule.netmdPassphraseTbl.id!=:param2 and schedule.netmdBranchTbl.id=:param3 and schedule.updateDateTime<:param4 order by updateDateTime";
	public static final String RETRIEVE_SCHEDULES_FOR_PRIMARY ="from DoctorScheduleTbl as schedule where  schedule.updateDateTime>=:param1 and schedule.netmdPassphraseTbl.id=:param2 and schedule.netmdBranchTbl.id=:param3 and schedule.updateDateTime<:param4 order by updateDateTime";
	
	/* PatientTbl */
	public static final String RETRIEVE_PATIENTS = "from PatientTbl as patient where patient.updateDateTime>=:param1 and patient.updateDateTime<:param2 and patient.netmdBranchTbl.id=:param3 and patient.netmdPassphraseTbl.id!=:param4";
	public static final String GET_PATIENT_LIST_BY_EMAILID = "from PatientTbl as patient left join fetch patient.netmdLoginTbl where TRIM(patient.netmdLoginTbl.userName) = :param1 group by patient.firstName";
	public static final String GET_PATIENT_LIST_BY_EMAIL = "from PatientTbl as patient where TRIM(patient.email) = :param1";
	public static final String GET_PATIENT_BY_NAME = "from PatientTbl as patient where TRIM(UPPER(patient.firstName)) = :param1 and patient.phone=:param2";
	public static final String GET_PATIENT_BY_EMAIL = "from PatientTbl as patient where TRIM(patient.email) = :param1";
	public static final String GET_PATIENT_BY_EMAIL_FIRSTNAME = "from PatientTbl as patient where TRIM(patient.email) = :param1 and TRIM(patient.firstName) =:param2";
	public static final String GET_PATIENT_BY_EMAIL_FIRSTNAME_BRANCH = "from PatientTbl as patient where TRIM(patient.email) = :param1 and TRIM(patient.firstName) =:param2 and patient.netmdBranchTbl.id=:param3 and patient.netmdLoginTbl.userType='patient'";
	public static final String GET_PATIENT_BY_USERNAME = "from PatientTbl as patient left join fetch patient.netmdLoginTbl where patient.netmdLoginTbl.userName =:param1";
	public static final String GET_PATIENT = "from PatientTbl as patient where TRIM(UPPER(patient.firstName)) = :param1";
	public static final String GET_PATIENT_BY_PHONE = "from PatientTbl as patient where TRIM(UPPER(patient.firstName))= :param1 and patient.netmdBranchTbl.id= :param2 and patient.phone= :param3";
	public static final String GET_PATIENT_BY_MOBILE = "from PatientTbl as patient where TRIM(UPPER(patient.firstName))= :param1 and patient.netmdBranchTbl.id= :param2 and patient.mobile= :param3";
	public static final String GET_PATIENT_BY_MAILID = "from PatientTbl as patient where TRIM(UPPER(patient.firstName))= :param1 and patient.netmdBranchTbl.id= :param2 and patient.email= :param3";
	public static final String GET_NETMD_PATIENTS_BY_NETMD_BRANCH = "from PatientTbl as patient where patient.netmdBranchTbl.id=:param1";
	public static final String GET_PATIENT_WITH_LOGIN = "from PatientTbl as patient where patient.netmdLoginTbl.id =:param1 and patient.netmdBranchTbl.id =:param2 and patient.firstName=:param3 and patient.netmdLoginTbl.userType='patient'";
	public static String RETRIEVE_PATIENTS_FOR_PRIMARY="from PatientTbl as patient where patient.updateDateTime>=:param1 and patient.updateDateTime<:param4 and patient.netmdBranchTbl.id=:param3 and patient.netmdPassphraseTbl.id=:param2";
	
	/* NetmdLoginTbl */
	public static final String GET_PATIENT_BY_USERNAME_PASSWORD = "from NetmdLoginTbl as login left join fetch NetmdUserTbl where login.password =:param1 and login.userName =:param2";
	public static final String GET_NETMD_USER_BY_PASSWORD = "from NetmdLoginTbl as login where login.password =:param1 and login.userName =:param2";
	public static final String GET_LOGIN_BY_PASSWORD = "from NetmdLoginTbl as login where login.password =:param1";
	public static final String GET_NETMD_USER_BY_USERNAME_PASSWORD = "from NetmdLoginTbl as login  where login.password =:param1 and login.userName =:param2";
	public static final String GET_NETMD_LOGIN_BY_USERNAME = "from NetmdLoginTbl as login where TRIM(login.userName)=:param1";
	public static final String GET_NETMD_LOGIN_PATIENT_BY_USERNAME = "from NetmdLoginTbl as login where TRIM(login.userName)=:param1 and login.userType='patient'";
	public static final String GET_LOGIN = "from NetmdLoginTbl as login where login.password = :param1 and login.loginId=:param2 ";
	public static final String GET_LOGIN_BY_USERNAME = "from NetmdLoginTbl as login where login.userName=:param1 and login.userType='patient'";
	public static final String GET_LOGIN_BY_OWNER_USERNAME = "from NetmdLoginTbl as login where login.userName=:param1 and login.userType='owner'";
	public static final String GET_LOGIN_BY_USERNAME_FIRSTNAME = "from NetmdLoginTbl as login left join fetch login.patientTbls as patient where login.userName=:param1 and patient.firstName=:param2";
	public static final String GET_PATIENT_FOR_PATIENTLOGIN = "from NetmdLoginTbl as login where login.password =:param1 and login.userName =:param2 and login.userType='patient' ";
	public static final String GET_NETMD_LOGIN_BY_USERNAME_USERTYPE = "from NetmdLoginTbl as login where login.userName=:param1 and login.userType=:param2";
	
	/* NetmdBranchTbl */
	public static final String GET_NETMD_BRANCH = "from NetmdBranchTbl as branch where branch.id=:param1 and branch.netmdTbl.id=:param2";
	public static final String GET_NETMD_HOME_BRANCH = "from NetmdBranchTbl as branch where branch.homeBranch=true and branch.netmdTbl.id=:param1";
	public static final String GET_NETMD_BRANCH_BY_NAME = "from NetmdBranchTbl as branch where branch.name=:param1";
	public static final String GET_NEW_NETMD_BRANCHES = "from NetmdBranchTbl as branch where  branch.createDateTime>:param1 and branch.createDateTime<:param2 order by createDateTime";
	public static final String GET_UPDATE_NETMD_BRANCHES = "from NetmdBranchTbl as branch where  branch.createDateTime < branch.updateDateTime and branch.updateDateTime>:param1 and branch.updateDateTime<:param2 order by updateDateTime";
	public static final String GET_NETMD_BRANCHES = "from NetmdBranchTbl as branch where branch.netmdTbl.id=:param1";

	/* NetmdPassphraseTbl */
	public static final String GET_MAC_BY_PASSPHRASE = " select branchPassphrase.macId from NetmdPassphraseTbl as branchPassphrase where branchPassphrase.passPhrase=:param1";
	public static final String GET_NETMD_BRANCH_PASSPHRASE = " from NetmdPassphraseTbl as branchPassphrase where branchPassphrase.passPhrase=:param1";
	public static final String GET_NETMD_BRANCH_ID = "from NetmdPassphraseTbl as branchPassphrase where branchPassphrase.passPhrase=:param1 and branchPassphrase.macId=:param2";
	public static final String GET_NETMD_PASSPHRASE_ID = "select netmdPassphrase.id from NetmdPassphraseTbl as netmdPassphrase where netmdPassphrase.passPhrase=:param1 and netmdPassphrase.netmdBranchTbl.id=:param2";
	public static final String GET_NETMD_BY_PASSPHRASE = "from NetmdPassphraseTbl as pass where pass.netmdTbl.id= :param1 and pass.passPhrase =:param2";
	public static final String GET_NETMD_PASSPHRASE = "from NetmdPassphraseTbl as phrase where phrase.passPhrase=:param1";
	public static final String GET_NETMD_PASSPHRASE_BY_BRANCH_ID = "from NetmdPassphraseTbl as branchPassphrase where branchPassphrase.netmdBranchTbl.id=:param1 and branchPassphrase.passPhrase=:param2";
	public static final String NETMD_PASSPHRASE_BY_BRANCH = "from NetmdPassphraseTbl as branchPassphrase where branchPassphrase.netmdBranchTbl.id=:param1";
	
	/* NetmdPatientBranchTbl */
	public static final String DELETE_BRANCH_BY_PATIENT = " delete from NetmdPatientBranchTbl as patientBranch where patientBranch.patientTbl.id=:param1";
	public static final String GET_BRANCH_BY_PATIENT = " from NetmdPatientBranchTbl as patientBranch where patientBranch.patientTbl.id=:param1";

	/* NetmdTbl */
	public static final String GET_NETMD_BY_NAME = "from NetmdTbl as netmd where  REPLACE(TRIM(UPPER(netmd.name)),' ','')=:param1";
	public static final String GET_MAC_AND_PASSPHRASE_BY_NETMD_BRANCH = "from NetmdPassphraseTbl as passPhrase where passPhrase.netmdBranchTbl.id=:param1";
	public static final String GET_NEW_NETMD = "from NetmdTbl as netmd where netmd.createDateTime>:param1 and netmd.createDateTime<:param2 order by createDateTime";
	public static final String GET_UPDATE_NETMD = "from NetmdTbl as netmd where  netmd.createDateTime < netmd.updateDateTime and netmd.updateDateTime>:param1 and netmd.updateDateTime<:param2 order by updateDateTime";
	public static final String GET_NETMD_BY_LOGIN_ID = "from NetmdTbl as netmd where  netmd.netmdLoginTbl.id=:param1";

	/* SeriesTbl */
	public static final String GET_EXISTING_SERIES = "from SeriesTbl as series where series.seriesId= :param1 and series.netmdPassphraseTbl.id=:param2";

	/*NetmdBranchSystemInfoTbl*/
	public static final String GET_NETMD_BRANCH_SYSTEM_DETAILS = " from NetmdBranchSystemInfoTbl as details where details.netmdBranchTbl.id=:param1 and details.netmdPassphraseTbl.id=:param2";
	
	/*NetmdHealthMonitorTbl*/
	public static final String GET_NETMD_TOTAL_RECORDS = "select count(*) from NetmdHealthMonitorTbl as h where h.netmdPassphraseTbl.id =:param1";
	public static final String GET_NETMD_HEALTH_MONITORING_DETAILS = "from NetmdHealthMonitorTbl as health where health.netmdPassphraseTbl.id =:param1  order by health.id ";
	
	/*NetMdBilltbl*/
	public static final String GET_BILL_BY_DATE ="from NetmdBillTbl as bill where bill.orderDate>=:param1 and bill.orderDate<=:param2 and bill.netmdTbl.id=:param3 and bill.netmdBranchTbl.id=:param4  order by orderDate";
	/*** NETLIMS ***/

	/* LabUserTbl */
	public static final String GET_NETLIMS_USER = "from LabUserTbl as user where user.labLoginTbl.id= :param1";
	public static final String GET_NETLIMS_USER_BY_EMAIL = "from LabUserTbl as user where user.email= :param1";
	public static final String GET_NEW_USERS = "from LabUserTbl as labUser where  labUser.id=:param1 and labUser.createDateTime>:param2 and labUser.createDateTime<:param3 order by createDateTime";
	public static final String GET_UPDATED_USERS = "from LabUserTbl as labUser where labUser.id=:param1 and labUser.createDateTime < labUser.updateDateTime and labUser.updateDateTime>:param2 and labUser.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_OWN_USER = "from LabUserTbl as labUser where  labUser.id=:param1 and labUser.updateDateTime>:param2 order by updateDateTime";

	/* LabUserBranchTbl */
	public static final String GET_USER_BY_BRANCH = "from LabUserBranchTbl as userBranch where userBranch.labUserTbl.id= :param1 and userBranch.labBranchTbl.id= :param2";
	public static final String GET_BRANCH_BY_USER = "from LabUserBranchTbl as userBranch where userBranch.labUserTbl.id= :param1";
	public static final String GET_USERS_IN_BRANCH = "from LabUserBranchTbl as userBranch where userBranch.labBranchTbl.id= :param1";
	public static final String GET_ADMINS_IN_BRANCH = "from LabUserBranchTbl as userBranch where userBranch.labBranchTbl.id= :param1 and userBranch.labBranchTbl.userType:=param2";

	/* LabLoginTbl */
	public static final String GET_NETLIMS_USER_BY_PASSWORD = "from LabLoginTbl as login where login.password =:param1 and login.userName =:param2";
	public static final String GET_NETLIMS_LOGIN_BY_USERNAME = "from LabLoginTbl as login where TRIM(login.userName)=:param1";

	/* SuperAdminTbl */
	public static final String GET_USER_BY_PASSWORD = "from SuperAdminTbl as login where login.password =:param1 and login.userName =:param2";
	public static final String GET_SUPER_ADMIN_BY_USERNAME = "from SuperAdminTbl as admin where TRIM(admin.userName)= :param1";
	public static final String GET_SUPER_ADMIN = "from SuperAdminTbl as admin where admin.loginTbl.loginId= :param1";

	/* LabBranchTbl */
	public static final String GET_HOME_BRANCH = "from LabBranchTbl as branch where branch.homeBranch=true and branch.labTbl.id=:param1";
	public static final String GET_BRANCHES = "from LabBranchTbl as branch where  branch.labTbl.id=:param1";
	public static final String GET_BRANCH = "from LabBranchTbl as branch where branch.id=:param1 and branch.labTbl.id=:param2";
	public static final String GET_LAB_BRANCH= " from LabBranchTbl as labBranch where labBranch.id =:param1 and labBranch.labTbl.id=:param2";
	
	/* LabPassphraseTbl */
	public static final String GET_NETLIMS_MAC_BY_PASSPHRASE = " select lab.macId from LabPassphraseTbl as lab where lab.passPhrase=:param1";
	public static final String GET_NETLIMS_BRANCH_BY_PASSPHRASE = " from LabPassphraseTbl as lab where lab.passPhrase=:param1";
	public static final String GET_BRANCH_BY_LAB = " from LabBranchTbl as branch where branch.labTbl.id=:param1 and branch.id=:param2";
	public static final String GET_ORDERS_BY_LAB_AND_BRANCH_ID = " from OrderTbl as order where order.destinationLabTbl.id=:param1 and order.destinationBranchTbl.id=:param2 and order.updateTime>:param3 order by updateTime";
	public static final String GET_MAC_AND_PASSPHRASE_BY_BRANCH = "  from LabPassphraseTbl as lab where lab.labBranchTbl.id=:param1";

	/* LabTbl */
	public static final String GET_LAB_BY_NAME = "from LabTbl as lab where  REPLACE(TRIM(UPPER(lab.name)),' ','')=:param1";
	public static final String GET_NETLIMS_OWNER = "from LabTbl as lab where  lab.labLoginTbl.id=:param1";
	public static final String GET_NEW_BRANCHES = "from LabBranchTbl as branch where  branch.labTbl.id!=:param1 and branch.createDateTime>:param2 and branch.createDateTime<:param3 order by createDateTime";
	public static final String GET_UPDATED_BRANCHES = "from LabBranchTbl as branch where  branch.labTbl.id!=:param1 and branch.createDateTime< branch.updateDateTime and branch.updateDateTime>:param2 and branch.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_OWN_BRANCHES = "from LabBranchTbl as branch where  branch.labTbl.id=:param1 and branch.updateDateTime>:param2 and branch.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_NEW_LABS = "from LabTbl as lab where  lab.id!=:param1 and lab.createDateTime>:param2 and lab.createDateTime<:param3 order by createDateTime";
	public static final String GET_UPDATED_LABS = "from LabTbl as lab where lab.id!=:param1 and lab.createDateTime < lab.updateDateTime and lab.updateDateTime>:param2 and lab.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_OWN_LAB = "from LabTbl as lab where  lab.id=:param1 and lab.updateDateTime>:param2 and lab.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_LAB_BY_LOGIN_ID = "from LabTbl as lab where  lab.labLoginTbl.id=:param1";
	public static final String GET_LAB_DETAILS_BY_TIME = "from LabTbl as lab where lab.id=:param1 and lab.updateDateTime >=:param2 and lab.updateDateTime<:param3";
	
	/* ResultTbl */
	public static final String GET_RESULT_BY_PATIENTID = "from ResultTbl as result where result.patientTbl.id=:param1";
	public static final String GET_RESULTS = "from ResultTbl as patientResults where patientResults.netmdTbl.id=:param1 and patientResults.netmdBranchTbl.id=:param2 and patientResults.updatedDateTime>:param3 and patientResults.updatedDateTime<:param4";
	public static final String GET_PATIENT_RESULT = "from ResultTbl as patientResults where patientResults.orderId=:param1 and patientResults.netmdBranchTbl.id=:param2 and patientResults.netmdTbl.id=:param3 and patientResults.patientTbl.id=:param4 and patientResults.labTbl.id=:param5 and patientResults.labBranchTbl.id=:param6";
	public static final String GET_PATIENT_RESULTS_BY_ORDERID ="from ResultTbl as patientResults where patientResults.patientTbl.id=:param1 and patientResults.orderId=:param2";
	
	/*OrderAmountTbl*/
	public static final String GET_BRANCH_ORDERS = "from OrderAmountTbl as orders where orders.labTbl.id=:param1 and orders.orderDate=:param2";
	public static final String GET_BRANCH_ORDERS_BY_DATE = "from OrderAmountTbl as orders where orders.orderDate>=:param1 and orders.orderDate<=:param2 and orders.labTbl.id=:param3 and orders.labBranchTbl.id=:param4 ";
	public static final String GET_BRANCH_ORDERS_BY_LAB ="from OrderAmountTbl as orders where orders.labTbl.id=:param1 and orders.labBranchTbl.id=:param2 and orders.orderDate=:param3";
	
	/*LabBranchSystemInfoTbl*/
	public static final String GET_SYSTEM_DETAILS_BY_BRANCH_ID = " from LabBranchSystemInfoTbl as details where details.labBranchTbl.id=:param1";

	/*LabHealthMonitorTbl*/
	public static final String GET_MONITORING_DETAILS_BY_BRANCH_ID = "from LabHealthMonitorTbl as health where health.labBranchTbl.id =:param1  order by health.id ";
	public static final String GET_TOTAL_RECORDS = "select COUNT(*) from LabHealthMonitorTbl as h where h.labBranchTbl.id =:param1";
	
	/********** Email *******/
	public static final String GET_INQUEUE_FROM_TABLE = "from PendingMessageTbl as msg where msg.status=:param3 and msg.communicationType=:param1 and msg.applicationSpecifier=:param2 ORDER BY lastAttemptOn ASC";
	public static final String GET_COUNT_NEW_FROM_TABLE = "from PendingMessageTbl as msg where msg.status=:param3 and msg.communicationType=:param1  and msg.applicationSpecifier=:param2";
	public static final String GET_NEW_MSG = "from PendingMessageTbl as msg where msg.status=:param3 and msg.communicationType=:param1 and msg.applicationSpecifier=:param2 ORDER BY lastAttemptOn ASC";
	
	/*OrderTransferTbl*/
	public static final String GET_LAST_UNIQUE_ID = "select MAX(u.uniqueId) from OrderTransferTbl as u"; 
	public static final String GET_ORDERS = "from OrderTransferTbl as order where order.destinationLab.id=:param1 and order.destinationBranch.id=:param2 and order.updatedDateTime>=:param3 and order.updatedDateTime<:param4";
	

	/*SpecimenTbl*/
	public static final String GET_SPECIMEN_BY_NAME="from SpecimenTbl as specimen where TRIM(UPPER(specimen.name)) = :param1";
	public static final String GET_LAST_UID = "select MAX(u.uid) from SpecimenTbl as u";   
	public static final String GET_SPECIMEN_BY_DATE = "from SpecimenTbl as specimen where specimen.updatedDateTime >=:param1 and specimen.updatedDateTime<:param2 order by updatedDateTime";
	
	/*TestTbl*/
	public static final String GET_TEST_BY_NAME = "from TestTbl as test where TRIM(UPPER(test.testName)) = :param1";
	public static final String GET_TEST_BY_ABBREVIATION = "from TestTbl as test where TRIM(UPPER(test.abbreviation)) = :param1";
	public static final String GET_LAST_TEST_UID = "select MAX(u.uid) from TestTbl as u";   
	public static final String GET_TEST_BY_DATE = " from TestTbl as test where test.updatedDateTime >= :param1 and test.updatedDateTime <:param2 order by updatedDateTime";
	
	/*TestSpecimenTbl*/
	public static final String GET_TEST_SPECIMEN_BY_SPECIMEN_UID ="from TestSpecimenTbl as testSpecimen where testSpecimen.testTbl.uid = :param1 and testSpecimen.specimenTbl.uid = :param2";
	public static final String GET_TEST_SPECIMEN = "from TestSpecimenTbl as testSpecimen where testSpecimen.testTbl.uid = :param1";
	public static final String GET_SPECIMEN_BY_TEST_UID = "from TestSpecimenTbl as testSpecimen where testSpecimen.testTbl.uid=:param1";
	
	
	/**************NetRx******************/
	
	/*NetRxTbl*/
	public static final String GET_NETRX_BY_NAME = "from NetrxTbl as netrx where  REPLACE(TRIM(UPPER(netrx.name)),' ','')=:param1";
	public static final String GET_NETRX_BY_LOGIN_ID = "from NetrxTbl as netmd where  netmd.netrxLoginTbl.id=:param1";
	
	/*NetrxBranchTbl*/
	public static final String GET_NETRX_BRANCHES = "from NetrxBranchTbl as netrxBranch where  netrxBranch.netrxTbl.id=:param1";
	
	/*NetrxUserTbl*/
	public static final String GET_NETRX_USERS = "from NetrxUserTbl as netrxUsers where  netrxUsers.netrxBranchTbl.id=:param1";
	public static final String GET_NETRX_BY_EMAIL_AND_BRANCH = "from NetrxUserTbl as netrxuser where  TRIM(netrxuser.email)=:param1 and  netrxuser.netrxBranchTbl.id=:param2";
	public static final String GET_NETRX_USER = "from NetrxUserTbl as user where user.netrxLoginTbl.id= :param1";
	
	/*NetrxPassphraseTbl*/
	public static final String GET_MAC_AND_PASSPHRASE_BY_NETRX_BRANCH = "from NetrxPassphraseTbl as passPhrase where passPhrase.netrxBranchTbl.id=:param1";
	public static final String GET_NETRX_BRANCH_PASSPHRASE = " from NetrxPassphraseTbl as branchPassphrase where branchPassphrase.passPhrase=:param1";
	
	/*NetrxLoginTbl*/
	public static final String GET_NETRX_LOGIN_BY_USERNAME = "from NetrxLoginTbl as login where login.userName=:param1";
	public static final String GET_NETRX_USER_BY_USERNAME_PASSWORD = "from NetrxLoginTbl as login  where login.password =:param1 and login.userName =:param2";
	public static final String GET_NETRX_PASSPHRASE_BY_BRANCH_ID = "from NetrxPassphraseTbl as branchPassphrase where branchPassphrase.netrxBranchTbl.id=:param1 and branchPassphrase.passPhrase=:param2";
	
	

	
	
}

