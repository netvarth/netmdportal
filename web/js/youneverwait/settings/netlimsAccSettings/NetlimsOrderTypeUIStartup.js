function NetlimsOrderTypeUIStartup(){
	this.netlimsAccorderTypeModal='#orderTypeModalNetLims';
	this.netlimsAccorderTypePage='#ordertypeFormNetLimsAcc';
	this.createButton = $j(this.netlimsAccorderTypePage + " #btnordertypeSubmit");
	this.cancelButton = $j(this.netlimsAccorderTypePage + " #btnordertypeCancel");
	this.errorHeader = $j(this.netlimsAccorderTypePage + " #errorDivHeader");
	this.errorData = $j(this.netlimsAccorderTypePage + " #errorDivChangePwdData");
    this.userlabId=userdata.labId;
	this.netlimsAccService = new NetlimsAccServiceImpl();
	this.inputFields = this.netlimsAccorderTypePage + " :input[type=text]";
	this.agentOrder=this.netlimsAccorderTypePage + " #agentorder";
	this.blanketOrder=this.netlimsAccorderTypePage + " #blanketorder";
	this.walkinOrder = this.netlimsAccorderTypePage + " #walkinorder"; 
}
NetlimsOrderTypeUIStartup.prototype.getNetlimsUIService = function() {
	return this.netlimsAccService;
}

NetlimsOrderTypeUIStartup.prototype.init = function() {
	 self =this;
	var orderTypeService = self.getNetlimsUIService();
	var orderTypeResponse =orderTypeService.orderTypeNetlims(self.userlabId);
	var orderFormat=$j.parseJSON(orderTypeResponse.orderTypeCodes);
	var agentordertype=orderFormat.agentorder;
	var blanketorder=orderFormat.blanketorder;
	var walkinorder=orderFormat.walkinorder;
	$j(self.agentOrder).val(agentordertype);
	$j(self.blanketOrder).val(blanketorder);
	$j(self.walkinOrder).val(walkinorder); 
	self.bindEvents(); 
}
  NetlimsOrderTypeUIStartup.prototype.clearFields = function() {
	$j(this.netlimsAccorderTypePage + " input[type=text]").val("");
}
NetlimsOrderTypeUIStartup.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.agentOrder);
	commonMethodInvoker.removeErrorColor(self.blanketOrder);
	commonMethodInvoker.removeErrorColor(self.walkinOrder);
}
NetlimsOrderTypeUIStartup.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}

NetlimsOrderTypeUIStartup.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.netlimsAccorderTypeModal + self.netlimsAccorderTypePage + " input[type=text]").val("");	
	$j(self.netlimsAccorderTypeModal).trigger('reveal:close');
	$j(self.netlimsAccorderTypeModal).remove();
	
}

NetlimsOrderTypeUIStartup.prototype.create = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var orderType = self.getOrderTypeDetail();
	var orderPassType = new OrderTypePassDTO(orderType,self.userlabId);
	//var ordertypeValidator = new OrdertypeValidator();
	//var error  = ordertypeValidator.validate(self);
	//if(error.errorStatus==false) {
		var orderTypeService = self.getNetlimsUIService();
		var orderTypeResponse =orderTypeService.setOrderTypeNetlims(orderPassType);
		if(orderTypeResponse.error==null) {
			showTip("Order Type Successfully Created");//For showing the global Tip			
		} else 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(orderTypeResponse.error));
	//} else 
	//	self.createError(error); 
}

NetlimsOrderTypeUIStartup.prototype.getOrderTypeDetail = function() {
	var self=this;
	var orderType = new OrderTypeDTO();
	orderType.setBlanketOrder($j(self.blanketOrder).val());
	orderType.setAgentOrder($j(self.agentOrder).val());
	orderType.setWalkinOrder($j(self.walkinOrder).val());
	return orderType;
}
 
NetlimsOrderTypeUIStartup.prototype.bindEvents = function() {
self = this;	
	self.removecolors(self.netlimsAccorderTypePage + " :input");
	

$j(self.netlimsAccorderTypeModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});	
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});	
	
	self.createButton.die('click').live('click',function(){
		self.create();
	});	
}	  