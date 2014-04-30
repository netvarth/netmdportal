function SemenLayout(honorific,result,testId, mode) {
	this.result =function(){ 
		var azospermia = false;
		var content =$j('<div align="center" class="resultTitle">'+result.testName+'</div>');
		var semenTable=$j('<table width="100%" id="SemenTable" />');
		var tbodyTag;
		$j(result.analysis).each(function(ResultContentIndex,analysis){
			if(analysis.analysisType){
				var theadTag = $j('<thead />');
				var trTag=$j('<tr></tr>');
				var tdTag = $j('<th colspan="2"></th>');
				tdTag.html(analysis.analysisType);
				trTag.append(tdTag);	
				theadTag.html(trTag);
				semenTable.append(theadTag);
			}	
			
			tbodyTag=$j('<tbody/>');
			
			if(analysis.analysisType=="MICROSCOPIC EXAMINATION"){
				var trTag = $j('<tr/>');
				var spanTag;
				$j(analysis.resultContent).each(function(index,resultcontent) {
					if(resultcontent.value=="Azospermia")
						azospermia = true;
					if(index!=2) {					
						tdTag = $j('<td style="width:50%"/>');	
						spanTag = $j('<span />');
					} else {
						spanTag = $j('<span />');
					}					
					if(mode=='print') {
						var lblTag = $j('<label/>');
						lblTag.html(resultcontent.value);
						spanTag.append(lblTag);
					} else {
						var inputTag=$j('<input type="text" id="' + resultcontent.id +'" value="' + resultcontent.value + '" />');
						if(resultcontent.className)
							inputTag.attr('class',resultcontent.className);
						spanTag.append(inputTag);
					}
					tdTag.append(spanTag);
					if(index!=1)
						trTag.append(tdTag);
				});	
				tbodyTag.append(trTag);
			}else if(analysis.analysisType=="SPERM MORPHOLOGY"){//Sperm Morphology Section 
				var trParentTag = $j('<tr />');
				var tdTag = $j('<td colspan="2"/>');
				var innerTable = $j('<table width="100%" />');
				var status =true;
				$j(analysis.resultContent).each(function(resultcontentIndex,resultcontent) {
					var trTag=$j('<tr/>');
					$j(resultcontent.values).each(function(valuesIndex,values){	
						//abnormal Heading
						if(resultcontentIndex==0) {
							if(status==true) {
								var tdchildTag=$j('<td >' + resultcontent.headerLabel +'</td>');
								trTag.append(tdchildTag);
								status=false;
							}	
							var tdchildTag = $j('<td style="width:27%"/>');
							var spanTag = $j('<span>' + values.normalRangeAttribute + '</span>');
							tdchildTag.append(spanTag);		
							spanTag = $j('<span/>');
							if(mode=='print') {	
								var lblTag = $j('<label/>');
								if(values.value=="")
									lblTag.html("--");
								else
									lblTag.html(values.value);							
								spanTag.html(lblTag);
								tdchildTag.append(spanTag);
							} else {
								var inputTag=$j('<input type="text" id="'+values.id + '" value="' + values.value + '"/>');
								if(values.className)
									inputTag.attr('class',values.className);
								spanTag.html(inputTag);
								tdchildTag.append(spanTag);
							}
							if(values.unit) {
								spanTag = $j('<span/>');
								if(azospermia==false && values.value.toUpperCase()!="NIL") 
									spanTag.html(values.unit);
								tdchildTag.append(spanTag);
							}							
							trTag.append(tdchildTag);
						} else {
							var tdchildTag=$j('<td colspan="2"/>');
							var spanTag = $j('<span>' + values.normalRangeAttribute + '</span>');
							tdchildTag.append(spanTag);		
							spanTag = $j('<span/>');
							if(mode=='print') {	
								var lblTag = $j('<label/>');
								lblTag.html(values.value);							
								spanTag.html(lblTag);
								tdchildTag.append(spanTag);
							} else {
								var inputTag=$j('<input type="text" id="'+values.id + '" value="' + values.value + '"/>');
								if(values.className)
									inputTag.attr('class',values.className);
								spanTag.html(inputTag);
								tdchildTag.append(spanTag);
							}							
							if(values.unit) {
								spanTag = $j('<span/>');
								if(azospermia==false && values.value.toUpperCase()!="NIL") 
									spanTag.html(values.unit);
								tdchildTag.append(spanTag);
							}
							trTag.append(tdchildTag);							
						}
					});						
					innerTable.append(trTag);
				});
				tdTag.append(innerTable);
				trParentTag.append(tdTag);
				tbodyTag.append(trParentTag);
			} else {		//Other Sections
				$j(analysis.resultContent).each(function(index,resultcontent) {
					var trTag = $j('<tr/>');
					if(resultcontent.normalRangeAttribute=="Liquefaction") {
						var tdTag = $j('<td style="width:50%"/>');
						var spanTag = $j('<span />');
						spanTag.html(resultcontent.normalRangeAttribute);
						tdTag.html(spanTag);
						spanTag = $j('<span />');
						if(mode=='print') {
							var lblTag = $j('<label style="float:right;"/>');
							lblTag.html(resultcontent.value);
							tdTag.append(lblTag);
						} else {
							var inputTag=$j('<input style="float:right;" type="text" align="right" id="'+ resultcontent.id + '" value="' + resultcontent.value + '"/>');
							if(resultcontent.className1)
								inputTag.attr('class',resultcontent.className1);
							tdTag.append(inputTag); 
						}						
						trTag.append(tdTag);
						
						tdTag = $j('<td style="width:50%"/>');
						if(mode=='print') {
							var lblTag = $j('<label/>');
							lblTag.html(resultcontent.value1);
							tdTag.append(lblTag);
						} else {
							var inputTag=$j('<input type="text" id="'+ resultcontent.id + '_1' +'" value="' + resultcontent.value1 + '"/>');
							if(resultcontent.className)
								inputTag.attr('class',resultcontent.className);
							tdTag.append(inputTag); 
						}	
						if(resultcontent.unit) {
							spanTag = $j('<span/>');
							spanTag.html(resultcontent.unit);
							tdTag.append(spanTag);
						}
						trTag.append(tdTag);
						tbodyTag.append(trTag);
					} else {
						var tdTag = $j('<td style="width:50%"/>');
						tdTag.html(resultcontent.normalRangeAttribute); // add first column commonly caption
						trTag.append(tdTag);
						tdTag = $j('<td style="width:50%" align="left"/>');
						if(mode=='print') {
							if(analysis.analysisType=="SPERM MOTILITY")
								var lblTag = $j('<div style="float:left;text-align:right;padding-right:5px;min-width:10%" />');
							else
								var lblTag = $j('<div style="float:left;padding-right:5px;" />');
								if(resultcontent.value=="")
									lblTag.html("--");
								else
									lblTag.html(resultcontent.value);
							tdTag.html(lblTag);
						} else {
							var inputTag=$j('<input type="text" id="'+resultcontent.id +'" value="' + resultcontent.value + '"/>');
							if(resultcontent.className)
								inputTag.attr('class',resultcontent.className);
							tdTag.html(inputTag); 
						}
						if(resultcontent.unit) {
							spanTag = $j('<span/>');
							if(azospermia==false && resultcontent.value.toUpperCase()!="NIL") 
								spanTag.html(resultcontent.unit);
							tdTag.append(spanTag);
						}
						trTag.append(tdTag);
						tbodyTag.append(trTag);
					}
				});
			}	
			semenTable.append(tbodyTag);			
		});	
		content.append(semenTable);
		return content; 
	};
}	