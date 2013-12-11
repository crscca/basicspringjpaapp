package com.samples.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.samples.dao.GenericJpaDAO;
import com.samples.dao.UserDAO;
import com.samples.entities.User;

@Repository
@Transactional
public class GenericService<T, ID extends Serializable> implements IGenericService<T, ID> {

	private GenericJpaDAO<T, ID> dao;
	private Class<T> entityBeanType;

	   

    protected GenericService() {
    	Type superClass = getClass()
        .getGenericSuperclass();
    
    	if(superClass==Object.class)
    	{
    		throw new RuntimeException("GenericService() is meant only to be used for a super class constructor");
    	}
    		this.entityBeanType = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
    	
        
      
    }
    
    public GenericService(Class<T> cls) {
    	Type superClass = getClass()
        .getGenericSuperclass();
    	if(superClass!=Object.class)
    	{
    		throw new RuntimeException("GenericService(Class<T> cls) is meant only to be used direcly and not as a super constructor");
    	}
    	
    		this.entityBeanType = cls;
    	
        
      
    }


	/* (non-Javadoc)
	 * @see com.samples.service.IGenericService#create(T)
	 */
	@Override
	public T create(T u) {
		
		return dao.makePersistent(u);
	}

	/* (non-Javadoc)
	 * @see com.samples.service.IGenericService#delete(ID)
	 */
	@Override
	public void delete(ID id) {
		dao.makeTransient(id, false);
	}
	
	/* (non-Javadoc)
	 * @see com.samples.service.IGenericService#updateField(ID, boolean, java.lang.String, java.lang.Object)
	 */
	@Override
	public void updateField(ID id, boolean lock, String fieldName, Object value)
	{
		dao.updateField(id, lock, fieldName, value);
		//throw new RuntimeException("testing rollback");
	}

	/* (non-Javadoc)
	 * @see com.samples.service.IGenericService#setDao(com.samples.dao.GenericJpaDAO)
	 */
	@Override
	public void setDao(GenericJpaDAO<T, ID> dao) {
		this.dao = dao;
	}
	
	/* (non-Javadoc)
	 * @see com.samples.service.IGenericService#findById(ID, boolean)
	 */
	@Override
	@Transactional(readOnly=true)
	 public T findById(ID id, boolean lock)
	 {
		return dao.findById(id, lock);
	 }
	/* (non-Javadoc)
	 * @see com.samples.service.IGenericService#findAll()
	 */
	@Override
	@Transactional(readOnly=true)
	 public    List<T> findAll()
	 {
		 return dao.findAll();
	 }

	

}
