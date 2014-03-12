function LipidLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$j('<div align="center" />');
	var titleSection =$j('<div align="center" style="font-size:18px; line-height:40px;">'+result.testName+'</div>');
	var table;
	if(honorific=="Animal"){
		if(mode!="print"){
			var spanTag=$j('<span style="float:right; margin-right:10px;font-size:14px; line-height:6px;">Manual calculation</span>');
			 var inputTag=$j('<input id="manualCalc" type="checkbox" />')
			 if(result.checked==true)
			   inputTag.attr('checked','true');
			spanTag.append(inputTag);
			titleSection.append(spanTag);
		}
		content.append(titleSection);
		var table=$j('<table width="100%" id="SpHgtTable" />');
		var thead=$j('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			if(mode=="print" && data.id=="LDL"){
			  if(result.checked==true )
					var column=$j('<td style="width:40%">'+data.name+' (Direct)</td>');
				else
					var column=$j('<td style="width:40%">'+data.name+'</td>');			
			}
			else
			var column=$j('<td style="width:40%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$j.parseJSON(testInfo.result);
			column=$j('<td class="middle"/>');
			if(mode=="print") {
				elementTag = $j('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			} else {
				var inputTag=$j('<input type="text" class="'+data.className+'" id="LPT' + index +'_1"  value="' + data.fieldval + '"/>');
				  column.append(inputTag);
				
			}		
			var spanTag=$j('<span>'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			  if(index==4 && result.checked==true ){
				 return;
			  }
			  else
			   tbody.append(row);
			  });
			  table.append(tbody);
	}
	else {
	     	if(mode!="print"){
			var spanTag=$j('<span style="float:right; margin-right:10px;font-size:14px; line-height:6px;">Manual calculation</span>');
			 var inputTag=$j('<input id="manualCalc" type="checkbox" />')
			 if(result.checked==true)
			   inputTag.attr('checked','true');
			spanTag.append(inputTag);
			titleSection.append(spanTag);
		}
		content.append(titleSection);
		var table=$j('<table width="100%" id="SpHgtTable" />');
		var thead=$j('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			if(mode=="print" && data.id=="LDL"){
			  if(result.checked==true )
					var column=$j('<td style="width:40%">'+data.name+' (Direct)</td>');
				else
					var column=$j('<td style="width:40%">'+data.name+'</td>');			
			}
			else
			var column=$j('<td style="width:40%">'+data.name+'</td>');
			row.append(column);
			var testId=getTestId(data.name);
			var testInfo = getTestData(testId);
			var resultInfo=$j.parseJSON(testInfo.result);
			column=$j('<td class="middle"/>');
			if(mode=="print") {
				elementTag = $j('<label />')
				elementTag.text(data.fieldval);
				column.append(elementTag);
			} else {
				var inputTag=$j('<input type="text" class="'+data.className+'" id="LPT' + index +'_1"  value="' + data.fieldval + '"/>');
				  column.append(inputTag);
				
			}		
			var spanTag=$j('<span>'+resultInfo.testUnit+'</span>');
			column.append(spanTag);
			row.append(column);
			var newcolumn=$j('<td style="width:35%" />');
			if(index==4){
				if(mode=="print") {
					elementTag = $j('<label />')
					elementTag.text(data.fieldvalue);
					newcolumn.append(elementTag);
				}
				else{
				var inputTagNew=$j('<input type="text" id="LPT4_2" value="'+data.fieldvalue+'"/>');
				newcolumn.append(inputTagNew);
				}
			}
			else{
				var spanTag=$j('<span/>');
				spanTag.append(testInfo.normalRange);
				newcolumn.append(spanTag);
				}
			  row.append(newcolumn);
			  if(index==4 && result.checked==true ){
				 return;
			  }
			  else
			   tbody.append(row);
			  });
			  table.append(tbody);
		 
	}		 
	content.append(table);
	return content;
		};

}