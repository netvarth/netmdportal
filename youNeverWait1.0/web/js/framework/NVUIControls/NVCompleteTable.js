function CompleteTable(jsondata){this.result=function(){var table=$j('<table />');table.attr('class','stdtable');var tableheading=$j('<thead><tr><th colspan="4" style="text-align:center;border: 0;"><label style="font-size:14px;"> Permissions</label></th></tr></thead>');table.append(tableheading);if(jsondata!=null){$j(jsondata.permissionDTO).each(function(index,element){var thead=$j('<thead>');thead.attr('id','thead_'+index);var headRow=$j('<tr/>');headRow.attr('class','accordion');var str=element.permissionGrpName;str=str.toLowerCase().replace(/\b[a-z]/g,function(letter){return letter.toUpperCase()});var headCol=$j('<th style="text-align:left;" colspan="4"/>');var headCheck=$j('<img class=expand_collapse name="expand" align="center" src="/weblims/images/expand.jpg">&nbsp&nbsp<label>'+str+'</label>');headCol.append(headCheck);headRow.html(headCol);thead.html(headRow);table.append(thead);var tbody=$j('<tbody/>');tbody.attr('class',element.permissionGrpName);var row=$j('<tr> <td style="text-align:left; border: 0;"><input type="checkbox" class="selectAll" id="'+element.permissionGrpName+'" name="'+element.permissionGrpName+'"/>&nbsp&nbsp<label>Select All</label></td><tr/>');tbody.append(row);var length=element.permissions.length;var rowElement;$j(element.permissions).each(function(index,value){if((index%4)==0)rowElement=$j('<tr></tr>');var column=$j('<td style="text-align:left; border: 0;"><input type="checkbox" id="'+value.name+'" name="'+value.name+'" class="'+element.permissionGrpName+'_cb-element'+'"/>&nbsp&nbsp<label>'+value.title+'</label></td>');rowElement.append(column);if((index+1)%4==0||(index+1)==length)tbody.append(rowElement)});table.append(tbody)})}return table}}