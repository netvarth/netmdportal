
function viewTest(testId,specimenTableObj,repTableObj) {
	$j('#pageTitle').html(constants_testDetails);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	var ptb = getPTBTestGeneral();
	$j('#pageToolBar-Container').html(ptb);
	var layout = createTestViewLayout();
	$j('#tabs-1').html(layout);	
	makeTestDataTable(specimenTableObj);
	makeTestDataTable(repTableObj);
	fillHeaderDataToControl('#viewSelectTableHeader');
	fillLayoutToControl('#viewSelectTestLayout');
	fillSpecimenToControl('#viewSelectTestSpecimen');
	fillRemarksValToControl('#viewSelectTestRemarks');
	viewTestInfo(testId,specimenTableObj,repTableObj);
}
function getPTBTestGeneral() {
	var ptbdata=getRequestData('/weblims/json/toolbars/viewGeneralToolbar.json');
	var ptbContainer = $j('<div id="testGeneralPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	return ptbContainer;
}
function createTestViewLayout() {
	var response = getRequestData('/weblims/json/testview.json');
	var contentForm = new form(response);
	return contentForm.result;
}
function makeTestDataTable(tableObj) {
	$j(tableObj).dataTable( {
		"bFilter":false,
		"bInfo":false,
		"bPaginate":false,
		"bSort": false,
		"bAutoWidth":false
	});
}
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
function viewTestInfo(testId,specimenTableObj,repTableObj) {
	
	$j('#pageTitle').html(constants_testDetails);
	$j('#viewTestForm #btnViewTestReportPreview').hide();
	$j('#viewTestForm input[type=text],textarea').attr('readonly','readonly');
	
	var testInfo = getTestData(testId);
	uploadstatus=testInfo.uploadStatus;
    $j("#viewTestForm #lblViewTestId label").text(testInfo.uid);
	$j("#viewTestForm #txtViewTestName ").val(testInfo.name);
	if(testInfo.abbreviation!=null)
		$j("#viewTestForm #txtViewTestAbbr").val(testInfo.abbreviation);
	else
		$j("#viewTestForm #txtViewTestAbbr ").val('Nil');
	if(testInfo.genericName)
		$j("#viewTestForm #txtViewTestGenericName").val(testInfo.genericName);
	else
		$j("#viewTestForm #txtViewTestGenericName").val('Nil');
	if(testInfo.minTimeRequirednormalRangenormalRange)
		$j("#viewTestForm #txtViewTestTimeRq ").val(testInfo.minTimeRequired);
	else
		$j("#viewTestForm #txtViewTestTimeRq ").val('Nil');
	if(testInfo.standardRate)
		$j('#viewTestForm #txtViewTestRate ').val(testInfo.standardRate.toFixed(2));
	else
	   $j("#viewTestForm #txtViewTestRate").val('Nil');
	if(testInfo.normalRange)
		$j('#viewTestForm #txtareaViewNormalRange').val(space2tab(br2nl(testInfo.normalRange)));
	else
	   $j("#viewTestForm #txtareaViewNormalRange").val('Nil'); 
	if(testInfo.remarks)
		$j('#viewTestForm #txtViewareaTestRemarks').val(space2tab(br2nl(testInfo.remarks)));
	else
	   $j("#viewTestForm #txtViewareaTestRemarks").val('Nil'); 
	if(testInfo.specimenentryStatus==true)
		$j("#viewTestForm #specimenEntryDispValue label").text('On Order');
	else
		$j("#viewTestForm #specimenEntryDisplayDiv").hide();
	if(testInfo.machineEntryStatus==true)
		$j("#viewTestForm #machineEntryDispValue label").text('readable');
	else
		$j("#viewTestForm #machineEntryDisplayDiv").hide();

	fillspecimenTable(testInfo,specimenTableObj);
	fillReportLayout(testInfo,repTableObj);
}

function fillspecimenTable(testInfo,specimenTableObj){
	$j(specimenTableObj).dataTable().fnClearTable();
	$j(testInfo.testSpecimens).each(function(index,specimen){
		var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="deleteSpecimen" src="/weblims/images/remove-btn.png"></a>';
		var rowData=$j(specimenTableObj).dataTable().fnAddData([specimen.specimenName,myData]);
		var row=$j(specimenTableObj).dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).attr('id',specimen.specimenUid);
	});
	$j(specimenTableObj).dataTable().fnSetColumnVis( 1, false );
}

