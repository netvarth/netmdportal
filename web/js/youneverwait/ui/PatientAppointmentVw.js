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
	var pastappointmentview=url1+PatientresId;	
	ajaxProcessor.setUrl(pastappointmentview);
	var tabpastdata =ajaxProcessor.get();
	$j(parent).html(new appointment(tabdata,tabpastdata).result);
		var tableObj="#pastappointment";
	var netmdTable=setpastAppointmentlistStructure();
			$j('.pastappointment').html(netmdTable.html());
			setCustomDataTable(tableObj);
			fillpastAppointmentTable(tabpastdata,tableObj);
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
	
	function fillpastAppointmentTable(tabpastdata,tableObj) {
	
	//var netmdListJson=postdataToServer("/youNeverWait/ws/ui/superAdmin/netmdList", netmdListJson );
	if(tabpastdata.pastAppointments) {
		if(tabpastdata.pastAppointments.length>0) {	
			$j(tableObj).dataTable().fnClearTable();
			$j(tabpastdata.pastAppointments).each(function (index, past) {
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
	//return netmdListJson;
	
	
}