package com.samples.main;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.samples.dao.UserDAO;
import com.samples.entities.Address;
import com.samples.entities.IAddressHolder;
import com.samples.entities.User;
import com.samples.service.UserService;


public class Check {

	public static void main(String[] args) {
		User u= new User("loginId1", "Mr", 'M', "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
		Address a = setAddress(u, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
		create(u);
			

	}

	public static User create(User u) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext(new String[] {"application.xml"});
		UserService bo = (UserService) context.getBean("userService");
		
		u=bo.createuser(u);
		long id=u.getId();
		//bo.updateField(id, false, "firstName", "Ra");
		//bo.deleteUser(id);
		return u;
	}
	
	private static Address setAddress(IAddressHolder u, String line1, String line2, String line3, String city, String state, String country, String zipOrPin) {
		Address a= new Address(line1, line2, line3, city, state, country, zipOrPin);
		u.setAddress(a);
		return a;
	}

}
