$j("#btnLogout").die('click').live("click",function() {
	ajaxProcessor.setUrl('/youNeverWait/ws/ui/superAdmin/logout');
	var response =ajaxProcessor.get(); 
	if(response.success==true)
	
	//window.location.href=serverPath + "/youNeverWait/ws/ui/superAdmin/startUp";
	location.reload();
});

$j("#btnLogoutNetLims").die('click').live("click",function() {
//var serverPath = "";
	ajaxProcessor.setUrl('/youNeverWait/ws/ui/lab/logout');
	var response =ajaxProcessor.get(); 
	if(response.success==true)
	//window.location.href=serverPath + "/youNeverWait/ws/ui/lab/startUp";
	location.reload();
});


$j("#btnLogoutNetMd").die('click').live("click",function() {
//var serverPath = "";
	 ajaxProcessor.setUrl('/youNeverWait/ws/ui/netMd/logout');
	var response =ajaxProcessor.get(); 
	//alert(JSON.stringify(response));
	if(response.success==true)
	//window.location.href=serverPath + "/youNeverWait/ws/ui/netMd/startUp";
	location.reload();
});


$j("#btnLogoutNetrx").die('click').live("click",function() {
//var serverPath = "";
	ajaxProcessor.setUrl('/youNeverWait/ws/ui/netRx/logout');
	var response =ajaxProcessor.get(); 
	if(response.success==true)
	//window.location.href=serverPath + "/youNeverWait/ws/ui/netRx/startUp";
	location.reload();
});