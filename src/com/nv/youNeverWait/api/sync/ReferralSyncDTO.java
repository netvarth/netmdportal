package com.nv.youNeverWait.api.sync;

import com.nv.youNeverWait.user.bl.service.ActionEnum;

public class ReferralSyncDTO {
	private ReferralInfo referralInfo;
	private Integer globalId;
	private ActionEnum actionName;
	public ReferralInfo getReferralInfo() {
		return referralInfo;
	}
	public void setReferralInfo(ReferralInfo referralInfo) {
		this.referralInfo = referralInfo;
	}
	public Integer getGlobalId() {
		return globalId;
	}
	public void setGlobalId(Integer globalId) {
		this.globalId = globalId;
	}
	public ActionEnum getActionName() {
		return actionName;
	}
	public void setActionName(ActionEnum actionName) {
		this.actionName = actionName;
	}
}
