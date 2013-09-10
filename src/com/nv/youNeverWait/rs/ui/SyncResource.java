/**
 * SyncResource.java
 * 
 * @Author Luciya Jose
 *
 * April 05, 2013
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
import com.nv.youNeverWait.rs.dto.LabSyncDTO;
import com.nv.youNeverWait.rs.dto.LabSyncResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.SyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponseDTO;
import com.nv.youNeverWait.user.bl.service.SyncService;

/**
 * @author Luciya Jose
 * 
 */
@Controller
@RequestMapping("ui/sync/")
public class SyncResource {
	private SyncService service;

	/**
	 * Method which performs synchronization  for NetMd
	 * 
	 * @param sync
	 * @return SyncResponseDTO
	 */
	@RequestMapping(value = "syncData", method = RequestMethod.POST)
	@ResponseBody
	public SyncResponseDTO syncData(@RequestBody SyncDTO sync) {

		SyncResponseDTO response = new SyncResponseDTO();
		try {
			response = service.syncData(sync);
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
