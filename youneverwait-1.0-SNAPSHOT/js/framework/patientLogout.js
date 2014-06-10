$j("#btnLogout").die('click').live("click",function() {
	var response = getRequestData('/youNeverWait/ws/ui/patient/logout');
	if(response.success==true){
	//var serverPath="";
	configData ={};
	defaultData ={};
	gmEnumList={};
	errorData={};
	//window.location.href=serverPath + "/youNeverWait/ws/ui/patient/startUp";
	location.reload();
	}
});

