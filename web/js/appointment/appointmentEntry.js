var doctorid="";
var parentid="";
$j('.createAppointment').die('click').live('click',function(){
	var docname=$j(this).parents('div.appDetail1 ').children('div.doc').children('div.docname').text();
	$j('#pageTitle').html("Create Appointment with "+docname);
		$j('.creteapp').show();		
		loadAppointmentPageToolBar();
		$j('.nextprev').hide();
		$j('#AppointmentPTBContainer #btn_delete_ptb_id').hide();
		$j('#AppointmentPTBContainer #btn_view_ptb_id').hide();
		/* $j('.appointmentInfoTab').hide();
		$j('.resultsInfoTab').hide();
		$j('.clinicsInfoTab').hide(); */
	    doctorid=$j(this).attr('id');
		//alert(doctorid);
	    parentid=$j(this).parents('div.view').attr('id');
		//alert(parentid);
	    var newappointmentVar=$j('<div/>');
		newappointmentVar.attr('id','calendar');
		newappointmentVar.attr('class','fc');
		$j('.branch').html(newappointmentVar);
	
		$j.cachedScript("/youNeverWait/js/plugins/fullcalendar.min.js").done(function(script, textStatus) {
			$j.cachedScript("/youNeverWait/js/plugins/calendar.js").done(function(script, textStatus) {	
			}).fail(function(jqxhr, settings, exception) {
			  alert(exception + jqxhr.status);
			});
		}).fail(function(jqxhr, settings, exception) {
		  alert(exception + jqxhr.status);
		});
	 	
		$j.cachedScript(constant_patientAppointmentFunctions_Url).done(function(script, textStatus) {
				getIdfornetmd(parentid);
				setDoctorid(doctorid);
		});
		$j('.fc-header-left .fc-button-agendaDay').trigger('click'); 
		
	//monthlyviewSchedule(doctorid,parentid);	
		
	
	
	});
	
	$j('.fc-header-left .fc-button-month ').die('click').live("click",function() {	
		
		$j('#AppointmentPTBContainer #btn_delete_ptb_id').hide();
		$j('#AppointmentPTBContainer #btn_view_ptb_id').hide();
		$j('.fc-border-separate tr td ').removeAttr('style');
		$j('.fc-border-separate tr td .fc-day-number').nextAll('div.fc-day-content').text("");
		monthlyviewSchedule(doctorid,parentid);	
		
	});
	
	function monthlyviewSchedule(doctorid,parentid){
	
	var selectedmonyr=$j('.fc-header-title h2').text();
	var monyersplit=selectedmonyr.split(' ');
var month=monyersplit[0];
var realmonth=convertToIntMonth(month);
if(realmonth<10){realmonth="0"+realmonth;}
var yer=monyersplit[1];
	 var branchId=parentid.split("_")[0];
	 var startdate=getLastDateOfMonth(yer,realmonth);
	 var currentstartdate=startdate.getDate();
	 var CurntMonthlastDate=yer+'-'+realmonth+'-'+currentstartdate;
	 CurntMonthlastDate=$j.trim(CurntMonthlastDate);
	 var CurntMonthfirstDate=yer+'-'+realmonth+'-'+'01';
	 CurntMonthfirstDate=$j.trim(CurntMonthfirstDate);
	 var monthlyResponse=getRequestData('/youNeverWait/ws/ui/schedule/monthlyView/'+ branchId +','+ doctorid +','+ CurntMonthfirstDate +','+ CurntMonthlastDate);
	
	if(monthlyResponse.success==true){
	//alert(JSON.stringify(monthlyResponse.scheduleList));
		$j(monthlyResponse.scheduleList).each(function(index,interval) {
			$j(interval.appointment).each(function(val,appointment) {
			var patntName=appointment.patientName;
			var dayvalue=appointment.startDate;
			dayvalue=dayvalue.slice(8,10);
			var patientName=$j("#select :selected").text();
			 if(dayvalue<10){dayvalue=dayvalue.slice(1,2);}
				$j('.fc-border-separate tr td .fc-day-number').each(function() {
						var dayofschedule=$j(this).text();
								if(dayvalue==dayofschedule){
									if(patntName==patientName){
									//alert("in ur appoint");
									$j(this).nextAll('div.fc-day-content').text("Your Appointment");
									}
								
								/* var len=$j(this).next('div .fc-day-content').text();
									if(len=="Your Appointment"){
										alert("ur patnt colr");
										$j(this).parent('div').parent('td').not('.fc-other-month').attr('style','background:#ADDFFF;');
									}
									else{
									alert("else");
										$j(this).parent('div').parent('td').not('.fc-other-month').attr('style','background:#52F3FF;');	
									} */
									}
				});
										
			});
		
		});
		
	}	
	
	
	}
	
	
	function loadAppointmentPageToolBar() {
			var ptbdata =getRequestData('/youNeverWait/json/toolbars/patientappointmentToolbar.json');
			var ptbContainer = $j('<div id="AppointmentPTBContainer"/>');
			var ptb = new PageToolBar(ptbdata);
			$j(ptbContainer).append(ptb.result);
			$j('#pageToolBar-Container').html(ptbContainer);
}

function getLastDateOfMonth(Year,Month){
		return(new Date((new Date(Year, Month,1))-1));
	}
	
function convertToIntMonth(monText) {
//Your arrays for the valid months for which you can throw exception (I did not do it) if the parameter is out of these 12.
var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var monInt;
for (i = 0; i < 12; i++) {
//in this loop, we'll check for the match in parameter and array items.
if (monText == monthNames[i])
{
//If match is found, we'll return the +1 value of actual match (as month array's index goes from 0 to 11, and we require it from 1 to 12)
monInt=i+1}
}
return monInt;
}	