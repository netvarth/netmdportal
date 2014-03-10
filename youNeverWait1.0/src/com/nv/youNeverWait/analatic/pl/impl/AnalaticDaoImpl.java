package com.nv.youNeverWait.analatic.pl.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;










import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nv.youNeverWait.analatic.bl.Measure;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
import com.nv.youNeverWait.analatic.pl.entity.ApgarScoreEntity;
import com.nv.youNeverWait.analatic.pl.entity.BloodGroupEntity;
import com.nv.youNeverWait.analatic.pl.entity.BodyMassIndexEntity;
import com.nv.youNeverWait.analatic.pl.entity.BookedStatisticsEntity;
import com.nv.youNeverWait.analatic.pl.entity.CaesareanSectionEntity;
import com.nv.youNeverWait.analatic.pl.entity.EpisiotomyEntity;
import com.nv.youNeverWait.analatic.pl.entity.InductionEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalAgeEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalComplicationsEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalHeightEntity;
import com.nv.youNeverWait.analatic.pl.entity.MaternalWeightEntity;
import com.nv.youNeverWait.analatic.pl.entity.ParityEntity;
import com.nv.youNeverWait.analatic.pl.entity.PerinealTearEntity;
import com.nv.youNeverWait.analatic.pl.entity.PresentationEntity;
import com.nv.youNeverWait.analatic.pl.entity.PreviousCSEntity;
import com.nv.youNeverWait.analatic.pl.entity.RobsonClassEntity;
import com.nv.youNeverWait.analatic.pl.entity.VaginalDeliveryEntity;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;




