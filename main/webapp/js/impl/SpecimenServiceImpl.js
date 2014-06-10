function SpecimenServiceImpl () {
	
	this.setTableValues = function(tableObj, specimenResult) {
		//alert(JSON.stringify(specimenResult));
	
		$j(tableObj).dataTable().fnClearTable();
		if(specimenResult.specimenList) {
			if(specimenResult.specimenList.length>0) {			
				$j(specimenResult.specimenList).each(function(index, specimen) {
					var id=specimen.uid;
					var rowData=$j(tableObj).dataTable().fnAddData([id,specimen.specimenName]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","specimenIdCol Ustyle");
				});
			}		
		}
	}
	
}

SpecimenServiceImpl.prototype.createSpecimen=function (specimenObj) {
	//alert(JSON.stringify(specimenObj));
	ajaxProcessor.setUrl(constants.CREATESPECIMENURL);
	return ajaxProcessor.post(specimenObj);
}
SpecimenServiceImpl.prototype.updateSpecimen=function(specimenObj) {
	ajaxProcessor.setUrl(constants.UPDATESPECIMENURL);
	return ajaxProcessor.post(specimenObj);
}
SpecimenServiceImpl.prototype.deleteSpecimen=function(specimenId) {
	ajaxProcessor.setUrl(constants.DELETESPECIMENURL + specimenId);
	return ajaxProcessor.get();
}
SpecimenServiceImpl.prototype.viewSpecimen=function(specimenId) {
	ajaxProcessor.setUrl(constants.VIEWSPECIMENURL + specimenId);
	return ajaxProcessor.get();
}