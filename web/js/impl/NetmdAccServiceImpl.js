function NetmdAccServiceImpl () {
		this.setTableValues = function(tableObj, branchResult) {
		$j(tableObj).dataTable().fnClearTable();
			if(branchResult.netmdBranch) {
				if(branchResult.netmdBranch.length>0) {			
					$j(branchResult.netmdBranch).each(function (index, netmdBranch) {
						var id=netmdBranch.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,netmdBranch.name,netmdBranch.mobile,netmdBranch.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","branchNetlimsAccIdCol Ustyle");
						});	
				}
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
 NetmdAccServiceImpl.prototype.createBranchNetmd=function (netmdObj) {
	ajaxProcessor.setUrl(constants.CREATENETMDACCBRANCHURL);
	return ajaxProcessor.post(netmdObj);
}
NetmdAccServiceImpl.prototype.updateAccBranchNetmd=function(netmdObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETMDACCBRCHURL);
	return ajaxProcessor.post(netmdObj);
}

NetmdAccServiceImpl.prototype.viewNetmdBranchDetails=function(netmdBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETMDACCBRCHURL + netmdBrchId);
	return ajaxProcessor.get();
}

NetmdAccServiceImpl.prototype.deleteNetmdBranch=function(netmdBrchId) {
	ajaxProcessor.setUrl(constants.DELETENETMDACCBRANCHURL + netmdBrchId);
	return ajaxProcessor.get();
}

NetmdAccServiceImpl.prototype.changePasswrdNetmd=function(changePasswrd) {
	ajaxProcessor.setUrl(constants.CHANGEPASSWORDNETMDURL);
	return ajaxProcessor.post(changePasswrd);
}

NetmdAccServiceImpl.prototype.syncdata=function(branchId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETLIMSACCURL + branchId);
	return ajaxProcessor.get();
}
NetmdAccServiceImpl.prototype.syncBranchNetlims=function(syncData) {
	ajaxProcessor.setUrl(constants.SETACCBRACHSYCURL);
	return ajaxProcessor.post(syncData);
}

NetmdAccServiceImpl.prototype.BranchOrderlistNetlims=function(orderlistData) {
	ajaxProcessor.setUrl(constants.GETORDERLISTURL);
	return ajaxProcessor.post(orderlistData);
}