package com.nv.portal.rs.netmd;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ApplicationNameEnum;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.LabSyncDTO;
import com.nv.youNeverWait.rs.dto.LabSyncResponseDTO;
import com.nv.youNeverWait.rs.dto.Parameter;
import com.nv.youNeverWait.rs.dto.SyncDTO;
import com.nv.youNeverWait.rs.dto.SyncResponseDTO;
import com.nv.youNeverWait.user.bl.service.LogService;
import com.nv.youNeverWait.user.bl.service.SyncService;

@Controller
@RequestMapping("ui/sync/")

public class SyncResource {
	private SyncService service;
	private LogService logService;

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
//		ServletRequestAttributes t = (ServletRequestAttributes) RequestContextHolder
//				.currentRequestAttributes();
//		HttpServletRequest request = t.getRequest();
//		logService.saveSyncDetails(request.getRemoteAddr(),ApplicationNameEnum.NetMd.getDisplayName(), sync.getLastSyncTime(),
//				sync.getHeader().getBranchId(),sync.getHeader().getHeadOfficeId());
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
