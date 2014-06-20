/**
 * 
 */
package com.nv.youNeverWait.user.pl.impl;

import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.nv.youNeverWait.pl.entity.UploadInstallerTbl;
import com.nv.youNeverWait.pl.impl.GenericDaoHibernateImpl;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.pl.dao.InstallerDao;

/**
 * @author net varth
 *
 */
public class InstallerDaoImpl extends GenericDaoHibernateImpl implements InstallerDao{

	@Override
	@Transactional
	public ResponseDTO uploadNewVersion(HttpServletRequest request, String path) {
		ResponseDTO response=new ResponseDTO();
		String filecontent="";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFileTwo = multipartRequest.getFile("file[1]");
		String application=multipartRequest.getParameter("applicationName");
		String version=multipartRequest.getParameter("versionNo");
		try{
		InputStream filetwo=multipartFileTwo.getInputStream();
		
		  int content;
          while ((content = filetwo.read()) != -1) {
              // convert to char and display it
        	  filecontent += (char) content;
          }
		}
		catch(Exception e){
			
		}
		Float versionNo=Float.valueOf(version) ;
		UploadInstallerTbl uploaderTbl= new UploadInstallerTbl();
		uploaderTbl.setApplication_Name(application);
		uploaderTbl.setQuery(filecontent);
		uploaderTbl.setVersionNo(versionNo);
		Date currentTime=new Date();
		uploaderTbl.setCreatedDate(currentTime);
		uploaderTbl.setWarFilePath(path);
		save(uploaderTbl);
		response.setId(uploaderTbl.getId());
		response.setSuccess(true);
		response.setError(null);
		
		return response;
	}



}
