var flag=$j('input[type=radio]:checked').val();	
	 if(flag!=""){$j('#syctxtEnTime').hide();
		 $j('#syctxtEnInr').hide();
		 $j('#syctxtEnTimelabel').show();
		 $j('#syctxtEnInrlabel').show();
	 }	
	fillTimeList("#syncTime");

$j('#Enable').die('click').live("click",function() {
		 $j('#syctxtEnTimelabel').show();
		 $j('#syctxtEnInrlabel').show();
	});

$j('#Disable').die('click').live("click",function() {
		$j('#syctxtEnTime').hide();
		$j('#syctxtEnInr').hide();
		$j('#syctxtEnTimelabel').show();
		$j('#syctxtEnInrlabel').show();
	});
	
$j('#newBranchForm #btnEdit').die('click').live('click',function(){

if($j('input[type=radio]:checked').val()!="Disable"){
		$j('#syctxtEnTime').show();
		$j('#syctxtEnInr').show();
		$j('#syctxtEnTimelabel').hide();
		$j('#syctxtEnInrlabel').hide();
		$j('#newBranchForm #btnEdit').hide();
		$j('#newBranchForm #btnCancel').show();
		$j('#newBranchForm #btnCreate').show();
			
}
else{   
		$j('#syctxtEnTime').hide();
		$j('#syctxtEnInr').hide();

		$j('#syctxtEnTimelabel').show();
		$j('#syctxtEnInrlabel').show();
		$j('#newBranchForm #btnEdit').hide();
		$j('#newBranchForm #btnCancel').show();
		$j('#newBranchForm #btnCreate').show();}	
	});
$j("#newBranchForm #btnCreate").die('click').live('click',function(){
		removeErrors();	
		$j('#newBranchForm #btnCreate').hide();
		$j('#newBranchForm #btnCancel').hide();
		$j('#newBranchForm #btnEdit').show();
	});	
	
$j('#newBranchForm #btnCancel').die('click').live('click',function(){
		$j('#newBranchForm #btnCreate').hide();
		$j('#newBranchForm #btnCancel').hide();
		$j('#newBranchForm #btnEdit').show();
	});	
	

function fillTimeList(controlobj)
	{
		var list=["daily","hourly","minutes"];
		$j(list).each(function (Index, List) {
			var freqlist=List;
			$j(controlobj).append('<option  value="'+freqlist+'">'+freqlist+'</option>');
		});

		
	}