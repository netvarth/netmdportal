package com.nv.youNeverWait.test.analatic.pl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.analytic.bl.impl.MaternalComplications;
import com.nv.youNeverWait.analytic.bl.impl.MaternalMortalityMorbidity;
import com.nv.youNeverWait.analytic.pl.AnalyticDao;
import com.nv.youNeverWait.analytic.pl.entity.ApgarScoreEntity;
import com.nv.youNeverWait.analytic.pl.entity.BirthWeightEntity;
import com.nv.youNeverWait.analytic.pl.entity.BloodLossEntity;
import com.nv.youNeverWait.analytic.pl.entity.BodyMassIndexEntity;
import com.nv.youNeverWait.analytic.pl.entity.BookedStatisticsEntity;
import com.nv.youNeverWait.analytic.pl.entity.CaesareanSectionEntity;
import com.nv.youNeverWait.analytic.pl.entity.EpisiotomyEntity;
import com.nv.youNeverWait.analytic.pl.entity.FetalComplicationsEntity;
import com.nv.youNeverWait.analytic.pl.entity.InductionEntity;
import com.nv.youNeverWait.analytic.pl.entity.IntravenusFluidEntity;
import com.nv.youNeverWait.analytic.pl.entity.MaternalAgeEntity;
import com.nv.youNeverWait.analytic.pl.entity.MaternalComplicationsEntity;
import com.nv.youNeverWait.analytic.pl.entity.MaternalHeightEntity;
import com.nv.youNeverWait.analytic.pl.entity.MaternalMortalityMorbidityEntity;
import com.nv.youNeverWait.analytic.pl.entity.MaternalWeightEntity;
import com.nv.youNeverWait.analytic.pl.entity.OxyTocicEntity;
import com.nv.youNeverWait.analytic.pl.entity.ParityEntity;
import com.nv.youNeverWait.analytic.pl.entity.PerinealTearEntity;
import com.nv.youNeverWait.analytic.pl.entity.PlacentalWtEntity;
import com.nv.youNeverWait.analytic.pl.entity.PresentationEntity;
import com.nv.youNeverWait.analytic.pl.entity.PreviousCSEntity;
import com.nv.youNeverWait.analytic.pl.entity.RobsonClassEntity;
import com.nv.youNeverWait.analytic.pl.entity.ThirdStageEntity;
import com.nv.youNeverWait.analytic.pl.entity.VaginalDeliveryEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })

public class AnalyticDaoImplTest {
	
	
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void ageInferencesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(1,2013, 1, 2015);
		 	  
	}
	
	@Test
	public void ageInferencesTestwithHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void bookedStatiInferencesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<BookedStatisticsEntity> entityList =analaticDao.getBookedStatInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void bookedStatiInferencesHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<BookedStatisticsEntity> entityList =analaticDao.getBookedStatInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void heightInferencesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalHeightEntity> entityList =analaticDao.getMaternalHeightInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void heightInferencesHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<MaternalHeightEntity> entityList =analaticDao.getMaternalHeightInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void weightInferencesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalWeightEntity> entityList =analaticDao.getMaternalWeightInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void weightInferencesHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<MaternalWeightEntity> entityList =analaticDao.getMaternalWeightInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void complicationsInferencesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalComplicationsEntity> entityList =analaticDao.getMaternalComplicationsInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void complicationsInferencesHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<MaternalComplicationsEntity> entityList =analaticDao.getMaternalComplicationsInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void bmiInferencesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<BodyMassIndexEntity> entityList =analaticDao.getBMIInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void bmiInferencesHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<BodyMassIndexEntity> entityList =analaticDao.getBMIInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	@Test
	public void parityInferencesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<ParityEntity> entityList =analaticDao.getParityInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void parityInferencesHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<ParityEntity> entityList =analaticDao.getParityInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	@Test
	public void previousCSTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<PreviousCSEntity> entityList =analaticDao.getPrevCsInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void previousCSPerHospital() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PreviousCSEntity> entityList =analaticDao.getPrevCsInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void vaginalDeliveryTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<VaginalDeliveryEntity> entityList =analaticDao.getVaginalDeliveryInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void vaginalDeliveryPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<VaginalDeliveryEntity> entityList =analaticDao.getVaginalDeliveryInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void caesareanSectionTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<CaesareanSectionEntity> entityList =analaticDao.getCaesareanSectionInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void caesareanSectionPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<CaesareanSectionEntity> entityList =analaticDao.getCaesareanSectionInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void robSonClassTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<RobsonClassEntity> entityList =analaticDao.getRobsonClassInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void robSonClassPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<RobsonClassEntity> entityList =analaticDao.getRobsonClassInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	@Test
	public void presentationTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PresentationEntity> entityList =analaticDao.getPresentationInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void presentationPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PresentationEntity> entityList =analaticDao.getPresentationInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void episiotomyTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<EpisiotomyEntity> entityList =analaticDao.getEpisiotomyInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void episiotomyPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<EpisiotomyEntity> entityList =analaticDao.getEpisiotomyInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void perinealTearTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PerinealTearEntity> entityList =analaticDao.getPerinealTearInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void perinealTearPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PerinealTearEntity> entityList =analaticDao.getPerinealTearInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}

	
	@Test
	public void inducedTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<InductionEntity> entityList =analaticDao.getInductionInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void inducedPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<InductionEntity> entityList =analaticDao.getInductionInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	@Test
	public void oxyTocicTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<OxyTocicEntity> entityList =analaticDao.getOxyTocicInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void oxyTocicHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<OxyTocicEntity> entityList =analaticDao.getOxyTocicInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void thirdStageDurationTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<ThirdStageEntity> entityList =analaticDao.getTSDInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void thirdStageDurationPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<ThirdStageEntity> entityList =analaticDao.getTSDInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}

	@Test
	public void bloodLossTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<BloodLossEntity> entityList =analaticDao.getBloodLossInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void bloodLossPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<BloodLossEntity> entityList =analaticDao.getBloodLossInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}

	@Test
	public void maternalMortalityMorbidityTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<MaternalMortalityMorbidityEntity> entityList =analaticDao.getMaternalMortalityMorbidityInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void maternalMortalityMorbidityPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<MaternalMortalityMorbidityEntity> entityList =analaticDao.getMaternalMortalityMorbidityInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void IntravenusFluidTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<IntravenusFluidEntity> entityList =analaticDao.getIvFluidInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void intravenusFluidPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<IntravenusFluidEntity> entityList =analaticDao.getIvFluidInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void babyWeightTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<BirthWeightEntity> entityList =analaticDao.getBirthWeightInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void babyWeightPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<BirthWeightEntity> entityList =analaticDao.getBirthWeightInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void upgarTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<ApgarScoreEntity> entityList =analaticDao.getApgarScoreInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void apgarPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<ApgarScoreEntity> entityList =analaticDao.getApgarScoreInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
    

	@Test
	public void fetalComplexitesTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<FetalComplicationsEntity> entityList =analaticDao.getFetalComplicationsInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void fetalComplexitesPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<FetalComplicationsEntity> entityList =analaticDao.getFetalComplicationsInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}

	@Test
	public void PlacentalWtTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<PlacentalWtEntity> entityList =analaticDao.getPlacentalWtInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void PlacentalWtPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<PlacentalWtEntity> entityList =analaticDao.getPlacentalWtInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}

	@Test
	public void ivFluidTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<IntravenusFluidEntity> entityList =analaticDao.getIvFluidInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void ivFluidPerHospitalTest() {
         AnalyticDao analaticDao = (AnalyticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
          List<IntravenusFluidEntity> entityList =analaticDao.getIvFluidInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}

	
	
}
