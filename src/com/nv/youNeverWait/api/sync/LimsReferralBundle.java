package com.nv.youNeverWait.api.sync;

import java.util.ArrayList;
import java.util.List;

public class LimsReferralBundle {

	private List<ReferralSyncDTO> referrals=new ArrayList<ReferralSyncDTO>();

	public List<ReferralSyncDTO> getReferrals() {
		return referrals;
	}

	public void setReferrals(List<ReferralSyncDTO> referrals) {
		this.referrals = referrals;
	}
}
