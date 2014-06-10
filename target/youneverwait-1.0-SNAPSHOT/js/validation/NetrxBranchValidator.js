function NetrxBranchValidator() {

	this.validate = function(branch, source) {
		var error = new ErrorDTO();
		var errorMsgs = []; // for storing the error messages list having field,Message
		//Name Validation
		if(validator.isEmpty(branch.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.name,constants.NAMEREQUIRED);
			errorMsgs.push(errorMessage);
		} else if(!validator.validateName(branch.getName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.name,constants.NAMEINVALID);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(branch.getPhone())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.phone,constants.PHONEREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(branch.getPhone())) {
			if(!validator.validatePhone(branch.getPhone())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.phone,constants.PHONEINVALID);
				errorMsgs.push(errorMessage);
			}	
		}
		//Email Validation
		if(!validator.isEmpty(branch.getEmail())) {
			if(!validator.validateEmail(branch.getEmail())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.email,constants.EMAILINVALID);
				errorMsgs.push(errorMessage);
			}
		}		
		if(validator.isEmpty(branch.getMobile())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.mobile,constants.MOBILEREQUIRED);
			errorMsgs.push(errorMessage);
		}
		else if(!validator.isEmpty(branch.getMobile())) {
			if(!validator.validateMobile(branch.getMobile())) {
				error.setErrorStatus(true);
				var errorMessage = new ErrorMessageDTO(source.mobile,constants.MOBILEINVALID);
				errorMsgs.push(errorMessage);
			}
		}
		if(branch.getnumberOfDevices()==0) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(source.numberOfDevices,constants.DEVICENUMBERREQUIRED);
			errorMsgs.push(errorMessage);
		}
		error.setErrorMsgs(errorMsgs);
		return error;
	}

}
