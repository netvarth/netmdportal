function waterCultureLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $j('<div/>');
		//div for report title starts here
		var headerDiv = $j('<div style="text-align:middle;"/>');
		var headerContent=$j('<h3 align="center" style="padding:10px 0 10px 0px"/>');
		headerContent.html(result.analysis.resultTitle);
		headerDiv.append(headerContent);
		content.append(headerDiv);
		//div for report title ends here
		
		var tblDiv = $j('<div/>');
		var resultTable = $j('<table id="cultureresultTable" style="float:left; margin-left:40px; margin-bottom:10px; width:90%;font-size:92%" />');
		
		var theadTag = $j('<thead/>');
		var trTag=$j('<tr />');	
		$j(result.analysis.columns).each(function(index, column) {
			var thTag = $j('<th />');
			thTag.append(column);
			trTag.append(thTag);
		});
		theadTag.append(trTag);	
		resultTable.append(theadTag);
		
		var tbodyTag = $j('<tbody/>');
		
		 var	trTag=$j('<tr />');		
		$j(result.analysis.data).each(function(index,data) {			
			var tdTag = $j('<td style="vertical-align:middle; padding-left:5px; line-height:50px;"/>');
			var inputTag;
			if(data.display) {
				if(data.display=='text') {					
					if(mode=="print") {
						tdTag.append(data.value + data.unit);
					} else {
						inputTag=$j('<input type="text" style="width:50px;"/>');
						inputTag.attr('id',"txtWaterCSResult");
						inputTag.addClass('middle');
						inputTag.val(data.value);
						tdTag.append(inputTag);
					}					
					spanTag=$j('<span style="margin-left:10px;"/>');
					spanTag.append(data.unit);
					tdTag.append(spanTag);
					tdTag.append($j('<br/>'));
					tdTag.append($j('<br/>'));
					spanTag=$j('<span style="margin-left:10px;">Water comes under </span>');
					tdTag.append(spanTag);					
					if(mode=="print") {
						tdTag.append(data.result);
					} else {
						inputTag=$j('<input type="text" id="lblwatercsResult"/>');
						inputTag.val(data.result);
						tdTag.append(inputTag);
					}					
					
				} else if(data.display=="textarea"){
					
					if(mode=="print") {
						tdTag.append(nl2br(data.value));
					} else {
						inputTag=$j('<textarea style="min-height:150px"/>');
						inputTag.attr('id',"txt"+index);
						inputTag.text(data.value);
						tdTag.append(inputTag);
					}										
				} else {
					tdTag.append(data.value);
				}
			}	
			trTag.append(tdTag);
			tbodyTag.append(trTag);
		});
		resultTable.append(tbodyTag);
		tblDiv.append(resultTable);
		
		content.append(tblDiv);	
		
		return content;
	};	

}