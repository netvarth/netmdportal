function NetrxServiceImpl () {
		this.setTableValues = function(tableObj, netrxResult) {
		$j(tableObj).dataTable().fnClearTable();
			if(netrxResult.netRx) {
				if(netrxResult.netRx.length>0) {			
					$j(netrxResult.netRx).each(function (index, netRx) {
					var id=netRx.globalId;
					var rowData=$j(tableObj).dataTable().fnAddData([id,netRx.name,netRx.ownerEmail,netRx.status]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);	
					$j(row).children("td:nth-child(1)").attr("class","netrxIdCol Ustyle");
					});	
				}
			}		
	 
		} 
		
		this.setviewordersnetlimsListTable = function(tableObj, netlimsorderResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(netlimsorderResult.branchOrders.length>0) {			
			$j(netlimsorderResult.branchOrders).each(function (index, netMd) {
				var id=netMd.branchId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,netMd.branchName,netMd.orderDate,netMd.totalOrders,netMd.netAmount,netMd.paidAmount,netMd.lastOrderdTime]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		}
	 
		} 
		
		
}
 NetrxServiceImpl.prototype.createNetrx=function (netrxObj) {
	ajaxProcessor.setUrl(constants.CREATENETRXURL);
	return ajaxProcessor.post(netrxObj);
}
NetrxServiceImpl.prototype.updateNetrx=function(netrxObj) {
	//alert(JSON.stringify(netrxObj));
	ajaxProcessor.setUrl(constants.UPDATENETRXURL);
	return ajaxProcessor.post(netrxObj);
}

NetrxServiceImpl.prototype.viewNetrxDetails=function(netrxId) {
	ajaxProcessor.setUrl(constants.VIEWNETRXDETAILURL + netrxId);
	return ajaxProcessor.get();
}

NetrxServiceImpl.prototype.deleteNetrx=function(netrxId) {
	ajaxProcessor.setUrl(constants.DELETENETRXURL + netrxId);
	return ajaxProcessor.get();
}



NetrxServiceImpl.prototype.netrxSyncdata=function(netrxId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETRXURL + netrxId);
	return ajaxProcessor.get();
}
NetrxServiceImpl.prototype.syncNetrx=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETRXSYCURL);
	return ajaxProcessor.post(syncData);
}

