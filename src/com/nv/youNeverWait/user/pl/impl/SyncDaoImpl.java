/**
 * SyncDaoImpl.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.youNeverWait.user.pl.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetmdPassphraseTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.HeaderResponseDTO;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.user.pl.dao.DoctorDao;
import com.nv.youNeverWait.user.pl.dao.PatientDao;
import com.nv.youNeverWait.user.pl.dao.SyncDao;

/**
 * @author Luciya Jose
 * 
 */
public class SyncDaoImpl extends GenericDaoHibernateImpl implements SyncDao{
	@PersistenceContext()
	private EntityManager em;
	private DoctorDao doctorDao;
	private PatientDao patientDao;

	
	/**
	 * Method to get header status
	 * @param header
	 * @return response
	 */
	@Override
	@Transactional
	public HeaderResponseDTO getHeaderStatus(HeaderDTO header) {
		HeaderResponseDTO response = new HeaderResponseDTO();
		// Validate header details
		if (header.getMacId() != null && header.getPassPhrase() != null
				&& !header.getMacId().isEmpty()
				&& !header.getPassPhrase().isEmpty()) {

			NetmdPassphraseTbl passPhrase = getByPassphrase(header
					.getPassPhrase());
			if (passPhrase == null
					|| passPhrase.getMacId() == null
					|| !passPhrase.getMacId().equals(header.getMacId())
					|| passPhrase.getNetmdBranchTbl().getId() != header
							.getBranchId()
					|| passPhrase.getNetmdBranchTbl().getNetmdTbl().getId() != header
							.getHeadOfficeId()) {

				ServiceException se = new ServiceException(ErrorCodeEnum.InvalidNetMdAccount);
				se.setDisplayErrMsg(true);
				throw se;
			}
			if (passPhrase.isPrimaryDevice()) {
				response.setPrimaryDevice(true);
				response.setSuccess(true);
			} else {
				response.setPrimaryDevice(false);
				response.setSuccess(true);
			}
		}

		return response;
	}

	/**
	 * Retrieve NetmdPassphraseTbl record by giving passPhrase
	 * 
	 * @param passPhrase
	 * @return NetmdPassphraseTbl
	 */
	private NetmdPassphraseTbl getByPassphrase(String passPhrase) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_NETMD_BRANCH_PASSPHRASE);
		query.setParameter("param1", passPhrase);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}

	/**
	 * @return the patientDao
	 */
	public PatientDao getPatientDao() {
		return patientDao;
	}

	/**
	 * @param patientDao
	 *            the patientDao to set
	 */
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	/**
	 * @return the doctorDao
	 */
	public DoctorDao getDoctorDao() {
		return doctorDao;
	}

	/**
	 * @param doctorDao
	 *            the doctorDao to set
	 */
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
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
