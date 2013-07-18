$j.cachedScript(constant_NetLimsBranchFunctions_Url).done(function(script, textStatus) {
})
function branchlist(netlimsId){
var a=getNetLimsData(netlimsId);
$j('#pageTitle').html(constant_BranchList_Msg);	
$j('#pageTitle1').show();
	$j('#pageTitle1').html('['+a.lab.name.toUpperCase()+']');
	var pgBranchList;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#branch"; // Table showing netlims list
	pgTableContainer = "#branchTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	exp=filterExpression(exp,netlimsId);
	var cururl = '{ "name":"' + "labId" + '","value":"' + netlimsId + '","operator":"' + "eq" + '"}';		
	exp.push(cururl);
	//fill the result
	var branchListJson= getFilterlistUrl(exp,(curPage-1),interval);
	//alert(branchListJson);
	pgBranchList = viewBranchList(branchListJson,pgTableName);
	if(pgBranchList.count)
		maxRecords = pgBranchList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	//setPaginationFields(curPage, maxPages, pgTableContainer);
	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			branchListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			branchListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			branchListJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			branchListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
//To select row from the table
	$j("#branch" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	

	$j('.branchIdCol').die('click').live('click',function(){
	removeErrors();
	    var branchId= $j(this).parent().attr('id');
	    if(branchId!="") {
			$j.cachedScript(constants_ViewBranchEntryPoint).done(function(script, textStatus) {
				viewBranch(branchId,'#passphrasenetlimsViewTable');
			})
		}	
	});
	
	$j('#branchPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
	  var branchId = getSelectedBranchId(pgTableName);
	    if(branchId!="") {
			$j.cachedScript(constants_ViewBranchEntryPoint).done(function(script, textStatus) {
				viewBranch(branchId,'#passphrasenetlimsViewTable');
			})
		}	
	});
	$j('#branchPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
	removeErrors();
		$j('#pageTitle1').hide();
		$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
		})
	});
	
	$j('#branchPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
		var deleteJson=createDeleteJson(branchId,netlimsId);
		if(branchId!="") {
		var response = postdataToServer(constant_newNetLimsBranch_Delete_Url, deleteJson );
		if(response.success==true){
		
			//$j(pgTableName).dataTable().fnDeleteRow($j('#'+branchId).closest('tr')[0]);	
			showTip("Inactivated Branch Successfully");
			viewBranchList(branchListJson,pgTableName);
			//defaultData=getDefaultData();
		}	
		//else
		//alert("error");
		//updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
	/*$j('#branchPTBContainer #btn_newuser_ptb_id').die('click').live("click",function() {
	alert("new user");
	removeErrors();
		var branchid= getSelectedBranchId(pgTableName);
		if(branchid!="") {
		var obj=$j(this);
		createModal(constants_newNetUserJson,'netlimsModal');	
		openModalBox(obj,'netlimsModal')
		$j.cachedScript(constants_newNetLimsUser).done(function(script, textStatus) {
		createUser(branchid);
		})
		}
	});*/
	
	
}
	
	function createDeleteJson(branchId,netlimsId){
		var BranchDetails = '{"globalId":'+branchId+',';
			BranchDetails +='"labId":' + netlimsId + '}';
		return BranchDetails;
	}
	
//function to get the selected id from table
	function getSelectedBranchId(pgTableName) {
		var branchId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selBranch = $j(pgTableName + ' tbody tr[selected]');
			if(selBranch.length==0){	
				updateTipsNew("Select atleast one branch",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selBranch.length>1) 
				updateTipsNew("Select only one branch",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				branchId=selBranch.attr('id');
		}		
		return branchId;
	}