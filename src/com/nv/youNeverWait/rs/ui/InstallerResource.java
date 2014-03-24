/**
 * 
 */
package com.nv.youNeverWait.rs.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nv.youNeverWait.exception.ServiceException;

import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.InstallerListDTO;

import com.nv.youNeverWait.rs.dto.Parameter;

import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.InstallerService;

/**
 * @author net varth
 *
 */
@Controller
@RequestMapping("ui/installer/")
public class InstallerResource {
	private InstallerService installerService;

	
	@RequestMapping(value = "uploadNewVersion", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO uploadNewVersion(HttpServletRequest request){
		
 		ResponseDTO response = new ResponseDTO();
		try{
			response=installerService.uploadNewVersion(request);
			
		}
		catch(ServiceException e){
			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setSuccess(false);
		}
		return response;
	}

	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public InstallerListDTO listUploadedVersions(@RequestBody FilterDTO filter){
		
		InstallerListDTO response = new InstallerListDTO();
		try{
			response=installerService.list(filter);
			
		}
		catch(ServiceException e){
			List<Parameter> parameters =e.getParamList();
			ErrorDTO error=new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
 		return response;
	}
	
	/**
	 * @return the installerService
	 */
	public InstallerService getInstallerService() {
		return installerService;
	}

	/**
	 * @param installerService the installerService to set
	 */
	public void setInstallerService(InstallerService installerService) {
		this.installerService = installerService;
	}




	
}
