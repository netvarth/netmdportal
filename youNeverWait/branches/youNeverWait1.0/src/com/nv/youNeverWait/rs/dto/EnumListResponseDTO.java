/**
 * 
 */
package com.nv.youNeverWait.rs.dto;

import java.util.ArrayList;
import java.util.List;



/**
 * @author netvarth
 *
 */
public class EnumListResponseDTO {
	private List<EnumDTO> enumListDTO= new ArrayList<EnumDTO>();

	/**
	 * @return the enumListDTO
	 */
	public List<EnumDTO> getEnumListDTO() {
		return enumListDTO;
	}

	/**
	 * @param enumListDTO the enumListDTO to set
	 */
	public void setEnumListDTO(List<EnumDTO> enumListDTO) {
		this.enumListDTO = enumListDTO;
	}
	
	

}
