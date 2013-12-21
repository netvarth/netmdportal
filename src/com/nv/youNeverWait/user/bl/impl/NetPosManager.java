/**
 * NetPosManager.java
 * Jithinraj
 * Nov 26, 2013
 */
package com.nv.youNeverWait.user.bl.impl;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.NetMdViewResponseDTO;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.rs.dto.NetPosViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.NetPosService;
import com.nv.youNeverWait.user.bl.validation.NetPosValidator;
import com.nv.youNeverWait.user.pl.dao.NetPosDao;



/**
 * @author Mani
 *
 */
public class NetPosManager implements NetPosService{
	
	private NetPosValidator netPosValidator;
	private NetPosDao netPosDao;
	

	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#createNetPos(com.nv.youNeverWait.rs.dto.NetPosDTO)
	 */
	@Override
	public ResponseDTO createNetPos(NetPosDTO netPos) {
	
		netPosValidator.validateNetPosAccount(netPos);
		netPosValidator.validateUserNameAndPassword(netPos.getUserName(),
				netPos.getPassword());
		ResponseDTO response = netPosDao.create(netPos);
		//sendEmailToNetPosOwner(Constants.NETMD_REGISTER, netPos);
		return response;
	}


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#updateNetPos(com.nv.youNeverWait.rs.dto.NetPosDTO)
	 */
	@Override
	public ResponseDTO updateNetPos(NetPosDTO netPos) {
		netPosValidator.validateNetposAccount(netPos);
		ResponseDTO response = netPosDao.update(netPos);
		return response;
	}


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#deleteNetPos(int)
	 */
	@Override
	public ResponseDTO deleteNetPos(int netPosId) {
		

	 ResponseDTO response = netPosDao.delete(netPosId);
		return response;

	}


	


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#viewNetPos(int)
	 */
	@Override
	public NetPosViewResponseDTO viewNetPos(int netPosId) {
		if (netPosId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetPosId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetPosViewResponseDTO response = netPosDao.view(netPosId);
		return response;
	}


	public NetPosValidator getNetPosValidator() {
		return netPosValidator;
	}


	public void setNetPosValidator(NetPosValidator netPosValidator) {
		this.netPosValidator = netPosValidator;
	}


	public NetPosDao getNetPosDao() {
		return netPosDao;
	}


	public void setNetPosDao(NetPosDao netPosDao) {
		this.netPosDao = netPosDao;
	}



}










