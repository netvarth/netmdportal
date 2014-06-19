

function viewLogList(userlogListJson,tableObj) {
	var userlogTable=setUserLogTableStructure();
	$j('#tabs-1').html(userlogTable.html());
	setCustomDataTable(tableObj);
	loadUserLogPageToolBar();
	loadUserlogFilterToolBar();
	pgUserLogList=fillUserLogTable(userlogListJson,tableObj);
	return pgUserLogList;
}

function setUserLogTableStructure() {
	//create the table structure for doctor Table
	var tblData = getRequestData('/youNeverWait/json/list/userlogTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);   
		});
	return 	boxDiv;
}

function loadUserLogPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/youNeverWait/json/toolbars/logPageToolBar.json');
	var ptbContainer = $j('<div id="logPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	//alert(JSON.stringify(ptb));
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function loadUserlogFilterToolBar(){
	var ftbdata =getRequestData('/youNeverWait/json/toolbars/userlogFilterToolBar.json');
	var ftb = new FilterToolBar(ftbdata);
	var usrFilterCont=$j('<div id="usr-filter-cont">');
	var usrFilterTB = $j('<div id="user-filter-toolbar" class="box-content"/>');
	usrFilterTB.append(ftb.result); // Add the filter tool bar to Div
	usrFilterCont.append(usrFilterTB);
	var usrFilterWB = $j('<div id="user-filter-wb" style="display:none;padding:0 0 0 0px"/>');
	var usrFilterWBInner = $j('<div id="user-filter-wb-inner" style="float:left; width:40%"/>');
	usrFilterWB.append(usrFilterWBInner);
	var usrFilterSubBtn = $j('<input type="button" value="Go", id="usr_btnfltrSubmit">');
	usrFilterWB.append(usrFilterSubBtn);
	usrFilterCont.append(usrFilterWB);
	$j('#filterToolBar-Container').html(usrFilterCont);
	$j('#filter').show();
	$j('#filterWorkBench').hide();	
}

function fillUserLogTable(userlogListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	//alert("inp"+userlogListJson);
	var userlogListJson=postdataToServer("/youNeverWait/ws/ui/superAdmin/userLogList", userlogListJson );
	//alert(JSON.stringify(userlogListJson));
	if(userlogListJson.log) {
		if(userlogListJson.log.length>0) {			
			$j(userlogListJson.log).each(function (index, log) {
				var id=log.id;
				if(log.userType=='Nil')
				var rowData=$j(tableObj).dataTable().fnAddData([id,log.userName,"",log.loginTime,log.logoutTime,log.applicationName,log.actionName,log.actionDate,log.ipAddress]);
				else
				var rowData=$j(tableObj).dataTable().fnAddData([id,log.userName,log.userType,log.loginTime,log.logoutTime,log.applicationName,log.actionName,log.actionDate,log.ipAddress]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				$j(row).children("td:nth-child(1)").attr("class","logIdCol Ustyle");
				});	
		}
	}		
	return userlogListJson;
	
}