$j('#pageTitle').html(constant_NetMdAccList_Msg);
$j.cachedScript(constant_NetMdAccFunctions_Url).done(function(script, textStatus) {
})

$j(document).ready(function() {
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	var Name=userdata.name;
	var username=userdata.userName;
	var netmdId=userdata.netmdId;
	//var labid=userdata.labId;
	var netmdOtherUserId=userdata.id;
	var usetType=userdata.userType;
	var pgBranchList;
	var cururl;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#branchNetmdAcc"; // Table showing netlims list
	pgTableContainer = "#branchNetmdAccTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	
	if(usetType=='owner'){
		exp=filterExpression(exp,netmdId);
		cururl = '{ "name":"' + "netMdId" + '","value":"' + netmdId + '","operator":"' + "eq" + '"}';		
	}
	else{
		exp=filterExpression(exp,netmdOtherUserId);
		cururl = '{ "name":"' + "userId" + '","value":"' + netmdOtherUserId + '","operator":"' + "eq" + '"}';		
	}
	exp.push(cururl);
	//fill the result
	var branchListNetmdAccJson= getFilterlistUrl(exp,(curPage-1),interval);
	pgBranchList = viewNetmdAccBranchList(branchListNetmdAccJson,pgTableName);
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
			branchListNetmdAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetmdAccBranchTable(branchListNetmdAccJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			branchListNetmdAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetmdAccBranchTable(branchListNetmdAccJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			branchListNetmdAccJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetmdAccBranchTable(branchListNetmdAccJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			branchListNetmdAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetmdAccBranchTable(branchListNetmdAccJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	//To select row from the table
	$j("#branchNetmdAcc" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});
	
	$j('#branchPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
	removeErrors();
		var obj=$j(this);
		createModal(constants_newBranchNetMdAccJson,'netmdAccBranchModal');	
		openModalBox(obj,'netmdAccBranchModal')
		$j.cachedScript(constants_newNetmdAccBranch).done(function(script, textStatus) {
		})
	});
	
	$j('.branchIdCol').die('click').live('click',function(){
	removeErrors();
	    var branchId= $j(this).parent().attr('id');
		if(branchId!="") {
			$j.cachedScript(constants_ViewNetmdAccBranchEntryPoint).done(function(script, textStatus) {
			viewNetmdAccBranch(branchId,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
			//viewbranchNetmdAccMacInfo(branchId);
			})
		}	
	});
	
	$j('#branchPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
		if(branchId!="") {
			$j.cachedScript(constants_ViewNetmdAccBranchEntryPoint).done(function(script, textStatus) {
			viewNetmdAccBranch(branchId,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
			//viewbranchNetmdAccMacInfo(branchId);
			})
		}

	});
	
	$j('#branchPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
		if(branchId!="") {
			//var deleteJson = '{"globalId":'+branchId+',';
			//deleteJson +='"netmdId":' + netmdId + '}';
			if(branchId!="") {
			var response = getRequestData(constant_newNetmdAccBranch_Delete_Url+branchId);
			if(response.success==true){
				showTip("Inactivated Branch Successfully");
				 viewNetmdAccBranchList(branchListNetmdAccJson,pgTableName);
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