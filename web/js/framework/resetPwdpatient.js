
	var a = window.location.toString();
	var name = a.substring(a.indexOf("=")+1);
	
	
$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";

$j(document).ready(function() {
	
	$j("#newPwdpatient").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#newPwdpatient").val()=="")
				return false;
			$j("#confirmPwdpatient").focus();
		}		
	});
	$j("#confirmPwdpatient").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#confirmPwdpatient").val()=="")
				return false;
			$j("#resetPwdpatient").trigger('click');
		}		
	});

	$j('#resetPwdpatient').die('click').live("click",function(){
		if(validate()) {
			var  newPwd=$j('#newPwdpatient').val();
			var confirmPwd=$j('#confirmPwdpatient').val();
			var loginData='{'+'"password"'+':"'+newPwd+'"'+','+'"userName"'+':"' + name +'"'+'}';
			var resetResult=postdataToServer("/youNeverWait/ynw/ui/patient/resetPassword", loginData );
			if(resetResult.success==true)
			{
			$j('#newPwdpatient').val("");
			$j('#confirmPwdpatient').val("");
			successTip("Password reset successful",'',$j('#loginErrorpatient'));
			}

			
			
	}
	});
	
	
	
	function validate() {
		var newPwd = $j('#newPwdpatient');
		var ConfirmPwd = $j('#confirmPwdpatient'); 
		var bValid=true,NewPwdValid=true,ConfirmPwdValid=true,PwdMatch=true;
		//bValid = bValid && checkNull( newPwd, "New Password", $j('#loginErrorpatient'));
		//bValid = bValid && checkNull( ConfirmPwd, "Confirm Password", $j('#loginErrorpatient'));
		ConfirmPwdValid = checkNull( ConfirmPwd, "confirm password", $j('#loginErrorpatient'));
		NewPwdValid = checkNull( newPwd, "new password", $j('#loginErrorpatient'));
		NewPwdValid=NewPwdValid&&ConfirmPwdValid; 
		if($j('#confirmPwdpatient').val()!=''&&$j('#newPwdpatient').val()!='')
		{
		
		if($j('#confirmPwdpatient').val()!=$j('#newPwdpatient').val())
		{
		errorTips("*"+"password mismatch",'',$j('#loginErrorpatient'));
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