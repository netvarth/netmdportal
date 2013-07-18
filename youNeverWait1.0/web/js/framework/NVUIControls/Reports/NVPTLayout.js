function PTLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content =$j('<div ></div>');
		var table=$j('<table width="100%" id="realTable"/>');
		var thead=$j('<thead><th>INVESTIGATION</th><th>OBSERVED VALUE</th><th>REFERENCE RANGE&UNIT</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each (function(index,data){
			var row=$j('<tr/>');
			if(data.FieldOneName){
				if(data.FieldOneName!=""){
					var column=$j('<td style="width:30%";>'+data.FieldOneName+'</td>');
					row.append(column);
				}
			}
			if(index==1){
		        var column=$j('<td rowspan="3"></td>');
				 row.append(column);
			}
			var column=$j('<td style="width:35%"/>');
			 var spanTag=$j('<div style="width:35%;float:left;">'+data.FieldTwoName+'</div>');
			column.append(spanTag);
			 var spanTag=$j('<div style="width:5%;float:left;">:</div>');
		    column.append(spanTag);
			  var spanTag=$j('<div style="width:35%;float:left;"/>');
		   if(mode=="print") {
				var lblTag = $j('<label />');
				lblTag.html(data.FieldVal +" "+data.unitVal);
				spanTag.append(lblTag);
			}else{
					var inputTag=$j('<input type="text"  id="PT_' + index +'_1" class="'+data.className+'" value="' + data.FieldVal + '"/>');
					spanTag.append(inputTag);
			}
			
		    column.append(spanTag);
			spanTag=$j('<div style="width:20%;float:left;"></div>');
				if(data.time){
					 if(mode=="print") {
						var lblTag = $j('<label />');
						lblTag.html(data.UnitType);
						spanTag.append(lblTag);
					}
					else{
					var inputTag=$j('<input type="text" class="'+data.time+'" value="'+data.unitVal+'" id="PT_'+index+'_2"/>');
					spanTag.append(inputTag);
						if(data.UnitType)
							spanTag.html(data.UnitType);
				}
			}		
		    column.append(spanTag);
		   row.append(column);
		  if(data.FieldThreeName){
			   if(data.FieldThreeName!=""){
					var column=$j('<td style="width:35%">'+data.FieldThreeName+'</td>');
				   row.append(column);
			   } 
		    } 
			 if(index==1){
				var column=$j('<td rowspan="3"></td>');
				row.append(column);
			   }
		  tbody.append(row);
		  });
		  table.append(tbody);
		  content.append(table);
		  return content;
		};
          

}