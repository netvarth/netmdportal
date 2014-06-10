function PatientClinicsView() {
	this.patientresultId=$j("#select").val();
}

/* PatientClinicsView.prototype.getNameListObj = function(patientresultId) {
	return this.patientresultId;
}
 */

PatientClinicsView.prototype.viewclinics= function(parent) {
	clinicsInfolist(parent);
	
}
 
 var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 5;// Interval between pages
	var curPage = 1;//Current selected page
	var currentno="";	
	
function clinicsInfolist(parent){
		var pgAgentList = clinicsInfo((curPage-1),interval);
	//alert(JSON.stringify(pgAgentList.count));
	if(pgAgentList.count)
		maxRecords = pgAgentList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	//setPaginationFields(curPage, maxPages, pgTableContainer);
	var resData = new clinics(pgAgentList);
		$j(parent).html(resData.result);
	currentno=$j("#prevnext").val(curPage);
		
		
	}	
	
	function clinicsInfo(curPage,interval){
	var name=$j("#select :selected").text();
	name=name.split(" ")[0];
	var id_email=$j("#select").val();
	var email=id_email.split("_")[1];
	var operator="eq";
	var filter=getClinicsFilterlistUrl(name,email,operator);
	var passjson='{"exp":'+ filter +',';
	passjson+='"from":'+ curPage +',';
	passjson+='"count":'+interval+',';
	passjson+='"asc":'+ false+'';
	passjson+='}';
	var response=postdataToServer("/youNeverWait/ws/ui/patient/netmdBranchList", passjson );
	//alert(JSON.stringify(response));
	return response;
}

	
function getClinicsFilterlistUrl(name,email,operator){

	var listJson='[{"name":"' + "patientFirstName" + '", "value":"' + name + '","operator":"'+operator + '"},{"name":"' + "patientEmail"+ '", "value":"' + email + '","operator":"'+operator + '"}]';
	return listJson;
} 
