function cultureLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $j('<tbody class="noBorder"/>');
		var trRootTag = $j('<tr/>');
		var tdRootTag = $j('<td />');
		//div for report title starts here
		var headerTable = $j('<table width="100%" class="cultureTable_Head">');
		
		var theadParentTag = $j('<thead>');
		var trParentTag = $j('<tr/>');
		var thParentTag = $j('<th colspan="4">');
		thParentTag.html(result.analysis.resultTitle);
		trParentTag.html(thParentTag);
		theadParentTag.append(trParentTag);
		headerTable.append(theadParentTag);
		//div for report title ends here
		
		var tbodyParentTag = $j('<tbody>');
		var trParentTag = $j('<tr/>');
		var tdParentTag = $j('<td colspan="2">');
		var labelTag = $j('<label/>');
		labelTag.html(result.analysis.organismGrownLabel);
		tdParentTag.append(labelTag);
		if(mode=='print') {
			var lblTag = $j('<label/>');
			if(result.analysis.organismGrownValue)
				lblTag.html(result.analysis.organismGrownValue);
			tdParentTag.append(lblTag);
		} else {
			var inputPTag=$j('<input type="text" class="cultureList large" id="organismValue" style="width:86%"/>');
			if(result.analysis.organismGrownValue)
				inputPTag.attr('value',result.analysis.organismGrownValue);
			tdParentTag.append(inputPTag);
		}		
		trParentTag.html(tdParentTag);	
		tbodyParentTag.append(trParentTag);
		
		//Second Row
		trParentTag = $j('<tr/>');
		tdParentTag = $j('<td style="width:50%"/>');
		spanPTag = $j('<span>1). </span>');
		tdParentTag.append(spanPTag);
		spanPTag = $j('<span/>');
		if(mode=='print') {
			var lblTag = $j('<label/>');
			if(result.analysis.columnValue1)
				lblTag.html(result.analysis.columnValue1+result.analysis.columnValue1Sub);
			spanPTag.html(lblTag);
			tdParentTag.append(spanPTag);
		} else {
			inputPTag=$j('<input type="text" class="antibiotics" id="columnValue1" style="width:70%"/>');
			 if(result.analysis.columnValue1)
				inputPTag.attr('value',result.analysis.columnValue1); 
			spanPTag.append(inputPTag);
			var inputPTag2=$j('<select class="raise2"  align="right;" id="columnValue1Sub" style="width:20%"/>');
			 if(result.analysis.columnValue1Sub)
				inputPTag2.attr('value',result.analysis.columnValue1Sub);
				spanPTag.append(inputPTag2);
			   tdParentTag.append(spanPTag);
		}	
		trParentTag.append(tdParentTag);	
		
		tdParentTag = $j('<td style="width:50%"/>');
		spanPTag = $j('<span>2). </span>');
		tdParentTag.append(spanPTag);
		spanPTag = $j('<span/>');
		if(mode=='print') {
			var lblTag = $j('<label/>');
			if(result.analysis.columnValue2)
				lblTag.html(result.analysis.columnValue2+result.analysis.columnValue2Sub); 
			spanPTag.html(lblTag);
			tdParentTag.append(spanPTag);
		} else {
			inputPTag=$j('<input type="text" class="antibiotics " id="columnValue2" style="width:70%"/>');
			 if(result.analysis.columnValue2)
				inputPTag.attr('value',result.analysis.columnValue2);
			spanPTag.append(inputPTag);
			var inputPTag2=$j('<select class="raise2"  align="right;"id="columnValue2Sub" style="width:20%"/>');
			 if(result.analysis.columnValue2Sub)
				inputPTag2.attr('value',result.analysis.columnValue2Sub);
				spanPTag.append(inputPTag2);
			   tdParentTag.append(spanPTag);
		}	
		trParentTag.append(tdParentTag);
		tbodyParentTag.append(trParentTag);
		headerTable.append(tbodyParentTag);
		
		tdRootTag.append(headerTable);
		
		var tblDiv = $j('<div/>');
		var resultTable = $j('<table id="cultureresultTable" style="float:left; margin-left:40px; width:90%" />');
		
		var theadTag = $j('<thead/>');
		var trTag=$j('<tr />');	
		for(i=0;i<2;i++) {
			$j(result.analysis.columns).each(function(index, column) {
				var thTag = $j('<th />');
				thTag.append(column);
				trTag.append(thTag);
			});
		}	
		theadTag.append(trTag);	
		resultTable.append(theadTag);
		
		var tbodyTag = $j('<tbody/>');
		
		var length=result.analysis.data.length;
		var trTag;
		var tabIndex=0;
		var tabIndex1=0; //
		var tabIndex2=0; //
		var tabIndex3=0;
		$j(result.analysis.data).each(function(index,data) {
			if(index%2==0){
				trTag=$j('<tr />');		
					tabIndex1++;
					tabIndex=tabIndex1;
			}else {
				tabIndex2=tabIndex1 + 20;
				tabIndex=tabIndex2;
			}
			var tdTag;
			if(mode=="print") 
				tdTag = $j('<td style="width:34%"><label>'+data.name+'</label></td>');
			else
			tdTag = $j('<td style="width:34%"><input type="text"  value="'+data.name+'" id="' + data.name + '_0" tabIndex=' + tabIndex + '></td>');
			//tdTag.append(data.name);
			trTag.append(tdTag);
				
			tdTag = $j('<td style="width:8%;text-align:center;"/>');
			if(mode=="print") 
				inputTag = $j('<label class="middle">'+data.value1+'</label>');
			else{
				inputTag=$j('<select class="number" tabIndex=' + (tabIndex+40)+ '/>');
				inputTag.attr('id',data.name+"_1");
				inputTag.addClass('middle');
				inputTag.attr('value',data.value1);
			}
			tdTag.append(inputTag);
			trTag.append(tdTag);			
			tdTag = $j('<td style="width:8%;text-align:center;"/>');
			if(mode=="print") 
				inputTag = $j('<label class="middle">'+data.value2+'</label>');
			else{
				inputTag=$j('<select class="number" tabIndex=' + ((tabIndex+40)*2) +'/>');
				inputTag.attr('id',data.name+"_2");
				inputTag.addClass('middle');
				inputTag.attr('value',data.value2);
			}
			tdTag.append(inputTag);
			trTag.append(tdTag);
				
			if((index+1)%2==0 || length==(index+1))
				tbodyTag.append(trTag);
		});
		resultTable.append(tbodyTag);
		tblDiv.append(resultTable);		
		tdRootTag.append(tblDiv);			
		//div for foeter title starts here
		var footerDiv = $j('<div style="float:left;;margin-left:2%;margin-right:2%; width:100%;"/>');
		var count = 0;
		if(result.analysis.footer) {
			$j(result.analysis.footer.values).each(function (footerIndex, footerdata) {
				if(count%3==0 && count!=0)
					footerDiv.append($j('<br/>'));
				var spanTag =$j('<div style="margin:0px 10px 0 10px; float:left; min-width:30%" />');	
				if(footerdata.name.trim()=="") 
					return;					
				if(mode=="print") {
					labelTag = $j('<label>'+footerdata.value+'</label>');
					spanTag.html(labelTag);
				} else{
					inputTag=$j('<input type="text" style="width:100%" class="large"/>');
					inputTag.attr('id',footerdata.name);
					inputTag.attr('value',footerdata.value);
					spanTag.html(inputTag);
				}
				footerDiv.append(spanTag);			
				count++;
			});
		}		
		tdRootTag.append(footerDiv);
		//div for report title ends here
		trRootTag.append(tdRootTag);
		content.append(trRootTag);
		return content;
	};	
}