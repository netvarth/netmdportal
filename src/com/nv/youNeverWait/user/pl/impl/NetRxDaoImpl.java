/**
 * NetRxDaoImpl.java
 *
 * @Author Luciya Jos
 * May 8, 2013 
 */
package com.nv.youNeverWait.user.pl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import com.nv.platform.email.util.StringEncoder;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.BranchStatusEnum;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetRxUserTypeEnum;
import com.nv.youNeverWait.pl.entity.NetrxBranchTbl;
import com.nv.youNeverWait.pl.entity.NetrxLoginTbl;
import com.nv.youNeverWait.pl.entity.NetrxPassphraseTbl;
import com.nv.youNeverWait.pl.entity.NetrxTbl;
import com.nv.youNeverWait.pl.entity.NetrxUserTbl;
import com.nv.youNeverWait.pl.entity.StatusEnum;
import com.nv.youNeverWait.pl.entity.SuperAdminTbl;
import com.nv.youNeverWait.pl.entity.SyncFreqTypeEnum;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.NetRxBranchDetail;
import com.nv.youNeverWait.rs.dto.NetRxBranchOwnerDetails;
import com.nv.youNeverWait.rs.dto.NetRxBranchResponseDTO;
import com.nv.youNeverWait.rs.dto.NetRxDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDTO;
import com.nv.youNeverWait.rs.dto.NetRxHeaderDTO;
import com.nv.youNeverWait.rs.dto.NetRxUserDetail;
import com.nv.youNeverWait.rs.dto.NetRxViewResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.PassPhraseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqDTO;
import com.nv.youNeverWait.rs.dto.SyncFreqResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.NetRxDao;

/**
 * @author netvarth
 * 
 */
