function enaProfile(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $j('<div/>');
		//div for report title starts here
		var fstDiv = $j('<div  align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
		var SecDiv = $j('<div class="five_sixth"/>');
	    var table=$j('<table id="realTable"/>');
		var thead=$j('<thead/>');
		var headrow=$j('<tr>');
		$j(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$j('<th>'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.Resultbody).each(function(index,resultbody){
		var newRow=$j('<tr/>');
		var newColumns=$j('<td>'+resultbody.headerOneval+'</td><td> '+resultbody.headerTwoval+'</td>');
		newRow.append(newColumns);
		var newColumns=$j('<td/>');
		var inputTag=$j('<input type="text" id="Ena_' + index + '_1" value="'+resultbody.headerThreeval+'"/>');
		 if(mode=="print") {
						inputTag.addClass('readonlyStyle');
						inputTag.attr('readonly','readonly');
						inputTag.removeClass('middle');
						inputTag.addClass('column-2');
					}	
		newColumns.append(inputTag);
		newRow.append(newColumns);
		tbody.append(newRow);
		});
		
		table.append(tbody);
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
		
		};


}