/**
 * ErrorCodeEnum.java
 * 
 * @Author Asha Chandran
 *
 * Dec 21, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.NetlimsPortal.pl.entity;

public enum ErrorCodeEnum {
	
/*
 * YouNeverWait errors
 */
	DatabaseError("1000","Database operation failed"),
	InvalidName("1001","Name should not be null"),
	InvalidMailId("1002","Email id is not valid"),
	InvalidPhoneFormat("1003","Invalid phone"),
	UserNull("1004","Invalid user name or password"),
	UserNameNull("1005","Please enter Username"),
	PasswordNull("1006","please enter password"),
	InvalidSortBy("1007","Invalid sort by field {sortBy}"),
	InvalidFilterExpName("1008","Invalid filter name {propertyName}"),
	InvalidFilterExpOperator("1009","Invalid  operator {operator} for expression having name {propertyName}"),
	InvalidFilterExpValue("1010","Invalid value for expression having name {propertyName}"),
	NetLimsAccountAlreadyExists("1011","There is another netlims account with given username"),
	NetMdAccountAlreadyExists("1012","There is another netmd account with given username"),
	ReplaceMacMsg("1013","Do you want to replace the Mac Id?"),
	ImageCreationFailed("1015","Captcha generation failed"),
	InvalidSecretCode("1016","You must give valid secret code"),
	InvalidVerificationCode("1017","You must give valid verification code"),
	InvalidSyncFreqType("1018","Invalid synchronization frequency type"),
	NoFileSelected("1019","There is no file selected for config data loading"),
	SynctimeExceeds("1020","Synchronization time interval should not exceed the head's time."),
	//DoctorExists("1009","Doctor already exists"),
	//NetMdNull("1012","Given netMd id {id} does not match"),
	
/*
 * Common Errors
 */
	 PassPhraseNull("1200","Passphrase should not be null"),
	 InvalidPassphrase("1201","Given passPhrase id is invalid"),
	 MacIdNull("1202","Mac Id should not be null"),
	 MacIdExists("1203","You have already installed the application using this passPhrase {passPhrase}"),
	 InvalidId("1204","Invalid Id"),
	 InvalidGlobalId("1205","Invalid  Global Id"),
	 GlobalIdNull("1206","Given global id{id} does not exists"),
	 InvalidActionType("1207","Invalid action type"),
	 InvalidDateFormat("1208","Invalid date format"),
	 InvalidSyncTime("1209","Invalid synchonization time"),
	 InvalidFromToDate("1210","To date should be greater than From date"),
	 InvalidBranchName("1211","You must enter valid branch name"),
	 InvalidBranchId("1212","Given branch id is invalid"),
	 BranchListEmpty("1213","Given branch id list is empty"),
	 BranchNameExists("1214","Branch with name {name} already exists"),
	 UserExists("1215","User already exists"),
	 UserLoginNull("1216","User with login id {id} does not exist"),
	 UserTypeNull("1217","User type should not be null"),
	 UserNameExists("1218","User with name {name} already exists"),
	 UserNotExists("1219","User does  not exists"),
	 UserIdNull("1220","User with id {id} does not exist"),
	 InvalidUserType("1221","Invalid user type"),
	 InvalidUserName("1222","You must enter valid user name"),
	 InvalidPassword("1223","You must enter password"),
	 InvalidOwnerName("1224","You must enter valid owner name"),
	 InvalidOwnerEmail("1225","You must enter valid owner email"),
	 InvalidHeadOfficeName("1226","You must enter valid head office name"),
	 InvalidHeadOfficeEmail("1227","You must enter valid email id for head office"),
	 InvalidUserBranch("1228","There is no record with userId {userId} and branchId {branchId}"),
	 EmailSendFailed("1229","Email sending operation failed"),
	 SyncTimeNull("1230","Synchronization time should be given"),
	 PassPhraseNotExist("1231","There is no record with given PassPhrase for this branch"),
	 SyncDisable("1232","Sync disbled"),
	 SyncFreqNull("1233","Synchronization frequecy type should be given"),
	 SyncIntervalTimeNull("1234","Synchronization interval time should be given"),
	 SyncFreqMissMatch("1235","Synchronization interval time has been changed.Please reset your synchronization time and sync later."),
