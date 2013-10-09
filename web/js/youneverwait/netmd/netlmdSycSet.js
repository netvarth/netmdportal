
var flag=$j('input[type=radio]:checked').val();	
 if(flag!=""){$j('#syctxtNetmdEnTime').hide();
 $j('#syctxtNetmdEnInr').hide();}	
fillTimeList("#NetmdSyncTime");

$j('#NetmdEnable').die('click').live("click",function() {
		$j('#syctxtNetmdEnTime').show();
		$j('#syctxtNetmdEnInr').show();
	});

$j('#NetmdDisable').die('click').live("click",function() {
		$j('#syctxtNetmdEnTime').hide();
		$j('#syctxtNetmdEnInr').hide();
	});
	
function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	} 