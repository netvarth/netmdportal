function stoolLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var content = $j('<div/>');
		var fstDiv = $j('<div  height="20px;" style="font-size:18px; line-height:40px;" align="center"> STOOL ANALYSIS</div>');
		content.append(fstDiv);
		var SecDiv = $j('<div class="five_sixth"/>');
	    var table=$j('<table id="SpHgtTable" style="width:550px;"/>');
		var tablebody=$j('<tbody/>')
		var count;
		$j(result.values).each(function(index,tblVal){
		    var row=$j('<tr/>');
			var rowColumns=$j('<td width="35%" >'+tblVal.testExamine+'</td>');
			row.append(rowColumns);
			 var rowColumns=$j('<td/>');
			 var spanTag=$j('<span />');
			 if(mode=="print") {
				var lblTag = $j('<label/>');
				lblTag.html(tblVal.value);
				spanTag.append(lblTag);
			}
			else{
				 var inputTag=$j('<input type="text" class="'+tblVal.className+'"  style="font-size:15px;"id="Stvalue_' + index +'_1" value="' + tblVal.value + '"/>');
				 spanTag.append(inputTag);
			}
            rowColumns.append(spanTag);			
			row.append(rowColumns);
			tablebody.append(row);			
		});
		var newrow=$j('<tr><td colspan="2" align="center" style="font-size:18px;" class="microscopy">Microscopy</td></tr>');
		tablebody.append(newrow);
		$j(result.testMicrosopy).each(function(index,tblVal){
			var row=$j('<tr/>');
			var column=$j('<td colspan="2"></td>');
			var spanTag=$j('<span />');
			if(mode=="print") {
					var lblTag = $j('<label/>');
					lblTag.html(tblVal.MSresult);
					spanTag.append(lblTag);
			}
			else{
				var inputTag=$j('<input type="text" id="StMicro_' + index +'_1" class="'+tblVal.className+'" value="' + tblVal.MSresult + '"/>');
				spanTag.append(inputTag);
			}			
			column.append(spanTag);
			row.append(column);
			if(mode=="print") {
				if(tblVal.MSresult.trim()!="") 
					tablebody.append(row); 
				
			} else
				tablebody.append(row);
		});
		var lastrow=$j('<tr><td> Occultblood</td></tr>');
		var newRow=$j('<td/>');
		var spanTag=$j('<span />');
		if(mode=="print") {
			var lblTag = $j('<label/>');
			lblTag.html(result.Occubld);
			spanTag.append(lblTag);
		}else{
			var inputTag=$j('<input type="text" id="StOccubld_0_1" style="font-size:15px;" class="medium common" value="' + result.Occubld + '"/>');
			spanTag.append(inputTag);
		}
		newRow.append(spanTag);
		lastrow.append(newRow);				
		tablebody.append(lastrow);
		table.append(tablebody);
		SecDiv.append(table);
		content.append(SecDiv);
		return content;
	};

}