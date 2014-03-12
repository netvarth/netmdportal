$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});

var serverPath = "";

$j(document).ready(function() {
	
	$j("#userName").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#userName").val()=="")
				return false;
			$j("#password").focus();
		}		
	});
	$j("#password").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#password").val()=="")
				return false;
			$j("#btnLogin").trigger('click');
		}		
	});

	$j('#btnLogin').die('click').live("click",function(){
		if(validate()) {
			var  username=$j('#userName').val();
			var password=$j('#password').val();
			var loginData='{'+'"userName"'+':"'+username+'"'+','+'"password"'+':"' + password +'"'+'}';
			$j.ajax({
				type: "POST",
				url: serverPath + "/youNeverWait/ws/ui/superAdminLogin/login",
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
		var userId = $j('#userName');
		var passwd = $j('#password'); 
		var bValid=true;
		bValid = bValid && checkNull( userId, "username", $j('#loginError'));
		bValid = bValid && checkNull( passwd, "password", $j('#loginError'));
		return bValid;
	}
	function checkNull(o,n, errorDiv){
		if(o.val().length=='0'){
			o.addClass("error");
			errorTips( "* " + n + " must not be null" ,o ,errorDiv);
			return false;	
		} 
		else 
			return true;
	}
});