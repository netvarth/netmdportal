function ResponseDTO(uid, success, error) {
	this.uid = uid;
	this.success=success;
	this.error= error;
}
	
ResponseDTO.setUid = function(uid) {
	this.uid=uid;
}

ResponseDTO.setSuccess = function(success) {
	this.success=success;
}

ResponseDTO.setError= function(error) {
	this.error=error;
}