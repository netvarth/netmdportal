function NetrxAccServiceImpl () {
		this.setTableValues = function(tableObj, branchResult) {
		$j(tableObj).dataTable().fnClearTable();
			if(branchResult.netRxBranch) {
				if(branchResult.netRxBranch.length>0) {			
					$j(branchResult.netRxBranch).each(function (index, netRxBranch) {
						var id=netRxBranch.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,netRxBranch.name,netRxBranch.mobile,netRxBranch.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","branchNetlimsAccIdCol Ustyle");
						});	
				}
			}		 
	 
		} 
		
		
		
}
 NetrxAccServiceImpl.prototype.createBranchNetrx=function (netrxObj) {
	ajaxProcessor.setUrl(constants.CREATENETRXACCBRANCHURL);
	return ajaxProcessor.post(netrxObj);
}
NetrxAccServiceImpl.prototype.updateAccBranchNetrx=function(netrxObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETRXACCBRCHURL);
	return ajaxProcessor.post(netrxObj);
}

NetrxAccServiceImpl.prototype.viewNetrxBranchDetails=function(netrxBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETRXACCBRCHURL + netrxBrchId);
	return ajaxProcessor.get();
}

NetrxAccServiceImpl.prototype.deleteNetrxBranch=function(netrxBrchId) {
	ajaxProcessor.setUrl(constants.DELETENETRXACCBRANCHURL + netrxBrchId);
	return ajaxProcessor.get();
}

NetrxAccServiceImpl.prototype.changePasswrdNetmd=function(changePasswrd) {
	ajaxProcessor.setUrl(constants.CHANGEPASSWORDNETMDURL);
	return ajaxProcessor.post(changePasswrd);
}

NetrxAccServiceImpl.prototype.syncdata=function(branchId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETRXACCURL + branchId);
	return ajaxProcessor.get();
}
NetrxAccServiceImpl.prototype.syncBranchNetrx=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETRXACCBRACHSYCURL);
	return ajaxProcessor.post(syncData);
}

