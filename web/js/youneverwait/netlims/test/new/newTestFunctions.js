
function makeDataTable(tableObj) {
	$j(tableObj).dataTable( {
		"sPaginationType": "full_numbers",
		"bFilter":false,
		"bInfo":false,
		"bPaginate":false,
		"bSort": false,
		"bRetrieve ": true,
		"sDom": '<"top"Hip>'
	});
}
function fillfirstRowRptTbl(){
	var testname=$j('#newTest #txtTestName').val();
	testname= testname.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	}); 
	var rowData=$j('#newTest #tbltestReport').dataTable().fnAddData(["INVESTIGATION",testname,'']);
	var row=$j('#newTest #tbltestReport').dataTable().fnSettings().aoData[rowData].nTr;
	$j(row).attr('id',"Investigation");
}

function fillSpecimenToTable(specimenName,specimenVal){
	removeErrors();
    var dupStatus = checkRowIdDuplication('#tblTestSpecimen',specimenVal);
	if(dupStatus==true){
		createError("specimen exists",$j('#selectTestSpecimen'));
		return false;
		}
    var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/youNeverWait/images/remove-btn.png" rel="tooltip" title="delete"></a>';
	var rowData=$j('#tblTestSpecimen').dataTable().fnAddData([specimenName,myData]);
	var row=$j('#tblTestSpecimen').dataTable().fnSettings().aoData[rowData].nTr;
	$j(row).attr('id',specimenVal);	
}

function fillheaderToTable(name,value,setTblVal){
    removeErrors();
    var dupStatus = checkRowIdDuplication('#tbltestReport',value);
	if(dupStatus==true){
		createError("Header exists",$j('#selectTableHeader'));
		return false;
		}
    var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="deleteRptHdr" src="/youNeverWait/images/remove-btn.png" rel="tooltip" title="delete"></a>';
	var rowData=$j('#newTest #tbltestReport').dataTable().fnAddData([name,setTblVal,myData]);
	var row=$j('#newTest #tbltestReport').dataTable().fnSettings().aoData[rowData].nTr;
	$j(row).attr('id',value);	
}	
	
