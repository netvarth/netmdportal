function OrganizationServiceImpl () {
		this.setTableValues = function(tableObj, orgnzResult,key) {
		$j(tableObj).dataTable().fnClearTable();
			if(orgnzResult.organisation) {
				if(orgnzResult.organisation.length>0) {			
					$j(orgnzResult.organisation).each(function (index, organisation) {
						var id=organisation.globalId;
						var rowData=$j(tableObj).dataTable().fnAddData([id,organisation.name,organisation.ownerEmail,organisation.status]);
						var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
						$j(row).attr('id',id);	
						$j(row).children("td:nth-child(1)").attr("class","netmdIdCol Ustyle");
					});	
				}
			}		
	 
		} 
		
		this.setviewordersnetlimsListTable = function(tableObj, netlimsorderResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(netlimsorderResult.branchOrders.length>0) {			
			$j(netlimsorderResult.branchOrders).each(function (index, organisation) {
				var id=organisation.branchId;
				var rowData=$j(tableObj).dataTable().fnAddData([id,organisation.branchName,organisation.orderDate,organisation.totalOrders,organisation.netAmount,organisation.paidAmount,organisation.lastOrderdTime]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);	
				//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
				});	
		}
	 
		} 
		
		this.setTableValueBranchOrderList = function(tableObj, branchResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(branchResult.branchOrders.length>0) {			
				$j(branchResult.branchOrders).each(function (index, organisation) {
					var id=organisation.branchId;
					var rowData=$j(tableObj).dataTable().fnAddData([organisation.orderDate,organisation.totalOrders,organisation.netAmount,organisation.paidAmount,organisation.lastOrderdTime]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);	
					//$j(row).children("td:nth-child(1)").attr("class","netlimsIdCol Ustyle");
					});	
		}
	 
		} 
}
 OrganizationServiceImpl.prototype.createOrgnz=function (orgObj) {
	ajaxProcessor.setUrl(constants.CREATEORGNZURL);
	return ajaxProcessor.post(orgObj);
}
OrganizationServiceImpl.prototype.updateOrgn=function(orgObj) {
	//alert(JSON.stringify(orgObj));
	ajaxProcessor.setUrl(constants.UPDATEORGNZURL);
	return ajaxProcessor.post(orgObj);
}

OrganizationServiceImpl.prototype.viewOrganzDetails=function(orgId) {
	ajaxProcessor.setUrl(constants.VIEWORGDETAILURL + orgId);
	return ajaxProcessor.get();
}

OrganizationServiceImpl.prototype.deleteOrganz=function(orgId) {
	ajaxProcessor.setUrl(constants.DELETEORGURL + orgId);
	return ajaxProcessor.get();
}



OrganizationServiceImpl.prototype.netmdSyncdata=function(netmdId) {
	ajaxProcessor.setUrl(constants.SYNCDATANETMDURL + netmdId);
	return ajaxProcessor.get();
}
OrganizationServiceImpl.prototype.syncNetmd=function(syncData) {
	ajaxProcessor.setUrl(constants.SETNETMDSYCURL);
	return ajaxProcessor.post(syncData);
}

