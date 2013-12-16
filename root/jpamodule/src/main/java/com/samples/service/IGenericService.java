package com.samples.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.samples.beans.PageReturnData;
import com.samples.dao.GenericJpaDAO;

public interface IGenericService<T, ID extends Serializable> {

	public abstract T create(T u);

	public abstract void delete(ID id);

	public abstract void updateField(ID id, boolean lock, String fieldName,
			Object value);

	public abstract void setDao(GenericJpaDAO<T, ID> dao);

	public abstract T findById(ID id, boolean lock);

	public abstract List<T> findAll();
	 public PageReturnData<T> getPage(int pageSize, int pageNo);

}