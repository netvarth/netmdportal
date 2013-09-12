function validateNewtest(){
	/*validation using regular expression*/
	var name = $j("#txtTestName");
	//var rate = $j("#txtTestRate");
	var remarks = $j('#txtareaTestRemarks');
	var normalRange = $j('#txtareaNormalRange');
   	var bValid=true;
  	bValid = checkNull( name,constants_nameRequired);
	//bValid = checkNull(rate, constants_rateRequired) && bValid;
	bValid = checkRegexp(remarks,/^[^"]*$/,constants_avoidDoubleQuotes) && bValid;
	bValid = checkRegexp(normalRange,/^[^"]*$/,constants_avoidDoubleQuotes) && bValid;
	/*if((isEmpty(name)))
	NameValid =  checkRegexp(name, /^[a-zA-Z]'?([a-zA-Z]|\.| |-)+$/,constants_nameInvalidMessage,$j('#errorDivNewOrderData'));
    bValid=bValid&&NameValid;*/


	return bValid;
}