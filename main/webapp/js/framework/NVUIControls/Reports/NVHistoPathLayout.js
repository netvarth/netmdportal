function HistoPathLayout(honorific,result,testId,mode) {
	
	this.result =	function(){ 
		var content =$j('<div align="center" style="font-weight:bold">'+result.testName+'</div>');
		var table=$j('<table width="100%" height="500px" id="realTable" />');
		var thead=$j('<thead></thead>');
		table.append(thead);
		var tbody=$j('<tbody/>');
		var row=$j('<tr/>');
		var tdTag = $j('<td class="topAlign"/>');
		$j(result.values).each (function(index,data){
			if(mode=="print"){
				var lblTag = $j('<label/>');
				lblTag.html(nl2br(data.fieldval));
				tdTag.append(lblTag);	
			}
			else{
				var textarea=$j('<textarea  style="width:100%; height:500px;" id="HistoPath_' + index +'_1" >'+ data.fieldval +'</textarea>');
				tdTag.append(textarea);					
				}	
		});
		row.append(tdTag);
		tbody.append(row);
		table.append(tbody);
		content.append(table);
		return content;
	};
          

}