$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";
$j.getScript("/youNeverWait/js/framework/RequestHandler.js").done(function(script, textStatus) {
}) 

$j(document).ready(function() {

	$j("#userNameNetrx").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#userNameNetrx").val()=="")
				return false;
			$j("#passwordNetrx").focus();
		}		
	});
	
	$j("#passwordNetrx").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#passwordNetrx").val()=="")
				return false;
			$j("#btnLoginNetrx").trigger('click');
		}		
	});
	
		
	$j('#forgotpwdNetrx').die('click').live("click",function(){
	//window.location.href = "/youNeverWait/html/netmdForgotPassword.html";
	});

	$j('#btnLoginNetrx').die('click').live("click",function(){
		var captcha=$j('#capchavalueNetrx').val();
		if(validate()){
					var  username=$j('#userNameNetrx').val();
					var password=$j('#passwordNetrx').val();
					var loginData='{'+'"userName"'+':"'+username+'"'+','+'"password"'+':"' + password +'"'+'}';
					$j.ajax({
						type: "POST",
						url: serverPath + "/youNeverWait/ws/ui/auth/netrxLogin",
						data: loginData,
						contentType: "application/json",
						dataType: "json",
						async: false,
						success:loginSuccess,
						error: loginError						
					});	
				
		}
	});
	
	function loginSuccess(data) {
		if(data.success==true)
		//window.location.href = "/youNeverWait/html/netRxIndex.html";
		location.reload();
		else
		errorTips("Incorrect username/password",'',$j('#loginError'));	
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
	function loginError() {
		errorTips("Incorrect username/password",'',$j('#loginError'));

	}
	
	function validate() {
		var userId = $j('#userNameNetrx');
		var passwd = $j('#passwordNetrx'); 
		var bValid=true;
		bValid = bValid && checkNull( userId,"Please enter the username", $j('#loginError'));
		bValid = bValid && checkNull( passwd, "Please enter the password ", $j('#loginError'));
		return bValid;
	}
	function checkNull(o,n, errorDiv){
		if(o.val().length=='0'){
			o.addClass("error");
			errorTips( "* " + n ,o ,errorDiv);
			return false;	
		} 
		else 
			return true;
	}

	
});