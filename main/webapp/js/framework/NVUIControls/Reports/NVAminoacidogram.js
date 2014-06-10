function aminoacidLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $j('<div/>');
		//div for report title starts here
		var fstDiv = $j('<div align="center" class="resultTitle">'+result.testName+'</div>');
		content.append(fstDiv);
	    var table=$j('<table id="realTable" style="width:90%"/>');
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
		var newColumns=$j('<td>'+resultbody.headerOneval+'</td>');
		newRow.append(newColumns);
		newColumns=$j('<td>');
		if(mode=="print"){
			var lblTag = $j('<label/>');
			lblTag.html(resultbody.value);
			newColumns.addClass('middle');
			newColumns.html(lblTag);
		}
		else{
			var inputTag=$j('<select type="text" class="Amino tiny" id="Aminoacid' + index +'_1" tabIndex="'+(index+1)+'" value="' + resultbody.value + '"/>');
			newColumns.append(inputTag);
		}	
			newRow.append(newColumns);
			tbody.append(newRow);
		});
		
		table.append(tbody);
		content.append(table);
		//div for footer title starts here
		var footerDiv = $j('<div style="float:left;;margin-left:2%;margin-right:2%; width:100%;"/>');
		var count = 0;
		if(result.footer) {
			$j(result.footer.values).each(function (footerIndex, footerdata) {
				if(count%3==0 && count!=0)
					footerDiv.append($j('<br/>'));
				var spanTag =$j('<div style="margin:0px 10px 0 10px; float:left; min-width:30%" />');	
				if(footerdata.name.trim()=="") 
					return;					
				if(mode=="print") {
					labelTag = $j('<label>'+footerdata.value+'</label>');
					spanTag.html(labelTag);
				} else{
					inputTag=$j('<input type="text" style="width:100%" class="large"/>');
					inputTag.attr('id',footerdata.name);
					inputTag.attr('value',footerdata.value);
					spanTag.html(inputTag);
				}
				footerDiv.append(spanTag);			
				count++;
			});
		}		
		content.append(footerDiv);
		return content;
		return content;
		
		};


}