/**
 * 
 */
package com.techcrux.rest.messenger.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.techcrux.rest.messenger.exceptions.DataAccessException;

/**
 * @author rnagaraju
 *
 */
public interface GenericDAO {

	public <T> T findByPrimaryKey(Class<T> entityClass, Serializable id) throws DataAccessException;

	public <T> T save(T entity) throws DataAccessException;

	public <T> T merge(T entity) throws DataAccessException;

	public <T> List<T> search(Class<T> entityClass, Map<String, Object> queryConditions) throws DataAccessException;

}
