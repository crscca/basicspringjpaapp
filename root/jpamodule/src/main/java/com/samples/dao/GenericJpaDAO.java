package com.samples.dao;

import javax.persistence.*;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.*;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;



/**
 * Implements the generic CRUD data access operations using Java Persistence APIs.
 * <p>
 * To write a DAO, subclass and parameterize this class with your entity.
 * Of course, assuming that you have a traditional 1:1 appraoch for
 * Entity:DAO design. This is actually an implementation that uses some
 * extensions for Java Persistence from Hibernate - you can see how the
 * packages for the extensions are not imported, but named inline.
 *
 * @author Christian Bauer
 */
public   class GenericJpaDAO<T,ID extends Serializable>
        implements GenericDAO<T, ID> {

	 private EntityManager em;
	private Class<T> entityBeanType;

   

    protected GenericJpaDAO() {
    	Type superClass = getClass()
        .getGenericSuperclass();
    
    	if(superClass==Object.class)
    	{
    		throw new RuntimeException("GenericJpaDAO() is meant only to be used for a super class constructor");
    	}
    		this.entityBeanType = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
    	
        
      
    }
    
    public GenericJpaDAO(Class<T> cls) {
    	Type superClass = getClass()
        .getGenericSuperclass();
    	if(superClass!=Object.class)
    	{
    		throw new RuntimeException("GenericJpaDAO(Class<T> cls) is meant only to be used direcly and not as a super constructor");
    	}
    	
    		this.entityBeanType = cls;
    	
        
      
    }
    
    
    

    // If this DAO is wired in as a Seam component, Seam injects the right persistence context
    // if a method on this DAO is called. If the caller is a conversational stateful component,
    // the persistence context will be scoped to the conversation, not to the method call.
    // You can call this method and set the EntityManager manually, in an integration test.
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    protected EntityManager getEntityManager() {
        if (em == null)
            throw new IllegalStateException("EntityManager has not been set on DAO before usage");
        return em;
    }

    public Class<T> getEntityBeanType() {
        return entityBeanType;
    }

    public T findById(ID id, boolean lock) {
        T entity;
        if (lock) {
            entity = getEntityManager().find(getEntityBeanType(), id);
            em.lock(entity, javax.persistence.LockModeType.WRITE);
        } else {
            entity = getEntityManager().find(getEntityBeanType(), id);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getEntityManager().createQuery("from " + getEntityBeanType().getName() ).getResultList();
    }


    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        // Using Hibernate, more difficult with EntityManager and EJB-QL
        org.hibernate.Criteria crit = ((org.hibernate.ejb.HibernateEntityManager)getEntityManager())
                            .getSession()
                            .createCriteria(getEntityBeanType());
        org.hibernate.criterion.Example example =
                org.hibernate.criterion.Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    public T makePersistent(T entity) {
        return getEntityManager().merge(entity);
    }

    public void makeTransient(T entity) {
        getEntityManager().remove(entity);
    }

    public void flush() {
        getEntityManager().flush();
    }

    public void clear() {
        getEntityManager().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(org.hibernate.criterion.Criterion... criterion) {
        // Using Hibernate, more difficult with EntityManager and EJB-QL
        org.hibernate.Session session =
                ((org.hibernate.ejb.HibernateEntityManager)getEntityManager()).getSession();
        org.hibernate.Criteria crit
                = session.createCriteria(getEntityBeanType());
        for (org.hibernate.criterion.Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }

	@Override
	public void makeTransient(ID id, boolean lock) {
		T entity=findById(id, lock);
		 getEntityManager().remove(entity);
		
	}
	
	
	public void updateField(ID id, boolean lock, String fieldName, Object value) {
		T entity=findById(id, lock);
		try {
			BeanUtils.copyProperty(entity, fieldName, value);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error copying the property", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Error copying the property", e);
		}
		 getEntityManager().merge(entity);
		
	}

	
public List<T> findAll(int firstResult, int maxResults) {
		
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(getEntityBeanType());
		
		String q="select m from "+getEntityBeanType().getSimpleName()+" m ";//+"left join fetch m.additionals";
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) 
		{
			Class<?> propertyType = propertyDescriptor.getPropertyType();
			if(propertyType.isAssignableFrom(Set.class)||propertyType.isAssignableFrom(List.class))
			{
				q+=" left join fetch m.";
				q+=propertyDescriptor.getName();
			}
		}
		Class cz=getEntityBeanType();
		
		System.out.println("-----------q="+q);
		TypedQuery<T> query = this.em.createQuery(q,
				getEntityBeanType());
		
		return query.setFirstResult(firstResult).setMaxResults(maxResults)
				.getResultList();
	}

	public Long count() {
		TypedQuery<Long> query = this.em.createQuery(
				"select count(m) from "+getEntityBeanType().getSimpleName()+" m", Long.class);
		return query.getSingleResult();
	}

}

