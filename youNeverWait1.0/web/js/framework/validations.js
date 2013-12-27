	function checkLength( o, n, min, max, errorDiv ) {
		if ( o.val().length > max || o.val().length < min ) {
			o.addClass( "error" );
		//	updateTips( "* Length of " + n + " must be between " +
		//			min + " and " + max + ". " ,o, errorDiv);
			createError("Length of " + n + " must be between " + min + " and " + max + ". " ,o );
			return false;	
		} else {
				return true;
		}
	}

	function checkRegexp( o, regexp, n, errorDiv ) {
		if ( !( regexp.test( o.val() ) ) ) {
			o.addClass( "error" );
			//updateTips( "* " + n,o,errorDiv );
			createError(n,o);
			return false;
		} else {
			return true;
		}
	}



 function checkNull(o,msg){
	if(o.val().length=='0'){
		o.addClass("error");
		//updateTips( "* " + n + " must not be null" ,o ,errorDiv);
		createError( msg ,o );
		return false;	
		} 
		else {
		return true;
		}
 
	}
	
	
	function isEmpty(o){
	if(o.val().length=='0'){
		return false;	
		} 
		else {
		return true;
		}
	}
 

	function updateTips(t,errorDiv) {
		errorDiv.attr('class',"errorHeaderStyle");
		errorDiv.text(t);
		$j('#errorDivHeader').show();
		setTimeout(function() {
				$j('#errorDivHeader').hide();
			}, 5000 );
	}
	
	function updateTipsNew(t,errorDiv, errorDivParent) {
		$j(errorDivParent).show();
		errorDiv.attr('class',"errorHeaderStyle");
		errorDiv.text(t);
			setTimeout(function() {
				$j(errorDivParent).hide();
			}, 5000 );
	}
	function updateTipsSuccess(t,errorDiv, errorDivParent) {
		$j(errorDivParent).show();
		errorDiv.addClass('successHeaderStyle');
		errorDiv.text(t);		
	}
	 
       	
	function createError(t,obj) {
		//if(obj!=null)
			//obj.focus();
		var errorDiv=$j('<div/>');
		errorDiv.attr('class',"errorStyle");
		errorDiv.text(t);
		obj.closest('p').append(errorDiv);
	}
	
	function removeErrors() {
		$j('.errorStyle').remove();
		$j('.error').removeClass('error');
		$j('#errorDivHeader').hide();
	}
	//function for removing error color while clicking in that field
	function removeErrorColor(col){
	$j(col).click(function() {
	$j(this).closest('p').children('div').hide().end();
	$j(col).removeClass('error');
    });
	}
	
	function tab2space(str) {
	return str.replace(/\t/g, '&nbsp;&nbsp;&nbsp;&nbsp;');
}