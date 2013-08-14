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
package com.nv.youNeverWait.pl.entity;

public enum ErrorCodeEnum {

	DatabaseError("1000","Database operation failed"),
	UserNameNull("1001","Please enter Username"),
	PasswordNull("1002","please enter password"),
	UserNull("1003","Invalid user name or password"),
	InvalidName("1004","Name should not be null"),
	InvalidMailId("1005","Email id is not valid"),
	InvalidPhoneFormat("1006","Invalid phone"),
	InvalidDateFormat("1007","Invalid date format"),
	InvalidFromToDate("1008","To date should be greater than From date"),
	DoctorExists("1009","Doctor already exists"),
	UserExists("1010","User already exists"),
	NetMdNull("1012","Given netMd id {id} does not match"),
	InvalidGlobalId("1013","Invalid  Global Id"),
	InvalidActionType("1014","Invalid action type"),
	DoctorNotFound("1015","Unknown doctor with id {id}"),
	ExperienceNotExist("1016","Requested  experience not exist"),
	QualificationNotExist("1017","Requested  qualification not exist"),
	GlobalIdNull("1018","Given global id{id} does not exists"),
	InvalidDoctor("1019","Doctor with netmdId {id} does not exist"),
	PatientExists("1020","Patient {name} already exists"),
	InvalidId("1021","Invalid Id"),
	UserTypeNull("1022","User type should not be null"),
	UserNameExists("1023","User with name {name} already exists"),
	UserNotExists("1024","User does  not exists"),
	NetMdIdNull("1025","Netmd Id should not be null"),
	PassPhraseNull("1026","Passphrase should not be null"),
	Unknowndoctor("1027","Given doctor does not exist in netmd id {id}"),
	InvalidUserName("1028","You must enter valid user name"),
	InvalidPassword("1029","You must enter password"),
	InvalidOwnerName("1030","You must enter valid owner name"),
	InvalidOwnerEmail("1031","You must enter valid owner email"),
	InvalidHeadOfficeName("1032","You must enter valid head office name"),
	InvalidHeadOfficeEmail("1033","You must enter valid email id for head office"),
	NetLimsAccountAlreadyExists("1034","There is another netlims account with given username"),
	InvalidLab("1035","There is no lab with id {id}"),
	InvalidBranchName("1036","You must enter valid branch name"),
	InvalidBranchId("1037","Given branch id is invalid"),
	BranchListEmpty("1038","Given branch id list is empty"),
	InvalidUserBranch("1039","There is no record with userId {userId} and branchId {branchId}"),
	UserIdNull("1040","User with id {id} does not exist"),
	InvalidUserType("1041","Invalid user type"),
	BranchNameExists("1042","Branch with name {name} already exists"),
	InvalidSortBy("1043","Invalid sort by field {sortBy}"),
	InvalidFilterExpName("1044","Invalid filter name {propertyName}"),
	InvalidFilterExpOperator("1045","Invalid  operator {operator} for expression having name {propertyName}"),
	InvalidFilterExpValue("1046","Invalid value for expression having name {propertyName}"),
	NetMdAccountAlreadyExists("1047","There is another netmd account with given username"),
	InvalidNetMd("1048","There is no netmd with id {id}"),
	ReplaceMacMsg("1049","Do you want to replace the Mac Id?"),
	MacIdNull("1050","Mac Id should not be null"),
	InvalidPassphrase("1051","Given passPhrase id is invalid"),
	PatientNotFound("1052","Unknown patient with id {id}"),
	NoScheduleFound("1053","No schedule found with the id {id}"),
	ScheduleNotExist("1054","Schedule not exist"),
	AppointmentAlreadyExist("1055","Appointment already exist"),
	InvalidSchedule("1056","Invalid schedule"),
	DoctorIdNotNull("1057","Doctor is mandatory"),
	ScheduleIdNotNull("1058","Schedule is mandatory"),
	InvalidTimeFormat("1059","Invalid time format"),
	InvalidAppointment("1060","Invalid appointment"),
	StartDateGreater("1061","Start date is greater than end date"),
	SeriesNotExist("1062","Series does not exist"),
	NoAppointmentFound("1063","No appointment found with the id {id}"),
	NobranchExists("1064","No such branch exists"),
	PassPhraseNotExist("1065","There is no record with given PassPhrase for this branch"),
	
	/*
	 * netlims Errors 
	 */
	InvalidSourceLab("500","The source lab id {id} you given is incorrect"),
	InvalidDestinationLab("501","The destination lab id {id} you given is incorrect"),
	InvalidSourceBranch("502","The source branch id {id} you given is incorrect"),
	InvalidDestinationBranch("503","The destination branch id {id} you given is incorrect"),
	EmptyResult("504","Invalid result"),
	InvalidOrderUid("505","Invalid orderUid"),
	InvalidLabId("506","There is no lab with id {id}"),
	InvalidBranch("507","There is no branch with id {id}"),
	InvalidSyncTime("508","Invalid synchonization time"),
	ImageCreationFailed("509","Captcha generation failed"),
	InvalidSecretCode("510","You must give valid secret code"),
	InvalidVerificationCode("511","You must give valid verification code"),
	UserLoginNull("512","User with login id {id} does not exist"),
	EmailSendFailed("513","Email sending operation failed"),
	LabNameExists("514","Lab with name {name} already exists"),
	PasswordNotExists("515","Password does not exists"),
	InvalidBranchStatus("516","You must give valid branch status"),
	InvalidNetLimsAccount("517","You are using invalid netlims account"),
	UnAuthorized("518","You are not authorized to sent result"),
	UserBranchNull("519","Cannot find users for this branch"),
	NoLabExists("520","No lab exists corresponding to this login name {name}"),
	NoUserExists("521","There is no user with this user name {name}"),
	InvalidDestinationNetMd("522","The destination NetMd id {id} you given is incorrect"),
	InvalidDestinationNetMdBranch("523","The destination  NetMd branch id {id} you given is incorrect"),
	InvalidStatus("524","Invalid status"),
	InvalidUser("525","You are not a valid user"),
	InvalidLabName("526","You must enter valid lab name"),
	InvalidMobileFormat("527","Invalid mobile number format"),
	DoctorEmailNull("528","Doctor mail id is not given"),
	InvalidTransfer("529","Cannot transfer results to any other lab"),
	DateNull("530","Please select From and To date"),
	FromDateNull("531","From date is not selected"),
	ToDateNull("532","To date is not selected"),
	OrderedTimeNull("533","Pease give last ordered time"),
	HardDiskSizeNull("534","Hard disk space used is not given"), 
	MemorySizeNull("535","Memory space used is not given"),
	CpuUsageNull("536","CPU usage is not given"), 
	FrequencyNull("537","Frequency is not given"),
	IntervalTimeNull("538","Interval time is not given"),
	InvaliFrequencyType("539","Invalid frequency type"),
	
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
	MacIdExists("1140","You have already installed the application using this passPhrase {passPhrase}"),
	SyncTimeNull("1141","Synchronization time should be given"),
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
	OrderDateNull("1159","Order date should be given"),
	EmptyPatientList("1160","The patient list is empty"),
	PatientResultEmpty("1161","There is no result for this patient with orderId{id}"),
	
	
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
