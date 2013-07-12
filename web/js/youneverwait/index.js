
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
	createLeftpaneToolBar();
	createGlobalToolBar();
	$j('#tabs-1').removeClass('ui-widget-content');
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	userName=userdata.userName;
	$j('#userNameYNW').html(userName);
	
	$j('#leftPaneNetLims').die('click').live("click",function() {
		removeErrors();
		$j('#pageTitle1').hide();
		$j.cachedScript(constant_NetLimsEntry_Url).done(function(script, textStatus) {
		})
		$j('#filter').hide();
		$j('#user-filter-toolbar').hide();
	});
	
	$j('#leftPaneNetMd').die('click').live("click",function() {
		removeErrors();
		$j('#pageTitle1').hide();
		$j.cachedScript(constant_NetMdEntry_Url).done(function(script, textStatus) {
		})
		$j('#filter').hide();
		$j('#user-filter-toolbar').hide();
	});
	
	$j('#leftPaneNetRx').die('click').live("click",function() {
		removeErrors();
		$j('#pageTitle1').hide();
		$j.cachedScript(constant_NetRxEntry_Url).done(function(script, textStatus) {
		})
		$j('#filter').hide();
		$j('#user-filter-toolbar').hide();
	});
	
	$j('#leftPaneSettings').die('click').live("click",function() {
		removeErrors();
		$j('#pageTitle1').hide();
		$j('#pageToolBar-Container').html("");
		$j.cachedScript(constant_SettingsEntry_Url).done(function(script, textStatus) {
		})
		$j('#filter').hide();
		$j('#user-filter-toolbar').hide();
	});
	
	
	$j('#ribbonNewNetLims').die('click').live("click",function() {
		removeErrors();
		var obj=$j(this);
		createModal(constants_newNetLimsJson,'netlimsModal');	
		openModalBox(obj,'netlimsModal')
		$j.cachedScript(constants_newNetLims).done(function(script, textStatus) {
		})
		$j('#filter').hide();
		$j('#user-filter-toolbar').hide();
	});
	
	$j('#ribbonNewNetMd').die('click').live("click",function() {
		removeErrors();
		var obj=$j(this);
		createModal(constants_newNetMdJson,'netmdModal');	
		openModalBox(obj,'netmdModal');
		$j.cachedScript(constants_newNetMd).done(function(script, textStatus) {
		})
		$j('#filter').hide();
		$j('#user-filter-toolbar').hide();
	});
		
	$j('#ribbonNewNetRx').die('click').live("click",function() {
		removeErrors();
		var obj=$j(this);
		createModal(constants_newNetRxJson,'netrxModal');	
		openModalBox(obj,'netrxModal');
		$j.cachedScript(constants_newNetRx).done(function(script, textStatus) {
		})
		$j('#filter').hide();
		$j('#user-filter-toolbar').hide();
	});
});	


