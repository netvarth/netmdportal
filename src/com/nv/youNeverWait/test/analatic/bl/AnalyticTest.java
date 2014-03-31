package com.nv.youNeverWait.test.analatic.bl;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.nv.youNeverWait.analytic.bl.AggregateMeasure;
import com.nv.youNeverWait.analytic.bl.Analytic;
import com.nv.youNeverWait.analytic.bl.Cluster;
import com.nv.youNeverWait.analytic.bl.Inference;
import com.nv.youNeverWait.analytic.bl.Measure;
import com.nv.youNeverWait.analytic.bl.impl.MaternalAge;
import com.nv.youNeverWait.analytic.pl.AnalyticDao;
import com.nv.youNeverWait.analytic.pl.entity.MaternalAgeEntity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:resource/context.xml",
		"file:resource/testDataSource.xml",
		"file:resource/youNeverWait-context.xml" })


public class AnalyticTest {

	
	@Autowired
	private ApplicationContext applicationContext;
	
	private Analytic analatic;
	
	
	@Before
    public void setup(){
		
		AnalyticDao analaticDao = EasyMock.createMock(AnalyticDao.class);
	     EasyMock.expect(analaticDao.getMaternalAgeInferences(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt())).andStubAnswer(new IAnswer<List<MaternalAgeEntity>>() {
		        @Override
		        public List<MaternalAgeEntity> answer() throws Throwable {
		        	      List<MaternalAgeEntity> entityList = new ArrayList<MaternalAgeEntity>();
		        	      MaternalAgeEntity maternalAgeEntity= new MaternalAgeEntity();
		        	      maternalAgeEntity.setHospital("Mother Hospital");
		        	      maternalAgeEntity.setMonth(1);
		        	      maternalAgeEntity.setYear("2013");
		        	      
		        		  entityList.add(maternalAgeEntity);        	
		        			
		            return entityList;
		        }
			});
		
	     
	     analatic = (Analytic) applicationContext.getBean("analatic.bean");
	     
	     List<Cluster> clusters =  analatic.getClusters();
	     
	     for (Cluster cluster : clusters){
	     ReflectionTestUtils.setField(cluster,"analaticDao", analaticDao);
	     }
	}
	     
	
	
	
	@Test
	public void getInferenceTest(){
		
	List<Inference> actualInference=analatic.getInferences("1", "2014", "12", "2014");
	
	Assert.assertNotNull(actualInference);
	}
	
	
	@Test
	public void getAggregaeInferenceTest(){
		
		List<Inference> actualInference=analatic.getAggregatedInferences("1", "2013", "12", "2015");
	
	Assert.assertNotNull(actualInference);
	}
	
	
	
}
