var testInfo = "";
TestSpecimenFilteredData = [];
var uploadstatus;
var ReportList=getRequestData('/youNeverWait/js/youneverwait/netlims/test/reportcreation.json');

$j("#testGeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {	
	$j.getScript( "/youNeverWait/js/youneverwait/netlims/test/testEntryPoint.js").done(function(script, textStatus) {
	})
});

//up button click action
$j("#testGeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {	
	removeErrors();
	var curTestId = getCurTestId();
	var testIdforView = getpreviousTestId(curTestId,pgTestList);
	viewTestInfo(testIdforView,'#viewTestForm #tblViewTestSpecimen');
});	

//up button click action
$j("#testGeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {	
	removeErrors();
	var curTestId = getCurTestId();
	var testIdforView = getnextTestId(curTestId,pgTestList);
	viewTestInfo(testIdforView,'#viewTestForm #tblViewTestSpecimen');
});	

//specimen update action
$j('#viewTestForm #updateSpecimen').die('click').live('click',function(){
	var specimenName=$j('#viewSelectTestSpecimen option:selected').text();
	var specimenVal=$j('#viewSelectTestSpecimen option:selected').val();
	if(specimenVal!="select"){
		fillViewSpecimenToTable(specimenName,specimenVal,'#tblViewTestSpecimen');
		$j('#viewTestForm select#viewSelectTestSpecimen option[value="select"]').attr("selected","selected");
		TestSpecimenFilteredData=resultJsonTest(specimenVal, "Add", testInfo,TestSpecimenFilteredData);
	}
});

//delete symbol in specimen table pressed
$j('#viewTestForm .deleteSpecimen').die('click').live('click',function(){
	removeErrors();
	var specimenId=$j(this).closest('tr').attr('id');
	var action="Delete";
	TestSpecimenFilteredData=resultJsonTest(specimenId, action, testInfo,TestSpecimenFilteredData);
});

//Cancel Button is pressed 
$j('#viewTestForm #btnViewCancelTest').die('click').live('click',function(){
	removeErrors();
	TestSpecimenFilteredData=[];
	testId=getCurTestId();
	restoreTestInfo(testId);
    return false;			
});

//Edit Button Pressed 
$j('#viewTestForm #btnEditTest').die('click').live('click',function(){
	var layout=$j('#viewTestForm #lblViewLayout label').text();
	$j('#viewTestForm #btnEditTest').hide();
	$j('#viewTestForm #btnViewSaveTest,#viewTestForm #btnViewCancelTest').show();
	$j('#viewTestForm #lblremarks').hide();
	$j('#viewTestForm #specimenEntryDiv').show();
	$j('#viewTestForm #specimenEntryDisplayDiv').hide();
	$j('#viewTestForm #machineEntryDiv').show();
	$j('#viewTestForm #machineEntryDisplayDiv').hide();
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').hide();
	//prevent from entering characters
	validateNumberDot("#viewTestForm #txtViewTestRate");
	clearNilFields(viewTestForm);
	var testId=getCurTestId();
	testInfo=getTestData(testId);
	
	if(layout=="General" || layout=="GeneralOne"){
		$j('#viewTestForm #viewEnterHdrDiv').attr('style','display:""');
		$j('#viewTestForm #btnViewTestReportPreview').show();
	}
	if(testInfo.specimenentryStatus==true)
		$j('#viewTestForm #specEntryStatus ').attr('checked','true');
	if(testInfo.machineEntryStatus==true)
		$j('#viewTestForm #machEntryStatus').attr('checked','true');	
	$j('#viewTestForm #viewlayout').hide();
	$j('#viewTestForm #selectLayout,#viewTestForm #editselectLayout').attr('style','display:""');
	$j('#viewSelectTestLayout option[value="'+layout+'"]').attr("selected","selected");
	$j('#viewTestForm #viewTbltestReport').dataTable().fnSetColumnVis( 2, true );
	$j('#viewTestForm input[type=text],textarea').removeClass('newBox');
	$j('#viewTestForm input[type=text],textarea').removeAttr('readonly');
	$j('#viewTestForm #workBenchSpecimen,#viewTestForm #selectRemarks').attr('style','display:""');
	$j('#viewTestForm #tblViewTestSpecimen').dataTable().fnSetColumnVis( 1, true );
    return false;
});

