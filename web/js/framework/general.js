String.prototype.startsWith = function(s) {
   if( this.indexOf(s) == 0 ) return true;
   return false;
}

/**
 * ReplaceAll by Fagner Brack (MIT Licensed)
 * Replaces all occurrences of a substring in a string
 */
String.prototype.replaceAll = function(token, newToken, ignoreCase) {
    var str, i = -1, _token;
    if((str = this.toString()) && typeof token === "string") {
        _token = ignoreCase === true? token.toLowerCase() : undefined;
        while((i = (
            _token !== undefined? 
                str.toLowerCase().indexOf(
                            _token, 
                            i >= 0? i + newToken.length : 0
                ) : str.indexOf(
                            token,
                            i >= 0? i + newToken.length : 0
                )
        )) !== -1 ) {
            str = str.substring(0, i)
                    .concat(newToken)
                    .concat(str.substring(i + token.length));
        }
    }
return str;
};

jQuery.fn.serializeObject = function()
{	
	var o = {};
	var a = this.serializeArray();
	jQuery.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
				o[this.name] = this.value || '';
		}
	});
	return o;
};

function makeautoComplete(id,sourceData){
	sourceData.sort();
	target = "#"+id;
	jQuery(target).autocomplete({
		source: function( request, response ) {
			var matches = jQuery.map( sourceData, function(tag) {
				if (tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
					return tag;
				}
			});
			response(matches);
		}			
	});		
	return;
}

function isIndexExists(source, index) {
	var foundStatus = false;
	for(ind = 0; ind<source.length;ind++){
		if(source[ind]==index) {
			foundStatus=true;
		}	
	}	
	return foundStatus;
}

function isEqual(value1,value2){
	var stat = false;
	if(value1==value2)
		stat = true;
	return stat;
}

jQuery.fn.dataTableExt.oPagination.newPagination = {
    /*
     * Function: oPagination.four_button.fnInit
     * Purpose:  Initalise dom elements required for pagination with a list of the pages
     * Returns:  -
     * Inputs:   object:oSettings - dataTables settings object
     *           node:nPaging - the DIV which contains this pagination control
     *           function:fnCallbackDraw - draw function which must be called on update
     */
    "fnInit": function ( oSettings, nPaging, fnCallbackDraw )
    {
        nFirst = document.createElement( 'span' );
		jQuery(nFirst).attr('id','first');
        nPrevious = document.createElement( 'span' );
		jQuery(nPrevious).attr('id','previous');
		nActive = document.createElement( 'span' );
		jQuery(nActive).attr('id','active');
        nNext = document.createElement( 'span' );
		jQuery(nNext).attr('id','next');
        nLast = document.createElement( 'span' );   
		jQuery(nLast).attr('id','last');		
        nFirst.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sFirst ) );
        nPrevious.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sPrevious ) );
		nActive.appendChild( document.createTextNode("1" ) );
        nNext.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sNext ) );
        nLast.appendChild( document.createTextNode( oSettings.oLanguage.oPaginate.sLast ) );
        nFirst.className = "paginate_button first";
        nPrevious.className = "paginate_button previous";
        nNext.className="paginate_button next";
        nLast.className = "paginate_button last";
        nActive.className = "paginate_active"; 
        nPaging.appendChild( nFirst );
        nPaging.appendChild( nPrevious );
		nPaging.appendChild( nActive );
        nPaging.appendChild( nNext );
        nPaging.appendChild( nLast ); 
        /* Disallow text selection */
        jQuery(nFirst).bind( 'selectstart', function () { return false; } );
        jQuery(nPrevious).bind( 'selectstart', function () { return false; } );
        jQuery(nNext).bind( 'selectstart', function () { return false; } );
        jQuery(nLast).bind( 'selectstart', function () { return false; } );

    },
     
    /*
     * Function: oPagination.four_button.fnUpdate
     * Purpose:  Update the list of page buttons shows
     * Returns:  -
     * Inputs:   object:oSettings - dataTables settings object
     *           function:fnCallbackDraw - draw function which must be called on update
     */
    "fnUpdate": function ( oSettings, fnCallbackDraw )
    {
        if ( !oSettings.aanFeatures.p )
        {
            return;
        }
    }
};

function openModalBox(obj,modalName) {
	obj.attr('data-reveal-id',modalName);
	obj.attr('data-animation','fade');
	var modalLocation = obj.attr('data-reveal-id');
	jQuery('#'+modalLocation).reveal(obj.data());
	jQuery('#'+modalName).trigger('reveal:open',"veryfast");
	obj.removeAttr('data-reveal-id');
	obj.removeAttr('data-animation');
}

function br2nl(str) {
    return str.replace(/<br\s*\/?>/mg,"\n");
}

