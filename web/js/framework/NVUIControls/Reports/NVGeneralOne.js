function generalOneLayout(honorific,testResult,testId,mode) {
	this.result =	function(){
		var resultTBody = $j('<tbody/>');
		resultTBody.attr('name','tbody_'+testId); // set the tbody id to test id	
		$j(testResult.analysis).each(function(analysisIndex,analysis) {
		$j(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
		$j(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
			var trTag = $j('<tr />'); // Create the first row
			trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
			trTag.attr('id',trTagId); // set id for the row
			tdTag= $j('<td style="width:40%;vertical-align:middle" />'); // First column for investigation
			tdTag.html(testResult.testName);
			//tdTag.attr('style','font-weight:bold;');
			trTag.append(tdTag); // Added Investigation Name to first column
			//Iterating through contents
			$j(resultparam.values).each(function(paramvaluesIndex,paramValues) {
				//Creating the next columns like observed value, normal range etc...
				tdTag= $j('<td class="middle" style="vertical-align:middle"/>');
				
				if(paramValues.values) {
					$j(paramValues.values).each(function(paramdataIndex,paramDataValues){
						if(paramValues.headervalue=='NormalRange') {
							tdTag.removeClass('middle');
							tdTag.attr('style',"width:35%");
						}
						if(mode=='print') {
							if(paramValues.headervalue!='NormalRange') {
								elementTag = $j('<label />')
								if(paramDataValues.value)
									elementTag.text(nl2br(paramDataValues.value));
								tdTag.html(elementTag);
								if(paramDataValues.unit){
									var spanTag = $j('<span style="padding:0px 0px 0px 5px"/>');
									spanTag.append(paramDataValues.unit);
									tdTag.append(spanTag);	
								}
							} else {
								var normalValue = nl2br(paramDataValues.value);
								normalValue  = normalValue.replaceAll(" ", "&nbsp;");
								tdTag.html(normalValue);
							}
						} else {
							var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
							if(paramValues.headervalue=='NormalRange') {
								var innerTblTag = $j('<table width="100%"/>');
								var newrowInner = $j('<tr/>');
								var columnTag = $j('<td class="middle"/>');
								selectTag=$j('<select class="select normalRange" style="width:100%;" >');
								columnTag.append(selectTag);
								newrowInner.append(columnTag);
								innerTblTag.append(newrowInner);
								newrowInner = $j('<tr/>');
								columnTag = $j('<td class="middle"/>');
								var textarea = $j('<textarea class="normalVal" />');
								textarea.attr('id',txtid);
								textarea.html(br2nl(paramDataValues.value));
								columnTag.append(textarea);
								newrowInner.append(columnTag);
								innerTblTag.append(newrowInner);
								tdTag.html(innerTblTag);
							} else {
								var elementTag = $j('<input type="text" style="float:left;" class="observed"/>');
								elementTag.attr('id',txtid);
								elementTag.attr('value',paramDataValues.value);
								tdTag.html(elementTag);								
								if(paramDataValues.unit) {
									var lblTag = $j('<label padding:0px 0px 0px 5px;"/>');
									lblTag.html(paramDataValues.unit);
									tdTag.append(lblTag);
								}									
							}
						}						
					});
				}
				trTag.append(tdTag);
			});
			resultTBody.append(trTag);
		});
		});
		});
		return resultTBody; // return the result table body
	};
}