function fillReportLayout(testInfo,tableObj){
    var testname=testInfo.name;
	if(testInfo.result=="{}"){
		$j('#viewTestForm #lblViewLayout label').text("None");
		$j('#viewTestForm #viewTbltestReport').hide();
	}
	var resultInfo=$j.parseJSON(testInfo.result);
	if(resultInfo.testUnit) 
         $j("#viewTestForm #txtViewNormalUnit").val(resultInfo.testUnit); 
	else
        $j("#viewTestForm #txtViewNormalUnit").val("Nil"); 
	$j('#viewTestForm #lblViewLayout label').text(resultInfo.testLayout.trim());
	
	if(resultInfo.testLayout=="General" || resultInfo.testLayout=="GeneralOne"){
	    $j(tableObj).dataTable().fnClearTable();
		//Add Investigation and Test Name to the Table
		var rowData=$j(tableObj).dataTable().fnAddData(["INVESTIGATION",testname,'']);
		var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).attr('id',"Investigation");
		$j(resultInfo.analysis).each(function(analysisIndex,analysis){
			$j(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				$j(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					$j(resultparam.values).each(function(valueindex,header){
						var tbldata=header.headerLabel;
						var myData="";
						var rowId="";
						if(header.headervalue)
							rowId=header.headervalue;
						var tblvalue;
						if(header.values)
							$j(header.values).each(function(valIndex,headervalue){
								if(headervalue.value)
									tblvalue=br2nl(headervalue.value);
								else
									tblvalue="";	
							});
						if(tbldata!="INVESTIGATION")
							myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="deleteViewHdr" src="/weblims/images/remove-btn.png"></a>';
						var rowData=$j(tableObj).dataTable().fnAddData([tbldata,tblvalue,myData]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',rowId);
					});
				});
			});
		});
		$j(tableObj).dataTable().fnSetColumnVis( 2, false );
		$j('#viewTestForm #viewTestReportSample').attr('style','display:""');
	}
}

function fillViewSpecimenToTable(specimenName,specimenVal,tableObj){
	removeErrors();
    var dupStatus = checkRowIdDuplication(tableObj,specimenVal);
	if(dupStatus==true){
		createError(constants_specimenExists,$j('#viewSelectTestSpecimen'));
		return false;
	}
    var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="deleteSpecimen" src="/weblims/images/remove-btn.png" rel="tooltip" title="delete"></a>';
	var rowData=$j(tableObj).dataTable().fnAddData([specimenName,myData]);
	var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
	$j(row).attr('id',specimenVal);	
}
//function for remove the duplication
function filterExpression_specId(expr,id) {
	var result=[];
	var tempexp = '[' + expr + ']';
	tempexp = $j.parseJSON(tempexp);
	$j(tempexp).each(function(index,data){
		if(data.specimenUid==id)
			tempexp.splice(index,1);
		else
			result.push(JSON.stringify(data));
	});
	return result;
}

function resultJsonTest(specimen_Id, actionName, testInformation,TestSpecimenData){
	var status = checkTestSpecimenExistsInJson(testInformation,specimen_Id);
	var action=actionName;
	var row="#"+specimen_Id;
    var rowId=$j(row);
	var qty=0;
    var specimenName=rowId.closest('tr').children('td:nth-child(1)').text();
	TestSpecimenData = filterExpression_specId(TestSpecimenData,specimen_Id);
	if(actionName=='Delete'){
		if(status==true) 
			TestSpecimenData.push('{'+'"specimenUid"'+ ':"'+specimen_Id  +'"' + ',' + '"actionName":"'+actionName+'"'+','+'"specimenName"'+':"'+specimenName+'"'+','+'"quantity"'+':'+qty+'}');
		var currentRow= rowId.closest('tr');
		$j('#viewTestForm #tblViewTestSpecimen').dataTable().fnDeleteRow(currentRow[0]);
	}else {
		if(status==true)
			TestSpecimenData.push('{'+'"specimenUid"'+ ':"'+specimen_Id  +'"' + ',' + '"actionName":"Update"'+','+'"specimenName"'+':"'+specimenName+'"'+','+'"quantity"'+':'+qty+'}');
		else 
			TestSpecimenData.push('{'+'"specimenUid"'+ ':"'+specimen_Id  +'"' + ',' + '"actionName":"Add"'+',' +'"specimenName"'+':"'+specimenName+'"'+','+'"quantity"'+':'+qty+'}');
	}

	return TestSpecimenData;
}

function checkTestSpecimenExistsInJson(testInformation, specimen_Id) {
	var status = false;
	$j(testInformation.testSpecimens).each(function (index,specimen) {
		if(specimen.specimenUid == specimen_Id){
			status=true;
			return false;
		}	  
	});
	return status;
}

