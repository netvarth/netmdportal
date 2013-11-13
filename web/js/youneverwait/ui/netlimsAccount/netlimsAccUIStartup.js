function netlimsAccountUIStartup() {
	this.pgTableName = "#branchNetlimsAcc";
	this.pgTableContainer="#branchNetlimsAccTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#branchNetlimsAccPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#branchNetlimsAccPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#branchNetlimsAccPTBContainer #btn_delete_ptb_id');
	this.ptbSync=$j('#branchNetlimsAccPTBContainer #btn_change_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.netlimsId=userdata.labId;
	this.pgTableRowClass = this.pgTableName + ' .branchNetlimsAccIdCol';
	this.exp = new ExpressionListDTO();
	this.netlimsAccService = new NetlimsAccServiceImpl();
	//this.testService = new TestServiceImpl();
	this.listUrl = constants.NETLIMSACCBRANCHLISTURL;
	this.netlimsAccTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.netlimsAccService,this.exp);
	
	//this.viewReferralUI = new ViewReferralUI(this);
}

netlimsAccountUIStartup.prototype.setnetlimsAccTableNavigator = function(netlimsAccTableNavigator) {
	this.netlimsAccTableNavigator = netlimsAccTableNavigator;
}
netlimsAccountUIStartup.prototype.getnetlimsAccTableNavigator = function() {
	return this.netlimsAccTableNavigator;
}
netlimsAccountUIStartup.prototype.getBranchService = function() {
	return this.netlimsAccService;
}
/* OrderUIStartup.prototype.getViewHistoryUI = function() {
	return this.viewHistoryUI;
}
OrderUIStartup.prototype.getViewOrderUI = function() {
	return this.viewOrderUI;
}
OrderUIStartup.prototype.setOrderTableNavigator = function(orderTableNavigator) {
	this.orderTableNavigator = orderTableNavigator;
}

OrderUIStartup.prototype.getOrderTableNavigator = function() {
	return this.orderTableNavigator;
}

OrderUIStartup.prototype.getTestPackageService = function() {
	return this.testPackageService;
}
OrderUIStartup.prototype.getTestService = function() {
	return this.testService;
}
OrderUIStartup.prototype.getOrderId = function() {
	return this.orderId;
} */

//Set the page title of the order ui page
netlimsAccountUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

//Return the selected Order Id from the list table
netlimsAccountUIStartup.prototype.getSelectedbranchId = function () {
	var self =this;
	var branchId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selbranch = $j(self.pgTableName + ' tbody tr[selected]');
		if(selbranch.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBRANCH);
		} else if(selbranch.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBRANCHONLY);
		else
			branchId=selbranch.attr('id');
	}
	return branchId;
}

netlimsAccountUIStartup.prototype.init = function() {
	var self = this;
	var selName="labId";
	var selValue=self.netlimsId;
	var selOperator = "eq";
	self.setPageTitle(constants.BRANCHLIST);
	var exp = new ExpressionListDTO();
	var expr = new ExpressionDTO(selName,selValue,selOperator);
	exp.add(expr);
	var netlimsAccTableNavigator = self.getnetlimsAccTableNavigator();
	netlimsAccTableNavigator.setExp(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.create(constants.NETLIMSBRANCH, constants.NETLIMSACCPAGEBRANCHJSON); //Create the Page tool Bar for Order
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.NETLIMSACCBRANCHLISTJSON);//Create Table for Listing Order
	dataTableProcessor.setCustomTable(self.pgTableName);
	netlimsAccTableNavigator.list();
	self.bindEvents();
	pageHandler.setActivePage(self);
}



netlimsAccountUIStartup.prototype.createBranchModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSACCBRANCHJSON,constants.NEWBRANCHMODAL);		
	openModalBox(obj,constants.NEWBRANCHMODAL);
	var newBranchUI = new NewBranchUI(self);
	newBranchUI.init();
	return newBranchUI;
}

netlimsAccountUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	
	
	
	/*Page Tool Bar Events ends here*/
	
	
	
}
netlimsAccountUIStartup.prototype.bindEvents = function() {
	parent = this;
	$j(parent.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}	
		removeErrors();
	});		
	/* $j(parent.pgTableRowClass).die('click').live('click',function(){
	   var referralId= $j(this).parent().attr('id');
	   $j('#' + constants.REFERRAL + '-filter-cont').hide();
	   $j(parent.filter).hide();
		if(referralId!="") {
			$j('#referral-filter-wb').hide();
			var viewReferralUI = parent.getViewReferralUI();
			viewReferralUI.init(referralId);
		}	
	}); */
}



/* netlimsAccountUIStartup.prototype.getPrevId = function(curId,orderResult) {
	var prevId;
	$j(orderResult.order).each(function (index, rowOrder) {
		if(curId==rowOrder.uid)	{
			var arrayLength=(orderResult.order).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=orderResult.order[index-1].uid;
		}
	});
	return prevId;	
}
netlimsAccountUIStartup.prototype.getNextId = function(curId,orderResult) {
	var nextId;
	$j(orderResult.order).each(function (index, roworder) {
		if(curId==roworder.uid)	{
			var arrayLength=(orderResult.order).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=orderResult.order[index+1].uid;
			}	
		}
	});	
	return nextId;	
} */