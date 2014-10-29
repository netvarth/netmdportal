

$j('#pageTitle').html("Settings");
//create the table structure for order Table
response = getRequestData('/youNeverWait/json/toolbars/adminChangePwd.json');
var adminTB = new AdminToolBar(response);
$j('#tabs-1').html(adminTB.result);	

	$j('#btnChangePwd').die('click').live("click",function() {
	var obj=$j(this);
		createModal(constants_adminChangePwdJsonas,'changepwdModal');	
		openModalBox(obj,'changepwdModal');
	});

	$j('#btnTestList').die('click').live("click",function() {
		removeErrors();
		$j.cachedScript("/youNeverWait/js/youneverwait/netlims/test/testEntryPoint.js").done(function(script, textStatus) {
			
		})
	
	});

	$j('#btnSpecimenList').die('click').live("click",function() {
		//alert("btnSpecimen click")
		var specimenUI = new SpecimenUIStartup();			
		specimenUI.init();
	
	});
	
	$j('#btn_userlogcontrol_ptb_id').die('click').live("click",function() {
	var currentstatus=getRequestData('/youNeverWait/ws/ui/superAdmin/enableLogStatus');
	var obj=$j(this);
		createModal(constants_userLogJson,'userLogModal');	
		openModalBox(obj,'userLogModal');
		if(currentstatus.status==false){$j('#userlogForm #userDisable').attr( "checked",true );}
		else{$j('#userlogForm #userEnable').attr( "checked",true );}
		
	});
		
	$j('#logPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
	removeErrors();
	//$j('#pageToolBar-Container').html("");
	$j.cachedScript(constant_SettingsEntry_Url).done(function(script, textStatus) {
	})
	$j('#filter').hide();
	$j('#logPTBContainer').hide();
	$j('#user-filter-toolbar').hide();

	});
	
	$j('#syncLogPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
	removeErrors();
	//$j('#pageToolBar-Container').html("");
	$j.cachedScript(constant_SettingsEntry_Url).done(function(script, textStatus) {
	})
	$j('#filter').hide();
	$j('#syncLogPTBContainer').hide();
	$j('#sync-filter-toolbar').hide();

	})
	
	$j('#btnUserLogList').die('click').live("click",function() {
	removeErrors();
	$j.cachedScript(constant_UserLogEntry_Url).done(function(script, textStatus) {
	})
	});
	
	$j('#btnSyncLogList').die('click').live("click",function() {
	removeErrors();
	$j.cachedScript(constant_SyncLogEntry_Url).done(function(script, textStatus) {
	})
	});	

			
	$j('#changePasswordForm #btnChangePwdSubmit').die('click').live('click',function(){
	removeErrors();	
	if(validateAdminChangePassword())
	{
		var response = submitChangePasswordInfo();
			if(response.success==true){
			showTip("Password Changed Successfully");
			$j('#errorDivChangePwdData').hide();
			$j("#changePasswordForm input[type=text],#changePasswordForm input[type=password]").val("");

		}
		else {
			updateTipsNew(getErrorName(response.error),$j('#changepwdModal #errorDivChangePwdAdminData'),$j('#changepwdModal #errorDivHeader'));
		}
	}	
	});
	
	$j('#userlogForm #btnUserLogSubmit').die('click').live('click',function(){
	if($j("#userlogForm input[name='userlog']:checked").val()=='Disable')
	{
	userLogData = '{"enableLog":'+false+'}';
	}
	else
	{
	userLogData = '{"enableLog":'+true+'}';
	}
	var response = postdataToServer(constant_userLogControl_Url, userLogData );
	//alert(JSON.stringify(response));
	if(response.success==true){
	showTip("Updated Successfully");
	$j('#errorDivUserLogData').hide();
	}
	else {
		updateTipsNew(getErrorName(response.error),$j('#userLogModal #errorDivUserLogData'),$j('#userLogModal #errorDivHeader'));
	}
	
	});
function submitChangePasswordInfo(){
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer(constant_adminChangePassword_Url, resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJson(){
	var userdata =getRequestData('/youNeverWait/ynw/auth/user');
    var changepwdDetails = '{"oldPassword":"'+$j('#oldpassword').val()  +'",';
		changepwdDetails += '"username":"'+ userdata.userName +'",';
		changepwdDetails +='"newPassword":"' + $j('#newpassword').val() + '"}';
	return changepwdDetails;
}


function validateAdminChangePassword(){
	var userName=$j('#username');
	var oldPassword=$j('#oldpassword');
	var newPassword=$j('#newpassword');
	var confirmPassword=$j('#confirmpassword');
	var newPassword1=$j('#newpassword').val();
	var confirmPassword1=$j('#confirmpassword').val();

	
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
	
	
	$j('#changePasswordForm #btnChangePwdCancel').die('click').live('click',function(){
	removeErrors();
	$j("#changePasswordForm input[type=text],#changePasswordForm input[type=password]").val("");
	});