$j('#pageTitle').html(constant_NetRxAccList_Msg);
$j.cachedScript(constant_NetRxAccFunctions_Url).done(function(script, textStatus) {
})

$j(document).ready(function() {
	var userdata =getRequestData('/youNeverWait/ynw/auth/user');
	//alert(JSON.stringify(userdata));
	var Name=userdata.name;
	var username=userdata.userName;
	var netrxId=userdata.netrxId;
	//var labid=userdata.labId;
	var netrxOtherUserId=userdata.id;
	var usetType=userdata.userType;
	var pgBranchList;
	var cururl;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#branchNetrxAcc"; // Table showing netlims list
	pgTableContainer = "#branchNetrxAccTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	
	if(usetType=='owner'){
		exp=filterExpression(exp,netrxId);
		cururl = '{ "name":"' + "netRxId" + '","value":"' + netrxId + '","operator":"' + "eq" + '"}';		
	}
	else{
		exp=filterExpression(exp,netrxOtherUserId);
		cururl = '{ "name":"' + "userId" + '","value":"' + netrxOtherUserId + '","operator":"' + "eq" + '"}';		
	}
	exp.push(cururl);
	//fill the result
	var branchListNetrxAccJson= getFilterlistUrl(exp,(curPage-1),interval);
	pgBranchList = viewNetrxAccBranchList(branchListNetrxAccJson,pgTableName);
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
			branchListNetrxAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetrxAccBranchTable(branchListNetrxAccJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			branchListNetrxAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetrxAccBranchTable(branchListNetrxAccJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			branchListNetrxAccJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetrxAccBranchTable(branchListNetrxAccJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			branchListNetrxAccJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetrxAccBranchTable(branchListNetrxAccJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	//To select row from the table
	$j("#branchNetrxAcc" + ' tbody tr').die('click').live('click',function(){		
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
		createModal(constants_newBranchNetrxAccJson,'netrxAccBranchModal');	
		openModalBox(obj,'netrxAccBranchModal')
		$j.cachedScript(constants_newNetrxAccBranch).done(function(script, textStatus) {
		})
	});
	
	$j('.branchIdCol').die('click').live('click',function(){
	removeErrors();
	    var branchId= $j(this).parent().attr('id');
		if(branchId!="") {
			$j.cachedScript(constants_ViewNetrxAccBranchEntryPoint).done(function(script, textStatus) {
			viewNetrxAccBranch(branchId,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
			//viewbranchNetmdAccMacInfo(branchId);
			})
		}	
	});
	
	$j('#branchPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
		if(branchId!="") {
			$j.cachedScript(constants_ViewNetrxAccBranchEntryPoint).done(function(script, textStatus) {
			viewNetrxAccBranch(branchId,'#passphraseViewTableAcc','#passphrasePrimaryViewTableAcc');
			//viewbranchNetmdAccMacInfo(branchId);
			})
		}

	});
	
	$j('#branchPTBContainer #btn_change_ptb_id').die('click').live("click",function() {
	removeErrors();
	  var netrxbranchId = getSelectedBranchId(pgTableName);
	    if(netrxbranchId!="") {
			var obj=$j(this);
			createModal(constants_netrxAccBranchSycSetJson,'netrxAccBranchSycSetModal');	
			openModalBox(obj,'netrxAccBranchSycSetModal')
		$j.cachedScript(constant_NetrxAccBranchSyncSet_Url).done(function(script, textStatus) {
		getNetrxBrchId(netrxbranchId)
		})
		}	
	});
	
	$j('#branchPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
	
			if(branchId!="") {
			var response = getRequestData(constant_newNetrxAccBranch_Delete_Url+branchId);
			//alert(JSON.stringify(response));
			if(response.success==true){
				showTip("Inactivated Branch Successfully");
				 viewNetrxAccBranchList(branchListNetrxAccJson,pgTableName);
			}
			else
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
			
			}
		
	});	
});	//document.ready ends here	


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