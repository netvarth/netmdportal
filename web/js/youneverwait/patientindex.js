var ajaxProcessor=new ServerUrlProcessor();
dataTableProcessor = new DataTableProcessor();
constants = new Constants();
pageHandler = new PageHandler();
commonMethodInvoker = new CommonMethodInvoker();
validator = new Validator();
var notifier = new Notifier();
var errorHandler = new ErrorHandler();
//var methodInvoker = new MethodInvoker();
var query = new Query();
$(function() {
	$("#BeeperBox").mouseenter(notifier.stopHide).mouseleave(notifier.startHide);
    $('.beeper_x').click(function () {
        $("#BeeperBox").hide();
    });
	var patientHome = new PatientHome();
	patientHome.init();
});
function Constants() {
	this.showMyName = function() {
		return "Constants";
	}
	this.DAY = 'Day';
	this.MONTH='Month';
	this.WEEK="Week";
	this.PATIENTSELECTCONTROLNAME = '#select';
	this.DATACONTAINERCONTROLNAME = '#tabs-1';
	this.RIBBONDATACONTROLNAME = '.global-bar-tab .controlB';
	this.GETUSERURL = '/youNeverWait/ynw/auth/user';
	this.USERLOGOUTURL = '/youNeverWait/ynw/auth/logout';
	this.PATIENTSLISTBYEMAILURL = '/youNeverWait/ynw/ui/patient/list/';
	this.PATIENT_TD_WK_FT_APPOINTMENTSURL="/youNeverWait/ynw/ui/patient/appointments/";
	this.PATIENTPASTAPPOINTMENTSURL = '/youNeverWait/ynw/ui/patient/getPastAppointments';
	this.PATIENTRESULTSURL = "/youNeverWait/netlims/ui/order/patient/getByFilter";
	this.DOCTORUSINGBRANCHIDURL='/youNeverWait/ynw/ui/patient/listDoctors/';
	this.RIBBONJSONURL = '/youNeverWait/json/netlims/toolbar/ynwGlobalToolbar.json';
	this.APPOINTMENTACCORDIONJSONURL = '/youNeverWait/json/netlims/appointmentAccordion.json';
	this.PAGETITLE = '#pageTitle';
	this.APPOINTMENTLISTCAPTION = "Appointments";
	this.PASTAPPOINTMENTSTABLEURL='/youNeverWait/json/netlims/pastAppointments.json';
	this.RESULTSTABLEURL = '/youNeverWait/json/netlims/orderTable.json';
	this.CLINICSTABLEURL='/youNeverWait/json/netlims/clinicsTable.json';
	this.APPOINTMENTPAGE = "Appointments";
	this.CLINICSPAGE = 'Clinics';
	this.RESULTPAGE='Results';
	this.CLINICS = 'Clinics';
	this.CLINICSPAGETBURL='/youNeverWait/json/netlims/toolbar/patientappointmentToolbar.json';
	this.SCHEDULEDAYVIEWURL = '/youNeverWait/netmd/ui/schedule/dayView/';
	this.SCHEDULEMONTHVIEWURL = '/youNeverWait/netmd/ui/schedule/monthlyView/';
	this.NEWAPPOINTMENTPAGEURL='/youNeverWait/json/new/newAppointment.json';
	this.APPOINTMENTMODALNAME='appointmentModal';
	this.CREATEPORTALAPPOINTMENTURL='/youNeverWait/ynw/ui/patient/createAppointmentFromPortal';
	this.DELETEPORTALAPPOINTMENTURL='/youNeverWait/ynw/ui/patient/deleteAppointmentFromPortal/';
	this.CONFIRMDELETEAPPOINTMENT = "Do you really want to delete this appointment?";
	this.CHANGEPASSWORDPAGEURL='/youNeverWait/json/netlims/changePassword.json';
	this.CHANGEPASSWORDMODALNAME='changePasswordModal';
	this.CHANGEPASSWORDURL = '/youNeverWait/ynw/auth/changePassword';
	this.FIELDREQUIRED = 'Field required';
	this.PASSWORDNOTMATCH = "Password doesn't match";
}
function Query() {
	this.showMyName = function() {
		return "Query";
	}
	this.getRibbonContent = function(){
		ajaxProcessor.setUrl(constants.RIBBONJSONURL);
		return ajaxProcessor.get();
	}
	this.getAccordionJson = function(path){
		ajaxProcessor.setUrl(path);
		return ajaxProcessor.get();
	}
	this.getUser = function() {
		ajaxProcessor.setUrl(constants.GETUSERURL);
		return ajaxProcessor.get();	
	}
	this.logout = function() {
		ajaxProcessor.setUrl(constants.USERLOGOUTURL);
		return ajaxProcessor.get(); 
	}
	this.getPatientsByEmail = function(user_email) {
		ajaxProcessor.setUrl(constants.PATIENTSLISTBYEMAILURL+user_email+"/");
		return ajaxProcessor.get();
	}
	this.getAppointments_TD_WK_FT = function(patientId) {
		ajaxProcessor.setUrl(constants.PATIENT_TD_WK_FT_APPOINTMENTSURL + patientId);
		return ajaxProcessor.get();
	}
	this.getPastAppointments = function(param) {
		ajaxProcessor.setUrl(constants.PATIENTPASTAPPOINTMENTSURL);
		return ajaxProcessor.post(param);
	}
	this.getDoctorsUsingBranchId =function(branchId) {
		ajaxProcessor.setUrl(constants.DOCTORUSINGBRANCHIDURL+branchId);
		return ajaxProcessor.get();
	}
	this.getScheduleDayView = function(param) {
		ajaxProcessor.setUrl(constants.SCHEDULEDAYVIEWURL+param);
		return ajaxProcessor.get();
	}
	this.getScheduleMonthView = function(param) {
		ajaxProcessor.setUrl(constants.SCHEDULEMONTHVIEWURL+param);
		return ajaxProcessor.get();
	}
	this.createAppointmentFromPortal = function(appointment){
		ajaxProcessor.setUrl(constants.CREATEPORTALAPPOINTMENTURL);
		return ajaxProcessor.post(appointment);
	}
	this.deleteAppointmentFromPortal = function(appointmentId) {
		ajaxProcessor.setUrl(constants.DELETEPORTALAPPOINTMENTURL+appointmentId);
		return ajaxProcessor.get(); 
	}
	this.changePassword = function(passwordInfo){
		ajaxProcessor.setUrl(constants.CHANGEPASSWORDURL);
		return ajaxProcessor.post(passwordInfo);
	}
}
function User() {
	this.showMyName = function() {
		return "User";
	}
	this.getUser = function(){
		return this.user;
	}
	this.setUser = function(user){
		this.user = user;
	}
	this.setInfo = function() {
		var user = query.getUser();
		this.setUser(user);
		this.setName(user.name);
		$('#userName').html(user.name);
	}
	this.getName = function() {
		return this.name;
	}
	this.setName = function(name){
		this.name = name;
	}
	this.bindEvents = function() {
		$(".headright #dropdownOne #dd").die('click').live("click",function() {
			$(this).toggleClass('active');
	    });
		$("#btnLogout").die('click').live("click",function() {
			var response = query.logout();
			if(response.success==true)
				location.reload();
		});

	}
}

