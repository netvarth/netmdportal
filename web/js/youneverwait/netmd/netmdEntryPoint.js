$j('#pageTitle').html(constant_NetMdList_Msg);
$j.cachedScript(constant_NetMdFunctions_Url).done(function(script, textStatus) {
})
$j(document).ready(function() {
		var pgNetMdList;
		var exp=[]; //For storing the filter expressions
		var pgTableName = "#netmd"; // Table showing netlims list
		pgTableContainer = "#netmdTableCont"; // Parent container of the netlims list table
		var maxRecords=0; // Total number of records
		var maxPages = 0; // Total number of pages
		var interval = 8;// Interval between pages
		var curPage = 1;//Current selected page

		//fill the result
		var netmdListJson= getFilterlistUrl(exp,(curPage-1),interval);
		pgNetmdList = viewNetMdList(netmdListJson,pgTableName);
		if(pgNetmdList.count)
			maxRecords = pgNetmdList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		setPaginationFields(curPage, maxPages, pgTableContainer);
		
			
	$j('#netmdPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
	removeErrors();
		var obj=$j(this);
		createModal(constants_newNetMdJson,'netmdModal');	
		openModalBox(obj,'netmdModal')
		$j.cachedScript(constants_newNetMd).done(function(script, textStatus) {
		})
	});
			
	$j('#netmdPTBContainer #btn_newbranch_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netmdId= getSelectedNetMdId(pgTableName);
		if(netmdId!="") {
		var obj=$j(this);
		createModal(constants_newNetMDBranchJson,'netmdbranchModal');	
		openModalBox(obj,'netmdbranchModal')
			$j.cachedScript(constants_newNetMdBranch).done(function(script, textStatus) {
				createbranch(netmdId);
			})
		}
	});
		
	//To change  (listing of branches)
	$j('#netmdPTBContainer #btn_brannchlist_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netmdId= getSelectedNetMdId(pgTableName);
		if(netmdId!="") {
			$j.cachedScript(constant_NetMdBranchEntry_Url).done(function(script, textStatus) {
				branchlist(netmdId);
			})
		}
	});
	
	$j('#netmdPTBContainer #btn_change_ptb_id').die('click').live("click",function() {
	removeErrors();
	//alert("list");
	var netmdId= getSelectedNetMdId(pgTableName);
		if(netmdId!="") {
		var obj=$j(this);
		createModal(constants_netmdSycSetJson,'netmdSycSetModal');	
		openModalBox(obj,'netmdSycSetModal')
		$j.cachedScript(constant_NetmdSyncSet_Url).done(function(script, textStatus) {
		
		})
		}
	});
		
	//To select row from the table
	$j("#netmd" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	
		
			
	$j('.netmdIdCol').die('click').live('click',function(){
	removeErrors();
		var netmdId= $j(this).parent().attr('id');
		if(netmdId!="") {
			$j.cachedScript(constants_ViewMdEntryPoint).done(function(script, textStatus) {
				viewNetMd(netmdId); 
			})
		}	
	});
		
		
	$j('#netmdPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var netmdId = getSelectedNetMdId(pgTableName);
		if(netmdId!="") {
			var response = getRequestData('/youNeverWait/ws/ui/superAdmin/deleteNetMd/' + netmdId);
			if(response.success==true){
				showTip("Inactivated Netmd Successfully");
				viewNetMdList(netmdListJson,pgTableName);
				//$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
				//})
				//defaultData=getDefaultData();
			}	
			else
			updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
		
		
	$j('#netmdPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
		var netmdId= getSelectedNetMdId(pgTableName);
		if(netmdId!="") {
			$j.cachedScript(constants_ViewMdEntryPoint).done(function(script, textStatus) {
				viewNetMd(netmdId);
			})
		}
	});

		
	//function to get the selected id from table
		
	function getSelectedNetMdId(pgTableName) {
		var netmdId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selNetMd = $j(pgTableName + ' tbody tr[selected]');
			if(selNetMd.length==0){	
				updateTipsNew("select atleast one netmd",$j('#errorDivData'),$j('#errorDivHeader'));
			} 
			else if(selNetMd.length>1) 
				updateTipsNew("select only one netmd",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				netmdId=selNetMd.attr('id');
		}		
			return netmdId;
	}


	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			netmdListJson=getFilterlistUrl(exp,startValue,interval);
			pgNetmdList=fillNetMdTable(netmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			netmdListJson=getFilterlistUrl(exp,startValue,interval);
			pgNetmdList=fillNetMdTable(netmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			netmdListJson= getFilterlistUrl(exp,startValue,interval);
			pgNetmdList=fillNetMdTable(netmdListJson,pgTableName);

			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			netmdListJson=getFilterlistUrl(exp,startValue,interval);
			pgNetmdList=fillNetMdTable(netmdListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});	

	
});	//document.ready ends here