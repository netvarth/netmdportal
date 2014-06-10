/**
 * 
 */
package com.nv.youNeverWait.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.VersionAvailabilityResponse;
import com.nv.youNeverWait.rs.dto.VersionDetail;
import com.nv.youNeverWait.version.bl.service.VersionService;



/**
 * @author Luciya
 * June 04 2014 Wednesday
 */
@Controller
@RequestMapping("ui/version/")

public class VersionResource {
	private VersionService service;
	
	@RequestMapping(value = "checkUpdates", method = RequestMethod.POST)
	@ResponseBody
	public VersionAvailabilityResponse checkUpdates( @RequestBody VersionDetail details) {
		VersionAvailabilityResponse response = new VersionAvailabilityResponse();
		try{
			response=service.checkUpdates(details);
		}catch(ServiceException e){
			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());
			response.setError(error);
			response.setSuccess(false);
		}
		return response;
	}
	public VersionService getService() {
		return service;
	}
	public void setService(VersionService service) {
		this.service = service;
	}

}
