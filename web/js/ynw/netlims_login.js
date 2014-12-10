var ajaxProcessor=new ServerUrlProcessor();
validator = new Validator();
constants = new Constants();
notifier = new Notifier();
query = new Query();
var errorHandler = new ErrorHandler();
$(document).ready(function() {
	var netlimsLogin = new NetlimsLogin();
	netlimsLogin.init();
});	
function Constants() {
	this.FORGOTPASSWORDURL = "/youNeverWait/html/netlimsForgotPassword.html";
	this.LOGINURL = "/youNeverWait/netlims/auth/netlimsLogin";
	this.FIELDREQUIRED = "Field required";
	this.INVALIDUSERNAMEPASSWORD = 	"Invalid UserId/Password";
	this.RESETPASSWORDURL="/youNeverWait/netlims/ui/lab/facility/resetPassword";
	this.PASSWORDRESETSUCCESS="Password reset successfully";
	this.INVALIDPASSWORDLENGTH="Password length should be greater than 6";
	this.FACILITYTYPE="Facility";
}
function Query(){
	this.login = function(loginInfo) {
		ajaxProcessor.setUrl(constants.LOGINURL);
		return ajaxProcessor.post(loginInfo);
	}
	this.resetPassword =function(loginInfo){
		ajaxProcessor.setUrl(constants.RESETPASSWORDURL);
		return ajaxProcessor.post(loginInfo);
	}
}
function NetlimsLogin(){
	this.loginForm = "#netlimsLoginForm";
	this.userName = this.loginForm + " #userName";
	this.password = this.loginForm + " #password";
	this.userType = this.loginForm + " #userType";
	this.loginButton = "#btnLogin";
	this.forgotPassword = "#forgotPassword";
	this.resetButton="#resetPassword";
	this.errorInfo = this.loginForm + " #errorInfo";

	this.init=function() {
		$(this.errorInfo).hide();
		this.bindEvents();
	}	
	this.bindEvents = function() {
		var self=this;
		errorHandler.removeBorder_Error(this.userName, this.errorInfo);
		errorHandler.removeBorder_Error(this.password, this.errorInfo);
		$(self.userName).bind("keypress", function (e) {
			if (e.keyCode == 13) {
				if($(self.userName).val()=="")
					return false;
				$(self.password).focus();
			}		
		});
		$(self.password).bind("keypress", function (e) {
			if (e.keyCode == 13) {
				if($(self.password).val()=="")
					return false;
				$(self.loginButton).trigger('click');
			}		
		});
		$(self.forgotPassword).die('click').live("click",function(){
			window.location.href = constants.FORGOTPASSWORDURL;
		});
		$(self.loginButton).die('click').live("click",function(){
			var login = new Login();
			login.setUserName($(self.userName).val());
			login.setPassword($(self.password).val());	
			login.setUserType($(self.userType).val());
			var error = self.validate(login);	
			if(error.errorStatus==false){	
				var response = query.login(login);
				if(response.success==true)
					location.reload();
				else {
					errorHandler.addErrorMessage(self.errorInfo, constants.INVALIDUSERNAMEPASSWORD);
					return false;
				}
			} else{
				self.createError(error);
				return false;
			}
		});
		$(self.resetButton).die('click').live("click",function(){
			var url = window.location.toString();
			var name = url.substring(url.indexOf("=")+1);
			var login = new Login();
			login.setUserName(name);
			login.setPassword($(self.password).val());	
			login.setUserType("Facility");
			var error = self.validate(login);		
			if(error.errorStatus==false){	
				var response = query.resetPassword(login);
				if(response.success==true){
					window.open('http://www.netlims.in',"_self");
					return false;
				}else {
					errorHandler.addErrorMessage(self.errorInfo, constants.INVALIDUSERNAMEPASSWORD);
					return false;
				}
			} else{
				self.createError(error);
				return false;
			}
		});
	}
	this.createError = function(error) {
		var self=this;
		errorHandler.addErrorMessage(self.errorInfo, constants.INVALIDUSERNAMEPASSWORD);
		$(error.errorMsgs).each(function(index, errormsg) {
			errorHandler.drawBorder_Error($(errormsg.errorField));
		});
	}
	this.validate = function(login) {
		var error = new ErrorDTO();
		var errorMsgs = [];
		if(validator.isEmpty(login.getUserName())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.userName,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		}
		if(validator.isEmpty(login.getPassword())) {
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.password,constants.FIELDREQUIRED);
			errorMsgs.push(errorMessage);
		} 
		if(!validator.validatePassword(login.getPassword())){
			error.setErrorStatus(true);
			var errorMessage = new ErrorMessageDTO(this.password,constants.INVALIDPASSWORDLENGTH);
			errorMsgs.push(errorMessage);
		}
		error.setErrorMsgs(errorMsgs);
		return error;
	}
}