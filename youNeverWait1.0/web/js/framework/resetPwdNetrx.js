
	var a = window.location.toString();
	var name = a.substring(a.indexOf("=")+1);
	
	
$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";

$j(document).ready(function() {
	
	$j("#newPwdNetrx").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#newPwdNetrx").val()=="")
				return false;
			$j("#confirmPwdNetrx").focus();
		}		
	});
	$j("#confirmPwdNetrx").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#confirmPwdNetrx").val()=="")
				return false;
			$j("#resetPwdNetrx").trigger('click');
		}		
	});

	$j('#resetPwdNetrx').die('click').live("click",function(){
		if(validate()) {
			var  newPwd=$j('#newPwdNetrx').val();
			var confirmPwd=$j('#confirmPwdNetrx').val();
			var loginData='{'+'"password"'+':"'+newPwd+'"'+','+'"userName"'+':"' + name +'"'+'}';
			var resetResult=postdataToServer("/youNeverWait/ws/ui/netRx/resetPassword", loginData );
			if(resetResult.success==true)
			{
			$j('#newPwdNetrx').val("");
			$j('#confirmPwdNetrx').val("");
			successTip("Password reset successful",'',$j('#loginErrorNetrx'));
			}

			
			/*$j.ajax({
				type: "POST",
				url: serverPath + "/weblims/ws/ui/auth/login",
				data: loginData,
				contentType: "application/json",
				dataType: "json",
				async: false,
				success:loginSuccess,
				error: loginError						
			});	*/	
	}
	});
	
	function loginSuccess(data) {
		if(data.success==true)
			location.reload();
		else
			errorTips("Incorrect username/password",'',$j('#loginErrorNetrx'));	
	}

	function loginError() {
		errorTips("Incorrect username/password",'',$j('#loginErrorNetrx'));

	}
	
	function validate() {
		var newPwd = $j('#newPwdNetrx');
		var ConfirmPwd = $j('#confirmPwdNetrx'); 
		var bValid=true,NewPwdValid=true,ConfirmPwdValid=true,PwdMatch=true;
		//bValid = bValid && checkNull( newPwd, "New Password", $j('#loginErrorNetrx'));
		//bValid = bValid && checkNull( ConfirmPwd, "Confirm Password", $j('#loginErrorNetrx'));
		ConfirmPwdValid = checkNull( ConfirmPwd, "confirm password", $j('#loginErrorNetrx'));
		NewPwdValid = checkNull( newPwd, "new password", $j('#loginErrorNetrx'));
		NewPwdValid=NewPwdValid&&ConfirmPwdValid; 
		if($j('#confirmPwdNetrx').val()!=''&&$j('#newPwdNetrx').val()!='')
		{
		
		if($j('#confirmPwdNetrx').val()!=$j('#newPwdNetrx').val())
		{
		errorTips("*"+"password mismatch",'',$j('#loginErrorNetrx'));
		PwdMatch=false;
		}
		}
		PwdMatch=PwdMatch&&ConfirmPwdValid;
		bValid=bValid && PwdMatch;

		return bValid;
	}
	function checkNull(o,n, errorDiv){
		if(o.val().length=='0'){
			o.addClass("error");
			errorTips("*"+"Please enter"+" " + n,o ,errorDiv);
			return false;	
		} 
		else 
			return true;
	}
});	



	
	function postdataToServer(posturl, requestData ) {
	var responseData;
	jQuery.ajax({
		type: "POST",
		url: posturl,
		data: requestData,
		contentType: "application/json",
		dataType: "json",
		async: false,
		success:function(response) {
			if(response==null){
				//window.location.href="/weblims/ws/ui/html/startUp";
				location.reload();
				return false;
			}	
			else{
				responseData = response;
			}	
		},
		error: function(xhr, ajaxOptions, thrownError) {
			alert(thrownError);
			responseData = false;	
			//window.location.href="/weblims/ws/ui/html/startUp";
			location.reload();  
		}
	});	
	return responseData;
}

		function  errorTips(t,obj,errorDiv) {
		errorDiv.attr('style','color:#ff0000');
		errorDiv.text(t);
		setTimeout(function() {
			if(obj!='') {
				obj.focus();
				obj.removeClass('error');
				obj.removeAttr('style');
			}
			errorDiv.text("");
		}, 5000 );
	}
function showTip(message) {
	$j('#BeeperBox .UIBeep_Title').html(message);
	$j("#BeeperBox").show();
    timerId = setTimeout(function () {
        $j("#BeeperBox").hide();
    }, 5000);
}
	function  successTip(t,obj,errorDiv) {
		errorDiv.attr('style','color:#008000');
		errorDiv.text(t);
		setTimeout(function() {
			if(obj!='') {
				obj.focus();
				obj.removeClass('error');
				obj.removeAttr('style');
			}
			errorDiv.text("");
		}, 10000 );
	}