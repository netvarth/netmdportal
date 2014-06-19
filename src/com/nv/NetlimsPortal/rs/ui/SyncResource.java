/**
 * SyncResource.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
 */
package com.nv.NetlimsPortal.rs.ui;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nv.NetlimsPortal.bl.service.LogService;
import com.nv.NetlimsPortal.bl.service.SyncService;
import com.nv.NetlimsPortal.exception.ServiceException;
import com.nv.NetlimsPortal.rs.dto.ErrorDTO;
import com.nv.NetlimsPortal.rs.dto.LabSyncDTO;
import com.nv.NetlimsPortal.rs.dto.LabSyncResponseDTO;
import com.nv.NetlimsPortal.rs.dto.Parameter;


/**
 * @author Luciya Jose
 * 
 */
@Controller
@RequestMapping("ui/sync/")
public class SyncResource {
	private SyncService service;


	
	
	/**
	 * Method which performs synchronization  for NetLims
	 * 
	 * @param sync
	 * @return SyncResponseDTO
	 */
	@RequestMapping(value = "syncNetLimsData", method = RequestMethod.POST)
	@ResponseBody
	public LabSyncResponseDTO syncNetLimsData(@RequestBody LabSyncDTO sync) {

		LabSyncResponseDTO response = new LabSyncResponseDTO();
		try {
			response = service.syncNetLimsData(sync);
		} catch (ServiceException e) {

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
	
	

	/**
	 * @return the service
	 */
	public SyncService getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	public void setService(SyncService service) {
		this.service = service;
	}

}
