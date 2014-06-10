function OrganizationUserValidator() {

	
	this.validate = function(orgnz, source) {
		//alert(JSON.stringify(orgnz));
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty(orgnz.getfirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.firstname,constants.ORGNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(orgnz.getfirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.firstname,constants.ORGNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(orgnz.getuserType())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.usertype,constants.USERTYPEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(orgnz.getuserType())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.usertype,constants.USERTYPEINVALID);
			errorMsgs.push(errorMessage);
		}
		
		//Email Validation
		if(validator.isEmpty(orgnz.getemail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.email,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(orgnz.getemail())) {
			if(!validator.validateEmail(orgnz.getemail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.email,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		
		
		
		
		 if(!validator.isEmpty(orgnz.getphone())) {
			if(!validator.validatePhone(orgnz.getphone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.phone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
		
			
		if(!validator.isEmpty(orgnz.getmobile())) {
			if(!validator.validateMobile(orgnz.getmobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.mobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		
		 
		error.setErrorMsgs(errorMsgs);
		return error;
	

}


}
