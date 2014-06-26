/**
 * 
 */
package com.nv.youNeverWait.user.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nv.youNeverWait.pl.entity.LabFacilityTbl;
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
	private FacilityDao facilityDao;

	@Override
	public int processFacility(FacilitySyncDTO facility, int branchId) {
		
		int facilityId = facilityDao.create(facility, branchId);
		
		LabFacilityTbl facilityTbl=getFacility(facility.getFacility().getAddress().getEmail());
		if(facilityTbl==null)
			facilityTbl=new LabFacilityTbl();
		facilityTbl.setEmail(facility.getFacility().getAddress().getEmail());
		facilityTbl.setCity(facility.getFacility().getAddress().getCity());
		facilityTbl.setMobile(facility.getFacility().getAddress().getMobile());
		facilityTbl.setPhone(facility.getFacility().getAddress().getPhone());
		facilityTbl.setPin(facility.getFacility().getAddress().getPin());
		facilityTbl.setState(facility.getFacility().getAddress().getState());
		facilityTbl.setName(facility.getFacility().getName());
		//facilityTbl.setId(facility.getFacility().getUid());
		
		saveOrUpdate(LabFacilityTbl.class, facilityTbl);
		
		return facilityTbl.getId();
	}

	private LabFacilityTbl getFacility(String email){
		javax.persistence.Query query=em.createQuery(Query.GET_FACILITY_BY_EMAILID);
		query.setParameter("param1", email);
		return executeUniqueQuery(LabFacilityTbl.class, query);
	}

	@Override
	public int create(FacilitySyncDTO facility, int branchId) {
		
		return 0;
	}

	/**
	 * @return the facilityDao
	 */
	public FacilityDao getFacilityDao() {
		return facilityDao;
	}

	/**
	 * @param facilityDao the facilityDao to set
	 */
	public void setFacilityDao(FacilityDao facilityDao) {
		this.facilityDao = facilityDao;
	}
}
