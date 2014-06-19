$j('#pageTitle').html(constant_NetLimsList_Msg);
$j.cachedScript(constant_NetLimsFunctions_Url).done(function(script, textStatus) {
})
$j(document).ready(function() {
	var pgNetLimsList;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#netlims"; // Table showing netlims list
	pgTableContainer = "#netlimsTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page

	//fill the result
	var netlimsListJson= getFilterlistUrl(exp,(curPage-1),interval);
	pgNetlimsList = viewNetLimsList(netlimsListJson,pgTableName);
	if(pgNetlimsList.count)
		maxRecords = pgNetlimsList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	$j('#netlimsPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
	removeErrors();
		var obj=$j(this);
		createModal(constants_newNetLimsJson,'netlimsModal');	
		openModalBox(obj,'netlimsModal')
		$j.cachedScript(constants_newNetLims).done(function(script, textStatus) {
		})
	});
	
	$j('#netlimsPTBContainer #btn_newbranch_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netlimsId= getSelectedNetLimsId(pgTableName);
		if(netlimsId!="") {
		var obj=$j(this);
		createModal(constants_newNetBranchJson,'netlimsBranchModal');	
		openModalBox(obj,'netlimsBranchModal')
		$j.cachedScript(constants_newNetLimsBranch).done(function(script, textStatus) {
		createbranch(netlimsId);
		})
		}
	});
	
	
	//To change  (listing of branches)
	$j('#netlimsPTBContainer #btn_brannchlist_ptb_id').die('click').live("click",function() {
	removeErrors();
	//alert("list");
	var netlimsId= getSelectedNetLimsId(pgTableName);
		if(netlimsId!="") {
		$j.cachedScript(constant_NetLimsBranchEntry_Url).done(function(script, textStatus) {
		branchlist(netlimsId);
		})
		}
	});
	
	$j('#netlimsPTBContainer #btn_change_ptb_id').die('click').live("click",function() {
	removeErrors();
	//alert("list");
	var netlimsId= getSelectedNetLimsId(pgTableName);
		if(netlimsId!="") {
		var obj=$j(this);
		createModal(constants_netlimsSycSetJson,'netlimsSycSetModal');	
		openModalBox(obj,'netlimsSycSetModal')
		$j.cachedScript(constant_NetLimsSyncSet_Url).done(function(script, textStatus) {
		getNetlimsId(netlimsId);
		})
		}
	});
	
	//To select row from the table
	$j("#netlims" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	

	$j('.netlimsIdCol').die('click').live('click',function(){
	removeErrors();
	    var netlimsId= $j(this).parent().attr('id');
	    if(netlimsId!="") {
			$j.cachedScript(constants_ViewNetLimsEntryPoint).done(function(script, textStatus) {
				viewNetLims(netlimsId);
			})
		}	
	});
	
	$j('#netlimsPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netlimsId= getSelectedNetLimsId(pgTableName);
		if(netlimsId!="") {
				//$j('#referral-filter-wb').hide();
				$j.cachedScript(constants_ViewNetLimsEntryPoint).done(function(script, textStatus) {
					viewNetLims(netlimsId);
			})
		}
	});

	$j('#netlimsPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netlimsId = getSelectedNetLimsId(pgTableName);
		if(netlimsId!="") {
		var response = getRequestData('/youNeverWait/ws/ui/superAdmin/deleteLab/' + netlimsId);
		//alert(JSON.stringify(response));
		if(response.success==true){
			//$j(pgTableName).dataTable().fnDeleteRow($j('#'+netlimsId).closest('tr')[0]);	
			showTip("Inactivated Netlims Successfully");
			viewNetLimsList(netlimsListJson,pgTableName);
			//$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
			//})
			//defaultData=getDefaultData();
		}	
		else
		//alert("error");
		updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
	
	//function to get the selected id from table
	function getSelectedNetLimsId(pgTableName) {
		var netlimsId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selNetLims = $j(pgTableName + ' tbody tr[selected]');
			if(selNetLims.length==0){	
				updateTipsNew("Select atleast one netlims",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selNetLims.length>1) 
				updateTipsNew("Select only one netlims",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				netlimsId=selNetLims.attr('id');
		}		
		return netlimsId;
	}
	
	
	
	
	
$j(pgTableContainer +' #next').die('click').click(function() {
	if(curPage!=maxPages && curPage<maxPages) {
	curPage+=1;
	var startValue = interval*(curPage-1);
	netlimsListJson=getFilterlistUrl(exp,startValue,interval);
pgNetLimsList=fillNetLimsTable(netlimsListJson,pgTableName);
	setPaginationFields(curPage, maxPages,pgTableContainer);
}
});
$j(pgTableContainer +' #previous').die('click').click(function() {
	if(curPage!=1) {
	curPage-=1;
	var startValue = interval*(curPage-1);
	netlimsListJson=getFilterlistUrl(exp,startValue,interval);
pgNetLimsList=fillNetLimsTable(netlimsListJson,pgTableName);
	setPaginationFields(curPage, maxPages,pgTableContainer);
}
});
$j(pgTableContainer +' #first').die('click').click(function() {
if(curPage!=1) {
curPage=1;
startValue = 0;
netlimsListJson= getFilterlistUrl(exp,startValue,interval);
pgNetLimsList=fillNetLimsTable(netlimsListJson,pgTableName);

setPaginationFields(curPage, maxPages, pgTableContainer);
}
});
$j(pgTableContainer +' #last').die('click').click(function() {
if(curPage!=maxPages && curPage<maxPages) {
curPage =maxPages;
startValue = (curPage-1)*interval;
netlimsListJson=getFilterlistUrl(exp,startValue,interval);
pgNetLimsList=fillNetLimsTable(netlimsListJson,pgTableName);
setPaginationFields(curPage, maxPages, pgTableContainer);
}
});
			
});	//document.ready ends here