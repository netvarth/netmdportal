package com.nv.portal.rs.netlims;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nv.youNeverWait.api.sync.LimsReferralBundle;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.rs.dto.CommonSyncResponse;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LabSyncDTO;
import com.nv.youNeverWait.rs.dto.LabSyncResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.SyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponse;
import com.nv.youNeverWait.rs.dto.SyncResponseDTO;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.SyncService;

@Controller
@RequestMapping("ui/sync/")

public class SyncResource {

	private SyncService service;
	private LogService logService;
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
//		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
//				.currentRequestAttributes();
//		HttpServletRequest request = t.getRequest();
//		logService.saveSyncDetails(request.getRemoteAddr(),ApplicationNameEnum.NetLims.getDisplayName(), sync.getLastSyncTime(),
//				sync.getHeader().getBranchId(),sync.getHeader().getHeadOfficeId());
		return response;
	}
	
	
public CommonSyncResponse processReferral(LimsReferralBundle bundle){
		
		List<SyncResponse> response=service.processReferral(bundle);
		CommonSyncResponse responses=new CommonSyncResponse();
		responses.setResponses(response);
		return responses;
		
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
