/**
 * NetPosManager.java
 * Jithinraj
 * Nov 26, 2013
 */
package com.nv.youNeverWait.user.bl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import com.nv.youNeverWait.common.Constants;
import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.pl.entity.NetposTbl;
import com.nv.youNeverWait.rs.dto.ErrorDTO;
import com.nv.youNeverWait.rs.dto.ExpressionDTO;
import com.nv.youNeverWait.rs.dto.FilterDTO;
import com.nv.youNeverWait.rs.dto.NetPosDTO;
import com.nv.youNeverWait.rs.dto.NetPosDetail;
import com.nv.youNeverWait.rs.dto.NetPosListResponseDTO;
import com.nv.youNeverWait.rs.dto.NetPosViewResponseDTO;
import com.nv.youNeverWait.rs.dto.ResponseDTO;
import com.nv.youNeverWait.user.bl.service.NetPosService;
import com.nv.youNeverWait.user.bl.validation.NetPosValidator;
import com.nv.youNeverWait.user.pl.dao.NetPosDao;
import com.nv.youNeverWait.util.filter.core.Filter;
import com.nv.youNeverWait.util.filter.core.FilterFactory;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;
import com.nv.youNeverWait.util.filter.core.QueryBuilderFactory;



/**
 * @author Mani
 *
 */
public class NetPosManager implements NetPosService{
	
	private NetPosValidator netPosValidator;
	private NetPosDao netPosDao;
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#createNetPos(com.nv.youNeverWait.rs.dto.NetPosDTO)
	 */
	@Override
	public ResponseDTO createNetPos(NetPosDTO netPos) {
	
		netPosValidator.validateNetPosAccount(netPos);
		netPosValidator.validateUserNameAndPassword(netPos.getUserName(),
				netPos.getPassword());
		ResponseDTO response = netPosDao.create(netPos);
		//sendEmailToNetPosOwner(Constants.NETMD_REGISTER, netPos);
		return response;
	}


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#updateNetPos(com.nv.youNeverWait.rs.dto.NetPosDTO)
	 */
	@Override
	public ResponseDTO updateNetPos(NetPosDTO netPos) {
		netPosValidator.validateNetposAccount(netPos);
		ResponseDTO response = netPosDao.update(netPos);
		return response;
	}


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#deleteNetPos(int)
	 */
	@Override
	public ResponseDTO deleteNetPos(int netPosId) {
		

	 ResponseDTO response = netPosDao.delete(netPosId);
		return response;

	}


	


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#viewNetPos(int)
	 */
	@Override
	public NetPosViewResponseDTO viewNetPos(int netPosId) {
		if (netPosId <= 0) {
			ServiceException se = new ServiceException(
					ErrorCodeEnum.InvalidNetPosId);
			se.setDisplayErrMsg(true);
			throw se;
		}
		NetPosViewResponseDTO response = netPosDao.view(netPosId);
		return response;
	}


	public NetPosValidator getNetPosValidator() {
		return netPosValidator;
	}


	public void setNetPosValidator(NetPosValidator netPosValidator) {
		this.netPosValidator = netPosValidator;
	}


	public NetPosDao getNetPosDao() {
		return netPosDao;
	}


	public void setNetPosDao(NetPosDao netPosDao) {
		this.netPosDao = netPosDao;
	}


	/* (non-Javadoc)
	 * @see com.nv.youNeverWait.user.bl.service.NetPosService#list(com.nv.youNeverWait.rs.dto.FilterDTO)
	 */
	@Override
	public NetPosListResponseDTO list(FilterDTO filterDTO) {
		
		NetPosListResponseDTO response = new NetPosListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,return result with appropriate error code
		ErrorDTO error = netPosValidator.validateNetPosFilter(filterDTO);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}

		// get queryBuilder for netpos branch from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.NETPOS);
		if (queryBuilder == null) {
			return response;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<NetposTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());

		// get count
		Long count = queryBuilder.getCount();
System.out.println("count="+count);
		// execute query
		List<NetposTbl> netposList = queryBuilder.executeQuery(q);
		response = getNetPosList(netposList);
		response.setCount(count);
		
		response.setSuccess(true);
		return response;
	}


	/**
	 * @param netposList
	 * @return
	 */
	private NetPosListResponseDTO getNetPosList(List<NetposTbl> netposList) {
		NetPosListResponseDTO response = new NetPosListResponseDTO();
		if (netposList.isEmpty()) {
			return response;
		}
		List<NetPosDetail> netPosDetails = new ArrayList<NetPosDetail>();
		for (NetposTbl netposTbl : netposList) {
			netPosDetails.add(new NetPosDetail(netposTbl));
		}
		response.setNetPos(netPosDetails);
		return response;
	}


	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}


	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}


	public FilterFactory getFilterFactory() {
		return filterFactory;
	}


	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}



}










