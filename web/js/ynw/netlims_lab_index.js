var ajaxProcessor = new ServerUrlProcessor();
var dataTableProcessor = new DataTableProcessor();
pageHandler = new PageHandler();
commonMethodInvoker = new CommonMethodInvoker();
var constants = new Constants();
var query = new Query();
var methodInvoker = new MethodInvoker();
errorHandler = new ErrorHandler();
validator = new Validator();
var notifier = new Notifier();
$(function() {
	$('.filter-main').hide();
	$('#filter').click(function () {
        $('.filter-main').toggle(500);
		$('#filterWorkBench').hide();
		dpi_y = document.getElementById('testdiv').offsetHeight;
		methodInvoker.setDPI(dpi_y);
	});
	var homePage = new NetlimsCorporateHome();
	homePage.init();
});	
function Constants(){
	this.PAGETITLE = "#pageTitle";
	this.CONTAINER = '#tabs-1';
	this.FILTER = '#filter';
	this.FILTERBENCH=$('#filterWorkBench');
	this.PTBCONTAINER='#pageToolBar-Container';
	this.FTBCONTAINER="#filterToolBar-Container";
	this.SELECTEDROWCOLOR  = 'background:#DCDCDC;';
	this.GETORDERURL = "/youNeverWait/netlims/ui/order/";
	this.SHOWRESULTURL="/youNeverWait/netlims/ui/result/print/";
	this.ORDER = "order";
	this.ORDERFILTERKEY = "";
	this.ORDERLISTURL = "/youNeverWait/json/netlims/orderFilter.json";
	this.ONLINERESULTSLIST="Online Results";
	this.ONLINERESULTSURL="/youNeverWait/netlims/ui/order/getByFilter";
	this.NETLIMSUSERURL="/youNeverWait/netlims/auth/user";
	this.LOGOUTURL="/youNeverWait/netlims/auth/logout";
	this.LEFTPANEURL="/youNeverWait/json/netlims/toolbar/lab_LeftPane.json";
	this.SETTINGSPANEURL="/youNeverWait/json/netlims/toolbar/lab_Settings.json";
	this.BRANCHESHOME = "Branch";
	this.BRANCHORDERLISTURL="/youNeverWait/superadmin/ui/superAdmin/orderList";
	this.BRANCHLISTJSONURL="/youNeverWait/json/netlims/branchList.json";
	this.FACILITYLISTJSONURL="/youNeverWait/json/netlims/facilityList.json";
	this.ONLINERESULTSLISTJSONURL="/youNeverWait/json/netlims/orderTable.json";
	this.BRANCHLISTURL="/youNeverWait/netlims/ui/lab/branchList";
	this.FACILITYLISTURL="/youNeverWait/netlims/ui/lab/facility/list";
	this.VIEWBRANCHPAGEURL="/youNeverWait/json/netlims/branch.json";
	this.GETBRANCHURL="/youNeverWait/netlims/ui/lab/viewBranch/";
	this.DELETEBRANCHURL="/youNeverWait/netlims/ui/lab/deleteBranch";
	this.UPDATEBRANCHURL="/youNeverWait/netlims/ui/lab/updateBranch";
	this.BRANCHLISTPTBJSONURL="/youNeverWait/json/netlims/toolbar/branchListPTB.json";
	this.FACILITYLISTPTBJSONURL="/youNeverWait/json/netlims/toolbar/facilityListPTB.json";
	this.BRANCHORDERSJSONURL="/youNeverWait/json/netlims/branchOrders.json";
	this.BRANCHORDERSLISTJSONURL="/youNeverWait/json/netlims/branchesOrders.json";
	this.TRANSFERREDORDERSLISTJSONURL="/youNeverWait/json/netlims/transferredOrders.json";
	this.TRANSFERREDRESULTSLISTJSONURL="/youNeverWait/json/netlims/transferredResults.json";
	this.TRANSFEREDORDERLISTURL="/youNeverWait/netlims/ui/lab/getTransferredOrders";
	this.TRANSFEREDRESULTLISTURL="/youNeverWait/netlims/ui/lab/getTransferredResults";
	this.BRANCHORDERSLISTURL="/youNeverWait/netlims/ui/lab/viewBranchOrders/";
	this.SELECTONEBRANCH = "Select atleast one branch";
	this.SELECTONEBRANCHONLY = "Select only one branch";
	this.SELECTONEFACILITY = "Select atleast one facility";
	this.SELECTONEFACILITYONLY = "Select only one facility";
	this.FINDORDERCOUNTURL="/youNeverWait/netlims/ui/order/count";
	this.FINDORDERCOUNTFORFACILITYURL="/youNeverWait/netlims/ui/order/countforFacility";
	this.GENERALPTBURL="/youNeverWait/json/netlims/toolbar/viewPTB.json";
	this.BRANCHUPDATESUCCESS = "Branch updated successfully";
	this.EMAILINVALID = "Enter valid email";
	this.NAMEREQUIRED = "Name required";
	this.PHONEREQUIRED="Phone number required";
	this.PHONEINVALID = "Enter valid landline number";
	this.MOBILEREQUIRED="Mobile number required";
	this.MOBILEINVALID = "Enter valid mobile number";
	this.NAMEINVALID = "Enter valid name";
	this.SHOWORDERMODALNAME="branchOrdersModal";
	this.CHANGEPASSWORDPAGEURL='/youNeverWait/json/netlims/changePassword.json';
	this.CHANGEPASSWORDMODALNAME='changePasswordModal';
	this.CHANGEPASSWORDURL = "/youNeverWait/netlims/auth/changePassword";
	this.FIELDREQUIRED = 'Field required';
	this.PASSWORDNOTMATCH = "Password doesn't match";
	this.TODAYSORDERLIST="Today's Branch Orders";
	this.TRANSFEREDORDERLIST="Transferred Orders";
	this.TRANSFEREDRESULTLIST="Transferred Results";
	this.ORDERCOUNTPAGEURL="/youNeverWait/json/netlims/orderCount.json";
	this.ORDERCOUNTMODALNAME="orderCountModal";
	this.SYNCSETUPPAGEURL="/youNeverWait/json/netlims/syncSetup.json";
	this.SYNCSETUPMODALNAME="syncSetupModal";
	this.SYNCDATAURL="/youNeverWait/netlims/ui/lab/getBranchSyncDetails/";
	this.SYNCUPDATEURL="/youNeverWait/netlims/ui/lab/setBranchSync";
	this.ORDERTYPEJSONURL="/youNeverWait/json/netlims/orderType.json";
	this.ORDERTYPEMODAL="orderTypeModal";
	this.GETORDERTYPEURL="/youNeverWait/netlims/ui/order/getOrderType/";
	this.UPDATEORDERTYPEURL="/youNeverWait/netlims/ui/order/setOrderType";
}
function Query(){
	this.getUser = function(){
		ajaxProcessor.setUrl(constants.NETLIMSUSERURL);
		return ajaxProcessor.get();	
	}
	this.logout = function(){
		ajaxProcessor.setUrl(constants.LOGOUTURL);
		return ajaxProcessor.get(); 
	}
	this.getLeftPaneInfo = function(){
		ajaxProcessor.setUrl(constants.LEFTPANEURL);
		return ajaxProcessor.get();
	}
	this.getBranch = function(branchId){
		ajaxProcessor.setUrl(constants.GETBRANCHURL+branchId);
		return ajaxProcessor.get();
	}
	this.getLabSettingsInfo=function(){
		ajaxProcessor.setUrl(constants.SETTINGSPANEURL);
		return ajaxProcessor.get();
	}
	this.updateBranch = function(branch){
		ajaxProcessor.setUrl(constants.UPDATEBRANCHURL);
		return ajaxProcessor.post(branch);
	}
	this.changePassword = function(passwordInfo){
		ajaxProcessor.setUrl(constants.CHANGEPASSWORDURL);
		return ajaxProcessor.post(passwordInfo);
	}
	this.getTodaysLabOrders=function(labId){
		ajaxProcessor.setUrl(constants.BRANCHORDERSLISTURL+labId);
		return ajaxProcessor.get();
	}
	this.findOrderCount=function(input){
		ajaxProcessor.setUrl(constants.FINDORDERCOUNTURL);
		return ajaxProcessor.post(input);
	}
	this.findOrderCountForFacility=function(input){
		ajaxProcessor.setUrl(constants.FINDORDERCOUNTFORFACILITYURL);
		return ajaxProcessor.post(input);
	}
	this.getSyncData=function(branchId){
		ajaxProcessor.setUrl(constants.SYNCDATAURL + branchId);
		return ajaxProcessor.get();
	}
	this.updateSyncData=function(input){
		ajaxProcessor.setUrl(constants.SYNCUPDATEURL);
		return ajaxProcessor.post(input);
	}
	this.getOrderType=function(labId){
		ajaxProcessor.setUrl(constants.GETORDERTYPEURL+labId);
		return ajaxProcessor.get();
	}
	this.updateOrderType=function(input){
		ajaxProcessor.setUrl(constants.UPDATEORDERTYPEURL);
		return ajaxProcessor.post(input);
	}
	this.getOrder = function(orderId) {
		ajaxProcessor.setUrl(constants.GETORDERURL+orderId);
		return ajaxProcessor.get();
	}
	this.showResult = function(param) {
		ajaxProcessor.setUrl(constants.SHOWRESULTURL+ param + "/");
		return ajaxProcessor.post();
	}
	this.viewUser = function(uid,branchId) {
		ajaxProcessor.setUrl(constants.USERURL + uid + "/" + branchId);
		return ajaxProcessor.get();
	}
}
function ChangePasswordProcessor(home) {
	this.parent = home;
	this.getCorporateHome = function() {return this.parent;}
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
	this.bindEvents = function() {
		var self=this;
		var corporateHome = this.getCorporateHome();
		user = corporateHome.getUser();
		$('#'+ constants.CHANGEPASSWORDMODALNAME + ' #btnCancel').die('click').live('click',function(){
			$('#'+ constants.CHANGEPASSWORDMODALNAME).trigger('reveal:close');
		});
		$('#'+ constants.CHANGEPASSWORDMODALNAME + ' #btnSubmit').die('click').live('click',function(){
			errorHandler.removeErrors();
			var confirmPassword = $(self.confirmPassword).val();
			var passwordInfo = new PasswordInfo();
			passwordInfo.setOldPassword($(self.oldPassword).val());
			passwordInfo.setUsername(user.userName);
			passwordInfo.setNewPassword($(self.newPassword).val());
			var error = self.validate(passwordInfo,confirmPassword);
			if(error.errorStatus==false){
				if(passwordInfo.getNewPassword()!=confirmPassword){
					error=new ErrorDTO();
					var errorMsgs=[];
					var errorMessage = new ErrorMessageDTO(self.confirmPassword,constants.PASSWORDNOTMATCH);
					errorMsgs.push(errorMessage);
					error.setErrorMsgs(errorMsgs);
					errorHandler.generateErrorFromList(error);
				} else {
					var response = query.changePassword(passwordInfo);
					if(!response.errorMessage){						
						$('#'+ constants.CHANGEPASSWORDMODALNAME + ' input[type=password]').val("");
						notifier.showTip("Password Changed Successfully");
					}else 
						errorHandler.updateTipsNew(response.errorMessage,$(self.errorMessage),$(self.errorContainer));
				}	
			} else 
				errorHandler.generateErrorFromList(error);
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
function NetlimsCorporateHome(){
	this.showMyName = function() {return "NetlimsCorporateHome";}
	this.setUser=function(user){this.user = user;}
	this.getUser = function(){return this.user;}
	this.downloadButton = "#ribbonNetlimsDownload";
	this.branchesButton = "#leftPaneNetlimsBranch";
	this.changePassword = "#btnChangePassword";
	this.branchOrdersLink = "#leftPaneNetlimsOrders";
	this.transferredOrdersLink="#leftPaneTransferredOrders";
	this.transferredResultLink="#leftPaneTransferredResults";
	this.settingsLink="#leftPaneSettings";
	this.onlineResultsLink="#leftPaneOnlineResults";
	this.init=function(){
		$(constants.FILTER).hide();
		var user = new User();
		var userInfo =query.getUser();
		user.setInfo(userInfo);
		user.bindEvents();
		this.setUser(userInfo);
		var leftpaneInfo = query.getLeftPaneInfo();
		var leftpaneTB = new leftpaneToolBar(leftpaneInfo.buttons);
		$('.leftmenu').html(leftpaneTB.result);	
		this.bindEvents();
	}
	this.bindEvents = function(){
		var self=this;
		var user = this.getUser();
		if($(this.branchesButton)) {
			$(this.branchesButton).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).hide();
				pageHandler.setHomePage(constants.BRANCHESHOME);
				var branchListUI = new BranchListUI();	
				branchListUI.init(user.labId);
			});
			$(self.branchOrdersLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).hide();
				pageHandler.setHomePage(constants.BRANCHORDERSHOME);
				var branchesOrders = new BranchOrders();	
				branchesOrders.init(user.labId);
			});
			$(self.transferredOrdersLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).hide();
				pageHandler.setHomePage(constants.BRANCHORDERSHOME);
				var orders = new TransferredOrders();	
				orders.init(user.labId);
			});
			$(self.transferredResultLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).hide();
				pageHandler.setHomePage(constants.BRANCHORDERSHOME);
				var orders = new TransferredResults();	
				orders.init(user.labId);
			});
			$(self.onlineResultsLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).show();
				pageHandler.setHomePage(constants.BRANCHORDERSHOME);
				var orders = new OnlineResults();	
				orders.init(user.labId);
			});
			$(self.settingsLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.PTBCONTAINER).empty();
				$(constants.FILTER).hide();
				pageHandler.setHomePage(constants.BRANCHORDERSHOME);
				var settingsInfo = query.getLabSettingsInfo();
				var settingsTB = new AdminToolBar(settingsInfo);
				$(constants.CONTAINER).html(settingsTB.result);
				var settings = new SettingsList();
				settings.init(user.labId);
			});
		}
		$(self.changePassword).die('click').live("click",function() {
			pageHandler.generateModalPage(constants.CHANGEPASSWORDPAGEURL,constants.CHANGEPASSWORDMODALNAME);
			pageHandler.openPageAsModal($(this), constants.CHANGEPASSWORDMODALNAME);	
			var changePasswordProcessor = new ChangePasswordProcessor(self);
			changePasswordProcessor.init();
		});		
	}
}
function User(user) {
	this.userName = "#userName";
	this.logoutButton = "#btnLogout";
	this.setInfo = function(user) {	
		if(user.userType=="owner")
			$(this.userName).html(user.userName);
		else
			$(this.userName).html(user.name);
	}
	this.bindEvents = function() {
		$(".headright #dropdownOne #dd").die('click').live("click",function() {
			$(this).toggleClass('active');
	    });
		$(this.logoutButton).die('click').live("click",function() {
			var response =query.logout();
			if(response==true)
				location.reload();
		});
	}
}
function BranchListUI(){
	this.branch = "branch";
	this.branchListPTBCaption="branchList";
	this.pgTableContainer = "#branchListTableCont";
	this.pgTableRowClass=".branchCol";
	this.exp = new ExpressionListDTO();
	this.pgTableName="#branchList";
	this.listUrl =constants.BRANCHLISTURL;
	this.branchForm="#branchForm";
	this.branchFormHeader = this.branchForm + " #branchHeader";
	this.id=this.branchForm + " #labId";
	this.branchId=this.branchForm + " #branchId";
	this.branchStatus=this.branchForm + " #branchStatus";
	this.passPhrase = this.branchForm + " #passPhrase";
	this.macId=this.branchForm + " #macId";
	this.name=this.branchForm + " #organizationName";
	this.phone=this.branchForm + " #phone";
	this.email=this.branchForm + " #email";
	this.address=this.branchForm + " #address";
	this.mobile=this.branchForm + " #mobile";
	this.saveButton = this.branchForm + " #btnSave";
	this.cancelButton = this.branchForm + " #btnCancel";
	this.editButton = this.branchForm + " #btnEdit";
	this.orderButton = this.branchForm + " #btnShowOrders";
	this.fromDate = this.branchForm + " #fromDate";
	this.toDate = this.branchForm + " #toDate";
	this.viewBranchPTB = "#branchListPTBContainer #btn_view_ptb_id";
	this.orderCountPTB = "#branchListPTBContainer #btn_ordercount_ptb_id";
	this.syncSetupPTB = "#branchListPTBContainer #btn_change_ptb_id";
	this.facilityListPTB = "#branchListPTBContainer #btn_facilityList_ptb_id";
	this.upButton_branch = "#branchPTBContainer #btn_up_ptb_id";
	this.downButton_branch = "#branchPTBContainer #btn_down_ptb_id";
	this.backButton_branch = "#branchPTBContainer #btn_back_ptb_id";
	this.errorData = $('#errorDivData');
	this.errorHeader = $('#errorDivHeader');
	this.inputFields = this.branchFormHeader + " :input[type=text]";
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.setLabId = function(labId){this.labId = labId;}
	this.getLabId = function(){return this.labId;}
	this.setCurrentBranchList = function(branchList) {this.branchList=branchList};
	this.getCurrentBranchList = function(){return this.branchList};
	this.setCurrentBranchId=function(branchId){this.selectedBranchId = branchId};
	
	this.getCurrentBranchId=function(){return this.selectedBranchId};
	
	this.getCurrentBranch = function(){return this.currentBranch;}
	this.setCurrentBranch = function(currentBranch){this.currentBranch=currentBranch;}
	this.branchOrdersView = new BranchOrdersView();
	this.getBranchOrdersView = function(){
		return this.branchOrdersView;
	}
	this.init = function(labId) {
		this.setLabId(labId);
		$(constants.PTBCONTAINER).empty().html('');
		$(constants.PAGETITLE).empty().html("Branch List");
		dataTableProcessor.create(this.pgTableName,constants.BRANCHLISTJSONURL,constants.CONTAINER);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("labId",labId,"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
		var ptbProcessor = new PageToolBarProcessor();
		ptbProcessor.create(this.branchListPTBCaption, constants.BRANCHLISTPTBJSONURL);
		this.bindEvents();
	}
	this.setTableValues = function(tableObj, branchResult) {
		$(tableObj).dataTable().fnClearTable();
		this.setCurrentBranchList(branchResult);
		if(branchResult.branch) {
			if(branchResult.branch.length>0) {			
				$(branchResult.branch).each(function (index, branch) {
					var id=branch.globalId;
					var rowData=$(tableObj).dataTable().fnAddData([id,branch.name,branch.mobile,branch.status]);
					var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$(row).attr('id',id);	
					$(row).children("td:nth-child(1)").attr("class","branchCol Ustyle");
				});	
			}
		}
	}
	this.resetBranchInfo=function(){
		$(self.id).val("");
		$(self.branchId).val("");
		$(self.branchStatus).val("");
		$(self.passPhrase).val("");
		$(self.macId).val("");
		$(self.name).val("");
		$(self.phone).val("");
		$(self.email).val("");
		$(self.address).val("");
		$(self.mobile).val("");
	}
	this.setBranchInfo=function(branch){
		var self=this;
		$(self.id).val(branch.labId);
		$(self.branchId).val(branch.globalId);
		$(self.branchStatus).val(branch.status);
		$(self.passPhrase).val(branch.passPhrase);
		$(self.macId).val(branch.macId);
		$(self.name).val(branch.name);
		$(self.phone).val(branch.phone);
		$(self.email).val(branch.email);
		$(self.address).val(commonMethodInvoker.br2nl(branch.address));
		$(self.mobile).val(branch.mobile);
		$(constants.PAGETITLE).empty().html("Branch - " + branch.name);
		self.setCurrentBranch(branch);
		self.setCurrentBranchId(branch.globalId);
	}
	this.view=function(branchId){
		var self=this;
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(self.branch, constants.GENERALPTBURL);
		pageHandler.create(constants.VIEWBRANCHPAGEURL);
		var branchInfo = query.getBranch(branchId);
		if(!branchInfo.errorMessage){
			this.setBranchInfo(branchInfo.branch);
		} else
			errorHandler.createServerError(self.errorHeader,self.errorData,branchInfo.errorMessage);
		$(self.fromDate).datepicker();
		$(self.toDate).datepicker();
		this.bindViewEvents();
		//commonMethodInvoker.setViewTable(this.testTable);
	}
	this.getPrevId = function(curId,branchResult) {
		var prevId;
		$(branchResult.branch).each(function (index, rowbranch) {
			if(curId==rowbranch.globalId)	{
				var arrayLength=(branchResult.branch).length;
				var comp=arrayLength-1;
				if(index==0)
					prevId = curId;
				else
					prevId=branchResult.branch[index-1].globalId;
			}
		});
		return prevId;	
	}
	this.getNextId = function(curId,branchResult) {
		var nextId;
		$(branchResult.branch).each(function (index, rowbranch) {
			if(curId==rowbranch.globalId)	{
				var arrayLength=(branchResult.branch).length;
				var comp=arrayLength-1;
				if(index==comp)
					nextId = curId;
				else
					nextId=branchResult.branch[index+1].globalId;	
			}
		});	
		return nextId;	
	}  
	this.removecolors = function(cl) {
		var self=this;
		errorHandler.removeErrorColor(self.name);
		errorHandler.removeErrorColor(self.phone);
		errorHandler.removeErrorColor(self.mobile);
		errorHandler.removeErrorColor(self.email);
	}
	this.writable = function() {
		self=this;
		self.removecolors(self.inputFields);
		$(self.editButton).hide();
		$(self.backButton_branch).hide();
		$(self.upButton_branch).hide();
		$(self.downButton_branch).hide();
		$(self.branchFormHeader + " input[type=text]").removeAttr('readonly');
		$(self.branchFormHeader  + " input[type=text]").removeClass('newBox');
		$(self.branchFormHeader  + " textarea").removeClass('newBox');
		$(self.branchFormHeader  + " textarea").removeAttr('readonly');
		$(self.branchId).addClass('newBox');
		$(self.branchId).attr('readonly','readonly');
		$(self.id).addClass('newBox');
		$(self.id).attr('readonly','readonly');
		$(self.branchStatus).addClass('newBox');
		$(self.branchStatus).attr('readonly','readonly');
		$(self.passPhrase).addClass('newBox');
		$(self.passPhrase).attr('readonly','readonly');
		$(self.macId).addClass('newBox');
		$(self.macId).attr('readonly','readonly');
		$(self.cancelButton).show();
		$(self.saveButton).show();
	}
	this.readable = function() {
		self=this;
		$(self.backButton_branch).show();
		$(self.upButton_branch).show();
		$(self.downButton_branch).show();
		$(self.branchFormHeader + " input[type=text]").attr('readonly',true);
		$(self.branchFormHeader + " input[type=text]").addClass('newBox');
		$(self.branchFormHeader + " textarea").attr('readonly',true);
		$(self.branchFormHeader + " textarea").addClass('newBox');
		$(self.cancelButton).hide();
		$(self.saveButton).hide();
		$(self.editButton).show();
	}
	this.bindViewEvents=function(){
		var self=this;
		$(self.backButton_branch).die('click').live('click',function(){
			errorHandler.removeErrors();
			self.init(self.getLabId());
		});
		$(self.upButton_branch).die('click').live('click',function(){
			errorHandler.removeErrors();
			var prevId = self.getPrevId(self.getCurrentBranchId(),self.getCurrentBranchList());
			if(prevId!=""){
				var branchInfo = query.getBranch(prevId);
				self.setBranchInfo(branchInfo.branch);
			}
		});
		$(self.downButton_branch).die('click').live('click',function(){
			errorHandler.removeErrors();
			var nextId = self.getNextId(self.getCurrentBranchId(),self.getCurrentBranchList());
			if(nextId!=""){
				var branchInfo = query.getBranch(nextId);
				self.setBranchInfo(branchInfo.branch);
			}
		});
		$(self.editButton).die('click').live('click',function(){
			self.errorHeader.hide();
			errorHandler.removeErrors();
			self.writable();
		});
		$(self.cancelButton).die('click').live('click',function(){
			self.errorHeader.hide();
			errorHandler.removeErrors();
			var branchInfo = self.getCurrentBranch();
			self.setBranchInfo(branchInfo);
			self.readable();
		});
		$(self.saveButton).die('click').live('click',function(){
			self.errorHeader.hide();
			errorHandler.removeErrors();
			var branchInput = self.getBranchInput();
			var error  = self.validate(branchInput, self);
			if(error.errorStatus==false) {
				var response = query.updateBranch(branchInput);
				if(response.error==null) {
					notifier.showTip(constants.BRANCHUPDATESUCCESS);//For showing the global Tip
					//self.setBranchInfo(branchInfo.branch);
					self.readable();
				} else
					errorHandler.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netlimsResponse.error));
			} else
				errorHandler.generateErrorFromList(error);	
		}); 
		$(self.orderButton).die('click').live('click',function(){
			var pgTableName = "#branchOrdersForm #orders";
			var pgTableContainer = "#branchOrdersForm #branchOrdersHeader";
			self.errorHeader.hide();
			var obj=$(this);
			errorHandler.removeErrors();	
			var selName="labId";
			var selValue=$(self.id).val();
			var selOperator = "eq";
			var selBrchName="labBranchId";
			var selBrchValue=$(self.branchId).val();
			var selBrchOperator = "eq";	
			var date="orderDate";
			var fromValue=$(self.fromDate).val();
			var fromOperator = "ge";
			var toValue=$(self.toDate).val();
			var toOperator = "le";
			var exp = new ExpressionListDTO();
			var expr = new ExpressionDTO(selName,selValue,selOperator);
			var expr1 = new ExpressionDTO(selBrchName,selBrchValue,selBrchOperator);
			var expr2 = new ExpressionDTO(date,fromValue,fromOperator);
			var expr3 = new ExpressionDTO(date,toValue,toOperator);
			exp.add(expr);
			exp.add(expr1);
			exp.add(expr2);
			exp.add(expr3);
			pageHandler.generateModalPage(constants.BRANCHORDERSJSONURL,constants.SHOWORDERMODALNAME);
			pageHandler.openPageAsModal($(this), constants.SHOWORDERMODALNAME);	
			dataTableProcessor.setCustomTable(pgTableName);
			var tableNavigator = new DataTableNavigator(pgTableName,constants.BRANCHORDERLISTURL,pgTableContainer,self.getBranchOrdersView(),exp);
			tableNavigator.list();
		});
	}
	this.validate = function(branch, source) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty(branch.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.name,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(branch.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.name,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(branch.getPhone())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.phone,constants.PHONEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.isEmpty(branch.getPhone())) {
			if(!validator.validatePhone(branch.getPhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.phone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
		//Email Validation
		if(!validator.isEmpty(branch.getEmail())) {
			if(!validator.validateEmail(branch.getEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.email,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}		
		if(validator.isEmpty(branch.getMobile())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.mobile,constants.MOBILEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.isEmpty(branch.getMobile())) {
			if(!validator.validateMobile(branch.getMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.mobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		error.setErrorMsgs(errorMsgs);
		return error;
	}
	this.getBranchInput = function() {
		var self=this;
		var branch = new Branch();
		var name=$(self.name).val();
		name= name.replace(/\b[a-z]/g, function(letter) {
			return letter.toUpperCase();
		});
		branch.setlabId($(self.id).val());
		branch.setglobalId($(self.branchId).val());
		branch.setName(name);
		branch.setEmail($(self.email).val());
		branch.setAddress(commonMethodInvoker.nl2br($(self.address).val()));
		branch.setPhone($(self.phone).val());
		branch.setMobile($(self.mobile).val());
		return branch;
	}
	this.getSelectedbranchId = function(){
		var self =this;
		var branchId="";
		if($(this.pgTableName).dataTable().fnGetData().length>0) {
			var selbranch = $(self.pgTableName + ' tbody tr[selected]');
			if(selbranch.length==0){
				errorHandler.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBRANCH);
			} else if(selbranch.length>1) 
				errorHandler.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBRANCHONLY);
			else
				branchId=selbranch.attr('id');
		}
		return branchId;
	}
	this.bindEvents = function(){
		var self = this;
		$(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
			if($(this).attr('selected')) {
				$(this).removeAttr('selected');
				$(this).removeAttr('style');
			} else {	
				$(this).attr('selected','selected');
				$(this).attr('style','background:#DCDCDC;');
			}	
			errorHandler.removeErrors();
		});	
		$(self.pgTableRowClass).die('click').live('click',function(){
		   var branchId= $(this).parent().attr('id');
			if(branchId!="") {
				$(constants.PTBCONTAINER).empty().html('');
				self.view(branchId);
			}	
		}); 
		$(self.viewBranchPTB).die('click').live('click',function() {
			errorHandler.removeErrors();
			var branchId=self.getSelectedbranchId(self.pgTableName);
			if(branchId!="") {
				$(constants.PTBCONTAINER).empty().html('');
				self.view(branchId);
			}	
		});
		$(self.orderCountPTB).die('click').live('click',function() {
			errorHandler.removeErrors();
			var branchId=self.getSelectedbranchId(self.pgTableName);
			if(branchId!="") {
				self.setCurrentBranchId(branchId);
				pageHandler.generateModalPage(constants.ORDERCOUNTPAGEURL,constants.ORDERCOUNTMODALNAME);
				pageHandler.openPageAsModal($(this), constants.ORDERCOUNTMODALNAME);
				self.bindOrderCountEvents();	
			}	
		});
		$(self.facilityListPTB).die('click').live('click',function() {
			errorHandler.removeErrors();
			var branchId=self.getSelectedbranchId(self.pgTableName);
			if(branchId!="") {
				self.setCurrentBranchId(branchId);
				facilityList = new FacilityList();
				facilityList.init(branchId);				
			}	
		});
		$(self.syncSetupPTB).die('click').live('click',function() {
			errorHandler.removeErrors();
			var branchId=self.getSelectedbranchId(self.pgTableName);
			if(branchId!="") {
				self.setCurrentBranchId(branchId);
				pageHandler.generateModalPage(constants.SYNCSETUPPAGEURL,constants.SYNCSETUPMODALNAME);
				pageHandler.openPageAsModal($(this), constants.SYNCSETUPMODALNAME);
				self.bindSyncPage();	
			}	
		});
	}
	this.bindSyncPage =function(){
		var interval = "#" + constants.SYNCSETUPMODALNAME + " #interval";
		var frequency= "#" + constants.SYNCSETUPMODALNAME + " #frequency";
		var time_interval_section="#" + constants.SYNCSETUPMODALNAME + " #timeAndIntervalSection";
		var selectedStatus="#" + constants.SYNCSETUPMODALNAME + " input[type=radio]:checked";
		var errorHeader="#" + constants.SYNCSETUPMODALNAME + " #errorDivHeader";
		var errorData="#" + constants.SYNCSETUPMODALNAME + " #errorDivData";

		var self=this;
		this.setFrequencyTime(frequency);
		commonMethodInvoker.validateNumber(interval);
		var syncInfo = query.getSyncData(self.getCurrentBranchId());
		var syncStatus = syncInfo.enableSync;
		if(syncStatus==true)
			$("#" + constants.SYNCSETUPMODALNAME + " #enableSync").attr('checked',true);
		else
			$("#" + constants.SYNCSETUPMODALNAME + " #disableSync").attr('checked',true);
		if(syncInfo.syncFreqType)
			$(frequency).val(syncInfo.syncFreqType);
		else
			$(frequency).val();
		if(syncInfo.syncTime)
			$(interval).val(syncInfo.syncTime);
		else
			$(interval).val();
		var status = $("#" + constants.SYNCSETUPMODALNAME + " #syncStatus").val();
		if(status!="disable" && status!=undefined)
			$(time_interval_section).show();
		else
			$(time_interval_section).hide();
		$("#" + constants.SYNCSETUPMODALNAME + " #enableSync").die('click').live('click',function(){
			$(time_interval_section).show();
		});
		$("#" + constants.SYNCSETUPMODALNAME + " #disableSync").die('click').live('click',function(){
			$(time_interval_section).hide();
		});
		$("#" + constants.SYNCSETUPMODALNAME + " #btnSubmit").die('click').live('click',function(){
			var syncInput = new BranchSyncInput();
			var status=false;
			if(($(selectedStatus).val())=="enable")
				status=true;
			syncInput.setenableSync(status);
			syncInput.setsyncTime(parseInt($(interval).val()));
			syncInput.setlabBranchId(parseInt(self.getCurrentBranchId()));
			syncInput.setsyncFreqType($(frequency).val());
			if(!validator.isZero(syncInput.getsyncTime())){
				var response = query.updateSyncData(syncInput);
				if(response.success==true){
					var statusMessage=response.msg;
					if(statusMessage==null)
						notifier.showTip("Updated Successfully");
					else
						errorHandler.createServerError($(errorHeader),$(errorData),statusMessage);
				}
			}else
				errorHandler.createError($(interval),constants.FIELDREQUIRED);
		});
	}
	this.setFrequencyTime=function(obj){
		var frequncyList=["daily","hourly","minutes"];
		$(frequncyList).each(function (Index, frequency) {
			$(obj).append('<option  value="'+frequency+'">'+frequency+'</option>');
		});
	}
	this.bindOrderCountEvents = function(){
		var self=this;
		$('#'+constants.ORDERCOUNTMODALNAME + ' #fromDate').datepicker();
		$('#'+constants.ORDERCOUNTMODALNAME + ' #toDate').datepicker();
		$('#'+constants.ORDERCOUNTMODALNAME + ' #btnCancel').die('click').live('click',function() {
			$('#' + constants.ORDERCOUNTMODALNAME).trigger('reveal:close');
		});
		$('#'+constants.ORDERCOUNTMODALNAME + ' #btnSearch').die('click').live('click',function() {
			var input = new OrderCountInput();
			input.setBranch(self.getCurrentBranchId());
			input.setFromDate($('#'+constants.ORDERCOUNTMODALNAME + ' #fromDate').val());
			input.setToDate($('#'+constants.ORDERCOUNTMODALNAME + ' #toDate').val());
			var result = query.findOrderCount(input);
			$('#'+constants.ORDERCOUNTMODALNAME + ' #orderCount').val(result);
		});
	}
}
function FacilityList(){
	this.orderCountPTB = "#facilityListPTBContainer #btn_ordercount_ptb_id";
	this.facility = "facility";
	this.facilityListPTBCaption="facilityList";
	this.pgTableContainer = "#facilityListTableCont";
	this.pgTableRowClass=".facilityCol";
	this.exp = new ExpressionListDTO();
	this.pgTableName="#facilityList";
	this.listUrl =constants.FACILITYLISTURL;
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.setCurrentFacilityId=function(facilityId){this.selectedFacilityId = facilityId};
	this.setCurrentFacilityList = function(facilityList) {this.facilityList=facilityList};
	this.getCurrentFacilityList = function(){return this.facilityList};
	this.setCurrentBranchId = function(branchId) {this.branchId=branchId};
	this.getCurrentFacilityId=function(){return this.selectedFacilityId};
	this.getCurrentBranchId = function(){return this.branchId};
	this.init=function(branchId){
		this.setCurrentBranchId(branchId);
		$(constants.PTBCONTAINER).empty().html('');
		$(constants.PAGETITLE).empty().html("Facility List");
		dataTableProcessor.create(this.pgTableName,constants.FACILITYLISTJSONURL,constants.CONTAINER);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("branchId",branchId,"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
		var ptbProcessor = new PageToolBarProcessor();
		ptbProcessor.create(this.facilityListPTBCaption, constants.FACILITYLISTPTBJSONURL);
		this.bindEvents();
	}
	this.setTableValues = function(tableObj, facilityResult) {
		$(tableObj).dataTable().fnClearTable();
		this.setCurrentFacilityList(facilityResult);
		if(facilityResult.list) {
			if(facilityResult.list.length>0) {			
				$(facilityResult.list).each(function (index, facility) {
					var rowData=$(tableObj).dataTable().fnAddData([facility.id,facility.name,facility.phone,facility.address]);
					var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$(row).attr('id',facility.id);	
					$(row).children("td:nth-child(1)").attr("class","facilityCol Ustyle");
				});	
			}
		}
	}
	this.bindEvents = function(){
		
		var self = this;
		$(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
			if($(this).attr('selected')) {
				$(this).removeAttr('selected');
				$(this).removeAttr('style');
			} else {	
				$(this).attr('selected','selected');
				$(this).attr('style','background:#DCDCDC;');
			}	
			errorHandler.removeErrors();
		});	
	
		$(self.orderCountPTB).die('click').live('click',function() {
			errorHandler.removeErrors();
			var facilityId=self.getSelectedfacilityId(self.pgTableName);
			if(facilityId!="") {
				self.setCurrentFacilityId(facilityId);
				pageHandler.generateModalPage(constants.ORDERCOUNTPAGEURL,constants.ORDERCOUNTMODALNAME);
				pageHandler.openPageAsModal($(this), constants.ORDERCOUNTMODALNAME);
				self.bindOrderCountEvents();	
			}	
		});
		this.bindOrderCountEvents = function(){
		var self=this;
		$('#'+constants.ORDERCOUNTMODALNAME + ' #fromDate').datepicker();
		$('#'+constants.ORDERCOUNTMODALNAME + ' #toDate').datepicker();
		$('#'+constants.ORDERCOUNTMODALNAME + ' #btnCancel').die('click').live('click',function() {
			$('#' + constants.ORDERCOUNTMODALNAME).trigger('reveal:close');
		});
		$('#'+constants.ORDERCOUNTMODALNAME + ' #btnSearch').die('click').live('click',function() {
			var input = new OrderCountInput();
			input.setFacility(self.getCurrentFacilityId());
			input.setFromDate($('#'+constants.ORDERCOUNTMODALNAME + ' #fromDate').val());
			input.setToDate($('#'+constants.ORDERCOUNTMODALNAME + ' #toDate').val());
			
			var result = query.findOrderCountForFacility(input);
			
			$('#'+constants.ORDERCOUNTMODALNAME + ' #orderCount').val(result);
		});
	}
	}
	this.getSelectedfacilityId = function(){
		var self =this;
		var facilityId="";
		if($(this.pgTableName).dataTable().fnGetData().length>0) {
			var selfacility = $(self.pgTableName + ' tbody tr[selected]');
			if(selfacility.length==0){
				errorHandler.createServerError(self.errorHeader,self.errorData, constants.SELECTONEFACILITY);
			} else if(selfacility.length>1) 
				errorHandler.createServerError(self.errorHeader,self.errorData, constants.SELECTONEFACILITYONLY);
			else
				facilityId=selfacility.attr('id');
		}
		return facilityId;
	}
}
function BranchOrdersView() {
	this.setOrderList=function(orderList){this.orderList=orderList;}
	this.getOrderList=function(){return this.orderList;}
	this.setTableValues = function(tableObj, orderResult) {
		$(tableObj).dataTable().fnClearTable();
		this.setOrderList(orderResult);
		if(orderResult.branchOrders) {
			if(orderResult.branchOrders.length>0) {			
				$(orderResult.branchOrders).each(function (index, lab) {
					var id=lab.branchId;
					var rowData=$(tableObj).dataTable().fnAddData([id,lab.branchName,lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
					var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$(row).attr('id',id);	
				});	
			} 
		}
	}
}
function BranchOrders(){
	this.exp = new ExpressionListDTO();
	this.pgTableContainer = "#branchesOrdersListTableCont";
	this.pgTableName= this.pgTableContainer + " #orders";
	this.init=function(labId){
		$(constants.PTBCONTAINER).empty().html('');
		$(constants.PAGETITLE).empty().html(constants.TODAYSORDERLIST);
		dataTableProcessor.create(this.pgTableName,constants.BRANCHORDERSLISTJSONURL, constants.CONTAINER);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var ordersList = query.getTodaysLabOrders(labId);
		this.setTableValues(this.pgTableName,ordersList);
	}
	this.setTableValues = function(tableObj, orderResult) {
		$(tableObj).dataTable().fnClearTable();
		if(orderResult.branchOrders.length>0) {			
			$(orderResult.branchOrders).each(function (index, lab) {
				var id=lab.branchId;
				var rowData=$(tableObj).dataTable().fnAddData([id,lab.branchName,lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
				var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$(row).attr('id',id);	
			});	
		} 
	}
}
function TransferredOrders(){
	this.showName=function() {alert("TransferredOrders");}
	this.exp = new ExpressionListDTO();
	this.pgTableContainer = "#transferredOrderTableCont";
	this.pgTableName= this.pgTableContainer + " #orders";
	this.listUrl = constants.TRANSFEREDORDERLISTURL;
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.init=function(labId){
		$(constants.PTBCONTAINER).empty().html('');
		$(constants.PAGETITLE).empty().html(constants.TRANSFEREDORDERLIST);
		dataTableProcessor.create(this.pgTableName,constants.TRANSFERREDORDERSLISTJSONURL, constants.CONTAINER);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("labId",labId,"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
	}
	this.setTableValues = function(tableObj, orderResult) {
		if(orderResult.transferreddetails.length>0) {			
			$(orderResult.transferreddetails).each(function (index, lab) {
				$(lab.destinationBranches).each(function (index, destination) {
					var id=lab.orderUid;
					var ordersent;
					if(destination.orderSent==true){ordersent="Yes"}else{ordersent="No"}
					var rowData=$(tableObj).dataTable().fnAddData([lab.fromBranch,destination.destinationBranch,id,ordersent]);
					var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$(row).attr('id',id);	
				});	
			});
		} 
	}
}
function TransferredResults(){
	this.showName=function() {alert("TransferredResults");}
	this.exp = new ExpressionListDTO();
	this.pgTableContainer = "#transferredResultsTableCont";
	this.pgTableName=this.pgTableContainer + " #results";
	this.listUrl=constants.TRANSFEREDRESULTLISTURL;
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.init=function(labId){
		$(constants.PTBCONTAINER).empty().html('');
		$(constants.PAGETITLE).empty().html(constants.TRANSFEREDRESULTLIST);
		dataTableProcessor.create(this.pgTableName,constants.TRANSFERREDRESULTSLISTJSONURL,constants.CONTAINER);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("labId",labId,"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
	}
	this.setTableValues = function(tableObj, orderResult) {
		if(orderResult.transferreddetails.length>0) {			
			$(orderResult.transferreddetails).each(function (index, lab) {
				var id=lab.branchId;
				var resultsent;
				if(lab.sent==true){resultsent="Yes"}else{resultsent="No"}
				var rowData=$(tableObj).dataTable().fnAddData([lab.fromBranch,lab.toBracnh,lab.orderUid,lab.testUId,resultsent]);
				var row=$(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$(row).attr('id',id);	
			});	
		}
	}
}
function MethodInvoker() {
	this.setDPI=function(dpi){this.dpi = dpi;}
	this.getDPI = function(){return this.dpi;}
}
function SettingsList(){
	this.orderTypeButton="#btnOrdertype";
	this.agentOrder="#" + constants.ORDERTYPEMODAL + " #agent";
	this.blanketOrder="#" + constants.ORDERTYPEMODAL + " #blanket";
	this.walkinOrder="#" + constants.ORDERTYPEMODAL + " #walkin";
	this.cancelButton="#" + constants.ORDERTYPEMODAL + " #btnCancel";
	this.submitButton="#" + constants.ORDERTYPEMODAL + " #btnSubmit";
	this.errorHeader="#" + constants.ORDERTYPEMODAL + " #errorDivHeader";
	this.errorData="#" + constants.ORDERTYPEMODAL + " #errorDivData";
	this.setLabId=function(labId){this.labId=labId;}
	this.getLabId=function(){return this.labId;}
	this.init=function(labId){
		this.setLabId(labId);
		this.bindTBEvents();
	}
	this.bindTBEvents=function(){
		var self=this;
		$(self.orderTypeButton).die('click').live("click",function(){
			pageHandler.generateModalPage(constants.ORDERTYPEJSONURL,constants.ORDERTYPEMODAL);
			pageHandler.openPageAsModal($(this), constants.ORDERTYPEMODAL);	
			var response = query.getOrderType(self.getLabId());
			var orderTypes=$.parseJSON(response.orderTypeCodes);
			if(orderTypes!=null){
				$(self.agentOrder).val(orderTypes.agentorder);
				$(self.blanketOrder).val(orderTypes.blanketorder);
				$(self.walkinOrder).val(orderTypes.walkinorder); 
			}
			self.bindEvents();
		});
	}
	this.bindEvents=function(){
		var self=this;
		$(self.cancelButton).die('click').live('click',function(){
			self.cancel();
		});	
		$(self.submitButton).die('click').live('click',function(){
			self.create();
		});	
	}
	this.create = function() {
		var self=this;
		$(self.errorHeader).hide();
		errorHandler.removeErrors();
		var orderType = new OrderType();
		orderType.setBlanketOrder($(self.blanketOrder).val());
		orderType.setAgentOrder($(self.agentOrder).val());
		orderType.setWalkinOrder($(self.walkinOrder).val());
		var orderPassType = new OrderTypeInput(orderType,self.getLabId());
		var response =query.updateOrderType(orderPassType);
		if(response.error==null) {
				notifier.showTip("Order Type Successfully Created");//For showing the global Tip			
			} else 
				errorHandler.createServerError(self.errorHeader, self.errorData,"Error");
			//	errorHandler.createServerError(self.errorHeader,self.errorData, errorHandler.getErrorName(orderTypeResponse.error));
	}
	this.cancel = function() {
		var self=this;
		$(self.errorHeader).hide();
		errorHandler.removeErrors();
		$("#" + constants.ORDERTYPEMODAL + " input[type=text]").val("");	
		$("#" + constants.ORDERTYPEMODAL).trigger('reveal:close');
		$("#" + constants.ORDERTYPEMODAL).remove();	
	}
}
function OnlineResults(){
	this.exp = new ExpressionListDTO();
	this.pgTableContainer = "#orderListTableCont";
	this.pgTableName=this.pgTableContainer + " #orders";
	this.ftbToolBar = "#order-filter-toolbar";
	this.filterActionButton = this.ftbToolBar + ' #btnGo';
	this.listUrl=constants.ONLINERESULTSURL;
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.init=function(labId){
		$(constants.PTBCONTAINER).empty().html('');
		$(constants.PAGETITLE).empty().html(constants.ONLINERESULTSLIST);
		var ftbProcessor = new FilterToolBarProcessor();
		ftbProcessor.create(constants.ORDER,constants.ORDERFILTERKEY,constants.ORDERLISTURL);
		dataTableProcessor.create(this.pgTableName,constants.ONLINERESULTSLISTJSONURL, constants.CONTAINER);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("labId",labId,"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
		this.bindEvents();
	}
	this.bindEvents=function(){
		var self=this;
		$(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
			var curObjName = $(this).attr('name');
			$(this).attr('selected','selected');
			$(this).addClass('button_filter');
			$('#lst'+curObjName).show();
			$('#txt'+curObjName).show();
			$('#txt'+curObjName).focus();
		})
		$(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
			var curObjName = $(this).attr('name');
			$(this).removeAttr('selected');
			$(this).removeClass('button_filter');
			$('#lst'+curObjName).hide();
			$('#txt'+curObjName).hide();
			$(self.filterActionButton).trigger('click');
		})
		$(self.filterActionButton).die('click').click(function(){
			var expList=new ExpressionListDTO();
			$(self.ftbToolBar + " a[selected]").each(function(){
				var selName=$(this).attr('name');
				var selTextValue=$("#txt"+ selName).val();
				var selOperator = 'like';
				if(selName=='referralName')
					selTextValue = 'Dr. ' + selTextValue;
				var expr = new ExpressionDTO(selName,selTextValue,selOperator);
				expList.add(expr);
			});
			self.tableNavigator.setExp(expList);
			self.tableNavigator.list();
		});
		$(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
			if(e.keyCode==13)
				$(self.filterActionButton).trigger('click');
		});
		$(self.pgTableName + ' tbody tr').die('click').live('click',function(){
			errorHandler.removeErrors();
			if($(this).attr('selected')) {
				$(this).removeAttr('selected');
				$(this).removeAttr('style');
			} else {	
				$(this).attr('selected','selected');
				$(this).attr('style',constants.SELECTEDROWCOLOR);
			}
		});
		$(self.pgTableName + ' .orderIdCol').die('click').live('click',function(){
			errorHandler.removeErrors();
			var objId=$(this).closest("tr").attr('id');
			var orderId = objId.split("_")[0];
			var branchId = objId.split("_")[1];
			var testList=[];
			self.print(testList,orderId,branchId);
		});
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
					if(order.headerData!=null && order.headerData!="") {
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
	this.print=function(testList, orderId,branchId){
		var order = query.getOrder(orderId);
		var layout = new LayoutJson();
		var layoutUpdater = new LayoutUpdater();
		var tests = [];
		layoutList = [];
		userList = [];
		userIds=[];
		var specimens = [];
		var count=0;
		var layoutTemplate;
		var status=false;
		if(testList.length==0)
			status=true;
		$(order.tests).each(function(index,test) {
			var testuid="";
			if(status==true){
				testuid=test.uid;
				if(!commonMethodInvoker.isIndexExists(testList, testuid))
					testList.push(testuid);
			} else 
				testuid=test.uid;
			if(commonMethodInvoker.isIndexExists(testList, testuid)){
				var result = $.parseJSON(test.result);
				$(result.specimen).each(function(index, specimenName){
					if(!commonMethodInvoker.isIndexExists(specimens,specimenName)){
						specimens.push(specimenName);
					}
				});
				var curTemplate = result.testLayout;
				if(curTemplate=='General'){
					if(tests.length==0){
						layoutTemplate = new Layouts_Template();
						layoutTemplate.setTestLayout(curTemplate);
					}
					tests.push(layoutUpdater.generateGeneral(result));
				} else if(curTemplate=='Urine'){
					if(tests.length>0){
						layoutTemplate.setTests(tests);
						layoutList.push(layoutTemplate);
						tests=[];
					}
					layoutTemplate = new Layouts_Template();
					layoutTemplate.setTestLayout(curTemplate);
					tests.push(layoutUpdater.generateUrine(result));
					layoutTemplate.setTests(tests);
					layoutList.push(layoutTemplate);
					tests=[];
				}  else if(curTemplate=='Layout1'){
					if(tests.length>0){
						layoutTemplate.setTests(tests);
						layoutList.push(layoutTemplate);
						tests=[];
					}
					layoutTemplate = new Layouts_Template();
					layoutTemplate.setTestLayout("Widal");
					tests.push(layoutUpdater.generateWidal(result));
					layoutTemplate.setTests(tests);
					layoutList.push(layoutTemplate);
					tests=[];
				} else if(curTemplate=='SemenLayout'){
					if(tests.length>0){
						layoutTemplate.setTests(tests);
						layoutList.push(layoutTemplate);
						tests=[];
					}
					layoutTemplate = new Layouts_Template();
					layoutTemplate.setTestLayout("Semen");
					tests.push(layoutUpdater.generateSemen(result));
					layoutTemplate.setTests(tests);
					layoutList.push(layoutTemplate);
					tests=[];
				}
				if(result.userId){
					if(!commonMethodInvoker.isIndexExists(userIds, result.userId)){
						var user = new VerifierDTO();
						user.setUserId(result.userId);
						user.setName(result.userName);
						user.setDesignation(result.userDesignation);
						var userInfo=query.viewUser(result.userId, branchId);
						user.setSignature(userInfo.signature);
						user.setIndex(++count);
						userList.push(user);
						userIds.push(result.userId);
					}
				}
			}
		});
		if(tests.length>0){
			layoutTemplate.setTests(tests);
			layoutList.push(layoutTemplate);
			tests=[];
		}
		var header = $.parseJSON(order.headerData).header;
		var specimen = "";
		$(specimens).each(function(index, specimenName){
			if(specimen=="")
				specimen=specimenName;
			else
				specimen=specimen+","+specimenName;
		});
		header["specimen"]=specimen;
		layout.setResultHeader(header);
		layout.setLayouts(layoutList);
		layout.setResultFooter(userList);
		var f = $('<form method="post" action="' + constants.SHOWRESULTURL + '" target="_blank"></form>');
		var inputTag = $('<input type="hidden" name="input" id="input" />');
		inputTag.attr('value',JSON.stringify(layout));
		f.html(inputTag);
		f.appendTo($('body')); // required for submission to work in Firefox
		f.submit();
		f.remove();
	}
}