/*
 * netlims Errors 
 */
	InvalidNetLimsAccount("500","You are using invalid netlims account"),
	InvalidLab("501","There is no lab with id {id}"),
	InvalidLabId("502","There is no lab with id {id}"),
	InvalidSourceLab("503","The source lab id {id} you given is incorrect"),
	InvalidDestinationLab("504","The destination lab id {id} you given is incorrect"),
	InvalidSourceBranch("505","The source branch id {id} you given is incorrect"),
	InvalidDestinationBranch("506","The destination branch id {id} you given is incorrect"),
	InvalidBranch("507","There is no branch with id {id}"),
	InvalidLabName("508","You must enter valid lab name"),
	InvalidMobileFormat("509","Invalid mobile number format"),
	LabNameExists("510","Lab with name {name} already exists"),
	InvalidBranchStatus("511","You must give valid branch status"),
	NoLabExists("512","No lab exists corresponding to this login name {name}"),
	InvalidSourceLabBranch("513","Wrong source lab id or source branch Id"),
	InvalidDestinationLabBranch("514","Wrong destination lab id or destination branch id"),
	SourceDestinationBranchSame("515","Source and destination branch is same"),
	BranchCodeNull("516","There is no branch code"),
	LabCodeNull("517","There is no lab code"),
	UnAuthorized("518","You are not authorized to sent result"),	
	InvalidStatus("519","Invalid status"),
	InvalidUser("520","You are not a valid user"),
	UserBranchNull("521","Cannot find users for this branch"),
	PasswordNotExists("522","Password does not exists"),
	NoUserExists("523","There is no user with this user name {name}"),
	
	DateNull("524","Please select From and To date"),
	FromDateNull("525","From date is not selected"),
	ToDateNull("526","To date is not selected"),
	OrderedTimeNull("527","Pease give last ordered time"),
	HardDiskSizeNull("528","Hard disk space used is not given"), 
	MemorySizeNull("529","Memory space used is not given"),
	CpuUsageNull("530","CPU usage is not given"), 
	FrequencyNull("531","Frequency is not given"),
	IntervalTimeNull("532","Interval time is not given"),
	InvaliFrequencyType("533","Invalid frequency type"),
	HardDiskSpaceNull("534","Total hard disk space is not given"), 
	MemorySpaceNull("541","total memory space is not given"), 
	CpuSpaceNull("542","Total cpu space is not given"), 
	BranchSystemInfoNull("543","Default system information for the branch id {id} does not exist"),
	SystemMonitorDetailsNull("545","There is no system health monitor details"),
	CriticalCpuLevelNull("546","Critical CPU level is not given"),
	CriticalHardDiskSPaceLevelNull("547","Critical hard disk space level is not given"),
	CriticalMemoryLevelNull("548","Critical memory level not given"),

	SpecimenUidNull("550","Specimen {uid} is null"),
	TestCodeNull("551","Test {uid} is null"),
	InvalidSpecimenName("552","Invalid specimen name"),
	SpecimenNameExists("553","Specimen with name {name} already exists"),
	InvalidSpecimenUid("554","Specimen uid is invalid"),
	SpecimenCantDelete("555","Unable to delete specimen.Its already in use"),
	InvalidTestName("556","Invalid test name"),
	TestNameExists("557","Test with name {name} already exists"),
	AbbreviationExists("558","Test with abbreviation {name} already exists"),
	InvalidTestUid("559","Test uid is invalid"),
	SpecimenExist("560","Test with specimenUid {SpecimenUid} already exist"),
	SpecimenNotExist("561","Test with specimenUid {SpecimenUid} does not exist"),
	InvalidTest("562","Test with uid {TestUid} does not exist"),
	NullTestUid("563","Test with uid {TestUid} does not exist"),
	
	DoctorEmailNull("564","Doctor mail id is not given"),
	EmptyResult("565","Invalid result"),
	InvalidTransfer("566","Cannot transfer results to any other lab"),
	InvalidOrderUid("567","Invalid orderUid"),
	OrderTypeNull("568","Nomenclature order types given empty."),
	OrderDetailsNull("569","No order details to transfer"),
	OrderDateNull("570","Order date should be given"),
	OrderUidNull("571","Order Uid is empty"),
	OrderTransferException("572","Order transfer exception"),
	//InvalidDestinationNetMd("571","The destination NetMd id {id} you given is incorrect"),
	//InvalidDestinationNetMdBranch("572","The destination  NetMd branch id {id} you given is incorrect"),
