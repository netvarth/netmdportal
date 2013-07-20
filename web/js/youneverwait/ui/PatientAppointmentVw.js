function PatientAppointmentVw() {
	this.patientresultId=$j("#select").val();
}

PatientAppointmentVw.prototype.getNameListObj = function(patientresultId) {
	return this.patientresultId;
}
PatientAppointmentVw.prototype.viewappointment= function(url,url1,parent) {
	var PatientresId=this.getNameListObj();
		PatientresId=PatientresId.split("_")[0];
	var appointmentview=url+PatientresId;	
	ajaxProcessor.setUrl(appointmentview);
	var tabdata =ajaxProcessor.get();
	
	var pgNetlimsList;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#pastappointment"; // Table showing netlims list
	pgTableContainer = "#pastappointmentTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	var operator="eq";
	
		
	//var response=postdataToServer("/youNeverWait/ws/ui/patient/getPastAppointments", passjson );
	$j(parent).html(new appointment(tabdata).result);
	var filter=getPastAppntFilterlistUrl(PatientresId,operator);
	var netmdTable=setpastAppointmentlistStructure();
			$j('.pastappointment').html(netmdTable.html());
			setCustomDataTable(pgTableName);
			
	var passresultjson=passingJson(filter,(curPage-1),interval);
	pgNetlimsList = fillpastAppointmentTable(passresultjson,pgTableName);
	if(pgNetlimsList.count)
		maxRecords = pgNetlimsList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
			
	
	$j(pgTableContainer +' #next').die('click').click(function() {
	if(curPage!=maxPages && curPage<maxPages) {
	curPage+=1;
	var startValue = interval*(curPage-1);
	 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillpastAppointmentTable(passresultjson,pgTableName);
	setPaginationFields(curPage, maxPages,pgTableContainer);
}
});
$j(pgTableContainer +' #previous').die('click').click(function() {
	if(curPage!=1) {
	curPage-=1;
	var startValue = interval*(curPage-1);
	 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillpastAppointmentTable(passresultjson,pgTableName);
	setPaginationFields(curPage, maxPages,pgTableContainer);
}
});
$j(pgTableContainer +' #first').die('click').click(function() {
if(curPage!=1) {
curPage=1;
startValue = 0;
 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillpastAppointmentTable(passresultjson,pgTableName);

setPaginationFields(curPage, maxPages, pgTableContainer);
}
});
$j(pgTableContainer +' #last').die('click').click(function() {
if(curPage!=maxPages && curPage<maxPages) {
curPage =maxPages;
startValue = (curPage-1)*interval;
 passresultjson=passingJson(filter,startValue,interval);
	pgNetlimsList = fillpastAppointmentTable(passresultjson,pgTableName);
setPaginationFields(curPage, maxPages, pgTableContainer);
}
});	

	
} 


function getPastAppntFilterlistUrl(PatientresId,operator){

	var listJson='[{"name":"' + "patientId" + '", "value":"' + PatientresId + '","operator":"'+operator + '"}]';
	return listJson;
} 

function passingJson(filter,curPage,interval){
var passjson='{"exp":'+ filter +',';
	passjson+='"from":'+ curPage +',';
	passjson+='"count":'+interval+',';
	passjson+='"asc":'+ false+'';
	passjson+='}';
	return passjson;
	}
			
			
function setpastAppointmentlistStructure() {
	//create the table structure for doctor Table
	var tblData = getRequestData('/youNeverWait/json/list/pastAppointmentTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}
	
	function fillpastAppointmentTable(passresultjson,tableObj) {
	
	var pastAppointmentList=postdataToServer("/youNeverWait/ws/ui/patient/getPastAppointments", passresultjson );
	//alert(JSON.stringify(pastAppointmentList));
	 if(pastAppointmentList.pastAppointments) {
		if(pastAppointmentList.pastAppointments.length>0) {	
			$j(tableObj).dataTable().fnClearTable();
			$j(pastAppointmentList.pastAppointments).each(function (index, past) {
				var id=past.globalId;
				var specialisation="";
				if(past.doctorSpecialisation==null){specialisation="";}else{specialisation="("+past.doctorSpecialisation+")";}
				var doctorDetail="Dr."+past.doctorFirstName+specialisation;
				var timeDetail=past.startingTime+" "+past.date;
				var rowData=$j(tableObj).dataTable().fnAddData([doctorDetail,timeDetail]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","pastIdCol Ustyle");
				});	
		}
	}		
	return pastAppointmentList; 
	
	
}