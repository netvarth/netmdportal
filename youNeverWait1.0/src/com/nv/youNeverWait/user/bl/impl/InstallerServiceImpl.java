/**
 * 
 */
package com.nv.youNeverWait.user.bl.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.UploadInstallerTbl;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.InstallerDTO;
import com.nv.youNeverWait.rs.dto.InstallerListDTO;
import com.nv.youNeverWait.rs.dto.NetMdBranchDetail;
import com.nv.youNeverWait.rs.dto.NetMdBranchListResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.InstallerService;
import com.nv.youNeverWait.user.bl.validation.InstallerValidator;
import com.nv.youNeverWait.user.pl.dao.InstallerDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;

/**
 * @author net varth
 *
 */
public class InstallerServiceImpl implements InstallerService {
	private String applicationVersionPath;
    private InstallerDao installerDao;
    private InstallerValidator validator;
    private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;


	@Override
	public ResponseDTO uploadNewVersion(HttpServletRequest request) {
		ResponseDTO response=new ResponseDTO();
        String path="";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFileOne = multipartRequest.getFile("file[0]");	
		String fileoneOriginalName=multipartFileOne.getContentType();
		String application=multipartRequest.getParameter("applicationName");
		String version=multipartRequest.getParameter("versionNo");
		try{
		InputStream inputOne = multipartFileOne.getInputStream();
	
		if(fileoneOriginalName!="text"){
		File newfileone=new File(applicationVersionPath + application + "_"+version+".war");
		OutputStream outputOne = new FileOutputStream(newfileone);
		try {
		    IOUtils.copy(inputOne,outputOne);
		} finally {
		    IOUtils.closeQuietly(outputOne);
		    IOUtils.closeQuietly(inputOne);
		}
        path=newfileone.getAbsolutePath();
		System.out.println("Uploaded file successfully saved in " + newfileone.getAbsolutePath());
		}
		
		
		}catch(Exception e){
		}
		
         response=installerDao.uploadNewVersion(request,path);
		return response;
	}


	@Override
	public InstallerListDTO list(FilterDTO filterDTO) {
		InstallerListDTO response = new InstallerListDTO();
		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = validator.validateInstallerFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netmd branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.INSTALLER);
		if (queryBuilder == null) {
			return response;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<UploadInstallerTbl> q = queryBuilder.buildQuery(
				filterDTO.isAsc(), filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();

		// execute query
		List<UploadInstallerTbl> installers = queryBuilder.executeQuery(q);
		response = getInstallers(installers);
		response.setCount(count);
		response.setSuccess(true);
		return response;
		
	}
	
	/**
	 * To set response with details of installers
	 * 
	 * @param installers
	 * @return InstallerListDTO
	 */
	private InstallerListDTO getInstallers(
			List<UploadInstallerTbl> installers) {
		InstallerListDTO response = new InstallerListDTO();
		if (installers == null) {
			return response;
		}
		List<InstallerDTO> installerDetail = new ArrayList<InstallerDTO>();
		for (UploadInstallerTbl uploadInstaller : installers) {
			InstallerDTO installer=new InstallerDTO();
			installer.setId(uploadInstaller.getId());
			installer.setApplicationName(uploadInstaller.getApplication_Name());
			installer.setCreatedDate(uploadInstaller.getCreatedDate().toString());
			installer.setQuery(uploadInstaller.getQuery());
			installer.setWarFilePath(uploadInstaller.getWarFilePath());
			installer.setVersionNo(uploadInstaller.getVersionNo());
			installerDetail.add(installer);
		}
		response.setInstallers(installerDetail);
		return response;
	}

	/**
	 * @return the validator
	 */
	public InstallerValidator getValidator() {
		return validator;
	}


	/**
	 * @param validator the validator to set
	 */
	public void setValidator(InstallerValidator validator) {
		this.validator = validator;
	}


	/**
	 * @return the queryBuilderFactory
	 */
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}


	/**
	 * @param queryBuilderFactory the queryBuilderFactory to set
	 */
	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}


	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory() {
		return filterFactory;
	}


	/**
	 * @param filterFactory the filterFactory to set
	 */
	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}


	/**
	 * @return the installerDao
	 */
	public InstallerDao getInstallerDao() {
		return installerDao;
	}


	/**
	 * @param installerDao the installerDao to set
	 */
	public void setInstallerDao(InstallerDao installerDao) {
		this.installerDao = installerDao;
	}


	/**
	 * @return the applicationVersionPath
	 */
	public String getApplicationVersionPath() {
		return applicationVersionPath;
	}


	/**
	 * @param applicationVersionPath the applicationVersionPath to set
	 */
	public void setApplicationVersionPath(String applicationVersionPath) {
		this.applicationVersionPath = applicationVersionPath;
	}

}
