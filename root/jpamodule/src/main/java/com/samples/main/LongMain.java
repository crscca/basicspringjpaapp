package com.samples.main;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.samples.dao.DAOFactory;
import com.samples.dao.GenericJpaDAO;
import com.samples.dao.UserDAO;
import com.samples.entities.User;

public class LongMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		DAOFactory factory = DAOFactory.instance(DAOFactory.JPA);
		
		UserDAO userDAO = factory.getUserDAO();
		
		GenericJpaDAO g=(GenericJpaDAO) userDAO;
		g.setEntityManager(entityManager);
		entityManager.getTransaction().begin();
		User u= new User("loginId1","password", "Mr", 'M', "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
		userDAO.makePersistent(u);
		
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
