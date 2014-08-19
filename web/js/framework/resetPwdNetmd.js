
	var a = window.location.toString();
	var name = a.substring(a.indexOf("=")+1);
	
	
$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";

$j(document).ready(function() {
	
	$j("#newPwdNetmd").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#newPwdNetmd").val()=="")
				return false;
			$j("#confirmPwdNetmd").focus();
		}		
	});
	$j("#confirmPwdNetmd").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#confirmPwdNetmd").val()=="")
				return false;
			$j("#resetPwdNetmd").trigger('click');
		}		
	});

	$j('#resetPwdNetmd').die('click').live("click",function(){
		if(validate()) {
			var  newPwd=$j('#newPwdNetmd').val();
			var confirmPwd=$j('#confirmPwdNetmd').val();
			var loginData='{'+'"password"'+':"'+newPwd+'"'+','+'"userName"'+':"' + name +'"'+'}';
			var resetResult=postdataToServer("/youNeverWait/netmd/ui/netMd/resetPassword", loginData );
			if(resetResult.success==true)
			{
			$j('#newPwdNetmd').val("");
			$j('#confirmPwdNetmd').val("");
			successTip("Password reset successful",'',$j('#loginErrorNetmd'));
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
			errorTips("Incorrect username/password",'',$j('#loginErrorNetmd'));	
	}

	function loginError() {
		errorTips("Incorrect username/password",'',$j('#loginErrorNetmd'));

	}
	
	function validate() {
		var newPwd = $j('#newPwdNetmd');
		var ConfirmPwd = $j('#confirmPwdNetmd'); 
		var bValid=true,NewPwdValid=true,ConfirmPwdValid=true,PwdMatch=true;
		//bValid = bValid && checkNull( newPwd, "New Password", $j('#loginErrorNetmd'));
		//bValid = bValid && checkNull( ConfirmPwd, "Confirm Password", $j('#loginErrorNetmd'));
		ConfirmPwdValid = checkNull( ConfirmPwd, "confirm password", $j('#loginErrorNetmd'));
		NewPwdValid = checkNull( newPwd, "new password", $j('#loginErrorNetmd'));
		NewPwdValid=NewPwdValid&&ConfirmPwdValid; 
		if($j('#confirmPwdNetmd').val()!=''&&$j('#newPwdNetmd').val()!='')
		{
		
		if($j('#confirmPwdNetmd').val()!=$j('#newPwdNetmd').val())
		{
		errorTips("*"+"password mismatch",'',$j('#loginErrorNetmd'));
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