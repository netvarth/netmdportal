
function generalLayout(testResult,testId,mode,honorific) {
	this.result =	function(){ 
		if(testId==null || testId=="")
			testId=0;
		var response = $j('<tbody/>');
		response.attr('name','tbody_'+testId);
		
		var status=true;
		
		if(honorific!='Animal') {
			$j(testResult.analysis).each(function(analysisIndex,analysis) {
				$j(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					$j(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
						if(resultparam.key){
							if(status==true) {
								var trTag = $j('<tr/>');
								var tdTag = $j('<td style="vertical-align:middle"/>');
								tdTag.attr('colspan',resultparam.values.length+1);
								tdTag.html(testResult.testName);	
								trTag.append(tdTag);
								response.append(trTag);
								status=false;
							}						
						}
						var trTag = $j('<tr/>');
						trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
						trTag.attr('id',trTagId);
						var tdTag= $j('<td style="width:40%;vertical-align:middle"/>');
						if(resultparam.key)
							tdTag.html(resultparam.key);
						else {					
							tdTag.html(testResult.testName);
							//tdTag.attr('style','font-weight:bold;');
						}	
						trTag.append(tdTag);
						
						$j(resultparam.values).each(function(paramvaluesIndex,paramValues) {
							$j(paramValues.values).each(function(paramdataIndex,paramData) {
								var tdTag= $j('<td style="vertical-align:middle" class="middle"/>');
								if(paramValues.headervalue=='NormalRange') 					
										tdTag.attr('style',"width:40%");
								var tblTag = $j('<table width="100%"/>');
								if(paramData.values) {				
									$j(paramData.values).each(function(paramdataValueIndex,paramDataValues){
										//inside normal range
										var newrow = $j('<tr/>');
										if(paramDataValues.normalRangeAttribute) {	
											var colTag = $j('<td style="vertical-align:middle;"  class="middle"/>');
											colTag.append(paramDataValues.normalRangeAttribute);	
											newrow.append(colTag);								
										}
										if(paramDataValues.values) {
											var colTag = $j('<td class="middle"/>');
											var innerTblTag = $j('<table width="100%"/>');
											
											
											$j(paramDataValues.values).each(function(paramdataValueInnerIndex,paramDataValuesInner){
												//inside normal range
												var newrowInner = $j('<tr/>');
												if(paramDataValuesInner.normalRangeAttribute) {	
													var colTag = $j('<td class="middle"/>');
													colTag.append(paramDataValuesInner.normalRangeAttribute);	
													newrowInner.append(colTag);								
												}
												if(paramDataValuesInner.value) {
													var colTag = $j('<td class="middle"/>');
													colTag.html(paramDataValuesInner.value);
													if(paramDataValuesInner.unit){
														var spanTag = $j('<span style="padding:0px 0px 0px 5px" />');
														spanTag.append(paramDataValuesInner.unit);
														colTag.append(spanTag);	
													}
													newrowInner.append(colTag);	
												}
												innerTblTag.append(newrowInner);
											});				
											colTag.append(innerTblTag);
											newrow.append(colTag);
										} else {
											if(paramDataValues.value) {
												var colTag = $j('<td class="middle"/>');
												colTag.html(nl2br(paramDataValues.value));
												if(paramDataValues.unit){
													var spanTag = $j('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramDataValues.unit);
													colTag.append(spanTag);	
												}
												newrow.append(colTag);	
											}	
										}
										tblTag.append(newrow);
									});
								} else {
									var newrow = $j('<tr/>');
									if(paramData.normalRangeAttribute) {	
										var colTag = $j('<td />');
										colTag.append(paramData.normalRangeAttribute);	
										newrow.append(colTag);								
									}
									if(paramData.displayType) {	
										var colTag = $j('<td />');
										if(paramData.headervalue=='NormalRange') {
										//	tdTag.removeClass('middle');
											tdTag.attr('style',"max-width:38%;min-width:40%");
										}
										if(paramData.displayType=='text') {
											var elementTag;
											var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
											if(mode=='print') {
												colTag.addClass('middle');
												elementTag = $j('<label />')
												if(paramData.value)
													elementTag.html(paramData.value);
											} else {
												elementTag = $j('<input type="text" class="observed" style="float:left;"/>');
												if(paramData.className)
													elementTag.attr('class',paramData.className);
												if(paramData.value)
													elementTag.attr('value',paramData.value);
												colTag.attr('style',"padding:0px");	
											}	
											$j(elementTag).attr('id',txtid);					
											colTag.append(elementTag);
											if(paramData.unit){
												var spanTag = $j('<span style="padding:0px 0px 0px 5px;"/>');
												spanTag.append(paramData.unit);
												colTag.append(spanTag);	
											}
										}
										newrow.append(colTag);
									} else {				
										if(paramData.value) {			
											var colTag = $j('<td />');
											if(paramData.value.length>60)
												colTag.addClass('leftalign');
											else
												colTag.addClass('middle');
											var normalValue = nl2br(paramData.value);
											normalValue  = normalValue.replaceAll(" ", "&nbsp;");
											colTag.html(normalValue);
											if(paramData.unit){
												var spanTag = $j('<span style="padding:0px 0px 0px 5px" />');
												spanTag.append(paramData.unit);
												colTag.append(spanTag);	
											}										
											newrow.append(colTag);
										}
									}	
									tblTag.append(newrow);
								}														
								tdTag.append(tblTag);
								trTag.append(tdTag);							
							});
						});
						response.append(trTag);
					});
				});
			});
		} /* else {
			$j(testResult.analysis).each(function(analysisIndex,analysis) {
				$j(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					$j(resultcontent.resultParams).each(function(resultparamIndex,resultparam) {
						var trTag = $j('<tr/>');
						trTagId = testId + '_' + analysisIndex + "_" + resultcontentIndex + "_" + resultparamIndex;
						trTag.attr('id',trTagId);
						//Investigation Field
						var tdTag= $j('<td style="width:40%;vertical-align:middle"/>');
						tdTag.html(testResult.testName);	
						trTag.append(tdTag);
						
						$j(resultparam.values).each(function(paramvaluesIndex,paramValues) {
							$j(paramValues.values).each(function(paramdataIndex,paramData) {
								var tdTag= $j('<td style="vertical-align:middle" class="middle"/>');
								if(paramValues.headervalue!='NormalRange') { 					
									var tblTag = $j('<table width="100%"/>');
									if(paramData.values) {				
										$j(paramData.values).each(function(paramdataValueIndex,paramDataValues){
											//inside normal range
											var newrow = $j('<tr/>');
											if(paramDataValues.normalRangeAttribute) {	
												var colTag = $j('<td style="vertical-align:middle;"  class="middle"/>');
												colTag.append(paramDataValues.normalRangeAttribute);	
												newrow.append(colTag);								
											}
											if(paramDataValues.values) {
												var colTag = $j('<td class="middle"/>');
												var innerTblTag = $j('<table width="100%"/>');
												
												
												$j(paramDataValues.values).each(function(paramdataValueInnerIndex,paramDataValuesInner){
													//inside normal range
													var newrowInner = $j('<tr/>');
													if(paramDataValuesInner.normalRangeAttribute) {	
														var colTag = $j('<td class="middle"/>');
														colTag.append(paramDataValuesInner.normalRangeAttribute);	
														newrowInner.append(colTag);								
													}
													if(paramDataValuesInner.value) {
														var colTag = $j('<td class="middle"/>');
														colTag.html(paramDataValuesInner.value);
														if(paramDataValuesInner.unit){
															var spanTag = $j('<span style="padding:0px 0px 0px 5px" />');
															spanTag.append(paramDataValuesInner.unit);
															colTag.append(spanTag);	
														}
														newrowInner.append(colTag);	
													}
													innerTblTag.append(newrowInner);
												});				
												colTag.append(innerTblTag);
												newrow.append(colTag);
											} else {
												if(paramDataValues.value) {
													var colTag = $j('<td class="middle"/>');
													colTag.html(nl2br(paramDataValues.value));
													if(paramDataValues.unit){
														var spanTag = $j('<span style="padding:0px 0px 0px 5px" />');
														spanTag.append(paramDataValues.unit);
														colTag.append(spanTag);	
													}
													newrow.append(colTag);	
												}	
											}
											tblTag.append(newrow);
										});
									} else {
										var newrow = $j('<tr/>');
										if(paramData.normalRangeAttribute) {	
											var colTag = $j('<td />');
											colTag.append(paramData.normalRangeAttribute);	
											newrow.append(colTag);								
										}
										if(paramData.displayType) {	
											var colTag = $j('<td />');									
											if(paramData.displayType=='text') {
												var elementTag;
												var txtid = testId + "_" + analysisIndex + "_" + resultcontentIndex + '_' + resultparamIndex +'_' + paramvaluesIndex +"_" +paramdataIndex;
												if(mode=='print') {
													colTag.addClass('middle');
													elementTag = $j('<label />')
													if(paramData.value)
														elementTag.html(paramData.value);
												} else {
													elementTag = $j('<input type="text" class="observed" style="float:left;"/>');
													if(paramData.className)
														elementTag.attr('class',paramData.className);
													if(paramData.value)
														elementTag.attr('value',paramData.value);
													colTag.attr('style',"padding:0px");	
												}	
												$j(elementTag).attr('id',txtid);					
												colTag.append(elementTag);
												if(paramData.unit){
													var spanTag = $j('<span style="padding:0px 0px 0px 5px;"/>');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}
											}
											newrow.append(colTag);
										} else {				
											if(paramData.value) {			
												var colTag = $j('<td />');
												if(paramData.value.length>60)
													colTag.addClass('leftalign');
												else
													colTag.addClass('middle');
												var normalValue = nl2br(paramData.value);
												normalValue  = normalValue.replaceAll(" ", "&nbsp;");
												colTag.html(normalValue);
												if(paramData.unit){
													var spanTag = $j('<span style="padding:0px 0px 0px 5px" />');
													spanTag.append(paramData.unit);
													colTag.append(spanTag);	
												}										
												newrow.append(colTag);
											}
										}	
										tblTag.append(newrow);
									}														
									tdTag.append(tblTag);
									trTag.append(tdTag);	
								}		
							});
						});
						response.append(trTag);		
					});
				});
			});		
		}	 */	
		return response;
		
	};
}