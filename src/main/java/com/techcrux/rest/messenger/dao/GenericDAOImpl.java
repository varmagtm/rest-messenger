package com.techcrux.rest.messenger.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techcrux.rest.messenger.exceptions.DataAccessException;


@Repository
public class GenericDAOImpl implements GenericDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public <T> T findByPrimaryKey(Class<T> entityClass, Serializable id) throws DataAccessException {
        try {
            Session session = sessionFactory.openSession();
            return session.get(entityClass, id);
        }
        catch (HibernateException e) {
            throw new DataAccessException("Error in getting entity by primary key", e);
        }
    }

    public <T> T save(T entity) throws DataAccessException {
        try {
            sessionFactory.openSession().save(entity);
            return entity;
        }
        catch (HibernateException e) {
            throw new DataAccessException("Error in persisting entity.", e);
        }

    }

    public <T> T merge(T entity) throws DataAccessException {
        try {
            Session session = sessionFactory.openSession();
            session.merge(entity);
            return entity;
        }
        catch (HibernateException e) {
            throw new DataAccessException("Error in merging entity.", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public <T> List<T> search(Class<T> entityClass, Map<String, Object> queryConditions) throws DataAccessException {
        try {
            Criteria criteria = sessionFactory.openSession().createCriteria(entityClass);
            if (queryConditions != null && !queryConditions.isEmpty()) {
                Set<Entry<String,Object>> entrySet = queryConditions.entrySet();
                for (Entry<String, Object> entry : entrySet) {
                    criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
                }
            }
            return criteria.list();
        }
        catch (HibernateException e) {
            throw new DataAccessException("Error in searching entities.", e);
        }
    }

}
