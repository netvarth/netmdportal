/**
 * NetPosDao.java
 * Jithinraj
 * Nov 26, 2013
 */
package com.nv.youNeverWait.user.pl.dao;


import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.rs.dto.NetPosViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Mani
 *
 */
public interface NetPosDao {
	public ResponseDTO  create(NetPosDTO netPos);
	public ResponseDTO update(NetPosDTO netPos);
	public ResponseDTO delete(int netPosId);
	public NetPosViewResponseDTO view(int netPosId);

}
