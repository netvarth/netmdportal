function organztnReportCreator() {

	this.neworgnzReportPage = '#reportViewForm';
	this.startmonth = $j("#startmonth");
	this.startyear = $j("#startyear");
	this.endmonth = $j("#endmonth");
	this.endyear = $j("#endyear");
	this.netmdlist = $j("#netmdlist");
	this.showreportButton = $j("#showreport");
	this.errorHeader = $j("#errorDivHeader");
	this.errorData = $j( "#errorDivvwreportData");
	this.inputFields = this.neworgnzReportPage + " :input[type=text]";
}

organztnReportCreator.prototype.getNameListObj = function(patientresultId) {
	return this.patientresultId;
}
organztnReportCreator.prototype.viewReport= function(url,parent) {
	self =this;
	ajaxProcessor.setUrl(url);
	var tabdata =ajaxProcessor.get();
	$j(parent).html(new form(tabdata).result);
	self.bindEvents();
}

organztnReportCreator.prototype.bindEvents = function() {
	self = this;
	self.fillMonth("#startmonth");
	self.fillMonth("#endmonth");
	self.fillYear("#startyear");
	self.fillYear("#endyear");
	ajaxProcessor.setUrl(constants.ORGNZTNREPORTFILTERVIEWURL);
	var ftbdata =ajaxProcessor.get();
	var ftb = new FilterToolBar(ftbdata);
	var myDiv = $j('#netmdlist');
	var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft" name="reportfilter" id="btn_reportfilter_filter_id"><span>Netmd List</span></a>');
		var inputTag=$j('<select></select>');
		inputTag.attr('id','txtreportfilter');
		inputTag.addClass('genTextHeight');
		inputTag.attr('style','display:none');
		myDiv.append(toolbutton);
		myDiv.append(inputTag);					
	self.fillnetMdlist("#txtreportfilter");
	$j( "#netmdlist a:not(:selected)").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#txt'+curObjName).show();
	});	
	$j( "#netmdlist a[selected]").die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#txt'+curObjName).hide();
	});	
	
	$j("#reportViewForm #showreport").die('click').live('click',function(){	
		$j("#reportViewForm").attr('method','post');
		$j("#reportViewForm").attr('action',constants.REPORTGENERATEURL);
		$j("#reportViewForm").attr('target','_blank');
		$j("#reportViewForm").submit();
	});	
	
}	
organztnReportCreator.prototype.fillMonth = function(controlObj) {
	var numbers = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
	$j.each(numbers, function(val, text) {
            $j(controlObj).append( $j('<option></option>').val(val+1).html(text) )
            });
}	

organztnReportCreator.prototype.fillYear = function(controlObj) {
	var firstYear = 2012;
var lastYear = 2032;
for(var i =firstYear; i<=lastYear; i++) {
        $j(controlObj).append( $j('<option></option>').val(i).html(i) )
}
	
}	

organztnReportCreator.prototype.fillnetMdlist = function(controlObj) {
	ajaxProcessor.setUrl(constants.ORGNZTNREPORTFILTERNETMDLISTURL);
	var netMdlistdata =ajaxProcessor.get();
	//alert(JSON.stringify(netMdlistdata))
	$j(netMdlistdata.netMd).each( function(val, text) {
            $j(controlObj).append( $j('<option></option>').val(text.globalId).html(text.name) )
            }); 
	
}	