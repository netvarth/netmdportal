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

$j(document).ready(function(){
	var classLoader = new ClassLoader();
		classLoader.load();
	var organzaccUI = new orgztnAccClassLoader();
		organzaccUI.load();
	var organzUI = new organztnUIStartup();
	organzUI.init();
	ajaxProcessor.setUrl('/youNeverWait/ws/ui/auth/getUser');
	userdata =ajaxProcessor.get();
	var ownerName=userdata.userName;
	var orgName=userdata.accountName;
	orgName=orgName.toUpperCase();
	$j('#orgName').html(orgName);
	$j('#Nameoforguser').html(ownerName);
	
});				