/**
 * UserStatusEnum.java
 */
package com.nv.youNeverWait.pl.entity;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.user.bl.impl.EnumDisplay;

/**
 * @author Luciya Jose
 *
 */
public enum UserStatusEnum implements EnumDisplay{
		Active("active"),InActive("inactive"),Suspend("suspend");

		private String displayName;

		/**
		 * @return the displayName
		 */
		public String getDisplayName() {
			return displayName;
		}

		/**
		 * @param displayName the displayName to set
		 */
		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}
		/**
		 * @param displayName
		 */
		private UserStatusEnum(String displayName) {
			this.displayName = displayName;
		}

		public static UserStatusEnum getEnum(String value) {
			if(value == null){
				ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
				se.setDisplayErrMsg(true);
				throw se;
			}
			for(UserStatusEnum v : values()){        	
				if(value.equalsIgnoreCase(v.getDisplayName())){
					return v;}}
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidUserType);
			se.setDisplayErrMsg(true);
			throw se;
		}


}
