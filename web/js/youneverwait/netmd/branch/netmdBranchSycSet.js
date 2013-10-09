var flag=$j('input[type=radio]:checked').val();	
 if(flag!=""){$j('#syctxtnetmdBranchEnTime').hide();
 $j('#syctxtnetmdBranchEnInr').hide();}	
fillTimeList("#NetmdBranchSyncTime");

$j('#netmdbrchEnable').die('click').live("click",function() {
		$j('#syctxtnetmdBranchEnTime').show();
		$j('#syctxtnetmdBranchEnInr').show();
	});

$j('#netmdbrchDisable').die('click').live("click",function() {
		$j('#syctxtnetmdBranchEnTime').hide();
		$j('#syctxtnetmdBranchEnInr').hide();
	});
	
function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	}