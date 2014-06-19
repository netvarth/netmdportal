function NetrxbranchServiceImpl () {
		this.setTableValues = function(tableObj, branchResult,key) {
		$j(tableObj).dataTable().fnClearTable();
			if(key=="branchlist"){
			if(branchResult.netRxBranch) {
				if(branchResult.netRxBranch.length>0) {			
					$j(branchResult.netRxBranch).each(function (index, netRxBranch) {
						var id=netRxBranch.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,netRxBranch.name,netRxBranch.mobile,netRxBranch.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","branchNetrxAccIdCol Ustyle");
						});	
				}
			}	}	 
	 
		} 
		
		
		
}
NetrxbranchServiceImpl.prototype.createBranchNetrx=function (netrxObj) {
	ajaxProcessor.setUrl(constants.CREATENETRXBRANCHURL);
	return ajaxProcessor.post(netrxObj);
}
NetrxbranchServiceImpl.prototype.updateAccBranchNetrx=function(netrxObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETRXBRCHURL);
	return ajaxProcessor.post(netrxObj);
}

NetrxbranchServiceImpl.prototype.viewNetrxBranchDetails=function(netrxBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETRXACCBRCHURL + netrxBrchId);
	return ajaxProcessor.get();
}

NetrxbranchServiceImpl.prototype.deleteNetrxBranch=function(netrxBrchId) {
	ajaxProcessor.setUrl(constants.DELETENETRXACCBRANCHURL + netrxBrchId);
	return ajaxProcessor.get();
}

NetrxbranchServiceImpl.prototype.clearMacNetrxBranch=function(clearMac) {
	ajaxProcessor.setUrl(constants.SETNETRXBRACHCLEARMACURL);
	return ajaxProcessor.post(clearMac);
}


NetrxbranchServiceImpl.prototype.syncdata=function(branchId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETRXACCURL + branchId);
	return ajaxProcessor.get();
}
NetrxbranchServiceImpl.prototype.syncBranchNetrx=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETRXBRACHSYCURL);
	return ajaxProcessor.post(syncData);
}

