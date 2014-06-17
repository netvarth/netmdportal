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
	createLeftpaneToolBarNetmd();
	createGlobalToolBarNetmd();
	$j('#tabs-1').removeClass('ui-widget-content');
	var userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	var usetType=userdata.userType;
	var ownerName=userdata.userName;
	var otherUserName=userdata.name;
	if(usetType=='owner')
	$j('#Nameofuser').html(ownerName);
	else
	$j('#Nameofuser').html(otherUserName);
	
	
	
	$j('#leftPaneNetMdBranch').die('click').live("click",function() {
		removeErrors();
		$j.cachedScript(constant_NetMdAccEntry_Url).done(function(script, textStatus) {
		})
	});
	
	
	
	$j('#leftPaneNetMdSettings').die('click').live("click",function() {
		removeErrors();
		$j('#pageToolBar-Container').html("");
		$j.cachedScript(constant_netMdaccSettingsEntry_Url).done(function(script, textStatus) {
		})
	});
	
	

	
	$j('#ribbonNewNetMdBranch').die('click').live("click",function() {
		removeErrors();
		var obj=$j(this);
		createModal(constants_newBranchNetMdAccJson,'netmdAccBranchModal');	
		openModalBox(obj,'netmdAccBranchModal')
		$j.cachedScript(constants_newNetmdAccBranch).done(function(script, textStatus) {
		})
	});
		
	$j('#ribbonDownload').die('click').live("click",function() {
		
		removeErrors();
		
		$j.ajax({
			type: 'GET',
			url: serverPath + "/youNeverWait/netmd/ui/netMd/download",
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





