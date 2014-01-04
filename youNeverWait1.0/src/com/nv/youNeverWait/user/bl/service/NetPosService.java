/**
 * NetPosService.java
 * Jithinraj
 * Nov 26, 2013
 */
package com.nv.youNeverWait.user.bl.service;

import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.rs.dto.NetPosListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetPosViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;

/**
 * @author Mani
 *
 */
public interface NetPosService {


	public ResponseDTO createNetPos(NetPosDTO netPos);
	public ResponseDTO updateNetPos(NetPosDTO netPos);
	public ResponseDTO deleteNetPos(int netPosId);
	public NetPosViewResponseDTO viewNetPos(int netPosId);
	public NetPosListResponseDTO list(FilterDTO filter);
	
	

}
