function osmoticLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $j('<div/>');
		//div for report title starts here
		var fstDiv = $j('<div  align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
	    var table=$j('<table id="SpHgtTable"/>');
		var thead=$j('<thead/>');
		var headrow=$j('<tr>');
		$j(result.Resultheaders).each(function(index,headervalue){
		  var headcol=$j('<th style="width:33%">'+headervalue.headername+'</th>');
		  headrow.append(headcol);
		});
		thead.append(headrow);
      	table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.Resultbody).each(function(index,headerbody){
		var newRow=$j('<tr/>');
		var newColumns=$j('<td />');
		if(mode=="print"){
			var lblTag = $j('<label/>');
			lblTag.html(headerbody.headerOneval);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else {
			var inputTag=$j('<input type=text  align="center" id="Osmotic_' + index +'_1" value="' + headerbody.headerOneval +'"/> ');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		var newColumns=$j('<td/>');
		if(mode=="print"){
			var lblTag = $j('<label/>');
			lblTag.html(headerbody.headerTwoval);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$j('<input type=text  align="center" id="Osmotic_' + index +'_2" tabIndex="'+(index+1)+'" value="' + headerbody.headerTwoval +'"/>');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		var newColumns=$j('<td/>');
		if(mode=="print"){
			var lblTag = $j('<label/>');
			lblTag.html(headerbody.headerThreeVal);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$j('<input type=text align="center" id="Osmotic_' + index +'_3" value="' + headerbody.headerThreeVal +'"/>');
			newColumns.append(inputTag);
		}
		newRow.append(newColumns);
		if(mode=="print") {
				if(headerbody.headerOneval.trim()!=""){
					tbody.append(newRow);
				}	
		}	
		else
			tbody.append(newRow);

		});
		table.append(tbody);
		content.append(table);
		return content;
		
		};


}