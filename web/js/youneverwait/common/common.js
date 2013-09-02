function createGlobalToolBar () {
	//var response = getRequestData('/weblims/ws/ui/admin/pageControles/' + constants_globalToolBarFileName);
	var response= getRequestData("/youNeverWait/json/toolbars/globalToolbar.json");
	var globalTB = new GlobalToolBar(response);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
function createLeftpaneToolBar() {
	var response= getRequestData("/youNeverWait/json/toolbars/leftPaneToolBar.json");
	var leftpaneTB = new leftpaneToolBar(response.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}
function createGlobalToolBarNetmd () {
	//var response = getRequestData('/weblims/ws/ui/admin/pageControles/' + constants_globalToolBarFileName);
	var response= getRequestData("/youNeverWait/json/toolbars/netMdGlobalTool Bar.json");
	var globalTB = new GlobalToolBar(response);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
function createLeftpaneToolBarNetrx() {
	var response= getRequestData("/youNeverWait/json/toolbars/netRxLeftPaneToolBar.json");
	var leftpaneTB = new leftpaneToolBar(response.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}

function createGlobalToolBarNetrx () {
	//var response = getRequestData('/weblims/ws/ui/admin/pageControles/' + constants_globalToolBarFileName);
	var response= getRequestData("/youNeverWait/json/toolbars/netRxGlobalToolBar.json");
	var globalTB = new GlobalToolBar(response);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
function createLeftpaneToolBarNetmd() {
	var response= getRequestData("/youNeverWait/json/toolbars/netMdLeftPaneToolBar.json");
	var leftpaneTB = new leftpaneToolBar(response.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}

function createGlobalToolBarNetlims() {
	//var response = getRequestData('/weblims/ws/ui/admin/pageControles/' + constants_globalToolBarFileName);
	var response= getRequestData("/youNeverWait/json/toolbars/netlimsGlobalToolbar.json");
	var globalTB = new GlobalToolBar(response);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
function createLeftpaneToolBarNetlims() {
	var response= getRequestData("/youNeverWait/json/toolbars/netlimsLeftPaneToolbar.json");
	var leftpaneTB = new leftpaneToolBar(response.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}

function getFilterlistUrl(filterExp,startindex,interval){
	var listJson='{"exp":[' + filterExp + '], "from":' + startindex + ',"count":'+interval + ',"asc":false}';
	return listJson;
}

function getNetLimsData(netlimsId) {
	var response= getRequestData('/youNeverWait/ws/ui/superAdmin/viewLab/'+ netlimsId);
	return response;
}
function getNetMdData(netmdId) {
	var response= getRequestData('/youNeverWait/ws/ui/superAdmin/viewNetMd/'+ netmdId);
	return response;
}
function getNetRxData(netrxId) {
	var response= getRequestData('/youNeverWait/ws/ui/superAdmin/viewNetRx/'+ netrxId);
	return response;
}
function getBranchData(branchId) {
	var response= getRequestData('/youNeverWait/ws/ui/superAdmin/viewBranch/'+ branchId);
	return response;
}
function getBranchNetlimsAccData(branchId) {
	var response= getRequestData('/youNeverWait/ws/ui/lab/viewBranch/'+ branchId);
	return response;
}
function getNetMdBranchData(netmdbranchId) {
	var response= getRequestData('/youNeverWait/ws/ui/superAdmin/viewNetMdBranch/'+ netmdbranchId);
	return response;
}

function getNetRxBranchData(netmdbranchId) {
	var response= getRequestData('/youNeverWait/ws/ui/superAdmin/viewNetRxBranch/'+ netmdbranchId);
	return response;
}

function getNetMdBranchAccData(netmdbranchId) {
	var response= getRequestData('/youNeverWait/ws/ui/netMd/viewNetMdBranch/'+ netmdbranchId);
	return response;
}
 
function getErrorName(error) {
	var errorcode = error.errCode;
	var errorName;
	$j(errorData.error).each(function(index,errordet){  	
		if(errorcode==errordet.errCode) {
			errorName=errordet.errMsg;
			$j(error.params).each(function(indexerror, err) {
				var toReplace='{' + err.parameterName + '}';
				var valuetoReplace = " " + err.value + " ";
				errorName=errorName.replace(toReplace,valuetoReplace);
			});
			return false;
		}
	});
	return errorName;
}
function getErrorData() {
	//get the config data	
	var errordata = getRequestData('/youNeverWait/ws/ui/auth/getErrorCodes');
	return errordata;
}

function clearNilFields(formid){
    var id='#'+formid;
	$j(id+'input[type=text],textarea ').each(function() {
		// Does value contain Nil?
		if ($j(this).val().indexOf("Nil") != -1)
			$j(this).val("");
		return;
    });
}

//function for remove the duplication
function filterExpression(expr,refName) {
	var result=[];
	var tempexp = '[' + expr + ']';
	tempexp = $j.parseJSON(tempexp);
	$j(tempexp).each(function(index,data){
		if(data.name==refName)
			tempexp.splice(index,1);
		else
			result.push(JSON.stringify(data));
	});
	return result;
}

function setReportFilterValues(referalName) {
//Created Date
	if((referalName=='actionDate')||(referalName=='loginTime')||(referalName=='logoutTime') ){
		$j("#txt"+referalName).datepicker();
	}

}