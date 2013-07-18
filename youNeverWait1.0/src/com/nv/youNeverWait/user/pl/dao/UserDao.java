/**
 * 
 */
package com.nv.youNeverWait.user.pl.dao;

import com.nv.youNeverWait.rs.dto.HeaderDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.rs.dto.UserInfoDetail;

/**
 * @author Luciya Jose
 *
 */
public interface UserDao {
	public ResponseDTO createNetMdUser(UserInfoDetail user,HeaderDTO header);
	public ResponseDTO updateNetMdUser(UserInfoDetail user, HeaderDTO header);
	public ResponseDTO deleteUser(int localId,int globalId, HeaderDTO header);
		
}
