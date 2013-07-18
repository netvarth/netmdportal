function layout1(honorific,testResult, testId, mode) {
	this.result =	function(){ 
		var response = $j('<table />');
		$j(testResult.analysis).each(function(analysisIndex,analysis) {
			var thead = $j('<thead class="noBorder">');
			thead.attr('id','thead_'+analysisIndex);
			var headRow = $j('<tr/>');
			var headCol = $j('<th style="border:none;"/>');
			headCol.html(analysis.analysisType);
			headRow.html(headCol);
			thead.html(headRow);
			response.append(thead);
			var tbody=$j('<tbody/>');
			var trTag = $j('<tr />');
			var tdTag = $j('<td style="border:none;"/>');
			$j(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
				var tblTag = $j('<table />');
				$j(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
					$j(resultparam.values).each(function(paramvaluesIndex,paramValues) {						
						$j(paramValues.values).each(function(paramdataIndex,paramData) {							
							if(paramData.values) {	
								var newrow = $j('<tr/>');
								if(resultparam.headerLabel) {
									var colhead=$j('<td>');
									colhead.html(resultparam.headerLabel);
									newrow.append(colhead);
								};	
								$j(paramData.values).each(function(paramdataValueIndex,paramDataValue) {																	
									if(paramDataValue.normalRangeAttribute) {	
										var colTag = $j('<td />');
										colTag.append(paramDataValue.normalRangeAttribute);	
										newrow.append(colTag);								
									}
									if(paramDataValue.displayType) {	
										var colTag = $j('<td />');
										var spanTag=$j('<span/>');
										if(paramDataValue.displayType=='text') {
											elementTag = $j('<input type="text"/>');
											if(mode=='print') {
												elementTag.attr('class','readonlybox');
												elementTag.attr('readonly',true);
											}
											var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex+"_"+paramdataValueIndex;
											$j(elementTag).attr('id',txtid);

											if(paramDataValue.className){
											elementTag.attr('class',paramDataValue.className)
											}
											if(paramDataValue.style){
											elementTag.attr('style',paramDataValue.style);
											}
											if(paramDataValue.value) {
											 elementTag.attr('value',paramDataValue.value);
											}
											colTag.append(elementTag);
												if(paramDataValue.unit) {
												colTag.append(paramDataValue.unit);
											}
											
											
										}
										newrow.append(colTag);
									} else {
										if(paramDataValue.value) {
											var colTag = $j('<td />');
											colTag.html(paramDataValue.value);
											if(paramDataValue.unit)
												colTag.prepend(paramDataValue.unit);
											newrow.append(colTag);	
										}
									}
									tblTag.append(newrow);									
								});								
							} 																															
						});	
						var newrow = $j('<tr/>');
						if(resultparam.headerLabel) {
							var colhead=$j('<td>');
							newrow.append(colhead);
						};	
						$j(paramValues.values).each(function(paramdataIndex,paramData) {															
							if(paramData.normalRangeAttribute) {	
								var colTag = $j('<td  style="width:65%;padding-left:35%;text-align:left"/>');
								colTag.append(paramData.normalRangeAttribute);	
								newrow.append(colTag);								
							}
							var colTag = $j('<td  style="text-align:center"/>');
							colTag.append("&nbsp; &nbsp;&nbsp;: &nbsp;&nbsp; ");	
							newrow.append(colTag);	
							if(paramData.displayType) {	
								var colTag = $j('<td style="text-align:left"/>');
								if(paramData.displayType=='text') {
									elementTag = $j('<input type="text" style="text-align:left"/>');
									if(mode=='print') {
										elementTag.attr('class','readonlybox');
										elementTag.attr('readonly',true);
									}
									var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
									$j(elementTag).attr('id',txtid);
								
									if(paramData.className){
											elementTag.attr('class',paramData.className);
											}
										if(paramData.style){
										elementTag.attr('style',paramData.style);
											}
									if(paramData.readonly){
											elementTag.attr('readonly',true);
											}
									if(paramData.value) {
										elementTag.attr('value',paramData.value);											
									}
									colTag.append(elementTag);
									if(paramData.unit) {
										colTag.append(paramData.unit);
									}
									 }

								if(paramData.displayType=="select") {
										elementTag = $j('<select/>');
										if(paramData.list) {
											$j(paramData.list).each(function(indexval,val) {
												var optTag = $j('<option/>')
												if(val.value)optTag.attr("value",val.value);
												if(val.selected)optTag.attr("selected",val.selected);
												optTag.html(val.value);
												elementTag.append(optTag);
											});
										}
										if(paramData.id){
										elementTag.attr('id',paramData.id)
										}
										colTag.append(elementTag);																							
									}
								
								newrow.append(colTag);
							} else {
								if(paramData.value) {
									var colTag = $j('<td />');
									colTag.html(paramData.value);
									if(paramData.unit)
										colTag.append(paramData.unit);
									newrow.append(colTag);	
								}
							}
						});
						tblTag.append(newrow);	
					});	
				});
				tdTag.append(tblTag);
				trTag.append(tdTag);
				tbody.append(trTag);
				response.append(tbody);
			});
			
		});	
		return response.html();
	};	

}

