$j('#pageTitle').html(constant_NetLimsAccList_Msg);
$j.cachedScript(constant_NetLimsAccFunctions_Url).done(function(script, textStatus) {
})

$j(document).ready(function() {
	//var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	var labName=userdata.name;
	var netlimsId=userdata.labId;
	var netlimsOtherUserId=userdata.id;
	var usetType=userdata.userType;
	var pgBranchList;
	var cururl;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#branchNetlimsAcc"; // Table showing netlims list
	pgTableContainer = "#branchNetlimsAccTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	
	if(usetType=='owner'){
		exp=filterExpression(exp,netlimsId);
		cururl = '{ "name":"' + "labId" + '","value":"' + netlimsId + '","operator":"' + "eq" + '"}';		
	}
	else{
		exp=filterExpression(exp,netlimsOtherUserId);
		cururl = '{ "name":"' + "userId" + '","value":"' + netlimsOtherUserId + '","operator":"' + "eq" + '"}';		
	}
	exp.push(cururl);
	//fill the result
	var branchListNetlimsAccJson= getFilterlistUrl(exp,(curPage-1),interval);
	pgBranchList = viewNetlimsAccBranchList(branchListNetlimsAccJson,pgTableName);
	if(pgBranchList.count)
		maxRecords = pgBranchList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			branchListNetlimsAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetlimsAccBranchTable(branchListNetlimsAccJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			branchListNetlimsAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetlimsAccBranchTable(branchListNetlimsAccJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			branchListNetlimsAccJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetlimsAccBranchTable(branchListNetlimsAccJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			branchListNetlimsAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetlimsAccBranchTable(branchListNetlimsAccJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
		//To select row from the table
	$j("#branchNetlimsAcc" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	
	
	$j('#branchNetlimsAccPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
	removeErrors();
		var obj=$j(this);
		createModal(constants_newBranchNetLimsAccJson,'netlimsAccBranchModal');	
		openModalBox(obj,'netlimsAccBranchModal')
		$j.cachedScript(constants_newNetLimsAccBranch).done(function(script, textStatus) {
		})
	});
	
	$j('.branchNetlimsAccIdCol').die('click').live('click',function(){
	removeErrors();
	    var branchId= $j(this).parent().attr('id');
		if(branchId!="") {
			$j.cachedScript(constants_ViewNetlimsAccBranchEntryPoint).done(function(script, textStatus) {
			viewNetlimsAccBranch(branchId);
			//viewbranchNetlimsAccMacInfo(branchId);
			})
		}	
	});
	
	$j('#branchNetlimsAccPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
		if(branchId!="") {
			$j.cachedScript(constants_ViewNetlimsAccBranchEntryPoint).done(function(script, textStatus) {
			viewNetlimsAccBranch(branchId);
			//viewbranchNetlimsAccMacInfo(branchId);
			})
		}

	});
	
	$j('#branchNetlimsAccPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
		if(branchId!="") {
			var deleteJson = '{"globalId":'+branchId+',';
			deleteJson +='"labId":' + netlimsId + '}';
			if(branchId!="") {
			var response = postdataToServer(constant_newNetLimsAccBranch_Delete_Url, deleteJson );
			if(response.success==true){
				showTip("Inactivated Branch Successfully");
				viewNetlimsAccBranchList(branchListNetlimsAccJson,pgTableName);
			}
			else
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
			
			}
		}
	});	
});	//document.ready ends here	*/

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