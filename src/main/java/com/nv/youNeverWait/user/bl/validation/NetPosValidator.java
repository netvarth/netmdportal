/**
 * NetPosValidator.java
 * Jithinraj
 * Nov 26, 2013
 */
package com.nv.youNeverWait.user.bl.validation;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.queryBuilder.NetPosPropertyEnum;
import com.nv.youNeverWait.util.filter.validation.FilterValidator;

/**
 * @author Mani
 *
 */
public class NetPosValidator extends FilterValidator{

	/**
	 * @param netPos
	 */
	public void validateNetPosAccount(NetPosDTO netPos) {
		
		if (netPos.getName() == null || netPos.getName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidnetPosName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netPos.getOwnerFirstName() == null
				|| netPos.getOwnerFirstName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netPos.getOwnerEmail() == null || netPos.getOwnerEmail().isEmpty()
				|| !isValidEmail(netPos.getOwnerEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidOwnerEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netPos.getHeadOfficeName() == null
				|| netPos.getHeadOfficeName().isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (netPos.getHeadOfficeEmail() == null
				|| netPos.getHeadOfficeEmail().isEmpty()
				|| !isValidEmail(netPos.getHeadOfficeEmail())) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidHeadOfficeEmail);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
	}

	/**
	 * @param ownerEmail
	 * @return
	 */
	private boolean isValidEmail(String ownerEmail) {
		if (ownerEmail
				.matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
			return true;
		}
		return false;
	}

	/**
	 * @param userName
	 * @param password
	 */
	public void validateUserNameAndPassword(String userName, String password) {
		
		if (userName == null || userName.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if (password == null || password.isEmpty()) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidPassword);
			se.setDisplayErrMsg(true);
			throw se;
		
	}
	}
	/**
	 * @param netPos
	 */
	public void validateNetposAccount(NetPosDTO netPos)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param filterDTO
	 * @return
	 */
	public ErrorDTO validateNetPosFilter(FilterDTO filterDTO) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filterDTO.getExp()) {
			Property property = null;
			try {
				property = NetPosPropertyEnum.valueOf(exp.getName());
			} catch (IllegalArgumentException e) {
				error = getInvalidExpNameError(exp);
				return error;
			}
			error = validateExp(exp, property);
			if (error != null) {
				return error;
			}
		}
		return null;
	}

}
