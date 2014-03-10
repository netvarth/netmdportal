package com.nv.youNeverWait.test.analatic.pl;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.analatic.bl.impl.MaternalComplications;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })

public class AnalaticDaoImplTest {
	
	
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void ageInferencesTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(1,2013, 1, 2015);
		 	  
	}
	
	@Test
	public void ageInferencesTestwithHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void bookedStatiInferencesTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<BookedStatisticsEntity> entityList =analaticDao.getBookedStatInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void bookedStatiInferencesHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<BookedStatisticsEntity> entityList =analaticDao.getBookedStatInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void heightInferencesTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalHeightEntity> entityList =analaticDao.getMaternalHeightInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void heightInferencesHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<MaternalHeightEntity> entityList =analaticDao.getMaternalHeightInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void weightInferencesTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalWeightEntity> entityList =analaticDao.getMaternalWeightInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void weightInferencesHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<MaternalWeightEntity> entityList =analaticDao.getMaternalWeightInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void complicationsInferencesTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<MaternalComplicationsEntity> entityList =analaticDao.getMaternalComplicationsInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void complicationsInferencesHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<MaternalComplicationsEntity> entityList =analaticDao.getMaternalComplicationsInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void bmiInferencesTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<BodyMassIndexEntity> entityList =analaticDao.getBMIInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void bmiInferencesHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<BodyMassIndexEntity> entityList =analaticDao.getBMIInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	@Test
	public void parityInferencesTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<ParityEntity> entityList =analaticDao.getParityInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void parityInferencesHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<ParityEntity> entityList =analaticDao.getParityInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	@Test
	public void previousCSTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<PreviousCSEntity> entityList =analaticDao.getPrevCsInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void previousCSPerHospital() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PreviousCSEntity> entityList =analaticDao.getPrevCsInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void vaginalDeliveryTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
		  List<VaginalDeliveryEntity> entityList =analaticDao.getVaginalDeliveryInferences(1,2013, 1, 2015);
		  System.out.println(entityList.toArray());  
	}
	
	@Test
	public void vaginalDeliveryPerHospitalTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<VaginalDeliveryEntity> entityList =analaticDao.getVaginalDeliveryInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void caesareanSectionTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<CaesareanSectionEntity> entityList =analaticDao.getCaesareanSectionInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void caesareanSectionPerHospitalTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<CaesareanSectionEntity> entityList =analaticDao.getCaesareanSectionInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void robSonClassTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<RobsonClassEntity> entityList =analaticDao.getRobsonClassInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void robSonClassPerHospitalTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<RobsonClassEntity> entityList =analaticDao.getRobsonClassInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	@Test
	public void presentationTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PresentationEntity> entityList =analaticDao.getPresentationInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void presentationPerHospitalTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PresentationEntity> entityList =analaticDao.getPresentationInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void episiotomyTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<EpisiotomyEntity> entityList =analaticDao.getEpisiotomyInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void episiotomyPerHospitalTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<EpisiotomyEntity> entityList =analaticDao.getEpisiotomyInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	
	@Test
	public void perinealTearTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PerinealTearEntity> entityList =analaticDao.getPerinealTearInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void perinealTearPerHospitalTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<PerinealTearEntity> entityList =analaticDao.getPerinealTearInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}

	
	@Test
	public void inducedTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<InductionEntity> entityList =analaticDao.getInductionInferences(1,2013, 1, 2015 );
		 	System.out.println(entityList.toArray());  
	}
	
	
	@Test
	public void inducedPerHospitalTest() {
         AnalaticDao analaticDao = (AnalaticDao) applicationContext.getBean("analatic.dao");     
		  //List<MaternalAgeEntity> entityList =analaticDao.getMaternalAgeInferences(2013, 1, 2014, 12);
         List<InductionEntity> entityList =analaticDao.getInductionInferences(1,2013, 1, 2015 ,20);
		 	System.out.println(entityList.toArray());  
	}
	

	
	
	
}
