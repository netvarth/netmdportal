$j('#pageTitle').html("Settings");
//create the table structure for order Table
response = getRequestData('/youNeverWait/json/toolbars/netMdAccChangePwd.json');
var adminTB = new AdminToolBar(response);
$j('#tabs-1').html(adminTB.result);	
	
	$j('#btnNetMdChangePwd').die('click').live("click",function() {
	var obj=$j(this);
		createModal(constants_netMdAccChangePwdJson,'changepwdModalNetMd');	
		openModalBox(obj,'changepwdModalNetMd');
	});

	$j('#changePasswordFormNetMdAcc #btnChangePwdSubmit').die('click').live('click',function(){
	removeErrors();	
		if(validateNetMdAccChangePassword())
		{
			var response = submitChangePasswordInfo();
				if(response.success==true){
				showTip("Password Changed Successfully");
				$j('#errorDivChangePwdData').hide();
				$j("#changePasswordFormNetMdAcc input[type=text],#changePasswordFormNetMdAcc input[type=password]").val("");

			}
			else {
				updateTipsNew(getErrorName(response.error),$j('#changepwdModalNetMd #errorDivChangePwdData'),$j('#changepwdModalNetMd #errorDivHeader'));
			}
		}	
	});
	
function submitChangePasswordInfo(){
	var resultJson = createSubmitJson();
	var response = postdataToServer(constant_netMdAccChangePassword_Url, resultJson );	
	return response;
}

function createSubmitJson(){
	var userdata =getRequestData('/youNeverWait/netmd/auth/user');
    var changepwdDetails = '{"oldPassword":"'+$j('#changePasswordFormNetMdAcc #oldpassword').val()  +'",';
		changepwdDetails += '"username":"'+ userdata.userName +'",';
		changepwdDetails +='"newPassword":"' + $j('#changePasswordFormNetMdAcc #newpassword').val() + '"}';
	return changepwdDetails;
}


function validateNetMdAccChangePassword(){
	//var userName=$j('#changePasswordFormNetMdAcc #username');
	var oldPassword=$j('#changePasswordFormNetMdAcc #oldpassword');
	var newPassword=$j('#changePasswordFormNetMdAcc #newpassword');
	var confirmPassword=$j('#changePasswordFormNetMdAcc #confirmpassword');
	var newPassword1=$j('#changePasswordFormNetMdAcc #newpassword').val();
	var confirmPassword1=$j('#changePasswordFormNetMdAcc #confirmpassword').val();

	
	var bValid=true,userNameValid=true,oldPasswordValid=true,newPasswordValid=true,confirmPasswordValid=true,userNameregValid=true,PwdCompValid=true;
	//userNameValid = checkNull( userName,constants_userNameRequired);
	oldPasswordValid = checkNull( oldPassword,constants_oldPasswordRequired);
	oldPasswordValid=oldPasswordValid&&userNameValid;
	newPasswordValid = checkNull( newPassword,constants_newPasswordRequired);
	newPasswordValid=newPasswordValid&&oldPasswordValid;
	confirmPassword = checkNull( confirmPassword,constants_confirmPasswordRequired);
	confirmPassword=confirmPassword&&newPasswordValid;
	
	/*if((isEmpty(userName)))
	userNameregValid =  checkRegexp(userName, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/, constants_userNameInvalidMessage ,$j('#errorDivNewNetlimsData'));
	userNameregValid=userNameregValid&&confirmPassword;*/
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
	
	
$j('#changePasswordFormNetMdAcc #btnChangePwdCancel').die('click').live('click',function(){
	removeErrors();
	$j("#changePasswordFormNetMdAcc input[type=text],#changePasswordFormNetMdAcc input[type=password]").val("");
});
	