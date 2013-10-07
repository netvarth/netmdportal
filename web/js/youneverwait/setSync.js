var flag=$j('input[type=radio]:checked').val();	
 if(flag!=""){$j('#syctxtEnTime').hide();
 $j('#syctxtEnInr').hide();}	
fillTimeList("#syncTime");

$j('#Enable').die('click').live("click",function() {
		$j('#syctxtEnTime').show();
		$j('#syctxtEnInr').show();
	});

$j('#Disable').die('click').live("click",function() {
		$j('#syctxtEnTime').hide();
		$j('#syctxtEnInr').hide();
	});
	
function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	}