$j('#viewSelectTestRemarks').die('change').live('change',function (e) { 
	var selectedValue=$j("#txtViewareaTestRemarks").val();
	if($j("#viewSelectTestRemarks  option:selected").text()!="select type"){
		curSelection = $j("#viewSelectTestRemarks  option:selected").text() + "\n";
		selectedValue+= curSelection;
		$j("#txtViewareaTestRemarks").val(selectedValue);
	}
});

$j('#viewTestForm #txtViewTestName').die('click').live('focusout',function(){
	var testname=$j('#viewTestForm #txtViewTestName').val();
	$j('#viewTbltestReport tbody tr:first ').children('td:nth-child(2)').text(testname);
});

function restoreTestInfo(){
	$j('#viewTestForm  #viewTbltestReport').dataTable().fnSetColumnVis( 2, false );
	$j('#viewTestForm  #tblViewTestSpecimen').dataTable().fnSetColumnVis( 1, false );
	$j('#viewTestForm  #btnViewSaveTest').hide();
	$j('#viewTestForm  #btnViewCancelTest').hide();
	$j('#viewTestForm  #btnEditTest').show();
	$j('#viewTestForm input[type=text],textarea').addClass('newBox');
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
	$j("#viewTestForm #viewTbltestReport").dataTable().fnClearTable();
	$j("#viewTestForm #tblViewTestSpecimen").dataTable().fnClearTable();
	$j("#viewTestForm #viewEnterHdrDiv,#viewTestForm #editselectLayout,#viewTestForm #selectRemarks").attr('style','display:none');
	$j('#viewTestForm #workBenchSpecimen').attr('style','display:none');
	$j('#viewTestForm #viewlayout').show();
	$j('#viewTestForm #lblremarks').show();
	$j('#viewTestForm #viewpreviewDiv').hide();
	$j('#viewTestForm #btnViewTestReportPreview').removeClass('visible');
	$j('#viewTestForm #machineEntryDiv').hide();
	$j('#viewTestForm #machineEntryDisplayDiv').show();
	$j('#viewTestForm #specimenEntryDiv').hide();
	$j('#viewTestForm #specimenEntryDisplayDiv').show();
	viewTestInfo(testId,'#viewTestForm  #tblViewTestSpecimen','#viewTestForm  #viewTbltestReport');
}
//when focus is lost from normal range it automatically fill the value to the table
$j('#viewTestForm #txtareaViewNormalRange').die('focusout').live('focusout',function(){
	var normalRange=$j('#viewTestForm #txtareaViewNormalRange').val();
	$j('#viewTestForm #viewTbltestReport tbody tr').each(function() {
		var Obj=$j(this);
		if(Obj.children('td:nth-child(1)').text()=="REFERENCE RANGE&UNIT"){
			Obj.children('td:nth-child(2)').text(br2nl(nl2br(normalRange)));
		}
	});	
});

$j('#viewTestForm #btnViewSaveTest').die('click').live('click',function(){
	$j('#viewTestForm #errorDivHeader').hide();
	removeErrors();
	if(validateViewtest()){
		var response = updateTestInfo(TestSpecimenFilteredData,ReportList,uploadstatus);
		if(response.success==true){	
			TestSpecimenFilteredData=[];
			showTip("Test updated successfully");
			testId=getCurTestId();
			restoreTestInfo(testId);
			//configData= getConfigData();
			//gbSpecialTestList = getSpecialTestList();
		} else
			updateTipsNew(getErrorName(response.error),$j('#viewTestForm #errorDivViewTestData'),$j('#viewTestForm #errorDivHeader'));
				
	}	
});

