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


var userdata="";
$j(document).ready(function(){

	var classLoader = new ClassLoader();
		classLoader.load();
	var globalToolbar = new GlobalToolBarProcessor();
		globalToolbar.init();
		$j('#tabs-1').removeClass('ui-widget-content');
		
	ajaxProcessor.setUrl('/youNeverWait/netlims/auth/user');
	userdata =ajaxProcessor.get();
	var usetType=userdata.userType;
	var ownerName=userdata.userName;
	var otherUserName=userdata.name;
	
	if(usetType=='owner')
		$j('#userName').html(ownerName);
	else
		$j('#userName').html(otherUserName);
		$j("#leftPaneNetlimsBranch").trigger('click');
	
	

});	


