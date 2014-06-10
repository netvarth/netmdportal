function ErrorDTO() {
	this.errorStatus=false;
	this.errorMsgs=[];
}
	
ErrorDTO.prototype.setErrorMsgs = function(errorMsgs) {
	this.errorMsgs=errorMsgs;
}

ErrorDTO.prototype.setErrorStatus = function(errorStatus) {
	this.errorStatus=errorStatus;
}