var flag=$j('input[type=radio]:checked').val();	
 if(flag!=""){$j('#syctxtnetlimsEnTime').hide();
 $j('#syctxtnetlimsEnInr').hide();}	
fillTimeList("#NetlimsSyncTime");

$j('#netlimsEnable').die('click').live("click",function() {
		$j('#syctxtnetlimsEnTime').show();
		$j('#syctxtnetlimsEnInr').show();
	});

$j('#netlimsDisable').die('click').live("click",function() {
		$j('#syctxtnetlimsEnTime').hide();
		$j('#syctxtnetlimsEnInr').hide();
	});
	
function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	}