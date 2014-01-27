 function ViewNetrxBranchUI(netrxUIStartup){
	this.viewNetrxBrchPage = "#viewNetrxBranchHeader";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.pageTitle = $j('#pageTitle');
	this.primaryTable="#passphrasePrimaryViewTable";
	this.secondaryTable="#passphraseViewTable"
	this.clearMacid="#netrxbranchViewForm .clearMacid";
	this.makePrim="#netrxbranchViewForm .chngprimdevice";
	this.clearMacidYes="#macidClearForm #btnClearMacYes";
	this.clearMacidNo="#macidClearForm #btnClearMacNo";
	this.updateButton = this.viewNetrxBrchPage + " #netrxbranchvwbtnDone";
	this.editButton = this.viewNetrxBrchPage + " #netrxbranchvwbtnEdit";
	this.cancelButton = this.viewNetrxBrchPage + " #netrxbranchvwbtnCancel";  
	this.ptbBack = '#netrxaccbrachPTBContainer #btn_back_ptb_id';
	this.ptbUp = '#netrxaccbrachPTBContainer #btn_up_ptb_id';
	this.ptbDown = '#netrxaccbrachPTBContainer #btn_down_ptb_id';  
	this.id = this.viewNetrxBrchPage + " #netrxid ";
	this.brachId=this.viewNetrxBrchPage + " #branchid";
	this.branchStatus=this.viewNetrxBrchPage + " #branchStatus";
	this.inputFields = this.viewNetrxBrchPage + " :input[type=text]";
	this.name=this.viewNetrxBrchPage + " #organizationName";
	this.address=this.viewNetrxBrchPage + " #address";
	this.phone = this.viewNetrxBrchPage + " #branchPhone";
	this.mobile=this.viewNetrxBrchPage + " #branchMobile";
	this.email = this.viewNetrxBrchPage + " #email";
	this.deviceNo= this.viewNetrxBrchPage + " #devicesno";
	this.netrxUIStartup=netrxUIStartup;
	this.viewNetrxBranchPTB = new ViewNetrxBranchPTB(this);
}
ViewNetrxBranchUI.prototype.getNetrxUIService = function() {
	var netrxUIStartup = this.getNetrxUIStartup();
	return netrxUIStartup.getNetrxUIService();
}
ViewNetrxBranchUI.prototype.getNetrxUIStartup = function() {
	return this.netrxUIStartup;
}
ViewNetrxBranchUI.prototype.getnetmdAccTableNavigator = function() {
	var netrxAccBrchUI = this.getNetrxUIStartup();
	return netrxAccBrchUI.getnetmdAccTableNavigator();
}
ViewNetrxBranchUI.prototype.setBranchId= function(branchId) {
	var netrxAccBrchUI = this.getNetrxUIStartup();
	return netrxAccBrchUI.setBranchId(branchId);
}
ViewNetrxBranchUI.prototype.getBranch = function() {
	return this.branch;
}
ViewNetrxBranchUI.prototype.setBranch = function(branch) {
	this.branch=branch;
}
ViewNetrxBranchUI.prototype.getnetrxAccTableNavigator = function() {
	var netrxAccBrchUI = this.getNetrxUIStartup();
	return netrxAccBrchUI.getnetrxAccTableNavigator();
}
ViewNetrxBranchUI.prototype.getviewNetrxBranchPTB = function() {
	return this.viewNetrxBranchPTB;
}

