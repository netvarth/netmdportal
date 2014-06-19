function OrgzViewValidator() {
	this.validate = function(netmd, source) {
		//alert(JSON.stringify(netmd));
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty(netmd.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netmd.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.organizationname,constants.ORGNAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		
		//Email Validation
		if(validator.isEmpty(netmd.getheadOfficeEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netmd.getheadOfficeEmail())) {
			if(!validator.validateEmail(netmd.getheadOfficeEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficeemail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(validator.isEmpty(netmd.getownerEmail())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(netmd.getownerEmail())) {
			if(!validator.validateEmail(netmd.getownerEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.owneremail,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		
		if(validator.isEmpty(netmd.getownerFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(netmd.getownerFirstName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.ownerfirstname,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		

		
		 if(!validator.isEmpty(netmd.getownerPhone())) {
			if(!validator.validatePhone(netmd.getownerPhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownerphone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
		if(!validator.isEmpty(netmd.getheadOfficePhone())) {
			if(!validator.validatePhone(netmd.getheadOfficePhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficephone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
			
		if(!validator.isEmpty(netmd.getheadOfficeMobile())) {
			if(!validator.validateMobile(netmd.getheadOfficeMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.headofficemobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(!validator.isEmpty(netmd.getownerMobile())) {
			if(!validator.validateMobile(netmd.getownerMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.ownermobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		 
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
