$j(document).ready(function() {
	$j.cachedScript("/youNeverWait/js/youneverwait/settings/test/new/newTestFunctions.js").done(function(script, textStatus) {
	})
	$j.cachedScript("/youNeverWait/js/youneverwait/settings/test/new/newTestValidation.js").done(function(script, textStatus) {
	})
	var ReportList=getRequestData('/youNeverWait/js/youneverwait/settings/test/reportcreation.json');
	fillLayoutToControl('#selectTestLayout');
	fillRemarksValToControl('#selectTestRemarks');
	fillSpecimenToControl('#selectTestSpecimen');
	fillHeaderDataToControl('#selectTableHeader');
	makeDataTable('#newTest #tblTestSpecimen');
	makeDataTable('#newTest #tbltestReport');
	fillfirstRowRptTbl();
	
	$j("#testModal #txtTestName").bind("keypress", function (e) {
		if (e.keyCode == 13)
			$j("#newTest #txtTestRate").focus();
		});
		

	$j('#newTest #txtTestName').die('click').live('focusout',function(){
		var testname=$j('#newTest #txtTestName').val();
		testname= testname.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	    }); 
		$j('#tbltestReport tbody tr:first ').children('td:nth-child(2)').text(testname);
	}); 

		
	$j('#newTest #txtareaNormalRange').die('click').live('focusout',function(){
		var normalRange=$j('#newTest #txtareaNormalRange').val();
		$j('#newTest #tbltestReport tbody tr').each(function() {
		var Obj=$j(this);
			if(Obj.children('td:nth-child(1)').text()=="REFERENCE RANGE&UNIT"){
			 Obj.children('td:nth-child(2)').text(br2nl(nl2br(normalRange)));
			}
		});	
	});
	
	$j('#newTest #Addspecimen').die('click').live('click',function(){
	  var specimenName=$j('#selectTestSpecimen option:selected').text();
	  var specimenVal=$j('#selectTestSpecimen option:selected').val();
	  if(specimenVal!="select"){
	  fillSpecimenToTable(specimenName,specimenVal);
	  $j('#newTest select#selectTestSpecimen option[value="select"]').attr("selected","selected");
	  }
	});

	$j('#newTest .delete').die('click').live('click',function(){
		removeErrors();
		var currow = $j(this).closest('tr');
		$j('#newTest #tblTestSpecimen').dataTable().fnDeleteRow(currow[0]);
	});

	$j('#newTest .deleteRptHdr').die('click').live('click',function(){
		removeErrors();
		var currow = $j(this).closest('tr');
		$j('#newTest #tbltestReport').dataTable().fnDeleteRow(currow[0]);
	});
	
    
	$j("#newTest select#selectTestLayout").die('change').change(function () {
	var curLayout=$j('#selectTestLayout').val();
	if(curLayout=="General"|| curLayout=="GeneralOne"){
	       $j("#newTest #tbltestReport").dataTable().fnClearTable();
			fillfirstRowRptTbl();
			$j('#newTest #enterHdrDiv').attr('style','display:""');
			$j('#newTest #testReportSample').attr('style','display:""');
			}
			else{
			$j('#newTest #enterHdrDiv').attr('style','display:none');
			$j('#newTest #testReportSample').attr('style','display:none');
		}
	 
      	 
	});
	$j('#newTest #btncreateTblHdr').die('click').live('click',function(){
		var Name=$j('#selectTableHeader option:selected').text();
		var value=$j('#selectTableHeader option:selected').val();
	    var setTblVal="";
	     if(value=="NormalRange")
			setTblVal=$j('#txtareaNormalRange').val();
		fillheaderToTable(Name,value,setTblVal);
	  $j('#newTest select#selectTestSpecimen option[value="select"]').attr("selected","selected");
	});

	$j('#newTest #selectTestRemarks').die('change').change(function (e) { 
		var selectedValue=$j("#txtareaTestRemarks").val();
		if($j("#selectTestRemarks  option:selected").text()!="select type"){
			curSelection = $j("#selectTestRemarks  option:selected").text() + "\n";
			selectedValue+= curSelection;
			$j("#txtareaTestRemarks").val(selectedValue);
		}
	});

	$j('#newTest #btntestReportPreview').die('click').live('click',function(){
	$j('#previewDiv').html('');
	var tableLng=$j('#newTest #tbltestReport').dataTable().fnGetNodes().length;
	if(tableLng!='0'){
	  var headerJson=createJsonforPreview();
	  var previewJson=$j.parseJSON(headerJson);
	  var resultPreview=new Preview(previewJson).result;
	   $j('#previewDiv').append(resultPreview);
	   $j('#previewDiv').attr('style','display:""');
	   }

	});

	$j('#newTest #btntestReportPreview').focusout(function(){
	  $j('#previewDiv').html('');
	  $j('#previewDiv').attr('style','display:none');

	});
	$j('#newTest #btnSaveTest').die('click').live('click',function(){
	  $j('#newTest #errorDivHeader').hide();
		removeErrors();
		if(validateNewtest()) {
			var response = submitTestInfo(ReportList);
			if(response.success==true){	
                resetNewtestForm();	
				//configData= getConfigData();
				//gbSpecialTestList = getSpecialTestList();
                $j.cachedScript("/youNeverWait/js/youneverwait/settings/test/testEntryPoint.js").done(function(script, textStatus) {
				})		
				showTip("Test created successfully");	
			} else
				updateTipsNew(getErrorName(response.error),$j('#newTest #errorDivNewTestData'),$j('#newTest #errorDivHeader'));
		}	
	});
		$j('#newTest #btnCancelTest').die('click').live('click',function(){
	    removeErrors();
		$j("#newTest input[type=text], textarea").val("");
		$j("#newTest #tbltestReport,#tblTestSpecimen").dataTable().fnClearTable();
		$j("#testModal").trigger('reveal:close');
	});	
	//prevent from entering characters
validateNumberDot("#newTest #txtTestRate");

});	


