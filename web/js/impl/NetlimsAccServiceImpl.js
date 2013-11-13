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
}
 NetlimsAccServiceImpl.prototype.createBranchNetlims=function (netlimsObj) {
	ajaxProcessor.setUrl(constants.CREATENETLIMSACCBRANCHURL);
	return ajaxProcessor.post(netlimsObj);
}
/*ReferralServiceImpl.prototype.updateReferral=function(netlimsObj) {
	ajaxProcessor.setUrl(constants.UPDATEREFERRALURL);
	return ajaxProcessor.post(netlimsObj);
}
ReferralServiceImpl.prototype.deleteReferral=function(netlimsId) {
	ajaxProcessor.setUrl(constants.DELETEREFERRALURL + netlimsId);
	return ajaxProcessor.get();
}
ReferralServiceImpl.prototype.viewReferral=function(netlimsId) {
	ajaxProcessor.setUrl(constants.VIEWREFERRALURL + netlimsId);
	return ajaxProcessor.get();
} */