public class AnalaticDaoImpl extends GenericDaoHibernateImpl implements AnalaticDao  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1279876257315240339L;
	@PersistenceContext()
	private EntityManager em;

	

	
	@Override
	public List<MaternalAgeEntity> getMaternalAgeInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear) {
		 Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.MATERNAL_AGE_QUERY);	
	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		
	    List<Object[]>entityList = query.getResultList();
	    
	    
        List<MaternalAgeEntity> ageList = new ArrayList<MaternalAgeEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalAgeEntity maternalAgeEntity = new MaternalAgeEntity();
	    	maternalAgeEntity.setHospital((String)entity[0]);
	    	maternalAgeEntity.setYear((String)entity[1]);
	    	maternalAgeEntity.setMonth((String)entity[2]);
            maternalAgeEntity.setLt20((BigDecimal)entity[3]);
            maternalAgeEntity.setBt20to24((BigDecimal)entity[4]);
            maternalAgeEntity.setBt25to29((BigDecimal)entity[5]);
            maternalAgeEntity.setBt30to35((BigDecimal)entity[6]);
            maternalAgeEntity.setGt35((BigDecimal)entity[7]);
            ageList.add(maternalAgeEntity);
            
	    }
	return ageList;
	}
	@Override
	public List<MaternalAgeEntity> getMaternalAgeInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(AnalaticQuery.MATERNAL_AGE_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
        
		List<Object[]>entityList = query.getResultList();
	    
	    
        List<MaternalAgeEntity> ageList = new ArrayList<MaternalAgeEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalAgeEntity maternalAgeEntity = new MaternalAgeEntity();
	    	maternalAgeEntity.setHospital((String)entity[0]);
	    	maternalAgeEntity.setYear((String)entity[1]);
	    	maternalAgeEntity.setMonth((String)entity[2]);
            maternalAgeEntity.setLt20((BigDecimal)entity[3]);
            maternalAgeEntity.setBt20to24((BigDecimal)entity[4]);
            maternalAgeEntity.setBt25to29((BigDecimal)entity[5]);
            maternalAgeEntity.setBt30to35((BigDecimal)entity[6]);
            maternalAgeEntity.setGt35((BigDecimal)entity[7]);
            ageList.add(maternalAgeEntity);
	    }
	return ageList;
	}
	
	@Override
	public List<BookedStatisticsEntity> getBookedStatInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear) {
		     Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.BOOKED_STATISTICS_QUERY);	
			
			query.setParameter("fYear", fyear);
			query.setParameter("fMonth", fmonth);
			query.setParameter("toYear", toyear);
			query.setParameter("toMonth", tomonth);
			
			List<Object[]>entityList = query.getResultList();
		    
		    
	        List<BookedStatisticsEntity> dataList = new ArrayList<BookedStatisticsEntity>();      
		    for(Object[] entity:entityList){
		    	
		    	BookedStatisticsEntity bookedStatisticsEntity = new BookedStatisticsEntity();
		    	bookedStatisticsEntity.setHospital((String)entity[0]);
		    	bookedStatisticsEntity.setYear((String)entity[1]);
		    	bookedStatisticsEntity.setMonth((String)entity[2]);
		    	bookedStatisticsEntity.setBooked((BigDecimal)entity[3]);
		    	bookedStatisticsEntity.setUnbooked((BigDecimal)entity[4]);
	            dataList.add(bookedStatisticsEntity);
		    }
		return dataList;
		
	}
	

	@Override
	public List<BookedStatisticsEntity> getBookedStatInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear, Integer hospital) {
		
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.BOOKED_STATISTICS_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		
        List<Object[]>entityList = query.getResultList();
	    
	    
        List<BookedStatisticsEntity> dataList = new ArrayList<BookedStatisticsEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	BookedStatisticsEntity bookedStatisticsEntity = new BookedStatisticsEntity();
	    	bookedStatisticsEntity.setHospital((String)entity[0]);
	    	bookedStatisticsEntity.setYear((String)entity[1]);
	    	bookedStatisticsEntity.setMonth((String)entity[2]);
	    	bookedStatisticsEntity.setBooked((BigDecimal)entity[3]);
	    	bookedStatisticsEntity.setUnbooked((BigDecimal)entity[4]);
            dataList.add(bookedStatisticsEntity);
	    }
	return dataList;
	}


	@Override
	public List<MaternalHeightEntity> getMaternalHeightInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.MATERNAL_HEIGHT_QUERY);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		List<Object[]>entityList = query.getResultList();
		List<MaternalHeightEntity> heightList = new ArrayList<MaternalHeightEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalHeightEntity maternalHeightEntity = new MaternalHeightEntity();
	    	maternalHeightEntity.setHospital((String)entity[0]);
	    	maternalHeightEntity.setYear((String)entity[1]);
	    	maternalHeightEntity.setMonth((String)entity[2]);
	    	maternalHeightEntity.setLt45((BigDecimal)entity[3]);
	    	maternalHeightEntity.setBt145to149((BigDecimal)entity[4]);
	    	maternalHeightEntity.setBt150to170((BigDecimal)entity[5]);
	    	maternalHeightEntity.setGt170((BigDecimal)entity[6]);
	    	
	    	heightList.add(maternalHeightEntity);
	    }
		
	
	return heightList;
	}

	@Override
	public List<MaternalHeightEntity> getMaternalHeightInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.MATERNAL_HEIGHT_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<MaternalHeightEntity> heightList = new ArrayList<MaternalHeightEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalHeightEntity maternalHeightEntity = new MaternalHeightEntity();
	    	maternalHeightEntity.setHospital((String)entity[0]);
	    	maternalHeightEntity.setYear((String)entity[1]);
	    	maternalHeightEntity.setMonth((String)entity[2]);
	    	maternalHeightEntity.setLt45((BigDecimal)entity[3]);
	    	maternalHeightEntity.setBt145to149((BigDecimal)entity[4]);
	    	maternalHeightEntity.setBt150to170((BigDecimal)entity[5]);
	    	maternalHeightEntity.setGt170((BigDecimal)entity[6]);
	    	
	    	heightList.add(maternalHeightEntity);
	    }
	return heightList;
	}

	@Override
	public List<MaternalWeightEntity> getMaternalWeightInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.MATERNAL_WEIGHT_QUERY);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		List<Object[]>entityList = query.getResultList();
		List<MaternalWeightEntity> weightList = new ArrayList<MaternalWeightEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalWeightEntity maternalWeightEntity = new MaternalWeightEntity();
	    	maternalWeightEntity.setHospital((String)entity[0]);
	    	maternalWeightEntity.setYear((String)entity[1]);
	    	maternalWeightEntity.setMonth((String)entity[2]);
	    	maternalWeightEntity.setBt40to49((BigDecimal)entity[3]);
	    	maternalWeightEntity.setBt50to69((BigDecimal)entity[4]);
	    	maternalWeightEntity.setBt70to89((BigDecimal)entity[5]);
	    	maternalWeightEntity.setGteq90((BigDecimal)entity[6]);
	    	
	    	weightList.add(maternalWeightEntity);
	    }
		
		return weightList;
	}

	@Override
	public List<MaternalWeightEntity> getMaternalWeightInferences(Integer fmonth,Integer fyear,
			 Integer tomonth,Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.MATERNAL_WEIGHT_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<MaternalWeightEntity> weightList = new ArrayList<MaternalWeightEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalWeightEntity maternalWeightEntity = new MaternalWeightEntity();
	    	maternalWeightEntity.setHospital((String)entity[0]);
	    	maternalWeightEntity.setYear((String)entity[1]);
	    	maternalWeightEntity.setMonth((String)entity[2]);
	    	maternalWeightEntity.setBt40to49((BigDecimal)entity[3]);
	    	maternalWeightEntity.setBt50to69((BigDecimal)entity[4]);
	    	maternalWeightEntity.setBt70to89((BigDecimal)entity[5]);
	    	maternalWeightEntity.setGteq90((BigDecimal)entity[6]);
	    	
	    	weightList.add(maternalWeightEntity);
	    }
	    return weightList;
	}

	@Override
	public List<MaternalComplicationsEntity> getMaternalComplicationsInferences(
			 Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.MATERNAL_COMPLICATIONS_QUERY);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		List<Object[]>entityList = query.getResultList();
		List<MaternalComplicationsEntity> compllicationList = new ArrayList<MaternalComplicationsEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalComplicationsEntity maternalComplicationsEntity = new MaternalComplicationsEntity();
	    	maternalComplicationsEntity.setHospital((String)entity[0]);
	    	maternalComplicationsEntity.setYear((String)entity[1]);
	    	maternalComplicationsEntity.setMonth((String)entity[2]);
	    	maternalComplicationsEntity.setAnemia((BigDecimal)entity[3]);
	    	maternalComplicationsEntity.setDiabetes((BigDecimal)entity[4]);
	    	maternalComplicationsEntity.setHeartDisease((BigDecimal)entity[5]);
	    	maternalComplicationsEntity.setHyperTension((BigDecimal)entity[6]);
	    	
	    	compllicationList.add(maternalComplicationsEntity);
	    }
	    return compllicationList;
	}

	@Override
	public List<MaternalComplicationsEntity> getMaternalComplicationsInferences(Integer fmonth,Integer fyear,Integer toMonth,Integer toYear,Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.MATERNAL_COMPLICATIONS_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toYear);
		query.setParameter("toMonth", toMonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<MaternalComplicationsEntity> compllicationList = new ArrayList<MaternalComplicationsEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	MaternalComplicationsEntity maternalComplicationsEntity = new MaternalComplicationsEntity();
	    	maternalComplicationsEntity.setHospital((String)entity[0]);
	    	maternalComplicationsEntity.setYear((String)entity[1]);
	    	maternalComplicationsEntity.setMonth((String)entity[2]);
	    	maternalComplicationsEntity.setAnemia((BigDecimal)entity[3]);
	    	maternalComplicationsEntity.setDiabetes((BigDecimal)entity[4]);
	    	maternalComplicationsEntity.setHeartDisease((BigDecimal)entity[5]);
	    	maternalComplicationsEntity.setHyperTension((BigDecimal)entity[6]);
	    	
	    	compllicationList.add(maternalComplicationsEntity);
	    }
	    return compllicationList;
	}

	
	@Override
	public List<BodyMassIndexEntity> getBMIInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.BODY_MASS_INDEX);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		List<Object[]>entityList = query.getResultList();
		List<BodyMassIndexEntity> bodyMassIndexList = new ArrayList<BodyMassIndexEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	BodyMassIndexEntity bodyMassIndexEntity = new BodyMassIndexEntity();
	    	bodyMassIndexEntity.setHospital((String)entity[0]);
	    	bodyMassIndexEntity.setYear((String)entity[1]);
	    	bodyMassIndexEntity.setMonth((String)entity[2]);
	    	bodyMassIndexEntity.setLt20((BigDecimal)entity[3]);
	    	bodyMassIndexEntity.setBt20to24((BigDecimal)entity[4]);
	    	bodyMassIndexEntity.setBt25to29((BigDecimal)entity[5]);
	    	bodyMassIndexEntity.setBt30to34((BigDecimal)entity[6]);
	    	bodyMassIndexEntity.setGteq35((BigDecimal)entity[7]);
	    	
	    	bodyMassIndexList.add(bodyMassIndexEntity);
	    }
	    return bodyMassIndexList;
		
		
		
		
	}

	@Override
	public List<BodyMassIndexEntity> getBMIInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.BODY_MASS_INDEX_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<BodyMassIndexEntity> bodyMassIndexList = new ArrayList<BodyMassIndexEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	BodyMassIndexEntity bodyMassIndexEntity = new BodyMassIndexEntity();
	    	bodyMassIndexEntity.setHospital((String)entity[0]);
	    	bodyMassIndexEntity.setYear((String)entity[1]);
	    	bodyMassIndexEntity.setMonth((String)entity[2]);
	    	bodyMassIndexEntity.setLt20((BigDecimal)entity[3]);
	    	bodyMassIndexEntity.setBt20to24((BigDecimal)entity[4]);
	    	bodyMassIndexEntity.setBt25to29((BigDecimal)entity[5]);
	    	bodyMassIndexEntity.setBt30to34((BigDecimal)entity[6]);
	    	bodyMassIndexEntity.setGteq35((BigDecimal)entity[7]);
	    	
	    	bodyMassIndexList.add(bodyMassIndexEntity);
	    }
	    return bodyMassIndexList;
	}
	
	
	@Override
	public List<BloodGroupEntity> getBloodGroupInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear){
		Query query = em.createQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.BLOOD_GROUP_QUERY);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);

		List<Object[]>entityList = query.getResultList();
		List<BloodGroupEntity> bloodGroupList = new ArrayList<BloodGroupEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	BloodGroupEntity bloodGroupEntity = new BloodGroupEntity();
	    	bloodGroupEntity.setHospital((String)entity[0]);
	    	bloodGroupEntity.setYear((String)entity[1]);
	    	bloodGroupEntity.setMonth((String)entity[2]);
	    	bloodGroupEntity.setA((BigDecimal)entity[3]);
	    	bloodGroupEntity.setB((BigDecimal)entity[4]);
	    	bloodGroupEntity.setAb((BigDecimal)entity[5]);
	    	bloodGroupEntity.setO((BigDecimal)entity[6]);
	    	bloodGroupEntity.setRhNegative((BigDecimal)entity[7]);
	    	
	    	bloodGroupList.add(bloodGroupEntity);
	    }
	    return bloodGroupList;
	}


	
	
	@Override
	public List<BloodGroupEntity> getBloodGroupInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.BLOOD_GROUP_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<BloodGroupEntity> bloodGroupList = new ArrayList<BloodGroupEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	BloodGroupEntity bloodGroupEntity = new BloodGroupEntity();
	    	bloodGroupEntity.setHospital((String)entity[0]);
	    	bloodGroupEntity.setYear((String)entity[1]);
	    	bloodGroupEntity.setMonth((String)entity[2]);
	    	bloodGroupEntity.setA((BigDecimal)entity[3]);
	    	bloodGroupEntity.setB((BigDecimal)entity[4]);
	    	bloodGroupEntity.setAb((BigDecimal)entity[5]);
	    	bloodGroupEntity.setO((BigDecimal)entity[6]);
	    	bloodGroupEntity.setRhNegative((BigDecimal)entity[7]);
	    	
	    	bloodGroupList.add(bloodGroupEntity);
	    }
	    return bloodGroupList;
		
	}
	
	
	

	@Override
	public List<ParityEntity> getParityInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.PARITY_QUERY);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);

		List<Object[]>entityList = query.getResultList();
		List<ParityEntity> parityList = new ArrayList<ParityEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	ParityEntity parityEntity = new ParityEntity();
	    	parityEntity.setHospital((String)entity[0]);
	    	parityEntity.setYear((String)entity[1]);
	    	parityEntity.setMonth((String)entity[2]);
	    	parityEntity.setEq1((BigDecimal)entity[3]);
	    	parityEntity.setEq2((BigDecimal)entity[4]);
	    	parityEntity.setEq3((BigDecimal)entity[5]);
	    	parityEntity.setBt4to5((BigDecimal)entity[6]);
	    	parityEntity.setGteq6((BigDecimal)entity[7]);
	    	
	    	parityList.add(parityEntity);
	    }
	    return parityList;
		
	}

	@Override
	public List<ParityEntity> getParityInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.PARITY_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<ParityEntity> parityList = new ArrayList<ParityEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	ParityEntity parityEntity = new ParityEntity();
	    	parityEntity.setHospital((String)entity[0]);
	    	parityEntity.setYear((String)entity[1]);
	    	parityEntity.setMonth((String)entity[2]);
	    	parityEntity.setEq1((BigDecimal)entity[3]);
	    	parityEntity.setEq2((BigDecimal)entity[4]);
	    	parityEntity.setEq3((BigDecimal)entity[5]);
	    	parityEntity.setBt4to5((BigDecimal)entity[6]);
	    	parityEntity.setGteq6((BigDecimal)entity[7]);
	    	
	    	parityList.add(parityEntity);
	    }
	    return parityList;
		
	}


	
	
	@Override
	public List<PreviousCSEntity> getPrevCsInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.PREVIOUS_CS_QUERY);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		List<Object[]>entityList = query.getResultList();
		List<PreviousCSEntity> previousCsList = new ArrayList<PreviousCSEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	PreviousCSEntity peviousCSEntity = new PreviousCSEntity();
	    	peviousCSEntity.setHospital((String)entity[0]);
	    	peviousCSEntity.setYear((String)entity[1]);
	    	peviousCSEntity.setMonth((String)entity[2]);
	    	peviousCSEntity.setSame1((BigDecimal)entity[3]);
	    	peviousCSEntity.setSame2((BigDecimal)entity[4]);
	    	peviousCSEntity.setSame3((BigDecimal)entity[5]);
	    	peviousCSEntity.setSamegt3((BigDecimal)entity[6]);
	    	peviousCSEntity.setOther1((BigDecimal)entity[7]);
	    	peviousCSEntity.setOther2((BigDecimal)entity[8]);
	    	peviousCSEntity.setOther3((BigDecimal)entity[9]);
	    	peviousCSEntity.setOthergt3((BigDecimal)entity[10]);


	    	
	    	previousCsList.add(peviousCSEntity);
	    }
	return previousCsList;
	}

	@Override
	public List<PreviousCSEntity> getPrevCsInferences(Integer fyear,
			Integer fmonth, Integer toyear, Integer tomonth, Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.PREVIOUS_CS_QUERY_WITH_HOSPITAL);	
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital",hospital);
		
		List<Object[]>entityList = query.getResultList();
		List<PreviousCSEntity> previousCsList = new ArrayList<PreviousCSEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	PreviousCSEntity peviousCSEntity = new PreviousCSEntity();
	    	peviousCSEntity.setHospital((String)entity[0]);
	    	peviousCSEntity.setYear((String)entity[1]);
	    	peviousCSEntity.setMonth((String)entity[2]);
	    	peviousCSEntity.setSame1((BigDecimal)entity[3]);
	    	peviousCSEntity.setSame2((BigDecimal)entity[4]);
	    	peviousCSEntity.setSame3((BigDecimal)entity[5]);
	    	peviousCSEntity.setSamegt3((BigDecimal)entity[6]);
	    	peviousCSEntity.setOther1((BigDecimal)entity[7]);
	    	peviousCSEntity.setOther2((BigDecimal)entity[8]);
	    	peviousCSEntity.setOther3((BigDecimal)entity[9]);
	    	peviousCSEntity.setOthergt3((BigDecimal)entity[10]);
	    	previousCsList.add(peviousCSEntity);
	    }
	return previousCsList;
	}
	
	
	

	@Override
	public List<VaginalDeliveryEntity> getVaginalDeliveryInferences(
			Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.VAGILE_DELIVERY_QUERY);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		
		List<Object[]>entityList = query.getResultList();
		List<VaginalDeliveryEntity> vaginalDeliveryList = new ArrayList<VaginalDeliveryEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	VaginalDeliveryEntity vaginalDeliveryEntity = new VaginalDeliveryEntity();
	    	vaginalDeliveryEntity.setHospital((String)entity[0]);
	    	vaginalDeliveryEntity.setYear((String)entity[1]);
	    	vaginalDeliveryEntity.setMonth((String)entity[2]);
	    	vaginalDeliveryEntity.setSpontaneous((BigDecimal)entity[3]);
	    	vaginalDeliveryEntity.setForceps((BigDecimal)entity[4]);
	    	vaginalDeliveryEntity.setVacum((BigDecimal)entity[5]);
	    	vaginalDeliveryEntity.setBreech((BigDecimal)entity[6]);
	    	vaginalDeliveryEntity.setVbac((BigDecimal)entity[7]);
	    	vaginalDeliveryEntity.setMp2((BigDecimal)entity[8]);
	    	vaginalDeliveryEntity.setMpgt2((BigDecimal)entity[9]);
	    	vaginalDeliveryList.add(vaginalDeliveryEntity);
	    }
	return vaginalDeliveryList;
		
		
	}
	@Override
	public List<VaginalDeliveryEntity> getVaginalDeliveryInferences(
			Integer fmonth,Integer fyear, Integer tomonth, Integer toyear,
			Integer hospital) {
		Query query = em.createNativeQuery(com.nv.youNeverWait.analatic.pl.impl.AnalaticQuery.VAGILE_DELIVERY_PER_HOSPITAL);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<VaginalDeliveryEntity> vaginalDeliveryList = new ArrayList<VaginalDeliveryEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	VaginalDeliveryEntity vaginalDeliveryEntity = new VaginalDeliveryEntity();
	    	vaginalDeliveryEntity.setHospital((String)entity[0]);
	    	vaginalDeliveryEntity.setYear((String)entity[1]);
	    	vaginalDeliveryEntity.setMonth((String)entity[2]);
	    	vaginalDeliveryEntity.setSpontaneous((BigDecimal)entity[3]);
	    	vaginalDeliveryEntity.setForceps((BigDecimal)entity[4]);
	    	vaginalDeliveryEntity.setVacum((BigDecimal)entity[5]);
	    	vaginalDeliveryEntity.setBreech((BigDecimal)entity[6]);
	    	vaginalDeliveryEntity.setVbac((BigDecimal)entity[7]);
	    	vaginalDeliveryEntity.setMp2((BigDecimal)entity[8]);
	    	vaginalDeliveryEntity.setMpgt2((BigDecimal)entity[9]);
	    	vaginalDeliveryList.add(vaginalDeliveryEntity);
	    }
	return vaginalDeliveryList;
	}
	
	@Override
	public List<CaesareanSectionEntity> getCaesareanSectionInferences(
			Integer fmonth, Integer fyear, Integer toMonth, Integer toYear) {
		Query query = em.createNativeQuery(AnalaticQuery.CAESERIAN_DELIVERY);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toYear);
		query.setParameter("toMonth", toMonth);
		
		List<Object[]>entityList = query.getResultList();
		List<CaesareanSectionEntity> caesareanDeliveryList = new ArrayList<CaesareanSectionEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	CaesareanSectionEntity caesareanSectionEntity = new CaesareanSectionEntity();
	    	caesareanSectionEntity.setHospital((String)entity[0]);
	    	caesareanSectionEntity.setYear((String)entity[1]);
	    	caesareanSectionEntity.setMonth((String)entity[2]);
	    	caesareanSectionEntity.setTotalCs((BigDecimal)entity[3]);
	    	caesareanDeliveryList.add(caesareanSectionEntity);
	    }
		return caesareanDeliveryList;
	}
	@Override
	public List<CaesareanSectionEntity> getCaesareanSectionInferences(
			Integer fmonth, Integer fyear, Integer toMonth, Integer toYear,
			Integer hospital) {
		Query query = em.createNativeQuery(AnalaticQuery.CAESERIAN_DELIVERY_PER_HOSPITAL);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toYear);
		query.setParameter("toMonth", toMonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<CaesareanSectionEntity> caesareanDeliveryList = new ArrayList<CaesareanSectionEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	CaesareanSectionEntity caesareanSectionEntity = new CaesareanSectionEntity();
	    	caesareanSectionEntity.setHospital((String)entity[0]);
	    	caesareanSectionEntity.setYear((String)entity[1]);
	    	caesareanSectionEntity.setMonth((String)entity[2]);
	    	caesareanSectionEntity.setTotalCs((BigDecimal)entity[3]);
	    	caesareanDeliveryList.add(caesareanSectionEntity);
	    }
		return caesareanDeliveryList;
		
	}	
	
	
	@Override
	public List<PresentationEntity> getPresentationInferences(Integer fmonth,
			Integer fyear, Integer toMonth, Integer toYear) {
		Query query = em.createNativeQuery(AnalaticQuery.PRESENTATION);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toYear);
		query.setParameter("toMonth", toMonth);
		
		List<Object[]>entityList = query.getResultList();
		List<PresentationEntity> presentationEntityList = new ArrayList<PresentationEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	PresentationEntity presentationEntity = new PresentationEntity();
	    	presentationEntity.setHospital((String)entity[0]);
	    	presentationEntity.setYear((String)entity[1]);
	    	presentationEntity.setMonth((String)entity[2]);
	    	presentationEntity.setVxoccAnt((BigDecimal)entity[3]);
	    	presentationEntity.setVxoccPost((BigDecimal)entity[4]);
	    	presentationEntity.setBreechVag((BigDecimal)entity[5]);
	    	presentationEntity.setTransverse((BigDecimal)entity[6]);
	    	presentationEntity.setFace((BigDecimal)entity[7]);
	    	presentationEntity.setOthers((BigDecimal)entity[8]);
	    	presentationEntityList.add(presentationEntity);
	    }
		return presentationEntityList;
	 	
		
	}
	@Override
	public List<PresentationEntity> getPresentationInferences(Integer fmonth,
			Integer fyear, Integer toMonth, Integer toYear, Integer hospital) {
		Query query = em.createNativeQuery(AnalaticQuery.PRESENTATION_PER_HOSPITAL);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toYear);
		query.setParameter("toMonth", toMonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<PresentationEntity> presentationEntityList = new ArrayList<PresentationEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	PresentationEntity presentationEntity = new PresentationEntity();
	    	presentationEntity.setHospital((String)entity[0]);
	    	presentationEntity.setYear((String)entity[1]);
	    	presentationEntity.setMonth((String)entity[2]);
	    	presentationEntity.setVxoccAnt((BigDecimal)entity[3]);
	    	presentationEntity.setVxoccPost((BigDecimal)entity[4]);
	    	presentationEntity.setBreechVag((BigDecimal)entity[5]);
	    	presentationEntity.setTransverse((BigDecimal)entity[6]);
	    	presentationEntity.setFace((BigDecimal)entity[7]);
	    	presentationEntity.setOthers((BigDecimal)entity[8]);
	    	presentationEntityList.add(presentationEntity);
	    }
		return presentationEntityList;
	}
	
	
	
		
	@Override
	public List<RobsonClassEntity> getRobsonClassInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		Query query = em.createNativeQuery(AnalaticQuery.ROBSON_CLASS);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		
		List<Object[]>entityList = query.getResultList();
		List<RobsonClassEntity> robsonClassList = new ArrayList<RobsonClassEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	RobsonClassEntity robsonClassEntity = new RobsonClassEntity();
	    	robsonClassEntity.setHospital((String)entity[0]);
	    	robsonClassEntity.setYear((String)entity[1]);
	    	robsonClassEntity.setMonth((String)entity[2]);
	    	robsonClassEntity.setTotal1((BigDecimal)entity[3]);
	    	robsonClassEntity.setTotal2e((BigDecimal)entity[4]);
	    	robsonClassEntity.setTotal2i((BigDecimal)entity[5]);
	    	robsonClassEntity.setTotal3((BigDecimal)entity[6]);
	    	robsonClassEntity.setTotal4e((BigDecimal)entity[7]);
	    	robsonClassEntity.setTotal4i((BigDecimal)entity[8]);
	    	robsonClassEntity.setTotal5((BigDecimal)entity[9]);
	    	robsonClassEntity.setTotal6((BigDecimal)entity[10]);
	    	robsonClassEntity.setTotal7((BigDecimal)entity[11]);
	    	robsonClassEntity.setTotal8((BigDecimal)entity[12]);
	    	robsonClassEntity.setTotal9((BigDecimal)entity[13]);
	    	robsonClassEntity.setTotal10((BigDecimal)entity[14]);
	    	robsonClassEntity.setCs1((BigDecimal)entity[14]);
	    	robsonClassEntity.setCs2e((BigDecimal)entity[15]);
	    	robsonClassEntity.setCs2i((BigDecimal)entity[16]);
	    	robsonClassEntity.setCs3((BigDecimal)entity[17]);
	    	robsonClassEntity.setCs4e((BigDecimal)entity[18]);
	    	robsonClassEntity.setCs4i((BigDecimal)entity[9]);
	    	robsonClassEntity.setCs5((BigDecimal)entity[20]);
	    	robsonClassEntity.setCs6((BigDecimal)entity[21]);
	    	robsonClassEntity.setCs7((BigDecimal)entity[22]);
	    	robsonClassEntity.setCs8((BigDecimal)entity[23]);
	    	robsonClassEntity.setCs9((BigDecimal)entity[24]);
	    	robsonClassEntity.setCs10((BigDecimal)entity[25]);
	    	
	    	robsonClassList.add(robsonClassEntity);
	    }
		return robsonClassList;
	}
	
	
	@Override
	public List<RobsonClassEntity> getRobsonClassInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(AnalaticQuery.ROBSON_CLASS_PER_HOSPITAL);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<RobsonClassEntity> robsonClassList = new ArrayList<RobsonClassEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	RobsonClassEntity robsonClassEntity = new RobsonClassEntity();
	    	robsonClassEntity.setHospital((String)entity[0]);
	    	robsonClassEntity.setYear((String)entity[1]);
	    	robsonClassEntity.setMonth((String)entity[2]);
	    	robsonClassEntity.setTotal1((BigDecimal)entity[3]);
	    	robsonClassEntity.setTotal2e((BigDecimal)entity[4]);
	    	robsonClassEntity.setTotal2i((BigDecimal)entity[5]);
	    	robsonClassEntity.setTotal3((BigDecimal)entity[6]);
	    	robsonClassEntity.setTotal4e((BigDecimal)entity[7]);
	    	robsonClassEntity.setTotal4i((BigDecimal)entity[8]);
	    	robsonClassEntity.setTotal5((BigDecimal)entity[9]);
	    	robsonClassEntity.setTotal6((BigDecimal)entity[10]);
	    	robsonClassEntity.setTotal7((BigDecimal)entity[11]);
	    	robsonClassEntity.setTotal8((BigDecimal)entity[12]);
	    	robsonClassEntity.setTotal9((BigDecimal)entity[13]);
	    	robsonClassEntity.setTotal10((BigDecimal)entity[14]);
	    	robsonClassEntity.setCs1((BigDecimal)entity[15]);
	    	robsonClassEntity.setCs2e((BigDecimal)entity[16]);
	    	robsonClassEntity.setCs2i((BigDecimal)entity[17]);
	    	robsonClassEntity.setCs3((BigDecimal)entity[18]);
	    	robsonClassEntity.setCs4e((BigDecimal)entity[19]);
	    	robsonClassEntity.setCs4i((BigDecimal)entity[20]);
	    	robsonClassEntity.setCs5((BigDecimal)entity[21]);
	    	robsonClassEntity.setCs6((BigDecimal)entity[22]);
	    	robsonClassEntity.setCs7((BigDecimal)entity[23]);
	    	robsonClassEntity.setCs8((BigDecimal)entity[24]);
	    	robsonClassEntity.setCs9((BigDecimal)entity[25]);
	    	robsonClassEntity.setCs10((BigDecimal)entity[26]);
	    	
	    	robsonClassList.add(robsonClassEntity);
	    }
		return robsonClassList;
	}
	
	
	@Override
	public List<EpisiotomyEntity> getEpisiotomyInferences(Integer fmonth,
			Integer fyear, Integer toMonth, Integer toYear) {
		Query query = em.createNativeQuery(AnalaticQuery.EPISIOTOMY);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toYear);
		query.setParameter("toMonth", toMonth);
		
		List<Object[]>entityList = query.getResultList();
		List<EpisiotomyEntity>  episiotomyList = new ArrayList<EpisiotomyEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	EpisiotomyEntity episiotomyEntity = new EpisiotomyEntity();
	    	episiotomyEntity.setHospital((String)entity[0]);
	    	episiotomyEntity.setYear((String)entity[1]);
	    	episiotomyEntity.setMonth((String)entity[2]);
	    	episiotomyEntity.setMidlineEpisiotomy((BigDecimal)entity[3]);
	    	episiotomyEntity.setMedioLateralEpisiotomy((BigDecimal)entity[4]);
	    	
	    	episiotomyList.add(episiotomyEntity);
	    }
		return episiotomyList;
	}	
	
	
	@Override
	public List<EpisiotomyEntity> getEpisiotomyInferences(Integer fmonth,
			Integer fyear, Integer toMonth, Integer toYear, Integer hospital) {
		Query query = em.createNativeQuery(AnalaticQuery.EPISIOTOMY_PER_HOSPITAL);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toYear);
		query.setParameter("toMonth", toMonth);
		query.setParameter("hospital", hospital);
		List<Object[]>entityList = query.getResultList();
		List<EpisiotomyEntity>  episiotomyList = new ArrayList<EpisiotomyEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	EpisiotomyEntity episiotomyEntity = new EpisiotomyEntity();
	    	episiotomyEntity.setHospital((String)entity[0]);
	    	episiotomyEntity.setYear((String)entity[1]);
	    	episiotomyEntity.setMonth((String)entity[2]);
	    	episiotomyEntity.setMidlineEpisiotomy((BigDecimal)entity[3]);
	    	episiotomyEntity.setMedioLateralEpisiotomy((BigDecimal)entity[4]);
	    	
	    	episiotomyList.add(episiotomyEntity);
	    }
		return episiotomyList;
	}

	
	
	
	@Override
	public List<PerinealTearEntity> getPerinealTearInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		Query query = em.createNativeQuery(AnalaticQuery.PERINEAL_QUERY);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
	
		List<Object[]>entityList = query.getResultList();
		List<PerinealTearEntity>  perinealTearList = new ArrayList<PerinealTearEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	PerinealTearEntity perinealTearEntity = new PerinealTearEntity();
	    	perinealTearEntity.setHospital((String)entity[0]);
	    	perinealTearEntity.setYear((String)entity[1]);
	    	perinealTearEntity.setMonth((String)entity[2]);
	    	perinealTearEntity.setPerinealTear1((BigDecimal)entity[3]);
	    	perinealTearEntity.setPerinealTear2((BigDecimal)entity[4]);
	    	perinealTearEntity.setPerinealTear3a((BigDecimal)entity[5]);
	    	perinealTearEntity.setPerinealTear3b((BigDecimal)entity[6]);
	    	perinealTearEntity.setPerinealTear3c((BigDecimal)entity[7]);
	    	perinealTearEntity.setPerinealTear4((BigDecimal)entity[8]);
	    	perinealTearList.add(perinealTearEntity);
	    }
		return perinealTearList;
		}
	
	@Override
	public List<PerinealTearEntity> getPerinealTearInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(AnalaticQuery.PERINEAL_QUERY_PER_HOSPITAL);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
	    query.setParameter("hospital",hospital); 
		List<Object[]>entityList = query.getResultList();
		List<PerinealTearEntity>  perinealTearList = new ArrayList<PerinealTearEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	PerinealTearEntity perinealTearEntity = new PerinealTearEntity();
	    	perinealTearEntity.setHospital((String)entity[0]);
	    	perinealTearEntity.setYear((String)entity[1]);
	    	perinealTearEntity.setMonth((String)entity[2]);
	    	perinealTearEntity.setPerinealTear1((BigDecimal)entity[3]);
	    	perinealTearEntity.setPerinealTear2((BigDecimal)entity[4]);
	    	perinealTearEntity.setPerinealTear3a((BigDecimal)entity[5]);
	    	perinealTearEntity.setPerinealTear3b((BigDecimal)entity[6]);
	    	perinealTearEntity.setPerinealTear3c((BigDecimal)entity[7]);
	    	perinealTearEntity.setPerinealTear4((BigDecimal)entity[8]);
	    	perinealTearList.add(perinealTearEntity);
	    }
		return perinealTearList;
	}
	
	
	
	
	
	@Override
	public List<InductionEntity> getInductionInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear) {
		Query query = em.createNativeQuery(AnalaticQuery.INDUCED_LABOUR);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
	     
		List<Object[]>entityList = query.getResultList();
		List<InductionEntity>  inductionEntityList = new ArrayList<InductionEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	InductionEntity inductionEntity = new InductionEntity();
	    	inductionEntity.setHospital((String)entity[0]);
	    	inductionEntity.setYear((String)entity[1]);
	    	inductionEntity.setMonth((String)entity[2]);
	    	inductionEntity.setInducedLabourLs24HrVag((BigDecimal)entity[3]);
	    	inductionEntity.setInducedLabourGr24HrVag((BigDecimal)entity[4]);
	    	inductionEntity.setElectiveInductionLs24HrVag((BigDecimal)entity[5]);
	    	inductionEntity.setElectiveInductionGr24HrVag((BigDecimal)entity[6]);
	    	inductionEntity.setElectiveInductionCs((BigDecimal)entity[7]);
	    	inductionEntity.setIndicatedInductionLs24HrVag((BigDecimal)entity[8]);
	    	inductionEntity.setIndicatedInductionGr24HrVag((BigDecimal)entity[9]);
	    	inductionEntity.setIndicatedInductionCs((BigDecimal)entity[10]);
	    	inductionEntityList.add(inductionEntity);
	    }
		return inductionEntityList;
	
	}
	@Override
	public List<InductionEntity> getInductionInferences(Integer fmonth,Integer fyear, Integer tomonth, Integer toyear, Integer hospital) {
		Query query = em.createNativeQuery(AnalaticQuery.INDUCED_LABOUR_PER_HOSPITAL);
		query.setParameter("fYear", fyear);
		query.setParameter("fMonth", fmonth);
		query.setParameter("toYear", toyear);
		query.setParameter("toMonth", tomonth);
	    query.setParameter("hospital",hospital); 
		List<Object[]>entityList = query.getResultList();
		List<InductionEntity>  inductionEntityList = new ArrayList<InductionEntity>();      
	    for(Object[] entity:entityList){
	    	
	    	InductionEntity inductionEntity = new InductionEntity();
	    	inductionEntity.setHospital((String)entity[0]);
	    	inductionEntity.setYear((String)entity[1]);
	    	inductionEntity.setMonth((String)entity[2]);
	    	inductionEntity.setInducedLabourLs24HrVag((BigDecimal)entity[3]);
	    	inductionEntity.setInducedLabourGr24HrVag((BigDecimal)entity[4]);
	    	inductionEntity.setElectiveInductionLs24HrVag((BigDecimal)entity[5]);
	    	inductionEntity.setElectiveInductionGr24HrVag((BigDecimal)entity[6]);
	    	inductionEntity.setElectiveInductionCs((BigDecimal)entity[7]);
	    	inductionEntity.setIndicatedInductionLs24HrVag((BigDecimal)entity[8]);
	    	inductionEntity.setIndicatedInductionGr24HrVag((BigDecimal)entity[9]);
	    	inductionEntity.setIndicatedInductionCs((BigDecimal)entity[10]);
	    	inductionEntityList.add(inductionEntity);
	    }
		return inductionEntityList;
	
	}
	

	
	

	
	
	
	@Override
	public List<ApgarScoreEntity> getApgarScoreInferences(String fyear,
			String fmonth, String toyear, String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ApgarScoreEntity> getApgarScoreInferences(String fyear,
			String fmonth, String toyear, String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getBloodLossInferences(String fyear, String fmonth,
			String toyear, String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getBloodLossInferences(String fyear, String fmonth,
			String toyear, String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getMaternalDeathInferences(String fyear, String fmonth,
			String toyear, String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getMaternalDeathInferences(String fyear, String fmonth,
			String toyear, String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getMaternalMorbidityInferences(String fyear, String fmonth,
			String toyear, String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getMaternalMorbidityInferences(String fyear, String fmonth,
			String toyear, String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getPlacentalWtInferences(String fyear, String fmonth,
			String toyear, String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getPlacentalWtInferences(String fyear, String fmonth,
			String toyear, String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Measure getTSDInferences(String fyear, String fmonth, String toyear,
			String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getTSDInferences(String fyear, String fmonth, String toyear,
			String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getBabySexInferences(String fyear, String fmonth,
			String toyear, String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getBabySexInferences(String fyear, String fmonth,
			String toyear, String tomonth, Integer hostpital) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getBabyWeightIferences(String fyear, String fmonth,
			String toyear, String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getBabyWeightIferences(String fyear, String fmonth,
			String toyear, String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getFSDInferences(String fyear, String fmonth, String toyear,
			String tomonth) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Measure getFSDInferences(String fyear, String fmonth, String toyear,
			String tomonth, Integer hospital) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
}
