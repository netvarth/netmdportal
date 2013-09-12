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

function getNetRxBranchAccData(netrxbranchId) {
	var response= getRequestData('/youNeverWait/ws/ui/netRx/viewNetRxBranch/'+ netrxbranchId);
	return response;
}
function getTestData(testId){
	response=getRequestData('/youNeverWait/ws/ui/test/viewTest/'+testId);
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

function checkRowIdDuplication(tableObj, rowId) {
	var status=false;
	if($j(tableObj).dataTable().fnGetData().length>0) {
		var curTable = $j(tableObj + " tr:gt(0)"); //this will not include the header row
		curTable.each(function() {
			var curId=$j(this).attr('id');
			if(rowId==curId) {
				status=true;
				return false;
			}
		});	
	}	
	return status;
}

function fillLayoutToControl(controlObj) {
	$j(controlObj).empty();
	$j(controlObj).append('<option value="None">None</option><option value="General">General</option><option value="GeneralOne">GeneralOne</option>><option value="Layout1">Layout1</option><option value="WaterCultureReport">WaterCultureReport</option><option value="AFB">AFB</option><option value="Stone Analysis">Stone Analysis</option><option value="Osmotic">Osmotic</option><option value="Urine">Urine</option><option value="HistoPath">HistoPath</option><option value="Peripheral">Peripheral</option><option value="GTT">GTT</option><option value="Aminoacidogram">Aminoacidogram</option><option value="Stool Analysis">Stool Analysis</option><option value="CD">CD</option><option value="Haemogram">Haemogram</option><option value="HaemogramESR">HaemogramESR</option><option value="ANA">ANA</option><option value="Culture Report">Culture Report</option><option value="ENA">ENA</option><option value="Semen Layout">Semen Layout</option><option value="PT">PT</option><option value="APPT">APPT</option><option value="DC">DC</option><option value="LipidLayout">LipidLayout</option><option value="LFT">LFT</option>');
}

function fillRemarksValToControl(selectoptins){
	var remarks=getRequestData('/youNeverWait/json/remarksList.json');
	$j(selectoptins).empty();
    $j(remarks).each(function(index,rem){ 
		$j(selectoptins).append('<option value='+rem.value+'>'+rem.value+'</option>');
	});
}

function fillSpecimenToControl(controlObj) {
	$j(controlObj).empty();
	$j(controlObj).append('<option value="select">select specimen</option>');
	var configData=getRequestData('/youNeverWait/ws/ui/specimen/specimenList');
	//alert(JSON.stringify(configData));
	$j(configData.specimenList).each(function(index,specimen){
	$j(controlObj).append('<option value="'+specimen.uid+'">'+specimen.specimenName+'</option>');
	});
}


function fillHeaderDataToControl(controlObj){
	var headerList=[{"name":"OBSERVED VALUE","value":"PatientValue"}, {"name":"REFERENCE RANGE&UNIT","value":"NormalRange"},{"name": "RESULT","value":"Result"}];
	$j(controlObj).empty();
	$j(headerList).each(function (index,item) {
		$j(controlObj).append('<option value="' + item.value+ '">' + item.name+ '</option>');
	});
}

