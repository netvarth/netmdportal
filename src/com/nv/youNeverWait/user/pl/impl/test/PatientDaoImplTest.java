/**
 * 
 */
package com.nv.youNeverWait.user.pl.impl.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.youNeverWait.rs.dto.Address;
import com.nv.youNeverWait.rs.dto.Patient;
import com.nv.youNeverWait.user.pl.dao.PatientDao;

/**
 * @author Mani E.V
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:resource/context.xml", "file:resource/testDataSource.xml" , "file:resource/youNeverWait-context.xml"})
public class PatientDaoImplTest {
	@Autowired
	private ApplicationContext applicationContext;
	
	/**
	 * 
	 */
	@Test
	public void setNetlimsPatient(){
	
		
	}
}
