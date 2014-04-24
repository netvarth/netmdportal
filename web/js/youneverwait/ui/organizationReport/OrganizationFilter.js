function FilterCheckbox(jsondata) {
 this.result = function () {
	var table=$j('<table />');
	table.attr('class','stdtable');
	var tableheading=$j('<thead><tr><th colspan="4" style="text-align:center;border: 0;"></th></tr></thead>');
	table.append(tableheading);
	if(jsondata!=null) {
		var tbody=$j('<tbody/>');
			var row=$j('<tr> <td style="text-align:left; border: 0;"><input type="checkbox" class="selectAll" id="selectAll" name="selectAll"/>&nbsp&nbsp<label style="font-weight:bold;font-size:14px;font-family:Times New Roman,Georgia,Serif;">Select All</label></td><tr/>');
				tbody.append(row);
			    var length = jsondata.reportFilter.length;
				var rowElement;
				$j(jsondata.reportFilter).each(function(index,element) {
			    	if((index%4)==0)				
						rowElement=$j('<tr></tr>');					
						var column=$j('<td style="text-align:left; border: 0;"><input type="checkbox" id="'+element.name+'" name="'+element.name+'" class="filter" />&nbsp&nbsp<label style="font-weight:bold;font-size:14px;font-family:Times New Roman,Georgia,Serif;">'+element.name+'</label></td>');				
						rowElement.append(column);
  
					if((index+1)%4==0 || (index+1)==length)
						tbody.append(rowElement);	
			
				});	    
				table.append(tbody);		   	
	    }
		return table;
	};
	}

