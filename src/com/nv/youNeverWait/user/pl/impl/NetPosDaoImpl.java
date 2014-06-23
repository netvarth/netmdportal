/**
 * NetPosDaoImlpimentation.java
 * Jithinraj
 * Nov 26, 2013
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.framework.util.text.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetposBranchTbl;
import com.nv.youNeverWait.pl.entity.NetposTbl;
import com.nv.youNeverWait.pl.entity.NetposLoginTbl;
import com.nv.youNeverWait.pl.entity.NetposUserTbl;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.entity.NetPosUserTypeEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.rs.dto.NetPosViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.NetPosDao;

/**
 * @author Mani
 *
 */
public class NetPosDaoImpl extends GenericDaoHibernateImpl implements NetPosDao{

	@PersistenceContext()
	private EntityManager em;

	/**
	 * Creates a netPos account
	 * 
	 * @param netPos
	 * @return ResponseDTO
	 */
	@Transactional(readOnly = false)
	@Override
	public ResponseDTO create(NetPosDTO netPos) {
		
	ResponseDTO response = new ResponseDTO();
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		NetposLoginTbl loginTbl = getLoginByUserName(netPos.getUserName());
		if (loginTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.netPosAccountAlreadyExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		// save login details
		NetposLoginTbl login = new NetposLoginTbl();
		login.setUserName(netPos.getUserName());
		login.setUserType(NetPosUserTypeEnum.Owner.getDisplayName());
		String password = StringEncoder.encryptWithKey(netPos.getPassword()
				.trim());
		login.setPassword(password);		save(login);
		// checking whether the netPos account with given name already exists or
		// not
		if (netPos.getName() != null) {
			String alphaDigitsOnly = netPos.getName().replaceAll(
					"[^a-zA-Z0-9]+", "");
			NetposTbl dupnetPosTb1 = (NetposTbl) getnetPosByName(alphaDigitsOnly
					.toUpperCase().trim());
			if (dupnetPosTb1 != null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.DuplicateNetPos);
				se.addParam(new Parameter(Constants.NAME, netPos.getName()));
				se.setDisplayErrMsg(true);
			throw se;
			}
		}
		// save netpos details to netpos table
	
NetposTbl netPosTb1=new NetposTbl();
	netPosTb1.setName(netPos.getName());
		netPosTb1.setOwnerFirstName(netPos.getOwnerFirstName());
		netPosTb1.setOwnerLastName(netPos.getOwnerLastName());
		netPosTb1.setOwnerEmail(netPos.getOwnerEmail());
		netPosTb1.setOwnerAddress(netPos.getOwnerAddress());
		netPosTb1.setOwnerMobile(netPos.getOwnerMobile());
		netPosTb1.setOwnerPhone(netPos.getOwnerPhone());
		netPosTb1.setNetposLoginTbl(login);
		netPosTb1.setHeadOfficeAddress(netPos.getHeadOfficeAddress());
		netPosTb1.setHeadOfficeEmail(netPos.getHeadOfficeEmail());
		netPosTb1.setHeadOfficeMobile(netPos.getHeadOfficeMobile());
		netPosTb1.setHeadOfficeName(netPos.getHeadOfficeName());
		netPosTb1.setHeadOfficePhone(netPos.getHeadOfficePhone());
		netPosTb1.setStatus(StatusEnum.Active.getDisplayName());
		Date createdTime = new Date();
		netPosTb1.setCreateDateTime(createdTime);
		netPosTb1.setUpdateDateTime(createdTime);
		if (superAdmin.getEnableSync() == false) {
		netPosTb1.setEnableSync(false);
		} else {
	netPosTb1.setEnableSync(true);
		netPosTb1.setSyncFreqType(superAdmin.getSyncFreqType());
			netPosTb1.setSyncTime(superAdmin.getSyncTime());
		}
		save(netPosTb1);
		response.setGlobalId(netPosTb1.getId());
		response.setSuccess(true);
		 return response;
	}

