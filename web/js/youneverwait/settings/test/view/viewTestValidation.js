function validateViewtest(){
	/*validation using regular expression*/
	var name = $j("#txtViewTestName");
	//var rate = $j("#txtViewTestRate");
	var remarks = $j('#txtViewareaTestRemarks');
	var normalRange = $j('#txtareaViewNormalRange');
   	var bValid=true;
  	bValid = checkNull( name,constants.NAMEREQUIRED);
	//bValid = checkNull(rate, constants_rateRequired) && bValid;
	bValid = checkRegexp(remarks,/^[^"]*$/,constants.AVOIDDOUBLEQUOTES) && bValid;
	bValid = checkRegexp(normalRange,/^[^"]*$/,constants.AVOIDDOUBLEQUOTES) && bValid;

	/*if((isEmpty(name)))
	NameValid =  checkRegexp(name, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/,constants_nameInvalidMessage,$j('#errorDivNewOrderData'));
    bValid=bValid&&NameValid;*/
	return bValid;
}