function checkRowIdDuplication(tableObj,rowId) {
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

function createSubmitJson(ReportList) {
	var submitdata;
	var testname= $j('#newTest #txtTestName').val();
	var rate=0;
	var replaceResult="";
	var timeRequired=$j('#newTest #txtTestTimeRq').val();
	var genericname=$j('#newTest #txtTestGenericName').val();	
	var abbreviation=$j('#newTest #txtTestAbbr').val();	
	var normalrange=tab2space(nl2br($j('#txtareaNormalRange').val()));
	var remark=tab2space(nl2br($j('#newTest #txtareaTestRemarks').val()));
	var specimen= getSpecimenTableData();
	var specimenEntryStatus = false;
	if($j('#newTest #specEntryStatus').is(':checked'))
		specimenEntryStatus = true;
	var machineEntryStatus=false;
	if($j('#newTest #machEntryStatus').is(':checked'))
		machineEntryStatus = true;
	var result=setJsonforReportModel(ReportList);
	result=JSON.stringify(setJsonforReportModel(ReportList));
    submitdata = '{' +'"name"' + ':"'+ testname+'",';
	submitdata+='"standardRate"' +':' + rate +',';
	submitdata+='"minTimeRequired"' + ':"' + timeRequired + '",';
	submitdata+='"genericName"' + ':"' + genericname + '",';
	submitdata+= '"abbreviation"' + ':"' + abbreviation +'",';
	submitdata+='"normalRange"' + ':"' + normalrange + '",';
	submitdata+='"remarks"' + ':"' + remark.trim() + '",';
	submitdata+='"uploadStatus"' + ':' +'false'+ ',';
	submitdata+='"result"'+':' + result+ ',';
	submitdata+='"specimenEntryStatus"'+':'+ specimenEntryStatus +',';
	submitdata+='"machineEntryStatus"'+':'+ machineEntryStatus +',';
	submitdata+='"testSpecimen"'+':'+ specimen + '}';
	return submitdata;
}

function getSpecimenTableData() {
	var specimentableArray=[];
	var qty=0;
	/*iterating table to  get the id,stdrateand discount*/
	var tableLng=$j('#newTest #tblTestSpecimen').dataTable().fnGetNodes().length;
		if(tableLng!='0'){
	    var specimenTable = $j("#newTest #tblTestSpecimen  tr:gt(0)"); //this will not include the header row
		specimenTable.each(function() {
		var rowobj=$j(this).attr('id');
		var row = '#' + rowobj;
		var specimenName=$j(row).children("td:nth-child(1)").text();
		specimentableArray.push('{'+'"specimenUid"' +':'+rowobj+''+','+'"specimenName"'+':"'+specimenName+'"'+','+'"quantity"'+':'+qty+'}');
     });
	 }
	 var specimen='['+specimentableArray+']';
	return specimen;
}
function createJsonforPreview(){
var tblarray=[];
	/*iterating table to  get the id,stdrateand discount*/
	var reportTable = $j("#newTest #tbltestReport  tr:gt(0)"); //this will not include the header row
		reportTable.each(function() {
		var rowobj=$j(this).attr('id');
		var row = '#' + rowobj;
		var HeaderName=$j(row).children("td:nth-child(1)").text();
		var headervalue=$j(row).children("td:nth-child(2)").text();
		var value=nl2br(headervalue);
		tblarray.push('{'+'"name"' +':"'+HeaderName+'"'+','+'"headervalue"'+':"'+value+'"'+'}');
     });
	 var table='{"colheaders":['+ tblarray + ']}';
	return table;
}

function createReportTblJson(){
var tblarray=[];
var selectedLayout=$j('#newTest #selectTestLayout').val();

	/*iterating table to  get the id,stdrateand discount*/
	var reportTable = $j("#newTest #tbltestReport  tr:gt(0)"); //this will not include the header row
		reportTable.each(function() {
		var rowobj=$j(this).attr('id');
		var row = '#' + rowobj;
		var displayType="text";
		if(rowobj!="Investigation"){
		if(selectedLayout=="GeneralOne" && rowobj=="NormalRange")
		   displayType="textarea";
		if(selectedLayout=="General" && rowobj=="NormalRange")
			displayType="";
		var unit = "";
		if((selectedLayout=="General" || selectedLayout=="GeneralOne") && (rowobj=="PatientValue" || rowobj=='Result'))
			unit = $j('#viewTestForm #txtViewNormalUnit').val();
		var HeaderName=$j(row).children("td:nth-child(1)").text();
		var headervalue=nl2br($j(row).children("td:nth-child(2)").text());
		var unit = $j('#txtViewNormalUnit').text();
		var value=nl2br(headervalue);
		tblarray.push({"headerLabel":HeaderName,"headervalue":rowobj,"values":[{"displayType":displayType,"value":headervalue,"unit":unit}]});
         }                                                           
     });
	return tblarray;
}
function setJsonforReportModel(ReportList){
	var createJson={};
	var tstNme=$j('#txtTestName').val();
	var layout=$j('#newTest #selectTestLayout').val();
	if(layout!="None"){
		var unit=$j('#newTest #txtNormalUnit').val();
		var layoutName=$j('#newTest #selectTestLayout option:selected').text();
		 if(layout=="General"|| layout=="GeneralOne"){
		var reportTblStructure=createReportTblJson();
		createJson={
				"testLayout":layoutName,
				"testName":tstNme,
				"testUnit":unit,
				"analysis":[
					{
						"analysisType": "",
						"resultContent":[
							{
								"title": "",
								"resultParams":[
									{
										"values":reportTblStructure}]}]}]
			};
			}
			else{
				$j(ReportList.test).each(function(index,testvalue){
					if(testvalue.Report==layout){
					  $j(testvalue.Result).each(function(index,resultval){
					  resultval["testName"]=tstNme;
					});
					createJson=testvalue.Result;
					}
				});
			}
		}
		
	return	createJson; 
}

function submitTestInfo(ReportList){
	/* making json data of new order page  section wise*/
	var resultJson=createSubmitJson(ReportList);
	var response = postdataToServer("/youNeverWait/ws/ui/test/createTest",resultJson );	
	return response;
}
function resetNewtestForm() {
	removeErrors();
	$j("#newTest input[type=text], textarea").val("");
	$j("#newTest #tbltestReport").dataTable().fnClearTable();
	$j("#newTest #tblTestSpecimen").dataTable().fnClearTable();
	$j('#newTest #enterHdrDiv').hide();
	$j('#newTest #testReportSample ,#newTest #previewDiv').hide();
	$j('#newTest #selectTestLayout option[value="None"]').attr('selected','selected');
	$j('#newTest #selectTestRemarks option[value="select"]').attr('selected','selected');
	$j("#newTest #txtTestName ").focus();
}