$j('#Enable').die('click').live("click",function() {
		alert("hihi");
		$j('#syncTime').show();
	});

$j('#Disable').die('click').live("click",function() {
		alert("dis");
		$j('#syncTime').hide();
	});