/*
 * netMd Errors 
 */
	
	BranchMissMatch("1100","Given branch id {id} does not match"),
	NetMdMissMatch("1101","Given netMd id {id} does not match"),
	PatientBranchNull("1102","Patient with id {id} does not exist in the NetMd branch"),
	MacStatusNull("1103","Mac id does not exist"),
	LoginNotExists("1104","There is no login with userName {name}"),
	PatientDetailNull("1105","Patient details null"),
	InvalidPatientName("1106","Patient name does not exist"),
	PatientListEmpty("1107","There is no patients with name {name}"),
	DuplicateNetMd("1108","Netmd with name {name} already exists"),
	InvalidNetMdName("1109","You must enter valid name for netmd"),
	InvalidNumberOfDevices("1110","You must give how many number of devices each branch supports"),
	InvalidNetMdAccount("1111","You are using invalid netmd account"),
	NoNetmdExists("1112","No  Netmd created or updated after last synchronization time"),
	InvalidNetMdBranchId("1113","Given netMd branch id is invalid"),
	InvalidEmail("1114","You must enter valid email id"),
	InvalidNetMdUserName("1115","You must enter valid name for user"),
	NetMdUserAlreadyExists("1116","User with email {email} already exists!"),
	NetMdNameExists("1117","NetMd with name {name} already exists!"),
	InvalidHeader("1118","You must give valid header details"),
	InvalidUserDetail("1119","You must give valid user details"),
	NetMdUserAccountAlreadyExists("1120","User with username {user} already exists"),
	InvalidEndDate("1121","Invalid end date"),
	InvalidOccuranceType("1122","Invalid occurance type"),
	InvalidRepeatType("1123","Invalid repeat type"),
	InvalidNetMdId("1124","You must give valid netmd id"),
	ScheduleDetailNull("1125","There is no details for creating schedule"),
	HeaderNull("1126","Header details should not be null"),
	ScheduleStartDateNull("1127","Schedule start date should not be null"),
	ScheduleStartTimeNull("1128","Schedule start time should not be null"),
	ScheduleEndTimeNull("1129","Schedule end date should not be null"),
	ScheduleStatusNull("1130","Schedule status not given"),
	InvalidDoctorId("1131","Invalid doctor Id"),
	ScheduleSeriesNull("1132","Schedule series should not be null"),
	OccuranceTypeNull("1133","Occurance type should not be null"),
	RepeatTypeNull("1134","Repeat mode should not be null"),
	NetMdIdInvalid("1135","Given Netmd id is invalid"),
	InActiveNetmdAccount("1136","You are using inactive Netmd account"),
	InvalidSeriesId("1137","Invalid  schedule series id"),
	InvalidNetMdUser("1138","User with id {id} does not exists in this netmd branch"),
	DoctorLoginNull("1139","No login provided for this doctor {name}"),
	
	EmailNull("1142","Email id should not be null"),
	InvalidAchievement("1143"," Doctor achievements should not be null"),
	InvalidMemberShip("1144","Doctor memberships should not be null"),
	InvalidExpertise("1145","Doctor expertise should not be null"),
	DoctorEmailExists("1150","Doctor with this mail id {email} already exists"),
	LoginExists("1151","Username and Password already exists"),
	InvalidUserId("1152","Invalid format of user id"),
	InvalidFromTime("1153","Schedule start time should not be greater than end time"),
	ScheduleAlreadyExist("1154","Schedule with date {date} already exist"),
	InvalidLocalSeriesId("1155","Invalid series id"),
	InvalidDoctorLogin("1156","Doctor with login id {id} does not exist"),
	LoginNotNull("1157","Login details should not be null"),
	DoctorListEmpty("1158","There is no doctors with Email Id  {email}"),
	EmptyPatientList("1160","The patient list is empty"),
	PatientResultEmpty("1161","There is no result for this patient with orderId{id}"),
	NobranchExists("1162","No such branch exists"),
	StartDateGreater("1163","Start date is greater than end date"),
	SeriesNotExist("1164","Series does not exist"),
	InvalidTimeFormat("1165","Invalid time format"),
	NoAppointmentFound("1166","No appointment found with the id {id}"),
	InvalidAppointment("1167","Invalid appointment"),
	PatientNotFound("1168","Unknown patient with id {id}"),
	NoScheduleFound("1169","No schedule found with the id {id}"),
	ScheduleNotExist("1170","Schedule not exist"),
	AppointmentAlreadyExist("1171","Appointment already exist"),
	InvalidSchedule("1172","Invalid schedule"),
	DoctorIdNotNull("1173","Doctor is mandatory"),
	ScheduleIdNotNull("1174","Schedule is mandatory"),
	InvalidNetMd("1175","There is no netmd with id {id}"),
	Unknowndoctor("1176","Given doctor does not exist in netmd id {id}"),
	NetMdIdNull("1177","Netmd Id should not be null"),
	PatientExists("1178","Patient {name} already exists"),
	InvalidDoctor("1179","Doctor with netmdId {id} does not exist"),
	ExperienceNotExist("1180","Requested  experience not exist"),
	QualificationNotExist("1181","Requested  qualification not exist"),
	DoctorNotFound("1182","Unknown doctor with id {id}"),
	NetmdBranchSystemInfoNull("1183","Default system information for this device does not exist"),
	
	/*netRx*/
	InvalidNetRxName("2000","You must enter valid name for netmd"),
	NetRxAccountAlreadyExists("2001","There is another netrx account with given username"),
	DuplicateNetRx("2002","Netmd with name {name} already exists"), 
	InvalidOwnerPhoneFormat("2003","Invalid owner phone format"), 
	InvalidOwnerMobileFormat("2004","Invalid owner mobile format"), 
	InvalidHeadOfficePhoneFormat("2005","Invalid head office phone format"),
	InvalidHeadOfficeMobileFormat("2006","Invalid head office mobile format"), 
	InvalidNetRx("2007","There is no netrx with id {id}"), 
	NetRxNameExists("2008","NetRx with name {name} already exists!"), 
	NetRxUserAlreadyExists("2009","User with email {email} already exists!"),
	NetRxUserAccountAlreadyExists("2010","User with username {user} already exists"),
	InvalidNetRxAccount("2011","You are using invalid netRx account"),
	InvalidNetRxBranchId("2012","Given netRx branch id is invalid"),
	InvalidNetRxUser("2013","User with id {id} does not exists in this netrx branch"),
	InvalidNetRxUserName("2014","Please enter valid user name"),
	NetRxIdNull("2015","Netrx Id should not be null"),
	  
	
	/*netPos*/
	
	
	InvalidnetPosName("3000","You must enter valid pos name"),
	netPosAccountAlreadyExists("3001","There is another netpos account with given username"),
	DuplicateNetPos("3002","Netpos with name {name} already exists"),
	InvalidnetPos("3003","There is no netmpos with id {id}"),
	netPosNameExists("3004","NetPos with name {name} already exists!"),
	InvalidNetPosId("3005","You must give valid netpos id"),
	
	/*Organisations*/
	InvalidOrganisationName("4000","You must give valid  organization name"), 
	DepartmentTypeEmpty("4001","No department type selected"),
	InvalidDepartmentType("4002","Invalid department type"),
	OrganisationAlreadyExists("4003","There is another organisation with given username"),
	DuplicateOrganisationName("4004","organisation  name {name} already exists"),
	InvalidOrganisation("4005","There is no organisation with id {id}"),
	OrganisationNameExists("4006","Organisation with name {name} already exists!"), 
	UserAlreadyExists("4007","User with name {name} already exists!"),
	InvalidPatientType("4008","Invalid patient type"),
	InvalidDepartmentId("4009","Invalid department id"),
	InvalidCaseId("4010","Invalid case id"),
	InvalidPatientId("4011","Invalid patient id"), 
	InValidCaseName("4012","Case name is empty "), 
	InvalidCaseStatus("4013","Invalid case status"),
	ReportNull("4014","Invalid report name"),
	InvalidStartMonth("4015","Start Month should not be greater than End Month"),
	InvalidStartYear("4016","Start Month should not be greater than End Month"),
	;
	private String errCode;
	private String errMsg;


	private ErrorCodeEnum(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