	/**
	 * @param trim
	 * @return
	 */
	private NetposTbl getnetPosByName(String trim) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETPOS_BY_NAME);
		query.setParameter("param1", trim);
		return executeUniqueQuery(NetposTbl.class, query);
	
	}

	/**
	 * @param userName
	 * @return
	 */
	private NetposLoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETPOS_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetposLoginTbl.class, query);
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.NetPosDao#update(com.nv.youNeverWait.rs.dto.NetPosDTO)
	 * 
	 * 
	 * UPDATE NETPOS
	 * 
	 */
	
	
	@Override
	public ResponseDTO update(NetPosDTO netPos) {
		ResponseDTO response = new ResponseDTO();
		if (netPos.getGlobalId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidnetPos);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netPos
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetposTbl netposTb1 = getById(NetposTbl.class, netPos.getGlobalId());
		if (netposTb1 == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidnetPos);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netPos
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* checking whether the name already exists */
		String alphaDigitsOnly = netPos.getName()
				.replaceAll("[^a-zA-Z0-9]+", "");
		NetposTbl netposTbl = (NetposTbl) getnetPosByName(alphaDigitsOnly
				.toUpperCase().trim());
		if (netposTb1 != null) {
			if (netposTb1.getId() != netPos.getGlobalId()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.netPosNameExists);
				se.addParam(new Parameter(Constants.NAME, netPos.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		netposTb1.setName(netPos.getName());
		netposTb1.setOwnerFirstName(netPos.getOwnerFirstName());
		netposTb1.setOwnerLastName(netPos.getOwnerLastName());
		netposTb1.setOwnerEmail(netPos.getOwnerEmail());
		netposTb1.setOwnerAddress(netPos.getOwnerAddress());
		netposTb1.setOwnerMobile(netPos.getOwnerMobile());
		netposTb1.setOwnerPhone(netPos.getOwnerPhone());
		netposTb1.setHeadOfficeAddress(netPos.getHeadOfficeAddress());
		netposTb1.setHeadOfficeEmail(netPos.getHeadOfficeEmail());
		netposTb1.setHeadOfficeMobile(netPos.getHeadOfficeMobile());
		netposTb1.setHeadOfficeName(netPos.getHeadOfficeName());
		netposTb1.setHeadOfficePhone(netPos.getHeadOfficePhone());
		netposTb1.setUpdateDateTime(new Date());
		update(netposTb1);

		response.setGlobalId(netposTb1.getId());
		response.setSuccess(true);
		return response;
	
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.NetPosDao#delete(int)
	 * 
	 * delete netpos
	 * 
	 */
	@Override
	@Transactional
	public ResponseDTO delete(int netPosId) {
		ResponseDTO response = new ResponseDTO();
		NetposTbl netpos = getById(NetposTbl.class, netPosId);
		if (netpos == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidnetPos);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netPosId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date updatedTime= new Date();
		netpos.setStatus(StatusEnum.InActive.getDisplayName());
		netpos.setUpdateDateTime(updatedTime);
		List<NetposBranchTbl> branches= getBranchesByNetPosId(netPosId);
		for (NetposBranchTbl netposBranchTbl : branches) {
			List<NetposUserTbl> users= getUsersBybranch(netposBranchTbl.getId());
			for (NetposUserTbl netposUserTbl :users) {
				netposUserTbl.setStatus(StatusEnum.InActive.getDisplayName());
				netposUserTbl.setUpdateDateTime(updatedTime);
				update(netposUserTbl);
			}
			netposBranchTbl.setStatus(StatusEnum.InActive.getDisplayName());
			netposBranchTbl.setUpdateDateTime(updatedTime);
			update(netposBranchTbl);
		}
		update(netpos);
		response.setGlobalId(netpos.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * @param id
	 * @return
	 */
	private List<NetposUserTbl> getUsersBybranch(int id) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETPOS_USERS);
		query.setParameter("param1", id);
		return executeQuery(NetposUserTbl.class, query);
	}

	/**
	 * @param netPosId
	 * @return
	 */
	private List<NetposBranchTbl> getBranchesByNetPosId(int netPosId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETPOS_BRANCHES);
		query.setParameter("param1", netPosId);
		return executeQuery(NetposBranchTbl.class, query);
		
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.NetPosDao#view(int)
	 * 
	 * view netpos
	 * 
	 */
	@Override
	public NetPosViewResponseDTO view(int netPosId) {
		
			NetPosViewResponseDTO response= new NetPosViewResponseDTO();
			NetposTbl netPos = getById(NetposTbl.class, netPosId);
			if (netPos == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidnetPos);
				se.addParam(new Parameter(Constants.ID, Integer.toString(netPosId)));
				se.setDisplayErrMsg(true);
				throw se;
			}
			NetPosDTO netPosDTO= new NetPosDTO();
			netPosDTO.setName(netPos.getName());
			netPosDTO.setGlobalId(netPos.getId());
			netPosDTO.setHeadOfficeAddress(netPos.getHeadOfficeAddress());
			netPosDTO.setHeadOfficeEmail(netPos.getHeadOfficeEmail());
			netPosDTO.setHeadOfficeMobile(netPos.getHeadOfficeMobile());
			netPosDTO.setHeadOfficeName(netPos.getHeadOfficeName());
			netPosDTO.setHeadOfficePhone(netPos.getHeadOfficePhone());
			netPosDTO.setOwnerAddress(netPos.getOwnerAddress());
			netPosDTO.setOwnerEmail(netPos.getOwnerEmail());
			netPosDTO.setOwnerMobile(netPos.getOwnerMobile());
			netPosDTO.setOwnerPhone(netPos.getOwnerPhone());
			netPosDTO.setOwnerFirstName(netPos.getOwnerFirstName());
			netPosDTO.setOwnerLastName(netPos.getOwnerLastName());
			netPosDTO.setStatus(netPos.getStatus());
			netPosDTO.setUserName(netPos.getNetposLoginTbl().getUserName());
			netPosDTO.setUserType(netPos.getNetposLoginTbl().getUserType());
			response.setNetPos(netPosDTO);
			response.setSuccess(true);
			return response;
		}
	

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
