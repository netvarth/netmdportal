/**
 * 
 */
package com.nv.youNeverWait.pl.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;


public interface GenericDao extends Serializable{
	public  void save(final Object obj);
	public  void update(final Object obj);
	public  void  delete(Object obj);
	public <T>  T getById(Class<T> className ,int id);
	public <T>  T getByUid(Class<T> className ,int uid);
	public BigInteger getNextSequence(String sequence);
	public <T> List<T> loadAll(Class<T> className);


}