function GlobalToolBarProcessor() {
	this.showMyName = function() {
		return "GlobalToolBarProcessor";
	}
	this.generateRibbonBar=function(globalTbInfo) {
		var globalTB = new GlobalToolBar(globalTbInfo);
		$(constants.RIBBONDATACONTROLNAME).html(globalTB.result);	
	}
}

function AccordionGenerator() {
	this.showMyName = function() {
		return "AccordionGenerator";
	}
	this.load=function(parent, path, accordionPath) {
		var layoutInfo = query.getAccordionJson(path);
		var layout = this.generate(layoutInfo);
		$(parent).html(layout);
		$( accordionPath ).accordion();
	}
	this.generate = function(layout, accordionPath) {
		var layoutStructure = $('<div id=' + accordionPath + '/>');

		$(layout.list).each(function(index, item){
			var header = $('<h3/>');
			header.attr('id',item.id + "_header");
			header.html(item.h3_caption);
			layoutStructure.append(header);

			var content = $('<div/>');
			content.html("&nbsp;");
			content.attr('id',item.id + "_content");
			layoutStructure.append(content);
		});
		return layoutStructure;
	}
}

function TabGenerator() {
	this.showMyName = function() {
		return "TabGenerator";
	}
	this.load=function(parent, path) {
		var layoutInfo = query.getAccordionJson(path);
		var layout = this.generate(layoutInfo);
		$(parent).html(layout);
		$( "#tabs_appointments" ).tabs();
	}
	this.generate = function(layout) {
		var layoutStructure = $('<div id="tabs_appointments"/>');
		var contentlist = $('<div/>');
		var tabHeaders = $('<ul/>');
		$(layout.list).each(function(index, item){
			var headerlist = $('<li/>');
			var header = $('<a/>');
			header.attr('href', "#" + item.id + "_content");
			header.attr('id',item.id + "_header");
			header.html(item.h3_caption);
			headerlist.append(header);
			tabHeaders.append(headerlist);
			var content = $('<div/>');
			content.attr('id',item.id + "_content");
			contentlist.append(content);
		});
		layoutStructure.append(tabHeaders);
		layoutStructure.append(contentlist.html());
		return layoutStructure;
	}
}

