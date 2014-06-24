/**
 * 
 */
package com.nv.youNeverWait.user.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nv.youNeverWait.pl.entity.DoctorTbl;
import com.nv.youNeverWait.pl.entity.FacilityTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.FacilityDao;

/**
 * @author Mani E.V
 *
 */
public class FacilityDaoImpl extends GenericDaoHibernateImpl implements FacilityDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext()
	private EntityManager em;

	@Override
	public int processFacility(FacilitySyncDTO facility) {
		
		FacilityTbl facilityTbl=getFacility(facility.getFacility().getAddress().getEmail());
		if(facilityTbl==null)
			facilityTbl=new FacilityTbl();
		facilityTbl.setEmail(facility.getFacility().getAddress().getEmail());
		facilityTbl.setCity(facility.getFacility().getAddress().getCity());
		facilityTbl.setMobile(facility.getFacility().getAddress().getMobile());
		facilityTbl.setPhone(facility.getFacility().getAddress().getPhone());
		facilityTbl.setPin(facility.getFacility().getAddress().getPin());
		facilityTbl.setState(facility.getFacility().getAddress().getState());
		facilityTbl.setName(facility.getFacility().getName());
		//facilityTbl.setId(facility.getFacility().getUid());
		
		saveOrUpdate(FacilityTbl.class, facilityTbl);
		
		return facilityTbl.getId();
	}

	private FacilityTbl getFacility(String email){
		javax.persistence.Query query=em.createQuery(Query.GET_FACILITY_BY_EMAILID);
		query.setParameter("param1", email);
		return executeUniqueQuery(FacilityTbl.class, query);
	}
}
