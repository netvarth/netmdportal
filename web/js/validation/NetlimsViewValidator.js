function NetlimsViewValidator() {
	this.validate = function(netlims, source) {
		//alert(JSON.stringify(netlims));
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty(netlims.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netlims.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(netlims.getheadOfficeName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficename,constants.HEDNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netlims.getheadOfficeName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficename,constants.HEDNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		//Email Validation
		if(validator.isEmpty(netlims.getheadOfficeEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netlims.getheadOfficeEmail())) {
			if(!validator.validateEmail(netlims.getheadOfficeEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(validator.isEmpty(netlims.getownerEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netlims.getownerEmail())) {
			if(!validator.validateEmail(netlims.getownerEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		
		if(validator.isEmpty(netlims.getownerFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netlims.getownerFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		

		
		 if(!validator.isEmpty(netlims.getownerPhone())) {
			if(!validator.validatePhone(netlims.getownerPhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownerphone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
		if(!validator.isEmpty(netlims.getheadOfficePhone())) {
			if(!validator.validatePhone(netlims.getheadOfficePhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficephone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
			
		if(!validator.isEmpty(netlims.getheadOfficeMobile())) {
			if(!validator.validateMobile(netlims.getheadOfficeMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficemobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(!validator.isEmpty(netlims.getownerMobile())) {
			if(!validator.validateMobile(netlims.getownerMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownermobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		 
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
