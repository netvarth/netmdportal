
$j('#pageTitle').html("Tests"); //set the 
var pgTestList; 
$j.cachedScript("/youNeverWait/js/youneverwait/netlims/test/testFunctions.js").done(function(script, textStatus) {
})

$j(document).ready(function(){
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#newTestRateTable"; // Table showing referral list
	pgTableContainer = "#testListTableCont"; // Parent container of the referral list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	//fill the result
	var testListJson= getFilterlistUrl(exp,(curPage-1),interval);
	pgTestList = viewTestList(pgTableName,testListJson);
	if(pgTestList.count)
		maxRecords = pgTestList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval)+ 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	//alert("in last");
	// page tool bar new button pressed 
	$j('#testPTBContainer #btn_new_ptb_id').die('click').live('click',function(){
		removeErrors();
		//alert("in new");
		var obj=$j(this);
		createModal('/youNeverWait/json/new/newTest.json','testModal');		
		openModalBox(obj,'testModal');
		$j.getScript("/youNeverWait/js/youneverwait/netlims/test/new/newTest.js").done(function(script, textStatus) {
		})
	});	

	//disable the submit function of the form while user press enter key
	$j("form").bind("keypress", function (e) {
		if (e.keyCode == 13) return false;
	});
	
	// View Button Pressed 
	$j('#testPTBContainer #btn_view_ptb_id').die('click').click(function(){
		removeErrors();
		var testId=getSelectedTestId(pgTableName);
		if(testId!="") {
			$j('#test-filter-wb').hide();
			$j.cachedScript("/youNeverWait/js/youneverwait/netlims/test/view/viewTestEntryPoint.js").done(function(script, textStatus) {
				viewTest(testId,'#viewTestForm #tblViewTestSpecimen','#viewTestForm #viewTbltestReport');
			})
		}	
	});
	
	// page tool bar delete button pressed 
	$j('#testPTBContainer #btn_delete_ptb_id').die('click').live('click',function(){
		removeErrors();
		var testId = getSelectedTestId(pgTableName);
		//alert(testId);
		if(testId!="") {
				var response = getRequestData('/youNeverWait/ws/ui/test/deleteTest/' + testId);
							
				if(response.success==true){
					viewTestList(pgTableName,testListJson);
					showTip("Test deleted successfully");
				}	
				else
					updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
						//gbSpecialTestList = getSpecialTestList();
				
			
		}
	});
	
	
	$j(pgTableName +' tbody tr').die('click').live('click',function(){
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeClass('rowselected');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).addClass('rowselected');
		}	
		removeErrors();
	});
	
	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			testListJson=getFilterlistUrl(exp,startValue,interval);
			pgTestList=fillTestTable(testListJson,pgTableName);	
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			testListJson=getFilterlistUrl(exp,startValue,interval);
			pgTestList=fillTestTable(testListJson,pgTableName);				
			setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			testListJson= getFilterlistUrl(exp,startValue,interval);
			pgTestList=fillTestTable(testListJson,pgTableName);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			testListJson=getFilterlistUrl(exp,startValue,interval);
			pgTestList=fillTestTable(testListJson,pgTableName);			
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j('.testIdCol').die('click').live('click',function(){
	   var testId= $j(this).parent().attr('id');
		if(testId!="") {
			$j('#test-filter-wb').hide();
			$j.cachedScript("/youNeverWait/js/youneverwait/netlims/test/view/viewTestEntryPoint.js").done(function(script, textStatus) {
				viewTest(testId,'#viewTestForm #tblViewTestSpecimen','#viewTestForm #viewTbltestReport');
			}).fail(function(jqhr,settings,exception){
           alert(exception);
			})	
		}	
	});
	
	//cancel button
	$j('#btnTestRateCancel').die('click').live("click",function(){
		removeErrors();
		var rows = $j(pgTableName +" tr:gt(0)"); //this will not include the header row
		rows.children("td:nth-child(3)").each(function() {
		$j(this).attr('contenteditable',true);
			var currentrow1=$j(this).closest('tr');
			var preVal= $j(this).attr('name');
			currentrow1.children(" td:eq(2)").text(preVal);
			$j('span[id="action"]').closest('tr').removeAttr('style');
			$j('span[id="action"]').text("");
		}); 
	});
	
	$j('#btnTestRateSave').die('click').live("click",function(){
		removeErrors();
		updatedTestRate();
		var rows = $j(pgTableName +" tr:gt(0)"); //this will not include the header row
		rows.children("td:nth-child(3)").each(function() {
			$j(this).attr('contenteditable',false);
		});
		$j('#btnTestRateSave').hide();
		$j('#btnTestRateCancel').hide();
	});
	
	//Make editable cell as uneditable
	$j(pgTableName +' tr td[contenteditable=true]').live("focusout",function(){
		var currentrow=$j(this).closest('tr');
		removeErrors();
		rowid=currentrow.attr('id');   
		currentrow.attr('style','background:#FF9999;');
		currentrow.children(" td:eq(2)").addClass("Update");
	});
	
	$j('#test-filter-toolbar #txt_Search').die('keypress').live('keypress',function(e){
		removeErrors();
		if(e.keyCode==13){
			startValue=0;
			curPage=1;
			exp=getExpression();
			testListJson=getFilterlistUrl(exp, startValue, interval);
			pgTestList=fillTestTable(testListJson,pgTableName);
			if(pgTestList.count)
				maxRecords = pgTestList.count;
			if(maxRecords%interval!=0)
				maxPages = parseInt(maxRecords/interval) + 1;
			else
				maxPages = parseInt(maxRecords/interval);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	
	$j('#testPTBContainer #btn_edit_ptb_id').die('click').live('click',function(){
		removeErrors();
		$j("#btnTestRateCancel").show();
		$j("#btnTestRateSave").show();
		var rows = $j("#newTestRateTable  tr:gt(0)"); //this will not include the header row
		rows.children("td:nth-child(3)").each(function() {
			$j(this).attr('contenteditable',true);		
			$j(this).addClass('colEditable');
		});
	});
	
	function getExpression() {
		var expArray = [];
		if($j('#test-filter-toolbar #txt_Search').val()!=""){
			var cururl = '{"name":"testName","value":"' + $j('#test-filter-toolbar #txt_Search').val() + '","operator":"like"}';						
			expArray.push(cururl);
		}
		return expArray;
	} 
	
	//function to get the selected testId
	function getSelectedTestId(pgTableName) {
		var testId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selTests = $j(pgTableName + ' tbody tr[selected]');
			if(selTests.length==0){		
				updateTipsNew(constants_selectOnlyOneTest,$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selTests.length>1) 
				updateTipsNew(constants_selectOnlyOneTest,$j('#errorDivData'),$j('#errorDivHeader'));
			else
				testId=selTests.attr('id');
		}	
		return testId;
	}
});	