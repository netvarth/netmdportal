package com.nv.youNeverWait.test.analatic.bl.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
//
//import org.easymock.EasyMock;
//import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.nv.youNeverWait.analatic.bl.Analatic;
import com.nv.youNeverWait.analatic.bl.Inference;
import com.nv.youNeverWait.analatic.bl.impl.MaternalAge;
import com.nv.youNeverWait.analatic.pl.AnalaticDao;
import com.nv.youNeverWait.analatic.pl.entity.MaternalAgeEntity;
import com.nv.youNeverWait.analatic.pl.impl.AnalaticDaoImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })

public class MaternalAgeTest {
	
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	
	private MaternalAge maternalAge;
	
	@Before
    public void setup(){
//		
//		AnalaticDao analaticDao = EasyMock.createMock(AnalaticDao.class);
//	     EasyMock.expect(analaticDao.getMaternalAgeInferences(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt())).andStubAnswer(new IAnswer<List<MaternalAgeEntity>>() {
//		        @Override
//		        public List<MaternalAgeEntity> answer() throws Throwable {
//		        	      List<MaternalAgeEntity> entityList = new ArrayList<MaternalAgeEntity>();
//		        	      MaternalAgeEntity maternalAgeEntity= new MaternalAgeEntity();
//		        	      maternalAgeEntity.setHospital("Mother Hospital");
//		        	      maternalAgeEntity.setMonth(1);
//		        	      maternalAgeEntity.setYear("2013");
//		        	      
//		        		  entityList.add(maternalAgeEntity);        	
//		        			
//		            return entityList;
//		        }
//			});
//		
//	     maternalAge =  (MaternalAge) applicationContext.getBean("age.cluster");
	     //ReflectionTestUtils.setField(maternalAge,"analaticDao", analaticDao);
	}
	     
	
	
	
	@Test
	public void getInferenceTest() {
		 Inference actual = maternalAge.getInference("2013","1","2015","1");  
		 
		 Assert.assertEquals(actual.getCluster(), "Age");
		 
		 
	}

}