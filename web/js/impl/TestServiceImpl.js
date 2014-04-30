function TestServiceImpl () {
	
	this.setTableValues = function(tableObj, testPackageResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(testPackageResult.testPkg) {
			if(testPackageResult.testPkg.length>0) {			
				$j(testPackageResult.testPkg).each(function(index, testPackage) {
					var id=testPackage.uid;
					var rowData=$j(tableObj).dataTable().fnAddData([id,testPackage.name,testPackage.category,testPackage.stdRate]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","testPackageIdCol Ustyle");
					if(testPackage.active==false)
						$j(row).attr('style','background:#888888;');	
				});
			}		
		}
	}
	
}

TestServiceImpl.prototype.createTest=function(testPackageObj) {
	ajaxProcessor.setUrl(constants.CREATETESTPACKAGEURL);
	return ajaxProcessor.post(testPackageObj);
}
TestServiceImpl.prototype.updateTest=function(testPackageObj) {
	ajaxProcessor.setUrl(constants.UPDATETESTPACKAGEURL);
	return ajaxProcessor.post(testPackageObj);
}
TestServiceImpl.prototype.deleteTest=function(testPackageId) {
	ajaxProcessor.setUrl(constants.DELETETESTPACKAGEURL + testPackageId);
	return ajaxProcessor.get();
}
TestServiceImpl.prototype.viewTest=function(testId) {
	ajaxProcessor.setUrl(constants.VIEWTESTPACKAGEURL + testId);
	return ajaxProcessor.get();
}
TestServiceImpl.prototype.getTestRateByUid=function(testPackageId) {
	ajaxProcessor.setUrl(constants.GETTESTPACKAGERATEURL + testPackageId);
	return ajaxProcessor.get();
}