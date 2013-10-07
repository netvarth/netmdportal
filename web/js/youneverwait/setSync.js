var flag=$j('input[type=radio]:checked').val();	
 if(flag!=""){$j('#syctxtEnTime').hide();
 $j('#syctxtEnInr').hide();}	


$j('#Enable').die('click').live("click",function() {
		$j('#syctxtEnTime').show();
		$j('#syctxtEnInr').show();
	});

$j('#Disable').die('click').live("click",function() {
		$j('#syctxtEnTime').hide();
		$j('#syctxtEnInr').hide();
	});