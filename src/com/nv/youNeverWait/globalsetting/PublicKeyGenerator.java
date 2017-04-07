package com.nv.youNeverWait.globalsetting;

import java.util.List;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.HealthCareOrganisationTbl;
import com.nv.youNeverWait.pl.entity.LabBranchTbl;
import com.nv.youNeverWait.pl.entity.NetmdBranchTbl;
import com.nv.youNeverWait.pl.entity.NetrxBranchTbl;
import com.nv.youNeverWait.rs.dto.HealthCareDetailDTO;
import com.nv.youNeverWait.rs.dto.PublicKeyDTO;
import com.nv.youNeverWait.user.pl.dao.LabDao;

public class PublicKeyGenerator {

	private LabDao labDao;

	public PublicKeyDTO keyGenerator(int branchId){
		PublicKeyDTO dto  = new PublicKeyDTO();
		HealthCareOrganisationTbl healthCareDetails = labDao.getBranchDetailsById(branchId);
		String publicKey = keyGenerate(healthCareDetails);
		if(publicKey != null)
			dto.setPublicKey(publicKey);
		else{
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidBranchId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return dto;
	}
	

	public String keyGenerate(HealthCareOrganisationTbl healthCareDetails){
			String publicKey = "";
			if(healthCareDetails instanceof NetmdBranchTbl){
				NetmdBranchTbl tbl = (NetmdBranchTbl)healthCareDetails;
				String branchAddress = tbl.getAddress();
				String branchName = tbl.getName();
				int id = tbl.getId();
				String[] bAdrs = branchAddress.split(",");
				if(bAdrs.length>1)
					branchAddress = bAdrs[0];
				publicKey = branchAddress+"_"+branchName+"_"+"Clinic";
				publicKey = publicKey.replaceAll("\\s+","");
				labDao.updatePublickey(publicKey, id);
				return publicKey;
			}
			if(healthCareDetails instanceof LabBranchTbl){
				LabBranchTbl tbl = (LabBranchTbl)healthCareDetails;
				String branchAddress = tbl.getAddress();
				String branchName = tbl.getName();
				int id = tbl.getId();
				String[] bAdrs = branchAddress.split(",");
				if(bAdrs.length>1){
					branchAddress = bAdrs[0];
				}
				publicKey = branchAddress+"_"+branchName+"_"+"Lab";
				publicKey = publicKey.replaceAll("\\s+","");
				labDao.updatePublickey(publicKey, id);
				return publicKey;
			}
//			if(healthCareDetails instanceof NetrxBranchTbl){
//				NetrxBranchTbl tbl = (NetrxBranchTbl)healthCareDetails;
//				String branchAddress = tbl.getAddress();
//				String branchName = tbl.getName();
//				int id = tbl.getId();
//				String[] bAdrs = branchAddress.split(",");
//				if(bAdrs.length>1){
//					branchAddress = bAdrs[0];
//				}
//				publicKey = branchAddress+"_"+branchName+"_"+"Pharma";
//				publicKey = publicKey.replaceAll("\\s+","");
//				labDao.updatePublickey(publicKey, id);
//				return publicKey;
//			}
			return null;
	}
	
	public HealthCareDetailDTO receiveBranchDetail(String publicKey){
		HealthCareDetailDTO dto = new HealthCareDetailDTO();
		List<HealthCareOrganisationTbl> list = labDao.getHealthCareDetail(publicKey);
		if(list.size()==0){
			ServiceException se = new ServiceException(ErrorCodeEnum.InvalidBranch);
			se.setDisplayErrMsg(true);
			throw se;
		}
		for(HealthCareOrganisationTbl tbl : list){
			dto.setBranchName(tbl.getName());
			//String name = tbl.getName();
			String id = "";
			if(tbl instanceof LabBranchTbl){
				Integer bId =  tbl.getId();
				id = bId.toString();
				dto.setBranchId(id);
				String adress = tbl.getAddress();
				dto.setAddress(adress);
				dto.setEmail(tbl.getEmail());
				dto.setPhoneNumber(tbl.getPhone());
				dto.setMobilNumber(tbl.getMobile());
				dto.setPublicKey(tbl.getPublickey());
			}else if(tbl instanceof NetmdBranchTbl){
				Integer bId = tbl.getId();
				id = bId.toString();
				dto.setBranchId(id);
				String adress = tbl.getAddress();
				dto.setAddress(adress);
				dto.setEmail(tbl.getEmail());
				dto.setPhoneNumber(tbl.getPhone());
				dto.setMobilNumber(tbl.getMobile());
				dto.setPublicKey(tbl.getPublickey());
			}else{
				Integer bId = tbl.getId();
				id = bId.toString();
				dto.setBranchId(id);
				String adress = tbl.getAddress();
				dto.setAddress(adress);
				dto.setEmail(tbl.getEmail());
				dto.setPhoneNumber(tbl.getPhone());
				dto.setMobilNumber(tbl.getMobile());
				dto.setPublicKey(tbl.getPublickey());
			}
			
		}
		return dto;
	}
	
	public LabDao getLabDao() {
		return labDao;
	}
	public void setLabDao(LabDao labDao) {
		this.labDao = labDao;
	}
}
