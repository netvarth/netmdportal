function InstallerServiceImpl () {
	
	this.setTableValues = function(tableObj, installerResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(installerResult.installers) {
			if(installerResult.installers.length>0) {			
				$j(installerResult.installers).each(function(index,installer) {
					var InstallerId=installer.id;
					var appName=installer.applicationName;
					var version=installer.versionNo;
					var date=installer.createdDate;
					var rowData=$j(tableObj).dataTable().fnAddData([InstallerId,appName,version,date]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',InstallerId);
					$j(row).children("td:nth-child(1)").attr("class","installerIdCol Ustyle");
				});
			}		
		} 
	}
	
}


InstallerServiceImpl.prototype.viewInstaller=function(installerId) {
	ajaxProcessor.setUrl(constants.VIEWINSTALLERNURL + installerId);
	return ajaxProcessor.get();
}