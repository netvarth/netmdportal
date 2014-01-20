function NetlimsServiceImpl () {
		this.setTableValues = function(tableObj, netlimsResult) {
		$j(tableObj).dataTable().fnClearTable();
			if(netlimsResult.lab) {
				if(netlimsResult.lab.length>0) {			
					$j(netlimsResult.lab).each(function (index, lab) {
						var id=lab.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,lab.name,lab.ownerEmail,lab.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
						});	
				}
			}		
	 
		} 
		
		this.setviewordersnetlimsListTable = function(tableObj, netlimsorderResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(netlimsorderResult.branchOrders.length>0) {			
			$j(netlimsorderResult.branchOrders).each(function (index, lab) {
				var id=lab.branchId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,lab.branchName,lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		}
	 
		} 
		
		this.setTableValueBranchOrderList = function(tableObj, branchResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(branchResult.branchOrders.length>0) {			
				$j(branchResult.branchOrders).each(function (index, lab) {
					var id=lab.branchId;
					var rowData=$j(tableObj).dataTable().fnAddData([lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);	
					//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
					});	
		}
	 
		} 
}
 NetlimsServiceImpl.prototype.createNetlims=function (netlimsObj) {
	ajaxProcessor.setUrl(constants.CREATENETLIMSURL);
	return ajaxProcessor.post(netlimsObj);
}
NetlimsServiceImpl.prototype.updateNetlims=function(netlimsObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETLIMSURL);
	return ajaxProcessor.post(netlimsObj);
}

NetlimsServiceImpl.prototype.viewNetlimsDetails=function(netlimsId) {
	ajaxProcessor.setUrl(constants.VIEWNETLIMSDETAILURL + netlimsId);
	return ajaxProcessor.get();
}

NetlimsServiceImpl.prototype.deleteNetlims=function(netlimsId) {
	ajaxProcessor.setUrl(constants.DELETENETLIMSURL + netlimsId);
	return ajaxProcessor.get();
}

NetlimsServiceImpl.prototype.viewordersnetlimsList=function(netlimsId) {
	ajaxProcessor.setUrl(constants.VIEWNETLIMSORDERLISTURL + netlimsId);
	return ajaxProcessor.get();
}

NetlimsServiceImpl.prototype.netlimsSyncdata=function(netlimsId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETLIMSURL + netlimsId);
	return ajaxProcessor.get();
}
NetlimsServiceImpl.prototype.syncNetlims=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETLIMSSYCURL);
	return ajaxProcessor.post(syncData);
}

