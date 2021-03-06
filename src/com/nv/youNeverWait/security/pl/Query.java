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
	public static final String GET_APPOINTMENT = "from PatientAppointmentTbl as appointment where appointment.netmdDoctorTbl.id=:param1 and appointment.appointmentDate=:param2 and appointment.startingTime=:param3 and appointment.status='active'";
	public static final String GET_APPOINTMENTLIST_BY_SCHEDULE = "from PatientAppointmentTbl as appointment where appointment.doctorScheduleTbl.id=:param1 and appointment.status='active'";
	public static final String GET_PAST_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.netmdDoctorTbl where appointment.netmdPatientTbl.id=:param1 and appointment.appointmentDate <:param2 order by appointment.appointmentDate";
	public static final String GET_PAST_APPOINTMENTS_BY_TIME = "from PatientAppointmentTbl as appointment left join fetch appointment.netmdDoctorTbl where appointment.netmdPatientTbl.id=:param1 and appointment.startingTime <:param2 order by appointment.appointmentDate";
	public static final String GET_FUTURE_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.netmdDoctorTbl where appointment.netmdPatientTbl.id=:param1 and appointment.appointmentDate >=:param2 and appointment.status='active'";
	public static final String GET_WAITINGFORAPPROVAL_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.netmdDoctorTbl where appointment.netmdPatientTbl.id=:param1 and appointment.appointmentStatus = 'Waiting for approval'";
	public static final String GET_APPOINTMENTS_OF_CURRENTDAY = "from PatientAppointmentTbl as appointment left join fetch appointment.netmdDoctorTbl where appointment.netmdPatientTbl.id=:param1 and appointment.appointmentDate=:param2 and appointment.status='active'";
	public static final String GET_APPOINTMENTS_OF_THE_WEEK = "from PatientAppointmentTbl as appointment left join fetch appointment.netmdDoctorTbl where appointment.netmdPatientTbl.id=:param1 and appointment.appointmentDate >=:param2 and  appointment.appointmentDate <=:param3 and appointment.status='active'";
	public static final String GET_REJECTED_APPOINTMENTS = "from PatientAppointmentTbl as appointment left join fetch appointment.netmdDoctorTbl where appointment.netmdPatientTbl.id=:param1 and appointment.appointmentStatus = 'Rejected'";
	public static final String GET_APPOINTMENTS_BY_NETMD_BRANCH = "from PatientAppointmentTbl as appointment where appointment.netmdBranchTbl.id=:param1 and appointment.status='active'";
	public static final String RETRIEVE_APPOINTMENTS = "from PatientAppointmentTbl as appointment where appointment.updateDateTime>:param1 and appointment.updateDateTime<=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.netmdPassphraseTbl.id!=:param4";
	public static final String RETRIEVE_APPOINTMENTS_CREATED_IN_PORTAL = "from PatientAppointmentTbl as appointment where appointment.updateDateTime>:param1 and appointment.updateDateTime<=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.netmdPassphraseTbl.id is null";
	public static final String RETRIEVE_APPOINTMENTS_FOR_PRIMARY ="from PatientAppointmentTbl as appointment where appointment.updateDateTime>:param1 and appointment.updateDateTime<=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.appointmnetLevel=true";
	public static final String GET_APPOINTMENTS = "from PatientAppointmentTbl as appointment where appointment.updateDateTime>=:param1 and appointment.netmdPassphraseTbl.id=:param2 and appointment.netmdBranchTbl.id=:param3 and appointment.updateDateTime<=:param4 and appointment.appointmnetLevel=false order by updateDateTime";
	
	
	/* NetmdUserTbl */
	public static final String GET_USER = "from NetmdUserTbl as user where user.loginTbl.id= :param1";
	public static final String GET_USER_BY_NAME = "from NetmdUserTbl as user where TRIM(UPPER(user.name))= :param1";
	public static final String GET_NETMD_BY_EMAIL = "from NetmdUserTbl as netmduser where  TRIM(netmduser.email)=:param1";
	public static final String GET_NETMD_BY_EMAIL_AND_BRANCH = "from NetmdUserTbl as netmduser where  TRIM(netmduser.email)=:param1 and  netmduser.netmdBranchTbl.id=:param2";
	public static final String GET_NETMD_USRS_BY_NETMD_BRANCH = "from NetmdUserTbl as netmduser where netmduser.netmdBranchTbl.id=:param1";
	public static final String GET_NETMD_USER = "from NetmdUserTbl as netmduser where netmduser.loginTbl.id= :param1";
	public static final String RETRIEVE_NETMD_USERS = "from NetmdUserTbl as netmduser where  netmduser.updateDateTime>:param1 and netmduser.netmdPassphraseTbl.id!=:param2 and netmduser.netmdBranchTbl.id=:param3 and netmduser.updateDateTime <:param4 order by updateDateTime";

	/* DoctorNetmdTbl */
	public static final String DELETE_DOCTOR_BY_NETMDID = "from DoctorNetmdTbl as doctor where doctor.netmdDoctorTbl.id =:param1 and doctor.netmdTbl.id =:param2";

	/* NetmdDoctorTbl */
	public static final String GET_DOCTOR_BY_EMAIL = "from NetmdDoctorTbl as doctor where doctor.email =:param1";
	public static final String GET_DOCTOR_BY_GLOBALID = "from NetmdDoctorTbl as doctor where doctor.globalId =:param1";
	public static final String GET_DOCTOR_BY_LOGIN = "from NetmdDoctorTbl as doctor where doctor.loginTbl.userName=:param1";
	public static final String GET_DOCTORS_BY_CLINIC = "from NetmdDoctorTbl as doctor where doctor.netmdBranchTbl.id=:param1 and doctor.status='active'";
	public static final String GET_DOCTOR_BY_LOGIN_ID = "from NetmdDoctorTbl as doctor where doctor.loginTbl.id=:param1";
	public static final String GET_EXISTING_DOCTOR = "from NetmdDoctorTbl as doctor where doctor.email =:param1 and doctor.netmdBranchTbl.id =:param2";
	public static final String GET_DOCTOR_WITH_LOGIN = "from NetmdDoctorTbl as doctor where doctor.loginTbl.id =:param1 and doctor.netmdBranchTbl.id =:param2 and doctor.status='active'";
	public static final String RETRIEVE_DOCTORS = "from NetmdDoctorTbl as doctor where  doctor.updateDateTime>:param1 and doctor.netmdPassphraseTbl.id!=:param2 and doctor.netmdBranchTbl.id=:param3 and doctor.updateDateTime<:param4 order by updateDateTime";
	public static final String GET_DOCTORS_BY_NETMD_BRANCH = "from NetmdDoctorTbl as doctor where doctor.netmdBranchTbl.id=:param1";
	public static final String RETRIEVE__UPDATED_DOCTORS = "from NetmdDoctorTbl as doctor where  doctor.updateDateTime>:param1 and doctor.netmdPassphraseTbl.id=:param2 and doctor.netmdBranchTbl.id=:param3 and doctor.updateDateTime<=:param4 order by updateDateTime";
	public static final String RETRIEVE_DOCTORS_FOR_PRIMARY = "from NetmdDoctorTbl as doctor where  doctor.updateDateTime>=:param1 and doctor.netmdPassphraseTbl.id=:param2 and doctor.netmdBranchTbl.id=:param3 and doctor.updateDateTime<:param4 order by updateDateTime";
	
	/* DoctorPracticeExperienceTbl */
	public static final String GET_EXPERIENCE_BY_DOCTORID = "from DoctorPracticeExperienceTbl as experience where experience.netmdDoctorTbl.id= :param1";
	public static final String GET_EXPERIENCE_BY_GLOBALID = "from DoctorPracticeExperienceTbl as experience where experience.id= :param1 and experience.netmdDoctorTbl.id= :param2";

	/* DoctorEducationalQualificationTbl */
	public static final String GET_QUALIFICATION = "from DoctorEducationalQualificationTbl as qualification where qualification.id=:param1 and qualification.netmdDoctorTbl.id=:param2 ";
	public static final String GET_QUALIFICATION_BY_DOCTOR_ID = "  from DoctorEducationalQualificationTbl as qualification where qualification.netmdDoctorTbl.id= :param1";

	/* DoctorAchievementTbl */
	public static final String GET_ACHIEVEMENT_BY_DOCTOR_ID = "  from DoctorAchievementTbl as achievement where achievement.netmdDoctorTbl.id= :param1";

	/* DoctorMembershipTbl */
	public static final String GET_MEMBERSHIP_BY_DOCTOR_ID = "  from DoctorMembershipTbl as membership where membership.netmdDoctorTbl.id= :param1";

	/* DoctorExpertiseTbl */
	public static final String GET_EXPERTISE_BY_DOCTOR_ID = "  from DoctorExpertiseTbl as expertise where expertise.netmdDoctorTbl.id= :param1";

	/* DoctorScheduleTbl */
	public static final String GET_SCHEDULE_BY_DOCTORID = "  from DoctorScheduleTbl as schedule where schedule.netmdDoctorTbl.id= :param1";
	public static final String GET_SCHEDULE_BY_DATE_DOC = "from DoctorScheduleTbl as schedule where schedule.date=:param1 and schedule.netmdDoctorTbl.id=:param2 and schedule.status='active' ";
	public static final String GET_SCHEDULE_BY_DATE_DOC_BRANCH = "from DoctorScheduleTbl as schedule where schedule.date=:param1 and schedule.netmdBranchTbl.id=:param2 and schedule.netmdDoctorTbl.id=:param3 and schedule.status='active'";
	public static final String GET_SCHEDULE = "from DoctorScheduleTbl as schedule where schedule.netmdDoctorTbl.id=:param1 and schedule.date=:param2 and schedule.status='active'";
	public static final String GET_SCHEDULE_BY_NETMD_BRANCH = "from DoctorScheduleTbl as schedule where schedule.netmdBranchTbl.id=:param1";
	public static final String RETRIEVE_SCHEDULES = "from DoctorScheduleTbl as schedule where  schedule.updateDateTime>:param1 and schedule.netmdPassphraseTbl.id!=:param2 and schedule.netmdBranchTbl.id=:param3 and schedule.updateDateTime<:param4 order by updateDateTime";
	public static final String RETRIEVE_SCHEDULES_FOR_PRIMARY ="from DoctorScheduleTbl as schedule where  schedule.updateDateTime>=:param1 and schedule.netmdPassphraseTbl.id=:param2 and schedule.netmdBranchTbl.id=:param3 and schedule.updateDateTime<:param4 order by updateDateTime";
	
	/* PatientTbl */
	public static final String RETRIEVE_PATIENTS = "from NetmdPatientTbl as patient where patient.updateDateTime>=:param1 and patient.updateDateTime<:param2 and patient.netmdBranchTbl.id=:param3 and patient.netmdPassphraseTbl.id!=:param4";
	public static final String GET_PATIENT_LIST_BY_EMAILID = "from NetmdPatientTbl as patient left join fetch patient.loginTbl where TRIM(patient.loginTbl.userName) = :param1 group by patient.firstName";
	public static final String GET_PATIENT_LIST_BY_EMAIL = "from NetmdPatientTbl as patient where TRIM(patient.email) = :param1";
	public static final String GET_PATIENT_BY_NAME = "from NetmdPatientTbl as patient where TRIM(UPPER(patient.firstName)) = :param1 and patient.phone=:param2";
	public static final String GET_PATIENT_BY_EMAIL = "from NetmdPatientTbl as patient where TRIM(patient.email) = :param1";
	public static final String GET_PATIENT_BY_EMAIL_FIRSTNAME = "from NetmdPatientTbl as patient where TRIM(patient.email) = :param1 and TRIM(patient.firstName) =:param2";
	public static final String GET_PATIENT_BY_EMAIL_FIRSTNAME_BRANCH = "from NetmdPatientTbl as patient where TRIM(patient.email) = :param1 and TRIM(patient.firstName) =:param2 and patient.netmdBranchTbl.id=:param3 and patient.loginTbl.userType='patient'";
	public static final String GET_PATIENT_BY_USERNAME = "from NetmdPatientTbl as patient left join fetch patient.loginTbl where patient.loginTbl.userName =:param1";
	public static final String GET_PATIENT = "from NetmdPatientTbl as patient where TRIM(UPPER(patient.firstName)) = :param1";
	public static final String GET_PATIENT_BY_PHONE = "from NetmdPatientTbl as patient where TRIM(UPPER(patient.firstName))= :param1 and patient.netmdBranchTbl.id= :param2 and patient.phone= :param3";
	public static final String GET_PATIENT_BY_MOBILE = "from NetmdPatientTbl as patient where TRIM(UPPER(patient.firstName))= :param1 and patient.netmdBranchTbl.id= :param2 and patient.mobile= :param3";
	public static final String GET_PATIENT_BY_MAILID = "from NetmdPatientTbl as patient where TRIM(UPPER(patient.firstName))= :param1 and patient.netmdBranchTbl.id= :param2 and patient.email= :param3";
	public static final String GET_NETMD_PATIENTS_BY_NETMD_BRANCH = "from NetmdPatientTbl as patient where patient.netmdBranchTbl.id=:param1";
	public static final String GET_PATIENT_WITH_LOGIN = "from NetmdPatientTbl as patient where patient.loginTbl.id =:param1 and patient.netmdBranchTbl.id =:param2 and patient.firstName=:param3 and patient.loginTbl.userType='patient'";
	public static String RETRIEVE_PATIENTS_FOR_PRIMARY="from NetmdPatientTbl as patient where patient.updateDateTime>=:param1 and patient.updateDateTime<:param4 and patient.netmdBranchTbl.id=:param3 and patient.netmdPassphraseTbl.id=:param2";
	public static final String GET_BY_PATIENT_NAME_AND_BRANCH_ID = "from NetmdPatientTbl as patient where UPPER(patient.firstName)=:param1 and UPPER(patient.lastName)=:param2 and patient.email=:param3 and patient.netmdBranchTbl.id=:param4";
	/* LoginTbl */
	public static final String GET_PATIENT_BY_USERNAME_PASSWORD = "from LoginTbl as login left join fetch NetmdUserTbl where login.password =:param1 and login.userName =:param2";
	public static final String GET_NETMD_USER_BY_PASSWORD = "from LoginTbl as login where login.password =:param1 and login.userName =:param2";
	public static final String GET_LOGIN_BY_PASSWORD = "from LoginTbl as login where login.password =:param1";
	public static final String GET_NETMD_USER_BY_USERNAME_PASSWORD = "from LoginTbl as login  where login.password =:param1 and login.userName =:param2 and login.userType=:param3";
	public static final String GET_NETMD_LOGIN_BY_USERNAME = "from LoginTbl as login where TRIM(login.userName)=:param1";
	public static final String GET_NETMD_LOGIN_PATIENT_BY_USERNAME = "from LoginTbl as login where TRIM(login.userName)=:param1 and login.userType='patient'";
	public static final String GET_LOGIN = "from LoginTbl as login where login.password = :param1 and login.loginId=:param2 ";
	public static final String GET_LOGIN_BY_USERNAME = "from LoginTbl as login where login.userName=:param1 and login.userType='patient'";
	public static final String GET_LOGIN_BY_OWNER_USERNAME = "from LoginTbl as login where login.userName=:param1 and login.userType='owner'";
	public static final String GET_LOGIN_BY_USERNAME_FIRSTNAME = "from LoginTbl as login left join fetch login.netmdPatientTbls as patient where login.userName=:param1 and patient.firstName=:param2";
	public static final String GET_PATIENT_FOR_PATIENTLOGIN = "from LoginTbl as login where login.password =:param1 and login.userName =:param2 and login.userType='patient' ";
	public static final String GET_NETMD_LOGIN_BY_USERNAME_USERTYPE = "from LoginTbl as login where login.userName=:param1 and login.userType=:param2";
	public static final String GET_USER_BY_NAME_PASSWORD_TYPE = "from LoginTbl as login where login.userName =:param1 and login.password =:param2 and login.userType=:param3";
	
	/* NetmdBranchTbl */
	public static final String GET_NETMD_BRANCH = "from NetmdBranchTbl as branch where branch.id=:param1 and branch.netmdTbl.id=:param2";
	public static final String GET_NETMD_HOME_BRANCH = "from NetmdBranchTbl as branch where branch.homeBranch=true and branch.netmdTbl.id=:param1";
	public static final String GET_NETMD_BRANCH_BY_NAME = "from NetmdBranchTbl as branch where branch.name=:param1";
	public static final String GET_NEW_NETMD_BRANCHES = "from NetmdBranchTbl as branch where  branch.createDateTime>:param1 and branch.createDateTime<:param2 order by createDateTime";
	public static final String GET_UPDATE_NETMD_BRANCHES = "from NetmdBranchTbl as branch where  branch.createDateTime < branch.updateDateTime and branch.updateDateTime>:param1 and branch.updateDateTime<:param2 order by updateDateTime";
	public static final String GET_NETMD_BRANCHES = "from NetmdBranchTbl as branch where branch.netmdTbl.id=:param1";
	public static final String GET_UPDATE_NETMD_BRANCH_DETAILS ="from NetmdBranchTbl as branch where  branch.createDateTime < branch.updateDateTime and branch.updateDateTime>:param1 and branch.updateDateTime<:param2 and branch.id=:param3";
	
	/*OrganisationNetmdTbl*/
	public static final String GET_ORGANISATION_NETMD_BRANCH_LIST = "from OrganisationNetmdTbl as orgNetmdBranch where orgNetmdBranch.organisationTbl.id=:param1 ";
	public static final String RETRIEVE_ORGANISATIONS = "from OrganisationNetmdTbl as org where org.netmdBranchTbl.id=:param1";
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
	public static final String GET_NETMD_BY_LOGIN_ID = "from NetmdTbl as netmd where  netmd.loginTbl.id=:param1";
	public static final String GET_NETMD = "from NetmdTbl as netmd" ;
	public static final String GET_UPDATE_NETMD_DETAILS = "from NetmdTbl as netmd where  netmd.createDateTime < netmd.updateDateTime and netmd.updateDateTime>:param1 and netmd.updateDateTime<:param2 and netmd.id=:param3";
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

	/* UserTbl */
	public static final String GET_NETLIMS_USER = "from LabUserTbl as user where user.loginTbl.id= :param1";
	public static final String GET_NETLIMS_USER_BY_EMAIL = "from UserTbl as user where user.email= :param1";
	public static final String GET_NEW_USERS = "from UserTbl as labUser where  labUser.id=:param1 and labUser.createDateTime>:param2 and labUser.createDateTime<:param3 order by createDateTime";
	public static final String GET_UPDATED_USERS = "from UserTbl as labUser where labUser.id=:param1 and labUser.createDateTime < labUser.updateDateTime and labUser.updateDateTime>:param2 and labUser.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_OWN_USER = "from UserTbl as labUser where  labUser.id=:param1 and labUser.updateDateTime>:param2 order by updateDateTime";

	/* LabUserTbl */
	public static final String GET_USER_BY_BRANCH = "from LabUserTbl as userBranch where userBranch.userTbl.id= :param1 and userBranch.labBranchTbl.id= :param2";
	public static final String GET_BRANCH_BY_USER = "from LabUserTbl as userBranch where userBranch.userTbl.id= :param1";
	public static final String GET_USERS_IN_BRANCH = "from LabUserTbl as userBranch where userBranch.labBranchTbl.id= :param1";
	public static final String GET_ADMINS_IN_BRANCH = "from LabUserTbl as userBranch where userBranch.labBranchTbl.id= :param1 and userBranch.labBranchTbl.userType:=param2";

	/* LoginTbl */
	public static final String GET_NETLIMS_USER_BY_PASSWORD = "from LoginTbl as login where login.password =:param1 and login.userName =:param2";
	public static final String GET_NETLIMS_LOGIN_BY_USERNAME = "from LoginTbl as login where TRIM(login.userName)=:param1";

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
	public static final String GET_NETLIMS_OWNER = "from LabTbl as lab where  lab.loginTbl.id=:param1";
	public static final String GET_NEW_BRANCHES = "from LabBranchTbl as branch where  branch.labTbl.id!=:param1 and branch.createDateTime>:param2 and branch.createDateTime<:param3 order by createDateTime";
	public static final String GET_UPDATED_BRANCHES = "from LabBranchTbl as branch where  branch.labTbl.id!=:param1 and branch.createDateTime< branch.updateDateTime and branch.updateDateTime>:param2 and branch.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_OWN_BRANCHES = "from LabBranchTbl as branch where  branch.labTbl.id=:param1 and branch.updateDateTime>:param2 and branch.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_NEW_LABS = "from LabTbl as lab where  lab.id!=:param1 and lab.createDateTime>:param2 and lab.createDateTime<:param3 order by createDateTime";
	public static final String GET_UPDATED_LABS = "from LabTbl as lab where lab.id!=:param1 and lab.createDateTime < lab.updateDateTime and lab.updateDateTime>:param2 and lab.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_OWN_LAB = "from LabTbl as lab where  lab.id=:param1 and lab.updateDateTime>:param2 and lab.updateDateTime<:param3 order by updateDateTime";
	public static final String GET_LAB_BY_LOGIN_ID = "from LabTbl as lab where  lab.loginTbl.id=:param1";
	public static final String GET_LAB_DETAILS_BY_TIME = "from LabTbl as lab where lab.id=:param1 and lab.updateDateTime >=:param2 and lab.updateDateTime<:param3";
	
	/* ResultTbl */
	public static final String GET_RESULT_BY_PATIENTID = "from ResultTbl as result where result.netmdPatientTbl.id=:param1";
	public static final String GET_RESULTS = "from ResultTbl as patientResults where patientResults.netmdTbl.id=:param1 and patientResults.netmdBranchTbl.id=:param2 and patientResults.updatedDateTime>:param3 and patientResults.updatedDateTime<:param4";
	public static final String GET_PATIENT_RESULT = "from ResultTbl as patientResults where patientResults.orderId=:param1 and patientResults.netmdBranchTbl.id=:param2 and patientResults.netmdTbl.id=:param3 and patientResults.netmdPatientTbl.id=:param4 and patientResults.labTbl.id=:param5 and patientResults.labBranchTbl.id=:param6";
	public static final String GET_PATIENT_RESULTS_BY_ORDERID ="from ResultTbl as patientResults where patientResults.netmdPatientTbl.id=:param1 and patientResults.orderId=:param2";
	
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
	//public static final String GET_LAST_UNIQUE_ID = "select MAX(u.uniqueId) from OrderTransferTbl as u"; -- wrong should use order branch tbl if this query is using
	public static final String GET_ORDERS = "from OrderTransferTbl as order where order.labTbl.id=:param1 and order.labBranchTbl.id=:param2 and order.updatedDateTime>=STR_TO_DATE(:param3, '%Y-%m-%d %H:%i:%s') and order.updatedDateTime<STR_TO_DATE(:param4, '%Y-%m-%d %H:%i:%s')";
	public static final String GET_DESTINATION_BRANCHES_BY_ORDER_ID="from OrderTransferTbl as orderTransfer where orderTransfer.orderBranchTbl.id=:param1";

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
	
	/*OrderBranchTbl*/
	public static final String GET_ORDER_BY_UID = "from OrderBranchTbl as order where order.orderUid=:param1";
	public static final String GET_ORDER_BY_UID_BRANCH = "from OrderBranchTbl as order where order.orderUid=:param1 and order.labBranchTbl.id=:param2";
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
	//public static final String GET_NAME_BY_AGE = " select (select qaTbl.answer from QusAnsTbl as qaTbl where qaTbl.questionTbl.questionKey='name' and qaTbl.caseTbl.id=q.caseTbl.id) from QusAnsTbl as q where q.questionTbl.questionKey='age' and q.answer=:param1";
	//public static final String GET_NAME_BY_AGE="select distinct qa1.answer,qa0.caseTbl.id,qa2.answer from(select distinct qa0.caseTbl.id  from QusAnsTbl as qa0) as q0 LEFT OUTER JOIN(select qa1.answer ,qa1.caseTbl.id from QusAnsTbl as qa1 where qa1.questionTbl.questionKey='name') as q1 ON q0.qa0.caseTbl.id = q1.qa1.caseTbl.id LEFT OUTER JOIN (select qa2.answer,qa2.caseTbl.id from QusAnsTbl as qa2 where qa2.questionTbl.questionKey='age') as q2 ON q1.qa1.caseTbl.id = q2.qa2.caseTbl.id where q2.qa2.answer> :param1";

	
