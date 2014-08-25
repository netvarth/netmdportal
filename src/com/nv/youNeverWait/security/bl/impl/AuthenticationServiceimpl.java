/**
 * AuthenticationServiceimpl.java
 *
 * Dec 3, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.security.bl.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.framework.sendmsg.SendEmailMsgWorkerThread;
import com.nv.framework.sendmsg.SendMsgCallbackEnum;
import com.nv.framework.sendmsg.email.SendMailMsgObj;
import com.nv.framework.util.text.StringEncoder;
import com.nv.security.youNeverWait.MailSendAdapter;
import com.nv.youNeverWait.rs.dto.ErrorCodeListResponseDTO;
import com.nv.youNeverWait.rs.dto.EnumDTO;
import com.nv.youNeverWait.rs.dto.EnumListResponseDTO;
import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.rs.dto.CaptchaResponseDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationDTO;
import com.nv.youNeverWait.rs.dto.CaptchaVerificationResponseDTO;
import com.nv.youNeverWait.rs.dto.CreatePasswordDTO;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LoginDTO;
import com.nv.youNeverWait.rs.dto.LoginResponseDTO;
import com.nv.youNeverWait.rs.dto.PasswordDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserCredentials;
import com.nv.youNeverWait.rs.dto.UserDetails;
import com.nv.youNeverWait.security.bl.service.AuthenticationService;
import com.nv.youNeverWait.security.pl.dao.AuthenticationDao;
import com.nv.youNeverWait.security.validation.AuthenticationValidator;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

public class AuthenticationServiceimpl implements AuthenticationService {

	private AuthenticationValidator validator;
	private AuthenticationDao authenticationDao;
	private List<Class<Enum>> enumList;
	private MailSendAdapter mailSendAdapter;
	private static final Log log = LogFactory.getLog(AuthenticationServiceimpl.class);

	
	/**
	 * Retrieves list of error messages
	 * 
	 * @return ErrorCodeListResponseDTO
	 */
	public ErrorCodeListResponseDTO getErrorCodes() {

		ErrorCodeListResponseDTO errorCode = authenticationDao.getErrorCodes();
		return errorCode;
	}

	/**
	 * Retrieves list of Enumerators
	 * 
	 * @return EnumListResponseDTO
	 */
	@Override
	public EnumListResponseDTO getEnumsList() {

		EnumListResponseDTO response = new EnumListResponseDTO();
		List<EnumDTO> enumDTOList = new ArrayList<EnumDTO>();
		for (Class<Enum> clazz : enumList) {

			EnumDTO enumDTO = new EnumDTO();
			List<String> enumValues = getEnumValues(clazz);
			enumDTO.setEnumValues(enumValues);
			enumDTO.setKey(clazz.getSimpleName());
			enumDTOList.add(enumDTO);
		}
		response.setEnumListDTO(enumDTOList);
		return response;
	}

	/**
	 * Method to get Enum values
	 * 
	 * @param elemType
	 */
	public <E extends Enum<E>> List<String> getEnumValues(Class<E> elemType) {

		List<String> enumValList = new ArrayList<String>();
		for (E e : java.util.EnumSet.allOf(elemType)) {
			enumValList.add(((EnumDisplay) e).getDisplayName());
		}
		return enumValList;
	}

	/**
	 * Method performed for NetLims login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional
	public LoginResponseDTO netlimsLogin(LoginDTO login) {

		LoginResponseDTO response = new LoginResponseDTO();
		ErrorDTO error = validator.validateLogin(login);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		String userName = login.getUserName().trim();
		login.setUserName(userName);
		System.out.println(login.getPassword());
		String encPassword = StringEncoder.encryptWithKey(login.getPassword().trim());
		login.setPassword(encPassword);
		response = authenticationDao.netlimsLogin(login);

		return response;
	}

	/**
	 * Method performed for NetMd login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional
	public LoginResponseDTO netmdLogin(LoginDTO login) {

		LoginResponseDTO response = new LoginResponseDTO();
		ErrorDTO error = validator.validateLogin(login);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		String userName = login.getUserName().trim();
		login.setUserName(userName);
		String encPassword = StringEncoder.encryptWithKey(login.getPassword().trim());
		login.setPassword(encPassword);
		response = authenticationDao.netmdLogin(login);

		return response;
	}

	
	@Override
	public ResponseDTO createPassword(CreatePasswordDTO passwords) {
		validator.validatePasswordsForCreatePassword(passwords);
		ResponseDTO response = authenticationDao.createPassword(passwords);
		return response;
	}

	
	/**
	 * Method performed for NetMd login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional
	public LoginResponseDTO netrxLogin(LoginDTO login) {

		LoginResponseDTO response = new LoginResponseDTO();
		ErrorDTO error = validator.validateLogin(login);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		String userName = login.getUserName().trim();
		login.setUserName(userName);
		String encPassword = StringEncoder.encryptWithKey(login.getPassword().trim());
		login.setPassword(encPassword);
		response = authenticationDao.netrxLogin(login);

		return response;
	}

	/**
	 * Method performed for patient login
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 */
	@Override
	@Transactional
	public LoginResponseDTO patientLogin(LoginDTO login) {

		LoginResponseDTO response = new LoginResponseDTO();
		ErrorDTO error = validator.validateLogin(login);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		String userName = login.getUserName().trim();
		login.setUserName(userName);
		String encPassword = StringEncoder.encryptWithKey(login.getPassword().trim());
		login.setPassword(encPassword);
		System.out.println("Password" + encPassword);
		response = authenticationDao.patientLogin(login);

		return response;
	}
	
	/**
	 * Method performed when password forgotten
	 * 
	 * @param login
	 * @return ResponseDTO
	 */

	@Override
	public ResponseDTO forgotPassword(LoginDTO login) {
		ResponseDTO response = new ResponseDTO();
		if (login.getUserName() == null || login.getUserName().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidUserName);
			se.setDisplayErrMsg(true);
			throw se;
		}
		UserCredentials user = authenticationDao.getUserCredentials(login);
		if (user.getEmailId() == null || user.getEmailId().equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidMailId);
			se.setDisplayErrMsg(true);
			throw se;
		}

		mailSendAdapter.sendEmailForResetPassword(Constants.RESET_PASSWORD, user);
		response.setSuccess(true);
		return response;

	}

	
	
	
	/**
	 * Method to reset password
	 * 
	 * @param login
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO resetPassword(LoginDTO login) {
		validator.validateUserNameAndPassword(login.getUserName(),
				login.getPassword());
		ResponseDTO response = authenticationDao.resetPassword(login);
		return response;

	}

	
	/**
	 * Method which performs password changing
	 * 
	 * @param passwords
	 * @return ResponseDTO
	 */
	@Override
	public ResponseDTO changePassword(PasswordDTO passwords) {
		validator.validatePasswords(passwords);
		return authenticationDao.changePassword(passwords);
	}


	/**
	 * Method performed to get Netlims user details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getNetlimsUser(String userName) {

		UserDetails user = null;
		if (userName == null || userName.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		user = authenticationDao.getNetlimsUser(userName);
		return user;
	}

	/**
	 * Method performed to get Netmd user details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getNetmdUser(String userName) {

		UserDetails user = null;
		if (userName == null || userName.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		user = authenticationDao.getNetmdUser(userName);
		return user;
	}
	/**
	 * Method performed to get Netrx user details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getNetrxUser(String userName) {

		UserDetails user = null;
		if (userName == null || userName.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		user = authenticationDao.getNetrxUser(userName);
		return user;
	}
	

	/**
	 * Method performed to get captcha
	 * 
	 * @return CaptchaResponseDTO
	 */
	@Override
	@Transactional
	public CaptchaResponseDTO getCaptcha() {

		String secretCode = encryptString();
		System.out.println("encrypted secret code"+secretCode);
		byte[] image = null;
		try {
			image = drawImage(secretCode);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error while getting captcha", e);
			ServiceException se = new ServiceException(
					ErrorCodeEnum.ImageCreationFailed);
			se.setDisplayErrMsg(true);
			throw se;
		}
		CaptchaResponseDTO response = new CaptchaResponseDTO();
		response.setSecretCode(secretCode);
		response.setImage(image);
		response.setSuccess(true);
		return response;
	}

	/**
	 * Method to draw Captcha image
	 * 
	 * @param secretCode
	 * @throws IOException
	 */
	private byte[] drawImage(String secretCode) throws IOException {

		BufferedImage image = new BufferedImage(115, 26,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		// GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.white,
		// true);
		//Color c = new Color(0.6662f, 0.4569f, 0.3232f);
		GradientPaint gp = new GradientPaint(0, 0, Color.red, 0, 26 / 2,
				Color.white, true);
		graphics2D.setPaint(gp);
		Font font = new Font("Bradley Hand ITC", Font.CENTER_BASELINE, 26);
		graphics2D.setFont(font);

		// Securitykey is a byte array which has been generated once and only
		// once (ie.not dynamic)using Keygenerator in the StringEncoder class.
		graphics2D.drawString(
				new StringEncoder().decryptWithStaticKey(secretCode), 2, 20);
		graphics2D.dispose();
		// Create a byte array output stream.
		ByteArrayOutputStream bao = new ByteArrayOutputStream();

		// Write to output stream
		ImageIO.write(image, "jpg", bao);
		// ImageIO.write(image, "jpeg", outputStream);
		byte[] b = bao.toByteArray();
		bao.close();
		return b;
	}

	/**
	 * Creates random string.
	 */
	private String encryptString() {

		Random randomString = new Random();
		String token = Long.toString(Math.abs(randomString.nextLong()), 36);
		String enc = StringEncoder.encryptWithStaticKey(token.substring(
				0, 6));
		return enc;
	}

	/**
	 * Method which verify captcha
	 * 
	 * @return CaptchaResponseDTO
	 */
	@Override
	@Transactional
	public CaptchaVerificationResponseDTO verifyCaptcha(
			CaptchaVerificationDTO captcha) {

		CaptchaVerificationResponseDTO response = new CaptchaVerificationResponseDTO();
		validator.validateCaptcha(captcha);
		String dec = StringEncoder.decryptWithStaticKey(captcha
				.getSecretCode());
		if (!dec.equals(captcha.getVerificationCode())) {
			response.setValid(false);
		} else {
			response.setValid(true);
		}
		response.setSuccess(true);
		return response;

	}

	/**
	 * Method performed to get patient details
	 * 
	 * @param userName
	 * @return UserDetails
	 */
	@Override
	@Transactional
	public UserDetails getPatient(String userName) {

		UserDetails user = new UserDetails();
		validator.validateNetlimsUser(userName);
		user = authenticationDao.getPatient(userName);
		return user;

	}


	

	/**
	 * @return the validator
	 */
	public AuthenticationValidator getValidator() {
		return validator;
	}

	/**
	 * @param validator
	 *            the validator to set
	 */
	public void setValidator(AuthenticationValidator validator) {
		this.validator = validator;
	}

	/**
	 * @return the authenticationDao
	 */
	public AuthenticationDao getAuthenticationDao() {
		return authenticationDao;
	}

	/**
	 * @param authenticationDao
	 *            the authenticationDao to set
	 */
	public void setAuthenticationDao(AuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}

	/**
	 * @return the enumList
	 */
	public List<Class<Enum>> getEnumList() {
		return enumList;
	}

	/**
	 * @param enumList
	 *            the enumList to set
	 */
	public void setEnumList(List<Class<Enum>> enumList) {
		this.enumList = enumList;
	}

	/**
	 * @param mailSendAdapter
	 */
	public void setMailSendAdapter(MailSendAdapter mailSendAdapter) {
		this.mailSendAdapter = mailSendAdapter;
	}

	@Override
	public UserDetails getFacilityUserInfo(String userName, String userType) {
		UserDetails user = null;
		if (userName == null || userName.equals("")) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.UserNameNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		user = authenticationDao.getNetlimsFacilityUser(userName, userType);
		return user;
	}

	

}
