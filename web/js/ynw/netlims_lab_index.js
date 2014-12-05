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
	this.FACILITYORDERLISTJSONURL="/youNeverWait/json/netlims/facilityOrderList.json";
	this.ONLINERESULTSLISTJSONURL="/youNeverWait/json/netlims/orderTable.json";
	this.BRANCHLISTURL="/youNeverWait/netlims/ui/lab/branchList";
	this.FACILITYLISTURL="/youNeverWait/netlims/ui/lab/facility/list";
	this.FACILITYORDERLISTURL="/youNeverWait/netlims/ui/order/facility/orders/getByFilter";
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
	this.SELECTONEORDER = "Select atleast one order";
	this.SELECTONEORDERONLY = "Select only one order";
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
	this.FINDORDERCOUNTURL="/youNeverWait/netlims/ui/order/count";
	this.SYNCSETUPPAGEURL="/youNeverWait/json/netlims/syncSetup.json";
	this.SYNCSETUPMODALNAME="syncSetupModal";
	this.SYNCDATAURL="/youNeverWait/netlims/ui/lab/getBranchSyncDetails/";
	this.SYNCUPDATEURL="/youNeverWait/netlims/ui/lab/setBranchSync";
	this.ORDERTYPEJSONURL="/youNeverWait/json/netlims/orderType.json";
	this.ORDERTYPEMODAL="orderTypeModal";
	this.GETORDERTYPEURL="/youNeverWait/netlims/ui/order/getOrderType/";
	this.UPDATEORDERTYPEURL="/youNeverWait/netlims/ui/order/setOrderType";
	this.GETTESTSURL = "/youNeverWait/netlims/ui/order/getTests/";
	this.ORDERPAGETOOLBAR = "/youNeverWait/json/netlims/orderPageBar.json";
	this.VIEWORDERPAGEURL = "/youNeverWait/json/netlims/orderTests.json";
	this.HOME = "home";
	this.HOMEPAGETOOLBAR = "/youNeverWait/json/netlims/homePageBar.json";
	this.DELIVERED="Delivered";
	this.LAYOUTGENERAL="General";
	this.LAYOUTGENERALONE="GeneralOne";
	this.LAYOUTGENERALMED="GeneralMed";
	this.LAYOUT1="Layout1";
	this.LAYOUTSEMEN = "Semen Layout";
	this.LAYOUTDC ="DC";
	this.LAYOUTCULTURE ="Culture Report";
	this.LAYOUTWATERCULTURE="WaterCultureReport";
	this.LAYOUTHAEMOGRAM = "Haemogram";
	this.LAYOUTHAEMOGRAMESR ="HaemogramESR";
	this.LAYOUTAMINOACIDOGRAM="Aminoacidogram";
	this.LAYOUTOSMOTIC="Osmotic";
	this.LAYOUTSTOOLANALYSIS="Stool Analysis";
	this.LAYOUTENA="ENA";
	this.LAYOUTSTONEANALYSIS ="Stone Analysis" ;
	this.LAYOUTURINE = "Urine";
	this.LAYOUTAFB = "AFB";
	this.LAYOUTCD = "CD";
	this.LAYOUTGTT = "GTT";
	this.LAYOUTPERIPHERAL = "Peripheral";
	this.LAYOUTPT = "PT";
	this.LAYOUTAPPT = "APPT";
	this.LAYOUTHISTOPATH = "HistoPath";
	this.LAYOUTLIPID = "LipidLayout";
	this.LAYOUTLFT = "LFT";
	this.LAYOUTANA = "ANA";
	this.LAYOUTGCT="GCT";
	this.LAYOUTBETAHCG = "BetaHCGLayout";
	this.MODEPRINT = "print";
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
	this.getTests = function(orderId) {
		ajaxProcessor.setUrl(constants.GETTESTSURL+orderId);
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
				$('.filter-main').hide();
				pageHandler.setHomePage(constants.BRANCHESHOME);
				var branchListUI = new BranchListUI();	
				branchListUI.init(user.labId);
			});
			$(self.branchOrdersLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).hide();
				$('.filter-main').hide();
				pageHandler.setHomePage(constants.BRANCHORDERSHOME);
				var branchesOrders = new BranchOrders();	
				branchesOrders.init(user.labId);
			});
			$(self.transferredOrdersLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).hide();
				$('.filter-main').hide();
				pageHandler.setHomePage(constants.BRANCHORDERSHOME);
				var orders = new TransferredOrders();	
				orders.init(user.labId);
			});
			$(self.transferredResultLink).die('click').live("click",function() {		
				var obj=$(this);
				errorHandler.removeErrors();
				$(constants.FILTER).hide();
				$('.filter-main').hide();
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
				$('.filter-main').hide();
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
	this.orderListPTB = "#branchListPTBContainer #btn_orderList_ptb_id";
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
	this.orderListPTB = "#facilityListPTBContainer #btn_orderlist_ptb_id";
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
		$(self.orderListPTB).die('click').live('click',function() {
			errorHandler.removeErrors();
			var facilityId=self.getSelectedfacilityId(self.pgTableName);
			if(facilityId!="") {
				self.setCurrentFacilityId(facilityId);
				orderList = new OrderList();
				orderList.init(facilityId);
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
function OrderList(){
	this.orderCountPTB = "#facilityListPTBContainer #btn_ordercount_ptb_id";
	this.pgTableContainer = "#orderListTableCont";
	this.pgTableName=this.pgTableContainer + " #orders";
	this.facility = "facility";
	this.facilityListPTBCaption="facilityList";
	this.pgTableRowClass=".facilityCol";
	this.exp = new ExpressionListDTO();
	this.listUrl =constants.FACILITYORDERLISTURL;
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.setCurrentFacilityId=function(facilityId){this.selectedFacilityId = facilityId};
	this.setCurrentFacilityList = function(facilityList) {this.facilityList=facilityList};
	this.getCurrentFacilityList = function(){return this.facilityList};
	this.setCurrentBranchId = function(branchId) {this.branchId=branchId};
	this.getCurrentFacilityId=function(){return this.selectedFacilityId};
	this.getCurrentBranchId = function(){return this.branchId};
	this.init=function(facilityId){
		this.setCurrentFacilityId(facilityId);
		$(constants.PTBCONTAINER).empty().html('');
		$(constants.PAGETITLE).empty().html("Order List");
		dataTableProcessor.create(this.pgTableName,constants.ONLINERESULTSLISTJSONURL,constants.CONTAINER);
		dataTableProcessor.setCustomTable(this.pgTableName);
		var expList=new ExpressionListDTO();
		var expr = new ExpressionDTO("facilityId",facilityId,"eq");
		expList.add(expr);
		this.tableNavigator.setExp(expList);
		this.tableNavigator.list();
	//	var ptbProcessor = new PageToolBarProcessor();
	//	ptbProcessor.create(this.facilityListPTBCaption, constants.FACILITYLISTPTBJSONURL);
	//	this.bindEvents();
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
		$(self.orderListPTB).die('click').live('click',function() {
			errorHandler.removeErrors();
			var facilityId=self.getSelectedfacilityId(self.pgTableName);
			if(facilityId!="") {
				self.setCurrentFacilityId(facilityId);
				orderList = new OrderList();
				orderList.init(facilityId);
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
	this.setSetting= function(setting) {this.setting = setting;}
	this.getPageSettingByKey=function(key) {
		var self=this;
		var result = null; 
		$(self.setting).each(function(index, data){
			if(data.key==key){
				result = data;
				return false;
			}
		});
		return result;
	}
	this.getAgeValue=function(age){
		age= ""+age;
		var curAge="";
		if(age.indexOf('D')!=-1 || age.indexOf('d')!=-1){
			age = parseFloat(age);
			curAge = age/360;
		} else if(age.indexOf('M')!=-1 || age.indexOf('m')!=-1){
			age = parseFloat(age);
			curAge = (age*30)/360;
		} else if(age!=""){
			age=parseFloat(age);
			curAge = age;
		}
		return curAge;
	}
	this.getCurrentNormalRange = function(order, testResult) {
		var self=this;
		var curNormalRange = "";
		var age=order.header.age;
		var curAge = age.split("-");
		var curYear=0, curMonth=0; curDay=0;
		years = parseFloat(curAge[0],10)||0;
		curYear = years * 360;
		curMonth = parseFloat(curAge[1],10)||0;
		curMonth = curMonth * 30;
		curDay = parseFloat(curAge[2],10)||0;
		curDay = curDay + curMonth + curYear;
		curYear = curDay/360;
		age = curYear;
		var state=false;
		if(order.header.gender=='Male'){
			if(testResult.normalRange){
				$(testResult.normalRange).each(function(index, resultGenderLevel){
					if(resultGenderLevel.sex=='male'){
						$(resultGenderLevel.values).each(function(index1, resultAgeLevel){
							var curMinAge = self.getAgeValue(resultAgeLevel.minAge);
							var curMaxAge = self.getAgeValue(resultAgeLevel.maxAge);
							if(age=="")
								return;							
							if(curMinAge<=age && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge==null && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge<age && resultAgeLevel.maxAge==null){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							}															
						});
						if(state==true)
						return false;
					}
				});
			}
		} else {
			if(testResult.normalRange){
				$(testResult.normalRange).each(function(index, resultGenderLevel){
					if(resultGenderLevel.sex=='female'){
						$(resultGenderLevel.values).each(function(index1, resultAgeLevel){
							var curMinAge = self.getAgeValue(resultAgeLevel.minAge);
							var curMaxAge = self.getAgeValue(resultAgeLevel.maxAge);
							if(age=="")
								return;
								
							if(curMinAge<=age && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge==null && curMaxAge>=age){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							} else if(curMinAge<age && resultAgeLevel.maxAge==null){
								if(resultAgeLevel.minValue!=null && resultAgeLevel.maxValue!=null){
									curNormalRange=resultAgeLevel.minValue + " - " + resultAgeLevel.maxValue + " " + testResult.testUnit;
								} else if(resultAgeLevel.minValue==null){
									curNormalRange="< " + resultAgeLevel.maxValue + " " + testResult.testUnit; 
								} else if(resultAgeLevel.maxValue==null){
									curNormalRange="> " + resultAgeLevel.minValue + " " + testResult.testUnit; 
								}
								state=true;
								return false;
							}															
						});
						if(state==true)
						return false;
					}
				});
			}
		}
		return curNormalRange;
	}
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
	this.viewTestPage = "#viewTest";
	this.testTable= this.viewTestPage + " #testTable";
	this.ftbToolBar = "#order-filter-toolbar";
	this.filterActionButton = this.ftbToolBar + ' #btnGo';
	this.errorHeader= $('#errorDivHeader');
	this.errorData = $('#errorDivData');
	this.chkSelectTestClassName = "printTestSelection";
	this.chkSelectTest=  ".printTestSelection";
	this.chkSelectAllTest="#printTestSelectAll";
	this.listUrl=constants.ONLINERESULTSURL;
	this.tableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this,this.exp);
	this.setLabId=function(labId){this.labId=labId;}
	this.getLabId=function(){return this.labId;}
	this.setOrder = function(orderId){this.order = query.getOrder(orderId);}
	this.getBranchId =function() {return this.branchId;}
	this.setBranchId = function(branchId) {this.branchId = branchId;}
	this.getOrderId =function() {return this.orderId;}
	this.setOrderId = function(orderId) {this.orderId = orderId;}
	this.getOrder = function() {return this.order;}
	this.getTestList = function() {return this.testList;}
	this.setPageTitle = function(value) {this.pageTitle.empty().html(value);}
	this.setTestList = function(testList) {	this.testList=testList;}

	this.init=function(labId){
		this.setLabId(labId);
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
		var ptbProcessor = new PageToolBarProcessor();
		ptbProcessor.create(constants.ORDER, constants.ORDERPAGETOOLBAR);
		this.bindEvents();
	}
	this.view = function(order_Branch_Id) {
		orderbranchId = order_Branch_Id.split('_');
		orderId = orderbranchId[0];
		branchId = orderbranchId[1];
		this.setOrderId(orderId);
		this.setOrder(orderId);
		this.setBranchId(branchId);
		var tests = query.getTests(orderId);
		this.setTestList(tests);
		pageHandler.create(constants.VIEWORDERPAGEURL);
		commonMethodInvoker.setViewTable(this.testTable);
		var ptbProcessor = new PageToolBarProcessor();
		ptbProcessor.create(constants.HOME, constants.HOMEPAGETOOLBAR);
		this.setTests(tests);
		this.viewEvents();
	}
	this.getOrderHeader = function() {return this.orderHeader;}
	this.getSetting = function() {return this.setting;}
	this.setResultPrintObj=function(resultPrintObj){this.resultPrintObj=resultPrintObj;}
	this.getResultPrintObj=function(){return this.resultPrintObj;}
	this.setOrderHeader_Setting = function(header_setting){
		this.orderHeader=$.parseJSON(header_setting).header;
		this.setting=$.parseJSON(header_setting).settings;
		methodInvoker.setSetting(this.setting);
	}
	this.getPageSettingByKey = function(key){
		var result = null; 
		var self=this;
		var result = null; 
		$(self.setting).each(function(index, data){
			if(data.key==key){
				result = data;
				return false;
			}
		});
		return result;
	}
	this.generateSelectedTestList = function(selectedList){
		var resultList = [];
		$(this.getTestList()).each(function(index, test){
			if(commonMethodInvoker.isIndexExists(selectedList,test.uid)==true)
				resultList.push($.parseJSON(test.result));
		});
		return resultList;
	}
	this.setTests = function(tests) {
		self=this;
		var checkbox = '<input type="checkbox" class="' + this.chkSelectTestClassName + '"/>';
		$(tests).each(function(){
			var testTable=$(self.testTable).dataTable().fnAddData([this.uid, this.testName,checkbox]);
			var row=$(tableObj).dataTable().fnSettings().aoData[testTable].nTr;
			$(row).attr('id',this.uid);
			
		});
	}
	this.getSelectedOrder_BranchId=function() {
		var self=this;
		var orderId="";
		if($(this.pgTableName).dataTable().fnGetData().length>0) {
			var selOrders = $(self.pgTableName + ' tbody tr[selected]');
			if(selOrders.length==0){
				errorHandler.createServerError(self.errorHeader,self.errorData, constants.SELECTONEORDER);
			} else if(selOrders.length>1) 
				errorHandler.createServerError(self.errorHeader,self.errorData, constants.SELECTONEORDERONLY);
			else
				orderId=selOrders.attr('id');
		}
		return orderId;
	}
	this.bindEvents=function(){
		var self=this;
		$('#orderPTBContainer #btn_view_ptb_id').die('click').live('click',function() {
			var objId = self.getSelectedOrder_BranchId(self.pgTableName);
			if(objId!="") 
				self.view(objId);

		});
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
			var expr = new ExpressionDTO("labId",self.getLabId(),"eq");
			expList.add(expr);
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
	this.viewEvents = function() {
		var self=this;
		var order = self.getOrder();
		self.setOrderHeader_Setting(order.headerData);
		$(self.chkSelectAllTest).die('click').live('click',function(e) {
			var obj=$(this);
			if(obj.attr('checked'))
				$(self.chkSelectTest).each(function(){
					$(this).closest('tr').attr('selected','selected');
					$(this).closest('tr').attr('style',constants.SELECTEDROWCOLOR);
					$(this).attr('checked',true);
				});
			else
				$(self.chkSelectTest).each(function(){
					$(this).removeAttr('checked');
					$(this).closest('tr').removeAttr('selected');
					$(this).closest('tr').removeAttr('style');
				});
		});
		$(self.testTable + ' tbody tr').die('click').live('click',function(){
			var obj = $(this);
			var objId=$(this).attr('id');		
			if($(this).attr('selected')) {
				$(this).removeAttr('selected');
				$(this).removeAttr('style');
			} else {	
				$(this).attr('selected','selected');
			}
			if(obj.attr('selected')) {
				$(this).children('td').children(self.chkSelectTest).attr('checked',true);
				if($(self.testTable + " tbody tr td input[class='" + self.chkSelectTestClassName + "']:checked").length == $(self.testTable).dataTable().fnGetData().length)
					$(self.chkSelectAllTest).attr('checked',true);
			} else {
				$(this).children('td').children(self.chkSelectTest).removeAttr('checked');
				$(self.chkSelectAllTest).removeAttr('checked');
			}
		});
		$('#homePTBContainer #btn_home_ptb_id').die('click').live('click',function() {
			$('#pageToolBar-Container').empty().html('');
			self.init(self.getLabId());	
		});
		$('#btnPrint').die('click').live('click',function(e) {
			var printStatus=true;
			var obj = $(this);
			/*Select Tests to Print*/
			var testArray = []; //To store the selected tests
			$(self.testTable + ' tbody tr td input[class="printTestSelection"]:checked').each(function(){
				testArray.push($(this).closest('tr').attr('id'));
			});
			var resultDataGenerator = new ResultDataGenerator();
			var orderHeader=self.getOrderHeader();
			var response = self.generateSelectedTestList(testArray);
			if(response.length<=0)
				return false;
			status="";
			if(status==constants.DELIVERED) {
				var printResultArray = resultDataGenerator.generatedDeliveredResult(self, testArray, response);
				var resultEntryParent = $('<div class="parent"/>');
				var resultEntryForm=$('<div class="inner-data"/>');
				var resultProcessor = new ResultPreviewProcessor(self);
				$(printResultArray).each(function(index, result) {
					var previewResult = resultProcessor.preview("", $.parseJSON(result), orderHeader.orderId, order,printStatus);
					resultEntryForm.append(previewResult);
				});	
				resultEntryParent.html(resultEntryForm);
				resultEntryAction = $('<div class="box" style="width:98%; margin-left:10px; padding:0px 0px 5px 0px"/>'); // create div for store the save button
				var savebutton = $('<input type="button" class="stdbtn" value="Print" id="btnPrintResult"/>');
				resultEntryAction.html(savebutton);	
				resultEntryParent.append(resultEntryAction);
				createResultModal(resultEntryParent,"finalresultModal","","215mm");
				$('.noBorder').parent().addClass('noBorder');
				e.preventDefault();
				openModalBox(obj,"finalresultModal");
			} else {
				var resultJson;
				var resultPrintObj = new ResultPrintResponseDTO();
				result = resultDataGenerator.generateResultData(self, testArray, response);	
				resultJson = $.parseJSON(result.getPrintResult());
				resultPrintObj.setOrderUid(result.getOrderUid());
				resultPrintObj.setResult(result.getResult());
				resultPrintObj.setReportDate(result.getReportDate());
				resultPrintObj.setReportTime(result.getReportTime());
				resultPrintObj.setTestList(result.getTestList());
				resultPrintObj.setPrinted(true);
				self.setResultPrintObj(resultPrintObj);
				var resultEntryParent = $('<div class="parent"/>');
				var resultEntryForm=$('<div class="inner-data"/>');
				var resultProcessor = new ResultPreviewProcessor(self);
				var previewResult = resultProcessor.preview("",resultJson,orderHeader.orderId, order,printStatus);
				resultEntryForm.html(previewResult);
				resultEntryParent.html(resultEntryForm);
				resultEntryAction = $('<div class="box" style="width:98%; margin-left:10px; padding:0px 0px 5px 0px"/>'); // create div for store the save button
				var printbutton = $('<input type="button" class="stdbtn" value="Print" id="btnPrintResult"/>');
				resultEntryAction.html(printbutton);	
				resultEntryParent.append(resultEntryAction);
				createResultModal(resultEntryParent,"finalresultModal","","204mm");
				$('.noBorder').parent().addClass('noBorder');
				e.preventDefault();
				openModalBox(obj,"finalresultModal");
			}
		});	
		$('#btnPrintResult').die('click').live('click',function(e) {
			e.preventDefault();
			errorHandler.removeErrors();
			var resultPrintObj = self.getResultPrintObj();
			var buffer=$('<div/>');
			var pageNos = $('#finalresultModal .pageRoot').length;
			var count=1;
			var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
			newLayouts=layoutStatus.visible;
			if(newLayouts==true) {		
				var resultHeight = $(".printresultTable").height() +  $(".remarks").height();
				var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight, methodInvoker.getDPI()).toFixed();	
				var contentset =methodInvoker.getPageSettingByKey('content');
				var headerset =methodInvoker.getPageSettingByKey('header');
				var originalHeight = parseInt(contentset.height,10) || 0;
				var marginHeight = parseInt(contentset.marginTop,10) || 0;
				var totalHeight = originalHeight-marginHeight;
				var len = 0;
				var pagecount=1;
				var page = $('<div class="page">&nbsp;</div>');
				var header = $('.pageRoot .resultHeader');
				var footer = $('.pageRoot .printReportFooter'); 
				var content = $('<div class="pageResultContent" />');
				content.css("height",totalHeight + "mm");
				content.css("width",contentset.width+"mm");
				content.css("margin-top",contentset.marginTop+"mm");
				content.css("font-size",contentset.fontSize + "px");
				page.height("297mm");
				//page.append(header);
				content.css("margin-left",headerset.marginLeft + "mm");
				footer.css("margin-left",headerset.marginLeft + "mm");
				$('.pageRoot .one').each(function() {
					pixelvalue = $(this).height();
					content.append($(this));
					len=len + commonMethodInvoker.getmmFromPixel(pixelvalue, methodInvoker.getDPI());
					if(len>=totalHeight){
						pagecount+=1;
						len=0;
						page.append(header.clone());
						page.append(content);
						page.append(footer.clone());
						buffer.append(page.clone());
						page.empty();
						content.empty();
						//var pageDiv = $('<div style="height:2mm;">&nbsp;</div>');
						buffer.append(pageDiv);
					}
				});
				if(len!=0) {
					page.append(header.clone());
					page.append(content);
					page.append(footer.clone());
					buffer.append(page);
				}
			} else {
				$('#finalresultModal .pageRoot').each(function() {
					var tbl = $(this);
					tbl.height("297mm");
					if(count>1) {
					//	var pageDiv = $('<div style="height:2mm;">&nbsp;</div>');
					//	buffer.append(pageDiv);
					}	
					buffer.append(tbl.html());
					count++;
				});
			}
			var printHandler = new ResultPrintHandler();
			var resultHTML = printHandler.generateHtmlString(buffer.html());
			printHandler.print(resultHTML);
			$('#finalresultModal').trigger('reveal:close',"veryfast");
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
}
function ResultPreviewProcessor(orderReference) {
	this.orderReference = orderReference;
	this.getOrderUI = function(){return this.orderReference;}
	//Function creating the Preview of Results
	this.preview = function( honorific,combinedResult,orderId, order,printStatus) {
		self = this;
		var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
		newLayouts=layoutStatus.visible;
		var reportRoot = $('<div id="resultRoot"/>');
		if(combinedResult.resultList)
			resultList=combinedResult.resultList;
		else	
			resultList=combinedResult;
		$(resultList).each(function (index, result) {
			if(newLayouts==false)
				reportRoot.append(self.getResult(honorific,result,combinedResult,orderId,order,printStatus));
			else
				reportRoot.append(self.generate(honorific,result,combinedResult,orderId,order));
		});	
		return reportRoot;
	}	
	this.getResult = function(honorific,result,combinedResult,orderId,order,printStatus) {
		var orderUI = this.getOrderUI();
		var ReportPageRoot = $('<div class="pageRoot" />');	
		if(printStatus!=true){
		var wrapper = $('<div style="background-color:#D6FCFF;" class="groupResultTable" />');
		}else{
		var wrapper = $('<div class="groupResultTable" />');
		}
		var resultContent = $('<div class="pageResultContent"  />');
		var resultParent = result;
		var parentTable = $('<table  style="width:90%; margin:auto; position:relative;"  />');	
		var remarksDiv = $('<div class="remarks"/>');
		var remarksHeaderTag = $('<p><b>Remarks</b></p>');
		var remarksTag = $('<p/>');	
		//Array used for holding the test/testpackage ids already listed
		var displayedIndex =[];
		var tpCount = 0;
		while((displayedIndex.length+tpCount)<result.testResult.length) {
			var trTag= $('<tr/>');
			var tdTag=$('<td>');
			var tblObj = $('<table width="95%" class="printresultTable"/>');
			var status=true;
			var headerData="";	
			// Iterating through tests and packages			
			$(result.testResult).each(function (resultIndex, singleResult) {
				//check whether the result already displayed
				isExistStatus =commonMethodInvoker.isIndexExists(displayedIndex, resultIndex);	
				if(isExistStatus==false) {
					if(singleResult.IsTstPkgTitle!=undefined) {
						if(singleResult.IsTstPkgTitle==false) {							
							$(singleResult.testResult).each(function (resultIndex1, tpTestResult) {
								if(headerData=="") {											
									if(tpTestResult.testLayout==constants.LAYOUTGENERAL || tpTestResult.testLayout==constants.LAYOUTGENERALMED || tpTestResult.testLayout==constants.LAYOUTGENERALONE || tpTestResult.testLayout==constants.LAYOUTDC) {
										if(status==true) {
											if(tpTestResult.testLayout==constants.LAYOUTDC)
												headerData = new DCLayoutHeader(honorific);
											else
												headerData = new generalLayoutHeader(tpTestResult, honorific);
											tblObj.append(headerData.result);
												status=false;
										}
										if(tpTestResult.testLayout==constants.LAYOUTGENERAL)
											var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific, order);	
										else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED)
											var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);	
										else if(tpTestResult.testLayout==constants.LAYOUTDC)
											var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
										else
											var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
										tblObj.append(modalData.result);
									} 	
								} else {
									if(tpTestResult.testLayout==constants.LAYOUTGENERALONE) {						 
										var tmpheaderData = new generalLayoutHeader(tpTestResult, honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);						
										findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {							
											var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);
											tblObj.append(modalData.result);
										}
									} else if(tpTestResult.testLayout==constants.LAYOUTGENERAL) {					
										var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);		
										//findHeaderstat = isEqual(header1.html(),header2.html());
										//if(findHeaderstat==true) {							
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific, order);
										tblObj.append(modalData.result);
										//}
									}else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED) {						
										var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result);
									}else if(tpTestResult.testLayout==constants.LAYOUTDC) {					
										var tmpheaderData = new DCLayoutHeader(honorific);
										var header1= $('<div/>');
										header1.append(headerData.result);	
										var header2= $('<div/>');
										header2.append(tmpheaderData.result);
										findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
										if(findHeaderstat==true) {	
											var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);
											tblObj.append(modalData.result);
										}
									}	
								}
							});
							displayedIndex.push(resultIndex);
						} else 
							tpCount++;
					} else {
						if(headerData=="") {
							if(singleResult.testLayout==constants.LAYOUTGENERAL || singleResult.testLayout==constants.LAYOUTGENERALONE || singleResult.testLayout==constants.LAYOUTDC) {
								if(status==true) {
									if(singleResult.testLayout==constants.LAYOUTDC)
										headerData = new DCLayoutHeader(honorific);
									else
										headerData = new generalLayoutHeader(singleResult, honorific);
									tblObj.append(headerData.result);
									displayedIndex.push(resultIndex);
									status=false;
								}
								var modalData;
								if(singleResult.testLayout==constants.LAYOUTGENERAL)
									modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);	
								else if(singleResult.testLayout==constants.LAYOUTDC)
									modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);	
								else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
									modalData = new generalLayoutMed(singleResult, null,constants.MODEPRINT,honorific);		
								} else
									var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);	
								tblObj.append(modalData.result);
							} else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
									var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);	
									tblObj.append(modalData.result);
									headerData = modalData.result;
									displayedIndex.push(resultIndex);		
							} else if(singleResult.testLayout==constants.LAYOUTCULTURE || singleResult.testLayout=="CultureReport" ) {
								var modalData = new cultureLayout(honorific,singleResult,null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTWATERCULTURE){
								var modalData = new waterCultureLayout(honorific,singleResult,null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAM){
								var modalData = new haemogramLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAMESR){
								var modalData = new haemogramESRLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAMINOACIDOGRAM){
								var modalData = new aminoacidLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTOSMOTIC){
								var modalData = new osmoticLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTSTOOLANALYSIS  || singleResult.testLayout=="StoolAnalysis"){
								var modalData = new stoolLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTENA){
								var modalData = new enaProfile(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout==constants.LAYOUTSTONEANALYSIS  || singleResult.testLayout=="StoneAnalysis"){
								var modalData = new stone(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTURINE){
								var modalData = new UrineLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAFB){
								var modalData = new AFBLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTCD){
								var modalData = new CDLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTGTT){
								var modalData = new GTTLayout(honorific,singleResult, null, constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTPERIPHERAL){
								var modalData = new PeripheralLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTPT){
								var modalData = new PTLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTAPPT){
								var modalData = new APPTLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTHISTOPATH){
								var modalData = new HistoPathLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTLIPID){
								var modalData = new LipidLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTLFT){
								var modalData = new LiverLayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTDC){
								var modalData = new DCLayout(singleResult,null, constants.MODEPRINT,honorific);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTANA){
								var modalData = new ANALayout(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTBETAHCG) {	
								tblObj.attr('style',"border:none;");
								var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
								tblObj.append(modalData.result);
								displayedIndex.push(resultIndex);
								tblObj.append('<br/>');
								headerData = "";									
								return false;
							} else if(singleResult.testLayout==constants.LAYOUTSEMEN || singleResult.testLayout=="SemenLayout"){
								var modalData = new SemenLayout(honorific,singleResult, null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout==constants.LAYOUTGCT){
								headerData = new DCLayoutHeader(honorific);
								tblObj.append(headerData.result);
								var modalData = new NVGCTLayout(singleResult, null,constants.MODEPRINT,honorific);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="WidalLayoutMed"){
								var modalData = new widal(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="UrineLayoutMed") {
								var modalData = new urineLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout=="FoodAllergy") {
								var modalData = new foodAllergy(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							}  else if(singleResult.testLayout=="ANAIFALayout") {
								var modalData = new anaIfa(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="DCLayoutMed") {
								var modalData = new DCLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="LipidLayoutMed"){
								var modalData = new lipidLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="LFTLayoutMed") {
								var modalData = new liverLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else if(singleResult.testLayout=="HaemogramLayoutMed") {
								var modalData = new haemogramLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;	
							} else {
								var modalData = new layout1(honorific,singleResult,null,constants.MODEPRINT);
								displayedIndex.push(resultIndex);
								tblObj.append(modalData.result);
								headerData = "";									
								return false;
							}	
						} else {
						    if(singleResult.testLayout==constants.LAYOUTGENERALONE) {						 
								var tmpheaderData = new generalLayoutHeader(singleResult,honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTGENERAL) {					
								var tmpheaderData = new generalLayoutHeader(singleResult,honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);						
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {							
									var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTDC) {					
								var tmpheaderData = new DCLayoutHeader(honorific);
								var header1= $('<div/>');
								header1.append(headerData.result);	
								var header2= $('<div/>');
								header2.append(tmpheaderData.result);
								findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
								if(findHeaderstat==true) {	
									var modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
									displayedIndex.push(resultIndex);
								}
							} else if(singleResult.testLayout==constants.LAYOUTGENERALMED){
								var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);									
								tblObj.append(modalData.result);
								displayedIndex.push(resultIndex);
							}	
						}
					}	
				}		
			});	
			tdTag.append(tblObj);
			trTag.append(tdTag);	
			parentTable.append(trTag);	
		}
		if(tpCount>0) {
			$(result.testResult).each(function (resultIndex, singleResult) {
				if(singleResult.IsTstPkgTitle!=undefined) {
					if(singleResult.IsTstPkgTitle==true) {
						trTag= $('<tr/>');
						tdTag=$('<td class="titleClass" />');
						tdTag.html(singleResult.testName);
						trTag.append(tdTag);
						parentTable.append(trTag);
						trTag= $('<tr/>');
						tdTag=$('<td />');					
						var tblObj = $('<table width="100%" class="printresultTable"/>');
						var status=true;
						headerData = "";
						$(singleResult.testResult).each(function (resultIndex, tpTestResult) {
							if(headerData=="") {											
								if(tpTestResult.testLayout==constants.LAYOUTGENERAL || tpTestResult.testLayout==constants.LAYOUTGENERALMED || tpTestResult.testLayout==constants.LAYOUTGENERALONE || tpTestResult.testLayout==constants.LAYOUTDC) {

									if(status==true) {
										if(tpTestResult.testLayout==constants.LAYOUTDC)
											headerData = new DCLayoutHeader(honorific);
										else
											headerData = new generalLayoutHeader(tpTestResult,honorific);
										tblObj.append(headerData.result);
											status=false;
									}
									if(tpTestResult.testLayout==constants.LAYOUTGENERAL)
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific,order);	
									else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED)
										var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);				
									else if(tpTestResult.testLayout==constants.LAYOUTDC)
										var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
									else
										var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);	
									tblObj.append(modalData.result);
								} 	
							} else {
								if(tpTestResult.testLayout==constants.LAYOUTGENERALONE) {						 
									var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);						
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {							
										var modalData = new generalOneLayout(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result); 
									}
								} else if(tpTestResult.testLayout==constants.LAYOUTGENERAL) {					
									var tmpheaderData = new generalLayoutHeader(tpTestResult,honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);						
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {							
										var modalData = new generalLayout(tpTestResult, null, constants.MODEPRINT,honorific,order);
										tblObj.append(modalData.result);
									}
								}else if(tpTestResult.testLayout==constants.LAYOUTGENERALMED) {					
									var modalData = new generalLayoutMed(tpTestResult, null, constants.MODEPRINT,honorific);
									tblObj.append(modalData.result);
								}else if(tpTestResult.testLayout==constants.LAYOUTDC) {					
									var tmpheaderData = new DCLayoutHeader(honorific);
									var header1= $('<div/>');
									header1.append(headerData.result);	
									var header2= $('<div/>');
									header2.append(tmpheaderData.result);
									findHeaderstat = commonMethodInvoker.isEqual(header1.html(),header2.html());
									if(findHeaderstat==true) {	
										var modalData = new DCLayout(tpTestResult, null, constants.MODEPRINT,honorific);
										tblObj.append(modalData.result);
									}
								}	
							}
						});
						tdTag.append(tblObj);
						trTag.append(tdTag);	
						parentTable.append(trTag);	
					}
				}
			});
		}
		//remarks section
		if(!result.testName) {				
				$(result.testResult).each(function (resultIndex, singleResult) {
						var remark=singleResult.remarks;
						if(remark!=null && remark.trim()!="") {
							var pTag = $('<p/>');	
							var remarkInfo = commonMethodInvoker.nl2br(remark);
							remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
							pTag.append(remarkInfo);
							remarksTag.append(pTag);
						}
				});
		} else {
			var remark=result.remarks;
			if(remark!=null && remark.trim()!="") {
				var pTag = $('<p/>');	
				var remarkInfo = commonMethodInvoker.nl2br(remark);
				remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
				pTag.append(remarkInfo);
				remarksTag.append(pTag);
			}
		}
		if(remarksTag.html().trim().length!=0) {
			remarksDiv.append(remarksHeaderTag);
			remarksDiv.append(remarksTag.html());
		}
		resultContent.append(parentTable);
		resultContent.append(remarksDiv);
		var header = new ResultHeaderProcessor(orderUI);
		var repHeader = header.make(resultParent,combinedResult,orderId,honorific,printStatus);
		wrapper.append(repHeader);
		var contentset =methodInvoker.getPageSettingByKey('content');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		resultContent.css("height",totalHeight + "mm");
		resultContent.css("width",contentset.width+"mm");
		resultContent.css("margin-top",contentset.marginTop+"mm");
		resultContent.css("font-size",contentset.fontSize + "px");
		wrapper.append(resultContent);
		var headerset =methodInvoker.getPageSettingByKey('header');
		var footerset =methodInvoker.getPageSettingByKey('footer');
		var footer = new ResultFooterProcessor(orderUI);
		var repFooter = footer.create(resultParent,combinedResult,orderId);
		wrapper.append(repFooter);
		totalHeight=parseInt(headerset.height) + parseInt(footerset.height) +parseInt(contentset.height);
		wrapper.css("height","297mm");
		//wrapper.css("height",totalHeight+"mm");
		wrapper.css("width",contentset.width+"mm");
		ReportPageRoot.append(wrapper);
		return ReportPageRoot;
	}
	this.generate = function(honorific,result,combinedResult,orderId,order) {
		var orderUI = this.getOrderUI();
		ReportPageRoot = $('<div class="pageRoot"/>');		
		var wrapper = $('<div class="groupResultTable" />');
		var resultContent = $('<div class="pageResultContent"  />');
		var resultParent = result;
		var parentTable = $('<div  style="width:100%;"  />');	
		var remarksDiv = $('<div class="remarks"/>');
		var remarksHeaderTag = $('<p class="nomargin"><b>Remarks</b></p>');
		var remarksTag = $('<p/>');	
		remarksTag.addClass('nomargin');
		var tblObj = $('<div class="printresultTable" style="float:left;width:100%"/>');
		var departmentList = [];
		$(result.testResult).each(function (resultIndex, singleResult) {
			departmentName=null;
			if(singleResult.departmentName)
				departmentName = singleResult.departmentName;
			else
				departmentName=" ";
			if(!commonMethodInvoker.isIndexExists(departmentList,departmentName)){
				departmentList.push(departmentName);
				if(departmentName!=null){
					var tbody=$('<div class="one singleheight "/>');
					var tdTag = $('<div class="onefirst">&nbsp;</div>');
					tbody.empty().html(tdTag);
					tdTag = $('<div class="onesecond bold underline">&nbsp;</div>');
					tdTag.empty().html(singleResult.departmentName);
					tbody.append(tdTag);
					tdTag = $('<div class="onesecond">&nbsp;</div>');
					tbody.append(tdTag);
					tblObj.append(tbody);
					parentTable.append(tblObj);
				}	
			}
			if(singleResult.testResult) {
				if(singleResult.IsTstPkgTitle==true){
					var tbody=$('<div class="one underline bold"/>');
					tbody.append(singleResult.testName);
					tblObj.append(tbody);
				}
				$(singleResult.testResult).each(function (resultIndex1, subResult) {
					var modalData = self.generateLayoutData(subResult, honorific, order);
					tblObj.append(modalData.result);
				});
			} else {
				var modalData = self.generateLayoutData(singleResult, honorific, order);
				tblObj.append(modalData.result);
			}
		});	
		//remarks section
		if(!result.testName) {			
			$(result.testResult).each(function (resultIndex, singleResult) {
				var remark=singleResult.remarks;
				if(remark!=null && remark.trim()!="") {
					var pTag = $('<p />');	
					pTag.addClass('nomargin');
					var remarkInfo = commonMethodInvoker.nl2br(remark);
					remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
					pTag.append(remarkInfo);
					remarksTag.append(pTag);
				}
			});
		} else {
			var remark=result.remarks;
			if(remark!=null && remark.trim()!="") {
				var pTag = $('<p/>');	
				pTag.addClass('nomargin');
				var remarkInfo = commonMethodInvoker.nl2br(remark);
				remarkInfo  = remarkInfo.replaceAll(" ", "&nbsp;");
				pTag.append(remarkInfo);
				remarksTag.append(pTag);
			}
		}
		if(remarksTag.html().trim().length!=0) {
			remarksDiv.append(remarksHeaderTag);
			remarksDiv.append(remarksTag.html());
		}
		remarksDiv.addClass("one");
		remarksDiv.addClass("columnleftalign");
		tblObj.append(remarksDiv);
		parentTable.append(tblObj);
		resultContent.append(parentTable);
		var header = new ResultHeaderProcessor(orderUI);
		var repHeader = header.make(resultParent,combinedResult,orderId,honorific,printStatus);
		var repHeader = header.generate(resultParent,combinedResult,orderId,honorific);
		wrapper.append(repHeader);
		var contentset =methodInvoker.getPageSettingByKey('content');
		var headerset =methodInvoker.getPageSettingByKey('header');
		var footerset =methodInvoker.getPageSettingByKey('footer');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		resultContent.css("height",totalHeight + "mm");
		resultContent.css("width",contentset.width+"mm");
		resultContent.css("margin-top",contentset.marginTop+"mm");
		resultContent.css("font-size",contentset.fontSize + "px");
		resultContent.css("margin-left",headerset.marginLeft + "mm");
		wrapper.append(resultContent);
		var footer = new ResultFooterProcessor(orderUI);
		var repFooter = footer.generalFooter();
		wrapper.append(repFooter);
		totalHeight=parseInt(headerset.height) + parseInt(footerset.height) +parseInt(contentset.height);
		wrapper.css("height",totalHeight+"mm");
		//wrapper.css("width",contentset.width+"mm");
		ReportPageRoot.append(wrapper);
		return ReportPageRoot;
	}
	this.generateLayoutData =function(singleResult,honorific,order) {
		if(singleResult.testLayout==constants.LAYOUTGENERAL)
			var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);	
		else if(singleResult.testLayout==constants.LAYOUTDC)
			var modalData = new DCLayout(singleResult, null, constants.MODEPRINT,honorific);	
		else if(singleResult.testLayout==constants.LAYOUTGENERALONE)
			var modalData = new generalOneLayout(singleResult, null, constants.MODEPRINT,honorific);	
		else if(singleResult.testLayout==constants.LAYOUTGENERALMED)
			var modalData = new generalLayoutMed(singleResult, null, constants.MODEPRINT,honorific);									
		else if(singleResult.testLayout==constants.LAYOUTCULTURE || singleResult.testLayout=="CultureReport" )
			var modalData = new cultureLayout(honorific,singleResult,null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTWATERCULTURE)
			var modalData = new waterCultureLayout(honorific,singleResult,null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAM)
			var modalData = new haemogramLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHAEMOGRAMESR)
			var modalData = new haemogramESRLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAMINOACIDOGRAM)
			var modalData = new aminoacidLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTOSMOTIC)
			var modalData = new osmoticLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTSTOOLANALYSIS  || singleResult.testLayout=="StoolAnalysis")
			var modalData = new stoolLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTENA)
			var modalData = new enaProfile(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTSTONEANALYSIS  || singleResult.testLayout=="StoneAnalysis")
			var modalData = new stone(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTURINE)
			var modalData = new UrineLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAFB)
			var modalData = new AFBLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTCD)
			var modalData = new CDLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTGTT)
			var modalData = new GTTLayout(honorific,singleResult, null, constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTPERIPHERAL)
			var modalData = new PeripheralLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTPT)
			var modalData = new PTLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTAPPT)
			var modalData = new APPTLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTHISTOPATH)
			var modalData = new HistoPathLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTLIPID)
			var modalData = new LipidLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTLFT)
			var modalData = new LiverLayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTDC)
			var modalData = new DCLayout(singleResult,null, constants.MODEPRINT,honorific);
		else if(singleResult.testLayout==constants.LAYOUTANA)
			var modalData = new ANALayout(honorific,singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTBETAHCG)
			var modalData = new generalLayout(singleResult, null, constants.MODEPRINT,honorific,order);
		else if(singleResult.testLayout==constants.LAYOUTSEMEN || singleResult.testLayout=="SemenLayout")
			var modalData = new SemenLayout(honorific,singleResult, null,constants.MODEPRINT);
		else if(singleResult.testLayout==constants.LAYOUTGCT)
			var modalData = new NVGCTLayout(singleResult, null,constants.MODEPRINT,honorific);
		else if(singleResult.testLayout=="WidalLayoutMed")
			var modalData = new widal(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="UrineLayoutMed")
			var modalData = new urineLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="LipidLayoutMed")
			var modalData = new lipidLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="DCLayoutMed")
			var modalData = new DCLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="FoodAllergy") 
			var modalData = new foodAllergy(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="ANAIFALayout") 
			var modalData = new anaIfa(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="LFTLayoutMed") 
			var modalData = new liverLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else if(singleResult.testLayout=="HaemogramLayoutMed") 
			var modalData = new haemogramLayoutMed(honorific, singleResult,null,constants.MODEPRINT);
		else 
			var modalData = new layout1(honorific,singleResult,null,constants.MODEPRINT);
		return modalData;
	}
}
function ResultDataGenerator() {
	this.arrangeByDeparment=function(testResult){
		var testResult = [];
		var departmentList = [];
		/*find departments*/
		$(testResult).each(function(index, result) {
			var currentResult = $.parseJSON(result.result);
			departmentName="";
			if(currentResult.departmentName)
				departmentName=currentResult.departmentName;
			if(!commonMethodInvoker.isIndexExists(departmentList, departmentName))
				departmentList.push(departmentName);
		});
		$(departmentList).each(function(index, department) {
			$(testResult).each(function(index, result) {
				var currentResult = $.parseJSON(result.result);
				departmentName="";
				if(currentResult.departmentName)
					departmentName=currentResult.departmentName;
				if(commonMethodInvoker.isEqual(departmentName, department))
					testResult.push(result);
			});
		});
		return testResult;
	}
	this.createFinalJson = function(orderReference , testResult, reportDate, reportTime) {
		var finalJson = new FinalJSONDTO();
		var testList = [];
		var resultHeader = orderReference.getOrderHeader();
		finalJson.setResultHeader(resultHeader);	
		$(testResult).each(function(index, result) {
			var testDetails = new TestListJsonDTO();
			var specimens=[];
			testDetails.setSpecimens(specimens);
			if(result.reportingDate)
				testDetails.setReportingDate(result.reportingDate);
			else
				testDetails.setReportingDate(reportDate);
			if(result.reportingTime)
				testDetails.setReportingTime(result.reportingTime);
			else
				testDetails.setReportingTime(reportTime);
			testDetails.setResult(result);
			testList.push(testDetails);
		});
		finalJson.setTestList(testList);
		return finalJson;
	}
	this.generateResultData =function(orderReference ,testArray, testResult) {
		var self = this;
		var orderHeader = orderReference.getOrderHeader();
		var resultViewResponse = new ResultViewResponseDTO();//response to return	
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		var curdate = new Date();
		var reportDate = commonMethodInvoker.getUserDateFromSystemDate(curdate);
		var reportTime = commonMethodInvoker.getUserTimeFromSystemDate(curdate);
		var finaljson = self.createFinalJson(orderReference, testResult, reportDate, reportTime);
		resultViewResponse.setResult(finaljson);
		var verifiedTestIdsList = [];
		var testSpecimenList = [];
		var resultToPrint = this.generatePrintJson(orderReference, testArray, testResult,reportDate,reportTime);
		resultViewResponse.setPrintResult(resultToPrint);
		resultViewResponse.setTestList(testSpecimenList);
		resultViewResponse.setReportDate(reportDate);
		resultViewResponse.setReportTime(reportTime);
		resultViewResponse.setSuccess(true);	
		resultViewResponse.setPrinted(true);
		resultViewResponse.setOrderUid(orderHeader.uid);
		return resultViewResponse;
	}
	this.generatedDeliveredResult = function(orderReference, testArray, testResult) {
		var orderHeader = orderReference.getOrderHeader();
		var self=this;
		var resultHtml;
		var pageResult = [];
		var selectedTests = [];
		var finalResult = [];
		var orderResultResponseCopy = testResult;
		var status=true;
		$(orderResultResponseCopy).each(function(index, result) {
			var testId=result.testUid;
			if(result.specimenUid)
				testId = testId + "_" + result.specimenUid;
			if(!commonMethodInvoker.isIndexExists(testArray, testId))
				return;
			var reportingDate = result.reportingDate;
			var reportingTime = result.reportingTime;
			$(testResult).each(function(index1, result1) {
				testId=result1.testUid;
				if(result1.specimenUid)
					testId = testId + "_" + result1.specimenUid;
				if(!commonMethodInvoker.isIndexExists(testArray, testId))
					return;
				if(result1.reportingDate==reportingDate && result1.reportingTime==reportingTime) {
					if(!commonMethodInvoker.isIndexExists(selectedTests, testId)) {
						pageResult.push(result1);
						selectedTests.push(testId);
					}	
				}
			});
			if(pageResult.length>0)
				finalResult.push(pageResult);
			pageResult=[];
		});
		var verifiedUserProvider = new VerifiedUserProvider(); //For getting verified users
		var resultPrintArray = [];
		var contentset =methodInvoker.getPageSettingByKey('content');
		var originalHeight = parseInt(contentset.height,10) || 0;
		var marginHeight = parseInt(contentset.marginTop,10) || 0;
		var totalHeight = originalHeight-marginHeight;
		$(finalResult).each(function(parentIndex,singleResult) {
			var resultArray = []; //storing tests info
			var resultTestPackageArray=[]; //For storing tests which should be in separate pages and testpackages
			var resultSet =[]; //
			var resultHeight=0;//for calculating the height of tests to include in one page
			var curReportDate;
			var curReportTime;
			$(singleResult).each(function (index,result ) {
				curReportDate = result.reportingDate;
				curReportTime = result.reportingTime;
				var testId = result.testUid;
				var tstId=testId;
				if(result.specimenUid)
					tstId = testId + "_" + result.specimenUid;		
				var resultVar=result;					
				if(resultVar.testLayout=="General" || resultVar.testLayout=="GeneralOne" || resultVar.testLayout=="DC" || resultVar.testLayout=='GTT' || resultVar.testLayout=='Urine' || resultVar.testLayout=='LipidLayout' || resultVar.testLayout=='PT' || resultVar.testLayout==constants.LAYOUTGENERALMED
					|| resultVar.testLayout=="WidalLayoutMed" || resultVar.testLayout=="UrineLayoutMed" || resultVar.testLayout=="LipidLayoutMed" || resultVar.testLayout=="DCLayoutMed") {
					if(resultVar.layoutHeight)
						resultHeight+=resultVar.layoutHeight;	
					var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight, methodInvoker.getDPI());			
					if(heightmm>totalHeight) {
					var specimens="";
						//var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":[' + resultArray + ']}',orderHeader.header.uid);
						var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
						resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
						resultArray=[];
						resultHeight=resultVar.layoutHeight+25;
					}	
					resultArray.push(result.result);
				} else
					resultTestPackageArray.push(result.result);
			});	
			if(resultArray.length>0){
				var specimens="";
			//	var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":[' + resultArray + ']}',orderInfo.header.uid);
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
				resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":['+verifiedUserIds + '],"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
			}	
			$(resultTestPackageArray).each(function (index, testResult) {
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(testResult) + '}',"");
				resultSet.push('{"testResult":' + JSON.stringify(testResult) + ',"verifiedUsers":['+verifiedUserIds + '],"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
			});
			var resultHeader = orderReference.getOrderHeader();
			resultToPrint = '{"resultHeader":' + JSON.stringify(resultHeader) + ',"resultList":[' + resultSet + ']}';
			resultPrintArray.push(resultToPrint);
		});
		return resultPrintArray;
	}	
}
function VerifiedUserProvider() {
	this.getVerifiedUsers = function(result,orderId) {
		var userArray = [];
		$($.parseJSON(result).testResult).each(function (index,testInfo) {
			if(testInfo.userId) {
				var userId=testInfo.userId;
				if(!commonMethodInvoker.isIndexExists(userArray,userId))
					userArray.push(userId);
			}
		});
		var userList=[];
		$(userArray).each(function (index,id) {
			$($.parseJSON(result).testResult).each(function (index1,testInfo) {
				if(id==testInfo.userId){
					var user = new VerifierDTO();
					user.setUserId(id);
					user.setName(testInfo.userName);
					user.setDesignation(testInfo.userDesignation);
					userList.push(user);
					user=null;
					return false;
				}	
			});
		});
		return userList;
	}
	this.getTestPackageTitleArea = function() {
		var testpkgTitleDiv=$('<div class="box-content column-right">');
		var pTag = $('<p>');
		pTag.append($('<br/>'));
		var spanTag = $('<span />');
		var checkboxBtn=$('<input type="checkbox" name="title" value="title" id="testpkgTitle">');
		spanTag.append(checkboxBtn);
		pTag.append(spanTag);
		spanTag = $('<span />');
		spanTag.append(constants_testPkgTitle);
		pTag.append(spanTag);
		testpkgTitleDiv.append(pTag);
		return testpkgTitleDiv;
	}
}	
ResultDataGenerator.prototype.generatePrintJson = function(orderReference,selectedTestArray, testResult,reportDate,reportTime) {
	var self=this;
	var curReportDate = reportDate;
	var curReportTime = reportTime;
	var resultHeight=0;//for calculating the height of tests to include in one page
	var verifiedUserProvider = new VerifiedUserProvider(); //For getting verified users
	var resultSpecimenProcessor = new ResultSpecimenProcessor(); //specimen process handler	
	var resultArray = []; //storing tests info
	var resultTestPackageArray=[]; //For storing tests which should be in separate pages and testpackages
	var resultSet =[]; //
	var contentset =methodInvoker.getPageSettingByKey('content');
	var originalHeight = parseInt(contentset.height,10) || 0;
	var marginHeight = parseInt(contentset.marginTop,10) || 0;
	var totalHeight = originalHeight-marginHeight;
	$(testResult).each(function(index, result) {
		var resultVar=result;			
		var layoutStatus = methodInvoker.getPageSettingByKey('general_layout');
		newLayouts=layoutStatus.visible;
		if(newLayouts==false){				
		if(resultVar.testLayout=="General" || resultVar.testLayout=="GeneralOne" || resultVar.testLayout=="DC" || resultVar.testLayout=='GTT' || resultVar.testLayout=='Urine' || resultVar.testLayout=='LipidLayout' || resultVar.testLayout=='PT' 
			|| resultVar.testLayout==constants.LAYOUTGENERALMED || resultVar.testLayout=="WidalLayoutMed" || resultVar.testLayout=="UrineLayoutMed" || resultVar.testLayout=="LipidLayoutMed" || resultVar.testLayout=="DCLayoutMed") {
			if(resultVar.layoutHeight)
				resultHeight+=resultVar.layoutHeight;	
			var heightmm = commonMethodInvoker.getmmFromPixel(resultHeight,methodInvoker.getDPI());		
			if(heightmm>totalHeight) {
				//var specimens="";
				var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(resultArray)  + '}',"");
				var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
				resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
				resultArray=[];
				resultHeight=resultVar.layoutHeight+25;
			}	
			resultArray.push(result);
		} else
			resultTestPackageArray.push(result);
		}else {
			resultArray.push(result);
		}
	});
	if(resultArray.length>0){
		var specimens="";
		var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(resultArray) + '}',"");
		var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(resultArray) + '}',"");
		resultSet.push('{"specimens":"' + specimens + '","testResult":' + JSON.stringify(resultArray) + ',"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
	}	
	$(resultTestPackageArray).each(function (index, testResult) {
		var specimens="";
		var specimens = resultSpecimenProcessor.getSpecimenList('{"testResult":' + JSON.stringify(testResult) + '}',"");
		var verifiedUserIds = verifiedUserProvider.getVerifiedUsers('{"testResult":' + JSON.stringify(testResult) + '}',"");
		resultSet.push('{"specimens":"' + specimens + '","testResult":[' + JSON.stringify(testResult) + '],"verifiedUsers":'+JSON.stringify(verifiedUserIds) + ',"reportDate":"' + curReportDate + '","reportTime":"'+ curReportTime + '"}');
	});
	var resultHeader = orderReference.getOrderHeader();
	resultToPrint = '{"resultHeader":' + JSON.stringify(resultHeader) + ',"resultList":[' + resultSet + ']}';
	return resultToPrint;
}
function ResultSpecimenProcessor() {
	this.getSpecimenList = function(result,orderId) {
		result = $.parseJSON(result);
		var specimenArray = [];
		$(result.testResult).each(function (index,testInfo) {
			$(testInfo.specimen).each(function(index1, specimen){
				if(!commonMethodInvoker.isIndexExists(specimenArray,specimen))
					specimenArray.push(specimen);
				return false;
			});	
		});
		return specimenArray;
	}
}
function ResultHeaderProcessor(orderUI) {
	this.orderUI = orderUI;	
	this.getOrderUI = function(){return this.orderUI;}
	this.make = function(result,combinedResult,orderId,honorific,status){
		var self=this;
		var header = $('<div id="resultHeader"/>');
		header.addClass('result_header');

		var headerSetting =methodInvoker.getPageSettingByKey('header');

		header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('height', (headerSetting.height-headerSetting.marginTop)+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
		var columnSymbol = $('<div>:</div>');
		columnSymbol.addClass('column_symbol');
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var nameCaption = $('<div>Name</div>');
		nameCaption.addClass('column_small');
		row.append(nameCaption);
		row.append(columnSymbol.clone());
		var name = $('<div/>');	
		name.addClass('column_extralarge');
		name.empty().html(self.getValue(result, combinedResult, orderId, honorific,"name"));
		row.append(name);
		var ageCaption=$('<div>Age</div>');
		ageCaption.addClass('column_very_small');
		row.append(ageCaption);
		row.append(columnSymbol.clone());
		var age = $('<div/>');	
		age.addClass('column_medium');
		age.empty().html(self.getValue(result, combinedResult, orderId, honorific,"age"));
		row.append(age);
		header.append(row);
		row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var refNoCaption = $('<div>Ref. No</div>');
		refNoCaption.addClass('column_small');
		row.append(refNoCaption);
		row.append(columnSymbol.clone().clone().clone());
		var refNo = $('<div/>');	
		refNo.addClass('column_extralarge');
		refNo.empty().html(self.getValue(result, combinedResult, orderId, honorific,"orderId"));
		row.append(refNo);
		var sexCaption=$('<div>Sex</div>');
		sexCaption.addClass('column_very_small');
		row.append(sexCaption);
		row.append(columnSymbol.clone().clone());
		var sex = $('<div/>');	
		sex.addClass('column_medium');
		sex.empty().html(self.getValue(result, combinedResult, orderId, honorific,"sex"));
		row.append(sex);
		header.append(row);
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var refNoCaption = $('<div>Ref. By</div>');
		refNoCaption.addClass('column_small');
		row.append(refNoCaption);
		row.append(columnSymbol.clone());
		var refBy = $('<div/>');
		refBy.addClass('column_very_extralarge');
		refName = self.getValue(result, combinedResult, orderId, honorific,"referredBy");
		if(refName=="")
			refName="&nbsp;";
		refBy.empty().html(refName);
		row.append(refBy);
		header.append(row);
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var collectedAtCaption = $('<div>Collected At</div>');
		collectedAtCaption.addClass('column_small');
		row.append(collectedAtCaption);
		row.append(columnSymbol.clone());
		var collectedAt = $('<div/>');	
		collectedAt.addClass('column_large');
		collectedAt.empty().html(self.getValue(result, combinedResult, orderId, honorific,"collectedAt"));
		row.append(collectedAt);
		var collectDateCaption=$('<div>Collection Date</div>');
		collectDateCaption.addClass('column_small_medium');
		row.append(collectDateCaption);
		row.append(columnSymbol.clone());
		var collectDate = $('<div/>');
		collectDate.addClass('column_medium');
		collectDate.empty().html(self.getValue(result, combinedResult, orderId, honorific,"collectionDate"));
		row.append(collectDate);
		var collectTimeCaption=$('<div>Time</div>');
		collectTimeCaption.addClass('column_very_small');
		row.append(collectTimeCaption);
		row.append(columnSymbol.clone());
		var collectionTime = $('<div/>');	
		collectionTime.addClass('column_medium');
		collectionTime.empty().html(self.getValue(result, combinedResult, orderId, honorific,"collectionTime"));
		row.append(collectionTime);
		header.append(row);
		var row = $('<div/>');
		row.addClass('five_sixth');
		row.addClass('column_top');
		var specimenCaption = $('<div>Specimen</div>');
		specimenCaption.addClass('column_small');
		row.append(specimenCaption);
		row.append(columnSymbol.clone());
		var specimen = $('<div/>');	
		specimen.addClass('column_large');
		specimen.empty().html(self.getValue(result, combinedResult, orderId, honorific,"specimen"));
		row.append(specimen);
		var reportDateCaption=$('<div>Reporting Date</div>');
		reportDateCaption.addClass('column_small_medium');
		row.append(reportDateCaption);
		row.append(columnSymbol.clone());
		var reportDate = $('<div/>');	
		reportDate.addClass('column_medium');
		reportDate.empty().html(self.getValue(result, combinedResult, orderId, honorific,"reportingDate"));
		row.append(reportDate);
		var reportTimeCaption=$('<div>Time</div>');
		reportTimeCaption.addClass('column_very_small');
		row.append(reportTimeCaption);
		row.append(columnSymbol);
		var reportTime = $('<div/>');	
		reportTime.addClass('column_medium');
		reportTime.empty().html(self.getValue(result, combinedResult, orderId, honorific,"reportingTime"));
		row.append(reportTime);
		header.append(row);
		return header;
	}
	this.create = function(result,combinedResult,orderId,honorific,status) {
		/*Set the Header Properties*/
		var self=this;
		var headerSetting =methodInvoker.getPageSettingByKey('header');
		var header = $("<div class='resultHeader' />");
		header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('height', headerSetting.height+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
		if(parseInt(headerSetting.marginTop)!=0){
			var newDiv = $("<div id='header'/>");
			if(status!=true)
			{
				var imgTag=$('<img src="'+headerSetting.bgImage+'"  style="height:30mm; width:216mm;">');
				newDiv.append(imgTag);
			}
			newDiv.css("height",headerSetting.marginTop+"mm");
			newDiv.css("width",headerSetting.width+"mm");
			header.append(newDiv);
		}
		/*Create the Header Sections*/
		var headerTable = $("<table cellpadding=0 cellspacing=0/>");
		//headerTable.css("height",(headerSetting.height - headerSetting.marginTop));
		var l1c = methodInvoker.getPageSettingByKey('L1C');
		var l1v = methodInvoker.getPageSettingByKey('L1V');
		var m1c = methodInvoker.getPageSettingByKey('M1C');
		var m1v = methodInvoker.getPageSettingByKey('M1V');
		var r1c = methodInvoker.getPageSettingByKey('R1C');
		var r1v = methodInvoker.getPageSettingByKey('R1V');
		if(l1v.label!="select" || m1v.label!="select" || r1v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l1c,l1v,m1c,m1v,r1c,r1v));	
		}
		var l2c = methodInvoker.getPageSettingByKey('L2C');
		var l2v = methodInvoker.getPageSettingByKey('L2V');
		var m2c = methodInvoker.getPageSettingByKey('M2C');
		var m2v = methodInvoker.getPageSettingByKey('M2V');
		var r2c = methodInvoker.getPageSettingByKey('R2C');
		var r2v = methodInvoker.getPageSettingByKey('R2V');
		if(l2v.label!="select" || m2v.label!="select" || r2v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l2c,l2v,m2c,m2v,r2c,r2v));	
		}
		var l3c = methodInvoker.getPageSettingByKey('L3C');
		var l3v = methodInvoker.getPageSettingByKey('L3V');
		var m3c = methodInvoker.getPageSettingByKey('M3C');
		var m3v = methodInvoker.getPageSettingByKey('M3V');
		var r3c = methodInvoker.getPageSettingByKey('R3C');
		var r3v = methodInvoker.getPageSettingByKey('R3V');
		if(l3v.label!="select" || m3v.label!="select" || r3v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l3c,l3v,m3c,m3v,r3c,r3v));	
		}
		var l4c = methodInvoker.getPageSettingByKey('L4C');
		var l4v = methodInvoker.getPageSettingByKey('L4V');
		var m4c = methodInvoker.getPageSettingByKey('M4C');
		var m4v = methodInvoker.getPageSettingByKey('M4V');
		var r4c = methodInvoker.getPageSettingByKey('R4C');
		var r4v = methodInvoker.getPageSettingByKey('R4V');
		if(l4v.label!="select" || m4v.label!="select" || r4v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l4c,l4v,m4c,m4v,r4c,r4v));	
		}
		var l5c = methodInvoker.getPageSettingByKey('L5C');
		var l5v = methodInvoker.getPageSettingByKey('L5V');
		var m5c = methodInvoker.getPageSettingByKey('M5C');
		var m5v = methodInvoker.getPageSettingByKey('M5V');
		var r5c = methodInvoker.getPageSettingByKey('R5C');
		var r5v = methodInvoker.getPageSettingByKey('R5V');
		if(l5v.label!="select" || m5v.label!="select" || r5v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l5c,l5v,m5c,m5v,r5c,r5v));	
		}
		var l6c = methodInvoker.getPageSettingByKey('L6C');
		var l6v = methodInvoker.getPageSettingByKey('L6V');
		var m6c = methodInvoker.getPageSettingByKey('M6C');
		var m6v = methodInvoker.getPageSettingByKey('M6V');
		var r6c = methodInvoker.getPageSettingByKey('R6C');
		var r6v = methodInvoker.getPageSettingByKey('R6V');
		if(l6v.label!="select" || m6v.label!="select" || r6v.label!="select"){
			headerTable.append(self.generateRow(result,combinedResult,orderId,honorific,l6c,l6v,m6c,m6v,r6c,r6v));	
		}
		header.append(headerTable);
		return header;
	}
	this.setStyles=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italics");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
		if(settings.width!="")
			column.css("width",settings.width+"mm");
		if(settings.height!="")
			column.css("height",settings.height+"mm");
	}
	this.setStylesWithoutMeasure=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italics");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
	}
	this.generateRow = function(result, combinedResult, orderId, honorific, lnc, lnv, mnc, mnv, rnc, rnv){
		var self=this;
		var row = $("<tr/>");
		var columnSpan=1;
		if(mnc.label=='' && mnv.label=='blank')
			columnSpan+=2;
		if(columnSpan!=1){
			var lnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnccolumn, lnc);
			//if(lnc.visible!=false && lnc.label!="") 
			//	lnccolumn.empty().html(lnc.label);
			lnccolumn.empty().html(lnc.label);
			row.append(lnccolumn);
			var lnvcolumn = $("<td colspan='3'/>");
			self.setStyles(lnvcolumn, lnv);
			self.setStylesWithoutMeasure(lnvcolumn, lnv);
			if(lnv.visible!=false && (lnv.label!="blank" || lnv.label!="select" )){
				var refName = self.getValue(result, combinedResult, orderId, honorific,lnv.label);
				if(refName!=undefined)
					if(refName.length>50)
						lnvcolumn.css('font-size','13px');
				lnvcolumn.empty().html(refName);
			}
			row.append(lnvcolumn);
		} else {
			var lnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnccolumn, lnc);
			//if(lnc.visible!=false && lnc.label!="") 
				lnccolumn.empty().html(lnc.label);
			row.append(lnccolumn);
			var lnvcolumn = $("<td>&nbsp;</td>");
			self.setStyles(lnvcolumn, lnv);
			if(lnv.visible!=false && (lnv.label!="blank" || lnv.label!="select" ))
				lnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,lnv.label));
			row.append(lnvcolumn);
			var mnccolumn = $("<td>&nbsp;</td>");
			self.setStyles(mnccolumn, mnc);
			//if(mnc.visible!=false && mnc.label!="") 
				mnccolumn.empty().html(mnc.label);
			row.append(mnccolumn);
			var mnvcolumn = $("<td>&nbsp;</td>");
			self.setStyles(mnvcolumn, mnv);
			if(mnv.visible!=false && (mnv.label!="blank" || mnv.label!="select" ))
				mnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,mnv.label));
			row.append(mnvcolumn);
		}
		var rnccolumn = $("<td>&nbsp;</td>");
		self.setStyles(rnccolumn, rnc);
		//if(rnc.visible!=false && rnc.label!="") 
			rnccolumn.empty().html(rnc.label);
		row.append(rnccolumn);
		var rnvcolumn = $("<td>&nbsp;</td>");
		self.setStyles(rnvcolumn, rnv);
		if(rnv.visible!=false && (rnv.label!="blank" || rnv.label!="select" ))
			rnvcolumn.empty().html(self.getValue(result, combinedResult, orderId, honorific,rnv.label));
		row.append(rnvcolumn);
		return row;	
	}
	this.getValue = function(result, combinedResult, orderId, honorific, key ) {
		if(key=='name')
			return combinedResult.resultHeader.patientName;
		if(key=='age') {
			curAge = "";
			if(combinedResult.resultHeader.age) {
				var age = combinedResult.resultHeader.age;
				var tempAge = age.split("-");
				if(tempAge.length>1){
					curYear = parseFloat(tempAge[0],10)||0;
					curMonth = parseFloat(tempAge[1],10)||0;
					curDay = parseFloat(tempAge[2],10)||0;
					if(curYear!=0)
						curAge = curYear + "Y ";
					if(curMonth!=0)
						curAge = curAge + curMonth + "M ";
					if(curDay!=0)
						curAge = curAge + curDay + "D";	
				} else
					curAge = tempAge[0];
			}	
			return curAge;	
		}	
		if(key=='orderId')
			return combinedResult.resultHeader.orderId;
		if(key=='referredBy')
			return combinedResult.resultHeader.referral;
		if(key=='sex')
			return combinedResult.resultHeader.gender;
		if(key=='collectedAt'){
			if(combinedResult.resultHeader.collectedAt) {
				if(combinedResult.resultHeader.collectedAt!=null && combinedResult.resultHeader.collectedAt!='null')
					return combinedResult.resultHeader.collectedAt;
			}
			return "";
		}
		if(key=='collectionDate'){
			if(combinedResult.resultHeader.date)
				return combinedResult.resultHeader.date;
			return "";
		}
		if(key=='collectionTime'){
			if(combinedResult.resultHeader.time)
				return combinedResult.resultHeader.time;
			return "";
		}
		if(key=='reportingDate') {
			if(result!=null)
				if(result.reportDate)
					return result.reportDate;
			return "";
		}
		if(key=='reportingTime') {
			if(result!=null)
				if(result.reportTime)
					return result.reportTime;
			return "";
		}
		if(key=='specimen'){
			//var resultSpecimenProcessor = new ResultSpecimenProcessor();
			if(result!=null){	
				if(result.specimens)
					return (' ' + result.specimens + ' ');
				else {
					//var specimenArray=resultSpecimenProcessor.getSpecimenList(JSON.stringify(result),orderId);
					var specimenArray=[];
					if(specimenArray.length==0)
						return "";
					else {
						specimenName="";
						$(specimenArray).each(function(index,specimen) {
							if(specimenName=="")
								specimenName=specimen;
							else
								specimenName+=','+ specimen;
						});
						return (' ' + specimenName + ' ');
					}	
				}
			}
		}
	}	
	this.generate = function(result,combinedResult,orderId,honorific) {
		var self=this;
		var headerSetting =methodInvoker.getPageSettingByKey('header');
		var header = $("<div class='resultHeader' />");
		//header.css('width', (headerSetting.width - headerSetting.marginLeft)+ "mm");
		header.css('width', headerSetting.width+"mm");
		header.css('height', headerSetting.height+ "mm");
		header.css('margin-left',headerSetting.marginLeft+ "mm");
		if(parseInt(headerSetting.fontSize)!=0)
			header.css('font-size',headerSetting.fontSize + "px")
		if(parseInt(headerSetting.marginTop)!=0){
			var newDiv = $("<div>&nbsp;</div>");
			newDiv.append(" ");
			newDiv.css("height",headerSetting.marginTop+"mm");
			newDiv.css("width",headerSetting.width+"mm");
			header.append(newDiv);
		}
		var l1c = methodInvoker.getPageSettingByKey('L1C');
		var l1v = methodInvoker.getPageSettingByKey('L1V');
		var m1c = methodInvoker.getPageSettingByKey('M1C');
		var m1v = methodInvoker.getPageSettingByKey('M1V');
		var r1c = methodInvoker.getPageSettingByKey('R1C');
		var r1v = methodInvoker.getPageSettingByKey('R1V');
		header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l1c,l1v,m1c,m1v,r1c,r1v));
		var l2c = methodInvoker.getPageSettingByKey('L2C');
		var l2v = methodInvoker.getPageSettingByKey('L2V');
		var m2c = methodInvoker.getPageSettingByKey('M2C');
		var m2v = methodInvoker.getPageSettingByKey('M2V');
		var r2c = methodInvoker.getPageSettingByKey('R2C');
		var r2v = methodInvoker.getPageSettingByKey('R2V');
		if(l2v.label!="select" || m2v.label!="select" || r2v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l2c,l2v,m2c,m2v,r2c,r2v));	
		}
		var l3c = methodInvoker.getPageSettingByKey('L3C');
		var l3v = methodInvoker.getPageSettingByKey('L3V');
		var m3c = methodInvoker.getPageSettingByKey('M3C');
		var m3v = methodInvoker.getPageSettingByKey('M3V');
		var r3c = methodInvoker.getPageSettingByKey('R3C');
		var r3v = methodInvoker.getPageSettingByKey('R3V');
		if(l3v.label!="select" || m3v.label!="select" || r3v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l3c,l3v,m3c,m3v,r3c,r3v));	
		}
		var l4c = methodInvoker.getPageSettingByKey('L4C');
		var l4v = methodInvoker.getPageSettingByKey('L4V');
		var m4c = methodInvoker.getPageSettingByKey('M4C');
		var m4v = methodInvoker.getPageSettingByKey('M4V');
		var r4c = methodInvoker.getPageSettingByKey('R4C');
		var r4v = methodInvoker.getPageSettingByKey('R4V');
		if(l4v.label!="select" || m4v.label!="select" || r4v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l4c,l4v,m4c,m4v,r4c,r4v));	
		}
		var l5c = methodInvoker.getPageSettingByKey('L5C');
		var l5v = methodInvoker.getPageSettingByKey('L5V');
		var m5c = methodInvoker.getPageSettingByKey('M5C');
		var m5v = methodInvoker.getPageSettingByKey('M5V');
		var r5c = methodInvoker.getPageSettingByKey('R5C');
		var r5v = methodInvoker.getPageSettingByKey('R5V');
		if(l5v.label!="select" || m5v.label!="select" || r5v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l5c,l5v,m5c,m5v,r5c,r5v));	
		}
		var l6c = methodInvoker.getPageSettingByKey('L6C');
		var l6v = methodInvoker.getPageSettingByKey('L6V');
		var m6c = methodInvoker.getPageSettingByKey('M6C');
		var m6v = methodInvoker.getPageSettingByKey('M6V');
		var r6c = methodInvoker.getPageSettingByKey('R6C');
		var r6v = methodInvoker.getPageSettingByKey('R6V');
		if(l6v.label!="select" || m6v.label!="select" || r6v.label!="select"){
			header.append(self.generateRowNew(result,combinedResult,orderId,honorific,l6c,l6v,m6c,m6v,r6c,r6v));	
		}
		
		return header;
	}
	this.generateRowNew = function(result, combinedResult, orderId, honorific, lnc, lnv, mnc, mnv, rnc, rnv){
		var self=this;
		var row="";
		var curwidth=0;
		var curcol = "";
		if(lnv.label!='blank' || mnv.label!="blank" || rnv.label!="blank" || 
			lnc.label.trim()!="" || mnc.label.trim()!="" || rnc.label.trim()!=""){
			row=$('<div class="oneHeader" >&nbsp;</div>');
			var column = $('<div class="columnleftalign">&nbsp;</div>');
			column.append("Name");
			self.setStyles(column,lnc);
			curwidth = parseInt(lnc.width),10||0;
			curcol = column;
			row.append(column);
			if(lnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, lnv.label));
				self.setStyles(column,lnv);
				curwidth = parseInt(lnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(lnv.width);
				curcol.css('width',curwidth+"mm");
			}
			if(mnc.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(mnc.label);
				self.setStyles(column,mnc);
				curwidth = parseInt(mnc.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(mnc.width);
				curcol.css('width',curwidth+"mm");
			}
			if(mnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, mnv.label));
				self.setStyles(column,mnv);
				curwidth = parseInt(mnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(mnv.width);
				curcol.css('width',curwidth+"mm");
			}
			if(rnc.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(rnc.label);
				self.setStyles(column,rnc);
				curwidth = parseInt(rnc.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(rnc.width);
				curcol.css('width',curwidth+"mm");
			}
			if(rnv.visible!=false){
				var column = $('<div class="columnleftalign">&nbsp;</div>');
				column.append(self.getValue(result, combinedResult, orderId, honorific, rnv.label));
				self.setStyles(column,rnv);
				curwidth = parseInt(rnv.width);
				curcol=column;
				row.append(column);
			} else {
				curwidth+=parseInt(rnv.width);
				curcol.css('width',curwidth+"mm");
			}
		}
		return row;
	}
}
function ResultFooterProcessor(orderUI) {
	this.create = function(result,combinedResult,orderId) {
		var self=this;
		var footer = $('<div class="printReportFooter " />');
		var headerSetting =methodInvoker.getPageSettingByKey('footer');
		var headerHeight = methodInvoker.getPageSettingByKey('header').marginTop;
		var signSetting = methodInvoker.getPageSettingByKey('USIGN');
		footer.css('width', headerSetting.width+ "mm");
		footer.css('height',(parseInt(headerSetting.height)+parseInt(headerHeight))+ "mm");
		var linehgt = headerSetting.height/5;
		if(headerSetting.visible==false) {
			footer.append('&nbsp;');
		} else {
			var tblFooter = $('<table style="width:100%; height:100%;vertical-align:bottom; float:left;"/>');
			trTag = $('<tr/>');
			tdTag = $('<td style="vertical-align:top"/>');
			var noOfUsers = result.verifiedUsers.length; 
			$(result.verifiedUsers).each(function(index, user) {	
				var userInfo=query.viewUser(user.userId, orderUI.getBranchId());
				if(noOfUsers==2) {
					if(index==0){
						divTag = $('<div style="width:46%;float:left;margin-left:2%" />');
					}else{
						divTag = $('<div style="width:46%;float:right;margin-right:2%;text-align:right" />');
					}
					var signTag=$('<div>&nbsp;</div>');
					if(userInfo.signature && signSetting.visible==true) {
						signTag.html('<img src=' + userInfo.signature + ' width="200" height="40" style="margin-left:5%;"/>');
					}	
					divTag.append(signTag);
					var childTag = $('<div/>');
					childTag.html(user.name);
					divTag.append(childTag);
					if(user.designation) {
						childTag = $('<div/>');
						childTag.html(user.designation);
						divTag.append(childTag);
					}
					divTag.attr('height',(linehgt*3)+"mm");	
					tdTag.append(divTag);
				}else {
					if(index==0)
						divTag = $('<div style="min-width:31%;float:left;margin-left:1%;text-align:left" />')
					else if(index==1)
						divTag = $('<div style="min-width:31%;float:left;margin-left:1%;text-align:center" />')
					else
						divTag = $('<div style="min-width:31%;float:right;margin-right:1%;text-align:right" />')

					var childTag = $('<div/>');
					var signTag=$('<div>&nbsp;</div>');
					if(userInfo.signature  && signSetting.visible==true) {
						signTag.html('<img src=' + userInfo.signature + ' width="200" height="40" style="margin-left:5%;"/>');
					}
					divTag.append(signTag);
					childTag.html(user.name);
					divTag.append(childTag);
					if(user.designation) {
						childTag = $('<div/>');
						childTag.html(user.designation);
						divTag.append(childTag);
					}	
					divTag.attr('height',(linehgt*3)+"mm");
					tdTag.append(divTag);
					if(index==2)
						return false;
					}
			});
			var f1l = methodInvoker.getPageSettingByKey('F1L');
			var f1m = methodInvoker.getPageSettingByKey('F1M');
			var f1r = methodInvoker.getPageSettingByKey('F1R');
			var f2l = methodInvoker.getPageSettingByKey('F2L');
			var f2m = methodInvoker.getPageSettingByKey('F2M');
			var f2r = methodInvoker.getPageSettingByKey('F2R');
			
			if(f1l.label.trim()=="" && f1m.label.trim()=="" && f1r.label.trim()==""){

			} else {
				var f1Div  = $('<div class="left oneHeader" style="clear:both; margin-left:0.5%;text-align:left"/>');
				f1Div.attr('height',linehgt+"mm");
				var f1 = $('<div class="onethird">&nbsp;</div>')
				if(f1l.label.trim()!=""){
					f1.append(f1l.label);
					self.setStyles(f1, f1l);
				}
				f1Div.append(f1);
				var f2 = $('<div class="onethird">&nbsp;</div>')
				if(f1m.label.trim()!=""){
					f2.append(f1m.label);
					self.setStyles(f2, f1m);
				}
				f1Div.append(f2);
				var f3 = $('<div class="onethird ">&nbsp;</div>')
				if(f1r.label.trim()!=""){
					f3.append(f1r.label);
					self.setStyles(f3, f1r);
				}
				f1Div.append(f3);
				tdTag.append(f1Div);
			}
			if(f2l.label.trim()=="" && f2m.label.trim()=="" && f2r.label.trim()==""){

			} else {
				var f2Div  = $('<div class="left oneHeader" style="clear:both;margin-left:0.5%;text-align:left"/>');
				f2Div.attr('height',linehgt+"mm");
				var f1 = $('<div class="onethird">&nbsp;</div>')
				if(f2l.label.trim()!=""){
					f1.append(f2l.label);
					self.setStyles(f1, f2l);
				}
				f2Div.append(f1);
				var f2 = $('<div class="onethird">&nbsp;</div>')
				if(f2m.label.trim()!=""){
					f2.append(f2m.label);
					self.setStyles(f2, f2m);
				}
				f2Div.append(f2);
				var f3 = $('<div class="onethird ">&nbsp;</div>')
				if(f2r.label.trim()!=""){
					f3.append(f2r.label);
					self.setStyles(f3, f2r);
				}
				f2Div.append(f3);
				tdTag.append(f2Div);
			}
			trTag.append(tdTag);
			tblFooter.append(trTag);
			footer.append(tblFooter);
		}
		return footer;
	}
	this.setStyles=function(column, settings){
		if(settings.bold==true)
			column.css("font-weight","bold");
		if(settings.italics==true)
			column.css("font-style","italic");
		if(settings.underline==true)
			column.css("text-decoration","underline");
		if(settings.fontSize!="")
			column.css("font-size",settings.fontSize+"px");
		if(settings.width!="")
			column.css("width",settings.width+"mm");
		if(settings.height!="")
			column.css("height",settings.height+"mm");
	}
	this.generalFooter = function() {
		var self=this;
		var footer = $('<div class="printReportFooter " style="vertical-align:bottom;"/>');
		var headerSetting =methodInvoker.getPageSettingByKey('footer');
		footer.css('width', headerSetting.width+ "mm");
		footer.css('height',headerSetting.height+ "mm");
		var f1l = methodInvoker.getPageSettingByKey('F1L');
		var f1m = methodInvoker.getPageSettingByKey('F1M');
		var f1r = methodInvoker.getPageSettingByKey('F1R');
		var f2l = methodInvoker.getPageSettingByKey('F2L');
		var f2m = methodInvoker.getPageSettingByKey('F2M');
		var f2r = methodInvoker.getPageSettingByKey('F2R');
		var f1Div  = $('<div class="left"/>');
		if(f1l.label.trim()=="" && f1m.label.trim()=="" && f1r.label.trim()){

		} else {
			var f1 = $('<div class="onethird">&nbsp;</div>')
			if(f1l.label.trim()!=""){
				f1.append(f1l.label);
				self.setStyles(f1, f1l);
			}
			f1Div.append(f1);
			var f2 = $('<div class="onethird">&nbsp;</div>')
			if(f1m.label.trim()!=""){
				f2.append(f1m.label);
				self.setStyles(f2, f1m);
			}
			f1Div.append(f2);
			var f3 = $('<div class="onethird ">&nbsp;</div>')
			if(f1r.label.trim()!=""){
				f3.append(f1r.label);
				self.setStyles(f3, f1r);
			}
			f1Div.append(f3);
		}
		footer.append(f1Div);
		var f2Div  = $('<div class="left"/>');
		if(f2l.label.trim()=="" && f2m.label.trim()=="" && f2r.label.trim()){

		} else {
			var f1 = $('<div class="onethird">&nbsp;</div>')
			if(f2l.label.trim()!=""){
				f1.append(f2l.label);
				self.setStyles(f1, f2l);
			}
			f2Div.append(f1);
			var f2 = $('<div class="onethird">&nbsp;</div>')
			if(f2m.label.trim()!=""){
				f2.append(f2m.label);
				self.setStyles(f2, f2m);
			}
			f2Div.append(f2);
			var f3 = $('<div class="onethird ">&nbsp;</div>')
			if(f2r.label.trim()!=""){
				f3.append(f2r.label);
				self.setStyles(f3, f2r);
			}
			f2Div.append(f3);
		}
		footer.append(f2Div);
		return footer;
	}
}
function ResultPrintHandler() {
	this.generateHtmlString = function(result) {
		var strHtml = '<html><head>'; 
		strHtml += "<style>";
		strHtml += "<!--";
		strHtml += ".groupResultTable{font-family:'arial';page-break-after:always; padding:none; margin:none;} ";
		strHtml += ".page{font-family:'arial';page-break-after:always; padding:none; margin:none;} ";
		strHtml += ".one {width:100%;}";
		strHtml += ".nomargin{margin:0px}";
		strHtml += ".singleheight {height:6.5mm;}";
		strHtml += ".oneHeader{width:100%}";
 		strHtml += ".onethird {width:33%;float:left}";
 		strHtml += ".onesecond {width:30%;float:left}";
 		strHtml += ".onefirst{width:39%;float:left}";
 		strHtml += ".onefive {width:20%;float:left;}";
 		strHtml += ".onefourth {width:15%;float:left;}";
		//strHtml += ".remarks { margin:auto; width:90%}";
		strHtml += ".column-2{text-align:right}";
		strHtml += ".middle{text-align:center}";
		strHtml += ".bold {font-weight:bold}";
		strHtml += ".underline{text-decoration:underline}";
		strHtml += ".columnleftalign{float:left;}";
		strHtml += ".one_first_column {width:17%}";
 		strHtml += ".one_second_column{width:5%}";
 		strHtml += ".one_third_column{width:77%}";
 		strHtml += "#foodAllergy {font-size:12px}";
		strHtml += ".foodallergy_onethird_border {border:1px solid}";
		strHtml += "#foodAllergy .one_third_column{font-size:11px}";
		strHtml += ".smallheight {line-height:15px;}";
		strHtml += ".column_height {line-height:15mm;}";
		strHtml += ".normal_height{line-height:7mm}";
		strHtml += ".column_symbol {float:left; width:1%}";
		strHtml += ".column_very_small {float:left;width:5%}";
		strHtml += ".column_small {float:left;width:12%}";
		strHtml += ".column_small_medium {float:left;width:14%}";
		strHtml += ".column_medium {float:left;width:15%}";
		strHtml += ".column_medium_extra {float:left;width:20%}";
		strHtml += ".column_large {float:left;width:36%}";
		strHtml += ".result_header {font-size:15}";
		strHtml += ".column_extralarge{float:left;width:66%}";
		strHtml += ".column_very_extralarge{float:left;width:87%}";
		strHtml += ".column_top {padding-top:3%}";
		var generalLayoutset =methodInvoker.getPageSettingByKey('general_layout');
		strHtml += ".printresultTable {";
		if(generalLayoutset.border==true) {
			strHtml += "	border:1px solid #000;";
			strHtml += "	margin:auto;";
			strHtml += "	border-spacing:15px;";
			strHtml += "	border-collapse:collapse;";
		}
		//strHtml += "	line-height:25px;";
		//strHtml += "	font-size:16px;";
		strHtml += "}";
		if(generalLayoutset.border==true) {
			strHtml += ".printresultTable thead tr th {border:1px solid #000; line-height:25px;}";
			strHtml += ".printresultTable tbody tr td{border:1px solid #000;line-height:25px;}";
			strHtml += ".printresultTable table tbody tr td{border:none;}";
		}
		strHtml += "input.readonlyStyle {";
		strHtml += "	background-color:#FAFAFA;";
		strHtml += "	border-style:none;";
		strHtml += "	border-width:0px;";
		strHtml += "	color:#000;";
		strHtml += "}";
		strHtml += ".resultTitle{ font-weight:bold; font-size:17px;margin-bottom:5px;}";
		strHtml += "#cultureresultTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:95%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:15px;";
		strHtml += "	font-size:90%";
		strHtml += "}";
		strHtml += "#cultureresultTable thead tr th {border:1px solid #000;}";
		strHtml += "#cultureresultTable tbody tr td{border:1px solid #000; line-height:19px;}";
		strHtml += "#cultureresultTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#cultureresultTable  td{padding:none;margin:none; line-height:19px;}";
		strHtml += ".cultureTable_Head thead tr th {border:0px solid #000; font-size:16px;}";
		strHtml += ".cultureTable_Head tbody tr td{border:0px solid #000;}";

		strHtml += "input.readonlybox {";
		strHtml += "	background:none;";
		strHtml += "	border:none;";
		strHtml += "	color:#000;";
		strHtml += "	padding:0px;";
		strHtml += "	margin:0px;";
		strHtml += "	width:50%;";
		strHtml += "	text-align:right;	";
		strHtml += "}";
		strHtml += "input.plainText {";
		strHtml += "	width:90%;";
		strHtml += "	background-color:#FAFAFA;";
		strHtml += "	border-style:none;";
		strHtml += "	border-width:0px;";
		strHtml += "	color:#000;";
		strHtml += "}";
		strHtml += "input.middle{";
		strHtml += "	text-align:center;";
		strHtml += "}";
		strHtml += "#realTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:100%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:21px;font-size:90%;";
		strHtml += "}";
		strHtml += "#realTable thead tr th {border:1px solid #000;font-size:12px;}";
		strHtml += "#realTable tbody tr td{border:1px solid #000;}";
		strHtml += "#realTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#realTable  td{padding:none;margin:none;}";
		strHtml += "#realTable input.medium { width: 35%;}";

		strHtml += "#SpHgtTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:100%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:25px;";
		strHtml += "}";
		strHtml += "#SpHgtTable thead tr th {border:1px solid #000;}";
		strHtml += "#SpHgtTable tbody tr td{border:1px solid #000;}";
		strHtml += "#SpHgtTable  table tbody tr td{border:1px solid #000;}";
		strHtml += "#SpHgtTable  td{padding-left:1%;margin:none;}";
		strHtml += "#SpHgtTable input.medium { width: 40%;}";
		strHtml += "#SpHgtTable input.part { width: 75%;}";
		strHtml += "#SpHgtTable input.large { width: 100%;}";
		strHtml += "#SpHgtTable td.microscopy {font-weight:bold;font-size:20px;}";
		strHtml += "#SpHgtTable input.mystyle{ width:100%;height:28px;font-size:15px;}";
		strHtml += "#StoneTable input.medium { width: 40%;}";
		strHtml += "#StoneTable input.part { width: 75%;}";
		strHtml += "#SemenTable{";
		strHtml += "	border:1px solid #000;";
		strHtml += "	width:90%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "}";
		strHtml += "";
		strHtml += "#SemenTable thead tr th {border:1px solid #000;}";
		strHtml += "#SemenTable tbody tr td{border:1px ;}";
		strHtml += "#SemenTable  tbody tr {border:1px;}";
		strHtml += "#SemenTable  td{padding:none;margin:none;}";
		strHtml += "#SemenTable input.medium { width:30%;}";
		strHtml += "#SemenTable input.tiny { width:10%;}";
		strHtml += "#SemenTable span {padding-right:5px;}";
		strHtml += "#SemenTable label {padding-right:3px;}";
		strHtml += "";
		strHtml += "#SpHgtTable #NoBorderTable{";
		strHtml += "	border:none;";
		strHtml += "	width:90%;";
		strHtml += "	margin:auto;";
		strHtml += "	border-spacing:15px;";
		strHtml += "	border-collapse:collapse;";
		strHtml += "	line-height:25px;";
		strHtml += "	width:100%;";
		strHtml += "	font-weight:normal;";
		strHtml += "}";
		strHtml += "#SpHgtTable #NoBorderTable thead tr th {border:none;}";
		strHtml += "#SpHgtTable #NoBorderTable tbody tr td{border:none;font-weight:italic}";
		strHtml += "#SpHgtTable #NoBorderTable  tbody tr {border:none;}";
		strHtml += "#SpHgtTable #NoBorderTable  td{padding:none;margin:none;}";
		strHtml += "#SpHgtTable #NoBorderTable input.medium { width:30%;}";
		strHtml += "";
		strHtml += ".column-left { float: left; width: 49%;}";
		strHtml += ".column-first { float: left; width: 38%;}";
		strHtml += ".column-second { float: left; width: 30%;}";
		strHtml += ".column-right {";
		strHtml += "  float: right;";
		strHtml += "  width: 49%;";
		strHtml += "  padding:0 0 0 0px;";
		strHtml += "}";
		strHtml += "#SpHgtTable  table tbody tr td{font-size:16px;}";
		strHtml += "form .column-left, form .column-right { width: 47%;  padding:0 2% 0 0;}";
		strHtml += ".titleClass {";
		strHtml += "	font-size:16px;";
		strHtml += "	font-weight:bold;";
		strHtml += "	text-align:center;";
		strHtml += "	line-height:25px;";
		strHtml += "	text-decoration: underline; ";
		strHtml += "}";
		strHtml += ".topAlign {vertical-align:top;}";
		strHtml += "table .noBorder {border:none;}";
		strHtml += "table .noBorder tbody tr td{border:none;}";
		strHtml += ".remarks p { margin-top:0px;margin-bottom:0px;}";
		strHtml += "--></style>";
		strHtml+='</head><body><div id="printDiv">';
		strHtml+= result + '</div></body></html>';
		return strHtml;
	}	
	this.print=function(htmlString) {
		var WindowObject = window.open('', 'PrintWindow', 'width=1000,height=650,top=50,left=50,toolbars=no,scrollbars=yes,status=no,resizable=yes');
		var writestat = WindowObject.document.writeln(htmlString);
		WindowObject.focus();
		WindowObject.print();
		WindowObject.document.close();
		WindowObject.close();
		return;
	}
}