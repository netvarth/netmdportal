function NetmdAccServiceImpl () {
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
				}
			else{
					if(branchResult.branchBillList.length>0) {						
						$j(branchResult.branchBillList).each(function (index, netmd) {
							var id=netmd.branchId;
							$j("#accBillDetailsModalForm #fromDate label").text(branchResult.fromDate);
							$j("#accBillDetailsModalForm #toDate label").text(branchResult.toDate);
							$j("#accBillDetailsModalForm #totalBillAmount label").text(branchResult.totalBillAmt);
							$j("#accBillDetailsModalForm #totalAmountPaid label").text(branchResult.totalAmtPaid);
							$j("#accBillDetailsModalForm #totalAmountDue label").text(branchResult.totalAmtDue);
							var rowData=$j(tableObj).dataTable().fnAddData([netmd.uid,netmd.orderDate,netmd.patientName,netmd.payStatus,netmd.billAmount,netmd.amountPaid,netmd.amountDue]);
							var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
							$j(row).attr('id',id);	
							$j(row).children("td:nth-child(5),td:nth-child(6),td:nth-child(7)").addClass('column-2');
						});	
					}
			
				}		 
	 
		} 
				
		this.setTableValueBranchBillList = function(tableObj, billResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(billResult.branchBillList.length>0) {			
				$j(billResult.branchBillList).each(function (index, netmd) {
					var id=netmd.branchId;
					var rowData=$j(tableObj).dataTable().fnAddData([netmd.uid,netmd.orderDate,netmd.patientName,netmd.payStatus,netmd.billAmount,netmd.amountPaid]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);	
					});	
			}
	 
		} 
}
 NetmdAccServiceImpl.prototype.createBranchNetmd=function (netmdObj) {
	ajaxProcessor.setUrl(constants.CREATENETMDACCBRANCHURL);
	return ajaxProcessor.post(netmdObj);
}
NetmdAccServiceImpl.prototype.updateAccBranchNetmd=function(netmdObj) {

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
	ajaxProcessor.setUrl(constants.SYNCDATANETMDACCURL + branchId);
	return ajaxProcessor.get();
}
NetmdAccServiceImpl.prototype.syncBranchNetmd=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETMDACCBRACHSYCURL);
	return ajaxProcessor.post(syncData);
}

NetmdAccServiceImpl.prototype.BranchBilllistNetmd=function(billlistData) {
	ajaxProcessor.setUrl(constants.GETBILLLISTURL);
	return ajaxProcessor.post(billlistData);
}