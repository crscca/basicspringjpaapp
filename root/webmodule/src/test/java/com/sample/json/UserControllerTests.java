package com.sample.json;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.sample.AbstractContextControllerTests;
import com.samples.entities.Address;
import com.samples.entities.Contact;
import com.samples.entities.IAddressHolder;
import com.samples.entities.User;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTests extends AbstractContextControllerTests {

	private static String URI = "/user/{action}";

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		WebApplicationContext wac2 = this.wac;
		
		this.mockMvc = webAppContextSetup(wac2).alwaysExpect(status().isOk()).build();
	}


	
	@Test 
	public void chweck()
	{
		
	}
	


@Test
public void createUser1() throws Exception {
	
	User user= new User("loginId5","password", "Mr", 'M', "FirstName1", "LastName1", "emailId@amail.com", "(011)2512-5189", new Date(2001-1900, 1-1,31));
	Address a = setAddress(user, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
	
	Contact contact= new Contact("Mr", 'M', "Contactfirstname", "Contactlastname", "contactemail@amail.com", "123456778");
	setAddress(contact, "line1", "line2", "line3", "city", "state", "country", "zipOrPin");
	
	user.getContacts().add(contact);
	ObjectMapper objectMapper = new ObjectMapper();
	 String inputString = objectMapper.writeValueAsString(user);
	
	
this.mockMvc.perform(
		post(URI, "create")
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputString.getBytes()))
			.andExpect(jsonPath("$.id", notNullValue()))
			.andExpect(jsonPath("$.address.id", notNullValue()))
			.andExpect(jsonPath("$.contacts", hasSize(1)))
			.andExpect(jsonPath("$.contacts[0].id", notNullValue()));			
}


	
private static Address setAddress(IAddressHolder u, String line1, String line2, String line3, String city, String state, String country, String zipOrPin) {
	Address a= new Address(line1, line2, line3, city, state, country, zipOrPin);
	u.setAddress(a);
	return a;
}
	
	
}
