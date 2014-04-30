function GTTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		if(mode=="print") {
			var content =$j('<div align="center" />');
			var titleSection = $j('<div align="center" style="font-size:18px; line-height:40px;">'+result.testName+'(<label style="margin-right:5px">'+result.testType+'</label>Oral Glucose)</div>');
			content.append(titleSection);
		} else
			var content =$j('<div align="center" >'+result.testName+'(<input type="text" id="GTT_heading" class="heading micro" value="'+result.testType+'"/>Oral Glucose)</div>');
		var table=$j('<table width="100%" id="realTable" />');
		var thead=$j('<thead><th>INVESTIGATION</th><th>BLOOD SUGAR</th><th>URINE SUGAR</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			var column=$j('<td />');
			if(mode=="print") {
				var lblTag = $j('<label/>');
				lblTag.html(data.fieldval1);
				column.html(lblTag);
			}
			else {
				var inputTag=$j('<input type="text"  id="GTT_' + index +'_1" value="' + data.fieldval1 + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1 );
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
			column=$j('<td>'  +data.BldUnit+'</td>');
			if(mode=="print") {
				var lblTag = $j('<label/>');
				lblTag.html(data.fieldval2+data.BldUnit);
				column.html(lblTag);
			}
			else{
				var inputTag=$j('<input type="text"  id="GTT_' + index +'_2" class="medium" value="' + data.fieldval2 + '"/>');
				if(data.className2)
					inputTag.attr('class',data.className2 );	
				column.prepend(inputTag);
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
			if(mode=="print") {
				var lblTag = $j('<label/>');
				lblTag.html(data.fieldval3);
				column.html(lblTag);
			}
			else{
				var inputTag=$j('<input type="text"  id="GTT_' + index +'_3" class="medium" value="' + data.fieldval3 + '"/>');
				if(data.className3)
					inputTag.attr('class',data.className3 );
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