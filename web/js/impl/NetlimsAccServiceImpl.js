function NetlimsAccServiceImpl () {
		this.setTableValues = function(tableObj, branchResult) {
		$j(tableObj).dataTable().fnClearTable();
			if(branchResult.branch) {
				if(branchResult.branch.length>0) {			
					$j(branchResult.branch).each(function (index, branch) {
						var id=branch.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,branch.name,branch.mobile,branch.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","branchNetlimsAccIdCol Ustyle");
						});	
				}
			}		 
	 
		} 
		
		this.setTableValuesOrderList = function(tableObj, branchResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(branchResult.branchOrders.length>0) {			
			$j(branchResult.branchOrders).each(function (index, lab) {
				var id=lab.branchId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,lab.branchName,lab.orderDate,lab.totalOrders,lab.netAmount,lab.paidAmount,lab.lastOrderdTime]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		} 
	 
		} 
}
 NetlimsAccServiceImpl.prototype.createBranchNetlims=function (netlimsObj) {
	ajaxProcessor.setUrl(constants.CREATENETLIMSACCBRANCHURL);
	return ajaxProcessor.post(netlimsObj);
}
NetlimsAccServiceImpl.prototype.updateAccBranchNetlims=function(netlimsObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETLIMSACCBRCHURL);
	return ajaxProcessor.post(netlimsObj);
}

NetlimsAccServiceImpl.prototype.viewNetlimsBranchDetails=function(netlimsBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETLIMSACCBRCHURL + netlimsBrchId);
	return ajaxProcessor.get();
}

NetlimsAccServiceImpl.prototype.deleteNetlimsBranch=function(netlimsObj) {
	ajaxProcessor.setUrl(constants.DELETENETLIMSBRANCHURL);
	return ajaxProcessor.post(netlimsObj);
}

NetlimsAccServiceImpl.prototype.changePasswrdNetlims=function(changePasswrd) {
	ajaxProcessor.setUrl(constants.CHANGEPASSWORDURL);
	return ajaxProcessor.post(changePasswrd);
}
NetlimsAccServiceImpl.prototype.orderTypeNetlims=function(netlimsBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETLIMSORDERTYPEURL + netlimsBrchId);
	return ajaxProcessor.get();
}
NetlimsAccServiceImpl.prototype.setOrderTypeNetlims=function(orderTypeJson) {
	ajaxProcessor.setUrl(constants.VIEWNETLIMSSETORDERTYPEURL);
	return ajaxProcessor.post(orderTypeJson);
}