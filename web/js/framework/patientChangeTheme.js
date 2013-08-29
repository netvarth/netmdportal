$j('#normaltheme').die('click').live("click",function() {
var classtab=$j('#tabs').attr('class');
if(classtab.indexOf('theme') != -1){
    $j('#tabs').removeClass();
	$j('#patientBody').removeClass();
	var cal=$j('#subcontentappointment').children('div').attr('class').split(' ');
	cal=cal[1];
	$j('#subcontentappointment').children('div').removeClass(cal);
	var relttab=$j('#labTable').attr('class');
	relttab=relttab[2];
	$j('#labTable').removeClass(relttab);

	var reltsel=$j('#dropdownOne1').attr('class').split(' ');
	reltsel=reltsel[1];
	$j('#dropdownOne1').removeClass(reltsel);

	var reltsel1=$j('#dropdownOne').attr('class').split(' ');
	reltsel1=reltsel1[1];
	$j('#dropdownOne').removeClass(reltsel1);

	var viwbtnTeme=$j('#tabs-nohdr').attr('class');
	$j('#tabs-nohdr').removeClass(viwbtnTeme);
}
	
});


$j('#browntheme').die('click').live("click",function() {

	var classtab=$j('#tabs').attr('class');
if(classtab.indexOf('theme') != -1){
    $j('#tabs').removeClass();
	$j('#patientBody').removeClass();
	var cal=$j('#subcontentappointment').children('div').attr('class').split(' ');
	cal=cal[1];
	$j('#subcontentappointment').children('div').removeClass(cal);
	var relttab=$j('#labTable').attr('class');
	relttab=relttab[2];
	$j('#labTable').removeClass(relttab);

	var reltsel=$j('#dropdownOne1').attr('class').split(' ');
	reltsel=reltsel[1];
	$j('#dropdownOne1').removeClass(reltsel);

	var reltsel1=$j('#dropdownOne').attr('class').split(' ');
	reltsel1=reltsel1[1];
	$j('#dropdownOne').removeClass(reltsel1);

	var viwbtnTeme=$j('#tabs-nohdr').attr('class');
	$j('#tabs-nohdr').removeClass(viwbtnTeme);

}
	
	
	$j('#tabs').addClass('theme1');
	$j('#patientBody').addClass('theme1');
	$j('.todaysapp').addClass('theme1');
	$j('#labTable').addClass('theme1');
	$j('#tabs-nohdr').addClass('theme1');
	$j('#dropdownOne1').addClass('theme1');
	$j('#dropdownOne').addClass('theme1');
});



$j('#greentheme').die('click').live("click",function() {

	var classtab=$j('#tabs').attr('class');
if(classtab.indexOf('theme') != -1){
    $j('#tabs').removeClass();
	$j('#patientBody').removeClass();
	var cal=$j('#subcontentappointment').children('div').attr('class').split(' ');
	cal=cal[1];
	$j('#subcontentappointment').children('div').removeClass(cal);
	var relttab=$j('#labTable').attr('class');
	relttab=relttab[2];
	$j('#labTable').removeClass(relttab);

	var reltsel=$j('#dropdownOne1').attr('class').split(' ');
	reltsel=reltsel[1];
	$j('#dropdownOne1').removeClass(reltsel);

	var reltsel1=$j('#dropdownOne').attr('class').split(' ');
	reltsel1=reltsel1[1];
	$j('#dropdownOne').removeClass(reltsel1);

	var viwbtnTeme=$j('#tabs-nohdr').attr('class');
	$j('#tabs-nohdr').removeClass(viwbtnTeme);

}


	$j('#tabs').addClass('theme2');
	$j('#patientBody').addClass('theme2');
	$j('.todaysapp').addClass('theme2');
	$j('#labTable').addClass('theme2');
	$j('#tabs-nohdr').addClass('theme2');
	$j('#dropdownOne1').addClass('theme2');
	$j('#dropdownOne').addClass('theme2');
});


