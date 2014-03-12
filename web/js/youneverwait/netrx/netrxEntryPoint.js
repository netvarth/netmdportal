$j('#pageTitle').html(constant_NetRxList_Msg);
$j.cachedScript(constant_NetRxFunctions_Url).done(function(script, textStatus) {
})

$j(document).ready(function() {
		var pgNetrxList;
		var exp=[]; //For storing the filter expressions
		var pgTableName = "#netrx"; // Table showing netlims list
		pgTableContainer = "#netrxTableCont"; // Parent container of the netlims list table
		var maxRecords=0; // Total number of records
		var maxPages = 0; // Total number of pages
		var interval = 8;// Interval between pages
		var curPage = 1;//Current selected page

		//fill the result
		var netrxListJson= getFilterlistUrl(exp,(curPage-1),interval);
		pgNetrxList = viewNetRxList(netrxListJson,pgTableName);
		if(pgNetrxList.count)
			maxRecords = pgNetrxList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		setPaginationFields(curPage, maxPages, pgTableContainer);	
			
				
	$j('#netrxPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
	removeErrors();
		var obj=$j(this);
		createModal(constants_newNetRxJson,'netrxModal');	
		openModalBox(obj,'netrxModal')
		$j.cachedScript(constants_newNetRx).done(function(script, textStatus) {
		})
	});	
	
	$j('#netrxPTBContainer #btn_newbranch_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netrxId= getSelectedNetRxId(pgTableName);
		if(netrxId!="") {
		var obj=$j(this);
		createModal(constants_newNetRXBranchJson,'netrxbranchModal');	
		openModalBox(obj,'netrxbranchModal')
			$j.cachedScript(constants_newNetRxBranch).done(function(script, textStatus) {
				createbranch(netrxId);
			})
		}
	});
		
	//To change  (listing of branches)
	$j('#netrxPTBContainer #btn_brannchlist_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netrxId= getSelectedNetRxId(pgTableName);
		if(netrxId!="") {
			$j.cachedScript(constant_NetRxBranchEntry_Url).done(function(script, textStatus) {
				branchlist(netrxId);
			})
		}
	});
	
	$j('#netrxPTBContainer #btn_change_ptb_id').die('click').live("click",function() {
	removeErrors();
	//alert("list");
	var netrxId= getSelectedNetRxId(pgTableName);
		if(netrxId!="") {
		var obj=$j(this);
		createModal(constants_netrxSycSetJson,'netrxSycSetModal');	
		openModalBox(obj,'netrxSycSetModal')
		$j.cachedScript(constant_NetrxSyncSet_Url).done(function(script, textStatus) {
		getNetrxId(netrxId)
		})
		}
	});
		
	//To select row from the table
	$j("#netrx" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	
		
			
	$j('.netrxIdCol').die('click').live('click',function(){
	removeErrors();
		var netrxId= $j(this).parent().attr('id');
		if(netrxId!="") {
			$j.cachedScript(constants_ViewRxEntryPoint).done(function(script, textStatus) {
				viewNetRx(netrxId); 
			})
		}	
	});
		
		
	$j('#netrxPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netrxId = getSelectedNetRxId(pgTableName);
		if(netrxId!="") {
			var response = getRequestData('/youNeverWait/ws/ui/superAdmin/deleteNetRx/' + netrxId);
			if(response.success==true){
				showTip("Inactivated Netrx Successfully");
				viewNetRxList(netrxListJson,pgTableName);
				//$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
				//})
				//defaultData=getDefaultData();
			}	
			else
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
		
		
	$j('#netrxPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
		var netrxId= getSelectedNetRxId(pgTableName);
		if(netrxId!="") {
			$j.cachedScript(constants_ViewRxEntryPoint).done(function(script, textStatus) {
				viewNetRx(netrxId);
			})
		}
	});

		
	//function to get the selected id from table
		
	function getSelectedNetRxId(pgTableName) {
		var netrxId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selNetRx = $j(pgTableName + ' tbody tr[selected]');
			if(selNetRx.length==0){	
				updateTipsNew("select atleast one netrx",$j('#errorDivData'),$j('#errorDivHeader'));
			} 
			else if(selNetRx.length>1) 
				updateTipsNew("select only one netrx",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				netrxId=selNetRx.attr('id');
		}		
			return netrxId;
	}
	
	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			netrxListJson=getFilterlistUrl(exp,startValue,interval);
			pgNetrxList=fillNetRxTable(netrxListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			netrxListJson=getFilterlistUrl(exp,startValue,interval);
			pgNetrxList=fillNetRxTable(netrxListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			netrxListJson= getFilterlistUrl(exp,startValue,interval);
			pgNetrxList=fillNetRxTable(netrxListJson,pgTableName);

			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			netrxListJson=getFilterlistUrl(exp,startValue,interval);
			pgNetrxList=fillNetRxTable(netrxListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});		
			
});	//document.ready ends here			