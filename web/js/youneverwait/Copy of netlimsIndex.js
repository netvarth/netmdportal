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
var userdata="";
$j(document).ready(function(){
	createLeftpaneToolBarNetlims();
	createGlobalToolBarNetlims();
	$j('#tabs-1').removeClass('ui-widget-content');
	userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	var usetType=userdata.userType;
	var ownerName=userdata.userName;
	var otherUserName=userdata.name;
	if(usetType=='owner')
	$j('#userName').html(ownerName);
	else
	$j('#userName').html(otherUserName);
	
	
	$j('#leftPaneNetlimsBranch').die('click').live("click",function() {
		removeErrors();
		$j.cachedScript(constant_NetLimsAccEntry_Url).done(function(script, textStatus) {
		})
	});
	
	$j('#leftPaneNetlimsOrders').die('click').live("click",function() {
		removeErrors();
		$j('#pageToolBar-Container').html("");
		$j.cachedScript(constant_NetLimsAccbranchorder_Url).done(function(script, textStatus) {
		})
	});
	
	$j('#leftPaneNetlimsSettings').die('click').live("click",function() {
		removeErrors();
		$j('#pageToolBar-Container').html("");
		$j.cachedScript(constant_SettingsNetlimsAccEntry_Url).done(function(script, textStatus) {
		})
	});
	
	
	$j('#ribbonNewNetlimsBranch').die('click').live("click",function() {
		removeErrors();
		var obj=$j(this);
		createModal(constants_newBranchNetLimsAccJson,'netlimsAccBranchModal');	
		openModalBox(obj,'netlimsAccBranchModal')
		$j.cachedScript(constants_newNetLimsAccBranch).done(function(script, textStatus) {
		})
	});
	
	$j('#ribbonNetlimsDownload').die('click').live("click",function() {
		removeErrors();
		
		$j.ajax({
			type: 'GET',
			url: serverPath + "/youNeverWait/netlims/ui/lab/download",
			dataType: 'html',
			contentType: 'text/html',
			success: function (html) {
			var w = window.open();
			w.document.writeln(html);
			//w.location.reload();
			}
		});

	});
		

});	