/**************NetPos******************/

	
	/*NetPosLoginTbl */
	public static final String GET_NETPOS_LOGIN_BY_USERNAME = "from NetposLoginTbl as login where login.userName=:param1";
	
	/* NetposTbl */
	
	public static final String GET_NETPOS_BY_NAME = "from NetposTbl as netpos where  REPLACE(TRIM(UPPER(netpos.name)),' ','')=:param1";
	
	/*NetposUserTbl*/
	public static final String GET_NETPOS_USERS = "from NetposUserTbl as netposUsers where  netposUsers.NetposUserTbl.id=:param1";

	/*NetposBranchTbl*/
	public static final String GET_NETPOS_BRANCHES = "from NetposBranchTbl as netposBranch where  netposBranch.NetposTbl.id=:param1";
	
/********************Organizations**************/
	/*OrganisationLoginTbl*/
	public static final String GET_LOGIN_BY_ORGANISATION_OWNER_USERNAME = "from OrganisationLoginTbl as login where login.userName=:param1 and login.userType='owner'";
	public static final String GET_ORGANISATION_USER_BY_PASSWORD = "from OrganisationLoginTbl as login  where login.password =:param1 and login.userName =:param2";
	public static final String GET_ORGANISATION_LOGIN_BY_USERNAME = "from OrganisationLoginTbl as login where TRIM(login.userName)=:param1";
	
	
	/*OrganisationTbl*/
	public static final String GET_ORGANISATION_BY_NAME =  "from OrganisationTbl as orgnstion where  REPLACE(TRIM(UPPER(orgnstion.name)),' ','')=:param1";
	public static final String GET_ORGANISATION_OWNER = "from OrganisationTbl as orgn where  orgn.organisationLoginTbl.id=:param1";
	public static final String GET_ORGANISATION = "from OrganisationTbl as org";
	/*OrganisationUserTbl*/
	public static final String GET_ORGANISATION_USER_BY_EMAIL_AND_BRANCH = " from OrganisationUserTbl as orgUser where orgUser.email=:param1 and orgUser.organisationTbl.id=:param2";
	public static final String GET_USERS_BY_ORGANISATION_ID = " from OrganisationUserTbl as users where users.organisationTbl.id=:param1 ";
	public static final String GET_ORGANISATION_USER = "from OrganisationUserTbl as user where user.organisationLoginTbl.id= :param1";
	
	/*DepartmentTbl*/
	public static final String GET_DEPARTMENT_NAME_BY_ID = "select dept.name from DepartmentTbl as dept where dept.id=:param1";
	
	/*QuestionTbl*/
	public static final String GET_ID_BY_KEY = " from QuestionTbl as q where q.questionKey=:param1";
	public static final String GET_BY_DEPT = "from QuestionTbl as qTbl where qTbl.departmentTbl.id=:param1";
	public static final String GET_BY_QUESTIONNAIRE_ID = "from AnswerTbl as answer where answer.questionTbl.id=:param1";
	
	/*AnswerTbl*/
	public static final String GET_BY_CASE = "from AnswerTbl as qaTbl where qaTbl.caseTbl.id=:param1";
	public static final String GET_NAME_BY_AGE="select  distinct name,caseId,age from (select distinct case_id  from qus_ans_tbl) as q0 LEFT OUTER JOIN (select answer as name ,case_id as caseId from qus_ans_tbl where quest_id = 5) as q1 ON q0.case_id = q1.caseId LEFT OUTER JOIN (select answer as age,case_id as caseI from qus_ans_tbl where quest_id = 6) as q2 ON q1.caseId = q2.caseI where q2.age>24";

	/*OrderResultTbl*/
	public static final String GET_ORDER_TEST_RESULTS = "from OrderResultTbl as orderResult where orderResult.ownerLabBranchTbl.id=:param1 and orderResult.labBranchTbl.id!=:param1 and orderResult.updatedDateTime>=:param2 and orderResult.updatedDateTime<:param3 ";

	/*Netmd_question_tbl*/
	public static final String GET_NETMD_QUESTION_TBL = "from NetmdQuestionTbl";
	public static final String GET_BY_QUESTIONNAIRE = "from NetmdAnswerTbl as answer where answer.netmdQuestionnaireTbl.id=:param1";
	//public static final String GET_NETMD_BILL_DETAILS ="select sum(bill.billAmount) as billAmt,sum(bill.amountPaid) as amtPaid from NetmdBillTbl  as bill where bill.netmdBranchTbl.id=:param1 and bill.orderDate>=:param2 and bill.orderDate<=:param3";
	public static final String GET_NETMD_BILL_DETAILS ="from NetmdBillTbl  as bill where bill.netmdBranchTbl.id=:param1 and bill.orderDate>=:param2 and bill.orderDate<=:param3 and bill.billStatus!=:param4";
	
	/*netlims_order_tbl*/
	public static final String GET_NETLIMSORDER_BY_ORDERID_BRANCHID = "from NetlimsOrderTbl as order where order.orderId=:param1 and order.labBranchTbl.id=:param2";
	public static final String GET_PATIENT_RESULT_TBL = "from PatientResultTbl as result where result.netlimsPatientTbl.id=:param1 and result.netlimsOrderTbl.id=:param2";
	public static final String GET_RESULTBY_ORDERID_TESTUID = "from NetlimsResultTbl as result where result.netlimsOrderTbl.id=:param1 and result.testUid=:param2";
	public static final String GET_REFERRAL_RESULTBY_ORDERID = "from ReferralResultTbl as ref where ref.netlimsOrderTbl.id=:param1";
	
	/*DoctorTbl*/
	public static final String GET_REFERRAL_BY_EMAILID = "from DoctorTbl as doctor where doctor.email =:param1";
	public static final String GET_NETLIMS_PATIENT_BY_EMAIL = "from PatientTbl as patient where TRIM(patient.email) = :param1";
	public static final String GET_NETLIMSRESULT_BY_ORDERID_TESTID = "from NetlimsResultTbl as result where result.netlimsOrderTbl.id=:param1 and result.testUid=:param2 order by result.itemId";
	public static final String GET_ORDERFACILITY_BY_ORDERID = "from FacilityResultTbl as result where result.netlimsOrderTbl.id=:param1";
	public static final String GET_NETLIMS_PATIENT_BY_PATIENTID_BRANCHID = "from NetlimsPatientTbl as patient where patient.patientTbl.id=:param1 and patient.labBranchTbl.id=:param2";
	public static final String GET_NETLIMSRESULTS_BY_ORDERID = "from NetlimsResultTbl as result where result.netlimsOrderTbl.id=:param1 order by result.itemId";
	/*FacilityTbl*/
	public static final String GET_FACILITY_BY_EMAILID ="from LabFacilityTbl as facility where facility.email =:param1";
	public static final String GET_FACILITY_BY_UID_BRANCHID = "from LabFacilityTbl as facility where facility.uid=:param1 and facility.labBranchTbl.id=:param2";
	public static final String GET_FACILITY_BY_LOGINID = "from LabFacilityTbl as facility where facility.loginTbl.id=:param1";
	public static final String GET_USER_BY_REFERRALID = "from LabUserTbl as user where user.refUid=:param1 and user.labBranchTbl.id=:param2";
	/*OrdertransferTbl*/
	//public static final String GET_ORDER_COUNT ="SELECT SUM(order_count) FROM order_transfer_count_tbl where brach_id =:param1";
	public static final String GET_ORDER_COUNT ="SELECT SUM(orderCount) as orderCt FROM OrderTransferCountTbl as ordercounttbl where ordercounttbl.labBranchTbl.id =:param1 and ordercounttbl.date>=:param2 and ordercounttbl.date<=:param3";
	//public static final String GET_ORDER_COUNT ="SELECT SUM(orderCount) as orderCt FROM OrderTransferCountTbl as ordercounttbl where ordercounttbl.labBranchTbl.id =:param1";
	public static final String GET_ORDER_COUNT_FOR_SAVE="from OrderTransferCountTbl as ordercounttbl where ordercounttbl.labBranchTbl.id =:param1 and ordercounttbl.date =:param2 ";
	public static final String GET_LAB_ORDER_COUNT="SELECT SUM(orderCount) as orderCt FROM OrderTransferCountTbl as ordercounttbl where ordercounttbl.labBranchTbl.labTbl.id =:param1 and ordercounttbl.date>=:param2 and ordercounttbl.date<=:param3 ";
	public static final String GET_FACILITY_ORDER_COUNT="SELECT SUM(orderCount) as orderCt FROM OrderTransferCountTbl as ordercounttbl where ordercounttbl.labFacilityTbl.id =:param1 and ordercounttbl.date>=:param2 and ordercounttbl.date<=:param3 ";
	//public static final String GET_FACILITY_LIST="SELECT lab_facility_tbl.* FROM lab_facility_tbl LEFT JOIN branch_facility_tbl ON lab_facility_tbl.id = branch_facility_tbl.facility LEFT JOIN lab_branch_tbl ON branch_facility_tbl.branch = lab_branch_tbl.id WHERE lab_branch_tbl.id =325";
	public static final String GET_PAGESETTING_BY_NAME = "from PageSettingsTbl as setting where setting.keyName=:param1 and setting.labBranchTbl.id=:param2";
	public static final String GET_PAGESETTINGS = "from PageSettingsTbl as setting where setting.labBranchTbl.id=:param1";
	
	
	public static final String GET_DETAILS_FROM_LAB_BRANCH_TBL    = "from LabBranchTbl as id where labTbl.id = :param1"; 
	public static final String GET_DETAILS_FROM_NET_MD_BRANCH_TBL = "from NetmdBranchTbl as id where netmdTbl.id = :param1";
	public static final String GET_DETAILS_FROM_NET_RX_BRANCH_TBL = "from NetrxBranchTbl as id where netrxTbl.id = :param1";
	public static final String UPDATE_PUBLIC_KEY = "UPDATE HealthCareOrganisationTbl as key set key.publickey= :param1 where key.id= :param2";
	
	public static final String GET_HEALTH_CARE_DEATILS =  "from HealthCareOrganisationTbl as key where key.publickey = :param1";
	public static final String GET_BRANCH_DETAIL_BY_ID = "from HealthCareOrganisationTbl as branchId where branchId.id = :param1";
}