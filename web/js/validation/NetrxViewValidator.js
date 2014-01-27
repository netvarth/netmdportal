function NetrxViewValidator() {
	this.validate = function(netrx, source) {
		//alert(JSON.stringify(netrx));
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty(netrx.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netrx.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(netrx.getheadOfficeName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficename,constants.HEDNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netrx.getheadOfficeName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficename,constants.HEDNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		//Email Validation
		if(validator.isEmpty(netrx.getheadOfficeEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netrx.getheadOfficeEmail())) {
			if(!validator.validateEmail(netrx.getheadOfficeEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(validator.isEmpty(netrx.getownerEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netrx.getownerEmail())) {
			if(!validator.validateEmail(netrx.getownerEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		
		if(validator.isEmpty(netrx.getownerFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netrx.getownerFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		

		
		 if(!validator.isEmpty(netrx.getownerPhone())) {
			if(!validator.validatePhone(netrx.getownerPhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownerphone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
		if(!validator.isEmpty(netrx.getheadOfficePhone())) {
			if(!validator.validatePhone(netrx.getheadOfficePhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficephone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
			
		if(!validator.isEmpty(netrx.getheadOfficeMobile())) {
			if(!validator.validateMobile(netrx.getheadOfficeMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficemobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(!validator.isEmpty(netrx.getownerMobile())) {
			if(!validator.validateMobile(netrx.getownerMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownermobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		 
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
