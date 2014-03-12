function NetmdServiceImpl () {
		this.setTableValues = function(tableObj, branchResult,key) {
		$j(tableObj).dataTable().fnClearTable();
		if(key=="netmdlist"){
			if(branchResult.netMd) {
				if(branchResult.netMd.length>0) {			
					$j(branchResult.netMd).each(function (index, netMd) {
						var id=netMd.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,netMd.name,netMd.ownerEmail,netMd.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","netmdIdCol Ustyle");
					});	
				}
			}		
	 
		} 
	}
		
		
}
 NetmdServiceImpl.prototype.createNetmd=function (netmdObj) {
	ajaxProcessor.setUrl(constants.CREATENETMDURL);
	return ajaxProcessor.post(netmdObj);
}
NetmdServiceImpl.prototype.updateNetmd=function(netmdObj) {
	//alert(JSON.stringify(netmdObj));
	ajaxProcessor.setUrl(constants.UPDATENETMDURL);
	return ajaxProcessor.post(netmdObj);
}

NetmdServiceImpl.prototype.viewNetmdDetails=function(netmdId) {
	ajaxProcessor.setUrl(constants.VIEWNETMDDETAILURL + netmdId);
	return ajaxProcessor.get();
}

NetmdServiceImpl.prototype.deleteNetmd=function(netmdId) {
	ajaxProcessor.setUrl(constants.DELETENETMDURL + netmdId);
	return ajaxProcessor.get();
}



NetmdServiceImpl.prototype.netmdSyncdata=function(netmdId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETMDURL + netmdId);
	return ajaxProcessor.get();
}
NetmdServiceImpl.prototype.syncNetmd=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETMDSYCURL);
	return ajaxProcessor.post(syncData);
}