/* $j('#viewTestForm #txtViewNormalUnit').die('focusout').live('focusout',function(){
	var oldValue = $j(this).attr('oldValue');
	var currentValue = $j(this).val();
	if($j('#viewTestForm #txtareaViewNormalRange').val()!=""){
		var textareaVal=$j('#viewTestForm #txtareaViewNormalRange').val().replaceAll(oldValue,currentValue); 
		$j('#viewTestForm #txtareaViewNormalRange').val(textareaVal);
	}	
});

$j('#viewTestForm #txtViewNormalUnit').live('focus', function(){
    $j(this).attr('oldValue',$j(this).val());
}); */

//$j('#viewTestForm #btntestReportPreview').focusout(function(){
//	$j('#viewpreviewDiv').html('');
//	$j('#viewpreviewDiv').attr('style','display:none');
//});

//$j('#viewTestForm #btnViewTestReportPreview').die('click').live('click',function(){
	
	
//});

$j("#viewTestForm select#viewSelectTestLayout").die('change').live('change',function(){
	var curLayout=$j('#viewSelectTestLayout').val();
    $j("#viewTestForm #viewTbltestReport").dataTable().fnClearTable();
    if(curLayout=="General" ||curLayout=="GeneralOne" ) {
		$j('#viewTestForm #viewTestReportSample').show();
		testname = $j('#viewTestForm #txtViewTestName').val();
	    var rowData=$j('#viewTestForm #viewTbltestReport').dataTable().fnAddData(["INVESTIGATION",testname,'']);
		var row=$j('#viewTestForm #viewTbltestReport').dataTable().fnSettings().aoData[rowData].nTr;
		$j(row).attr('id',"Investigation");
		
		
	    $j('#viewTestForm #viewEnterHdrDiv').attr('style','display:""');
		$j('#viewTestForm #viewTbltestReport').show();
		$j('#viewTestForm #btnViewTestReportPreview').show();
	}
	else{
		$j('#viewTestForm #viewEnterHdrDiv').attr('style','display:none');
		$j('#viewTestForm #viewTestReportSample').attr('style','display:none');
		$j('#viewTestForm #btnViewTestReportPreview').hide();
	}	      	 
});

$j('#viewTestForm #btnViewCreateTblHdr').die('click').live('click',function(){
	var Name=$j('#viewSelectTableHeader option:selected').text();
	var value=$j('#viewSelectTableHeader option:selected').val();
	var setTblVal="";
		if(value=="NormalRange")
			setTblVal=$j('#txtareaViewNormalRange').val();
	fillViewheaderToTable(Name,value,setTblVal);
	$j('#viewTestForm select#selectTestSpecimen option[value="select"]').attr("selected","selected");
});

$j('#viewTestForm .deleteViewHdr').die('click').live('click',function(){
	removeErrors();
	var currow = $j(this).closest('tr');
	$j('#viewTestForm #viewTbltestReport').dataTable().fnDeleteRow(currow[0]);
});

$j('#viewTestForm #btnViewTestReportPreview').die('click').live('click',function(){
	var dispClass=$j('#viewTestForm #btnViewTestReportPreview').attr('class');
	if(dispClass.indexOf('visible')>=0) {
		$j('#viewTestForm #viewpreviewDiv').hide();
		$j('#viewTestForm #btnViewTestReportPreview').removeClass('visible');
	} else {
		$j('#viewTestForm #viewpreviewDiv').show();
		$j('#viewpreviewDiv').html('');
		var tableLng=$j('#viewTestForm #viewTbltestReport').dataTable().fnGetNodes().length;
		if(tableLng!='0'){
			var headerJson=createViewJsonforPreview();
			var previewJson=$j.parseJSON(headerJson);
			var resultPreview=new Preview(previewJson).result;
			$j('#viewpreviewDiv').append(resultPreview);
			$j('#viewpreviewDiv').attr('style','display:""');
		}
		$j('#viewTestForm #btnViewTestReportPreview').addClass('visible');
	}	
});

function getCurTestId() {
	return $j('#viewTestForm #lblViewTestId label').text();
}