/* iterating table to generate preview*/
function createViewJsonforPreview(){
	var tblarray=[];
	/*iterating table to  get the id,stdrate and discount*/
	var reportTable = $j("#viewTbltestReport  tr:gt(0)"); //this will not include the header row
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

function createUpdateJson(TestSpecimenFilteredData,ReportList,uploadstatus) {
	var submitdata;
	TestSpecimenFilteredData='['+TestSpecimenFilteredData+']';
	var testname= $j('#txtViewTestName').val();
	var rate=parseFloat($j('#txtViewTestRate').val(),10) || 0;
	if(rate=="")
	 rate=0;
	var timeRequired=$j('#txtViewTestTimeRq').val();
	var testid=$j('#lblViewTestId label').text();
	var genericname=$j('#txtViewTestGenericName').val();	
	var abbreviation=$j('#txtViewTestAbbr').val();	
	var normalrange=tab2space(nl2br($j('#txtareaViewNormalRange').val()));
	var remark=tab2space(nl2br($j('#txtViewareaTestRemarks').val()));
	var result=JSON.stringify(setViewJsonforReportModel(ReportList));
	var specimenEntryStatus = false;
	if($j('#viewTestForm #specEntryStatus').is(':checked'))
		specimenEntryStatus = true;
	var machineEntryStatus = false;
	if($j('#viewTestForm #machEntryStatus').is(':checked'))
		machineEntryStatus = true;
    submitdata = '{' +'"name"' + ':"'+ testname+'",';
	submitdata+='"uid"' +':"' + testid +'",';
	submitdata+='"standardRate"' +':' + rate +',';
	submitdata+='"minTimeRequired"' + ':"' + timeRequired + '",';
	submitdata+='"genericName"' + ':"' + genericname + '",';
	submitdata+= '"abbreviation"' + ':"' + abbreviation +'",';
	submitdata+='"normalRange"' + ':"' + normalrange + '",';
	submitdata+='"remarks"' + ':"' + remark.trim() + '",';
	submitdata+='"uploadStatus"' + ':' +uploadstatus+ ',';
	submitdata+='"result"'+':' + result+ ',';
	submitdata+='"specimenEntryStatus"'+':'+specimenEntryStatus+',';
	submitdata+='"machineEntryStatus"'+':'+machineEntryStatus+',';
	submitdata+='"testSpecimen"'+':'+ TestSpecimenFilteredData + '}';
	return submitdata;
}

function setViewJsonforReportModel(ReportList){
	var tstNme=$j('#txtViewTestName').val();
	var createJson={};
	var layout=$j('#viewTestForm #viewSelectTestLayout').val();
	if(layout!="None"){
	var unit=$j('#viewTestForm #txtViewNormalUnit').val();
	var layoutName=$j('#viewTestForm #viewSelectTestLayout option:selected').text();
	if(layout=="General"|| layout=="GeneralOne"){
	var reportTblStructure=createViewReportTblJson();
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
function updateTestInfo(TestSpecimenFilteredData,ReportList,uploadstatus){
	/* making json data of new order page  section wise*/
	var resultJson=createUpdateJson(TestSpecimenFilteredData,ReportList,uploadstatus);
	var response = postdataToServer("/weblims/ws/ui/test/updateTest",resultJson );	
	return response;
}

function createViewReportTblJson(){
var tblarray=[];
	/*iterating table to  get the id,stdrateand discount*/
	var reportTable = $j("#viewTbltestReport  tr:gt(0)");//this will not include 
	var layoutSelected=$j('#viewTestForm #viewSelectTestLayout').val();	
		reportTable.each(function() {
		var rowobj=$j(this).attr('id');
		var row = '#' + rowobj;
		var displayType="text";
		if(rowobj!="Investigation"){
		if(layoutSelected=="GeneralOne" && rowobj=="NormalRange")
		   displayType="textarea";
		if(layoutSelected=="General" && rowobj=="NormalRange")
			displayType="";
		var unit = "";
		if((layoutSelected=="General" || layoutSelected=="GeneralOne") && rowobj=="PatientValue" || rowobj=='Result')
			unit = $j('#viewTestForm #txtViewNormalUnit').val();
		var HeaderName=$j(row).children("td:nth-child(1)").text();
		var headervalue=$j(row).children("td:nth-child(2)").text();
		var value=nl2br(headervalue);
		tblarray.push({"headerLabel":HeaderName,"headervalue":rowobj,"values":[{"displayType":displayType,"value":value,"unit":unit}]});
         }                                                           
     });
	return tblarray;
}

function fillViewheaderToTable(name,value,setTblVal){
    removeErrors();
    var dupStatus = checkRowIdDuplication('#viewTbltestReport',value);
	if(dupStatus==true){
		createError("Header exists",$j('#viewSelectTableHeader'));
		return false;
		}
    var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="deleteRptHdr" src="/weblims/images/remove-btn.png" rel="tooltip" title="delete"></a>';
	var rowData=$j('#viewTbltestReport').dataTable().fnAddData([name,setTblVal,myData]);
	var row=$j('#viewTbltestReport').dataTable().fnSettings().aoData[rowData].nTr;
	$j(row).attr('id',value);	
}