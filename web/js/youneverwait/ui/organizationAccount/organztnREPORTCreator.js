function organztnReportCreator() {

	this.neworgnzReportPage = '#reportViewForm';
	this.startMonth = $j("#startMonth");
	this.startYear = $j("#startYear");
	this.endMonth = $j("#endMonth");
	this.endYear = $j("#endYear");
	this.netmdlist = $j("#netmdlist");
	this.showreportButton = $j("#showreport");
	this.errorHeader = $j("#errorDivHeader");
	this.errorData = $j( "#errorDivvwreportData");
	this.inputFields = this.neworgnzReportPage + " :input[type=text]";
}


organztnReportCreator.prototype.viewReport= function(url,parent,netMdlistdata) {
	self =this;
	ajaxProcessor.setUrl(url);
	var tabdata =ajaxProcessor.get();
	$j(parent).html(new form(tabdata).result);
	self.bindEvents(netMdlistdata);
}

organztnReportCreator.prototype.bindEvents = function(netMdlistdata) {

	self = this;
	self.fillMonth("#reportViewForm #startMonth");
	self.fillMonth("#reportViewForm #endMonth");
	self.fillYear("#reportViewForm #startYear");
	self.fillYear("#reportViewForm #endYear");

	var myDiv = $j('#netmdlist');
	var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft" name="reportfilter" id="btn_reportfilter_filter_id"><span><b>Hospitals</b></span></a>');
		var inputTag=$j('<select></select>');
		inputTag.attr('id','txtreportfilter');
		inputTag.attr('name','paramList');
		inputTag.addClass('genTextHeight');
		inputTag.attr('style','display:none');
		myDiv.append(toolbutton);
		myDiv.append(inputTag);					
	//self.fillnetMdlist("#txtreportfilter");
	
	$j( "#netmdlist a:not(:selected)").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#txt'+curObjName).show();
		self.fillnetMdlist("#txtreportfilter",netMdlistdata);
		
	});	
	$j( "#netmdlist a[selected]").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#txtreportfilter').find('option').remove()
		$j('#txt'+curObjName).hide();
		
	});	
	
	$j("#reportViewForm #showreport").die('click').live('click',function(){	
		commonMethodInvoker.removeErrors();
			var report=$j('#reportViewForm #report option:selected').val()
	$j("#reportViewForm #reportName").attr('value',report);
		if(self.validateMonthYer()){
		$j("#reportViewForm").attr('method','post');
		$j("#reportViewForm").attr('action',constants.REPORTGENERATEURL);
		$j("#reportViewForm").attr('target','_blank');
		$j("#reportViewForm").submit(); 
		}
	});	
	
}

organztnReportCreator.prototype.validateMonthYer = function() {
	var bValid=true;
	var startMonth=$j("#reportViewForm #startMonth").val();
	var endMonth=$j("#reportViewForm #endMonth").val();
	var startYear=$j("#reportViewForm #startYear").val();
	var endyear=$j("#reportViewForm #endYear").val();
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
	
organztnReportCreator.prototype.fillMonth = function(controlObj) {
	var numbers = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
	$j.each(numbers, function(val, text) {
            $j(controlObj).append( $j('<option></option>').val(val+1).html(text) )
            });
}	

organztnReportCreator.prototype.fillYear = function(controlObj) {
	var firstYear = 2013;
var lastYear = 2034;
for(var i =firstYear; i<=lastYear; i++) {
        $j(controlObj).append( $j('<option></option>').val(i).html(i) )
}
	
}	

organztnReportCreator.prototype.fillnetMdlist = function(controlObj,netMdlistdata) {
	//ajaxProcessor.setUrl(constants.ORGNZTNREPORTFILTERNETMDLISTURL + organisationId);
	//var netMdlistdata =ajaxProcessor.get();
	//alert(JSON.stringify(netMdlistdata))
	$j(netMdlistdata.netmdBranch).each( function(val, text) {
            $j(controlObj).append( $j('<option></option>').val(text.globalId).html(text.name) )
            }); 
	
}	