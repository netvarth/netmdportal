function DCLayoutHeader(honorific) {
	this.result =function(){
		var headTag='<thead><tr><th style="width:40%" class="head1" >INVESTIGATION</th><th class="head1" style="width:30%" >OBSERVED VALUE</th>';
		if(honorific!='Animal')
			headTag+='<th class="head1" style="width:30%"  >REFERENCE RANGE&UNIT</th>';
		headTag+='</tr></thead>';
		var theadTag =$j(headTag);
		return theadTag;
	};	
}