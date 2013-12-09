package com.sample.json;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sample.AbstractTestNGTest;
import com.samples.entities.Address;
import com.samples.entities.Contact;
import com.samples.entities.IAddressHolder;
import com.samples.entities.User;


//@ContextConfiguration(locations={})
public class UserControllerIT extends AbstractTestNGTest{ 
	

	@Test()
	public void createUser() throws Exception {
		
		User user= new User("loginId1", "Mr", 'M', "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
		Address a = setAddress(user, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
		
		Contact contact= new Contact("Mr", 'M', "Contactfirstname", "Contactlastname", "contactemail@amail.com", "123456778");
		setAddress(contact, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
		
		user.getContacts().add(contact);
		IOData<User, User> io = processPostAndResponse(user, "http://localhost:8080/webmodule/user/create");
		
		
		Assert.assertNull(io.getInputObject().getId());
		Assert.assertNull(io.getInputObject().getAddress().getId());
		Assert.assertNotNull(io.getOutputObject().getId());
		Assert.assertNotNull(io.getOutputObject().getAddress().getId());
		io.getOutputAsMap().put("id", null);
		((Map)io.getOutputAsMap().get("address")).put("id", null);
		final List<Map> contacts = (List) io.getOutputAsMap().get("contacts");
		for (Map contact1 : contacts) {
			contact1.put("id", null);
			((Map)contact1.get("address")).put("id", null);
		}
		final Map outputAsMap = io.getOutputAsMap();
		final Map inputAsMap = io.getInputAsMap();
		matchMaps(outputAsMap, inputAsMap);
	}

	
	private static Address setAddress(IAddressHolder u, String line1, String line2, String line3, String city, String state, String country, String zipOrPin) {
		Address a= new Address(line1, line2, line3, city, state, country, zipOrPin);
		u.setAddress(a);
		return a;
	}
	

	
}
