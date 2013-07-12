function LiverLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$j('<div align="center"/>');
	var titleSection=$j('<div align="center" class="resultTitle" >'+result.testName+'</div>');
	content.append(titleSection);
	var table;
	if(honorific=="Animal"){
		table=$j('<table width="100%" id="SpHgtTable" />');
		var thead=$j('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			var column=$j('<td style="width:50%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$j.parseJSON(testInfo.result);
			column=$j('<td style="width:50%"></td>');
			if(mode=="print") {
				elementTag = $j('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			}
			else{	
				var inputTag=$j('<input type="text" class="'+data.className+'" id="LFT' + index +'_1"  value="' + data.fieldval + '" tabindex=' + (index+1) + '/>');
				 if(data.readonly)	
				 inputTag.attr("readonly","readonly");
				column.append(inputTag);
			}
			var spanTag=$j('<span style="font-weight:normal">'+'&nbsp;&nbsp;'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			
			  tbody.append(row);
			  });
			  table.append(tbody);
	} 
	else{
		table=$j('<table width="100%" id="SpHgtTable" />');
		var thead=$j('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			var column=$j('<td style="width:30%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$j.parseJSON(testInfo.result);
			column=$j('<td></td>');
			if(mode=="print") {
				elementTag = $j('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			}
			else{	
				var inputTag=$j('<input type="text" class="'+data.className+'" id="LFT' + index +'_1"  value="' + data.fieldval + '" tabindex=' + (index+1) + '/>');
				 if(data.readonly)	
				 inputTag.attr("readonly","readonly");
				column.append(inputTag);
			}
			var spanTag=$j('<span style="font-weight:normal">'+'&nbsp;&nbsp;'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			var newcolumn=$j('<td style="width:40%;font-weight:normal"></td>');
			if(mode=="print") {
				newcolumn.append(space2tab(nl2br(data.OBSRDVal)));
			}
			else{
				if(testId=="T184"){
					var textarea=$j('<textarea id="LFT' + index +'_2" />');
					textarea.html(br2nl(testInfo.normalRange));
					newcolumn.append(textarea);
				}
				else{
					var inputTag=$j('<input type="text" id="LFT' + index +'_2"  value="' + br2nl(testInfo.normalRange) + '"/>');		
					newcolumn.append(inputTag);
				}
			}	
			  row.append(newcolumn);
			  tbody.append(row);
			  });
			  table.append(tbody);
		
		
	}		
		  content.append(table);
		  return content;
		};
          

}