ViewNetrxBranchUI.prototype.setPageTitle = function() {
	this.pageTitle.empty().html("View Netrx Branch");
}
ViewNetrxBranchUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
	commonMethodInvoker.removeErrorColor(self.phone);
	commonMethodInvoker.removeErrorColor(self.mobile);
	commonMethodInvoker.removeErrorColor(self.email);
}
ViewNetrxBranchUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}
ViewNetrxBranchUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewNetrxBrchPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewNetrxBrchPage + " input[type=text]").removeClass('newBox');
	$j(self.viewNetrxBrchPage + " textarea").removeClass('newBox');
	$j(self.viewNetrxBrchPage + " textarea").removeAttr('readonly');
		$j(self.brachId).addClass('newBox');
		$j(self.brachId).attr('readonly','readonly');
		$j(self.id).addClass('newBox');
		$j(self.id).attr('readonly','readonly');
		$j(self.branchStatus).addClass('newBox');
		$j(self.branchStatus).attr('readonly','readonly');
		$j(self.deviceNo).addClass('newBox');
		$j(self.deviceNo).attr('readonly','readonly');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
}
ViewNetrxBranchUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetrxBrchPage + " input[type=text]").attr('readonly',true);
	$j(self.viewNetrxBrchPage + " input[type=text]").addClass('newBox');
	$j(self.viewNetrxBrchPage + " textarea").attr('readonly',true);
	$j(self.viewNetrxBrchPage + " textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
}
ViewNetrxBranchUI.prototype.init = function(branchId) {
	var self = this;
	var viewNetrxBranchPTB = self.getviewNetrxBranchPTB();
	viewNetrxBranchPTB.init(self);
	pageHandler.create(constants.VIEWNETRXBRCHPAGEURL);
	self.readable();
	self.viewNetrxBranchDetails(branchId);
	pageHandler.setActivePage(self);
	self.bindEvents();
	
}
ViewNetrxBranchUI.prototype.bindEvents = function() {
	self = this;
	parent = self;
	$j(self.editButton).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable();
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var branchInfo = self.getBranch();
		var branchId = branchInfo.branch.globalId;
		self.viewNetrxBranchDetails(branchId);
		self.readable();
	});
	 $j(self.updateButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var branchPass = self.getBranchRequest();
		var branchValidator = new NetrxBranchValidator();
		var error  = branchValidator.validate(branchPass, self);
		if(error.errorStatus==false) {
			var netrxUIService = self.getNetrxUIService();
			var netrxResponse = netrxUIService.updateAccBranchNetrx(branchPass);
			//alert(JSON.stringify(netrxResponse));
			if(netrxResponse.success==true) {
				showTip(constants.BRANCHUPDATESUCCESS);//For showing the global Tip
				var branchInfo = self.getBranch();
				//alert(JSON.stringify(branchInfo));
				self.viewNetrxBranchDetails(branchInfo.branch.globalId);
				self.readable();
			} else
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netrxResponse.error));
		} else
			self.createError(error);	
	}); 
	
	$j(self.clearMacid).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		var obj=$j(this);
		commonMethodInvoker.removeErrors();
		self.createClearmacidModal(obj);
	});
	
	/* $j(self.makePrim).die('click').live('click',function(){
 		self=parent;
		self.errorHeader.hide();
		var obj=$j(this);
		var branchId=$j(self.brachId).val();
		var passPhrase=obj.closest('tr').children('td:nth-child(1)').text();
		var macid=obj.closest('tr').children('td:nth-child(2)').text();
		var resultMacJson=self.createMacJson(passPhrase,macid);
		//alert(resultMacJson);
		var netrxUIService = self.getNetrxUIService();
		var response = netrxUIService.changePrimaryNetmdBranch(resultMacJson);	
			//alert(JSON.stringify(response));
			if(response.success==true) {
			var netrxUIService = self.getNetrxUIService();
			var netrxResponse = netrxUIService.viewNetrxBranchDetails(branchId);
				self.viewPassPhraseTableAcc(netrxResponse);
				showTip("Primary Device Changed");
			}	
			else	
			commonMethodInvoker.createServerError(self.errorHeaderMacId,self.errorDataMacId, commonMethodInvoker.getErrorName(response.error));
	}); */
	
	 
}

ViewNetrxBranchUI.prototype.createClearmacidModal = function(obj) {
	var self = this;
	commonMethodInvoker.removeErrors();
	createModal(constants.NEWNETLIMSBRANCHMACIDCLEARJSON,constants.NEWNETRXMACIDCLEARMODAL);		
	openModalBox(obj,constants.NEWNETRXMACIDCLEARMODAL);
	self.macidClearevents(obj);
}

ViewNetrxBranchUI.prototype.macidClearevents = function(obj) {
	var self = this;
	$j(self.clearMacidYes).die('click').live('click',function() {
	var branchId=$j(self.brachId).val();
	var passPhrase=obj.closest('tr').children('td:nth-child(1)').text();
	var macid=obj.closest('tr').children('td:nth-child(2)').text();
	var resultMacJson=self.createMacJson(passPhrase,macid);
	//alert(resultMacJson);
	var netrxUIService = self.getNetrxUIService();
	var response = netrxUIService.clearMacNetrxBranch(resultMacJson);	
		//alert(JSON.stringify(response));
		if(response.success==true) {
		var netrxUIService = self.getNetrxUIService();
		var netrxResponse = netrxUIService.viewNetrxBranchDetails(branchId);
			self.viewPassPhraseTableAcc(netrxResponse);
			$j('#netrxmacIdClearModal').trigger('reveal:close');
			showTip("MacId Cleared");
		}	
		else	
		commonMethodInvoker.createServerError(self.errorHeaderMacId,self.errorDataMacId, commonMethodInvoker.getErrorName(response.error));
	});
	$j(self.clearMacidNo).die('click').live('click',function() {
		$j('#netrxmacIdClearModal').trigger('reveal:close');
	});
}

