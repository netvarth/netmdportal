
function organizationStartup() {
 this.organizationService = new OrganizationServiceImpl();
}

organizationStartup.prototype.getOrganizationService = function() {
	return this.organizationService;
}
organizationStartup.prototype.init = function() {
	var self=this;
	var organizationService=self.getOrganizationService();
	var userDetails=organizationService.getUserDetails();
	var userName=userDetails.userName;
	var organizationName=userDetails.accountName;
	var hospitalList=organizationService.getNetmdBrachesInOrganisation(userDetails.organisationId);
	var filterList=organizationService.getReportfilterList();
	var filterCheckboxlist= new FilterCheckbox(filterList);
	var result=filterCheckboxlist.result();
	$j('#filterContent').append(result);
	var graph=organizationService.getGraphicalRepresentation();
	$j('#graph').html(graph);
	//$j('#orgName').html(organizationName);
	$j('#userName').html(userName);
	self.fillMonth("#report #startMonth");
	self.fillMonth("#report #endMonth");
	self.fillYear("#report #startYear");
	self.fillYear("#report #endYear");
	self.fillHospitalList("#report #hospital",hospitalList); 
	$j("#filterContent :input").each(function(){
	 $j(this).attr('checked','checked');
	});

	self.bindEvents();
	
}

organizationStartup.prototype.bindEvents = function() {
var self=this;
    $j("#dataPoints").click(function(){
		$j("#filterContent").toggle();
	});
   $j("#selectAll").click(function () {
          $j('.filter').attr('checked', this.checked);
    });
 
    $j(".filter").click(function(){
        if($j(".filter").length == $j(".filter:checked").length) {
            $j("#selectAll").attr("checked", "checked");
        } else {
            $j("#selectAll").removeAttr("checked");
        }
 
    });
	$j('body').click(function(evt) {
    if($j(evt.target).parents('#filterContent').length==0) {
        $j('#filterContent').hide();
    }
});
	$j('#closediv').click(function(){
		$j('#filterContent').hide();
	});
		$j("#report #showreport").die('click').live('click',function(){	
		alert("clicked");
				$j("form:input").each(function(){
	 alert($j(this).val());
	});
		//self.getFilters();
	//commonMethodInvoker.removeErrors();
		var report=$j('#report #reportType option:selected').val()
		$j("#report #reportName").attr('value',report);
		
		//if(self.validateMonthandYear()){
		$j("#report").attr('method','post');
		$j("#report").attr('action',constants.REPORTGENERATEURL);
		$j("#report").attr('target','_blank');

		$j("#report").submit ( );
		
	});	
 

}
organizationStartup.prototype.getFilters = function() {
$j("#filterContent :input").each(function(){
alert(this.value);

});

}	
organizationStartup.prototype.fillMonth = function(controlObj) {
	var numbers = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
	$j.each(numbers, function(val, text) {
            $j(controlObj).append( $j('<option></option>').val(val+1).html(text) )
            });
}	

organizationStartup.prototype.fillYear = function(controlObj) {
	var firstYear = 2013;
	var lastYear = 2034;
	for(var i =firstYear; i<=lastYear; i++) {
        $j(controlObj).append( $j('<option></option>').val(i).html(i) )
	}	
}	
organizationStartup.prototype.fillHospitalList = function(controlObj,data) {
	$j(data.netmdBranch).each( function(val, text) {
            $j(controlObj).append( $j('<option></option>').val(text.globalId).html(text.name) )
            }); 
	
}	
organizationStartup.prototype.validateMonthandYear = function() {
	var bValid=true;
	var startMonth=$j("#report #startMonth").val();
	var endMonth=$j("#report #endMonth").val();
	var startYear=$j("#report #startYear").val();
	var endyear=$j("#report #endYear").val();
	if(startYear==endyear){
		if(startMonth<=endMonth){
			bValid=true;
		}
		else{
			commonMethodInvoker.createServerError($j("#errorDivHeader"),$j("#errorDivData"),"Start month must be less than end month");
			bValid=false;
		}
	}
	 if(startYear>endyear){
			commonMethodInvoker.createServerError($j("#errorDivHeader"),$j("#errorDivData"),"Start year must be less than end year");
			bValid=false;
			
	}
	return bValid;
}	