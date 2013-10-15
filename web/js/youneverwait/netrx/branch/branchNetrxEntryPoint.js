$j.cachedScript(constant_NetRxBranchFunctions_Url).done(function(script, textStatus) {
})

function branchlist(netrxId){
var a=getNetRxData(netrxId);
$j('#pageTitle').html(constant_BranchNetRxList_Msg);	
$j('#pageTitle1').show();
$j('#pageTitle1').html('['+a.netRxDTO.name.toUpperCase()+']');

	var pgBranchList;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#branchNetRx"; // Table showing netrx list
	pgTableContainer = "#branchNetRxTableCont"; // Parent container of the netrx list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	exp=filterExpression(exp,netrxId);
	var cururl = '{ "name":"' + "netRxId" + '","value":"' + netrxId + '","operator":"' + "eq" + '"}';		
	exp.push(cururl);
	//fill the result
	var branchNetrxListJson= getFilterlistUrl(exp,(curPage-1),interval);
	//alert(branchNetrxListJson);
	pgBranchList = viewNetRxBranchList(branchNetrxListJson,pgTableName);
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
			branchNetrxListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetRxBranchTable(branchNetrxListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			branchNetrxListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetRxBranchTable(branchNetrxListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			branchNetrxListJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetRxBranchTable(branchNetrxListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			branchNetrxListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetRxBranchTable(branchNetrxListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
//To select row from the table
	$j("#branchNetRx" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	
	
	$j('.netrxbranchIdCol').die('click').live('click',function(){
	removeErrors();
	    var netrxbranchId= $j(this).parent().attr('id');
	    if(netrxbranchId!="") {
			$j.cachedScript(constants_ViewNetrxBranchEntryPoint).done(function(script, textStatus) {
				viewNetrxBranch(netrxbranchId,'#passphraseViewTable','#passphrasePrimaryViewTable');
			})
		}	
	});
	
	$j('#branchNetrxPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
	var netrxbranchId = getSelectedNetrxBranchId(pgTableName);
	    if(netrxbranchId!="") {
			$j.cachedScript(constants_ViewNetrxBranchEntryPoint).done(function(script, textStatus) {
				viewNetrxBranch(netrxbranchId,'#passphraseViewTable','#passphrasePrimaryViewTable');
			})
		}
	});
	
	$j('#branchNetrxPTBContainer #btn_change_ptb_id').die('click').live("click",function() {
	removeErrors();
	  var netrxbranchId = getSelectedNetrxBranchId(pgTableName);
	    if(netrxbranchId!="") {
			var obj=$j(this);
			createModal(constants_netrxBranchSycSetJson,'netrxBranchSycSetModal');	
			openModalBox(obj,'netrxBranchSycSetModal')
		$j.cachedScript(constant_NetrxBranchSyncSet_Url).done(function(script, textStatus) {
		//getNetrxBrchId(netrxbranchId)
		})
		}	
	});
	
	
	$j('#branchNetrxPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
	removeErrors();
		$j('#pageTitle1').hide();
		$j.cachedScript(constant_NetRxEntry_Url).done(function(script, textStatus) {
		})
	});
	
	$j('#branchNetrxPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netrxbranchId = getSelectedNetrxBranchId(pgTableName);
		if(netrxbranchId!="") {
			var response = getRequestData('/youNeverWait/ws/ui/superAdmin/deleteNetRxBranch/' + netrxbranchId);
			if(response.success==true){
				showTip("Inactivated Branch Successfully");
				viewNetRxBranchList(branchNetrxListJson,pgTableName);
				//defaultData=getDefaultData();
			}	
		else
		updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
	/*$j('#branchNetrxPTBContainer #btn_newuser_ptb_id').die('click').live("click",function() {
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

//function to get the selected id from table
	function getSelectedNetrxBranchId(pgTableName) {
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