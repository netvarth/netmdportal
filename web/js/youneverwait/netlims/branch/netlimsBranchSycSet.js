var flag=$j('input[type=radio]:checked').val();	
 if(flag!=""){$j('#syctxtBranchEnTime').hide();
 $j('#syctxtBranchEnInr').hide();}	
fillTimeList("#NetlimsBranchSyncTime");

$j('#Enable').die('click').live("click",function() {
		$j('#syctxtBranchEnTime').show();
		$j('#syctxtBranchEnInr').show();
	});

$j('#Disable').die('click').live("click",function() {
		$j('#syctxtBranchEnTime').hide();
		$j('#syctxtBranchEnInr').hide();
	});
	
function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	}