$j.cachedScript(constant_NetLimsBranchFunctions_Url).done(function(script, textStatus) {
})
function branchlist(netlimsId){
var a=getNetLimsData(netlimsId);
$j('#pageTitle').html(constant_BranchList_Msg);	
$j('#pageTitle1').show();
	$j('#pageTitle1').html('['+a.lab.name.toUpperCase()+']');
	var pgBranchList;
	var exp=[]; //For storing the filter expressions
	var pgTableName = "#branch"; // Table showing netlims list
	pgTableContainer = "#branchTableCont"; // Parent container of the netlims list table
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 8;// Interval between pages
	var curPage = 1;//Current selected page
	exp=filterExpression(exp,netlimsId);
	var cururl = '{ "name":"' + "labId" + '","value":"' + netlimsId + '","operator":"' + "eq" + '"}';		
	exp.push(cururl);
	//fill the result
	var branchListJson= getFilterlistUrl(exp,(curPage-1),interval);
	//alert(branchListJson);
	pgBranchList = viewBranchList(branchListJson,pgTableName);
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
			branchListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
		
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			branchListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			branchListJson= getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			branchListJson=getFilterlistUrl(exp,startValue,interval);
			pgBranchList=fillBranchTable(branchListJson,pgTableName);
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
//To select row from the table
	$j("#branch" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	

	$j('.branchIdCol').die('click').live('click',function(){
	removeErrors();
	    var branchId= $j(this).parent().attr('id');
	    if(branchId!="") {
			$j.cachedScript(constants_ViewBranchEntryPoint).done(function(script, textStatus) {
				viewBranch(branchId,'#passphrasenetlimsViewTable');
			})
		}	
	});
	
	$j('#branchPTBContainer #btn_health_ptb_id').die('click').live("click",function() {
	removeErrors();
	  var branchId = getSelectedBranchId(pgTableName);
	    if(branchId!="") {
	    	//var healtMontr=getRequestData('/youNeverWait/ws/ui/superAdmin/viewBranchSystemInfo/'+branchId);
			//alert(JSON.stringify(healtMontr));
	    	var tableobj="#healthMonitorTable";
	    	var obj=$j(this);
		createModal(constants_graphHealthmonitorJson,'graphHealthmonitorModal');	
		openModalBox(obj,'graphHealthmonitorModal');
		$j.cachedScript(constant_Healthmonitor_Url).done(function(script, textStatus) {
})
		//makeDataTable(tableobj);
		//fillhealthmonitortable(tableobj,branchId);
		viewHealthmonitor(branchId);

			/*var healtMontr=getRequestData('/youNeverWait/ws/ui/superAdmin/viewBranchSystemInfo/'+branchId);
			alert(JSON.stringify(healtMontr));
			var obj=$j(this);
		createModal(constants_netlimsHealthmonitorJson,'netlimsHealthmonitorModal');	
		openModalBox(obj,'netlimsHealthmonitorModal')
		viewHealthmonitor(healtMontr);*/
		}	
	});
	
	$j('#branchPTBContainer #btn_change_ptb_id').die('click').live("click",function() {
	removeErrors();
	  var branchId = getSelectedBranchId(pgTableName);
	    if(branchId!="") {
			var obj=$j(this);
			createModal(constants_netlimsBranchSycSetJson,'netlimsBrachSycSetModal');	
			openModalBox(obj,'netlimsBrachSycSetModal')
		$j.cachedScript(constant_NetLimsBranchSyncSet_Url).done(function(script, textStatus) {
		
		})
		}	
	});
	
	$j('#branchPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	removeErrors();
	  var branchId = getSelectedBranchId(pgTableName);
	    if(branchId!="") {
			$j.cachedScript(constants_ViewBranchEntryPoint).done(function(script, textStatus) {
				viewBranch(branchId,'#passphrasenetlimsViewTable');
			})
		}	
	});
	$j('#branchPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
	removeErrors();
		$j('#pageTitle1').hide();
		$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
		})
	});
	
	$j('#branchPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	removeErrors();
		var branchId = getSelectedBranchId(pgTableName);
		var deleteJson=createDeleteJson(branchId,netlimsId);
		if(branchId!="") {
		var response = postdataToServer(constant_newNetLimsBranch_Delete_Url, deleteJson );
		if(response.success==true){
		
			//$j(pgTableName).dataTable().fnDeleteRow($j('#'+branchId).closest('tr')[0]);	
			showTip("Inactivated Branch Successfully");
			viewBranchList(branchListJson,pgTableName);
			//defaultData=getDefaultData();
		}	
		//else
		//alert("error");
		//updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
	$j('#newHealthForm #healthbtnedit').die('click').live('click',function(){
		validateNumber("#newHealthForm #criticalCpuLevel");
		validateNumber("#newHealthForm #criticalMemoryLevel");
		validateNumber("#newHealthForm #criticalHardDiskSpaceLevel");
		validateNumber("#newHealthForm #intervalTime");
		clearNilFields(newHealthForm);
		removeErrors();
		
		$j('#newHealthForm #texfrequencyType').hide();
		$j('#newHealthForm #selfrequencyType').show();
		$j('#newHealthForm #healthbtnedit').hide();
		$j('#newHealthForm #healthbtnDone').show();
		$j('#newHealthForm #healthbtnCancel').show();
			
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').removeClass('newBox'); // make box 
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').removeAttr('readonly');
		$j('#newHealthForm #branchid').addClass('newBox');
		$j('#newHealthForm #branchid').attr('readonly','readonly');
		$j('#newHealthForm #selectfrequencyType').empty();
		fillFrequencyList("#selectfrequencyType");
	});

	$j('#newHealthForm #healthbtnCancel').die('click').live('click',function(){
	removeErrors();
		
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').addClass('newBox');
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').attr('readonly','readonly');	
		$j('#newHealthForm #texfrequencyType').show();
		$j('#newHealthForm #selfrequencyType').hide();
		$j('#newHealthForm #healthbtnDone').hide();
		$j('#newHealthForm #healthbtnCancel').hide();
		$j('#newHealthForm #healthbtnedit').show();
		 var branchId = $j("#newHealthForm #branchid").val();
		 viewHealthmonitor(branchId);

	
	});

	$j('#newHealthForm #healthbtnDone').die('click').live('click',function(){
	removeErrors();
		var response = submitHealthmonitorInfo();
		if(response.success==true) {
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').addClass('newBox');
		$j('#viewBranchHeader input[type=text],#viewBranchHeader textarea').attr('readonly','readonly');	
		$j('#newHealthForm #healthbtnDone').hide();
		$j('#newHealthForm #healthbtnCancel').hide();
		$j('#newHealthForm #healthbtnedit').show();
		$j('#newHealthForm #texfrequencyType').show();
		$j('#newHealthForm #selfrequencyType').hide();
		var branchId = getSelectedBranchId(pgTableName);
		 viewHealthmonitor(branchId);
		showTip("Healthmonitor updated successfully");					
	}	
	else
		updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		

	
	});

}
	function fillhealthmonitortable(tableobj,branchId) {
	$j(tableobj).dataTable().fnClearTable();
	var healtMontr=getRequestData('/youNeverWait/ws/ui/superAdmin/viewBranchSystemInfo/'+branchId);
	//alert(JSON.stringify(healtMontr));
		if(healtMontr.healthMonitorList.length>0) {			
			$j(healtMontr.healthMonitorList).each(function (index, lab) {
				
				var rowData=$j(tableobj).dataTable().fnAddData([lab.createdDateTime,lab.cpuUsage,lab.memoryUsage,lab.hardDiskSpaceUasge]);
				var row=$j(tableobj).dataTable().fnSettings().aoData[rowData].nTr;
				//$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		}
	//}	 
	return healtMontr;
}

	function fillFrequencyList(controlobj)
	{
		
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	}

	function createDeleteJson(branchId,netlimsId){
		var BranchDetails = '{"globalId":'+branchId+',';
			BranchDetails +='"labId":' + netlimsId + '}';
		return BranchDetails;
	}
	
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

	function makeDataTable(tableObj) {
	$j(tableObj).dataTable( {
		"sPaginationType": "full_numbers",
		"bFilter":false,
		"bInfo":false,
		"bPaginate":false,
		"bSort": false,
		"bDestroy": true,
		"bAutoWidth": false,
		"sDom": '<"top"Hip>'
	});
}