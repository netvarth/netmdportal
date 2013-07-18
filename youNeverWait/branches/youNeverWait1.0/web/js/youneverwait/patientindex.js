$j = jQuery.noConflict();/* avoid conflicts of js--*/
$j.ajaxSetup({async:false});
$j.cachedScript = function(url, options) {
  // allow user to set any option except for dataType, cache, and url
  options = $j.extend(options || {}, {
    dataType: "script",
    cache: true,
    url: url
  });
  // Use $j.ajax() since it is more flexible than $j.getScript
  // Return the jqXHR object so we can chain callbacks
  return $j.ajax(options);
};

$j.cachedScript("/youNeverWait/js/youneverwait/common/constants.js").done(function(script, textStatus) {
}).fail(function(xhr,status,exception){
alert(exception);
})
$j.cachedScript("/youNeverWait/js/resource/Constants.js").done(function(script, textStatus) {
}) .fail(function(xhr,status,exception){
alert(exception);
})
$j.cachedScript("/youNeverWait/js/youneverwait/ui/PatientUIStartup.js").done(function(script, textStatus) {
}).fail(function(xhr,status,exception){
alert(exception);
})
$j.cachedScript("/youNeverWait/js/youneverwait/ui/PatientTABCreator.js").done(function(script, textStatus) {
}).fail(function(xhr,status,exception){
alert(exception);
})
$j.cachedScript("/youNeverWait/js/youneverwait/ui/PatientAppointmentVw.js").done(function(script, textStatus) {
}).fail(function(xhr,status,exception){
alert(exception);
})
$j.cachedScript("/youNeverWait/js/youneverwait/ui/PatientResultVw.js").done(function(script, textStatus) {
}).fail(function(xhr,status,exception){
alert(exception);
})
$j.cachedScript("/youNeverWait/js/youneverwait/ui/PatientClinicsView.js").done(function(script, textStatus) {
}).fail(function(xhr,status,exception){
alert(exception);
})
$j.cachedScript("/youNeverWait/js/resource/ServerUrlProcessor.js").done(function(script, textStatus) {
}) 
$j.cachedScript("/youNeverWait/js/framework/RequestHandler.js").done(function(script, textStatus) {
}) 
$j.cachedScript("/youNeverWait/js/framework/general.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/youneverwait/common/common.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/invokeUIControls.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/createModal.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/validations.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/notifier.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/patientLogout.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/patientChangePwd.js").done(function(script, textStatus) {
})

var errorData = getErrorData();
var constant = new Constants();
var ajaxProcessor=new ServerUrlProcessor();
$j(document).ready(function(){
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/getCurrentUser');
	$j('#userloged').html(userdata);
	var status = fillpatientList("#select");
	var patientUI = new PatientUIStartup();
		patientUI.init();
		
	$j.cachedScript(constant_patientappointmentEntry_Url).done(function(script, textStatus) {
				
	});
	
	$j(".headright #dropdownOne #dd").die('click').live("click",function() {
		$j(this).toggleClass('active');

	});
	
	$j('.todaysapp').die('click').live('click',function(){
		$j(this).next('div').toggle();
	});
	
	$j('.doctor').die('click').live('click',function(){
		var obj=$j(this);
		var flag=false;
		var message="";
		if(obj.hasClass('doctorbeforeswitch')){
			obj.removeClass('doctorbeforeswitch').addClass('doctorafterSwitch');
		    obj.parents('div').children('div .view').show();
			flag=true;
			}
		else{
		    obj.removeClass('doctorafterSwitch').addClass('doctorbeforeswitch');
			obj.parents('div').children('div .view').hide();
		}
		var netBranchId=obj.attr('name');
		var newdiv1;
		var response = getRequestData('/youNeverWait/ws/ui/patient/listDoctors/'+ netBranchId);
		if((response.doctor)!=""){
		    obj.parents('div').children('.view').empty();
			$j(response.doctor).each(function(index,doctor){
			if(doctor.specialisation==null){specialisation="";}else{specialisation=doctor.specialisation}
				 newdiv1=$j('<div class="appDetail1 " style="height: 8%;margin: 10px;"><div class="column-fourth doc"><div class="column-left docname"style="" >Dr.'+doctor.firstName+'</div><div class="column-right" >'+specialisation+'</div></div><div  class="column-third"><input id="'+doctor.id+'" type=button class="stdbtn createAppointment" value="Create Appointment"></div></div>');
			     obj.parents('div').children('div .view').append(newdiv1);
			});
		}
		else{
		        obj.parents('div').children('.view').empty();
		        message="Currently no doctors are available in this clinic";
				newdiv1=$j('<div class="appDetail1 "style="color:#123D8C;font-weight: bold;" align="center">'+message+'</div>');
				obj.parents('div').children('div .view').append(newdiv1);
			}		
	});
	
	
	$j('.nextbtn').die('click').live('click',function(){
		//alert("next");
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
		var	agentListJson=clinicsInfo(startValue,interval);
			var resData = new clinics(agentListJson);
		$j('#clinicsInfoTab').html(resData.result);
		currentno=$j("#prevnext").val(curPage);
			//pgAgentList=fillAgentTable(agentListJson,pgTableName);			
			//setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	
	$j('.prevbtn').die('click').live('click',function(){
	//alert("prev");
		if(curPage!=1) {
			curPage-=1;
		var startValue = interval*(curPage-1);
		var	agentListJson1=clinicsInfo(startValue,interval);
			var resData = new clinics(agentListJson1);
		$j('#clinicsInfoTab').html(resData.result);
			currentno=$j("#prevnext").val(curPage);
			//pgAgentList=fillAgentTable(agentListJson,pgTableName);				
			//setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	
	$j('#select').change(function() {
		selectAndBack()	
	});
	
	
	
	$j('#AppointmentPTBContainer #btn_back_ptb_id').die('click').live('click',function(){
			selectAndBack()			
	});

	
function selectAndBack(){
		curPage=1; 
		$j('#clinicsInfoTab').html("");
		$j('#resultsInfoTab').html("");
		$j('#appointmentInfoTab').html("");
		/* $j('.clinicsInfoTab').show();
		$j('.appointmentInfoTab').show();
		$j('.resultsInfoTab').show(); */
		$j('#pageTitle').html("");
		$j('.creteapp').hide();	
		$j('.nextprev').show();
		$j('#AppointmentPTBContainer #btn_back_ptb_id').hide();
		$j('#AppointmentPTBContainer #btn_delete_ptb_id').hide();
		$j('#AppointmentPTBContainer #btn_view_ptb_id').hide();
		var patientUI = new PatientUIStartup();
		patientUI.initsel();
}		

function fillpatientList(controlObj) {
   
		var username=getRequestData('/youNeverWait/ws/ui/auth/getCurrentUser');
		var passusername='{"email":"'+ username +'"}';
		var patientlist=postdataToServer('/youNeverWait/ws/ui/patient/listPatientsForLogin',passusername);
		//alert(JSON.stringify(patientlist));
		var add = $j(patientlist.patientList).each(function (patientIndex, patientList) {
			var value=patientList.id+'_'+patientList.email+'_'+patientList.branchId;
			var lastName;
			if(patientList.lastName==null){lastName="";}
			else{lastName=patientList.lastName;}
			var patientName=patientList.firstName+" "+lastName;
			patientName = patientName.toLowerCase().replace(/\b[a-z]/g, function(letter) {
			return letter.toUpperCase();
			});
			$j(controlObj).append('<option  value="'+value+'">'+patientName+'</option>');
		});
		
		return true;
		
}


	
});	


