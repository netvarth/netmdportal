$j('#pageTitle').html(constants.SYNCLOGLISTMSG);
$j.cachedScript(constants.SYNCFUNCTIONSURL).done(function(script, textStatus) {
})
$j(document).ready(function() {
		var pgSyncLogList;
		var exp=[]; //For storing the filter expressions
		var pgTableName = "#synclog"; // Table showing netlims list
		pgTableContainer = "#synclogTableCont"; // Parent container of the netlims list table
		var maxRecords=0; // Total number of records
		var maxPages = 0; // Total number of pages
		var interval = 10;// Interval between pages
		var curPage = 1;//Current selected page

		//fill the result
		var syncLogListJson= getFilterlistUrl(exp,(curPage-1),interval);
		pgSyncLogList = viewLogList(syncLogListJson,pgTableName);
		if(pgSyncLogList.count)
			maxRecords = pgSyncLogList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		setPaginationFields(curPage, maxPages, pgTableContainer);
		
	
	$j('#filter').die('click').live('click',function(){
		removeErrors();
		var filterArray = $j('#sync-filter-toolbar .button_filter');
		$j(filterArray).each(function(){
			var element = $j(this);
			element.removeClass('button_filter');
			element.removeAttr('selected');
			$j('#sync-filter-wb').hide();
		});
	});
	
	$j('#sync-filter-toolbar  a:not(:selected)').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#txt'+curObjName).show();
		$j('#select'+curObjName).show();
		setReportFilterValues(curObjName);
	});
	//if the sync deselect the filter option
	$j('#sync-filter-toolbar a[selected]').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#txt'+curObjName).hide();
		$j('#select'+curObjName).hide();
		$j('#txt'+curObjName).val("");
		$j('#select'+curObjName).val("");
		$j('#sync-filter-toolbar #btnGo').trigger('click');
	});
	
	$j('#sync-filter-toolbar #btnGo').die('click').click(function(){
		removeErrors();
		exp=[];
		$j('#sync-filter-toolbar a[selected]').each(function(){
			var selName=$j(this).attr('name');
			if(selName=='applicationName')
			var selTextValue=$j("#select"+ selName).val();
			else
			var selTextValue=$j("#txt"+ selName).val();
			//if((selName=='loginTime')||(selName=='logoutTime')||(selName=='actionTime'))
			//exp.push('{"name":"' + selName + '","value":' + selTextValue+ ',"operator":"eq"}');
			//else
			exp.push('{"name":"' + selName + '","value":"' + selTextValue + '","operator":"eq"}');
			
		});	
		startValue=0;
		curPage =1;
		syncLogListJson= getFilterlistUrl(exp,startValue,interval);
		pgUserList=fillSyncLogTable(syncLogListJson,pgTableName);
		if(pgUserList.count)
			maxRecords = pgUserList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		setPaginationFields(curPage, maxPages, pgTableContainer);	
	});
	
	
	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			syncLogListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillSyncLogTable(syncLogListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			syncLogListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillSyncLogTable(syncLogListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			syncLogListJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillSyncLogTable(syncLogListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			syncLogListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillSyncLogTable(syncLogListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j('#syncLogPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
	removeErrors();
	$j("#leftPaneSettings").trigger('click');
	$j('#filter').hide();
	$j('#syncLogPTBContainer').hide();
	$j('#sync-filter-toolbar').hide();

	})
		
});	//document.ready ends here