function PatientHome () {
	this.showMyName = function() {
		return "PatientHome";
	}
	this.setUser=function(user){
		this.user = user;
	}
	this.getUser = function(){
		return this.user;
	}
	this.ribbonAppointments = '#ribbonAppointments';
	this.ribbonResults = '#ribbonResults';
	this.ribbonClinics = '#ribbonClinics';
	this.init = function() {
		var user = new User();
		user.setInfo();
		user.bindEvents();
		this.setUser(user);
		var patientProcessor = new PatientProcessor(this);
		patientProcessor.init();
		var list=patientProcessor.list(user.name);
		this.setPatientsListToControl(list, constants.PATIENTSELECTCONTROLNAME);

		var ribbonContent = query.getRibbonContent();
		var ribbonGenerator = new GlobalToolBarProcessor();
		ribbonGenerator.generateRibbonBar(ribbonContent);
		this.bindEvents();

		$(this.ribbonAppointments).trigger('click');
		
	}
	this.getPatientId = function() {
		return $(constants.PATIENTSELECTCONTROLNAME).val().split("_")[0];
	}
	this.getPatientEmail = function() {
		return $(constants.PATIENTSELECTCONTROLNAME).val().split("_")[1];
	}
	this.getSelectedPatient = function() {
		return $(constants.PATIENTSELECTCONTROLNAME + " :selected").text().split(" ")[0];
	}
	this.getSelectedPatientName = function() {
		return $(constants.PATIENTSELECTCONTROLNAME + " :selected").text();
	}
	this.bindEvents = function() {
		var self=this;
		var appointmentListProcessor;
		var clinicsProcessor;
		var resultsProcessor;
		$(self.ribbonAppointments).click(function(){
			if(pageHandler.getActivePage!=constants.APPOINTMENTPAGE){
				appointmentListProcessor = new AppointmentListProcessor(self);
				pageHandler.setActivePage(constants.APPOINTMENTPAGE);
			}
			appointmentListProcessor.init();
		});
		$(self.ribbonClinics).click(function() {
			if(pageHandler.getActivePage!=constants.CLINICSPAGE){
				clinicsProcessor = new ClinicsProcessor(self);
				pageHandler.setActivePage(constants.CLINICSPAGE);
			}
			clinicsProcessor.init();
		});
		$(self.ribbonResults).click(function(){
			if(pageHandler.getActivePage!=constants.RESULTPAGE){
				resultsProcessor = new ResultsProcessor(self);
				pageHandler.setActivePage(constants.RESULTPAGE);
			}
			resultsProcessor.init();
		});
		$(constants.PATIENTSELECTCONTROLNAME).change(function() {
			if(pageHandler.getActivePage!=constants.APPOINTMENTPAGE){
				appointmentListProcessor = new AppointmentListProcessor(self);
				pageHandler.setActivePage(constants.APPOINTMENTPAGE);
			}
			appointmentListProcessor.init();
		});
	}
	this.setPatientsListToControl = function(list, controlName) {
		$(list.patientList).each(function (index, patient) {
			var value=patient.id+'_'+patient.email+'_'+patient.branchId;
			var lastName;
			if(patient.lastName==null){lastName="";}
			else{lastName=patient.lastName;}
			var patientName=patient.firstName+" "+lastName;
			patientName = patientName.toLowerCase().replace(/\b[a-z]/g, function(letter) {
				return letter.toUpperCase();
			});
			$(controlName).append('<option  value="'+value+'">'+patientName+'</option>');
		});
	}
}
function ChangePasswordProcessor(patientProcessor) {
	this.patientProcessor = patientProcessor;

	this.getPatientProcessor = function() {
		return this.patientProcessor;
	}

	this.getPatientHome = function() {
		var patientProcessor = this.getPatientProcessor();
		return patientProcessor.getParent();
	}
	this.currentPage = '#'+constants.CHANGEPASSWORDMODALNAME;
	this.oldPassword=this.currentPage + ' ' +'#oldPassword';
	this.newPassword=this.currentPage + ' '+ '#newPassword';
	this.confirmPassword=this.currentPage + ' ' + '#confirmPassword';
	this.errorContainer = this.currentPage + ' ' + '#errorDivHeader';
	this.errorMessage = this.currentPage + ' ' + '#errorDivData';

	this.init = function() {
		this.bindEvents();
		this.removecolors(self.inputFields);
	}
	this.removecolors = function(cl) {
		errorHandler.removeErrorColor(this.oldPassword);
		errorHandler.removeErrorColor(this.newPassword);
		errorHandler.removeErrorColor(this.confirmPassword);
	}
	this.createError = function(error) {
		var self=this;
		$(error.errorMsgs).each(function(index, errormsg) {
			errorHandler.createError($(errormsg.errorField), errormsg.errorMessage);
		});
	}
	this.bindEvents = function() {
		var self=this;
		var patientHome = this.getPatientHome();
		user = patientHome.getUser();
		$('#'+ constants.CHANGEPASSWORDMODALNAME + ' #btnCancel').die('click').live('click',function(){
			$('#'+ constants.CHANGEPASSWORDMODALNAME).trigger('reveal:close');
		});
		$('#'+ constants.CHANGEPASSWORDMODALNAME + ' #btnSubmit').die('click').live('click',function(){
			errorHandler.removeErrors();
			var confirmPassword = $(self.confirmPassword).val();
			var passwordInfo = new PasswordInfo();
			passwordInfo.setOldPassword($(self.oldPassword).val());
			passwordInfo.setUsername(user.name);
			passwordInfo.setNewPassword($(self.newPassword).val());
			var error = self.validate(passwordInfo,confirmPassword);
			if(error.errorStatus==false){
				if(passwordInfo.getNewPassword()!=confirmPassword){
					error=new ErrorDTO();
					var errorMsgs=[];
					var errorMessage = new ErrorMessageDTO(self.confirmPassword,constants.PASSWORDNOTMATCH);
					errorMsgs.push(errorMessage);
					error.setErrorMsgs(errorMsgs);
					self.createError(error);
				} else {
					var response = query.changePassword(passwordInfo);
					if(!response.errorMessage){						
						$('#'+ constants.CHANGEPASSWORDMODALNAME + ' input[type=password]').val("");
						notifier.showTip("Password Changed Successfully");
					}else 
						errorHandler.updateTipsNew(response.errorMessage,$(self.errorMessage),$(self.errorContainer));
				}	
			} else 
				self.createError(error);
		});
	}
	this.validate = function(passwordInfo, confirmPassword) {
		var error = new ErrorDTO();
		var errorMsgs = [];
		if(validator.isEmpty(passwordInfo.getNewPassword())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.newPassword,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(confirmPassword)) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.confirmPassword,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(validator.isEmpty(passwordInfo.getOldPassword())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.oldPassword,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}

function PatientProcessor(patientHome) {
	this.parent=patientHome;
	
	this.getParent = function() {
		return this.parent;
	}
	this.showMyName = function() {
		return "PatientProcessor";
	}
	this.list = function(user_email) {
		return query.getPatientsByEmail(user_email);
	}
	this.init = function(){
		this.bindEvents();
	}
	this.bindEvents = function(){
		var self=this;
		$("#btnChangePassword").die('click').live("click",function() {
			pageHandler.generateModalPage(constants.CHANGEPASSWORDPAGEURL,constants.CHANGEPASSWORDMODALNAME);
			pageHandler.openPageAsModal($(this), constants.CHANGEPASSWORDMODALNAME);	
			var changePasswordProcessor = new ChangePasswordProcessor(self);
			changePasswordProcessor.init();
		});

	}
}
function PasswordInfo() {
	this.setOldPassword = function(oldPassword) {
		this.oldPassword = oldPassword;
	}
	this.getOldPassword = function(){
		return this.oldPassword;
	}
	this.setUsername = function(name){
		this.username = name;
	}
	this.getUsername = function() {
		return this.username;
	}
	this.getNewPassword = function(){
		return this.newPassword;
	}
	this.setNewPassword = function(newPassword){
		this.newPassword = newPassword;
	}
}
function FutureAppointmentListProcessor(parent) {
	this.parent = parent;
	this.showMyName = function() {
		return "FutureAppointmentListProcessor";
	}
	this.getParent = function(){
		return this.parent;
	}
	this.init = function() {
		var parent = this.getParent();
		var appointments = parent.getAppointments(parent.getPatientId());
		if(appointments.futureAppointment.length==0)
			$(parent.futureContainer).empty().html("No appointments");
		else
			$(parent.futureContainer).html(parent.listAppointments(appointments.futureAppointment));
	}
}
function WeekAppointmentListProcessor(parent) {
	this.parent = parent;
	this.showMyName = function() {
		return "WeekAppointmentListProcessor";
	}
	this.getParent = function(){
		return this.parent;
	}
	this.init = function() {
		var parent = this.getParent();
		var appointments = parent.getAppointments(parent.getPatientId());
		if(appointments.currentWeeksAppointment.length==0)
			$(parent.weekContainer).empty().html("No appointments");
		else
			$(parent.weekContainer).html(parent.listAppointments(appointments.currentWeeksAppointment));
	}
}
function PastAppointmentListProcessor(parent) {
	this.parent = parent;
	this.pgTableName = '#pastAppointments';
	this.pgTableContainer='#pastAppointmentsTableCont';
	this.listUrl=constants.PATIENTPASTAPPOINTMENTSURL;
	this.exp = new ExpressionListDTO();
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);

	this.showMyName = function() {
		return "PastAppointmentListProcessor";
	}

	this.getParent = function(){
		return this.parent;
	}
	this.init = function() {
		var parent = this.getParent();
		dataTableProcessor.create(this.pgTableName,constants.PASTAPPOINTMENTSTABLEURL, parent.pastContainer);//Create Table for Listing Agent
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("patientId",parent.getPatientId(),"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
	}
	this.getPastAppointments = function(patientId) {
		return query.getPastAppointments();
	}
	this.setTableValues=function(pgTable, appointments, key) {
		var parent=this.getParent();
		if(appointments.count==0) {
			$(parent.pastContainer).empty().html("No Appointments");
			return false;
		}
		$(pgTable).dataTable().fnClearTable();
		$(appointments.pastAppointments).each(function(index, appointment){
			var id=appointment.globalId;
			var specialization="";
			if(!appointment.doctorSpecialisation=="")
				specialization=" ( "+appointment.doctorSpecialisation+" ) ";	
			var doctorInfo="Dr."+appointment.doctorFirstName+specialization;
			var timeSlot=appointment.startingTime+" "+appointment.date;
			var rowData=$(pgTable).dataTable().fnAddData([doctorInfo,timeSlot]);
			var row=$(pgTable).dataTable().fnSettings().aoData[rowData].nTr;
			$(row).attr('id',id);	
		});
	}
}
function TodayAppointmentListProcessor(parent) {
	this.parent = parent;
	this.showMyName = function() {
		return "TodayAppointmentListProcessor";
	}
	this.getParent = function(){
		return this.parent;
	}
	this.init = function() {
		var parent = this.getParent();
		var appointments = parent.getAppointments(parent.getPatientId());
		if(appointments.todaysAppointment.length==0)
			$(parent.todayContainer).html("No appointments");
		else
			$(parent.todayContainer).html(parent.listAppointments(appointments.todaysAppointment));
	}
}
function AppointmentListProcessor(patientHome){
	this.parent = patientHome;
	this.pastAppointmentP = new PastAppointmentListProcessor(this);
	this.todaysAppointmentP = new TodayAppointmentListProcessor(this);
	this.futureAppointmentP = new FutureAppointmentListProcessor(this);
	this.weekAppointmentP = new WeekAppointmentListProcessor(this);

	this.weekContainer = '#week_content';
	this.todayContainer = '#today_content';
	this.pastContainer = '#past_content';
	this.futureContainer = '#future_content';

	this.weekTab='#week_header';
	this.futureTab='#week_header';
	this.pastTab='#week_header';
	this.todayTab='#today_header';
	this.showMyName = function() {
		return "AppointmentListProcessor";
	}
	this.getAppointments = function(patientId) {
		return query.getAppointments_TD_WK_FT(patientId);
	}
	this.getPatientId = function() {
		return $(constants.PATIENTSELECTCONTROLNAME).val().split("_")[0];
	}
	this.getUser = function() {
		return this.user;
	}
	this.setUser = function(user) {
		this.user = user;
	}
	this.bindEvents=function() {
		var self=this;
		$('a[href=' + self.todayContainer + ']').click(function(){
			self.todaysAppointmentP.init();
		});
		$('a[href=' + self.weekContainer + ']').click(function() {
			self.weekAppointmentP.init();
		});
		$('a[href=' + self.pastContainer + ']').click(function(){
			self.pastAppointmentP.init();
		});
		$('a[href=' + self.futureContainer + ']').click(function() {
			self.futureAppointmentP.init();
		});
	}
	this.init =function() {
		var self=this;
		$('#pageToolBar-Container').empty().html('');
		var tabGenerator = new TabGenerator();
		tabGenerator.load(constants.DATACONTAINERCONTROLNAME,constants.APPOINTMENTACCORDIONJSONURL);
		$(constants.PAGETITLE).empty().html(constants.APPOINTMENTLISTCAPTION);
		self.bindEvents();
		$('a[href=' + self.todayContainer + ']').trigger('click');
	}
	this.listAppointments = function(appointments) {
		var appointmentsView = $('<div/>');
		$(appointments).each(function(index,appointment) {
			var specialisation="";
			if(appointment.doctorSpecialisation==null){specialisation="";}else{specialisation=appointment.doctorSpecialisation}
			var row = $('<div style="line-height:20px;border-bottom:1px solid #D8D8D8 ;overflow: auto;padding: 1em 1.2em;"/>');
			var content = '<span class="accordion_h3_head">Dr. '+appointment.doctorFirstName +'</span> <span>'+specialisation +'</span><span class="accordion_h3_subhead">' + appointment.startingTime + ' ' + appointment.date+ '</span>';
			row.empty().html(content);
			appointmentsView.append(row);
		}); 
		return appointmentsView.html();
	}
}
function ResultsProcessor(patientHome){
	this.parent = patientHome;
	this.pgTableName='#orders';
	this.container = '#tabs-1';
	this.listUrl = "/youNeverWait/netlims/ui/order/patient/getByFilter";
	this.pgTableContainer='#orderListTableCont';
	this.listUrl=constants.PATIENTRESULTSURL;
	this.exp = new ExpressionListDTO();
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.showMyName = function() {
		return "ResultsProcessor";
	}
	this.getParent = function() {
		return this.parent;
	}
	this.init = function() {
		$('#pageToolBar-Container').empty().html('');
		parent = this.getParent();
		$(constants.PAGETITLE).empty().html("Results");
		dataTableProcessor.create(this.pgTableName,constants.RESULTSTABLEURL, this.container);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("email",parent.getPatientEmail(),"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
	}
	this.setTableValues = function(tableObj, orderResult) {
		$(tableObj).dataTable().fnClearTable();
		if(orderResult.list) {
			if(orderResult.list.length>0) {			
				$(orderResult.list).each(function(index, order) {
					var patient=order.patient;
					var createdOn=order.createdOn;
					var paymentStatus=order.orderStatus
					var branchId = order.branchId;
					if(order.headerData!=null) {
						var header = $.parseJSON(order.headerData).header;
						var ageheader = header.age.split("-");
						var age="";
						var age_year = "";
						var age_month = "";
						var age_day = "";
						if(ageheader[0]!="" && ageheader[0]!=undefined)
							age_year = ageheader[0] + "Y";
						if(ageheader[1]!="" && ageheader[1]!=undefined)
							age_month = ageheader[1] + "M";
						if(ageheader[2]!="" && ageheader[2]!=undefined)
							age_day = ageheader[2] + "D";
						age = age_year + age_month + age_day;
					var rowData=$(tableObj).dataTable().fnAddData([order.uid, header.patientName, age, createdOn,header.collectedAt,header.referral, order.branchName]);
					var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$(row).children("td:nth-child(9)").attr("class","column-2");
					$(row).attr('id',order.id + "_" + branchId );
					$(row).children("td:nth-child(1)").attr("class","orderIdCol Ustyle");
					}
				});
			}		
		}
	}
}
function ClinicsProcessor(patientHome) {
	this.parent = patientHome;
	this.pgTableName='#clinics';
	this.container = '#tabs-1';
	this.listUrl = "/youNeverWait/ynw/ui/patient/netmdBranchList";
	this.pgTableContainer='#clinicsListTableCont';
	this.clinicsContainer = "#tabs-1";
	this.exp = new ExpressionListDTO();
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.scheduleProcessor = new ScheduleProcessor(this);
	this.calendar = new Calendar(this);

	this.showMyName = function() {
		return "ClinicsProcessor";
	}
	this.getParent = function() {
		return this.parent;
	}
	this.getPatientHome = function() {
		return  this.parent;
	}
	this.getCalender = function() {
		return this.calendar;
	}
	this.getScheduleProcessor = function() {
		return this.scheduleProcessor;
	}
	this.init = function() {
		parent = this.getParent();
		$(constants.PAGETITLE).empty().html("Clinics");
		dataTableProcessor.create(this.pgTableName,constants.CLINICSTABLEURL, this.container);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("patientFirstName",parent.getSelectedPatient(),"eq");
		expList.add(expr);
		expr = new ExpressionDTO("patientEmail",parent.getPatientEmail(),"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
		this.bindEvents();
		$('#clinicsAccordion h3:first').trigger('click');
	}
	this.bindEvents = function() {
		var self=this;
		$('#clinicsAccordion h3').click(function() {
			var selectedIds = $(this).attr('id').split("_");
			var globalId_netmdId = selectedIds[0]+"_"+selectedIds[1];
			$(globalId_netmdId +"_content").empty();
			var doctors = query.getDoctorsUsingBranchId(selectedIds[0]);
			$('#'+globalId_netmdId +"_content").empty();
			if(doctors.doctor.length>0){
				$(doctors.doctor).each(function(index,doctor){
					if(doctor.specialisation==null){specialisation="";}else{specialisation=doctor.specialisation}
					var row = $('<div style="line-height:45px;border-bottom:1px solid #D8D8D8;"/>');
					var content = '<span class="accordion_h3_head">Dr. '+doctor.firstName +'</span> <span>'+specialisation +'</span><span class="accordion_h3_subhead"><input id="'+doctor.id+'" type=button class="stdbtn createAppointment" value="Create Appointment"/></span>';
					row.empty().html(content);
				 	//newdiv1=$('<div class="row" style="height: 8%;margin: 10px;"><div class="column-fourth doc"><div class="column-left docname"style="" >Dr.'+doctor.firstName+'</div><div class="column-right" >'+specialisation+'</div></div><div  class="column-third"><input id="'+doctor.id+'" type=button class="stdbtn createAppointment" value="Create Appointment"></div></div>');
			     	$('#'+globalId_netmdId +"_content").append(row);
				});
			}else {
		        var message="No doctors available for this clinic";
				newdiv1=$('<div class="appDetail1">'+message+'</div>');
				$('#'+globalId_netmdId +"_content").html(newdiv1);
			}	
		});
		$('span .createAppointment').die('click').live('click',function() {
			var doctorId = $(this).attr('id');
			var parentId = $(this).closest('div').parent().attr('id');
			self.setDoctorId(doctorId);
			self.setParentId(parentId);			
			var doctorName = $(this).closest('div').children('.accordion_h3_head').text();
			var title = 'Create appointment with ' + doctorName;
			$(constants.PAGETITLE).html(title);
			self.generatePageToolBar();

			var calenderObj = $('<div/>');
			calenderObj.attr('id','calendar');
			calenderObj.attr('class','fc');
			$('#tabs-1').html(calenderObj);
			var calendar = self.getCalender();
			var scheduleProcessor = self.getScheduleProcessor();
			calendar.init();
			scheduleProcessor.init();
			$('.fc-header-left .fc-button-agendaDay').trigger('click');
			return false;
		});
	}
	this.setDoctorId = function(doctorId) {
		this.doctorId = doctorId;
	}
	this.setParentId = function(parentId) {
		this.parentId = parentId;
	}
	this.getDoctorId = function() {
		return this.doctorId;
	}
	this.getParentId = function() {
		return this.parentId;
	}
	this.getNetmdBranchId = function(parentId) {
		return parentId.split("_")[0];
	}
	this.getNetmdId = function(parentId){
		return parentId.split("_")[1];
	}
	this.generatePageToolBar = function() {
		var ptbProcessor = new PageToolBarProcessor();
		ptbProcessor.create(constants.CLINICS, constants.CLINICSPAGETBURL); 
		this.bindToolBarEvents();
	}
	this.bindToolBarEvents = function() {
		var self=this;
		$('#ClinicsPTBContainer #btn_home_ptb_id').die('click').live('click',function(){
			$(constants.PAGETITLE).html(constants.CLINICS);
			self.showMyName();
			self.init();
			$('#pageToolBar-Container').empty();
		});
		$('#ClinicsPTBContainer #btn_delete_ptb_id').die('click').live('click',function(){
			var pgTableName=".fc-agenda-slots";
			errorHandler.removeErrors();
	    	var textlen=$('tr td[selected] div').text();
			if(textlen!=""){
				var appointmentId = self.getSelectedAppointmentId(pgTableName);
				var confirmStatus = confirm(constants.CONFIRMDELETEAPPOINTMENT);
				if(confirmStatus==true) {
					if(appointmentId!="") {
						var response = query.deleteAppointmentFromPortal(appointmentId);
						if(response.success==true){
							var appid=response.id;
							$('tr td[selected] div').text("Take Your Appointment");
							$('tr td[selected]').removeClass('calendar_disabled_row');
							$('tr td[selected]').removeClass('calender_row_selected');
							$('tr td').removeAttr('selected');
							notifier.showTip("Appointment deleted Successfully");
						} else{
							alert(response.error);
							//updateTipsNew(getErrorName(response.error),$('#errorDivData'),$('#errorDivHeader'));
						}
					}
				} else {
					$('tr td[selected]').removeClass('calender_row_selected');
					$('tr td').removeAttr('selected');
				}
			}
		});
	}
	this.getSelectedAppointmentId = function(tableName){
		var appointmentId="";
		var selappointment = $(tableName + ' tbody tr td[selected]');
		if(selappointment.length==0){		
			alert("Select only one appointment");
			//updateTipsNew("Select atleast one appointment",$('#errorDivData'),$('#errorDivHeader'));
		} else if(selappointment.length>1){ 
			alert("Select only one appointment");
			//updateTipsNew("Select only one appointment",$('#errorDivData'),$('#errorDivHeader'));
		} else
			appointmentId=selappointment.val();
		return appointmentId;
	}
	this.setTableValues = function(pgTable, clinics) {
		parent = this.getParent();
		if(clinics.count==0) {
			$(parent.clinicsContainer).empty().html("No Clinics");
			return false;
		}
		$(this.pgTableName).dataTable().empty();
		var accordionInput = new AccordionListCreator();
		var list = [];
		$(clinics.netmdBranch).each(function(index,clinic){
			var content = new AccordionListContentCreator();
			var address = "";
			var mobile = "";
			var phone = "";
			if(clinic.address!=null && clinic.address!="")
				address=" "+clinic.address;
			if(clinic.mobile!=null && clinic.mobile!="")
				mobile="Mob:" + clinic.mobile;
			if(clinic.phone!=null && clinic.phone!="")
				phone="Ph:" + clinic.phone;
			var h3_caption = '<span class="accordion_h3_head">'+clinic.name + address +'</span><span class="accordion_h3_subhead">'+mobile +'</span><span class="accordion_h3_subhead">'+ phone+'</span>';
			var id = clinic.globalId + '_' + clinic.netmdId;
			content.setCaption(h3_caption);
			content.setId(id);
			list.push(content);
		 });
		accordionInput.setList(list);
		accordionGenerator = new AccordionGenerator();
		layoutInfo = $('<div/>');
		layout = accordionGenerator.generate(accordionInput, 'clinicsAccordion');
		layoutInfo.html(layout);
		$('#clinicsAccordion').remove();
		$('#clinicsListTableCont').append(layoutInfo.html());
		$('#clinicsAccordion').accordion({autoHeight: false,navigation: true});
	}
}
function AppointmentProcessor(parent) {
	this.parent = parent;
	this.showMyName = function() {
		return "AppointmentProcessor";
	}
	this.getParent = function() {
		return this.parent;
	}
	this.getPatientHome = function() {
		var parent = this.getParent();
		return parent.getPatientHome();
	}
	this.init = function() {

	}
	this.takeAppoitment = function (obj1, date, timeslot1) {
		pageHandler.generateModalPage(constants.NEWAPPOINTMENTPAGEURL,constants.APPOINTMENTMODALNAME);
		pageHandler.openPageAsModal(obj1, constants.APPOINTMENTMODALNAME);
		$("#newpatintAppointmentForm #Patientappointment").text(date);
		$("#newpatintAppointmentForm #Patientappointmenttime").text(timeslot1);
		this.bindEvents();
	}

	this.getAppointmentRequest = function(scheduleId,timeslot1) {
		var patientHome = this.getPatientHome();
		var patientId = patientHome.getPatientId();
		var patientName = patientHome.getSelectedPatientName();
		var scheduleP = this.getParent();
		var clinicsP = scheduleP.getParent();
		var parentId = clinicsP.getParentId();
		var netmdId = clinicsP.getNetmdId(parentId);
		var netmdBranchId = clinicsP.getNetmdBranchId(parentId);
		var doctorId = clinicsP.getDoctorId();
		var email = patientHome.getPatientEmail();
		var selecteddate=$('.fc-header-title h2').text();
		var datesplit=selecteddate.split(',');
		var date=datesplit[1];
		date=$.trim(date);
		var appointmentReq = new AppointmentReq();
		var header = new AppointmentHeader();
		header.setHeadOfficeId(netmdId);
		header.setBranchId(netmdBranchId);
		appointmentReq.setHeader(header);
		var appointment = new Appointment();
		appointment.setPatientId(patientId);
		appointment.setDoctorId(doctorId);
		appointment.setScheduleId(scheduleId);
		appointment.setStartTime(timeslot1);
		appointment.setStartDate(date);
		appointment.setPatientName(patientName);
		appointment.setEmailId(email);
		appointmentReq.setAppointmentDetails(appointment);
		return appointmentReq;
	}
	this.bindEvents = function() {
		var self=this;
		/*$('#viewpatintAppointmentForm #btnCancel').die('click').live('click',function(){
			$("#viewpatintAppointmentForm").trigger('reveal:close');
		});
		$('#viewpatintAppointmentForm #btnDone').die('click').live('click',function(){
			
			var patientapp=createUpdateSubmitJson(scheduleId);
			var patient="Your Appointment";
			var headerdetal=patientAppointment()
			var appointmentJson='{"header"'+":"+headerdetal+",";
			appointmentJson+='"appointmentDetails"'+":"+patientapp+"}";
			var appointmentResponse=postdataToServer("/youNeverWait/ws/ui/patient/updateAppointmentFromPortal",appointmentJson);	
			//alert(JSON.stringify(appointmentResponse));				
	    if(appointmentResponse.success==true){
			$('.fc-agenda-slots tr th').each(function() {
			var timeslotapp=$(this).text();
			var appId=appointmentResponse.id;
			var time=appointmentResponse.startTime
			var realTime=ampmConversion(time);
			if(timeslotapp==realTime){
				$(this).nextAll('td:first').children('div').text(patient);
				$(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
				$(this).nextAll('td:first').attr('style','background:#ADDFFF;');
				$(this).nextAll('td:first').attr('value',appId);
				$('tr td[selected] div').text("Take Your Appointment");
				$('tr td[selected]').attr('style','background:#52F3FF;');
				$('tr td').removeAttr('selected');
				}
			});
			showTip("Appointment updated Successfully");
			$("#viewpatintAppointmentForm").trigger('reveal:close');
			 }
			
	else {
			updateTipsNew(getErrorName(appointmentResponse.error),$('#error'));
		  		   $("#error").delay(1100).fadeOut(1100);
		}
		
		});*/
		 $('#newpatintAppointmentForm #btnDone').die('click').live("click",function() {	
		 	var appointment = self.getAppointmentRequest(scheduleId,timeslot1);
			var patient="Your Appointment";
			var response = query.createAppointmentFromPortal(appointment);		
		    if(response.success==true){
				$('.fc-agenda-slots tr th').each(function() {
					var timeslotapp=$(this).text();
					var appId=response.globalId;
					if(timeslotapp==timeslot1){
						$(this).nextAll('td:first').children('div').text(patient);
						$(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
						$(this).nextAll('td:first').addClass('calendar_disabled_row');
						$(this).nextAll('td:first').attr('value',appId);
					}
				});
				$("#newpatintAppointmentForm").trigger('reveal:close');
				showTip("Appointment created Successfully");
			} else {
				//showTip(getErrorName(appointmentResponse.error));
		  		//$("#error").delay(1100).fadeOut(1100);
		  		alert(response.error);
			}		
		});
		$('#newpatintAppointmentForm #btnCancel').die('click').live("click",function() {	
			$("#newpatintAppointmentForm").trigger('reveal:close');
		});
	}
}
function AppointmentReq() {
	this.getHeader = function() {
		return this.header;
	}
	this.getAppointmentDetails=function(){
		return this.appointmentDetails;
	}
	this.setHeader=function(header) {
		this.header = header;
	}
	this.setAppointmentDetails=function(appointmentDetails) {
		this.appointmentDetails=appointmentDetails;
	}
}
function AppointmentHeader() {
	this.getHeadOfficeId = function() {
		return this.headOfficeId;
	}
	this.setHeadOfficeId = function(netmdId) {
		this.headOfficeId = netmdId;
	}
	this.getPassPhrase = function() {
		return this.passPhrase;
	}
	this.setPassPhrase = function(passphrase){
		this.passPhrase=passphrase;
	}
	this.getMacId = function() {
		return this.macId;
	}
	this.setMacId = function(macid){
		this.macId=macid;
	}
	this.getBranchId = function() {
		return this.branchId;
	}
	this.setBranchId = function(branchId) {
		this.branchId = branchId;
	}
}
function Appointment() {
	this.setPatientId = function(patientId){
		this.patientId = patientId;
	}
	this.getPatientId = function(){
		return this.patientId;
	}
	this.getDoctorId = function() {
		return this.doctorId;
	}
	this.setDoctorId = function(doctorId) {
		this.doctorId = doctorId;
	}
	this.getScheduleId = function() {
		return this.scheduleId;
	}
	this.setScheduleId = function(scheduleId) {
		this.scheduleId = scheduleId;
	}
	this.getStartTime =function() {
		return this.startTime;
	}
	this.setStartTime = function(startTime) {
		this.startTime = startTime;
	}
	this.getStartDate = function() {
		return this.startDate;
	}
	this.setStartDate = function(startDate) {
		this.startDate = startDate;
	}
	this.getPatientName = function() {
		return this.patientName;
	}
	this.setPatientName = function(name) {
		this.patientName = name;
	}
	this.getEmailId = function() {
		return this.emailId;
	}
	this.setEmailId = function(email) {
		this.emailId = email;
	}
}
function ScheduleProcessor(parent) {
	this.parent = parent;
	this.appointmentProcessor = new AppointmentProcessor(this);

	this.getAppointmentProcessor = function(){
		return this.appointmentProcessor;
	}
	this.showMyName = function() {
		return "ScheduleProcessor";
	}
	this.getParent = function() {
		return this.parent;
	}
	this.getPatientHome = function() {
		var parent = this.getParent();
		return parent.getPatientHome();
	}
	this.getCalender = function () {
		return this.parent.getCalender();
	}
	this.monthlyView = function() {
		var calendar = this.getCalender();
		var parent = this.getParent();
		var patientHome = this.getPatientHome();
		var parentId = parent.getParentId();
		var netmdBranchId = parent.getNetmdBranchId(parentId);
		var patientName = patientHome.getSelectedPatientName();
		var selected_month_year=$('.fc-header-title h2').text();
		var month_year=selected_month_year.split(' ');
		var month=month_year[0];
		var year=month_year[1];
		var curmonth=calendar.convertToIntMonth(month);
		if(curmonth<10){curmonth="0"+curmonth;} 	
		var startDate=calendar.getLastDateOfMonth(year,curmonth);
		var curStartDate=startDate.getDate();
		var curMonthLastDate=year+'-'+curmonth+'-'+curStartDate;
		curMonthLastDate=$.trim(curMonthLastDate);
		var curMonFirstDate=year+'-'+curmonth+'-'+'01';
		curMonFirstDate=$.trim(curMonFirstDate);

		param = netmdBranchId+','+parent.getDoctorId()+','+curMonFirstDate+","+curMonthLastDate;
		var response = query.getScheduleMonthView(param);
		$('.fc-view-month .fc-border-separate tbody tr td').not('.fc-other-month').each(function() {
			var fc_content = $(this).children('div').children('div.fc-day-content').children('div');
			fc_content.html("");
		});		
		if(response.success==true){
			$(response.scheduleList).each(function(index,interval) {
				$(interval.appointment).each(function(val,appointment) {
					var patntName=appointment.patientName;
					var dayvalue=appointment.startDate;
					dayvalue=dayvalue.slice(8,10);
			 		if(dayvalue<10){dayvalue=dayvalue.slice(1,2);}
					$('.fc-view-month .fc-border-separate tbody tr td').not('.fc-other-month').each(function() {
						var dayofschedule = $(this).children('div').children('div.fc-day-number').text();
						if(dayvalue==dayofschedule){
							if(patntName==patientName){
								var fc_content = $(this).children('div').children('div.fc-day-content').children('div');
								fc_content.html("Your Appointment");
							}
						}
					});					
				});
			});
		}	
	}
	this.init = function() {
		this.bindEvents();
	}
	this.setCalendarSelectionType = function(type) {
		this.type=type;
	}
	this.getCalendarSelectionType = function() {
		return this.type;
	}
	this.dayView = function(date) {
		var appointmentProcessor = this.getAppointmentProcessor();
		var self = this;
		var parent = this.getParent();
		var patientHome = this.getPatientHome();
		var parentId = parent.getParentId();
		var netmdBranchId = parent.getNetmdBranchId(parentId);
		var calendar = this.getCalender();
		self.setCalendarSelectionType(constants.DAY);
		$('.fc-agenda-slots tbody tr').show();
		param = netmdBranchId+','+parent.getDoctorId()+','+date.trim();
		var response = query.getScheduleDayView(param);
		$(response.schedule).each(function(index,interval) {
			if(calendar.CompareDate(interval.startDate)!=1)
				return;
			var timevw=interval.startTime;
			var endtime=interval.endTime;
			var status=interval.status;
			var nameid=interval.id;
			var conversion=calendar.ampmConversion(timevw);
			var conversion1=calendar.ampmConversion(endtime);
			var end = "00:30";
			$('.fc-agenda-slots tr th').each(function() {
				var timeslot=$(this).text();
				var frmat=conversion.slice(6,8);
				if(timeslot==conversion && conversion!=conversion1){
					if(status=="Working Hours"){
						$(this).nextAll('td').children('div').text("Take Your Appointment");
						$(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
						$(this).nextAll('td:first').children('div').attr('name',nameid);
						$(this).parents('.fc-agenda-slots tr').addClass('appointment');
						conversion=calendar.addTime(conversion,end,frmat);
					}
				}					
			});	
		});
		$(response.appointment).each(function(index,appinterval) {
			var apptime=appinterval.startTime;
			var appointId=appinterval.id;
			var currentdate=appinterval.startDate;
			var realapptime=calendar.ampmConversion(apptime);
			var idpatient=patientHome.getPatientId();
			var appopatientid=appinterval.patientId;
			$('.fc-agenda-slots tr th').each(function() {
				var timeslotapp=$(this).text();
				if(timeslotapp==realapptime&&date.trim()==currentdate){
					if(appopatientid==idpatient){
						$(this).nextAll('td:first').children('div').text("Your Appointment");
						$(this).nextAll('td:first').attr('value',appointId);
						$(this).nextAll('td:first').addClass('calendar_disabled_row');
					}else{
						$(this).nextAll('td:first').children('div').text("Reserved");
					}
					$(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
				}
			});
		});		
	}
	this.getScheduleDate = function(){
		return this.scheduleDate;
	}
	this.setScheduleDate=function(scheduleDate) {
		this.scheduleDate=scheduleDate;
	}
	this.setTodaysDate = function(){
		var curDate = new Date();
		var month = curDate.getMonth()+1;
		if(month<10)
			month='0' + month;
		var day = curDate.getDate();
		if (day<10)
			day='0' + day;
		var year = curDate.getFullYear();
		var today = "" + year + '-' + month + '-' + day + "";
		this.setScheduleDate(today);
	}
	this.setCalendarDate = function() {
		var selecteddate=$('.fc-header-title h2').text();
		var datesplit=selecteddate.split(',');
		var date=datesplit[1];
		this.setScheduleDate(date);
	}
	this.bindEvents = function() {
		var calendar = this.getCalender();
		var appointmentProcessor = this.getAppointmentProcessor();
		var self = this;
		self.setTodaysDate();
		$('.fc-header-left .fc-button-agendaDay').die('click').live("click",function() {
			date = self.getScheduleDate();
			self.dayView(date);
		});
		$('.fc-header-right .fc-button-today').die('click').live("click",function() {
			self.setTodaysDate();
			$('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
			$('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
			$('.fc-agenda-slots tr th').nextAll('td').removeAttr('style');
			$('.fc-agenda-slots tr th').nextAll('td').removeAttr('selected');
			$('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
			$('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').css('visibility', 'visible');
			$('.fc-agenda-slots tr td').removeClass('calendar_disabled_row');
			$('.fc-agenda-slots tr td').removeClass('calender_row_selected');
			$('.fc-header-left .fc-button-agendaDay').trigger('click'); 
		});
		$('.fc-header-right .fc-button-prev').die('click').live("click",function() {
			$('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
			$('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
			$('.fc-agenda-slots tr th').nextAll('td').removeAttr('style');
			$('.fc-agenda-slots tr th').nextAll('td').removeAttr('selected');
			$('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
			$('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').css('visibility', 'visible');
			$('.fc-agenda-slots tr td').removeClass('calendar_disabled_row');
			$('.fc-agenda-slots tr td').removeClass('calender_row_selected');
			if(self.getCalendarSelectionType()==constants.DAY){
				self.setCalendarDate();
				$('.fc-header-left .fc-button-agendaDay').trigger('click'); 
			} else if(self.getCalendarSelectionType()==constants.MONTH)
				$('.fc-header-left .fc-button-month ').trigger('click');
			else
				$('.fc-header-left .fc-button-agendaWeek ').trigger('click');
		});
		$('.fc-header-right .fc-button-next').die('click').live("click",function() {
			$('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
			$('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
			$('.fc-agenda-slots tr th').nextAll('td').removeAttr('style');
			$('.fc-agenda-slots tr th').nextAll('td').removeAttr('selected');
			$('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
			$('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').css('visibility', 'visible');
			$('.fc-agenda-slots tr td').removeClass('calendar_disabled_row');
			$('.fc-agenda-slots tr td').removeClass('calender_row_selected');
			if(self.getCalendarSelectionType()==constants.DAY){
				self.setCalendarDate();
				$('.fc-header-left .fc-button-agendaDay').trigger('click'); 
			}else if(self.getCalendarSelectionType()==constants.MONTH)
				$('.fc-header-left .fc-button-month ').trigger('click');
			else
				$('.fc-header-left .fc-button-agendaWeek ').trigger('click');
		});
		$('.fc-header-left .fc-button-agendaWeek ').die('click').live("click",function() {	
			self.setCalendarSelectionType(constants.WEEK);
			$('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
			$('.fc-agenda-slots tr th').nextAll('td').removeAttr('style');
			$('.fc-agenda-slots tr td').removeClass('calendar_disabled_row');
			$('.fc-agenda-slots tr td').removeClass('calender_row_selected');
		});	
		$('.fc-agenda-slots tbody tr td').die('click').live("click",function() {	
			var lengthDiv=$(this).children('div').text().length; 
			timeslot1=$(this).closest('tr').children('th').text();
			scheduleId=$(this).children('div').attr('name');
			var slotdataText=$(this).text();
			if(slotdataText=="Take Your Appointment"){
				var selecteddate=$('.fc-header-title h2').text();
				var datesplit=selecteddate.split(',');
				var date=datesplit[1];
				var obj1=$(this);	
				appointmentProcessor.takeAppoitment(obj1, date, timeslot1);
			}
			if(slotdataText=="Your Appointment"){
		  		if($(this).attr('selected')) {
					$(this).removeAttr('selected');
					$(this).removeAttr('style');
					$(this).addClass('calendar_disabled_row');
				}else {	
					$(this).attr('selected','selected');
					$(this).addClass('calender_row_selected');
				}
				//removeErrors();
			}		
	 	}); 
	 	$('.fc-agenda-slots tbody tr th').die('click').live("click",function() {		
			var lengthDiv=$(this).next('td').children('div').text().length; 
			timeslot1=$(this).closest('tr').children('th').text();
			scheduleId=$(this).next('td').children('div').attr('name');
			var slotdataText=$(this).next('td').text();
			if(slotdataText=="Your Appointment"){
		  		if($(this).next('td').attr('selected')) {
					$(this).next('td').removeAttr('selected');
					$(this).next('td').removeAttr('style');
					$(this).next('td').addClass('calendar_disabled_row');
				} else {	
					$(this).next('td').attr('selected','selected');
					$(this).next('td').addClass('calender_row_selected'); 
				}
				//removeErrors();
			} 			
	 	});	
	 	$('.fc-header-left .fc-button-month ').die('click').live("click",function() {	
	 		self.setCalendarSelectionType(constants.MONTH);	
			//$('.fc-border-separate tr td ').removeAttr('style');
			//$('div.fc-day-content').text("");
			//$('.fc-border-separate tr td .fc-day-number').nextAll('div.fc-day-content').text("");
			self.monthlyView();
		});	
		$('.fc-view-month .fc-border-separate tbody tr td').die('click').live("click",function() {	
			if($(this).hasClass('fc-other-month'))
				return false;
			var day = $(this).children('div').children('div.fc-day-number').text();
			var month_year_title =$('.fc-header-title h2').text();
			var month_year= month_year_title.split(" ");
			var year = month_year[1];
			var month = calendar.convertToIntMonth(month_year[0]);
			if(month<10)
				month='0'+month;
			if(day<10)
				day='0'+day;
			var date = year+"-"+month+"-"+day;
			self.setScheduleDate(date);	
			$('.fc-header-left .fc-button-agendaDay').trigger('click');
		});
	}
}
function AccordionListCreator() {
	this.showMyName = function() {
		return "AccordionListCreator";
	}
	this.setList = function(list) {
		this.list = list;
	}
	this.getList = function(){
		return this.list;
	}
}
function AccordionListContentCreator() {
	this.showMyName = function() {
		return "AccordionListContentCreator";
	}
	this.setId = function(id) {
		this.id = id;
	}
	this.getId =function() {
		return this.id;
	}
	this.setCaption = function(caption) {
		this.h3_caption = caption;
	}
	this.getCaption = function(){
		return ths.h3_caption;
	}
}
function Calendar() {
	this.showMyName = function() {
		return "Calendar";
	}
	this.init=function() {
		this.bindEvents();
	}
	this.getLastDateOfMonth=function(Year,Month){
		return(new Date((new Date(Year, Month,1))-1));
	}
	this.convertToIntMonth = function(monText){
		//Your arrays for the valid months for which you can throw exception (I did not do it) if the parameter is out of these 12.
		var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
		var monInt;
		for (i = 0; i < 12; i++) {
			//in this loop, we'll check for the match in parameter and array items.
			if (monText == monthNames[i]){
			//If match is found, we'll return the +1 value of actual match (as month array's index goes from 0 to 11, and we require it from 1 to 12)
				monInt=i+1;
			}
		}
		return monInt;
	}	
	this.CompareDate=function(date) { //date in format yyyy-mm-dd
       	var selectedDate = moment(date, "YYYY-MM-DD");
		var startDate = moment(this.getCustomDate(new Date()), "DD-MM-YYYY");
		if (selectedDate.isBefore(startDate)) 
		   return 0;
		else 
            return 1;
    }
	this.ampmConversion = function(time) {
		var splitstrttme=time.slice(0,5);
		var splitstrttme1=time.slice(6,8);
		if((splitstrttme1)=="AM")
			splitstrttme1="am";
		else
			splitstrttme1="pm";
		var strttime=splitstrttme+" "+splitstrttme1;
		return strttime;
	}
	this.splitTimeStr = function(t){
		var t = t.split(":");
		var timeslot=t[1].slice(0,2);
		t[0] = Number(t[0]);
		t[1] = Number(timeslot);	
		return t;
	}
	this.addTime = function(t1, t2,format){
		var t1Hr = this.splitTimeStr(t1)[0];
		var t1Min = this.splitTimeStr(t1)[1];
		var t2Hr = this.splitTimeStr(t2)[0];
		var t2Min = this.splitTimeStr(t2)[1];
		var rHr = t1Hr + t2Hr;
		var rMin = t1Min + t2Min;
		var check=rHr+"."+rMin;	
		if (rMin >= 60)	{
			rMin = rMin - 60;
			rHr = rHr + 1;
		}
		if(check > 12.30)
			rHr = rHr - 12
		var timefrmat=rHr + ":" + rMin;
		if(timefrmat == "12:0")	{ 
			if(format=="am")
				format="pm";
			else
			   format="am";
		}	
		if (rMin < 10) rMin = "0" + rMin;
		if (rHr < 10) rHr = "0" + rHr;
		return "" + rHr + ":" + rMin+" "+format;
	}
	this.getCustomDate=function(curDate) {
		var month = curDate.getMonth()+1;
		if(month<10)
			month='0' + month;
		var day = curDate.getDate();
		if (day<10)
			day='0' + day;
		var year = curDate.getFullYear();
		var today = "" + day + '-' + month + '-' + year + "";
		return today;
	}
	this.bindEvents = function() {
		/* initialize the external events */
		$('#external-events div.external-event').each(function() {
			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title: $.trim($(this).text()) // use the element's text as the event title
			};	
			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);
			// make the event draggable using $ UI
			$(this).draggable({
				zIndex: 999,
				revert: true,      // will cause the event to go back to its
				revertDuration: 0  //  original position after the drag
			});		
		});	
		/* initialize the calendar */
		$('#calendar').fullCalendar({
			header: {
				left: 'month,agendaDay',
				center: 'title',
				right: 'today, prev, next'
			},
			buttonText: {
				prev: '&laquo;',
				next: '&raquo;',
				prevYear: '&nbsp;&lt;&lt;&nbsp;',
				nextYear: '&nbsp;&gt;&gt;&nbsp;',
				today: 'Today',
				month: 'Month',
				week: 'Week',
				day: 'Day'
			},
			editable: true,
			droppable: true, // this allows things to be dropped onto the calendar !!!
			drop: function(date, allDay) { // this function is called when something is dropped
				// retrieve the dropped element's stored Event Object
				var originalEventObject = $(this).data('eventObject');
				// we need to copy it, so that multiple events don't have a reference to the same object
				var copiedEventObject = $.extend({}, originalEventObject);
				// assign it the date that was reported
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				// render the event on the calendar
				// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
				// is the "remove after drop" checkbox checked?
				$(this).remove();
			}
		});
	}
	this.init = function() {
		this.bindEvents();
	}
}