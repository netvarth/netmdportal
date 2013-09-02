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
$j.cachedScript("/youNeverWait/js/framework/logout.js").done(function(script, textStatus) {
})
$j.cachedScript("/youNeverWait/js/framework/login.js").done(function(script, textStatus) {
})
var errorData = getErrorData();
$j(document).ready(function(){
	createLeftpaneToolBarNetrx();
	createGlobalToolBarNetrx();
	$j('#tabs-1').removeClass('ui-widget-content');
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	var usetType=userdata.userType;
	var ownerName=userdata.userName;
	var otherUserName=userdata.name;
	if(usetType=='owner')
	$j('#Nameofnetrxuser').html(ownerName);
	else
	$j('#Nameofnetrxuser').html(otherUserName);
});	

$j('#leftPaneNetRxBranch').die('click').live("click",function() {
		removeErrors();
		$j.cachedScript(constant_NetRxAccEntry_Url).done(function(script, textStatus) {
		})
	});



