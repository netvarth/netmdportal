function CDLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$j('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$j('<table width="100%" id="SpHgtTable" />');
		var thead=$j('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			var column=$j('<td></td>');
			if(mode=="print"){
				var lblTag = $j('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else {
				var inputTag=$j('<input type="text" style="width:100%" id="CD_' + index +'_1" class="large" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval1.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$j('<td></td>');
			if(mode=="print"){
				var lblTag = $j('<label/>');
				lblTag.html(data.fieldval2);
				column.addClass('middle');
				column.html(lblTag);
			}
			else {
				var inputTag=$j('<input type="text" style="width:100%" id="CD_' + index +'_2" class="large" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2);
				column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval2.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
			column=$j('<td></td>');
			if(mode=="print"){
				var lblTag = $j('<label/>');
				column.addClass('middle');
				lblTag.html(data.fieldval3);
				column.html(lblTag);
			}
			else {
				var inputTag=$j('<input type="text" style="width:100%" id="CD_' + index +'_3" class="large" value="' + data.fieldval3 + '"/>');
			    if(data.className3)
					inputTag.attr('class',data.className3);
			    column.append(inputTag);
			}
			row.append(column);
			if(mode=="print") {
				if(data.fieldval3.trim()!=""){
					tbody.append(row);
				}	
			}	
			else
				tbody.append(row);
		  });
		table.append(tbody);
		content.append(table);
		return content;
		};
          
}