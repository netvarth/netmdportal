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
		
		this.setTableValueBranchOrderList = function(tableObj, branchResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(branchResult.branchOrders.length>0) {			
				$j(branchResult.branchOrders).each(function (index, netMd) {
					var id=netMd.branchId;
					var rowData=$j(tableObj).dataTable().fnAddData([netMd.orderDate,netMd.totalOrders,netMd.netAmount,netMd.paidAmount,netMd.lastOrderdTime]);
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
NetrxServiceImpl.prototype.updateNetmd=function(netmdObj) {
	//alert(JSON.stringify(netmdObj));
	ajaxProcessor.setUrl(constants.UPDATENETMDURL);
	return ajaxProcessor.post(netmdObj);
}

NetrxServiceImpl.prototype.viewNetmdDetails=function(netmdId) {
	ajaxProcessor.setUrl(constants.VIEWNETMDDETAILURL + netmdId);
	return ajaxProcessor.get();
}

NetrxServiceImpl.prototype.deleteNetmd=function(netmdId) {
	ajaxProcessor.setUrl(constants.DELETENETMDURL + netmdId);
	return ajaxProcessor.get();
}



NetrxServiceImpl.prototype.netmdSyncdata=function(netmdId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETMDURL + netmdId);
	return ajaxProcessor.get();
}
NetrxServiceImpl.prototype.syncNetmd=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETMDSYCURL);
	return ajaxProcessor.post(syncData);
}

