 
 $j(document).ready(function(){
 
 var doctorId;
 var splitpatientId;
 var timeslot1;
 var scheduleId;
 $j('.fc-header-left .fc-button-agendaDay').die('click').live("click",function() {	
		$j('#AppointmentPTBContainer #btn_delete_ptb_id').show();
		$j('#AppointmentPTBContainer #btn_view_ptb_id').show();
		$j('.fc-agenda-slots tbody tr').show();
		var selecteddate=$j('.fc-header-title h2').text();
		var datesplit=selecteddate.split(',');
		var date=datesplit[1];
		var netMdBranchid=getNetmdbrId();
		var Docid=getDoctorId();
		var response = getRequestData('/youNeverWait/ws/ui/schedule/dayView/'+netMdBranchid+','+Docid+','+date.trim());
			//alert(JSON.stringify(response));
			$j(response.schedule).each(function(index,interval) {
				var timevw=interval.startTime;
				var endtime=interval.endTime;
				var status=interval.status;
				var nameid=interval.id;
				//var srisid=interval.seriesId;
				var conversion=ampmConversion(timevw);
				var conversion1=ampmConversion(endtime);
				var end = "00:30";
					$j('.fc-agenda-slots tr th').each(function() {
						var timeslot=$j(this).text();
						var frmat=conversion.slice(6,8);
						if(timeslot==conversion && conversion!=conversion1){
						 if(status=="Working Hours"){
							$j(this).nextAll('td').children('div').text("Take Your Appointment");
							$j(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
							$j(this).nextAll('td:first').children('div').attr('name',nameid);
							$j(this).nextAll('td:first').attr('style','background:#52F3FF;');
							$j(this).parents('.fc-agenda-slots tr').addClass('appointment');
							//$j(this).nextAll('td:first').children('div').attr('value',srisid);
							conversion=addTime(conversion,end,frmat);
						 }
						}					
					});
					
					
			});
			 			
			/* $j('.fc-agenda-slots tbody tr').each(function() {     
						//$j(this).not('.appointment').hide();
						$j(this).not('.appointment').css('visibility', 'hidden');
					
					}); */
					
			$j(response.appointment).each(function(index,appinterval) {
							var apptime=appinterval.startTime;
							var appointId=appinterval.id;
							var currentdate=appinterval.startDate;
							var realapptime=ampmConversion(apptime);
							var Idpatient=$j("#select").val();
							Idpatient=Idpatient.split("_")[0];
							var appopatientid=appinterval.patientId;
							
						$j('.fc-agenda-slots tr th').each(function() {
						var timeslotapp=$j(this).text();
						
						
							if(timeslotapp==realapptime&&date.trim()==currentdate){
								if(appopatientid==Idpatient){
									$j(this).nextAll('td:first').children('div').text("Your Appointment");
									$j(this).nextAll('td:first').attr('value',appointId);
									}
								else{
								    $j(this).nextAll('td:first').children('div').text("Reserved");
									}
								$j(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
								$j(this).nextAll('td:first').attr('style','background:#ADDFFF;');
								}
							});
							});		
			
	});
	
	
	$j('.fc-header-right .fc-button-today').die('click').live("click",function() {
		$j('#AppointmentPTBContainer #btn_delete_ptb_id').show();
		$j('#AppointmentPTBContainer #btn_view_ptb_id').show();
		//$j('.fc-agenda-slots').show();
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
		$j('.fc-agenda-slots tr th').nextAll('td').removeAttr('style');
		$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
		$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').css('visibility', 'visible');
		//$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').show();
		//$j('.fc-agenda-slots tbody tr').show();
		$j('.fc-header-left .fc-button-agendaDay').trigger('click'); 
	});
	$j('.fc-header-right .fc-button-prev').die('click').live("click",function() {
		//	$j('.fc-agenda-slots').show();
		$j('#AppointmentPTBContainer #btn_delete_ptb_id').show();
		$j('#AppointmentPTBContainer #btn_view_ptb_id').show();
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
		$j('.fc-agenda-slots tr th').nextAll('td').removeAttr('style');
		$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
		$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').css('visibility', 'visible');
		//$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').show();
		//$j('.fc-agenda-slots tbody tr').show();
		$j('.fc-header-left .fc-button-agendaDay').trigger('click'); 
	});
	$j('.fc-header-right .fc-button-next').die('click').live("click",function() {
		//$j('.fc-agenda-slots').show();
		$j('#AppointmentPTBContainer #btn_delete_ptb_id').show();
		$j('#AppointmentPTBContainer #btn_view_ptb_id').show();
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
		$j('.fc-agenda-slots tr th').nextAll('td').removeAttr('style');
		$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
		$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').css('visibility', 'visible');
		//$j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').show();
		//$j('.fc-agenda-slots tbody tr').show();
		$j('.fc-header-left .fc-button-agendaDay').trigger('click'); 
	});
	
	
	
	$j('.fc-header-left .fc-button-agendaWeek ').die('click').live("click",function() {	
	$j('#AppointmentPTBContainer #btn_delete_ptb_id').hide();
	$j('#AppointmentPTBContainer #btn_view_ptb_id').hide();
	});	
	
	$j('.fc-agenda-slots tbody tr td ').die('click').live("click",function() {	
		var lengthDiv=$j(this).children('div').text().length; 
		timeslot1=$j(this).closest('tr').children('th').text();
		scheduleId=$j(this).children('div').attr('name');
		var slotdataText=$j(this).text();
		if(slotdataText=="Take Your Appointment"){
		//if(lengthDiv<2){
			var selecteddate=$j('.fc-header-title h2').text();
			var datesplit=selecteddate.split(',');
			var date=datesplit[1];
			var obj1=$j(this);	
			createModal(constants_newappointmentJson,'appointmentModal');		
			openModalBox(obj1,'appointmentModal');
			$j("#newpatintAppointmentForm #Patientappointment").text(date);
			$j("#newpatintAppointmentForm #Patientappointmenttime").text(timeslot1);
			}
			else{
		  	if($j(this).attr('selected')) {
				$j(this).removeAttr('selected');
				$j(this).removeAttr('style');
			} 
			else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
			}
				removeErrors();
		}
			
				
	 });
	 
	 $j('#newpatintAppointmentForm #btnDone').die('click').live("click",function() {	
		//alert("ok");
		var patientapp=createSubmitJson(scheduleId,timeslot1);
			var patient="Your Appointment";
			var headerdetal=patientAppointment()
			var appointmentJson='{"header"'+":"+headerdetal+",";
			appointmentJson+='"appointmentDetails"'+":"+patientapp+"}";
			//alert(appointmentJson);
			var appointmentResponse=postdataToServer("/youNeverWait/ws/ui/patient/createAppointmentFromPortal",appointmentJson);	
			//alert(JSON.stringify(appointmentResponse));		
	    if(appointmentResponse.success==true){
			$j('.fc-agenda-slots tr th').each(function() {
			var timeslotapp=$j(this).text();
			
			var appId=appointmentResponse.globalId;
			
			if(timeslotapp==timeslot1){
				$j(this).nextAll('td:first').children('div').text(patient);
				$j(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
				$j(this).nextAll('td:first').attr('style','background:#ADDFFF;');
				$j(this).nextAll('td:first').attr('value',appId);
				}
			});
			$j("#newpatintAppointmentForm").trigger('reveal:close');
			 }
			
	else {
			updateTipsNew(getErrorName(appointmentResponse.error),$j('#error'));
		  		   $j("#error").delay(1100).fadeOut(1100);
		}		
	 
	 });
	 
	$j('#newpatintAppointmentForm #btnCancel').die('click').live("click",function() {	
		$j("#newpatintAppointmentForm").trigger('reveal:close');
	});
	  
	 $j('#AppointmentPTBContainer #btn_delete_ptb_id').die('click').live('click',function(){
		var pgTableName=".fc-agenda-slots";
		removeErrors();
		var appointmentId = getSelectedappointmentId(pgTableName);
		//alert(appointmentId);
		if(appointmentId!="") {
			var response = getRequestData('/youNeverWait/ws/ui/patient/deleteAppointmentFromPortal/'+ appointmentId);
			//alert(JSON.stringify(response));
			if(response.success==true){
				var appid=response.id;
				//$j(pgTableName).dataTable().fnDeleteRow($j('#'+facilityId).closest('tr')[0]);	
				showTip("Appointment deleted Successfully");
				$j('tr td[selected] div').text("Take Your Appointment");
				$j('tr td[selected]').attr('style','background:#52F3FF;');
				$j('tr td').removeAttr('selected');
				//defaultData=getDefaultData();
			}	
			else
				updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	 
		});
		
		$j('#AppointmentPTBContainer #btn_view_ptb_id').die('click').live('click',function(){
				var obj1=$j(this);	
				var pgTableName=".fc-agenda-slots";
				removeErrors();
				var appointmentId = getSelectedappointmentId(pgTableName);
				if(appointmentId!=""){
				var time=$j('tr td[selected]').closest('tr').children('th').text();
				createModal(constants_viewappointmentJson,'viewappointmentModal');		
				openModalBox(obj1,'viewappointmentModal');
				$j("#viewpatintAppointmentForm #Patientappointment").text(time);
				//$j("#viewpatintAppointmentForm #from").timepicker();
				$j("#viewpatintAppointmentForm #Startdate").datepicker();
				var selecteddate=$j('.fc-header-title h2').text();
				var datesplit=selecteddate.split(',');
				var date1=datesplit[1];
				var date=$j("#viewpatintAppointmentForm #Startdate").val(date1);
				filltimeList("#from",date1);
				}
		});
		
		$j('#viewpatintAppointmentForm #btnEdit').die('click').live('click',function(){
			
			$j("#viewpatintAppointmentForm #viewappointment").hide();
			$j('#viewpatintAppointmentForm #btnEdit').hide();
			$j('#viewpatintAppointmentForm #updatedetails').show();
			$j('#viewpatintAppointmentForm #btnDone').show();
		
		
		});
		
		$j('#viewpatintAppointmentForm #btnDone').die('click').live('click',function(){
			
			var patientapp=createUpdateSubmitJson(scheduleId);
			var patient="Your Appointment";
			var headerdetal=patientAppointment()
			var appointmentJson='{"header"'+":"+headerdetal+",";
			appointmentJson+='"appointmentDetails"'+":"+patientapp+"}";
			var appointmentResponse=postdataToServer("/youNeverWait/ws/ui/patient/updateAppointmentFromPortal",appointmentJson);	
			//alert(JSON.stringify(appointmentResponse));				
	    if(appointmentResponse.success==true){
			$j('.fc-agenda-slots tr th').each(function() {
			var timeslotapp=$j(this).text();
			var appId=appointmentResponse.id;
			var time=appointmentResponse.startTime
			var realTime=ampmConversion(time);
			if(timeslotapp==realTime){
				$j(this).nextAll('td:first').children('div').text(patient);
				$j(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
				$j(this).nextAll('td:first').attr('style','background:#ADDFFF;');
				$j(this).nextAll('td:first').attr('value',appId);
				$j('tr td[selected] div').text("Take Your Appointment");
				$j('tr td[selected]').attr('style','background:#52F3FF;');
				$j('tr td').removeAttr('selected');
				}
			});
			$j("#viewpatintAppointmentForm").trigger('reveal:close');
			 }
			
	else {
			updateTipsNew(getErrorName(appointmentResponse.error),$j('#error'));
		  		   $j("#error").delay(1100).fadeOut(1100);
		}
		
		});
		
		$j('#viewpatintAppointmentForm #btnCancel').die('click').live('click',function(){
			$j("#viewpatintAppointmentForm").trigger('reveal:close');
		});
		
		$j("#viewpatintAppointmentForm #Startdate").live('change',function(){
			$j("#viewpatintAppointmentForm #from").text("");
			var currentDate=$j("#viewpatintAppointmentForm #Startdate").val();
			filltimeList("#from",currentDate);
		});
		
	});	
	
	function filltimeList(controlObj,date) {
   
		var currentDate=date;
		var netMdBranchid=getNetmdbrId();
		var Docid=getDoctorId();
		var response = getRequestData('/youNeverWait/ws/ui/schedule/dayView/'+netMdBranchid+','+Docid+','+currentDate.trim());
		//alert(JSON.stringify(response));
		
		var timeInterval = "00:30";
		$j(response.schedule).each(function(index,interval) {
			var startTime = ampmConversion(interval.startTime);
			var endTime = ampmConversion(interval.endTime);
			var amOrpm =startTime.slice(6,8);
			while(startTime!=endTime) {	
				var status = true;
				$j(response.appointment).each(function(index,appointment) {
					if(startTime == ampmConversion(appointment.startTime)) {
						status=false;
						return false;
					}
				});		
				if(status==true)
					$j(controlObj).append('<option >' + startTime + '</option>');	
				startTime = addTime(startTime,timeInterval,amOrpm);
			}
		});	
	}
	function getIdfornetmd(parentid){
	  splitpatientId=parentid;
	}
	
	function setDoctorid(doctorid){
				doctorId=doctorid;
	}	
	function  getNetmdbrId(){
	var netmdbrchId=splitpatientId.split("_")[0];
	return netmdbrchId;
	}
	
	function  getDoctorId(){
		return doctorId;
	}
	
	function patientAppointment(){
	var netmdId=splitpatientId.split("_")[1];
	var netmdbranchId=splitpatientId.split("_")[0];
	var patientappointmentDetails = '{"netMdId":'+ netmdId +',';
			patientappointmentDetails += '"passPhrase":"",';
			patientappointmentDetails +='"macId":"",';
			patientappointmentDetails +='"netMdBranchId":'+netmdbranchId+'';
			patientappointmentDetails += '}';
		return patientappointmentDetails;
	
	}	
	
	function createSubmitJson(scheduleId,timeslot1){
	
		var selecteddate=$j('.fc-header-title h2').text();
		var datesplit=selecteddate.split(',');
		var date=datesplit[1];
			date=$j.trim(date);
		var patientId=$j("#select").val();
			patientId=patientId.split("_")[0];
		var appointmentDetails = '{"patientId":'+ patientId +',';
			appointmentDetails += '"doctorId":'+ doctorId +',';
			appointmentDetails +='"scheduleId":' + scheduleId+',';
			appointmentDetails +='"startTime":"' + timeslot1+ '",';
			appointmentDetails +='"startDate":"'+date+'",';
			appointmentDetails +='"patientName":"' + $j("#select :selected").text() + '"';
			appointmentDetails += '}';
		return appointmentDetails;

	}	
	
	function createUpdateSubmitJson(scheduleId){
	
		var date=$j('#viewpatintAppointmentForm #Startdate').val();
			date=$j.trim(date);
		var patientId=$j("#select").val();
			patientId=patientId.split("_")[0];
			var pgTableName=".fc-agenda-slots";
			var appointmentId = getSelectedappointmentId(pgTableName);
		var appointmentDetails = '{"id":'+ appointmentId +',';
			appointmentDetails += '"patientId":'+ patientId +',';
			appointmentDetails += '"doctorId":'+ doctorId +',';
			appointmentDetails +='"scheduleId":' + scheduleId+',';
			appointmentDetails +='"startTime":"' + $j('#viewpatintAppointmentForm #from').val()+ '",';
			appointmentDetails +='"startDate":"'+date+'",';
			appointmentDetails +='"patientName":"' + $j("#select :selected").text() + '"';
			appointmentDetails += '}';
		return appointmentDetails;

	}	
	
	function getSelectedappointmentId(pgTableName){
		var appointmentId="";
		//if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selappointment = $j(pgTableName + ' tbody tr td[selected]');
			if(selappointment.length==0){		
				updateTipsNew("Select atleast one appointment",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selappointment.length>1) 
				updateTipsNew("Select only one appointment",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				appointmentId=selappointment.val();
				
		//}
		return appointmentId;
	}
	
		
			
	function ampmConversion(time){
				var splitstrttme=time.slice(0,5);
				var splitstrttme1=time.slice(6,8);
				if((splitstrttme1)=="AM")
				{
				splitstrttme1="am";
				}
				else
				{
				splitstrttme1="pm";
				}
				var strttime=splitstrttme+" "+splitstrttme1;
				return strttime;
	}
	
	var splitTimeStr = function(t){
			var t = t.split(":");
			var timeslot=t[1].slice(0,2);
			t[0] = Number(t[0]);
			t[1] = Number(timeslot);	
			return t;
		};

		var addTime = function(t1, t2,format){
			var t1Hr = splitTimeStr(t1)[0];
			var t1Min = splitTimeStr(t1)[1];
			var t2Hr = splitTimeStr(t2)[0];
			var t2Min = splitTimeStr(t2)[1];
			var rHr = t1Hr + t2Hr;
			var rMin = t1Min + t2Min;
			var check=rHr+"."+rMin;
			
			
			if (rMin >= 60)
			{
				rMin = rMin - 60;
				rHr = rHr + 1;
			}
			if(check > 12.30)
			{
			rHr = rHr - 12
			}
			var timefrmat=rHr + ":" + rMin;
			if(timefrmat == "12:0")
			{ 
			
			  if(format=="am"){
			  
			   format="pm";
			   }
			  else
			   format="am";

			}
			
			if (rMin < 10) rMin = "0" + rMin;
			if (rHr < 10) rHr = "0" + rHr;
			return "" + rHr + ":" + rMin+" "+format;
		};
	
	
	
