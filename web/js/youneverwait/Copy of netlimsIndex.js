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
	userdata =getRequestData('/youNeverWait/ws/ui/auth/getUser');
	var usetType=userdata.userType;
	var ownerName=userdata.userName;
	var otherUserName=userdata.name;
	if(usetType=='owner')
	$j('#userName').html(ownerName);
	else
	$j('#userName').html(otherUserName);
	$j("#leftPaneNetlimsBranch").trigger('click');
	
	var errorData = getErrorData();
	/* $j('#leftPaneNetlimsBranch').die('click').live("click",function() {
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
			url: serverPath + "/youNeverWait/ws/ui/lab/download",
			dataType: 'html',
			contentType: 'text/html',
			success: function (html) {
			var w = window.open();
			w.document.writeln(html);
			//w.location.reload();
			}
		});

	}); */
		

});	


