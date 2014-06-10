
function ChangePasswrdValidator() {
	this.validate = function(self) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty($j(self.oldpassword).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.oldpassword,constants.OLDPASSWRDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(validator.isEmpty($j(self.newpassword).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.newpassword,constants.NEWPASSWRDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(validator.isEmpty($j(self.confirmpassword).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.confirmpassword,constants.CONFIRMPASSWRDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(($j(self.newpassword).val()!="")&&($j(self.confirmpassword).val()!="")) {
			if(($j(self.newpassword).val())!=($j(self.confirmpassword).val())){
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.newpassword,constants.PASSWRDMISMATCH);
			errorMsgs.push(errorMessage);
		}
		}
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
