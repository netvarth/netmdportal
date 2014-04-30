function haemogramESRLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $j('<div/>');
		//div for report title starts here
		var fstDiv = $j('<div align="center" class="resultTitle"> '+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $j('<div class="five_sixth"/>');
		var table;
		if(honorific=="Animal"){
			table=$j('<table id="realTable"/>');
			var mainrow=$j('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$j('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:14%;">%VALUE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$j('<tbody/>')
			$j(result.values).each(function(index,tblVal){
				var row=$j('<tr/>');
				var rowColumns=$j('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$j('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$j('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$j('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$j('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$j('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$j('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
				rowColumns=$j('<td></td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);
		 
		}
		else {
		 	table=$j('<table id="realTable"/>');
			var mainrow=$j('<thead><tr><th rowspan="2" ></th> <th colspan="4" align="center">PATIENT\'S VALUE  </th> </tr></thead>');
			table.append(mainrow);
			var tableheading=$j('<thead ><tr><th style="width:29%">          </th><th style="width:19%">ABSOLUTE VALUE</th><th style="width:24%">NORMAL RANGE</th><th style="width:14%;">%VALUE</th><th style="width:14%">NORMAL RANGE</th></tr></thead>');
			table.append(tableheading);
			var tablebody=$j('<tbody/>')
			$j(result.values).each(function(index,tblVal){
				var row=$j('<tr/>');
				var rowColumns=$j('<td >'+tblVal.testnameval+'</td>');
				if(tblVal.testnameval=='ESR')
					rowColumns.attr('style','font-weight:bold;');
				row.append(rowColumns);
				if(mode=="print") {
					rowColumns=$j('<td class="middle"/>');
					rowColumns.html(tblVal.absoluteValue + " " + tblVal.aVUnit);
				} else {	
					rowColumns=$j('<td >'+tblVal.aVUnit+'</td>');
					var inputTag=$j('<input type="text" class="medium" id="Haemogram_' + index +'_1" value="' + tblVal.absoluteValue + '"/>');
					rowColumns.prepend(inputTag);
				}
				row.append(rowColumns);
				var rowColumns= $j('<td  class="middle">'+tblVal.normalRange+'</td>');
				row.append(rowColumns);
				
				if(tblVal.VUnit.trim()!=""){
					if(mode=="print") {
						rowColumns=$j('<td class="middle"/>');
						rowColumns.html(tblVal.value  + " " + tblVal.VUnit);
					} else {
						rowColumns=$j('<td>'+ tblVal.VUnit + '</td>');
						var inputTag=$j('<input type="text" class="medium" id="Haemogram_' + index +'_2" value="' + tblVal.value + '"/>');
						rowColumns.prepend(inputTag);
					}
				 row.append(rowColumns);
				 }
				else
					rowColumns=$j('<td></td>');
				row.append(rowColumns);
				rowColumns=$j('<td >'+tblVal.NR+'</td>');
				row.append(rowColumns);
				tablebody.append(row);
			  });
			  table.append(tablebody);
			  

	   } 
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};
			
}