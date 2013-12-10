package com.samples.main;

import java.util.Date;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.samples.entities.Address;
import com.samples.entities.Contact;
import com.samples.entities.IAddressHolder;
import com.samples.entities.User;

public class QuickMain {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" );
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		User u= new User("loginId1","password", "Mr", 'M', "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
		Address a = setAddress(u, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
		
		addDummyContact(entityManager, u, 2, 0);
		Set<Contact> contacts = u.getContacts();
		for (Contact contact : contacts) {
			Address address = contact.getAddress();
			//entityManager.persist(address  );
			//entityManager.persist(contact);
			//instead of above doing nothing and allowing cascade to handle it
		}
		
		entityManager.persist(a  );
		entityManager.persist(u  );
		
		entityManager.getTransaction().commit();
		entityManager.close();

	}

	private static void addDummyContact(EntityManager entityManager, User u, int noOfContacts, int prefix) {
		for (int i = 0; i < noOfContacts; i++) 
		{
			Contact c= new Contact("Miss", 'F', "Firstname"+i, "Lastname"+i, "emailId"+i+"@amail.com", "phone"+i);
			Address a1 = setAddress(c, "line1a"+i+prefix, "line2a", "line3", "city", "state", "country", "zipOrPin");
			u.getContacts().add(c);	
		}
		
		//entityManager.persist(a1  );
		//entityManager.persist(c  );
	}

	private static Address setAddress(IAddressHolder u, String line1, String line2, String line3, String city, String state, String country, String zipOrPin) {
		Address a= new Address(line1, line2, line3, city, state, country, zipOrPin);
		u.setAddress(a);
		return a;
	}

}
