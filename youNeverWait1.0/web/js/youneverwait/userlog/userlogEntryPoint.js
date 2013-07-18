$j('#pageTitle').html(constant_UserLogList_Msg);
$j.cachedScript(constant_UserLogFunctions_Url).done(function(script, textStatus) {
})
$j(document).ready(function() {
		var pgUserLogList;
		var exp=[]; //For storing the filter expressions
		var pgTableName = "#userlog"; // Table showing netlims list
		pgTableContainer = "#userlogTableCont"; // Parent container of the netlims list table
		var maxRecords=0; // Total number of records
		var maxPages = 0; // Total number of pages
		var interval = 10;// Interval between pages
		var curPage = 1;//Current selected page

		//fill the result
		var userlogListJson= getFilterlistUrl(exp,(curPage-1),interval);
		pgUserLogList = viewLogList(userlogListJson,pgTableName);
		if(pgUserLogList.count)
			maxRecords = pgUserLogList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		//setPaginationFields(curPage, maxPages, pgTableContainer);
		
	
	$j('#filter').die('click').live('click',function(){
		removeErrors();
		var filterArray = $j('#user-filter-toolbar .button_filter');
		$j(filterArray).each(function(){
			var element = $j(this);
			element.removeClass('button_filter');
			element.removeAttr('selected');
			$j('#user-filter-wb').hide();
		});
	});
	
	$j('#user-filter-toolbar  a:not(:selected)').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#txt'+curObjName).show();
		$j('#select'+curObjName).show();
		setReportFilterValues(curObjName);
	});
	//if the user deselect the filter option
	$j('#user-filter-toolbar a[selected]').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#txt'+curObjName).hide();
		$j('#select'+curObjName).hide();
		$j('#txt'+curObjName).val("");
		$j('#select'+curObjName).val("");
		$j('#user-filter-toolbar #btnGo').trigger('click');
	});
	
	$j('#user-filter-toolbar #btnGo').die('click').click(function(){
		removeErrors();
		exp=[];
		$j('#user-filter-toolbar a[selected]').each(function(){
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
		userlogListJson= getFilterlistUrl(exp,startValue,interval);
		pgUserList=fillUserLogTable(userlogListJson,pgTableName);
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
			userlogListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillUserLogTable(userlogListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			userlogListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillUserLogTable(userlogListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			userlogListJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillUserLogTable(userlogListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			userlogListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillUserLogTable(userlogListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
		
});	//document.ready ends here