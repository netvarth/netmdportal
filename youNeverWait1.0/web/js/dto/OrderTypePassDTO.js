function OrderTypePassDTO(orderType,userlabId) {
	if(arguments.length>=1) {
	this.labId=userlabId;
	this.orderTypeCodes=JSON.stringify(orderType);
	}	
}
OrderTypeDTO.prototype.setlabId= function(labId) {
	this.labId=labId;
}
OrderTypeDTO.prototype.getlabId = function() {
	return this.labId;
}
OrderTypeDTO.prototype.setOrderTypeCodes= function(orderTypeCodes) {
	this.orderTypeCodes=orderTypeCodes;
}
OrderTypeDTO.prototype.getOrderTypeCodes = function() {
	return this.orderTypeCodes;
}
