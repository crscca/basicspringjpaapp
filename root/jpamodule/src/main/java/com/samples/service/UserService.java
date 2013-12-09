package com.samples.service;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.samples.dao.GenericJpaDAO;
import com.samples.dao.UserDAO;
import com.samples.entities.User;

@Repository
@Transactional
public class UserService {

	private GenericJpaDAO<User, Long> userDao;

	public User createuser(User u) {
		
		return userDao.makePersistent(u);
	}

	public void deleteUser(Long id) {
		userDao.makeTransient(id, false);
	}
	
	public void updateField(Long id, boolean lock, String fieldName, Object value)
	{
		userDao.updateField(id, lock, fieldName, value);
		//throw new RuntimeException("testing rollback");
	}

	public void setUserDao(GenericJpaDAO<User, Long> userDao) {
		this.userDao = userDao;
	}
	
	

}