ViewNetrxBranchUI.prototype.createMacJson = function(passPhrase,macid) {
	self=this;
	var branch = new NetmdMacPassphrseDTO();
	branch.setheadOfficeId($j(self.id).val());
	branch.setbranchId($j(self.brachId).val());
	branch.setpassPhrase(passPhrase);
	branch.setmacId(macid);
	return branch;
	
}

ViewNetrxBranchUI.prototype.viewNetrxBranchDetails = function(branchId) {
	self=this;
	self.setBranchId(branchId);
	var NetrxUIService = self.getNetrxUIService();
	var netrxResponse = NetrxUIService.viewNetrxBranchDetails(branchId);
	if(!netrxResponse.errorMessage) {
		self.setBranch(netrxResponse);
		$j(self.id).val(netrxResponse.branch.netRxId);
		$j(self.brachId).val(netrxResponse.branch.globalId);
		$j(self.branchStatus).val(netrxResponse.branch.status);
		$j(self.name).val(netrxResponse.branch.name);
		$j(self.phone).val(netrxResponse.branch.phone);
		$j(self.deviceNo).val(netrxResponse.branch.numberOfDevices);
		$j(self.email).val(netrxResponse.branch.email);
		$j(self.address).val(commonMethodInvoker.br2nl(netrxResponse.branch.address));
		$j(self.mobile).val(netrxResponse.branch.mobile);
		dataTableProcessor.setCustomTableWithoutNavigator(self.primaryTable);
		dataTableProcessor.setCustomTableWithoutNavigator(self.secondaryTable);
		self.viewPassPhraseTableAcc(netrxResponse);
	} else 
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,netrxResponse.errorMessage);
	self.setPageTitle("View Netrx Branch"); 
}

ViewNetrxBranchUI.prototype.viewPassPhraseTableAcc = function(netrxResponse) {
	self=this;
	$j(self.secondaryTable).dataTable().fnClearTable();
	$j(self.primaryTable).dataTable().fnClearTable();
		$j(netrxResponse.branch).each(function (index,branchInfo) {
	var myData='<input type="button" value="clear" class="clearMacid stdbtn">';
	var makePrim='<input type="button" value="clear"  class="clearMacid stdbtn"><input type="button" value="make primary"  class="chngprimdevice stdbtn"  >';
		$j(branchInfo.passPhrase).each(function (index,passPhrase) {
		if(passPhrase.primary==true)
		{
		if(passPhrase.macId==null)
		{		var rowData=$j(self.primaryTable).dataTable().fnAddData([passPhrase.passPhrase,'Nil',myData]);
			var row=$j(self.primaryTable).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(self.primaryTable).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId,myData]);
		var row=$j(self.primaryTable).dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		}
		// var button=$j('<button name="save"/>');
		else
		{
		if(passPhrase.macId==null)
		{		
		var rowData=$j(self.secondaryTable).dataTable().fnAddData([passPhrase.passPhrase,'Nil',makePrim]);
		var row=$j(self.secondaryTable).dataTable().fnSettings().aoData[rowData].nTr
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		else
		{
		var rowData=$j(self.secondaryTable).dataTable().fnAddData([passPhrase.passPhrase,passPhrase.macId,makePrim]);
		var row=$j(self.secondaryTable).dataTable().fnSettings().aoData[rowData].nTr; 
		$j(row).children("td:nth-child(2)").attr("class","nilstyle");
		}
		}
				
		});	
	});

}

ViewNetrxBranchUI.prototype.getBranchRequest = function() {
	var self=this;
	var branch = new BranchNetrxDTO();
	var device=$j(self.deviceNo).val();
	if(device==""){device=0;}
	var name=$j(self.name).val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
	
	branch.setglobalId($j(self.brachId).val());
	branch.setnetRxId($j(self.id).val());
	branch.setName(name);
	branch.setEmail($j(self.email).val());
	branch.setAddress(commonMethodInvoker.nl2br($j(self.address).val()));
	branch.setPhone($j(self.phone).val());
	branch.setMobile($j(self.mobile).val());
	branch.setnumberOfDevices(parseInt(device));
	return branch;
}

ViewNetrxBranchUI.prototype.getPrevId = function(curId,branchResult) {
	var prevId;
	$j(branchResult.netRxBranch).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netRxBranch).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=branchResult.netRxBranch[index-1].globalId;
		}
	});
	return prevId;	
}
ViewNetrxBranchUI.prototype.getNextId = function(curId,branchResult) {
	var nextId;
	$j(branchResult.netRxBranch).each(function (index, rowbranch) {
		if(curId==rowbranch.globalId)	{
			var arrayLength=(branchResult.netRxBranch).length;
			var comp=arrayLength-1;
			if(index==comp)
				nextId = curId;
			else
				nextId=branchResult.netRxBranch[index+1].globalId;	
		}
	});	
	return nextId;	
} 