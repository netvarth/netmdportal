/**
 * RootQueryBuilder.java
 *
 * Sep 5, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.youNeverWait.util.filter.queryBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.nv.youNeverWait.exception.ServiceException;
import com.nv.youNeverWait.pl.dao.FilterDao;
import com.nv.youNeverWait.pl.entity.ErrorCodeEnum;
import com.nv.youNeverWait.util.filter.core.Expression;
import com.nv.youNeverWait.util.filter.core.Property;
import com.nv.youNeverWait.util.filter.core.QueryBuilder;


/**
 * @param <K>
 * 
 */
public abstract class RootQueryBuilder<K> implements QueryBuilder {

	private FilterDao  filterDao;
	protected Map<String,Join<K,?>> referenceMap = new HashMap<String,Join<K,?>>();
	protected Map<String,Path<Object>> pathReferenceMap = new HashMap<String,Path<Object>>();
	private CriteriaBuilder criteriaBuilder ;
	private CriteriaQuery<K> criteriaQuery;
	private Root<K> root;
	private CriteriaQuery<Long> criteriaCountQuery;
	private Root<K> countRoot;
	protected List<Predicate> predicates = new ArrayList<Predicate>();
	protected List<Expression> expressions = new ArrayList<Expression>();

	public RootQueryBuilder(){

	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */

	public void init(FilterDao filterDao,Class clazz) {
		this.filterDao= filterDao;
		this.criteriaBuilder = filterDao.getEM().getCriteriaBuilder();
		this.criteriaQuery = (CriteriaQuery<K>) this.criteriaBuilder.createQuery(clazz);
		this.root = (Root<K>) this.criteriaQuery.from(clazz);
		this.criteriaCountQuery = (CriteriaQuery<Long>) this.criteriaBuilder.createQuery(Long.class);
		this.countRoot = (Root<K>) this.criteriaCountQuery.from(clazz);
		this.criteriaQuery .distinct(true);
		System.out.println("init ends..");
	}

	/**
	 * Get count
	 */

	@Transactional
	public Long getCount(){
		System.out.println("in build countQuery");
		criteriaCountQuery.select(criteriaBuilder.countDistinct(countRoot));
		if(predicates.size()!=0){
			criteriaCountQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		}
		TypedQuery<Long> q = filterDao.getEM().createQuery(criteriaCountQuery);
		Long count=(long) 0;
		if(q!=null){
			for (Expression expression : expressions) {
				q.setParameter(expression.getName(), expression.getValue());
			}
			try{
				count = q.getSingleResult();
				System.out.println("count :"+count);
			}catch (ClassCastException e) {
				ServiceException se= new ServiceException(ErrorCodeEnum.DatabaseError);
				se.setDisplayErrMsg(true);
				throw se;
			}catch(RuntimeException e){
				ServiceException se = new ServiceException(ErrorCodeEnum.DatabaseError);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		return count;
	}
	/**
	 * Get count
	 */

	@Transactional
	public Long getSearchCount(){
		System.out.println("in build countQuery");
		criteriaCountQuery.select(criteriaBuilder.countDistinct(countRoot));
		if(predicates.size()!=0){
			criteriaCountQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
		}
		TypedQuery<Long> q = filterDao.getEM().createQuery(criteriaCountQuery);
		Long count=(long) 0;
		if(q!=null){
			for (Expression expression : expressions) {
				q.setParameter(expression.getName(), expression.getValue());
			}
			try{
				count = q.getSingleResult();
				System.out.println("count :"+count);
			}catch (ClassCastException e) {
				ServiceException se= new ServiceException(ErrorCodeEnum.DatabaseError);
				se.setDisplayErrMsg(true);
				throw se;
			}catch(RuntimeException e){
				ServiceException se = new ServiceException(ErrorCodeEnum.DatabaseError);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		return count;
	}
	/**
	 * Build query with all predicates then apply limit and orderBy clauses
	 * @param asc, start,end
	 *//*

	@Transactional
	public TypedQuery<K> buildQuery(boolean asc,int start,int end,String sortBy){
		System.out.println("in buildQuery");
		Property property = OrderSortByEnum.valueOf("agentName");
		if(predicates.size()!=0){
			criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		}
		if(asc){
			if(referenceMap.get("agentTbl")!=null){
				criteriaQuery.orderBy(criteriaBuilder.asc(
						referenceMap.get("agentTbl").get(property.getFieldName())
						));
			}else if(pathReferenceMap.get(property.getPathReferenceName())!=null){
				criteriaQuery.orderBy(criteriaBuilder.asc(
						pathReferenceMap.get(property.getPathReferenceName()).get(property.getFieldName())
						));
			}else{
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(property.getFieldName())));
			}
		}else{
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortBy)));
		}

		TypedQuery<K> q = filterDao.getEM().createQuery(criteriaQuery);
		if(q!=null){
			for (Expression expression : expressions) {
				q.setParameter(expression.getName(), expression.getValue());
			}
			q.setFirstResult(start);
			q.setMaxResults(end);	
		}
		return q;
	}*/
	/**
	 * Build query with all predicates then apply limit and orderBy clauses
	 * @param asc, start,end
	 */

	@Transactional
	public TypedQuery<K> buildQuery(boolean asc,int start,int end){
		System.out.println("in buildQuery");
		if(predicates.size()!=0){
			criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		}
		if(asc){
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		}else{
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		}

		TypedQuery<K> q = filterDao.getEM().createQuery(criteriaQuery);
		if(q!=null){
			for (Expression expression : expressions) {
				q.setParameter(expression.getName(), expression.getValue());
			}
			q.setFirstResult(start);
			q.setMaxResults(end);	
		}
		return q;
	}
	@Transactional
	public TypedQuery<K> buildSearchQuery(boolean asc,int start,int end){
		System.out.println("in buildQuery");
		if(predicates.size()!=0){
			criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
		}
		if(asc){
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		}else{
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		}

		TypedQuery<K> q = filterDao.getEM().createQuery(criteriaQuery);
		if(q!=null){
			for (Expression expression : expressions) {
				q.setParameter(expression.getName(), expression.getValue());
			}
			q.setFirstResult(start);
			q.setMaxResults(end);
		}

		return q;
	}

	/**
	 * Execute query and return result list
	 * @param TypedQuery<T>
	 * return List<T>
	 */

	@Transactional
	public <K> List<K> executeQuery(TypedQuery<K> q) {
		System.out.println("in executeQuery");
		List<K> list = new ArrayList<K>();
		if(q!=null){
			try{
				list = (List<K>) q.getResultList();
			}catch (ClassCastException e) {
				ServiceException se= new ServiceException(ErrorCodeEnum.DatabaseError);
				se.setDisplayErrMsg(true);
				throw se;
			}catch(RuntimeException e){
				e.printStackTrace();
				ServiceException se = new ServiceException(ErrorCodeEnum.DatabaseError);
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		return  list;
	}

	protected <T> Join<K,T> getJoin(String referenceName){
		Join<K,T> join = root.join(referenceName,JoinType.LEFT);
		countRoot.join(referenceName,JoinType.LEFT);
		return join;
	}


	public CriteriaBuilder getCriteriaBuilder() {
		return this.criteriaBuilder;
	}


	public CriteriaQuery<K> getCriteriaQuery() {
		return this.criteriaQuery;
	}


	public Root<K> getRoot() {
		return this.root;
	}
	@Override
	public Map<String, Path<Object>> getPathReferenceMap() {
		return this.pathReferenceMap;
	}
	@Override
	public Map<String, Join<K, ?>> getReferenceMap() {
		return this.referenceMap;
	}

}
