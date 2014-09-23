function NetlimsbranchServiceImpl () {
		this.setTableValues = function(tableObj, branchResult,key) {
		$j(tableObj).dataTable().fnClearTable();
		if(key=="branchlist"){
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
	 }else{
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
	 
		} }
		} 
		
		
		
		/* this.setTableValueBranchOrderList = function(tableObj, branchResult) {
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
	 
		}  */
}
NetlimsbranchServiceImpl.prototype.createBranchNetlims=function (netlimsObj) {
	ajaxProcessor.setUrl(constants.CREATENETLIMSBRANCHURL);
	return ajaxProcessor.post(netlimsObj);
}
NetlimsbranchServiceImpl.prototype.findOrderCount=function (param) {
	ajaxProcessor.setUrl(constants.FINDORDERCOUNTURL);
	return ajaxProcessor.post(param);
}
NetlimsbranchServiceImpl.prototype.updateAccBranchNetlims=function(netlimsObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETLIMSBRCHURL);
	return ajaxProcessor.post(netlimsObj);
}

NetlimsbranchServiceImpl.prototype.viewNetlimsBranchDetails=function(netlimsBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETLIMSBRCHURL + netlimsBrchId);
	return ajaxProcessor.get();
}

NetlimsbranchServiceImpl.prototype.deleteNetlimsBranch=function(netlimsObj) {
	ajaxProcessor.setUrl(constants.DELETENETLIMSLABBRANCHURL);
	return ajaxProcessor.post(netlimsObj);
}


NetlimsbranchServiceImpl.prototype.syncdata=function(branchId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETLIMSBRCHURL + branchId);
	return ajaxProcessor.get();
}
NetlimsbranchServiceImpl.prototype.syncBranchNetlims=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETLIMSBRACHSYCURL);
	return ajaxProcessor.post(syncData);
}

NetlimsbranchServiceImpl.prototype.clearMacNetlimsBranch=function(clearMac) {
	ajaxProcessor.setUrl(constants.SETNETLIMSBRACHCLEARMACURL);
	return ajaxProcessor.post(clearMac);
}

NetlimsbranchServiceImpl.prototype.viewHealthmonitorDetails=function(branchId) {
	ajaxProcessor.setUrl(constants.VIEWHEALTHMONITORETLIMSBRCHURL + branchId);
	return ajaxProcessor.get();
}

NetlimsbranchServiceImpl.prototype.updateHealthmonitorDetails=function(healthmonitorPass) {
	ajaxProcessor.setUrl(constants.UPDATEHEALTHMONITORURL);
	return ajaxProcessor.post(healthmonitorPass);
}

NetlimsbranchServiceImpl.prototype.BranchOrderlistNetlims=function(orderlistData) {
	ajaxProcessor.setUrl(constants.GETNETLIMSBRANCHORDERLISTURL);
	return ajaxProcessor.post(orderlistData);
}