function nl2br(str) {
    return str.replace(/[\r\n]/g, "<br/>");
}
//fuction for prevent the entering of characters
function validateNumber(p)	{
	jQuery(p).keydown(function (e) {
		if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
            e.preventDefault();         // Prevent character input
        } 	else {
                var n = e.keyCode;
                if (!((n == 8) 				// backspace
                        || (n == 46)                // delete
                        || (n >= 35 && n <= 40)     // arrow keys/home/end
                        || (n >= 48 && n <= 57)     // numbers on keyboard
                        || (n >= 96 && n <= 105)   // number on keypad
						|| (n==109 || n==189)	//minus sign
						|| (n==107 )			//plus sign
						|| (n==187 )			//plus sign
						||	(n==32 )				//Space
						||(n==13 || n==9))      // enter and tab
						
                        )
						{
                 
                    e.preventDefault();     // Prevent character input
						}
				}
        });
	}
	function validateNumberDot(p)	{
		jQuery(p).keydown(function (e) {
            if (e.shiftKey || e.ctrlKey || e.altKey) { // if shift, ctrl or alt keys held down
                e.preventDefault();         // Prevent character input
            } 
			else {
                var n = e.keyCode;
                if (!((n == 8) 				// backspace
                        || (n == 46)                // delete
                        || (n >= 35 && n <= 40)     // arrow keys/home/end
                        || (n >= 48 && n <= 57)     // numbers on keyboard
                        || (n >= 96 && n <= 105)   // number on keypad
						|| (n==190|| n==110)			//dot
						||(n==13 || n==9))      // enter and tab
						
                        )
						
						{
                 
                    e.preventDefault();     // Prevent character input
						}
						else{
						if(n==190||n==110)
						{
						var total =jQuery(p).val();//fixed to 2 decimal points
				(total).toFixed(2); 
						}
						}
				}
        });
	}

function setPaginationFields(curPage, maxPages, obj) {
	jQuery(obj + ' #first').removeClass('paginate_button_disabled');
	jQuery(obj + ' #next').removeClass('paginate_button_disabled');
	jQuery(obj + ' #previous').removeClass('paginate_button_disabled');
	jQuery(obj + ' #last').removeClass('paginate_button_disabled');
	jQuery(obj + ' #first').addClass('paginate_button');
	jQuery(obj + ' #next').addClass('paginate_button');
	jQuery(obj + ' #previous').addClass('paginate_button');
	jQuery(obj + ' #last').addClass('paginate_button');
	jQuery(obj + ' .paginate_active').html(curPage);
	if(curPage==1 && maxPages<=1) {
		//jQuery(obj + ' #first').removeClass('paginate_button');
		//jQuery(obj + ' #next').removeClass('paginate_button');
		//jQuery(obj + ' #previous').removeClass('paginate_button');
		//jQuery(obj + ' #last').removeClass('paginate_button');
		jQuery(obj + ' #first').addClass('paginate_button_disabled');
		jQuery(obj + ' #next').addClass('paginate_button_disabled');
		jQuery(obj + ' #previous').addClass('paginate_button_disabled');
		jQuery(obj + ' #last').addClass('paginate_button_disabled');
	}else if(curPage==1) {
		//jQuery(obj + ' #previous').removeClass('paginate_button');
		jQuery(obj + ' #previous').addClass('paginate_button_disabled');
		//jQuery(obj + ' #first').removeClass('paginate_button');
		jQuery(obj + ' #first').addClass('paginate_button_disabled');
	}else if(curPage==maxPages) {
		jQuery(obj + ' #first').removeClass('paginate_button_disabled');
		jQuery(obj + ' #first').addClass('paginate_button');
		jQuery(obj + ' #previous').removeClass('paginate_button_disabled');
		jQuery(obj + ' #previous').addClass('paginate_button');
		
		//jQuery(obj + ' #last').removeClass('paginate_button');
		jQuery(obj + ' #last').addClass('paginate_button_disabled');
		//jQuery(obj + ' #next').removeClass('paginate_button');
		jQuery(obj + ' #next').addClass('paginate_button_disabled');
	} 
}

function setCustomDataTable(tableObj) {
	jQuery(tableObj).dataTable( {
		"sPaginationType": "newPagination",
		"bRetrieve":true,
		"bFilter":false,
		"bInfo":false,
		"bLengthChange":false,
		"bSort":false,
		"sDom": '<"top"Hip>'
	});
}

function createSearchDiv(id) {
	var searchDiv = jQuery('<div class="dataTables_info"/>');
	searchDiv.attr('id',id);
	var searchbox = jQuery('<span style="float:left; margin:30px 0 0 5px;">Search :</span><span style="float:right;margin:20px 0 0 0 "><input type="text" id="txt_Search" style="width:175px; height:30px; margin:6px 0 0 0;"/></span>');
	searchDiv.html(searchbox);
	return searchDiv;
}
function space2tab(str) {
	return str.replace(/&nbsp;/g, ' ');
}