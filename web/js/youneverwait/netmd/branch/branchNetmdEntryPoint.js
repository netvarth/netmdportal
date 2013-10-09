$j.cachedScript(constant_NetMdBranchFunctions_Url).done(function(script, textStatus) {
})

function branchlist(netmdId){
var a=getNetMdData(netmdId);
$j('#pageTitle').html(constant_BranchNetMdList_Msg);
$j('#pageTitle1').show();	
$j('#pageTitle1').html('['+a.netMd.name.toUpperCase()+']');

	var pgBranchList;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#branchNetMd"; // Table showing netmd list
	pgTableContainer = "#branchNetMdTableCont"; // Parent container of the netmd list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	exp=filterExpression(exp,netmdId);
	var cururl = '{ "name":"' + "netMdId" + '","value":"' + netmdId + '","operator":"' + "eq" + '"}';		
	exp.push(cururl);
	//fill the result
	var branchNetmdListJson= getFilterlistUrl(exp,(curPage-1),interval);
	//alert(branchNetmdListJson);
	pgBranchList = viewNetMdBranchList(branchNetmdListJson,pgTableName);
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
			branchNetmdListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetMdBranchTable(branchNetmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			branchNetmdListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetMdBranchTable(branchNetmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			branchNetmdListJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetMdBranchTable(branchNetmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			branchNetmdListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillNetMdBranchTable(branchNetmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
//To select row from the table
	$j("#branchNetMd" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	
	
	$j('.netmdbranchIdCol').die('click').live('click',function(){
	removeErrors();
	    var netmdbranchId= $j(this).parent().attr('id');
	    if(netmdbranchId!="") {
			$j.cachedScript(constants_ViewNetmdBranchEntryPoint).done(function(script, textStatus) {
				viewNetmdBranch(netmdbranchId,'#passphraseViewTable','#passphrasePrimaryViewTable');
			})
		}	
	});
	
	$j('#branchPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
	var netmdbranchId = getSelectedNetmdBranchId(pgTableName);
	    if(netmdbranchId!="") {
			$j.cachedScript(constants_ViewNetmdBranchEntryPoint).done(function(script, textStatus) {
				viewNetmdBranch(netmdbranchId,'#passphraseViewTable','#passphrasePrimaryViewTable');
			})
		}
	});
	
	$j('#branchPTBContainer #btn_change_ptb_id').die('click').live("click",function() {
	removeErrors();
	  var branchId = getSelectedNetmdBranchId(pgTableName);
	    if(branchId!="") {
			var obj=$j(this);
			createModal(constants_netmdBranchSycSetJson,'netmdBrachSycSetModal');	
			openModalBox(obj,'netmdBrachSycSetModal')
		$j.cachedScript(constant_NetmdBranchSyncSet_Url).done(function(script, textStatus) {
		
		})
		}	
	});
	
	$j('#branchPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
	removeErrors();
	$j('#pageTitle1').hide();
		$j.cachedScript(constant_NetMdEntry_Url).done(function(script, textStatus) {
		})
	});
	
	$j('#branchPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netmdbranchId = getSelectedNetmdBranchId(pgTableName);
		if(netmdbranchId!="") {
			var response = getRequestData('/youNeverWait/ws/ui/superAdmin/deleteNetMdBranch/' + netmdbranchId);
			if(response.success==true){
				showTip("Inactivated Branch Successfully");
				viewNetMdBranchList(branchNetmdListJson,pgTableName);
				//defaultData=getDefaultData();
			}	
		//else
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

//function to get the selected id from table
	function getSelectedNetmdBranchId(pgTableName) {
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