//alert("function");
function viewTestList(specimentableObj) {
	loadTestPageToolBar();
	var testTable=setTestTableStructure();
	$j('#tabs-1').html(testTable.html());
	setCustomDataTable(specimentableObj);
	//searchDiv = createSearchDiv("test-filter-toolbar");
	//$j('#tabs-1 .dataTables_wrapper .top').append(searchDiv);
	//fillTestOnlyToControl("txt_Search");
	//pgTestList=fillTestTable(testListJson,specimentableObj);
	//return pgTestList;
}

function loadTestPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =  getRequestData('/youNeverWait/json/toolbars/testPageToolBar.json');
	var ptbContainer = $j('<div id="testPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function setTestTableStructure() {
	//create the table structure for testPkg Table	
	var group=getRequestData('/youNeverWait/json/list/testTable.json');
	var boxDiv = $j('<div/>');
	$j(group.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return boxDiv;
}

/*function fillTestTable(testListJson, tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var testRateList = postdataToServer("/weblims/ws/ui/test/getTests",testListJson);
	curSelLength=testRateList.test.length;
	if(curSelLength>0) {		
		$j(testRateList.test).each(function(index,testDetail) {
			var stdRate=testDetail.stdRate;
			var active=testDetail.active;
			var rowData= $j(tableObj).dataTable().fnAddData([testDetail.uid,testDetail.name,stdRate.toFixed(2)]);
			var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(3)").attr('name',stdRate);
			$j(row).children("td:nth-child(3)").attr("class","column-2");
			$j(row).attr('id',testDetail.uid);	
			$j(row).children("td:nth-child(1)").attr("class","testIdCol Ustyle");		
			if(active==false)
				$j(row).attr('style','background:#888888 ;');					
		});
	}	
	return testRateList;		
}

// for search option
function filltestSearchTable(testListJson, tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	var testRateList = postdataToServer("/weblims/ws/ui/test/searchTest",testListJson);	
	curSelLength=testRateList.test.length;
	if(curSelLength>0) {		
		$j(testRateList.test).each(function(index,testDetail) {
			var stdRate=testDetail.stdRate;
			var operations='<span id="action" style="position:absolute;visibility:hidden;"></span>';
			var rowData= $j(tableObj).dataTable().fnAddData([testDetail.uid,testDetail.name,stdRate.toFixed(2),operations]);
			var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).children("td:nth-child(3)").attr('name',stdRate);
			$j(row).children("td:nth-child(3)").attr("class","column-2");
			$j(row).attr('id',testDetail.uid);	
		});
	}	
	return testRateList;		
}

// function to get the  previous testid from the testlist json 
function getpreviousTestId(testId, testResult) {
	var tstId;
	$j(testResult.test).each(function (index, rowTest) {
		if(testId==rowTest.uid)	{
			var arrayLength=(testResult.test).length;
			var comp=arrayLength-1;
			if(index==0)
				tstId = testId;
			else
				tstId=testResult.test[index-1].uid;
			return false;
		}
	});
	return tstId;	
}
// function to get the  next testid from the testlist json 
function getnextTestId(testId, testResult) {
	var tstId;
	$j(testResult.test).each(function (index, rowTest) {
		if(testId==rowTest.uid)	{
			var arrayLength=(testResult.test).length;
			var comp=arrayLength-1;
			if(index==comp)
				tstId = testId;
			else
				tstId=testResult.test[index+1].uid;	
			return false;
		}
	});	
	return tstId;	
}

//following are functions for edit rate

function updatedTestRate(){
	var len = getCount();
	if(len>0) {
		var resultJson=createPkgJson();
		var orderResult = postdataToServer("/weblims/ws/ui/test/updateTestRate", resultJson );
		$j('.Update').closest('tr').removeAttr('style');
		return;
	}
}
function createPkgJson(){
	var testpkgArray=[];
	var newjson;
	var editArraycount;
	var rows = $j("#newTestRateTable  tr:gt(0)");
	rows.children("td:nth-child(3)").each(function() {
		var change=$j(this).attr('class');
		if(change.indexOf('Update')>=0){
		   var currentrow=$j(this).closest('tr');
		   var id=$j(this).closest('tr').attr('id');
		   var rate=parseFloat(currentrow.children("td:nth-child(3)").text(),10) || 0;
		   currentrow.children("td:nth-child(3)").attr('name',rate);
		   testpkgArray.push('{'+'"uid"' +':"'+id+'"'+','+'"stdRate"'+':"'+rate+'"' + ',"actionName":"Update"' +'}');
		}
		newjson ='{"testRateDTO":['+testpkgArray+']}';   
	});
	editArraycount=testpkgArray.length;
	if(editArraycount>0)
		showTip(constants_testRateEditSuccess);
	return newjson;
}
function getCount() {
	var count  = 0;
	var rows = $j("#newTestRateTable  tr:gt(0)");
	rows.children("td:nth-child(3)").each(function() {
		var change=$j(this).attr('class');
		if(change.indexOf('Update')>=0){
			count+=1;
		}
	});
	return count;
}
*/
