
function NetPosServiceImpl() {

this.setTableValues = function(tableObj, branchResult) {
	
		$j(tableObj).dataTable().fnClearTable();
			if(branchResult.netPos) {
				if(branchResult.netPos.length>0) {			
					$j(branchResult.netPos).each(function (index, netPos) {
						var id=netPos.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,netPos.name,netPos.mobile,netPos.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","branchNetPossAccIdCol Ustyle");
						});	
				}
			}		 
	 
		} 
		
	/*	this.setTableValuesOrderList = function(tableObj, branchResult) {
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
*/
}



		
 NetPosServiceImpl.prototype.createNetPos=function (netPos) {
	ajaxProcessor.setUrl(constants.CREATENETPOSURL);
	return ajaxProcessor.post(netPos);
}
/*
NetPosServiceImpl.prototype.updateAccBranchNetlims=function(netlimsObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETLIMSACCBRCHURL);
	return ajaxProcessor.post(netlimsObj);
}

NetPosServiceImpl.prototype.viewNetlimsBranchDetails=function(netlimsBrchId) {
	ajaxProcessor.setUrl(constants.VIEWNETLIMSACCBRCHURL + netlimsBrchId);
	return ajaxProcessor.get();
}

ReferralServiceImpl.prototype.deleteReferral=function(netlimsId) {
	ajaxProcessor.setUrl(constants.DELETEREFERRALURL + netlimsId);
	return ajaxProcessor.get();
}*/ 