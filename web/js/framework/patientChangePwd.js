
	$j('#settings').die('click').live("click",function() {
	var obj=$j(this);
		createModal(constants_patientChangePwdJsonas,'changePatientpwdModal');	
		openModalBox(obj,'changePatientpwdModal');
	});
	
			
	$j('#changepatientPasswordForm #btnChangepatientPwdSubmit').die('click').live('click',function(){
	removeErrors();	
	if(validatePatientChangePassword())
	{
		var response = submitChangePasswordInfo();
			if(response.success==true){
			showTip("Password Changed Successfully");
			$j("#changepatientPasswordForm input[type=text],#changepatientPasswordForm input[type=password]").val("");

		}
		else {
			updateTipsNew(getErrorName(response.error),$j('#changePatientpwdModal #errorDivChangepatientPwdData'),$j('#changePatientpwdModal #errorDivHeader'));
		}
	}	
	});
	
function submitChangePasswordInfo(){
	var resultJson = createSubmitJson();
	var response = postdataToServer(constant_patientChangePassword_Url, resultJson );	
	return response;
}

function createSubmitJson(){
	var username=getRequestData('/youNeverWait/ws/ui/auth/getCurrentUser');
    var changepwdDetails = '{"oldPassword":"'+$j('#changepatientPasswordForm #oldpatientpassword').val()  +'",';
		changepwdDetails += '"username":"'+ username +'",';
		changepwdDetails +='"newPassword":"' + $j('#changepatientPasswordForm #newpatientpassword').val() + '"}';
	return changepwdDetails;
}


function validatePatientChangePassword(){
	//var userName=$j('#patientusername');
	var oldPassword=$j('#changepatientPasswordForm #oldpatientpassword');
	var newPassword=$j('#changepatientPasswordForm #newpatientpassword');
	var confirmPassword=$j('#changepatientPasswordForm #confirmpatientpassword');
	var newPassword1=$j('#changepatientPasswordForm #newpatientpassword').val();
	var confirmPassword1=$j('#changepatientPasswordForm #confirmpatientpassword').val();

	
	var bValid=true,userNameValid=true,oldPasswordValid=true,newPasswordValid=true,confirmPasswordValid=true,userNameregValid=true,PwdCompValid=true;
	//userNameValid = checkNull( userName,constants_userNameRequired);
	oldPasswordValid = checkNull( oldPassword,constants_oldPasswordRequired);
	oldPasswordValid=oldPasswordValid&&userNameValid;
	newPasswordValid = checkNull( newPassword,constants_newPasswordRequired);
	newPasswordValid=newPasswordValid&&oldPasswordValid;
	confirmPassword = checkNull( confirmPassword,constants_confirmPasswordRequired);
	confirmPassword=confirmPassword&&newPasswordValid;
	
	/* if((isEmpty(userName)))
	userNameregValid =  checkRegexp(userName, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i	, constants_userNameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	userNameregValid=userNameregValid&&confirmPassword;
	 */
	if((newPassword1!="")&&(confirmPassword1!="")){
	if(newPassword1!=confirmPassword1)
	{
		createError(constants_passwordMismatch,newPassword);
		PwdCompValid=false;
	}}
	PwdCompValid=PwdCompValid&&confirmPassword;
	
	bValid=bValid && PwdCompValid;
	return bValid;
}
	
	
	$j('#changepatientPasswordForm #btnChangepatientPwdCancel').die('click').live('click',function(){
	removeErrors();
	$j("#changepatientPasswordForm input[type=text],#changepatientPasswordForm input[type=password]").val("");
	});