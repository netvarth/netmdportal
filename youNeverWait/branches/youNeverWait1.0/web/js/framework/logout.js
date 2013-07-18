$j("#btnLogout").die('click').live("click",function() {
	var response = getRequestData('/youNeverWait/ws/ui/superAdmin/logout');
	if(response.success==true)
	//window.location.href = "/youNeverWait/html/superadminLoginPage.html";
	configData ={};
	defaultData ={};
	gmEnumList={};
	errorData={};
	//window.location.href=serverPath + "/youNeverWait/ws/ui/superAdmin/startUp";
	location.reload();
});

$j("#btnLogoutNetLims").die('click').live("click",function() {
//var serverPath = "";
	var response = getRequestData('/youNeverWait/ws/ui/lab/logout');
	if(response.success==true)
	//window.location.href=serverPath + "/youNeverWait/ws/ui/lab/startUp";
	location.reload();
});


$j("#btnLogoutNetMd").die('click').live("click",function() {
//var serverPath = "";
	var response = getRequestData('/youNeverWait/ws/ui/netMd/logout');
	//alert(JSON.stringify(response));
	if(response.success==true)
	//window.location.href=serverPath + "/youNeverWait/ws/ui/netMd/startUp";
	location.reload();
});


$j("#btnLogoutNetrx").die('click').live("click",function() {
//var serverPath = "";
	var response = getRequestData('/youNeverWait/ws/ui/netRx/logout');
	if(response.success==true)
	//window.location.href=serverPath + "/youNeverWait/ws/ui/netRx/startUp";
	location.reload();
});