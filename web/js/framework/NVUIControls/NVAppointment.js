function appointment(contents) {
this.result =function(){ 
		var content =$j('<div/>');
		var div=$j('<div></div>');
		var todaydiv=$j('<div class="todaysapp  "><label style="font-weight: bold;margin-top: -4px;display: block;text-align: center;">Today'+"'"+'s Appointment(s)</label></div>');
		var currweekdiv=$j('<div class="todaysapp "><label style="font-weight: bold;margin-top: -4px;display: block;text-align: center;">This Week'+"'"+'s Appointment(s)</label></div>');
		var futurediv=$j('<div class="todaysapp " ><label style="font-weight: bold;margin-top: -4px;display: block;text-align: center;">Future Appointment(s)</label></div>');
		var pastdiv=$j('<div class="todaysapp " ><label style="font-weight: bold;margin-top: -4px;display: block;text-align: center;">Past Appointment(s)</label></div>');
		if((contents.todaysAppointment)!=""){
			div.append(todaydiv);
			var subdiv=$j('<div class=" list"style="overflow: hidden;"><label class="dochead">Doctor</label><label class="doctime">Time Slot</label></div>');
			$j(contents.todaysAppointment).each(function(ResultContentIndex,today){
			var dateapp=today.date;
			var realdate=dateapp.split("/")[0];
			var day=realdate.slice(0,4);
			var date=realdate.slice(4,6);
			var month=dateapp.split("/")[1];
			var year=dateapp.split("/")[2];
			var specialisation="";
			if(today.doctorSpecialisation==null){specialisation="";}else{specialisation=today.doctorSpecialisation}
				var newdiv=$j('<div class="appDetail" style="height: 8%;overflow: hidden;"><div class="column-left" style="margin: 0px 15px 0px 15px;"><label>Dr.'+today.doctorFirstName+'</br>'+specialisation+'</label></div><div class="column-third" ><label>'+today.startingTime+' '+day+''+month+' '+date+','+year+'</label></div></div>');
				subdiv.append(newdiv);
			}); 
          div.append(subdiv);			
		 }
		 else{div.append(todaydiv);
			var subdiv=$j('<div style="font-weight: bold;" align="center">There are no appointments</div>');
				div.append(subdiv);	
		 }
		 if((contents.currentWeeksAppointment)!=""){
			div.append(currweekdiv);
			var subdivcurr=$j('<div class="list"style="overflow: hidden;"><label class="dochead">Doctor</label><label class="doctime">Time Slot</label></div>');
			$j(contents.currentWeeksAppointment).each(function(ResultContentIndex,today){
			var dateapp=today.date;
			var realdate=dateapp.split("/")[0];
			var day=realdate.slice(0,4);
			var date=realdate.slice(4,6);
			var month=dateapp.split("/")[1];
			var year=dateapp.split("/")[2];
			var specialisation="";
			if(today.doctorSpecialisation==null){specialisation="";}else{specialisation=today.doctorSpecialisation}
				var newdiv=$j('<div class="appDetail" style="height: 8%;overflow: hidden;"><div class="column-left"style="margin: 0px 15px 0px 15px;;" ><label>Dr.'+today.doctorFirstName+'</br>'+specialisation+'</label></div><div class="column-third" ><label>'+today.startingTime+' '+day+''+month+' '+date+','+year+'</label></div></div>');
				subdivcurr.append(newdiv);
		 }); 
		 div.append(subdivcurr);
		 }
		 else{div.append(currweekdiv);
		 var subdiv=$j('<div style="font-weight: bold;" align="center">There are no appointments</div>');
				div.append(subdiv);	
		 }
		
		if((contents.futureAppointment)!=""){
			div.append(futurediv);
			var subdivfutur=$j('<div class=" list"style="overflow: hidden;"><label class="dochead">Doctor</label><label class="doctime">Time Slot</label></div>');
			$j(contents.futureAppointment).each(function(ResultContentIndex,today){
			var dateapp=today.date;
			var realdate=dateapp.split("/")[0];
			var day=realdate.slice(0,4);
			var date=realdate.slice(4,6);
			var month=dateapp.split("/")[1];
			var year=dateapp.split("/")[2];
			var specialisation="";
			if(today.doctorSpecialisation==null){specialisation="";}else{specialisation=today.doctorSpecialisation}
				var newdiv=$j('<div class="appDetail" style="height: 8%;overflow: hidden;"><div class="column-left"style="margin: 0px 15px 0px 15px;;" ><label>Dr.'+today.doctorFirstName+'</br>'+specialisation+'</label></div><div class="column-third" ><label>'+today.startingTime+' '+day+''+month+' '+date+','+year+'</label></div></div>');
				subdivfutur.append(newdiv);
		 }); 
		 div.append(subdivfutur);
		 }
		 else{div.append(futurediv);
		 var subdiv=$j('<div style="font-weight: bold;" align="center">There are no appointments</div>');
				div.append(subdiv);	
		 }
		
		 //if((pastapp.pastAppointments)!=""){
			div.append(pastdiv);
			
			var subpastdiv=$j('<div class="list pastappointment"style="display:none;overflow: hidden;"></div>');
			
		 div.append(subpastdiv);
		// }
		
		 content.append(div);
		/* if((pastapp.pastAppointments=="")&&(contents.todaysAppointment==""))
		{
		var msg=$j('<div style="font-weight: bold;" align="center">There are no appointments</div>');
		content.append(msg);
		} */
		return content; 
	};
	

}	
