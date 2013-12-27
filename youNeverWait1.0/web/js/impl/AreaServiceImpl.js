function AreaServiceImpl () {
	
	this.setTableValues = function(tableObj, areaResult) {
		$j(tableObj).dataTable().fnClearTable();
		if(areaResult.area) {
			if(areaResult.area.length>0) {			
				$j(areaResult.area).each(function(index, area) {
					var id=area.uid;
					var rowData=$j(tableObj).dataTable().fnAddData([id,area.name]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","areaIdCol Ustyle");
				});
			}		
		}
	}
	
}

AreaServiceImpl.prototype.createArea=function (areaObj) {
	ajaxProcessor.setUrl(constants.CREATEAREAURL);
	return ajaxProcessor.post(areaObj);
}
AreaServiceImpl.prototype.updateArea=function(areaObj) {
	ajaxProcessor.setUrl(constants.UPDATEAREAURL);
	return ajaxProcessor.post(areaObj);
}
AreaServiceImpl.prototype.deleteArea=function(areaId) {
	ajaxProcessor.setUrl(constants.DELETEAREAURL + areaId);
	return ajaxProcessor.get();
}
AreaServiceImpl.prototype.viewArea=function(areaId) {
	ajaxProcessor.setUrl(constants.VIEWAREAURL + areaId);
	return ajaxProcessor.get();
}