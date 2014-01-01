function NetPosValidator() {

	this.validate = function(netPos, source) {
		alert(JSON.stringify(netPos));
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		
		//Name Validation
		if(validator.isEmpty(netPos.getName())) {
	
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEREQUIRED);
			alert(constants.ORGNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netPos.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(netPos.gethoName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficename,constants.HEDNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netPos.gethoName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficename,constants.HEDNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		
		if(validator.isEmpty(netPos.getofName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netPos.getofName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		
		 if(validator.isEmpty(netPos.getuName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.username,constants.USERNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		}  
		
		
		//Email Validation
		
		if(validator.isEmpty(netPos.gethoEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netPos.gethoEmail())) {
			if(!validator.validateEmail(netPos.gethoEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(validator.isEmpty(netPos.getoEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netPos.getoEmail())) {
			if(!validator.validateEmail(netPos.getoEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		
		//phone Validation
		
		 if(!validator.isEmpty(netPos.getoPh())) {
			if(!validator.validatePhone(netPos.getoPh())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownerphone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
		if(!validator.isEmpty(netPos.gethoPh())) {
			if(!validator.validatePhone(netPos.gethoPh())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficephone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
			
			//mob Validation
			
			
		if(!validator.isEmpty(netPos.gethoMob())) {
			if(!validator.validateMobile(netPos.gethoMob())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficemobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(!validator.isEmpty(netPos.getoMob())) {
			if(!validator.validateMobile(netPos.getoMob())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownermobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		
		//password Validation
		
		
		 if(validator.isEmpty($j(self.password).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.password,constants.PASSWRDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(validator.isEmpty($j(self.rePassword).val())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.rePassword,constants.CONFIRMPASSWRDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(($j(self.password).val()!="")&&($j(self.rePassword).val()!="")) {
			if(($j(self.password).val())!=($j(self.rePassword).val())){
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(self.password,constants.PASSWRDMISMATCH);
			errorMsgs.push(errorMessage);
		}
		} 
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
