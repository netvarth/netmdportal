function stone(honorific,result,testId,mode) {
this.result =function(){ 
		var content =$j('<div/>');
		var table=$j('<table width="100%" />');
		var thead=$j('<thead><th colspan="2">'+result.analysisType+'</th></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.resultContent).each(function(ResultContentIndex,resultContentVal){
			if(resultContentVal.title){
				var newrow=$j('<tr><td>'+resultContentVal.title+'</td></tr>');
				tbody.append(newrow);
			}
			newrow=$j('<tr><td></td></tr>');
			newtd=$j('<td/>');
			var newtable=$j('<table id="StoneTable"/>');
			var newtablebody=$j('<tbody/>');
			$j(resultContentVal.resultParams).each(function(index,tblVal){
				 var tablerow=$j('<tr/>');
				 var tablecolumns=$j('<td colspan="2">'+tblVal.Attribute+'<td>');
				 tablerow.append(tablecolumns);
				 if(tblVal.colon){
					tablecolumns=$j('<td >'+tblVal.colon+'<td>');
					tablerow.append(tablecolumns);
				}
				tablecolumns=$j('<td width="100%"/>');
				if(tblVal.displayType=="text"){
					var spanTag=$j('<span />');
					if(mode=="print") {
						var lblTag = $j('<label/>');
						lblTag.html(tblVal.value);
						spanTag.append(lblTag);
					}
					else{		
						var inputTag=$j('<input type="text" class="'+tblVal.className+'" id="Stone_' + index +'_1" value="' + tblVal.value + '"/>');
						spanTag.append(inputTag)			
			          }
					tablecolumns.append(spanTag);
					tablerow.append(tablecolumns);
					newtablebody.append(tablerow);
			    }
				else if(tblVal.displayType=="textarea"){
					var spanTag=$j('<span />');
					if(mode=="print") {
						var lblTag = $j('<label/>');
						lblTag.html(tblVal.value);
						spanTag.append(lblTag);
					}														
					else{		
						 var textarea=$j('<textarea class="large" id="Stone_3_1" style="width:100%" >'+tblVal.value+'</textarea>');	
						spanTag.append(textarea);						 
						
					}
					tablecolumns.append(spanTag);
					tablerow.append(tablecolumns);
					newtablebody.append(tablerow);
			    } 
			});
			newtable.append(newtablebody);
			newtd.append(newtable)
			newrow.append(newtd);
			tbody.append(newrow);   
			 
		 });
		 table.append(tbody);
		 content.append(table);
		
		return content; 
	};
}	