public class NetRxDaoImpl extends GenericDaoHibernateImpl implements NetRxDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6251101671090236235L;
	@PersistenceContext()
	private EntityManager em;

	/**
	 * Creates a netrx account
	 * 
	 * @param netRx
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO create(NetRxDTO netRx) {
		ResponseDTO response = new ResponseDTO();
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		NetrxLoginTbl loginTbl = getLoginByUserName(netRx.getUserName());
		if (loginTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetRxAccountAlreadyExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		// save login details
		NetrxLoginTbl login = new NetrxLoginTbl();
		login.setUserName(netRx.getUserName());
		login.setUserType(NetRxUserTypeEnum.Owner.getDisplayName());
		String password = StringEncoder.encryptWithKey(netRx.getPassword().trim());
		login.setPassword(password);
		save(login);

		// checking whether the netrx account with given name already exists or
		// not
		if (netRx.getName() != null) {
			String alphaDigitsOnly = netRx.getName().replaceAll(
					"[^a-zA-Z0-9]+", "");
			NetrxTbl dupNetRxTbl = (NetrxTbl) getNetRxByName(alphaDigitsOnly
					.toUpperCase().trim());
			if (dupNetRxTbl != null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.DuplicateNetRx);
				se.addParam(new Parameter(Constants.NAME, netRx.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		// save netmd details to netmd table
		NetrxTbl netRxTbl = new NetrxTbl();
		netRxTbl.setName(netRx.getName());
		netRxTbl.setOwnerFirstName(netRx.getOwnerFirstName());
		netRxTbl.setOwnerLastName(netRx.getOwnerLastName());
		netRxTbl.setOwnerEmail(netRx.getOwnerEmail());
		netRxTbl.setOwnerAddress(netRx.getOwnerAddress());
		netRxTbl.setOwnerMobile(netRx.getOwnerMobile());
		netRxTbl.setOwnerPhone(netRx.getOwnerPhone());
		netRxTbl.setNetrxLoginTbl(login);
		netRxTbl.setHeadOfficeAddress(netRx.getHeadOfficeAddress());
		netRxTbl.setHeadOfficeEmail(netRx.getHeadOfficeEmail());
		netRxTbl.setHeadOfficeMobile(netRx.getHeadOfficeMobile());
		netRxTbl.setHeadOfficeName(netRx.getHeadOfficeName());
		netRxTbl.setHeadOfficePhone(netRx.getHeadOfficePhone());
		netRxTbl.setStatus(StatusEnum.Active.getDisplayName());
		Date createdTime = new Date();
		netRxTbl.setCreateDateTime(createdTime);
		netRxTbl.setUpdateDateTime(createdTime);
		if (superAdmin.getEnableSync() == false) {
			netRxTbl.setEnableSync(false);
		} else {
			netRxTbl.setEnableSync(true);
			netRxTbl.setSyncFreqType(superAdmin.getSyncFreqType());
			netRxTbl.setSyncTime(superAdmin.getSyncTime());
		}
		save(netRxTbl);
		response.setGlobalId(netRxTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * update a netrx account
	 * 
	 * @param netRx
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO update(NetRxDTO netRx) {
		ResponseDTO response = new ResponseDTO();
		if (netRx.getGlobalId() <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netRx
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetrxTbl netrxTbl = getById(NetrxTbl.class, netRx.getGlobalId());
		if (netrxTbl == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netRx
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* checking whether the name already exists */
		String alphaDigitsOnly = netRx.getName()
				.replaceAll("[^a-zA-Z0-9]+", "");
		NetrxTbl netRxTbl = (NetrxTbl) getNetRxByName(alphaDigitsOnly
				.toUpperCase().trim());
		if (netRxTbl != null) {
			if (netRxTbl.getId() != netRx.getGlobalId()) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NetRxNameExists);
				se.addParam(new Parameter(Constants.NAME, netRx.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		netrxTbl.setName(netRx.getName());
		netrxTbl.setOwnerFirstName(netRx.getOwnerFirstName());
		netrxTbl.setOwnerLastName(netRx.getOwnerLastName());
		netrxTbl.setOwnerEmail(netRx.getOwnerEmail());
		netrxTbl.setOwnerAddress(netRx.getOwnerAddress());
		netrxTbl.setOwnerMobile(netRx.getOwnerMobile());
		netrxTbl.setOwnerPhone(netRx.getOwnerPhone());
		netrxTbl.setHeadOfficeAddress(netRx.getHeadOfficeAddress());
		netrxTbl.setHeadOfficeEmail(netRx.getHeadOfficeEmail());
		netrxTbl.setHeadOfficeMobile(netRx.getHeadOfficeMobile());
		netrxTbl.setHeadOfficeName(netRx.getHeadOfficeName());
		netrxTbl.setHeadOfficePhone(netRx.getHeadOfficePhone());
		netrxTbl.setUpdateDateTime(new Date());
		update(netrxTbl);

		response.setGlobalId(netrxTbl.getId());
		response.setSuccess(true);
		return response;
	}
	
	/**
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO changePassword(@RequestBody PasswordDTO passwords) {
		ResponseDTO response = new ResponseDTO();
		String encOldPassword = StringEncoder.encryptWithKey(passwords.getOldPassword()
				.trim());
		NetrxLoginTbl login = getNetRxUserByName(passwords
				.getUsername());
		if (login == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNotExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (!login.getPassword().equals(encOldPassword)) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.PasswordNotExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		String encNewPassword = StringEncoder.encryptWithKey(passwords
				.getNewPassword().trim());
		login.setPassword(encNewPassword);
		update(login);
		response.setSuccess(true);
		return response;
	}
	/**
	 * Method which clears mac Id
	 * 
	 * @param header
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO clearMacId(NetRxHeaderDTO header) {

		ResponseDTO response = new ResponseDTO();
		/* Query to get netrx record for given netRx id */
		NetrxTbl netRx = getById(NetrxTbl.class, header.getNetRxId());
		/* Setting error message when netmd Id is incorrect */
		if (netRx == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxAccount);
			se.setDisplayErrMsg(true);
			throw se;
		}
		/* Query to get the list of branches for given Netrx id */
		List<NetrxBranchTbl> branches = (List<NetrxBranchTbl>) getNetRxBranches(netRx
				.getId());
		/*Setting error message when there is no branches corresponding to the Netrx given*/
		if (branches.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.BranchMissMatch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(header
					.getNetRxBranchId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		int count = 0;
		/*Iterating through each branches and retrieving the netmdPassPhrase record corresponding to it*/
		for (NetrxBranchTbl netRxBranch : branches) {
			count++;
			/*Checking whether netmd branch id matches with given netMd branch id*/
			if (netRxBranch.getId() == header.getNetRxBranchId()) {
				/*Query for retrieving netmd branch passphrase*/
				NetrxPassphraseTbl branchPassPhrase = getMacPassPhraseByBranch(
						netRxBranch.getId(), header.getPassPhrase());
				/*Setting error message when there is no netmd branch passphrase*/
				if (branchPassPhrase == null) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.PassPhraseNotExist);
					se.setDisplayErrMsg(false);
					throw se;
				}// end of branch passphrase if
				branchPassPhrase.setMacId(null);
				update(branchPassPhrase);
				response.setSuccess(true);
				return response;

			} else if (count == branches.size()) {
				/*Setting error message when no branches matches to the given branch id*/
				ServiceException se = new ServiceException(
						ErrorCodeEnum.BranchMissMatch);
				se.addParam(new Parameter(Constants.ID, Integer.toString(header
						.getNetRxBranchId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		return response;
	}
	
	/**
	 * Creates netrx user
	 * 
	 * @param netrxUser
	 * @return ResponseDTO
	 */
	@Transactional
	@Override
	public ResponseDTO createUser(NetRxHeaderDTO header, NetRxUserDetail netRxUser) {
		ResponseDTO response = new ResponseDTO();
		NetrxUserTbl user = new NetrxUserTbl();

		NetrxBranchTbl branch = getById(NetrxBranchTbl.class,
				header.getNetRxBranchId());
		if (branch == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			NetrxPassphraseTbl passPhrase = getByPassphrase(header
					.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetrxBranchTbl().getId() != header
					.getNetRxBranchId()
					|| passPhrase.getNetrxBranchTbl().getNetrxTbl().getId() != header
					.getNetRxId()) {

				ServiceException se = new ServiceException(
						ErrorCodeEnum.InvalidNetRxAccount);
				se.setDisplayErrMsg(true);
				throw se;
			} else {
				user.setNetrxPassphraseTbl(passPhrase);
			}
		}

		NetrxUserTbl netrxUserTbl = getNetRxUserByEmail(netRxUser.getEmail(),
				header.getNetRxBranchId());
		if (netrxUserTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetRxUserAlreadyExists);
			se.addParam(new Parameter(Constants.EMAIL, netRxUser.getEmail()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		NetrxLoginTbl loginTbl = getLoginByUserName(netRxUser.getUserName());
		if (loginTbl != null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetRxUserAccountAlreadyExists);
			se.addParam(new Parameter(Constants.USER, netRxUser.getUserName()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		// save user login details to NetrxLoginTbl
		NetrxLoginTbl login = new NetrxLoginTbl();
		login.setUserName(netRxUser.getUserName());
		login.setUserType(netRxUser.getUserType());
		String password = StringEncoder.encryptWithKey(netRxUser
				.getPassword().trim());
		login.setPassword(password);
		save(login);

		Date date = new Date();
		user.setAddress(netRxUser.getAddress());
		user.setCreateDateTime(date);
		user.setEmail(netRxUser.getEmail());
		user.setMobile(netRxUser.getMobile());
		user.setFirstName(netRxUser.getFirstName());
		user.setLastName(netRxUser.getLastName());
		user.setNetrxBranchTbl(branch);
		user.setNetrxLoginTbl(login);
		user.setPhone(netRxUser.getPhone());
		user.setStatus(StatusEnum.Active.getDisplayName());
		user.setUpdateDateTime(date);
		user.setUserType(netRxUser.getUserType());
		save(user);
		response.setGlobalId(user.getId());
		response.setId(netRxUser.getId());
		return response;
	}

	/**
	 * Update netrx user
	 * 
	 * @param netrxUser
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO updateUser(NetRxHeaderDTO header, NetRxUserDetail netRxUser) {
		ResponseDTO response = new ResponseDTO();
		NetrxUserTbl user = getById(NetrxUserTbl.class, netRxUser.getGlobalId());
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxUser);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netRxUser
					.getGlobalId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetrxUserTbl netrxUserTbl = getNetRxUserByEmail(netRxUser.getEmail(),
				netRxUser.getNetRxBranchId());
		// duplicate checking
		if (netrxUserTbl != null && netrxUserTbl.getId() != user.getId()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.NetRxUserAlreadyExists);
			se.addParam(new Parameter(Constants.EMAIL, netRxUser.getEmail()));
			se.setDisplayErrMsg(true);
			throw se;
		}

		user.setAddress(netRxUser.getAddress());
		user.setEmail(netRxUser.getEmail());
		user.setMobile(netRxUser.getMobile());
		user.setFirstName(netRxUser.getFirstName());
		user.setLastName(netRxUser.getLastName());
		user.setPhone(netRxUser.getPhone());
		user.setUserType(netRxUser.getUserType());
		user.setUpdateDateTime(new Date());
		update(user);
		NetrxLoginTbl login = getById(NetrxLoginTbl.class, user
				.getNetrxLoginTbl().getId());
		login.setUserType(netRxUser.getUserType());
		update(login);

		response.setGlobalId(user.getId());
		response.setId(netRxUser.getId());
		return response;
	}
	/**
	 * View netrx user
	 * 
	 * @param globalId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public NetRxUserDTO viewUser(int globalId) {
		NetRxUserDTO response = new NetRxUserDTO();
		NetRxUserDetail userDetail = new NetRxUserDetail();
		NetrxUserTbl user = getById(NetrxUserTbl.class, globalId);
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxUser);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		userDetail.setAddress(user.getAddress());
		userDetail.setEmail(user.getEmail());
		userDetail.setFirstName(user.getFirstName());
		userDetail.setGlobalId(user.getId());
		userDetail.setLastName(user.getLastName());
		userDetail.setMobile(user.getMobile());
		userDetail.setNetRxBranchId(user.getNetrxBranchTbl().getId());
		userDetail.setNetRxId(user.getNetrxBranchTbl().getNetrxTbl().getId());
		userDetail.setPhone(user.getPhone());
		userDetail.setStatus(user.getStatus());
		if (user.getNetrxLoginTbl() != null) {
			userDetail.setUserName(user.getNetrxLoginTbl().getUserName());
			userDetail.setUserType(user.getNetrxLoginTbl().getUserType());
		}
		response.setUser(userDetail);
		response.setSuccess(true);
		return response;
	}
	/**
	 * To get netrx branch name by giving netrx branch id
	 * 
	 * @param netRxBranchId
	 * @return netrx branch name
	 */
	@Override
	@Transactional
	public String getNetRxBranchName(int netRxBranchId) {
		NetrxBranchTbl netRxBranchTbl = getById(NetrxBranchTbl.class,
				netRxBranchId);
		if (netRxBranchTbl == null) {
			return "";
		}
		return netRxBranchTbl.getName();
	}
	/**
	 * Retrieve NetrxPassphraseTbl record by giving passPhrase
	 * 
	 * @param passPhrase
	 * @return NetrxPassphraseTbl
	 */
	private NetrxPassphraseTbl getByPassphrase(String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_BRANCH_PASSPHRASE);
		query.setParameter("param1", passPhrase);
		return executeUniqueQuery(NetrxPassphraseTbl.class, query);
	}
	/**
	 * Method performed for deleting users from NetrxUserTbl
	 * 
	 * @param globalId
	 */
	@Override
	@Transactional
	public ResponseDTO deleteUser(int globalId) {
		ResponseDTO response = new ResponseDTO();
		NetrxUserTbl user = getById(NetrxUserTbl.class, globalId);
		if (user == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRxUser);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		user.setStatus(StatusEnum.InActive.getDisplayName());
		user.setUpdateDateTime(new Date());
		update(user);
		response.setSuccess(true);
		return response;
	}
	/**
	 * Retrieve netrx user by email
	 * 
	 * @param email
	 * @return NetrxUserTbl
	 */
	private NetrxUserTbl getNetRxUserByEmail(String email, int branchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_BY_EMAIL_AND_BRANCH);
		query.setParameter("param1", email.trim());
		query.setParameter("param2", branchId);
		return executeUniqueQuery(NetrxUserTbl.class, query);
	}
	/**
	 * To get netrx name by giving netrx id
	 * 
	 * @param netMdId
	 * @return netmd name
	 */
	@Override
	public String getNetRxName(int netRxId) {
		NetrxTbl netrxTbl = getById(NetrxTbl.class, netRxId);
		if (netrxTbl == null) {
			return "";
		}
		return netrxTbl.getName();
	}
	/**
	 * Deletes a netRx 
	 * 
	 * @param netRxId
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO deleteNetRx(int netRxId) {
		ResponseDTO response = new ResponseDTO();
		NetrxTbl netrx = getById(NetrxTbl.class, netRxId);
		if (netrx == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netRxId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		Date updatedTime= new Date();
		netrx.setStatus(StatusEnum.InActive.getDisplayName());
		netrx.setUpdateDateTime(updatedTime);
		List<NetrxBranchTbl> branches= getBranchesByNetRxId(netRxId);
		for (NetrxBranchTbl netRxBranchTbl : branches) {
			List<NetrxUserTbl> users= getUsersBybranch(netRxBranchTbl.getId());
			for (NetrxUserTbl netrxUserTbl :users) {
				netrxUserTbl.setStatus(StatusEnum.InActive.getDisplayName());
				netrxUserTbl.setUpdateDateTime(updatedTime);
				update(netrxUserTbl);
			}
			netRxBranchTbl.setStatus(StatusEnum.InActive.getDisplayName());
			netRxBranchTbl.setUpdateDateTime(updatedTime);
			update(netRxBranchTbl);
		}
		update(netrx);
		response.setGlobalId(netrx.getId());
		response.setSuccess(true);
		return response;
	}

	/**
	 * View netRx account
	 * 
	 * @param netRxId
	 * @return NetRxViewResponseDTO
	 */
	@Override
	@Transactional
	public NetRxViewResponseDTO viewNetRx(int netRxId) {
		NetRxViewResponseDTO response= new NetRxViewResponseDTO();
		NetrxTbl netRx = getById(NetrxTbl.class, netRxId);
		if (netRx == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netRxId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetRxDTO netRxDTO= new NetRxDTO();
		netRxDTO.setName(netRx.getName());
		netRxDTO.setGlobalId(netRx.getId());
		netRxDTO.setHeadOfficeAddress(netRx.getHeadOfficeAddress());
		netRxDTO.setHeadOfficeEmail(netRx.getHeadOfficeEmail());
		netRxDTO.setHeadOfficeMobile(netRx.getHeadOfficeMobile());
		netRxDTO.setHeadOfficeName(netRx.getHeadOfficeName());
		netRxDTO.setHeadOfficePhone(netRx.getHeadOfficePhone());
		netRxDTO.setOwnerAddress(netRx.getOwnerAddress());
		netRxDTO.setOwnerEmail(netRx.getOwnerEmail());
		netRxDTO.setOwnerMobile(netRx.getOwnerMobile());
		netRxDTO.setOwnerPhone(netRx.getOwnerPhone());
		netRxDTO.setOwnerFirstName(netRx.getOwnerFirstName());
		netRxDTO.setOwnerLastName(netRx.getOwnerLastName());
		netRxDTO.setStatus(netRx.getStatus());
		netRxDTO.setUserName(netRx.getNetrxLoginTbl().getUserName());
		netRxDTO.setUserType(netRx.getNetrxLoginTbl().getUserType());
		response.setNetRxDTO(netRxDTO);
		response.setSuccess(true);
		return response;
	}

	/**
	 * View netrx branch details
	 * 
	 * @param globalId
	 * @return NetRxBranchResponseDTO
	 */
	@Override
	@Transactional
	public NetRxBranchResponseDTO viewBranch(int netrxBranchId) {
		NetRxBranchResponseDTO response= new NetRxBranchResponseDTO();
		NetrxBranchTbl netrxBranchTbl = getById(NetrxBranchTbl.class,
				netrxBranchId);
		if(netrxBranchTbl==null)
		{
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netrxBranchId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetRxBranchDetail branch= new NetRxBranchDetail();
		branch.setName(netrxBranchTbl.getName());
		branch.setAddress(netrxBranchTbl.getAddress());
		branch.setEmail(netrxBranchTbl.getEmail());
		branch.setPhone(netrxBranchTbl.getPhone());
		branch.setMobile(netrxBranchTbl.getMobile());
		branch.setGlobalId(netrxBranchTbl.getId());
		branch.setNetRxId(netrxBranchTbl.getNetrxTbl().getId());
		branch.setStatus(netrxBranchTbl.getStatus());
		List<NetrxPassphraseTbl> passPhrases = getMacPassPhraseByNetRxBranch(netrxBranchId);
		List<PassPhraseDTO> passPhraseList = new ArrayList<PassPhraseDTO>();
		branch.setNumberOfDevices(passPhrases.size());
		for (NetrxPassphraseTbl netrxPassphraseTbl : passPhrases) {
			PassPhraseDTO pass = new PassPhraseDTO();
			pass.setPassPhrase(netrxPassphraseTbl.getPassPhrase());
			pass.setMacId(netrxPassphraseTbl.getMacId());
			if (netrxPassphraseTbl.isPrimaryDevice()) {
				pass.setPrimary(true);
			} else {
				pass.setPrimary(false);
			}
			passPhraseList.add(pass);
		}
		branch.setPassPhrase(passPhraseList);
		response.setBranch(branch);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Deletes a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO deleteBranch(int globalId) {
		ResponseDTO response = new ResponseDTO();

		NetrxBranchTbl netrxBranchTbl = getById(NetrxBranchTbl.class,
				globalId);
		if(netrxBranchTbl==null)
		{
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		netrxBranchTbl.setStatus(StatusEnum.InActive.getDisplayName());
		netrxBranchTbl.setUpdateDateTime(new Date());
		for (NetrxUserTbl netrxUserTbl : netrxBranchTbl.getNetrxUserTbls()) {
			netrxUserTbl.setStatus(StatusEnum.InActive.getDisplayName());
			update(netrxUserTbl);
		}
		update(netrxBranchTbl);
		response.setSuccess(true);
		response.setGlobalId(globalId);
		return response;
	}

	/**
	 * Creates a netRx branch
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO createBranch(NetRxBranchDetail branch) {
		ResponseDTO response = new ResponseDTO();
		NetrxTbl netRx = getById(NetrxTbl.class, branch.getNetRxId());
		if (netRx == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch
					.getNetRxId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetrxBranchTbl netRxBranch = new NetrxBranchTbl();
		netRxBranch.setAddress(branch.getAddress());
		Date createdTime = new Date();
		netRxBranch.setName(branch.getName());
		netRxBranch.setStatus(BranchStatusEnum.Active.getDisplayName());
		netRxBranch.setCreateDateTime(createdTime);
		netRxBranch.setUpdateDateTime(createdTime);
		netRxBranch.setPhone(branch.getPhone());
		netRxBranch.setMobile(branch.getMobile());
		netRxBranch.setEmail(branch.getEmail());
		netRxBranch.setNetrxTbl(netRx);
		if(netRx.getEnableSync()==false){
			netRxBranch.setEnableSync(false);
		}
		else{
			netRxBranch.setEnableSync(true);
			netRxBranch.setSyncFreqType(netRx.getSyncFreqType());
			netRxBranch.setSyncTime(netRx.getSyncTime());
		}
		save(netRxBranch);
		NetrxPassphraseTbl netRxPassPhrase = new NetrxPassphraseTbl();
		String passphrase = StringEncoder.getKeyvalue(StringEncoder.getKey());
		netRxPassPhrase.setPassPhrase(passphrase);
		netRxPassPhrase.setNetrxBranchTbl(netRxBranch);
		netRxPassPhrase.setMacId(null);
		netRxPassPhrase.setPrimaryDevice(true);
		save(netRxPassPhrase);
		for (int i = 1; i < branch.getNumberOfDevices(); i++) {
			NetrxPassphraseTbl netRxPassPhraseTbl = new NetrxPassphraseTbl();
			String pass = StringEncoder.getKeyvalue(StringEncoder.getKey());
			netRxPassPhraseTbl.setPassPhrase(pass);
			netRxPassPhraseTbl.setNetrxBranchTbl(netRxBranch);
			netRxPassPhraseTbl.setMacId(null);
			netRxPassPhraseTbl.setPrimaryDevice(false);
			save(netRxPassPhraseTbl);
		}
		response.setSuccess(true);
		response.setGlobalId(netRxBranch.getId());
		return response;
	}

	/**
	 * Retrieve netrx owner details
	 * 
	 * @param netRxBranchId
	 * @return NetRxBranchOwnerDetails
	 */

	@Override
	@Transactional
	public NetRxBranchOwnerDetails getBranchOwners(int netRxBranchId) {
		NetrxBranchTbl netrxBranchTbl = getById(NetrxBranchTbl.class,
				netRxBranchId);
		NetRxBranchOwnerDetails response = new NetRxBranchOwnerDetails();
		response.setOwnerEmail(netrxBranchTbl.getNetrxTbl().getOwnerEmail());
		response.setOwnerFirstName(netrxBranchTbl.getNetrxTbl()
				.getOwnerFirstName());
		response.setOwnerLastName(netrxBranchTbl.getNetrxTbl()
				.getOwnerLastName());
		response.setNetRxName(netrxBranchTbl.getNetrxTbl().getName());
		List<NetrxPassphraseTbl> netrxPassphraseTbls = getMacPassPhraseByNetRxBranch(netRxBranchId);
		List<String> passPhrases = new ArrayList<String>();
		for (NetrxPassphraseTbl netrxPassphraseTbl : netrxPassphraseTbls) {
			if (netrxPassphraseTbl.isPrimaryDevice()) {
				response.setPrimaryPassPhrase(netrxPassphraseTbl
						.getPassPhrase());
			} else {
				passPhrases.add(netrxPassphraseTbl.getPassPhrase());
			}

		}
		response.setPassPhrase(passPhrases);
		response.setBranchName(netrxBranchTbl.getName());
		return response;
	}

	/**
	 * Updates netRx branch details
	 * 
	 * @param branch
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO updateNetRxBranch(NetRxBranchDetail branch) {
		ResponseDTO response= new ResponseDTO();
		NetrxBranchTbl netrxBranchTbl= getById(NetrxBranchTbl.class, branch.globalId);
		if(netrxBranchTbl== null){
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branch.globalId)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		netrxBranchTbl.setName(branch.getName());
		netrxBranchTbl.setAddress(branch.getAddress());
		netrxBranchTbl.setPhone(branch.getPhone());
		netrxBranchTbl.setMobile(branch.getMobile());
		netrxBranchTbl.setEmail(branch.getEmail());
		netrxBranchTbl.setUpdateDateTime(new Date());
		update(netrxBranchTbl);
		response.setGlobalId(netrxBranchTbl.getId());
		response.setId(branch.getId());
		response.setSuccess(true);
		return response;
	}


	@Override
	@Transactional
	public UserCredentials getUserCredentials(LoginDTO login) {
		UserCredentials user = new UserCredentials();
		NetrxLoginTbl userLogin = getLoginByUserName(login.getUserName().trim());
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (userLogin.getUserType().equals(
				NetRxUserTypeEnum.Owner.getDisplayName())) {
			NetrxTbl netrxTbl = getNetRxByLoginId(userLogin.getId());
			if (netrxTbl == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(netrxTbl.getOwnerEmail());
			user.setFirstName(netrxTbl.getOwnerFirstName());
			user.setLastName(netrxTbl.getOwnerLastName());
			user.setUserName(userLogin.getUserName());
		} else {
			NetrxUserTbl netrxUser = getNetRxUserByLoginId(userLogin.getId());
			if (netrxUser == null) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.NoUserExists);
				se.addParam(new Parameter(Constants.NAME, userLogin
						.getUserName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			user.setEmailId(netrxUser.getEmail());
			user.setFirstName(netrxUser.getFirstName());
			user.setLastName(netrxUser.getLastName());
			user.setUserName(userLogin.getUserName());
		}
		return user;
	}
	
	/**
	 * To reset password of netRx user/owner
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	@Transactional
	public ResponseDTO resetPassword(LoginDTO login) {
		ResponseDTO response= new ResponseDTO();
		String newPassword = StringEncoder.encryptWithKey(login.getPassword());
		String decrypedUserName = StringEncoder.decryptWithStaticKey(login
				.getUserName());
		NetrxLoginTbl userLogin = getLoginByUserName(decrypedUserName);
		if (userLogin == null) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		userLogin.setPassword(newPassword);
		update(userLogin);
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.NetRxDao#setNetRxSync(com.nv.youNeverWait.rs.dto.SyncFreqDTO)
	 */
	@Override
	@Transactional
	public SyncFreqResponseDTO setNetRxSync(SyncFreqDTO sync) {
 		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		Date newDate= new Date();
		NetrxTbl netrx = getById(NetrxTbl.class, sync.getNetrxId());
		SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
		if (netrx != null) {
			if (superAdmin.getEnableSync() == false) {
				netrx.setEnableSync(false);
			} else
				netrx.setEnableSync(sync.isEnableSync());
			netrx.setUpdateDateTime(newDate);
			update(netrx);
			/**** Setting values when the sync is enabled ****/
			if (netrx.getEnableSync() == true) {

				/****** Checking sync values with global sync time *****/
				checkSync(superAdmin.getSyncFreqType(), sync.getSyncFreqType(),
						sync.getSyncTime(), superAdmin.getSyncTime());

				netrx.setSyncTime(sync.getSyncTime());
				netrx.setSyncFreqType(sync.getSyncFreqType());
				netrx.setUpdateDateTime(newDate);
				update(netrx);
			} else {
				if (sync.isEnableSync())
					response.setMsg(Constants.MESSAGE);
				/****** Setting all branches of the lab as disabled *******/
				for (NetrxBranchTbl netrxBranch : netrx.getNetrxBranchTbls()) {
					netrxBranch.setEnableSync(netrx.getEnableSync());
					netrxBranch.setUpdateDateTime(newDate);
					update(netrxBranch);
					
				}// end of for loop
			}

		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(sync
					.getNetrxId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		response.setSuccess(true);
		return response;
	}
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.NetRxDao#setNetRxBranchSync(com.nv.youNeverWait.rs.dto.SyncFreqDTO)
	 */
	@Override
	@Transactional
	public SyncFreqResponseDTO setNetRxBranchSync(SyncFreqDTO sync) {
		SyncFreqResponseDTO response = new SyncFreqResponseDTO();
		Date newDate= new Date();
		NetrxBranchTbl netrxBranch = getById(NetrxBranchTbl.class,
				sync.getNetrxBranchId());
		if (netrxBranch != null) {
			if (netrxBranch.getNetrxTbl().getEnableSync() == false) {
				netrxBranch.setEnableSync(false);
			} else {
				SuperAdminTbl superAdmin = getById(SuperAdminTbl.class, 1);
				if (superAdmin.getEnableSync() == false) {
					netrxBranch.setEnableSync(false);
				} else {
					netrxBranch.setEnableSync(sync.isEnableSync());
				}
			}
			netrxBranch.setUpdateDateTime(newDate);
			update(netrxBranch);
			if (netrxBranch.getEnableSync()==true) {
				/**
				 * Checking whether branch sync time is greater than netrx sync
				 * time
				 **/
				checkSync(netrxBranch.getNetrxTbl().getSyncFreqType(),
						sync.getSyncFreqType(), sync.getSyncTime(), netrxBranch
								.getNetrxTbl().getSyncTime());

				netrxBranch.setSyncTime(sync.getSyncTime());
				netrxBranch.setSyncFreqType(sync.getSyncFreqType());
				netrxBranch.setUpdateDateTime(newDate);
				update(netrxBranch);
			} else {
				if (sync.isEnableSync())
				response.setMsg(Constants.MESSAGE);
			}
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		response.setSuccess(true);
		return response;
	}

	/**
	 * @param priorSyncFreqType
	 * @param syncFreqType
	 * @param syncTime
	 * @param priorSyncTime
	 */
	private void checkSync(String priorSyncFreqType, String syncFreqType,
			int syncTime, int priorSyncTime) {
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of daily if loop
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.HOURLY.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SynctimeExceeds);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (syncFreqType.equals(SyncFreqTypeEnum.HOURLY
					.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of hourly if loop
		if (priorSyncFreqType.equals(SyncFreqTypeEnum.MINUTES.getDisplayName())) {
			if (syncFreqType.equals(SyncFreqTypeEnum.DAILY.getDisplayName())
					|| syncFreqType.equals(SyncFreqTypeEnum.HOURLY
							.getDisplayName())) {
				ServiceException se = new ServiceException(
						ErrorCodeEnum.SynctimeExceeds);
				se.setDisplayErrMsg(true);
				throw se;
			} else if (syncFreqType.equals(SyncFreqTypeEnum.MINUTES
					.getDisplayName())) {
				if (syncTime > priorSyncTime) {
					ServiceException se = new ServiceException(
							ErrorCodeEnum.SynctimeExceeds);
					se.setDisplayErrMsg(true);
					throw se;
				}
			}
		} // end of minutes if loop
	}
	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.NetRxDao#getBranchSyncDetails(int)
	 */
	@Override
	@Transactional
	public SyncFreqDTO getBranchSyncDetails(int branchId) {
		SyncFreqDTO sync = new SyncFreqDTO();
		NetrxBranchTbl netrxBranch = getById(NetrxBranchTbl.class, branchId);
		if (netrxBranch != null) {
			sync.setSyncFreqType(netrxBranch.getSyncFreqType());
			sync.setSyncTime(netrxBranch.getSyncTime());
			sync.setEnableSync(netrxBranch.getEnableSync());
			sync.setSuccess(true);
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidBranch);
			se.addParam(new Parameter(Constants.ID, Integer.toString(branchId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

		return sync;
	}

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.pl.dao.NetRxDao#getNetrxSyncDetails(int)
	 */
	@Override
	@Transactional
	public SyncFreqDTO getNetrxSyncDetails(int netrxId) {
		SyncFreqDTO sync = new SyncFreqDTO();
		NetrxTbl netrx = getById(NetrxTbl.class, netrxId);
		if (netrx != null) {
			sync.setSyncFreqType(netrx.getSyncFreqType());
			sync.setSyncTime(netrx.getSyncTime());
			sync.setEnableSync(netrx.getEnableSync());
			sync.setSuccess(true);
		} else {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetRx);
			se.addParam(new Parameter(Constants.ID, Integer.toString(netrxId)));
			se.setDisplayErrMsg(true);
			throw se;
		}

		return sync;
	}
	
	
	/**
	 * 
	 * @param loginId
	 * @return NetrxUserTbl
	 */
	public NetrxUserTbl getNetRxUserByLoginId(int loginId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETRX_USER);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetrxUserTbl.class, query);
	}
	
	/**
	 * 
	 * @param loginId
	 * @return NetrxTbl
	 */
	public NetrxTbl getNetRxByLoginId(int loginId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_BY_LOGIN_ID);
		query.setParameter("param1", loginId);
		return executeUniqueQuery(NetrxTbl.class, query);
	}
	
	/**
	 * Retrieve netrx branches
	 * 
	 * @param name
	 * @return NetrxTbl
	 */
	private List<NetrxBranchTbl> getBranchesByNetRxId(int netRxId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETRX_BRANCHES);
		query.setParameter("param1", netRxId);
		return executeQuery(NetrxBranchTbl.class, query);
	}

	private List<NetrxUserTbl> getUsersBybranch(int netRxBranchId) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETRX_USERS);
		query.setParameter("param1", netRxBranchId);
		return executeQuery(NetrxUserTbl.class, query);
	}

	/**
	 * Retrieve netrx record by giving name
	 * 
	 * @param name
	 * @return NetrxTbl
	 */
	private NetrxTbl getNetRxByName(String name) {
		javax.persistence.Query query = em.createQuery(Query.GET_NETRX_BY_NAME);
		query.setParameter("param1", name);
		return executeUniqueQuery(NetrxTbl.class, query);
	}
	
	/**
	 * Retrieve NetrxLoginTbl record by giving userName
	 * 
	 * @param userName
	 * @return NetrxLoginTbl
	 */
	private NetrxLoginTbl getLoginByUserName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetrxLoginTbl.class, query);
	}

	/**
	 * @param netRxBranchId
	 * @return
	 */
	private List<NetrxPassphraseTbl> getMacPassPhraseByNetRxBranch(
			int netRxBranchId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_MAC_AND_PASSPHRASE_BY_NETRX_BRANCH);
		query.setParameter("param1", netRxBranchId);
		return (List<NetrxPassphraseTbl>) executeQuery(
				NetrxPassphraseTbl.class, query);
	}
	/**
	 * get MacPassPhrase By Branch
	 * @param branchId
	 * @param passPhrase 
	 * @return LabPassphraseTbl
	 */
	public NetrxPassphraseTbl getMacPassPhraseByBranch(int branchId,
			String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_PASSPHRASE_BY_BRANCH_ID);
		query.setParameter("param1", branchId);
		query.setParameter("param2", passPhrase);
		return executeUniqueQuery(NetrxPassphraseTbl.class, query);
	}
	/**
	 * Method to get new netmd branches list
	 * @param netRxId 
	 * @return List<NetrxBranchTbl>
	 */
	public List<NetrxBranchTbl> getNetRxBranches(int netRxId) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_BRANCHES);
		query.setParameter("param1", netRxId);
		return executeQuery(NetrxBranchTbl.class, query);
	}

	/**
	 * Get NetRx User By Name
	 * @param userName
	 * @return LoginTbl
	 */
	public NetrxLoginTbl getNetRxUserByName(String userName) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETRX_LOGIN_BY_USERNAME);
		query.setParameter("param1", userName);
		return executeUniqueQuery(NetrxLoginTbl.class, query);
	}
	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	

	

	
}
