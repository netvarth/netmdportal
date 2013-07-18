function PeripheralLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
	var content =$j('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$j('<table width="100%" id="realTable" />');
		var thead=$j('<thead></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
		  var row=$j('<tr/>');
		  var column=$j('<td></td>');
		  if(mode=="print"){
			var lblTag = $j('<label/>');
			lblTag.html(data.fieldval);
			column.html(lblTag);
		  }
		  else{
				var inputTag=$j('<input type="text"  style="width:100%;" id="Peripheral_' + index +'_1" value="' + data.fieldval + '"/>');
				column.append(inputTag);
			}	
		  row.append(column);
		  if(mode=="print") {
				if(data.fieldval.trim()!=""){
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