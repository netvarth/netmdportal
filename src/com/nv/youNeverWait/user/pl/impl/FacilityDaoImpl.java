/**
 * 
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.LabFacilityTbl;
import com.nv.youNeverWait.pl.entity.LoginTbl;
import com.nv.youNeverWait.pl.entity.NetmdUserTypeEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.FacilitySyncDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
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

	private LabFacilityTbl getBy_uid_branchId(int uid, int branchId) {
		javax.persistence.Query query=em.createQuery(Query.GET_FACILITY_BY_UID_BRANCHID);
		query.setParameter("param1", uid);
		query.setParameter("param2", branchId);
		return executeUniqueQuery(LabFacilityTbl.class, query);
	}

	@Override
	@Transactional(readOnly=false)
	public int create(FacilitySyncDTO facility, int branchId) {
		LabFacilityTbl labFacilityTbl = getBy_uid_branchId(facility.getFacility().getUid(), branchId);
		if(labFacilityTbl!=null)
			return labFacilityTbl.getId();
		labFacilityTbl = new LabFacilityTbl();
		labFacilityTbl.setUid(facility.getFacility().getUid());
		labFacilityTbl.setEmail(facility.getFacility().getAddress().getEmail());
		labFacilityTbl.setCity(facility.getFacility().getAddress().getCity());
		labFacilityTbl.setMobile(facility.getFacility().getAddress().getMobile());
		labFacilityTbl.setPhone(facility.getFacility().getAddress().getPhone());
		labFacilityTbl.setPin(facility.getFacility().getAddress().getPin());
		labFacilityTbl.setState(facility.getFacility().getAddress().getState());
		labFacilityTbl.setName(facility.getFacility().getName());
		labFacilityTbl.setLabBranchTbl(getById(LabBranchTbl.class, branchId));
		labFacilityTbl.setCreateDateTime(new Date());
		save(labFacilityTbl);
		return labFacilityTbl.getId();
	}

	private LabFacilityTbl getByEmailId(String email) {
		javax.persistence.Query query=em.createQuery(Query.GET_FACILITY_BY_EMAILID);
		query.setParameter("param1", email);
		return executeUniqueQuery(LabFacilityTbl.class, query);
	}

	@Override
	@Transactional(readOnly=false)
	public int update(FacilitySyncDTO facility, Integer branchId) {
		LabFacilityTbl labFacilityTbl = getById(LabFacilityTbl.class, facility.getGlobalId());
		if(labFacilityTbl==null)
			return 0;
		labFacilityTbl.setEmail(facility.getFacility().getAddress().getEmail());
		labFacilityTbl.setCity(facility.getFacility().getAddress().getCity());
		labFacilityTbl.setMobile(facility.getFacility().getAddress().getMobile());
		labFacilityTbl.setPhone(facility.getFacility().getAddress().getPhone());
		labFacilityTbl.setPin(facility.getFacility().getAddress().getPin());
		labFacilityTbl.setState(facility.getFacility().getAddress().getState());
		labFacilityTbl.setName(facility.getFacility().getName());
		labFacilityTbl.setLabBranchTbl(getById(LabBranchTbl.class, branchId));
		update(labFacilityTbl);		
		return labFacilityTbl.getId();
	}

	@Override
	@Transactional(readOnly=false)
	public LoginDTO setLoginInfo(LoginDTO login, int facilityId) {	
		LabFacilityTbl labFacilityTbl = getById(LabFacilityTbl.class, facilityId);
		LoginTbl loginTbl = getLoginByUser_Type(labFacilityTbl.getEmail(), NetmdUserTypeEnum.Facility.getDisplayName());
		if(loginTbl==null){
			loginTbl = new LoginTbl();
			loginTbl.setUserName(labFacilityTbl.getEmail());
			loginTbl.setUserType(NetmdUserTypeEnum.Facility.getDisplayName());
			loginTbl.setPassword(login.getPassword());
			save(loginTbl);
		}
		login.setPassword(loginTbl.getPassword());	
		labFacilityTbl.setLoginTbl(loginTbl);
		update(labFacilityTbl);
		return login;
	}

	private LoginTbl getLoginByUser_Type(String userId, String userType) {
		javax.persistence.Query query=em.createQuery(Query.GET_NETMD_LOGIN_BY_USERNAME_USERTYPE);
		query.setParameter("param1", userId);
		query.setParameter("param2", userType);
		return executeUniqueQuery(LoginTbl.class, query);
	}

	@Override
	public String getFacilityBranch(int branchId) {
		return getById(LabBranchTbl.class, branchId).getName();
	}

	@Override
	public void validateFacility(FacilitySyncDTO facility) {
		LabFacilityTbl labFacilityTbl = getByEmailId(facility.getFacility().getAddress().getEmail());
		if(labFacilityTbl!=null){
			ServiceException se = new ServiceException(ErrorCodeEnum.NetRxUserAlreadyExists);
			se.addParam(new Parameter(Constants.EMAIL, facility.getFacility().getAddress().getEmail()));
			se.setDisplayErrMsg(true);
			throw se;
		}	
	}
}
