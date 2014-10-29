var ajaxProcessor=new ServerUrlProcessor();
validator = new Validator();
constants = new Constants();
query = new Query();
var errorHandler = new ErrorHandler();
$(document).ready(function() {
	var patientLogin = new PatientLogin();
	patientLogin.init();
});	
function Constants() {
	this.FORGOTPASSWORDURL = "/youNeverWait/html/patientForgotPassword.html";
	this.LOGINURL = "/youNeverWait/ynw/auth/patientLogin";
	this.FIELDREQUIRED = "Field required";
	this.INVALIDUSERNAMEPASSWORD = 	"Invalid UserId/Password";
}
function Query(){
	this.login = function(loginInfo) {
		ajaxProcessor.setUrl(constants.LOGINURL);
		return ajaxProcessor.post(loginInfo);
	}
}
function PatientLogin(){
	this.userName = "#loginForm #userName";
	this.password = "#loginForm #password";
	this.loginButton = "#loginForm #btnLogin";
	this.forgotPassword = "#loginForm #forgotpwdpatnt";
	this.errorInfo = "#loginForm #errorInfo";

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
		error.setErrorMsgs(errorMsgs);
		return error;
	}
}