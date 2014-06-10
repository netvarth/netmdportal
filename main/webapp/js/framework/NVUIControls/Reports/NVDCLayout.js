function DCLayout(result, testId, mode,honorific) {
	this.result =	function(){ 
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			var column=$j('<td ></td>');
			if(mode=="print") {
				var lblTag = $j('<label/>');
				lblTag.html(data.key);
				column.html(lblTag);
			} else {	
				var inputTag=$j('<input type="text" id="DC_' + index +'_1"  value="' + data.key + '"/>');
				if(data.className1)
					inputTag.attr('class',data.className1);
				column.append(inputTag);
			}	
			row.append(column);
			if(mode=="print") {
				if(data.key.trim()!="")
					tbody.append(row);
			}	
			else
				tbody.append(row);
				
				column=$j('<td class="middle"></td>');
				if(mode=="print") {
					var lblTag = $j('<label/>');
					lblTag.html(data.fieldvaltwo + " " + data.unit);
					column.html(lblTag);
				} else {	
					var inputTag=$j('<input type="text" class="observed" style="float:left;" id="DC_' + index +'_2" tabindex="' + (index +1)+'" value="' + data.fieldvaltwo + '"/>');
					var label=$j('<label >' + data.unit + '</label>');					
					column.append(inputTag);
					column.append(label);
				}	
				row.append(column);
				
				if(mode=="print") {
					if(data.key.trim()!="")
						tbody.append(row);
				}	
				else
					tbody.append(row);
				if(honorific!='Animal') {
					column=$j('<td class="middle"></td>');
					if(mode=="print") {
						var lblTag = $j('<label/>');
						lblTag.html(data.fieldvalthree+" " + data.unit);
						column.html(lblTag);
					} else {	
						var inputTag=$j('<input type="text"  style="float:left;" id="DC_' + index +'_3"  value="' + data.fieldvalthree + '"/>');
						var label=$j('<label >' + data.unit + '</label>');
						if(data.className3)
							inputTag.attr('class',data.className3);
						column.append(inputTag);
						column.append(label);
					}	
					row.append(column);
					if(mode=="print") {
						if(data.key.trim()!=""){
							tbody.append(row);
						}
					}	
					else
						tbody.append(row);	
				}		
		}); 
		return tbody;
	};
}