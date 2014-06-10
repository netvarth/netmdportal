function AFBLayout(honorific,result,testId,mode) {
	this.result =	function(){ 
		var table=$j('<table width="100%"/>');
		var thead=$j('<thead></thead>');
		var theadRow=$j('<tr/>');
		var tdTag=$j('<td align="center";/>');
		var breakTag=$j('<br/>');
		if(mode=="print") {
			var lblTag = $j('<label />');
			lblTag.html(result.testTitle);
			tdTag.append(lblTag);
			tdTag.append(breakTag);
		}else {
			var header=$j(' <input type="text" class="Title" style="width:100%;font-weight:bold;"  id="AFB_Title" value="'+result.testTitle+'"/>');
			tdTag.append(header);
			tdTag.append(breakTag);
		}
		theadRow.append(tdTag);
		thead.append(theadRow);
		table.append(thead);
		var tbody=$j('<tbody/>');
		$j(result.values).each(function(index,data){
			var row=$j('<tr/>');
			var column=$j('<td colspan="2" align="left"></td>');
			spanTag=$j('<span />');
			breakTag=$j('<br/>');
			if(mode=="print") {
				var lblTag = $j('<label/>');
				lblTag.html(data.fieldval);
				spanTag.append(lblTag);
				spanTag.append(breakTag);
			} else {
				var inputTag=$j('<input type="text" style="width:100%" id="AFB_' + index +'_1" />');
				inputTag.attr('value',data.fieldval);
				if(data.className)
					inputTag.attr('class',data.className);
				spanTag.append(inputTag);
				spanTag.append(breakTag);
			}			
			column.append(spanTag);
			row.append(column);
			tbody.append(row);
		});
		table.append(tbody);
		
		return table;
	};
}