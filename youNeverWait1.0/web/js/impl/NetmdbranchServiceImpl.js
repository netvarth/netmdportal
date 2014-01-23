function NetmdbranchServiceImpl () {
		this.setTableValues = function(tableObj, branchResult,key) {
		$j(tableObj).dataTable().fnClearTable();
			if(key=="branchlist"){
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
	 
		} else
		{
			if(branchResult.branchBillList.length>0) {			
					$j(branchResult.branchBillList).each(function (index, netmd) {
						var id=netmd.branchId;
						var rowData=$j(tableObj).dataTable().fnAddData([netmd.uid,netmd.orderDate,netmd.patientName,netmd.payStatus,netmd.billAmount,netmd.amountPaid]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
						});	
				}
			}
		}
		
		
		
}
 NetmdbranchServiceImpl.prototype.createBranchNetmd=function (netmdObj) {
	ajaxProcessor.setUrl(constants.CREATENETMDBRANCHURL);
	return ajaxProcessor.post(netmdObj);
}
NetmdbranchServiceImpl.prototype.updateAccBranchNetmd=function(netmdObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETMDBRCHURL);
	return ajaxProcessor.post(netmdObj);
}

NetmdbranchServiceImpl.prototype.viewNetmdBranchDetails=function(netmdBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETMDBRCHURL + netmdBrchId);
	return ajaxProcessor.get();
}

NetmdbranchServiceImpl.prototype.deleteNetmdBranch=function(netmdBrchId) {
	ajaxProcessor.setUrl(constants.DELETENETMDBRANCHURL + netmdBrchId);
	return ajaxProcessor.get();
}



NetmdbranchServiceImpl.prototype.syncdata=function(branchId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETMDACCURL + branchId);
	return ajaxProcessor.get();
}
NetmdbranchServiceImpl.prototype.syncBranchNetmd=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETMDBRACHSYCURL);
	return ajaxProcessor.post(syncData);
}
NetmdbranchServiceImpl.prototype.clearMacNetmdBranch=function(clearMac) {
	ajaxProcessor.setUrl(constants.SETNETMDBRACHCLEARMACURL);
	return ajaxProcessor.post(clearMac);
}
NetmdbranchServiceImpl.prototype.changePrimaryNetmdBranch=function(changePrimary) {
	ajaxProcessor.setUrl(constants.SETNETMDBRACHCHANGEPRIMARYURL);
	return ajaxProcessor.post(changePrimary);
}
NetmdbranchServiceImpl.prototype.BranchBilllistNetmd=function(billlistData) {
	ajaxProcessor.setUrl(constants.GETNETMDBILLLISTURL);
	return ajaxProcessor.post(billlistData);
}