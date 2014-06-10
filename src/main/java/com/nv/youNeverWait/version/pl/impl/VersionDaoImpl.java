/**
 * @author Luciya
 * June 04 2014 Wednesday
 */
package com.nv.youNeverWait.version.pl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.UploadInstallerTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.VersionAvailabilityResponse;
import com.nv.youNeverWait.rs.dto.VersionDetail;
import com.nv.youNeverWait.security.pl.Query;
import com.nv.youNeverWait.version.pl.dao.VersionDao;

/**
 * @author Luciya
 *  June 04, 2014 Wednesday
 */
public class VersionDaoImpl extends GenericDaoHibernateImpl implements VersionDao{
	@PersistenceContext()
	private EntityManager em;
	
	@Override
	@Transactional
	public VersionAvailabilityResponse checkUpdates(VersionDetail details) {
		VersionAvailabilityResponse response = new VersionAvailabilityResponse();
		int count=0;
		// executing query for getting latest version
		List<UploadInstallerTbl> uploadedVersionList = getUploadedVersions(details.getAppName(), details.getSourceType());
		for(UploadInstallerTbl uploadedVersions:uploadedVersionList){
			count++;
			//checking whether current version number is same as the latest version number
			//If yes then set a display message and return back
			if(count==1 && details.getVersionNumber().equals(uploadedVersions.getVersionNo())){
				response.setSuccess(true);
				response.setDisplayMsg(Constants.NO_UPDATES);
				break;
				
			}
			// checking whether the current version is compatible with latest version
			String[] compatibleVersions = uploadedVersions.getVersionCompatible().split(",");
			for (String s: compatibleVersions){
						
				// If version is compatible with the latest version
				if(s.equals(details.getVersionNumber())){
					response.setAvailableVersion(uploadedVersions.getVersionNo());
					response.setSuccess(true);
					break;   // break out from inner loop	
				}	
				
			}           // end of for loop
			if(response.isSuccess())
			{
				break;  // break out from outer loop
			}
		}           // end of outer for loop
		
		return response;
	}

	private List<UploadInstallerTbl> getUploadedVersions(String appName,String sourceType) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPLOADED_VERSIONS);
		query.setParameter("param1", appName);
		query.setParameter("param2", sourceType);
		return executeQuery(UploadInstallerTbl.class, query);
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
