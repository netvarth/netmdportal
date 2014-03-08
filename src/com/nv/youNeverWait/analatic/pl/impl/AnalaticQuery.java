package com.nv.youNeverWait.analatic.pl.impl;

public class AnalaticQuery {

	public static final String MATERNAL_AGE_QUERY ="select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(age='20',1,0)) as lt20,"+
			"sum(if(age BETWEEN 20 AND 24,1,0)) as bt20to24,"+
			"sum(if(age BETWEEN 25 AND 29,1,0)) as bt25to29,"+
			"sum(if(age BETWEEN 30 AND 35,1,0)) as bt30to35,"+
			"sum(if(age > 35,1,0)) as gt35 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as age,case_id as caseId from answer_tbl where quest_id=4 ) as q4 ON q0.caseId=q4.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String MATERNAL_AGE_QUERY_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(age='20',1,0)) as lt20,"+
			"sum(if(age BETWEEN 20 AND 24,1,0)) as bt20to24,"+
			"sum(if(age BETWEEN 25 AND 29,1,0)) as bt25to29,"+
			"sum(if(age BETWEEN 30 AND 35,1,0)) as bt30to35,"+
			"sum(if(age > 35,1,0)) as gt35 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as age,case_id as caseId from answer_tbl where quest_id=4 ) as q4 ON q0.caseId=q4.caseId "+			
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	
   public static final String BOOKED_STATISTICS_QUERY_WITH_HOSPITAL="select nmd.name as hospital,yer as year, mnth as month,"+
		    "sum(if(book='booked',1,0)) as booked,"+
		    "sum(if(book='unbooked',1,0)) as unbooked "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as book,case_id as caseId from answer_tbl where quest_id=2 ) as q3 ON q0.caseId=q3.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
 
   
	public static final String BOOKED_STATISTICS_QUERY= "select nmd.name as hospital,yer as year, mnth as month,"+
		    "sum(if(book='booked',1,0)) as booked,"+
		    "sum(if(book='unbooked',1,0)) as unbooked "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as book,case_id as caseId from answer_tbl where quest_id=2 ) as q3 ON q0.caseId=q3.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
 

	public static final String MATERNAL_HEIGHT_QUERY = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(height<145,1,0)) as lt45,"
			+ "sum(if(height BETWEEN 145 AND 149,1,0)) as bt145to149,"
			+ "sum(if(height BETWEEN 150 AND 170,1,0)) as bt150to170,"
			+ "sum(if(height > 170,1,0)) as gt170 "+
			" from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0"+
			" LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId"+
			" LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId"+
			" LEFT OUTER JOIN (select answer as height,case_id as caseId from answer_tbl where quest_id=6 ) as q5 ON q0.caseId=q5.caseId"+
			" LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "
			+ " where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String MATERNAL_HEIGHT_QUERY_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,sum(if(height<145,1,0)) as lt45,sum(if(height BETWEEN 145 AND 149,1,0)) as bt145to149,sum(if(height BETWEEN 150 AND 170,1,0)) as bt150to170,sum(if(height > 170,1,0)) as gt170 from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0"+
			" LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId"+
			" LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId"+
			" LEFT OUTER JOIN (select answer as height,case_id as caseId from answer_tbl where quest_id=6 ) as q5 ON q0.caseId=q5.caseId"+
			" LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			" where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";


	public static final String MATERNAL_WEIGHT_QUERY="select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(weight<40,1,0)) as lt40,"
			+ "sum(if(weight BETWEEN 40 AND 49,1,0)) as bt40to49,"
			+ "sum(if(weight BETWEEN 50 AND 69,1,0)) as bt50to69,"
			+ "sum(if(weight BETWEEN 70 AND 89,1,0)) as bt70to89,"
			+ "sum(if(weight > 90,1,0)) as gteq90 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0"+
			" LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId"+
			" LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId"+
			" LEFT OUTER JOIN (select answer as weight,case_id as caseId from answer_tbl where quest_id=7 ) as q6 ON q0.caseId=q6.caseId"+
			" LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			" where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String MATERNAL_WEIGHT_QUERY_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(weight<40,1,0)) as lt40,"
			+ "sum(if(weight BETWEEN 40 AND 49,1,0)) as bt40to49,"
			+ "sum(if(weight BETWEEN 50 AND 69,1,0)) as bt50to69,"
			+ "sum(if(weight BETWEEN 70 AND 89,1,0)) as bt70to89,"
			+ "sum(if(weight > 90,1,0)) as gteq90 "+
			" from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0"+
			" LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId"+
			" LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId"+
			" LEFT OUTER JOIN (select answer as weight,case_id as caseId from answer_tbl where quest_id=7 ) as q6 ON q0.caseId=q6.caseId"+
			" LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			" where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

			

	public static final String MATERNAL_COMPLICATIONS_QUERY = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(anemia ='yes',1,0)) as anemia,"
			+ "sum(if(diabetesMellitus ='yes',1,0)) as diabetes,"
			+ "sum(if(heartDisease ='yes',1,0)) as heartDisease,"
			+ "sum(if(severeHyper ='yes',1,0)) as hyperTension "+
			" from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0"+
			" LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId"+
			" LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId"+
			" LEFT OUTER JOIN (select answer as anemia,case_id as caseId from answer_tbl where quest_id=31 ) as q7 ON q0.caseId=q7.caseId"+
			" LEFT OUTER JOIN (select answer as diabetesMellitus,case_id as caseId from answer_tbl where quest_id=33 ) as q8 ON q0.caseId=q8.caseId"+
			" LEFT OUTER JOIN (select answer as heartDisease,case_id as caseId from answer_tbl where quest_id=34 ) as q9 ON q0.caseId=q9.caseId"+
			" LEFT OUTER JOIN (select answer as severeHyper,case_id as caseId from answer_tbl where quest_id=39 ) as q10 ON q0.caseId=q10.caseId"+
			" LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			" where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String MATERNAL_COMPLICATIONS_QUERY_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(anemia ='yes',1,0)) as anemia,"
			+ "sum(if(diabetesMellitus ='yes',1,0)) as diabetes,"
			+ "sum(if(heartDisease ='yes',1,0)) as heartDisease,"
			+ "sum(if(severeHyper ='yes',1,0)) as hyperTension "+
			" from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0"+
			" LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId"+
			" LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId"+
			" LEFT OUTER JOIN (select answer as anemia,case_id as caseId from answer_tbl where quest_id=31 ) as q7 ON q0.caseId=q7.caseId"+
			" LEFT OUTER JOIN (select answer as diabetesMellitus,case_id as caseId from answer_tbl where quest_id=33 ) as q8 ON q0.caseId=q8.caseId"+
			" LEFT OUTER JOIN (select answer as heartDisease,case_id as caseId from answer_tbl where quest_id=34 ) as q9 ON q0.caseId=q9.caseId"+
			" LEFT OUTER JOIN (select answer as severeHyper,case_id as caseId from answer_tbl where quest_id=39 ) as q10 ON q0.caseId=q10.caseId"+
			" LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			" where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String BODY_MASS_INDEX = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(bmi<20,1,0)) as lt20,"
			+ "sum(if(bmi BETWEEN 20 AND 24,1,0)) as bt20to24,"
			+ "sum(if(bmi BETWEEN 25 AND 29,1,0)) as bt25to29,"
			+ "sum(if(bmi BETWEEN 30 AND 34,1,0)) as bt30to34,"
			+ "sum(if(bmi >=35,1,0)) as gteq35 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as bmi,case_id as caseId from answer_tbl where quest_id=8 ) as q11 ON q0.caseId=q11.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String BODY_MASS_INDEX_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(bmi<20,1,0)) as bmiLs20,"
			+ "sum(if(bmi BETWEEN 20 AND 24,1,0)) as bmibtw20and24,"
			+ "sum(if(bmi BETWEEN 25 AND 29,1,0)) as bmibtw25and29,"
			+ "sum(if(bmi BETWEEN 30 AND 34,1,0)) as bmibtw30and34,"
			+ "sum(if(bmi >=35,1,0)) as bmiGrEq35 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as bmi,case_id as caseId from answer_tbl where quest_id=8 ) as q11 ON q0.caseId=q11.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String BLOOD_GROUP_QUERY = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(blooodGrp='A',1,0)) as a,"
			+ "sum(if(blooodGrp='B',1,0)) as b,"
			+ "sum(if(blooodGrp='AB',1,0)) as ab,"
			+ "sum(if(blooodGrp='O',1,0)) as o,"
			+ "sum(if(RhFactor='negative',1,0)) as rhNegative "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as blooodGrp,case_id as caseId from answer_tbl where quest_id=9 ) as q12 ON q0.caseId=q12.caseId "+
			"LEFT OUTER JOIN (select answer as RhFactor,case_id as caseId from answer_tbl where quest_id=10 ) as q13 ON q0.caseId=q13.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";


	public static final String BLOOD_GROUP_QUERY_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(blooodGrp='A',1,0)) as A,"
			+ "sum(if(blooodGrp='B',1,0)) as B,"
			+ "sum(if(blooodGrp='AB',1,0)) as AB,"
			+ "sum(if(blooodGrp='O',1,0)) as O,"
			+ "sum(if(RhFactor='negative',1,0)) as RhNegative "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId"+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId"+
			"LEFT OUTER JOIN (select answer as blooodGrp,case_id as caseId from answer_tbl where quest_id=9 ) as q12 ON q0.caseId=q12.caseId"+
			"LEFT OUTER JOIN (select answer as RhFactor,case_id as caseId from answer_tbl where quest_id=10 ) as q13 ON q0.caseId=q13.caseId"+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String PARITY_QUERY ="select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(parity='1',1,0)) as eq1,"
			+ "sum(if(parity='2',1,0)) as eq2,"
			+ "sum(if(parity='3',1,0)) as eq3,"
			+ "sum(if(parity BETWEEN 4 AND 5,1,0)) as bt4to5,"
			+ "sum(if(parity >=6,1,0)) as gteq6 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as parity,case_id as caseId from answer_tbl where quest_id=26 ) as q14 ON q0.caseId=q14.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))"; 

	public static final String PARITY_QUERY_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(parity='1',1,0)) as eq1,"
			+ "sum(if(parity='2',1,0)) as eq2,"
			+ "sum(if(parity='3',1,0)) as eq3,"
			+ "sum(if(parity BETWEEN 4 AND 5,1,0)) as bt4to5,"
			+ "sum(if(parity >=6,1,0)) as gteq6 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as parity,case_id as caseId from answer_tbl where quest_id=26 ) as q14 ON q0.caseId=q14.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))"; 


	public static final String PREVIOUS_CS_QUERY = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(prevCsInSameHosp='1',1,0)) as prevCsSameHosp1,"
			+ "sum(if(prevCsInSameHosp='2',1,0)) as prevCsSameHosp2,"
			+ "sum(if(prevCsInSameHosp='3',1,0)) as prevCsSameHosp3,"
			+ "sum(if(prevCsInSameHosp >3,1,0)) as prevCsSameHosp3Above,"
			+ "sum(if(prevCsInOtherHosp='1',1,0)) as prevCsInOthrHosp1,"
			+ "sum(if(prevCsInOtherHosp='2',1,0)) as prevCsInOthrHosp2,"
			+ "sum(if(prevCsInOtherHosp='3',1,0)) as prevCsInOthrHosp3,"
			+ "sum(if(prevCsInOtherHosp >3,1,0)) as prevCsInOthrHosp3Above "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInSameHosp,case_id as caseId from answer_tbl where quest_id=20 ) as q15 ON q0.caseId=q15.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInOtherHosp,case_id as caseId from answer_tbl where quest_id=21 ) as q16 ON q0.caseId=q16.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	;	
	public static final String PREVIOUS_CS_QUERY_WITH_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"
			+ "sum(if(prevCsInSameHosp='1',1,0)) as prevCsSameHosp1,"
			+ "sum(if(prevCsInSameHosp='2',1,0)) as prevCsSameHosp2,"
			+ "sum(if(prevCsInSameHosp='3',1,0)) as prevCsSameHosp3,"
			+ "sum(if(prevCsInSameHosp='>3',1,0)) as prevCsSameHosp3Above,"
			+ "sum(if(prevCsInOtherHosp='1',1,0)) as prevCsInOthrHosp1,"
			+ "sum(if(prevCsInOtherHosp='2',1,0)) as prevCsInOthrHosp2,"
			+ "sum(if(prevCsInOtherHosp='3',1,0)) as prevCsInOthrHosp3,"
			+ "sum(if(prevCsInOtherHosp='>3',1,0)) as prevCsInOthrHosp3Above "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInSameHosp,case_id as caseId from answer_tbl where quest_id=20 ) as q15 ON q0.caseId=q15.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInOtherHosp,case_id as caseId from answer_tbl where quest_id=21 ) as q16 ON q0.caseId=q16.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";


	public static final String VAGILE_DELIVERY_QUERY = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(deliveryType='Normal/vaginal',1,0)) as vaginal,"+
			"sum(if(deliveryType='Forcepsdelivery',1,0)) as forceps,"+
			"sum(if(deliveryType='Vacuumextraction',1,0)) as vacuum,"+
			"sum(if(deliveryType='Breech',1,0)) as breech,"+
			"sum(if(deliveryType='Cesarean',1,0)) as cesarean,"+
			"sum(if(multipliePreg='twins',1,0)) as twins,"+
			"sum(if(multipliePreg='triplets',1,0)) as triplets,"+
			"sum(if(multipliePreg='others',1,0)) as others "+

			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as deliveryType,case_id as caseId from answer_tbl where quest_id=116 ) as q17 ON q0.caseId=q17.caseId "+
			"LEFT OUTER JOIN (select answer as multipliePreg,case_id as caseId from answer_tbl where quest_id=69 ) as q18 ON q0.caseId=q18.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
		
	public static final String VAGILE_DELIVERY_PER_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(deliveryType='Normal/vaginal',1,0)) as vaginal,"+
			"sum(if(deliveryType='Forcepsdelivery',1,0)) as forceps,"+
			"sum(if(deliveryType='Vacuumextraction',1,0)) as vacuum,"+
			"sum(if(deliveryType='Breech',1,0)) as breech,"+
			"sum(if(deliveryType='Cesarean',1,0)) as cesarean,"+
			"sum(if(multipliePreg='twins',1,0)) as twins,"+
			"sum(if(multipliePreg='triplets',1,0)) as triplets,"+
			"sum(if(multipliePreg='others',1,0)) as others "+

			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as deliveryType,case_id as caseId from answer_tbl where quest_id=116 ) as q17 ON q0.caseId=q17.caseId "+
			"LEFT OUTER JOIN (select answer as multipliePreg,case_id as caseId from answer_tbl where quest_id=69 ) as q18 ON q0.caseId=q18.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";


	public static final String CAESERIAN_DELIVERY = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(deliveryType='CS',1,0)) as totalcs "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as deliveryType,case_id as caseId from answer_tbl where quest_id=116 ) as q17 ON q0.caseId=q17.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInSameHosp,case_id as caseId from answer_tbl where quest_id=20 ) as q15 ON q0.caseId=q15.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInOtherHosp,case_id as caseId from answer_tbl where quest_id=21 ) as q16 ON q0.caseId=q16.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	
	
	public static final String CAESERIAN_DELIVERY_PER_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(deliveryType='Cesarean',1,0)) as totalcs "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as deliveryType,case_id as caseId from answer_tbl where quest_id=116 ) as q17 ON q0.caseId=q17.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInSameHosp,case_id as caseId from answer_tbl where quest_id=20 ) as q15 ON q0.caseId=q15.caseId "+
			"LEFT OUTER JOIN (select answer as prevCsInOtherHosp,case_id as caseId from answer_tbl where quest_id=21 ) as q16 ON q0.caseId=q16.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	
	
	public static final String ROBSON_CLASS = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(robsonsClass='Group 1',1,0)) as total1,"+
			"sum(if(robsonsClass='Group 2e ( elective )',1,0)) as total2e,"+
			"sum(if(robsonsClass='Group 2i ( indicated )',1,0)) as tota2i,"+
			"sum(if(robsonsClass='Group 3',1,0)) as total3,"+
			"sum(if(robsonsClass='Group 4e ( elective )',1,0)) as total4e,"+
			"sum(if(robsonsClass='Group 4i ( indicated )',1,0)) as total4i,"+
			"sum(if(robsonsClass='Group 5',1,0)) as total5,"+
			"sum(if(robsonsClass='Group 6',1,0)) as total6,"+
			"sum(if(robsonsClass='Group 7',1,0)) as total7,"+
			"sum(if(robsonsClass='Group 8',1,0)) as total8,"+
			"sum(if(robsonsClass='Group 9',1,0)) as total9,"+
			"sum(if(robsonsClass='Group 10',1,0)) as total10, "+
			"sum(if(robsonsClass='Group 1' AND outcome='CS',1,0)) as cs1,"+
			"sum(if(robsonsClass='Group 2e ( elective )' AND outcome='CS',1,0)) as cs2e,"+
			"sum(if(robsonsClass='Group 2i ( indicated )' AND outcome='CS',1,0)) as cs2i,"+
			"sum(if(robsonsClass='Group 3' AND outcome='CS',1,0)) as cs3,"+
			"sum(if(robsonsClass='Group 4e ( elective )' AND outcome='CS',1,0)) as cs4e,"+
			"sum(if(robsonsClass='Group 4i ( indicated )' AND outcome='CS',1,0)) as csei,"+
			"sum(if(robsonsClass='Group 5' AND outcome='CS',1,0)) as cs5,"+
			"sum(if(robsonsClass='Group 6' AND outcome='CS',1,0)) as cs6,"+
			"sum(if(robsonsClass='Group 7' AND outcome='CS',1,0)) as cs7,"+
			"sum(if(robsonsClass='Group 8' AND outcome='CS',1,0)) as cs8,"+
			"sum(if(robsonsClass='Group 9' AND outcome='CS',1,0)) as cs9,"+
			"sum(if(robsonsClass='Group 10' AND outcome='CS',1,0)) as cs10 "+

			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as robsonsClass,case_id as caseId from answer_tbl where quest_id=119 ) as q19 ON q0.caseId=q19.caseId "+
			"LEFT OUTER JOIN (select answer as outcome,case_id as caseId from answer_tbl where quest_id=57) as q26 ON q0.caseId=q26.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	
	
	public static final String ROBSON_CLASS_PER_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(robsonsClass='Group 1',1,0)) as total1,"+
			"sum(if(robsonsClass='Group 2e ( elective )',1,0)) as total2e,"+
			"sum(if(robsonsClass='Group 2i ( indicated )',1,0)) as tota2i,"+
			"sum(if(robsonsClass='Group 3',1,0)) as total3,"+
			"sum(if(robsonsClass='Group 4e ( elective )',1,0)) as total4e,"+
			"sum(if(robsonsClass='Group 4i ( indicated )',1,0)) as total4i,"+
			"sum(if(robsonsClass='Group 5',1,0)) as total5,"+
			"sum(if(robsonsClass='Group 6',1,0)) as total6,"+
			"sum(if(robsonsClass='Group 7',1,0)) as total7,"+
			"sum(if(robsonsClass='Group 8',1,0)) as total8,"+
			"sum(if(robsonsClass='Group 9',1,0)) as total9,"+
			"sum(if(robsonsClass='Group 10',1,0)) as total10, "+
			"sum(if(robsonsClass='Group 1' AND outcome='CS',1,0)) as cs1,"+
			"sum(if(robsonsClass='Group 2e ( elective )' AND outcome='CS',1,0)) as cs2e,"+
			"sum(if(robsonsClass='Group 2i ( indicated )' AND outcome='CS',1,0)) as cs2i,"+
			"sum(if(robsonsClass='Group 3' AND outcome='CS',1,0)) as cs3,"+
			"sum(if(robsonsClass='Group 4e ( elective )' AND outcome='CS',1,0)) as cs4e,"+
			"sum(if(robsonsClass='Group 4i ( indicated )' AND outcome='CS',1,0)) as csei,"+
			"sum(if(robsonsClass='Group 5' AND outcome='CS',1,0)) as cs5,"+
			"sum(if(robsonsClass='Group 6' AND outcome='CS',1,0)) as cs6,"+
			"sum(if(robsonsClass='Group 7' AND outcome='CS',1,0)) as cs7,"+
			"sum(if(robsonsClass='Group 8' AND outcome='CS',1,0)) as cs8,"+
			"sum(if(robsonsClass='Group 9' AND outcome='CS',1,0)) as cs9,"+
			"sum(if(robsonsClass='Group 10' AND outcome='CS',1,0)) as cs10 "+

			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as robsonsClass,case_id as caseId from answer_tbl where quest_id=119 ) as q19 ON q0.caseId=q19.caseId "+
			"LEFT OUTER JOIN (select answer as outcome,case_id as caseId from answer_tbl where quest_id=57) as q26 ON q0.caseId=q26.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	

	public static final String PRESENTATION = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(presentation='vxoccant',1,0)) as vxoccAnt,"+
			"sum(if(presentation='vxoccpost',1,0)) as vxoccPost,"+
			"sum(if(presentation='Breech vag',1,0)) as breechVag,"+
			"sum(if(presentation='Transverse',1,0)) as traverse,"+
			"sum(if(presentation='Face',1,0)) as face,"+
			"sum(if(presentation='Others',1,0)) as others "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as presentation,case_id as caseId from answer_tbl where quest_id=61 ) as q3 ON q0.caseId=q3.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	
	
	public static final String PRESENTATION_PER_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(presentation='vxoccant',1,0)) as vxoccAnt,"+
			"sum(if(presentation='vxoccpost',1,0)) as vxoccPost,"+
			"sum(if(presentation='Breech vag',1,0)) as breechVag,"+
			"sum(if(presentation='Transverse',1,0)) as traverse,"+
			"sum(if(presentation='Face',1,0)) as face,"+
			"sum(if(presentation='Others',1,0)) as others "+ 
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as presentation,case_id as caseId from answer_tbl where quest_id=61 ) as q3 ON q0.caseId=q3.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	
	public static final String EPISIOTOMY = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(episiotomy='Midline Episiotomy',1,0)) as midlineEpisiotomy, "+
			"sum(if(episiotomy='Medio-Lateral Episiotomy',1,0)) as medioLateralEpisiotomy "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as episiotomy,case_id as caseId from answer_tbl where quest_id=63 ) as q25 ON q0.caseId=q25.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			" group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";
	
	
	public static final String EPISIOTOMY_PER_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(episiotomy='Midline Episiotomy',1,0)) as midlineEpisiotomy, "+
			"sum(if(episiotomy='Medio-Lateral Episiotomy',1,0)) as medioLateralEpisiotomy "+ 
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as episiotomy,case_id as caseId from answer_tbl where quest_id=63 ) as q25 ON q0.caseId=q25.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	
	public static final String PERINEAL_QUERY = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(perinealTearType='1',1,0)) as perinealTear1,"+
			"sum(if(perinealTearType='2',1,0)) as perinealTear2,"+
			"sum(if(perinealTearType='3a',1,0)) as perinealTear3a,"+
			"sum(if(perinealTearType='3b',1,0)) as perinealTear3b,"+
			"sum(if(perinealTearType='3c',1,0)) as perinealTear3c,"+
			"sum(if(perinealTearType='4',1,0)) as perinealTear4 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as perinealTearType,case_id as caseId from answer_tbl where quest_id=107 ) as q20 ON q0.caseId=q20.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	public static final String PERINEAL_QUERY_PER_HOSPITAL = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(perinealTearType='1',1,0)) as perinealTear1,"+
			"sum(if(perinealTearType='2',1,0)) as perinealTear2,"+
			"sum(if(perinealTearType='3a',1,0)) as perinealTear3a,"+
			"sum(if(perinealTearType='3b',1,0)) as perinealTear3b,"+
			"sum(if(perinealTearType='3c',1,0)) as perinealTear3c,"+
			"sum(if(perinealTearType='4',1,0)) as perinealTear4 "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as perinealTearType,case_id as caseId from answer_tbl where quest_id=107 ) as q20 ON q0.caseId=q20.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";



	public static final String INDUCED_LABOUR = "select nmd.name as hospital,yer as year, mnth as month,"+
			"sum(if(inducedLabour='Yes' AND outcome='Vaginal' ,1,0)) as inducedLabourLs24HrVag, "+
			"sum(if(inducedLabour='Yes' AND outcome='Vaginal' ,1,0)) as inducedLabourGr24HrVag,"+
			
			"sum(if(inducedLabour='Yes' AND outcome='Cs',1,0)) as inducedLabourCs, "+
			
			"sum(if(electiveInduction='elective induction'AND outcome='Vaginal' AND (indDelInterval='<12' OR indDelInterval='12-24'),1,0)) as electiveInductionLs24HrVag, "+
			"sum(if(electiveInduction='elective induction'AND outcome='Vaginal' AND (indDelInterval='24-36' OR indDelInterval='36-48'OR indDelInterval='>48'),1,0)) as electiveInductionGr24HrVag, "+
			
			"sum(if(electiveInduction='elective induction'AND outcome='Cs',1,0)) as electiveInductionCs, "+
			
			"sum(if(electiveInduction!='elective induction'AND outcome='Vaginal' AND (indDelInterval='<12' OR indDelInterval='12-24'),1,0)) as indicatedInductionLs24HrVag, "+
			"sum(if(electiveInduction!='elective induction'AND outcome='Vaginal' AND (indDelInterval='24-36' OR indDelInterval='36-48'OR indDelInterval='>48'),1,0)) as indicatedInductionGr24HrVag, "+
			
			"sum(if(electiveInduction!='elective induction'AND outcome='Cs',1,0)) as indicatedInductionCs "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as inducedLabour,case_id as caseId from answer_tbl where quest_id=51 ) as q21 ON q0.caseId=q21.caseId "+
			"LEFT OUTER JOIN (select answer as electiveInduction,case_id as caseId from answer_tbl where quest_id=53 ) as q22 ON q0.caseId=q22.caseId "+
			"LEFT OUTER JOIN (select answer as outcome,case_id as caseId from answer_tbl where quest_id=57) as q26 ON q0.caseId=q26.caseId "+
			"LEFT OUTER JOIN (select answer as indDelInterval,case_id as caseId from answer_tbl where quest_id=58) as q27 ON q0.caseId=q27.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";

	
	
	public static final String INDUCED_LABOUR_PER_HOSPITAL = "select mnth as month,yer as year,nmd.name as hospital,"+
			"sum(if(inducedLabour='Yes' AND outcome='Vaginal' AND (indDelInterval='<12' OR indDelInterval='12-24'),1,0)) as inducedLabourLs24HrVag, "+
			"sum(if(inducedLabour='Yes' AND outcome='Vaginal' AND (indDelInterval='24-36' OR indDelInterval='36-48'OR indDelInterval='>48'),1,0)) as inducedLabourGr24HrVag,"+
			
			"sum(if(inducedLabour='Yes' AND outcome='Cs',1,0)) as inducedLabourCs, "+
			
			"sum(if(electiveInduction='elective induction'AND outcome='Vaginal' AND (indDelInterval='<12' OR indDelInterval='12-24'),1,0)) as electiveInductionLs24HrVag, "+
			"sum(if(electiveInduction='elective induction'AND outcome='Vaginal' AND (indDelInterval='24-36' OR indDelInterval='36-48'OR indDelInterval='>48'),1,0)) as electiveInductionGr24HrVag, "+
			
			"sum(if(electiveInduction='elective induction'AND outcome='Cs',1,0)) as electiveInductionCs, "+
			
			"sum(if(electiveInduction!='elective induction'AND outcome='Vaginal' AND (indDelInterval='<12' OR indDelInterval='12-24'),1,0)) as indicatedInductionLs24HrVag, "+
			"sum(if(electiveInduction!='elective induction'AND outcome='Vaginal' AND (indDelInterval='24-36' OR indDelInterval='36-48'OR indDelInterval='>48'),1,0)) as indicatedInductionGr24HrVag, "+
			
			"sum(if(electiveInduction!='elective induction'AND outcome='Cs',1,0)) as indicatedInductionCs "+
			"from (select distinct case_id as caseId, netmd_branch_id as netmdBranchId from answer_tbl) as q0 "+
			"LEFT OUTER JOIN (select answer as mnth,case_id as caseId from answer_tbl where quest_id=123 ) as q1 ON q0.caseId=q1.caseId "+
			"LEFT OUTER JOIN (select answer as yer,case_id as caseId from answer_tbl where quest_id=124 ) as q2 ON q0.caseId=q2.caseId "+
			"LEFT OUTER JOIN (select answer as inducedLabour,case_id as caseId from answer_tbl where quest_id=51 ) as q21 ON q0.caseId=q21.caseId "+
			"LEFT OUTER JOIN (select answer as electiveInduction,case_id as caseId from answer_tbl where quest_id=53 ) as q22 ON q0.caseId=q22.caseId "+
			"LEFT OUTER JOIN (select answer as outcome,case_id as caseId from answer_tbl where quest_id=57) as q26 ON q0.caseId=q26.caseId "+
			"LEFT OUTER JOIN (select answer as indDelInterval,case_id as caseId from answer_tbl where quest_id=58) as q27 ON q0.caseId=q27.caseId "+
			"LEFT JOIN  netmd_branch_tbl as nmd ON q0.netmdBranchId=nmd.id "+
			"where str_to_date(concat(mnth,yer),'%M%Y') between str_to_date(concat(:fMonth,' ',:fYear),'%m%Y') and  str_to_date(concat(:toMonth,' ',:toYear),'%m%Y') "+
			"and netmdBranchId=:hospital  group by nmd.name,mnth,yer order by nmd.name,yer,month(str_to_date(SUBSTRING(mnth,1,3),'%b'))";


	
	

}
