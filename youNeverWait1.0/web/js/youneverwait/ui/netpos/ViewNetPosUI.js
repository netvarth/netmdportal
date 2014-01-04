function ViewNetPosUI(NetPosStartup) {
	this.viewNetPosPage = "#viewNetPos";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewNetPos #id label";
	this.name = "#viewNetPos #name";
	this.description="#viewNetPos #description";
	this.updateButton = "#viewNetPos #btnNetPosSave";
	this.editButton = "#viewNetPos #btnNetPosEdit";
	this.cancelButton = "#viewNetPos #btnNetPosCancel"; 
	this.ptbBack = "#NetPosGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#NetPosGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#NetPosGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.NetPosStartup=NetPosStartup;
	this.viewNetPosPTB = new ViewNetPosPTB(this);
}
ViewNetPosUI.prototype.getNetPosUIStartup = function() {
	return this.NetPosStartup;
}

ViewNetPosUI.prototype.getViewNetPosPTB = function() {
	return this.viewNetPosPTB;
}

ViewNetPosUI.prototype.getNetPosTableNavigator = function() {
	var NetPosUIStartup = this.getNetPosUIStartup();
	return NetPosUIStartup.getNetPosTableNavigator();
}

ViewNetPosUI.prototype.getNetPosService = function() {
	var NetPosUIStartup = this.getNetPosUIStartup();
	return NetPosUIStartup.getNetPosService();
}
//Set the page title of the area ui page
ViewNetPosUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewNetPosUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}
ViewNetPosUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewNetPosPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

ViewNetPosUI.prototype.init = function(netPosId) {
	var self = this;
	var viewNetPosPTB = self.getViewNetPosPTB();
	viewNetPosPTB.init(self);
	pageHandler.create(constants.VIEWNETPOSURL);
	
	self.bindEvents();
	self.viewNetPos(netPosId);
}

ViewNetPosUI.prototype.viewNetPos = function(netPosId) {
	self=this;
	var header = constants.VIEWNETPOSTITLE;
	var netPosService = self.getNetPosService();
	var netPosResponse = netPosService.viewNetPos(netPosId);
	if(netPosResponse.success==true) {
		var netPos = new NetPosDTO();
		netPos.setNetPosName(netPosResponse.netPosName);
		netPos.setDescription(netPosResponse.description);
		netPos.setId(netPosResponse.id);
		self.setNetPos(netPosResponse);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netPosResponse.error));
	}
	self.setPageTitle(header);
}

ViewNetPosUI.prototype.setNetPos = function(netPosResponse) {
	self=this;
	$j(self.id).text(netPosResponse.id);
	$j(self.name).val(netPosResponse.netPosName);
	$j(self.description).val(netPosResponse.description);
}

ViewNetPosUI.prototype.getNetPos = function() {
	var self=this;
	var id = parseInt($j(self.id).text());
	var name = $j(self.name).val();
	var description = $j(self.description).val();
	var NetPos = new NetPosDTO();
	NetPos.setId(id);
	NetPos.setNetPosName(name);
	NetPos.setDescription(description);
	return NetPos;
}

ViewNetPosUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewNetPosPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}




ViewNetPosUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewNetPosPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewNetPosPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewNetPosPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewNetPosPage + " input[type=text],textarea").removeAttr('disabled');
}

ViewNetPosUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewNetPosPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewNetPosPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewNetPosPage + " input[type=text],textarea").attr('disabled',true);
}

ViewNetPosUI.prototype.getPrevId = function(curId,NetPosResult) {
	var prevId;
	$j(NetPosResult.netPos).each(function (index, rowNetPos) {
		if(curId==rowNetPos.id)	{
			var arrayLength=(NetPosResult.netPos).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=NetPosResult.netPos[index-1].id;
		}
	});
	return prevId;	
}
	
ViewNetPosUI.prototype.getNextId = function(curId,NetPosResult) {
	var nextId;
	$j(NetPosResult.netPos).each(function (index, rowNetPos) {
		if(curId==rowNetPos.id)	{
			var arrayLength=(NetPosResult.netPos).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=NetPosResult.netPos[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewNetPosUI.prototype.bindEvents = function() {
	self = this;

	$j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var NetPos = self.getNetPos();
		self.viewNetPos(NetPos.id);
		self.readable();
	});
	
	
	//Update NetPos Button Click Event
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var netPos = self.getNetPos();
		var netPosValidator = new NetPosValidator();
		var error  = netPosValidator.validate(netPos);
		if(error.errorStatus==false) {
			var netPosService =self.getNetPosService();
			var netPosResponse = netPosService.updateNetPos(netPos);
			if(netPosResponse.success==true) {
				showTip(constants.NETPOSUPDATESUCCESS);//For showing the global Tip
				var netPos = self.getNetPos();
				self.viewNetPos(netPos.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(netPosResponse.error));
		}
		} else {
		self.createError(error);
	}		
	});
	
	
	//Edit NetPos Button Click Event
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable();
		
	});
	
	
}