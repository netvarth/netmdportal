function OrganizationUserServiceImpl () {
		this.setTableValues = function(tableObj, branchResult,key) {
		$j(tableObj).dataTable().fnClearTable();
			 if(key=="userlist"){
				if(branchResult.organisationUsers) {
					if(branchResult.organisationUsers.length>0) {			
						$j(branchResult.organisationUsers).each(function (index, organisationUsers) {
							var id=organisationUsers.globalId;
							var rowData=$j(tableObj).dataTable().fnAddData([id,organisationUsers.firstName,organisationUsers.mobile,organisationUsers.status]);
							var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
							$j(row).attr('id',id);	
							$j(row).children("td:nth-child(1)").attr("class","userOrgAccIdCol Ustyle");
							});	
					}
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
					//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
					});	
			}
	 
		} 
}
 OrganizationUserServiceImpl.prototype.createBranchOrg=function (orgnzObj) {
	ajaxProcessor.setUrl(constants.CREATEORGUSERURL);
	return ajaxProcessor.post(orgnzObj);
}
OrganizationUserServiceImpl.prototype.updateAccBranchOrg=function(orgnzObj) {
	//alert(JSON.stringify(netlimsObj));
	ajaxProcessor.setUrl(constants.UPDATENETMDBRCHURL);
	return ajaxProcessor.post(orgnzObj);
}

OrganizationUserServiceImpl.prototype.viewOrgBranchDetails=function(orgBrchId) {
	ajaxProcessor.setUrl(constants.VIEWORGUSERURL + orgBrchId);
	return ajaxProcessor.get();
}

OrganizationUserServiceImpl.prototype.deleteOrgBranch=function(orgBrchId) {
	ajaxProcessor.setUrl(constants.DELETENETMDBRANCHURL + orgBrchId);
	return ajaxProcessor.get();
}



OrganizationUserServiceImpl.prototype.syncdata=function(branchId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETMDACCURL + branchId);
	return ajaxProcessor.get();
}
OrganizationUserServiceImpl.prototype.syncBranchNetmd=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETMDBRACHSYCURL);
	return ajaxProcessor.post(syncData);
}
OrganizationUserServiceImpl.prototype.clearMacNetmdBranch=function(clearMac) {
	ajaxProcessor.setUrl(constants.SETNETMDBRACHCLEARMACURL);
	return ajaxProcessor.post(clearMac);
}
OrganizationUserServiceImpl.prototype.changePrimaryNetmdBranch=function(changePrimary) {
	ajaxProcessor.setUrl(constants.SETNETMDBRACHCHANGEPRIMARYURL);
	return ajaxProcessor.post(changePrimary);
}
OrganizationUserServiceImpl.prototype.BranchBilllistNetmd=function(billlistData) {
	ajaxProcessor.setUrl(constants.GETNETMDBILLLISTURL);
	return ajaxProcessor.post(billlistData);
}